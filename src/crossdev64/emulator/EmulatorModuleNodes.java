package crossdev64.emulator;

import java.awt.Window;

import crossdev64.emulator.vice.VICESettingsNode;
import crossdev64.settings.GlobalSettings;
import crossdev64.settings.SettingsNode;

public class EmulatorModuleNodes
	extends SettingsNode
{
	private static final long serialVersionUID = 1L;

	public EmulatorModuleNodes()
	{
		super(GlobalSettings.getInstance().getResourceString("string.emulator"));

		// Register all our supported emulators. This instance serves as the default when a new configuration node will be added.
		registerChild(new VICESettingsNode());
	}

	@Override
	public boolean canAdd()
	{
		return true;
	}

	@Override
	public SettingsNode createItem(Window oParent)
	{
		// TODO: For now we just create a VICE node. If we support other emulators as well, we need to replace it with a selection dialog. 
		return new VICESettingsNode();
	}
}
