package crossdev64.emulator;

import crossdev64.settings.SettingsNode;

public class EmulatorModuleNode
	extends SettingsNode
{
	private static final long serialVersionUID = 1L;

	public EmulatorModuleNode(String oTitle)
	{
		super(oTitle);
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
}
