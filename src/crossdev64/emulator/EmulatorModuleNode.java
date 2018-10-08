package crossdev64.emulator;

import crossdev64.gui.TreeNode;

public class EmulatorModuleNode
	extends TreeNode
{
	private static final long serialVersionUID = 1L;

	public EmulatorModuleNode()
	{
		super(new EmulatorModule());
	}
}
