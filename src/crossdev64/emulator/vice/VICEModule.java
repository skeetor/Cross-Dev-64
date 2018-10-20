package crossdev64.emulator.vice;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import crossdev64.gui.CopyableModule;
import crossdev64.settings.ModuleSettings;

@XmlRootElement(name = "VICEModule")
public class VICEModule
	extends CopyableModule
{
	protected static final boolean REGISTERED = ModuleSettings.registerModule(VICEModule.class);

	private String mInstallationPath = "";
	private int mPort = 6510;
	
	public VICEModule()
	{
		super(UUID.randomUUID().toString().toUpperCase(), "VICE", new VICESettingsPanel());
		setPort(6510);
	}

	public VICEModule(VICEModule oSource)
	{
		this();
		copy(oSource);
	}

	/**
	 * Constructor to be used for loading from settings, where the UUID is already known and
	 * stored in the settings/project file.
	 * 
	 * @param oUUID
	 */
	public VICEModule(String oUUID)
	{
		super("VICE Module", oUUID, new VICESettingsPanel());
	}

	@JsonIgnore
	@Override
	public void copy(ModuleSettings oSource)
	{
		if(oSource == null || !(oSource instanceof VICEModule))
			return;

		VICEModule module = (VICEModule)oSource;

		super.copy(module);
		setInstallationPath(module.getInstallationPath());
		setPort(module.getPort());
	}

	@Override
	public boolean allowChilds()
	{
		return false;
	}

	@Override
	public VICESettingsPanel getConfigPanel()
	{
		VICESettingsPanel panel = (VICESettingsPanel)super.getConfigPanel();

		panel.setInstallationPath(getInstallationPath());
		panel.setPort(getPort());
		
		return panel;
	}

	@Override
	public void onApply()
	{
		VICESettingsPanel panel = (VICESettingsPanel)super.getConfigPanel();
		setInstallationPath(panel.getInstallationPath());
		setPort(panel.getPort());
	}

	@Override
	public ModuleSettings createItem(ModuleSettings oDefault)
	{
		return new VICEModule((VICEModule)oDefault);
	}

	@XmlElement(name="InstallationPath")
	public String getInstallationPath()
	{
		return mInstallationPath;
	}

	public void setInstallationPath(String oPath)
	{
		mInstallationPath = oPath;
	}

	@XmlElement(name="Port")
	public int getPort()
	{
		return mPort;
	}

	public void setPort(int nPort)
	{
		mPort = nPort;
	}
}
