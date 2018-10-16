package crossdev64.emulator.vice;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import crossdev64.gui.DialogBasePanel;
import crossdev64.settings.GlobalSettings;
import crossdev64.utils.NamedTextCtrl;
import crossdev64.utils.PathDialogCtrl;
import crossdev64.utils.Stack;

public class VICESettingsPanel
	extends DialogBasePanel
{
	private static final long serialVersionUID = 1L;
	private NamedTextCtrl mPort;
	PathDialogCtrl mInstallationPathCtrl;

	public VICESettingsPanel()
	{
		super();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		mInstallationPathCtrl = new PathDialogCtrl();
		mInstallationPathCtrl.setBrowseText(GlobalSettings.getResourceString("string.browse"));
		mInstallationPathCtrl.setLabelText(GlobalSettings.getResourceString("string.installation_path"));
		GridBagConstraints gbc_mInstallationPathCtrl = new GridBagConstraints();
		gbc_mInstallationPathCtrl.fill = GridBagConstraints.HORIZONTAL;
		gbc_mInstallationPathCtrl.gridwidth = 3;
		gbc_mInstallationPathCtrl.insets = new Insets(0, 0, 5, 0);
		gbc_mInstallationPathCtrl.gridx = 0;
		gbc_mInstallationPathCtrl.gridy = 0;
		add(mInstallationPathCtrl, gbc_mInstallationPathCtrl);

		mPort = new NamedTextCtrl();
		mPort.setLabelText(GlobalSettings.getResourceString("string.port"));
		GridBagConstraints gbc_mPort = new GridBagConstraints();
		gbc_mPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_mPort.gridwidth = 2;
		gbc_mPort.insets = new Insets(0, 0, 5, 5);
		gbc_mPort.gridx = 0;
		gbc_mPort.gridy = 1;
		add(mPort, gbc_mPort);
//		mPort.setColumns(5);
	}

	public String getInstallationPath()
	{
		return mInstallationPathCtrl.getText();
	}

	public void setInstallationPath(String oPath)
	{
		mInstallationPathCtrl.setText(oPath);
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

	protected void onBrowse()
	{
		System.out.println(Stack.getSourcePosition()+"Browse");
	}
}
