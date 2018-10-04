package crossdev64.settings.nodes;

import crossdev64.gui.TreeNodeBase;
import crossdev64.settings.GlobalSettings;

/**
 * A label node is a simple a node which can have childs and is only a string.
 * @author sparhawk
 *
 */
public class SettingsColorThemeNode
	extends TreeNodeBase
{
	public static final String MODULE_ID = "C3E68A0E-8262-463E-87B2-279B13D47722";

	private static final long serialVersionUID = 1L;

	public SettingsColorThemeNode()
	{
		super(GlobalSettings.getInstance().getResourceString("string.theme"), MODULE_ID);
	}
}
