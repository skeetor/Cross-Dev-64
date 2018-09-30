package crossdev64.emulator;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.SettingsModuleNode;

public class EmulatorNodes
	extends SettingsModuleNode
{
	private static final long serialVersionUID = 1L;

	public EmulatorNodes()
	{
		super(GlobalSettings.getInstance().getResourceString("string.emulator"));
	}

	@Override
	public boolean canAdd()
	{
		return true;
	}

	@Override
	public SettingsModuleNode createItem()
	{
		return null;
	}
}
