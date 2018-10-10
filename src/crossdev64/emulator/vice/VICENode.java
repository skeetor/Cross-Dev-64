package crossdev64.emulator.vice;

import crossdev64.gui.TreeNode;

public class VICENode
	extends TreeNode<VICEModule>
{
	private static final long serialVersionUID = 1L;

	public VICENode()
	{
		super(new VICEModule());
	}
}
