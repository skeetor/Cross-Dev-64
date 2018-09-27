package crossdev64.gui;

import java.awt.Window;

import javax.swing.JDialog;

public class DialogBaseDlg
	extends JDialog
{
	private static final long serialVersionUID = 1L;

	private boolean mOK;

	public DialogBaseDlg(Window oParent)
	{
		super(oParent);
		mOK = false;
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
