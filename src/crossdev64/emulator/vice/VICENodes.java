package crossdev64.emulator.vice;

import crossdev64.gui.TreeNode;
import crossdev64.settings.ModuleSettings;

public class VICENodes
	extends TreeNode<VICEModules>
{
	private static final long serialVersionUID = 1L;

	public VICENodes()
	{
		super(new VICEModules());
	}
}
