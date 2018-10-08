package crossdev64.project;

import crossdev64.gui.TreeNode;

public class ProjectTreeNode
	extends TreeNode
{
	private static final long serialVersionUID = 1L;

	public ProjectTreeNode()
	{
		super(new ProjectSettings());
	}
}
