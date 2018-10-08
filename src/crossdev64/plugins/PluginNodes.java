package crossdev64.plugins;

import crossdev64.gui.TreeNode;

public class PluginNodes
	extends TreeNode<PluginModules>
{
	private static final long serialVersionUID = 1L;

	public PluginNodes()
	{
		super(new PluginModules());
	}
}
