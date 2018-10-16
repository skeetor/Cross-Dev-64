package crossdev64.project;

import javax.swing.JPanel;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.ModuleSettings;

@XmlRootElement(name="ProjectSettings")
public class ProjectSettings
	extends ModuleSettings
{
	public static final String MODULE_ID = "D4FCC276-04EC-4CE4-9847-070F6C23AD46";
	protected static final boolean REGISTERED = ModuleSettings.registerModule(ProjectSettings.class);

	@JsonIgnore
	private ProjectNodePanel mPanel;

	public ProjectSettings()
	{
		super(MODULE_ID, GlobalSettings.getResourceString("string.project"));
		mPanel = new ProjectNodePanel();
	}

	@JsonIgnore
	@Override
	public JPanel getConfigPanel()
	{
		return getPanel();
	}

	@JsonIgnore
	private ProjectNodePanel getPanel()
	{
		return mPanel;
	}

	@JsonIgnore
	@Override
	public void copy(ModuleSettings oSource)
	{
		if(oSource == null || !(oSource instanceof ProjectSettings))
			return;

		ProjectSettings project = (ProjectSettings)oSource;

		setProjectRootPath(project.getProjectRootPath());
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
