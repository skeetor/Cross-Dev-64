package crossdev64.keybinding;

import crossdev64.gui.TreeNode;

public class KeyBindingNode
	extends TreeNode<KeyBindingModule>
{
	private static final long serialVersionUID = 1L;

	public KeyBindingNode()
	{
		super(new KeyBindingModule());
	}
}
