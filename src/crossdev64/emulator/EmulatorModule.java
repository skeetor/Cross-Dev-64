package crossdev64.emulator;

import javax.xml.bind.annotation.XmlRootElement;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.ModuleSettings;

@XmlRootElement(name="Emulators")
public class EmulatorModule
	extends ModuleSettings
{
	public static final String MODULE_ID = "21453DC0-866F-433B-95D4-E25C984D9824";
	protected static final boolean REGISTERED = ModuleSettings.registerModule(EmulatorModule.class);

	public EmulatorModule()
	{
		super(MODULE_ID, GlobalSettings.getResourceString("string.emulator"));
	}
}
