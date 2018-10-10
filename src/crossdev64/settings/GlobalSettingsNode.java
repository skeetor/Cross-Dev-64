package crossdev64.settings;

import crossdev64.emulator.EmulatorNode;
import crossdev64.gui.TreeNode;
import crossdev64.plugins.PluginNodes;
import crossdev64.settings.nodes.GeneralSettingsNode;

public class GlobalSettingsNode
	extends TreeNode<GlobalSettingsModule>
{
	private static final long serialVersionUID = 1L;

	public GlobalSettingsNode()
	{
		super(new GlobalSettingsModule());

		add(new GeneralSettingsNode());
		add(new EmulatorNode());
		add(new PluginNodes());
	}
}
