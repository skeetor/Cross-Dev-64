package crossdev64.settings.nodes;

import crossdev64.gui.TreeNode;
import crossdev64.project.ProjectTreeNode;

public class GeneralSettingsNode
	extends TreeNode
{
	private static final long serialVersionUID = 1L;

	public GeneralSettingsNode()
	{
		super(new GeneralSettingsModule());
		add(new ProjectTreeNode());
		add(new ColorThemeNode());
	}
}
