package crossdev64.emulator.vice;

import java.awt.Window;

import javax.swing.JPanel;

import crossdev64.emulator.EmulatorModuleNode;
import crossdev64.settings.SettingsNode;

public class VICESettingsNode
	extends EmulatorModuleNode
{
	private static final long serialVersionUID = 1L;

	public VICESettingsNode()
	{
		super("VICE");
	}

	@Override
	public JPanel getConfigPanel()
	{
		return null;
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
