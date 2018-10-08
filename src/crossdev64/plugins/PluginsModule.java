package crossdev64.plugins;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.ModuleSettings;

public class PluginsModule
	extends ModuleSettings
{
	public static final String MODULE_ID = "720FE006-1621-4D09-A1B7-485C7A9E781F";

	public PluginsModule()
	{
		super(MODULE_ID, GlobalSettings.getResourceString("string.plugins"));
	}

	@Override
	public boolean canAdd()
	{
		return true;
	}
}
