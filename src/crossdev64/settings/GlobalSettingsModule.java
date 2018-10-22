package crossdev64.settings;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GlobalSettings")
public class GlobalSettingsModule
	extends ModuleSettings
{
	public static final String MODULE_ID = "5A75ED5D-6A8E-48F5-A1EF-68C823D8443E";
	protected static final boolean REGISTERED = ModuleSettings.registerModule(GlobalSettingsModule.class);

	public GlobalSettingsModule()
	{
		super(MODULE_ID, GlobalSettings.getResourceString("string.settings"), null);
	}
}
