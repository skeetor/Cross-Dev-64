package crossdev64.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import crossdev64.settings.GlobalSettings;

public class DialogBaseDlg<T extends DialogBasePanel>
	extends JDialog
{
	private static final long serialVersionUID = 1L;

	private boolean mOK;
	private T mPanel;

	/**
	 * @wbp.parser.constructor
	 */
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
		addOKButton(buttonPanel);
		addCancelButton(buttonPanel);
		getContentPane().add(oPanel, BorderLayout.CENTER);
	}

	protected void addOKButton(JPanel oButtonPanel)
	{
		JButton okButton = new JButton(GlobalSettings.getInstance().getResourceString("string.ok"));
		okButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				onOK();
			}
		});
		oButtonPanel.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}

	protected void addCancelButton(JPanel oButtonPanel)
	{
		JButton cancelButton = new JButton(GlobalSettings.getInstance().getResourceString("string.cancel"));
		cancelButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				onCancel();
			}
		});
		oButtonPanel.add(cancelButton);
	}

	protected JPanel createButtonPanel()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		return buttonPanel;
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

	protected void onCancel()
	{
		mOK = false;
		setVisible(false);
	}
}
