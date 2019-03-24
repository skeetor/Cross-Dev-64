package crossdev64.gui;

public class StringInputDlg
	extends DialogBaseDlg<StringInputPanel>
{
	private static final long serialVersionUID = 1L;

	public StringInputDlg()
	{
		super();
		showApply(false);
	}

	public StringInputDlg(String oTitle, String oInfotext, String oValue)
	{
		super();
		showApply(false);
		setParams(oTitle, oInfotext, oValue);
	}

	public void setParams(String oTitle, String oInfotext, String oValue)
	{
		initDialog(new StringInputPanel(oTitle, oInfotext, oValue));
	}
	
	public String getInputString()
	{
		return getPanel().getInputString();
	}
}
