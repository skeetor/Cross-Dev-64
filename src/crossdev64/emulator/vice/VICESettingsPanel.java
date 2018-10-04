package crossdev64.emulator.vice;

import crossdev64.gui.DialogBasePanel;
import crossdev64.settings.GlobalSettings;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

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
		mPort.setColumns(10);
	}
	
	public JTextField getInstallationPath()
	{
		return mInstallationPath;
	}
	
	public JTextField getPort()
	{
		return mPort;
	}
}
