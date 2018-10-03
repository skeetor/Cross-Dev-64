package crossdev64.settings.nodes;

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
public abstract class SettingsNodeBase
	extends DefaultMutableTreeNode
{
	private static final long serialVersionUID = 1L;

	private List<SettingsNodeBase> mChilds = new ArrayList<>();

	private final String mName;
	private String mModuleId;
	private boolean mDirty;

	public SettingsNodeBase(String oName, String oModuleId)
	{
		super(oName);
		mName = oName;
		mModuleId = oModuleId;
		mDirty = false;
	}

	public void registerChild(SettingsNodeBase oModuleNode)
	{
		mChilds.add(oModuleNode);
	}

	public List<SettingsNodeBase> getChildModules()
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

	public boolean addByParent()
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
	public SettingsNodeBase createItem(Window oParent, SettingsNodeBase oDefault)
	{
		return null;
	}
}
