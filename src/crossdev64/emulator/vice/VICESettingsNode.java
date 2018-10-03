package crossdev64.emulator.vice;

import java.awt.Window;
import java.util.UUID;

import javax.swing.JPanel;

import crossdev64.emulator.EmulatorModuleNode;
import crossdev64.settings.nodes.SettingsNodeBase;

public class VICESettingsNode
	extends EmulatorModuleNode
{
	private static final long serialVersionUID = 1L;

	private VICESettingsPanel mPanel;

	public VICESettingsNode()
	{
		super("VICE", UUID.randomUUID().toString().toUpperCase());
		mPanel = new VICESettingsPanel();
	}

	/**
	 * Constructor to be used for loading from settings, where the UUID is already known and
	 * stored in the settings/project file.
	 * 
	 * @param oUUID
	 */
	public VICESettingsNode(String oUUID)
	{
		super("VICE", oUUID);
		mPanel = new VICESettingsPanel();
	}

	@Override
	public JPanel getConfigPanel()
	{
		return getPanel();
	}

	@Override
	public SettingsNodeBase createItem(Window oParent, SettingsNodeBase oDefault)
	{
		return null;
	}

	private VICESettingsPanel getPanel()
	{
		return mPanel;
	}
}
