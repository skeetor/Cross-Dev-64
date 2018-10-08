package crossdev64.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import crossdev64.settings.ModuleSettings;

public class TreeNode<T extends ModuleSettings>
	extends DefaultMutableTreeNode
{
	private static final long serialVersionUID = 1L;

	private T mModule;
	private List<TreeNode<? extends ModuleSettings>> mChilds = new ArrayList<>();

	public TreeNode(T oSetting)
	{
		super(oSetting.getModuleName());
		mModule = oSetting;
	}

	public T getModule()
	{
		return mModule;
	}

	public void setModule(T oModule)
	{
		mModule = oModule;
	}

	public void add(TreeNode<? extends ModuleSettings> oModuleNode)
	{
		mChilds.add(oModuleNode);
		mModule.addChild(oModuleNode.getModule());
		super.add(oModuleNode);
	}

	public List<TreeNode<? extends ModuleSettings>> getChildModules()
	{
		return mChilds;
	}

	@Override
	public boolean isLeaf()
	{
		boolean leaf = super.isLeaf();

		if(!leaf)
			return leaf;

		if(getChildModules().size() > 0)
			return false;

		ModuleSettings module = getModule();
		if(module.canAdd())
			return false;

		return true;
	}
}