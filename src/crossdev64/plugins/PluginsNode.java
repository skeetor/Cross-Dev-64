package crossdev64.plugins;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.nodes.SettingsNodeBase;

/**
 * A label node is a simple a node which can have childs and is only a string.
 * @author sparhawk
 *
 */
public class PluginsNode
	extends SettingsNodeBase
{
	public static final String MODULE_ID = "720FE006-1621-4D09-A1B7-485C7A9E781F";

	private static final long serialVersionUID = 1L;

	public PluginsNode()
	{
		super(GlobalSettings.getInstance().getResourceString("string.plugins"), MODULE_ID);
	}

	@Override
	public boolean canAdd()
	{
		return true;
	}
}
