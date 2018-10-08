package crossdev64.emulator;

import crossdev64.emulator.vice.VICENodes;
import crossdev64.gui.TreeNode;

public class EmulatorNode
	extends TreeNode<EmulatorModule>
{
	private static final long serialVersionUID = 1L;

	public EmulatorNode()
	{
		super(new EmulatorModule());

		add(new VICENodes());
	}
}
