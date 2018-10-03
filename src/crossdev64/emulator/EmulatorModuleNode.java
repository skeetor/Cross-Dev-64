package crossdev64.emulator;

import crossdev64.settings.nodes.SettingsNodeBase;

/**
 * EmulatorModuleNode is a base class for emulators.
 * 
 * @author sparhawk
 *
 */
public abstract class EmulatorModuleNode
	extends SettingsNodeBase
{
	private static final long serialVersionUID = 1L;

	public EmulatorModuleNode(String oTitle, String oUUID)
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
	public boolean addByParent()
	{
		return true;
	}

	@Override
	public boolean canRemove()
	{
		return true;
	}
}
