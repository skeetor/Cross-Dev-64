package crossdev64.emulator;

import java.awt.Window;

import javax.swing.JPanel;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.SettingsModuleNode;

public class EmulatorModule
	extends SettingsModuleNode
{
	private static final long serialVersionUID = 1L;

	public EmulatorModule()
	{
		super(GlobalSettings.getInstance().getResourceString("string.emulator"));
	}

	@Override
	public JPanel getConfigPanel()
	{
		return null;
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
	public SettingsModuleNode createItem(Window oParent)
	{
		return null;
	}

	@Override
	public SettingsModuleNode createItem(Window oParent, SettingsModuleNode oModuleItem)
	{
		return null;
	}
}
