package crossdev64.emulator;

import java.awt.Window;

import crossdev64.emulator.vice.VICEModule;
import crossdev64.settings.GlobalSettings;
import crossdev64.settings.ModuleSettings;

public class EmulatorModule
	extends ModuleSettings
{
	public static final String MODULE_ID = "21453DC0-866F-433B-95D4-E25C984D9824";

	public EmulatorModule()
	{
		super(MODULE_ID, GlobalSettings.getResourceString("string.emulator"));
	}

	@Override
	public boolean canAdd()
	{
		return true;
	}

	@Override
	public ModuleSettings createItem(Window oParent, ModuleSettings oDefault)
	{
		// TODO: For now we just create a VICE node. If we support other emulators as well, we need to replace it with a selection dialog. 
		return new VICEModule();
	}
}
