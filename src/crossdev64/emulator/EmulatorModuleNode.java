package crossdev64.emulator;

import java.awt.Window;

import javax.swing.JPanel;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.SettingsNode;

public class EmulatorModuleNode
	extends SettingsNode
{
	private static final long serialVersionUID = 1L;

	public EmulatorModuleNode()
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
	public boolean canRename()
	{
		return true;
	}

	@Override
	public SettingsNode createItem(Window oParent)
	{
		return null;
	}

	@Override
	public SettingsNode createItem(Window oParent, SettingsNode oModuleItem)
	{
		return null;
	}
}
