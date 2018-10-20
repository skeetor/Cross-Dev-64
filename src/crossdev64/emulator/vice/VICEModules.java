package crossdev64.emulator.vice;

import javax.xml.bind.annotation.XmlRootElement;

import crossdev64.settings.ModuleSettings;

@XmlRootElement(name="VICEModules")
public class VICEModules
	extends ModuleSettings
{
	public static final String MODULE_ID = "B886B496-A518-4FEF-B668-684FBADDD498";
	protected static final boolean REGISTERED = ModuleSettings.registerModule(VICEModules.class);

	public VICEModules()
	{
		super(MODULE_ID, "VICE", null);

		// Since the node is initially empty, we need to register this here, so it can be unmarshalled.
		ModuleSettings.registerModule(VICEModule.class);
	}

	@Override
	public boolean canAdd()
	{
		return true;
	}

	@Override
	public ModuleSettings createItem(ModuleSettings oDefault)
	{
		VICEModule m = new VICEModule();
		m.copy(oDefault);
		return m;
	}
}
