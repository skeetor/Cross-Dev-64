package crossdev64.emulator.vice;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import crossdev64.gui.DialogBasePanel;
import crossdev64.gui.controls.BrowseButton;
import crossdev64.settings.GlobalSettings;
import crossdev64.utils.Stack;

public class VICESettingsPanel
	extends DialogBasePanel
{
	private static final long serialVersionUID = 1L;
	private JTextField mInstallationPathTxt;
	private JTextField mPortTxt;

	public VICESettingsPanel()
	{
		super();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblStringinstallationpath = new JLabel(GlobalSettings.getResourceString("string.installation_path"));
		GridBagConstraints gbc_lblStringinstallationpath = new GridBagConstraints();
		gbc_lblStringinstallationpath.anchor = GridBagConstraints.WEST;
		gbc_lblStringinstallationpath.insets = new Insets(0, 0, 5, 5);
		gbc_lblStringinstallationpath.gridx = 0;
		gbc_lblStringinstallationpath.gridy = 0;
		add(lblStringinstallationpath, gbc_lblStringinstallationpath);
		
		mInstallationPathTxt = new JTextField();
		GridBagConstraints gbc_mInstallationPathTxt = new GridBagConstraints();
		gbc_mInstallationPathTxt.gridwidth = 2;
		gbc_mInstallationPathTxt.insets = new Insets(0, 0, 5, 5);
		gbc_mInstallationPathTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_mInstallationPathTxt.gridx = 1;
		gbc_mInstallationPathTxt.gridy = 0;
		add(mInstallationPathTxt, gbc_mInstallationPathTxt);
		mInstallationPathTxt.setColumns(10);

		BrowseButton btnNewButton = new BrowseButton(GlobalSettings.getResourceString("string.browse"))
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean onBrowse(ActionEvent oEvent)
			{
				super.setPath(getInstallationPath());
				boolean rc = super.onBrowse(oEvent);
				if(rc)	
					setInstallationPath(super.getPath());

				return rc;
			}
		};

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel = new JLabel(GlobalSettings.getResourceString("string.port"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		mPortTxt = new JTextField();
		GridBagConstraints gbc_mPortTxt = new GridBagConstraints();
		gbc_mPortTxt.anchor = GridBagConstraints.WEST;
		gbc_mPortTxt.insets = new Insets(0, 0, 0, 5);
		gbc_mPortTxt.gridx = 1;
		gbc_mPortTxt.gridy = 1;
		add(mPortTxt, gbc_mPortTxt);
		mPortTxt.setColumns(5);
	}

	public String getInstallationPath()
	{
		return mInstallationPathTxt.getText();
	}

	public void setInstallationPath(String oPath)
	{
		mInstallationPathTxt.setText(oPath);
	}
	
	public int getPort()
	{
		int port = 6510;
		String s = mPortTxt.getText();
		if(s.isEmpty())
			return port;

		try
		{
			port = Integer.parseInt(s);
		}
		catch(Exception e)
		{
		}
		
		return port;
	}
	
	public void setPort(int nPort)
	{
		mPortTxt.setText(""+nPort);
	}

	protected void onBrowse()
	{
		System.out.println(Stack.getSourcePosition()+"Browse");
	}
}
