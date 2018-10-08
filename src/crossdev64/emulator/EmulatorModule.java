package crossdev64.emulator;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.ModuleSettings;

public class EmulatorModule
	extends ModuleSettings
{
	public static final String MODULE_ID = "21453DC0-866F-433B-95D4-E25C984D9824";

	public EmulatorModule()
	{
		super(MODULE_ID, GlobalSettings.getResourceString("string.emulator"));
	}
}
