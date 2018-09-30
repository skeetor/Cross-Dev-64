package crossdev64.gui;

import java.awt.Window;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class StringInputDlg
	extends DialogBaseDlg
{
	private static final long serialVersionUID = 1L;

	/**
	 * @wbp.parser.constructor
	 */
	public StringInputDlg(Window oParent)
	{
		super(oParent);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
	}

	public StringInputDlg(Window oParent, String oInfotext)
	{
		super(oParent);
	}
}
