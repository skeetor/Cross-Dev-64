package crossdev64.emulator.vice;

import java.awt.Window;
import java.util.UUID;

import javax.swing.JPanel;

import crossdev64.gui.CopyableModule;
import crossdev64.settings.ModuleSettings;

public class VICESettingsNode
	extends CopyableModule
{
	private VICESettingsPanel mPanel;

	public VICESettingsNode()
	{
		super("VICE", UUID.randomUUID().toString().toUpperCase());
		mPanel = new VICESettingsPanel();
		mPanel.setPort(6510);
	}

	public VICESettingsNode(VICESettingsNode oSource)
	{
		this();
		copy(oSource);
	}

	public void copy(VICESettingsNode oSource)
	{
		if(oSource == null)
			return;

		setInstallationPath(oSource.getInstallationPath());
		setPort(oSource.getPort());
	}

	/**
	 * Constructor to be used for loading from settings, where the UUID is already known and
	 * stored in the settings/project file.
	 * 
	 * @param oUUID
	 */
	public VICESettingsNode(String oUUID)
	{
		super("VICE", oUUID);
		mPanel = new VICESettingsPanel();
	}

	@Override
	public JPanel getConfigPanel()
	{
		return getPanel();
	}

	@Override
	public ModuleSettings createItem(Window oParent, ModuleSettings oDefault)
	{
		return new VICESettingsNode((VICESettingsNode)oDefault);
	}

	private VICESettingsPanel getPanel()
	{
		return mPanel;
	}
	
	public String getInstallationPath()
	{
		return mPanel.getInstallationPath();
	}

	public void setInstallationPath(String oPath)
	{
		mPanel.setInstallationPath(oPath);
	}

	public int getPort()
	{
		return mPanel.getPort();
	}
	
	public void setPort(int nPort)
	{
		mPanel.setPort(nPort);
	}
}
