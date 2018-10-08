package crossdev64.gui;

import javax.swing.tree.DefaultTreeModel;

public class TreeNodeModel
	extends DefaultTreeModel
{
	private static final long serialVersionUID = 1L;

	public TreeNodeModel(TreeNode oRootNode)
	{
		super(oRootNode);
		initModules(oRootNode);
	}

	private void initModules(TreeNode oRoot)
	{
		for(TreeNode node : oRoot.getChildModules())
		{
			oRoot.add(node);
			if(node.getChildModules().size() > 0)
				initModules(node);
		}
	}
}
