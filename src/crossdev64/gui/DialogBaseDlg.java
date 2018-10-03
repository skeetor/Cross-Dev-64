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
import crossdev64.utils.Stack;

public class DialogBaseDlg<T extends DialogBasePanel>
	extends JDialog
{
	private static final long serialVersionUID = 1L;

	private boolean mOK;
	private T mPanel;
	private JPanel buttonPanel_1;

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
		{
			JButton button = new JButton(GlobalSettings.getInstance().getResourceString("string.ok"));
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
		{
			JButton button = new JButton(GlobalSettings.getInstance().getResourceString("string.apply"));
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
		{
			JButton button = new JButton(GlobalSettings.getInstance().getResourceString("string.cancel"));
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
		buttonPanel_1 = new JPanel();
		buttonPanel_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPanel_1, BorderLayout.SOUTH);

		return buttonPanel_1;
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
		System.out.println(Stack.getSourcePosition()+"Apply");
	}

	protected void onCancel()
	{
		mOK = false;
		setVisible(false);
	}
}
