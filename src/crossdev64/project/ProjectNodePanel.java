package crossdev64.project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import crossdev64.gui.DialogBasePanel;
import crossdev64.settings.GlobalSettings;

public class ProjectNodePanel
	extends DialogBasePanel
{
	private static final long serialVersionUID = 1L;

	private JTextField mProjectRootPath;

	public ProjectNodePanel()
	{
		super();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel(GlobalSettings.getResourceString("string.default_project_root_path"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		mProjectRootPath = new JTextField();
		GridBagConstraints gbc_mInstallationPath = new GridBagConstraints();
		gbc_mInstallationPath.fill = GridBagConstraints.HORIZONTAL;
		gbc_mInstallationPath.gridwidth = 2;
		gbc_mInstallationPath.insets = new Insets(0, 0, 5, 5);
		gbc_mInstallationPath.gridx = 1;
		gbc_mInstallationPath.gridy = 0;
		add(mProjectRootPath, gbc_mInstallationPath);
		mProjectRootPath.setColumns(10);

		JButton btnNewButton = new JButton(GlobalSettings.getResourceString("string.browse"));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
	}

	public String getProjectRootPath()
	{
		return mProjectRootPath.getText();
	}

	public void setProjectRootPath(String oPath)
	{
		mProjectRootPath.setText(oPath);
	}
}
