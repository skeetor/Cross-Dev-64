package crossdev64.settings.nodes;

import crossdev64.project.ProjectTreeNode;
import crossdev64.settings.GlobalSettings;
import crossdev64.settings.ModuleSettings;

public class SettingsGeneralNode
	extends ModuleSettings
{
	public static final String MODULE_ID = "070B3D0E-5CEC-4EA5-9501-46ACD3210CF5";

	public SettingsGeneralNode()
	{
		super(MODULE_ID, GlobalSettings.getResourceString("string.general"));

		addChild(new ProjectTreeNode());
		addChild(new SettingsColorThemeNode());
	}
}
