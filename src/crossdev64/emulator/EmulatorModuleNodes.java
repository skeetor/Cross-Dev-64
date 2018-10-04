package crossdev64.emulator;

import java.awt.Window;

import crossdev64.emulator.vice.VICESettingsNode;
import crossdev64.gui.TreeNodeBase;
import crossdev64.settings.GlobalSettings;

public class EmulatorModuleNodes
	extends TreeNodeBase
{
	public static final String MODULE_ID = "21453DC0-866F-433B-95D4-E25C984D9824";

	private static final long serialVersionUID = 1L;

	public EmulatorModuleNodes()
	{
		super(GlobalSettings.getResourceString("string.emulator"), MODULE_ID);
	}

	@Override
	public boolean canAdd()
	{
		return true;
	}

	@Override
	public TreeNodeBase createItem(Window oParent, TreeNodeBase oDefault)
	{
		// TODO: For now we just create a VICE node. If we support other emulators as well, we need to replace it with a selection dialog. 
		return new VICESettingsNode();
	}
}
