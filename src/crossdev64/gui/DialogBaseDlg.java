package crossdev64.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import crossdev64.settings.GlobalSettings;

public class DialogBaseDlg<T extends DialogBasePanel>
	extends JDialog
{
	private static final long serialVersionUID = 1L;

	private boolean mOK;
	private T mPanel;
	private JPanel mButtonPanel;
	private boolean mShowApply = true;
	private boolean mShowCancel = true;
	private boolean mShowOK = true;
	private String mOKText = "string.ok";
	private String mCancelText = "string.cancel";
	private String mApplyText = "string.apply";
	
	public DialogBaseDlg()
	{
		super();
		mOK = false;
	}

	public DialogBaseDlg(Window oParent, T oPanel)
	{
		super(oParent);
		mOK = false;
		initDialog(oPanel);
	}

	protected void initDialog(T oPanel)
	{
		mPanel = oPanel;
		setTitle(oPanel.getTitle());
		JPanel buttonPanel = createButtonPanel();

		if(mShowOK)
		{
			JButton button = new JButton(GlobalSettings.getResourceString(mOKText));
			button.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					onOK();
				}
			});
			buttonPanel.add(button);
			getRootPane().setDefaultButton(button);
		}

		if(mShowApply)
		{
			JButton button = new JButton(GlobalSettings.getResourceString(mApplyText));
			button.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					onApply();
				}
			});
			buttonPanel.add(button);
		}

		if(mShowCancel)
		{
			JButton button = new JButton(GlobalSettings.getResourceString(mCancelText));
			button.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					onCancel();
				}
			});
			buttonPanel.add(button);
		}
		getContentPane().add(oPanel, BorderLayout.CENTER);
	}

	protected JPanel createButtonPanel()
	{
		mButtonPanel = new JPanel();
		mButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(mButtonPanel, BorderLayout.SOUTH);

		return mButtonPanel;
	}

	public void setOKText(String oOKResourceText)
	{
		mOKText = oOKResourceText;
	}

	public void setCancelText(String oCancelResourceText)
	{
		mCancelText = oCancelResourceText;
	}

	public void setApplyText(String oApplyResourceText)
	{
		mApplyText = oApplyResourceText;
	}

	public void showOK(boolean bShowOK)
	{
		mShowOK = bShowOK;
	}

	public void showCancel(boolean bShowCancel)
	{
		mShowCancel = bShowCancel;
	}

	public void showApply(boolean bShowApply)
	{
		mShowApply = bShowApply;
	}
	
	protected T getPanel()
	{
		return mPanel;
	}

	/**
	 * Returns true if the OK button was used, otherwise false.
	 * 
	 * @return
	 */
	public boolean showModal()
	{
		mOK = false;
		setModal(true);
		setVisible(true);
		return mOK;
	}

	public void setOK(boolean bOK)
	{
		mOK = bOK;
	}

	protected void onOK()
	{
		mOK = true;
		setVisible(false);
	}

	protected void onApply()
	{
	}

	protected void onCancel()
	{
		mOK = false;
		setVisible(false);
	}
	
	public void center(Component oParent, int nWidth, int nHeight)
	{
		Point p = new Point();
		p.x = 0;
		p.y = 0;
		SwingUtilities.convertPointToScreen(p, oParent);
		Rectangle dr = oParent.getBounds();

		setBounds(p.x + (dr.width-nWidth)/2, p.y+(dr.height-nHeight)/2, nWidth, nHeight);
	}
}
