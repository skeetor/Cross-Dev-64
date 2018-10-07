package crossdev64.project;

import javax.swing.JPanel;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import crossdev64.gui.TreeNodeBase;
import crossdev64.settings.GlobalSettings;

@XmlRootElement(name="Project")
public class ProjectTreeNode
	extends TreeNodeBase
{
	public static final String MODULE_ID = "D4FCC276-04EC-4CE4-9847-070F6C23AD46";

	private static final long serialVersionUID = 1L;

	private ProjectNodePanel mPanel;

	public ProjectTreeNode()
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

	@XmlElement(name="ProjectRootPath")
	public String getProjectRootPath()
	{
		return mPanel.getProjectRootPath();
	}

	public void setProjectRootPath(String oPath)
	{
		mPanel.setProjectRootPath(oPath);
	}
}
