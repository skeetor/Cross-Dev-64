package crossdev64.settings.nodes;

import crossdev64.gui.TreeNode;
import crossdev64.project.ProjectTreeNode;

public class SettingsGeneralNode
	extends TreeNode
{
	private static final long serialVersionUID = 1L;

	public SettingsGeneralNode()
	{
		super(new SettingsGeneralModule());
		add(new ProjectTreeNode());
		add(new ColorThemeNode());
	}
}
