package crossdev64.gui;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * A label node is a simple a node which can have childs and is only a string.
 * @author sparhawk
 *
 */
public abstract class TreeNodeBase
	extends DefaultMutableTreeNode
{
	private static final long serialVersionUID = 1L;

	private List<TreeNodeBase> mChilds = new ArrayList<>();

	private final String mName;
	private String mModuleId;
	private boolean mDirty;

	public TreeNodeBase(String oName, String oModuleId)
	{
		super(oName);
		mName = oName;
		mModuleId = oModuleId;
		mDirty = false;
	}

	public void registerChild(TreeNodeBase oModuleNode)
	{
		mChilds.add(oModuleNode);
	}

	public List<TreeNodeBase> getChildModules()
	{
		return mChilds;
	}
	
	public String getModuleId()
	{
		return mModuleId;
	}
	
	protected void setModuleId(String oModuleId)
	{
		mModuleId = oModuleId;
	}

	public String getModuleName()
	{
		return mName;
	}

	public JPanel getConfigPanel()
	{
		return null;
	}

	protected void setDirty(boolean bDirty)
	{
		mDirty = bDirty;
	}

	public boolean isDirty()
	{
		return mDirty;
	}

	public void clearDirty()
	{
		mDirty = false;
	}

	public boolean canAdd()
	{
		return false;
	}

	public boolean addToParent()
	{
		return false;
	}

	public boolean canCopy()
	{
		return false;
	}

	public boolean canDelete()
	{
		return false;
	}

	public boolean canRename()
	{
		return false;
	}

	public boolean canRemove()
	{
		return false;
	}

	/**
	 * Create a new node. If oDefault is not null, then it should be used to create a copy
	 * with the same settings.
	 * 
	 * @param oParent
	 * @param oDefault
	 * @return
	 */
	public TreeNodeBase createItem(Window oParent, TreeNodeBase oDefault)
	{
		return null;
	}
}
