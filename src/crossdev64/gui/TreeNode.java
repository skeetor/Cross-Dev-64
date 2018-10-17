package crossdev64.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import crossdev64.settings.ModuleSettings;
import crossdev64.utils.Stack;

public class TreeNode<T extends ModuleSettings>
	extends DefaultMutableTreeNode
{
	private static final long serialVersionUID = 1L;

	private T mModule;
	private List<TreeNode<? extends ModuleSettings>> mChilds = new ArrayList<>();

	public TreeNode(T oSetting)
	{
		super();
		mModule = oSetting;
	}

	public String toString()
	{
		return mModule.getName();
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
		if(getModule().getId().equals(oId))
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

	protected boolean createTree(TreeNode<? extends ModuleSettings> oParent, ModuleSettings oSettings)
	{
		if(oParent == null)
			oParent = this;

		TreeNode<? extends ModuleSettings> node = oParent;
		ModuleSettings module = getModule();
		String id = oSettings.getId();
		if(id.equals(module.getId()))
			module.copy(oSettings);
		else
		{
			node = find(id);
			if(node == null)
			{
				if(oSettings.addToParent())
					oParent.getModule().addChild(oParent.getModule().createItem(oSettings));
				else
				{
					// TODO: Invalid id in the settings file?
					System.out.println(Stack.getSourcePosition()+"Unable to find settings node. node == null");
					return false;
				}
			}
		}

		return true;
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
		return createTree(null, oSettings);
	}
}
