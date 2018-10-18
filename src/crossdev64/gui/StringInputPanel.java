package crossdev64.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class StringInputPanel
	extends DialogBasePanel
{
	private static final long serialVersionUID = 1L;

	private JTextField mStringEdit;
	private String mTitle;

	public StringInputPanel(String oTitle, String oInfotext, String oValue)
	{
		super();

		mTitle = oTitle;

		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{126};
		gridBagLayout.rowHeights = new int[]{20};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JEditorPane dtrpnInfo = new JEditorPane();
		dtrpnInfo.setText(oInfotext);
		dtrpnInfo.setEnabled(false);
		dtrpnInfo.setEditable(false);
		GridBagConstraints gbc_dtrpnInfo = new GridBagConstraints();
		gbc_dtrpnInfo.fill = GridBagConstraints.BOTH;
		gbc_dtrpnInfo.insets = new Insets(0, 0, 5, 0);
		gbc_dtrpnInfo.gridx = 0;
		gbc_dtrpnInfo.gridy = 0;
		add(dtrpnInfo, gbc_dtrpnInfo);
		
		mStringEdit = new JTextField();
		GridBagConstraints gbc_mStringEdit = new GridBagConstraints();
		gbc_mStringEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_mStringEdit.anchor = GridBagConstraints.NORTH;
		gbc_mStringEdit.gridx = 0;
		gbc_mStringEdit.gridy = 1;
		add(mStringEdit, gbc_mStringEdit);
		if(oValue != null)
			mStringEdit.setText(oValue);
	}

	@Override
	public String getTitle()
	{
		return mTitle;
	}

	public String getInputString()
	{
		return mStringEdit.getText();
	}
}
