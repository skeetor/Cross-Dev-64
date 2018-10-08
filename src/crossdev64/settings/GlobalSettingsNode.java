package crossdev64.settings;

import crossdev64.emulator.EmulatorModuleNodes;
import crossdev64.gui.TreeNode;
import crossdev64.plugins.PluginsNode;
import crossdev64.settings.nodes.SettingsGeneralNode;

public class GlobalSettingsNode
	extends TreeNode
{
	private static final long serialVersionUID = 1L;

	public GlobalSettingsNode()
	{
		super(new GlobalSettingsModule());

		add(new TreeNode(new SettingsGeneralNode()));
		add(new TreeNode(new EmulatorModuleNodes()));
		add(new TreeNode(new PluginsNode()));
	}
}
