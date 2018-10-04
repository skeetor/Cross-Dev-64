package crossdev64.gui;

import javax.swing.tree.DefaultTreeModel;

public class TreeNodeModel
	extends DefaultTreeModel
{
	private static final long serialVersionUID = 1L;

	public TreeNodeModel(TreeNodeBase oRootNode)
	{
		super(oRootNode);
		initModules(oRootNode);
	}

	private void initModules(TreeNodeBase oRoot)
	{
		for(TreeNodeBase node : oRoot.getChildModules())
		{
			oRoot.add(node);
			if(oRoot.getChildModules().size() > 0)
				initModules(node);
		}
	}
}
