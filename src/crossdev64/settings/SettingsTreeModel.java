package crossdev64.settings;

import javax.swing.tree.DefaultTreeModel;

import crossdev64.settings.nodes.SettingsNodeBase;

public class SettingsTreeModel
	extends DefaultTreeModel
{
	private static final long serialVersionUID = 1L;

	private SettingsNodeBase mRoot;
	
	public SettingsTreeModel()
	{
		super(GlobalSettings.getInstance().getRootNode());
		mRoot = (SettingsNodeBase)super.getRoot();
		initModules(mRoot);
	}

	private void initModules(SettingsNodeBase oRoot)
	{
		for(SettingsNodeBase node : oRoot.getChildModules())
		{
			oRoot.add(node);
			if(oRoot.getChildModules().size() > 0)
				initModules(node);
		}
	}
}
