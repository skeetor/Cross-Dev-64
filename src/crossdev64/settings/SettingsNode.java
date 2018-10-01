package crossdev64.settings;

import java.awt.Window;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * A label node is a simple a node which can have childs and is only a string.
 * @author sparhawk
 *
 */
public class SettingsNode
	extends DefaultMutableTreeNode
{
	private static final long serialVersionUID = 1L;

	private String mName;
	private boolean mDirty;

	public SettingsNode(String oName)
	{
		super(oName);
		mName = oName;
		mDirty = false;
	}

	public String getModuleId()
	{
		return null;
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

	public SettingsNode createItem(Window oParent)
	{
		return null;
	}

	public SettingsNode createItem(Window oParent, SettingsNode oModuleItem)
	{
		return null;
	}
}
