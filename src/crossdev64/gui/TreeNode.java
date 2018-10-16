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

		ModuleSettings module = getModule();
		return !module.isNode();
	}

	public TreeNode<? extends ModuleSettings> find(String oId)
	{
		if(getModule().getModuleId().equals(oId))
			return this;

		for(int i = 0; i < getChildCount(); i++)
		{
			@SuppressWarnings("unchecked")
			TreeNode<? extends ModuleSettings> c = (TreeNode<? extends ModuleSettings>)getChildAt(i);
			TreeNode<? extends ModuleSettings>result = c.find(oId);
			if(result != null)
				return result;
		}
		return null;
	}

	/**
	 * Create the tree structure from the provided settings.
	 * This method does not remove nodes, it only adds nodes which are 
	 * available in the provided settings structure.
	 *   
	 * @param oSettings
	 * @return
	 */
	public boolean createTree(ModuleSettings oSettings)
	{
		return false;
	}
}
