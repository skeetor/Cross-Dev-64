package crossdev64.settings.nodes;

import crossdev64.gui.TreeNodeBase;
import crossdev64.settings.GlobalSettings;

/**
 * A label node is a simple a node which can have childs and is only a string.
 * @author sparhawk
 *
 */
public class SettingsGeneralNode
	extends TreeNodeBase
{
	public static final String MODULE_ID = "070B3D0E-5CEC-4EA5-9501-46ACD3210CF5";

	private static final long serialVersionUID = 1L;

	public SettingsGeneralNode()
	{
		super(GlobalSettings.getInstance().getResourceString("string.general"), MODULE_ID);

		registerChild(new SettingsColorThemeNode());
	}
}
