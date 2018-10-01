package crossdev64.gui;

import java.awt.Window;

public class StringInputDlg
	extends DialogBaseDlg<StringInputPanel>
{
	private static final long serialVersionUID = 1L;

	public StringInputDlg(Window oParent, String oTitle, String oInfotext, String oValue)
	{
		super(oParent, new StringInputPanel(oTitle, oInfotext, oValue));
		//setBounds(100, 100, 337, 122);
	}

	public String getInputString()
	{
		return getPanel().getInputString();
	}
}
