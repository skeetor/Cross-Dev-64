package crossdev64.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import crossdev64.settings.ModuleSettings;

public class TreeNode
	extends DefaultMutableTreeNode
{
	private static final long serialVersionUID = 1L;

	private ModuleSettings mModule;
	private List<TreeNode> mChilds = new ArrayList<>();

	public TreeNode(ModuleSettings oSetting)
	{
		super(oSetting.getModuleName());
		mModule = oSetting;
	}

	public ModuleSettings getModule()
	{
		return mModule;
	}

	public void setModule(ModuleSettings oModule)
	{
		mModule = oModule;
	}

	public void add(TreeNode oModuleNode)
	{
		mChilds.add(oModuleNode);
		mModule.addChild(oModuleNode.getModule());
		super.add(oModuleNode);
	}

	public List<TreeNode> getChildModules()
	{
		return mChilds;
	}
}
