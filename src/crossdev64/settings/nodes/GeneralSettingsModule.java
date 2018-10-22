package crossdev64.settings.nodes;

import javax.xml.bind.annotation.XmlRootElement;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.ModuleSettings;

@XmlRootElement(name="GeneralSettings")
public class GeneralSettingsModule
	extends ModuleSettings
{
	public static final String MODULE_ID = "070B3D0E-5CEC-4EA5-9501-46ACD3210CF5";
	protected static final boolean REGISTERED = ModuleSettings.registerModule(GeneralSettingsModule.class);

	public GeneralSettingsModule()
	{
		super(MODULE_ID, GlobalSettings.getResourceString("string.general"), null);
	}
}
