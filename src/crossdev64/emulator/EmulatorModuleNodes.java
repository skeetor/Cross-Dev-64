package crossdev64.emulator;

import java.awt.Window;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.SettingsModuleNode;

public class EmulatorModuleNodes
	extends SettingsModuleNode
{
	private static final long serialVersionUID = 1L;

	public EmulatorModuleNodes()
	{
		super(GlobalSettings.getInstance().getResourceString("string.emulator"));
	}

	@Override
	public boolean canAdd()
	{
		return true;
	}

	@Override
	public SettingsModuleNode createItem(Window oParent)
	{
		return new EmulatorModuleNode();
	}
}
