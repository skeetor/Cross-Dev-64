package crossdev64.gui;

/**
 * EmulatorModuleNode is a base class for emulators.
 * 
 * @author sparhawk
 *
 */
public abstract class CopyableModuleNode
	extends TreeNodeBase
{
	private static final long serialVersionUID = 1L;

	public CopyableModuleNode(String oTitle, String oUUID)
	{
		super(oTitle, oUUID);
	}

	@Override
	public boolean canAdd()
	{
		return true;
	}

	@Override
	public boolean canCopy()
	{
		return true;
	}

	@Override
	public boolean canDelete()
	{
		return true;
	}

	@Override
	public boolean canRename()
	{
		return true;
	}

	@Override
	public boolean addToParent()
	{
		return true;
	}

	@Override
	public boolean canRemove()
	{
		return true;
	}
}
