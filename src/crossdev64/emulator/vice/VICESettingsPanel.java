package crossdev64.emulator.vice;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import crossdev64.gui.DialogBasePanel;
import crossdev64.settings.GlobalSettings;

public class VICESettingsPanel
	extends DialogBasePanel
{
	private static final long serialVersionUID = 1L;

	private JTextField mInstallationPath;
	private JTextField mPort;

	public VICESettingsPanel()
	{
		super();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel(GlobalSettings.getResourceString("string.installation_path"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		mInstallationPath = new JTextField();
		GridBagConstraints gbc_mInstallationPath = new GridBagConstraints();
		gbc_mInstallationPath.fill = GridBagConstraints.HORIZONTAL;
		gbc_mInstallationPath.gridwidth = 2;
		gbc_mInstallationPath.insets = new Insets(0, 0, 5, 5);
		gbc_mInstallationPath.gridx = 1;
		gbc_mInstallationPath.gridy = 0;
		add(mInstallationPath, gbc_mInstallationPath);
		mInstallationPath.setColumns(10);

		JButton btnNewButton = new JButton(GlobalSettings.getResourceString("string.browse"));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel(GlobalSettings.getResourceString("string.port"));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		mPort = new JTextField();
		GridBagConstraints gbc_mPort = new GridBagConstraints();
		gbc_mPort.anchor = GridBagConstraints.WEST;
		gbc_mPort.insets = new Insets(0, 0, 5, 5);
		gbc_mPort.gridx = 1;
		gbc_mPort.gridy = 1;
		add(mPort, gbc_mPort);
		mPort.setColumns(5);
	}

	public String getInstallationPath()
	{
		return mInstallationPath.getText();
	}

	public void setInstallationPath(String oPath)
	{
		mInstallationPath.setText(oPath);
	}
	
	public int getPort()
	{
		int port = 6510;
		String s = mPort.getText();
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
		mPort.setText(""+nPort);
	}
}
