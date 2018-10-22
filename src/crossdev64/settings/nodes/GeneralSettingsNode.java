package crossdev64.settings.nodes;

import crossdev64.gui.TreeNode;
import crossdev64.keybinding.KeyBindingNode;
import crossdev64.project.ProjectNodes;

public class GeneralSettingsNode
	extends TreeNode<GeneralSettingsModule>
{
	private static final long serialVersionUID = 1L;

	public GeneralSettingsNode()
	{
		super(new GeneralSettingsModule());

		add(new ProjectNodes());
		add(new KeyBindingNode());
		add(new ColorThemeNode());
	}
}
