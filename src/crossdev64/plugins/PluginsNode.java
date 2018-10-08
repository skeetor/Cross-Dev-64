package crossdev64.plugins;

import crossdev64.gui.TreeNode;

public class PluginsNode
	extends TreeNode
{
	private static final long serialVersionUID = 1L;

	public PluginsNode()
	{
		super(new PluginsModule());
	}
}
