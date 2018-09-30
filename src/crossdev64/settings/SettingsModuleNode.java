package crossdev64.settings;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * A label node is a simple a node which can have childs and is only a string.
 * @author sparhawk
 *
 */
public class SettingsModuleNode
	extends DefaultMutableTreeNode
{
	private static final long serialVersionUID = 1L;

	private String mName;

	public SettingsModuleNode(String oName)
	{
		super(oName);
		mName = oName;
	}

	public String getModuleName()
	{
		return mName;
	}

	public JPanel getConfigPanel()
	{
		return null;
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

	public SettingsModuleNode createItem()
	{
		return null;
	}

	public SettingsModuleNode createItem(SettingsModuleNode oModuleItem)
	{
		return null;
	}
}
