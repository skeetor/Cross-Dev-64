package crossdev64.settings;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import crossdev64.emulator.EmulatorModuleNodes;

public class SettingsTreeModel
	extends DefaultTreeModel
{
	private static final long serialVersionUID = 1L;

	private SettingsNode mRoot;
	
	public SettingsTreeModel()
	{
		super(new SettingsNode(GlobalSettings.getInstance().getResourceString("string.settings")));
		mRoot = (SettingsNode)super.getRoot();
		initModules();
	}

	private void initModules()
	{
		initGeneral(mRoot);
		mRoot.add(new EmulatorModuleNodes());
	}

	private void initGeneral(DefaultMutableTreeNode oNode)
	{
		SettingsNode general = new SettingsNode(GlobalSettings.getInstance().getResourceString("string.general"));
		oNode.add(general);
		SettingsNode layout = new SettingsNode(GlobalSettings.getInstance().getResourceString("string.layout"));
		general.add(layout);
	}
}
