package crossdev64.settings;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import crossdev64.emulator.EmulatorModuleNodes;

public class SettingsTreeModel
	extends DefaultTreeModel
{
	private static final long serialVersionUID = 1L;

	private SettingsModuleNode mRoot;
	
	public SettingsTreeModel()
	{
		super(new SettingsModuleNode(GlobalSettings.getInstance().getResourceString("string.settings")));
		mRoot = (SettingsModuleNode)super.getRoot();
		initModules();
	}

	private void initModules()
	{
		initGeneral(mRoot);
		mRoot.add(new EmulatorModuleNodes());
	}

	private void initGeneral(DefaultMutableTreeNode oNode)
	{
		SettingsModuleNode general = new SettingsModuleNode(GlobalSettings.getInstance().getResourceString("string.general"));
		oNode.add(general);
		SettingsModuleNode layout = new SettingsModuleNode(GlobalSettings.getInstance().getResourceString("string.layout"));
		general.add(layout);
	}
}
