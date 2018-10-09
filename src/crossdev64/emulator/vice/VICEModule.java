package crossdev64.emulator.vice;

import java.awt.Window;
import java.util.UUID;

import javax.swing.JPanel;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import crossdev64.gui.CopyableModule;
import crossdev64.settings.ModuleSettings;

@XmlRootElement(name = "VICEModule")
public class VICEModule
	extends CopyableModule
{
	private VICESettingsPanel mPanel;

	public VICEModule()
	{
		super(UUID.randomUUID().toString().toUpperCase(), "VICE");
		mPanel = new VICESettingsPanel();
		mPanel.setPort(6510);
	}

	public VICEModule(VICEModule oSource)
	{
		this();
		copy(oSource);
	}

	public void copy(VICEModule oSource)
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
	public VICEModule(String oUUID)
	{
		super("VICE Module", oUUID);
		mPanel = new VICESettingsPanel();
	}

	@Override
	public boolean isNode()
	{
		return false;
	}

	@Override
	public JPanel getConfigPanel()
	{
		return getPanel();
	}

	@Override
	public ModuleSettings createItem(Window oParent, ModuleSettings oDefault)
	{
		return new VICEModule((VICEModule)oDefault);
	}

	private VICESettingsPanel getPanel()
	{
		return mPanel;
	}

	@XmlElement(name="InstallationPath")
	public String getInstallationPath()
	{
		return mPanel.getInstallationPath();
	}

	public void setInstallationPath(String oPath)
	{
		mPanel.setInstallationPath(oPath);
	}

	@XmlElement(name="Port")
	public int getPort()
	{
		return mPanel.getPort();
	}

	public void setPort(int nPort)
	{
		mPanel.setPort(nPort);
	}
}
