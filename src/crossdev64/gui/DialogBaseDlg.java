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

public class DialogBaseDlg
	extends JDialog
{
	private static final long serialVersionUID = 1L;

	private boolean mOK;

	public DialogBaseDlg(Window oParent)
	{
		super(oParent);
		mOK = false;
		initDialog();
	}

	protected void initDialog()
	{
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
				buttonPane.add(cancelButton);
			}
		}
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
