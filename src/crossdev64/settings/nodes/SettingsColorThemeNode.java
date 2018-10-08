package crossdev64.settings.nodes;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.ModuleSettings;

public class SettingsColorThemeNode
	extends ModuleSettings
{
	public static final String MODULE_ID = "C3E68A0E-8262-463E-87B2-279B13D47722";

	public SettingsColorThemeNode()
	{
		super(MODULE_ID, GlobalSettings.getResourceString("string.theme"));
	}
}
