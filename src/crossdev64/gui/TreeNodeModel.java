package crossdev64.gui;

import javax.swing.tree.DefaultTreeModel;

import crossdev64.settings.ModuleSettings;

public class TreeNodeModel
	extends DefaultTreeModel
{
	private static final long serialVersionUID = 1L;

	public TreeNodeModel(TreeNode<? extends ModuleSettings> oRootNode)
	{
		super(oRootNode);
	}
}
