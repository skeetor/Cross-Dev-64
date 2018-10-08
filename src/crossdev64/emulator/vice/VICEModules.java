package crossdev64.emulator.vice;

import java.awt.Window;

import crossdev64.settings.ModuleSettings;

public class VICEModules
	extends ModuleSettings
{
	public static final String MODULE_ID = "B886B496-A518-4FEF-B668-684FBADDD498";

	public VICEModules()
	{
		super(MODULE_ID, "VICE");
	}

	@Override
	public boolean canAdd()
	{
		return true;
	}

	@Override
	public ModuleSettings createItem(Window oParent, ModuleSettings oDefault)
	{
		return new VICEModule();
	}
}
