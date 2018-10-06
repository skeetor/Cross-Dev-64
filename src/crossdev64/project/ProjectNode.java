package crossdev64.project;

import javax.swing.JPanel;

import crossdev64.gui.TreeNodeBase;
import crossdev64.settings.GlobalSettings;

public class ProjectNode
	extends TreeNodeBase
{
	public static final String MODULE_ID = "D4FCC276-04EC-4CE4-9847-070F6C23AD46";

	private static final long serialVersionUID = 1L;

	private ProjectNodePanel mPanel;

	public ProjectNode()
	{
		super(GlobalSettings.getResourceString("string.project"), MODULE_ID);
		mPanel = new ProjectNodePanel();
	}

	@Override
	public JPanel getConfigPanel()
	{
		return getPanel();
	}

	private ProjectNodePanel getPanel()
	{
		return mPanel;
	}
}
