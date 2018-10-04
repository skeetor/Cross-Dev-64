package crossdev64.settings;

import javax.swing.tree.DefaultTreeModel;

import crossdev64.gui.TreeNodeBase;

public class SettingsTreeModel
	extends DefaultTreeModel
{
	private static final long serialVersionUID = 1L;

	private TreeNodeBase mRoot;
	
	public SettingsTreeModel()
	{
		super(GlobalSettings.getInstance().getRootNode());
		mRoot = (TreeNodeBase)super.getRoot();
		initModules(mRoot);
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
