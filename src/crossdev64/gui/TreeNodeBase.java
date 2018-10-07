package crossdev64.gui;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * A label node is a simple a node which can have childs and is only a string.
 * @author sparhawk
 *
 */
@JsonInclude(Include.NON_NULL)
public abstract class TreeNodeBase
	extends DefaultMutableTreeNode
{
	private static final long serialVersionUID = 1L;

	private List<TreeNodeBase> mChilds = new ArrayList<>();

	private String mName;
	private String mModuleId;
	private boolean mDirty;

	public TreeNodeBase(String oName, String oModuleId)
	{
		super(oName);
		setModuleName(oName);
		setModuleId(oModuleId);
		setDirty(false);
	}

	@JsonIgnore
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

	public void setModuleName(String oName)
	{
		mName = oName;
	}

	@JsonIgnore
	public JPanel getConfigPanel()
	{
		return null;
	}

	@JsonIgnore
	protected void setDirty(boolean bDirty)
	{
		mDirty = bDirty;
	}

	@JsonIgnore
	public boolean isDirty()
	{
		return mDirty;
	}

	@JsonIgnore
	public void clearDirty()
	{
		mDirty = false;
	}

	@JsonIgnore
	public boolean canAdd()
	{
		return false;
	}

	@JsonIgnore
	public boolean addToParent()
	{
		return false;
	}

	@JsonIgnore
	public boolean canCopy()
	{
		return false;
	}

	@JsonIgnore
	public boolean canDelete()
	{
		return false;
	}

	@JsonIgnore
	public boolean canRename()
	{
		return false;
	}

	@JsonIgnore
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
	@JsonIgnore
	public TreeNodeBase createItem(Window oParent, TreeNodeBase oDefault)
	{
		return null;
	}

	@JsonIgnore
	public ObjectNode save(ObjectNode root)
	{
		return null;
	}
}
