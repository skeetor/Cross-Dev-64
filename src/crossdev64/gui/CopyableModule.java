package crossdev64.gui;

import crossdev64.settings.ModuleSettings;

public abstract class CopyableModule
	extends ModuleSettings
{
	public CopyableModule(String oModuleId, String oModuleName)
	{
		super(oModuleId, oModuleName);
	}

	@Override
	public void copy(ModuleSettings oSource)
	{
		setId(oSource.getId());
		setName(oSource.getName());
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
