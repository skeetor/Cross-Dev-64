package crossdev64.settings.nodes;

import javax.xml.bind.annotation.XmlRootElement;

import crossdev64.emulator.EmulatorModuleNodes;
import crossdev64.gui.TreeNodeBase;
import crossdev64.plugins.PluginsNode;
import crossdev64.settings.GlobalSettings;

/**
 * A label node is a simple a node which can have childs and is only a string.
 * @author sparhawk
 *
 */
@XmlRootElement(name="Settings")
public class SettingsRootNode
	extends TreeNodeBase
{
	public static final String MODULE_ID = "5A75ED5D-6A8E-48F5-A1EF-68C823D8443E";

	private static final long serialVersionUID = 1L;

	public SettingsRootNode()
	{
		super(GlobalSettings.getResourceString("string.settings"), MODULE_ID);

		registerChild(new SettingsGeneralNode());
		registerChild(new EmulatorModuleNodes());
		registerChild(new PluginsNode());
	}
}
