package crossdev64.settings;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ModuleSettings
{
	private List<ModuleSettings> mChilds = new ArrayList<>();

	private String mModuleId;
	private String mModuleName;

	@JsonIgnore
	private boolean mDirty;
	
	public ModuleSettings(String oModuleId, String oModuleName)
	{
		setModuleId(oModuleId);
		setModuleName(oModuleName);
	}

	@JsonIgnore
	public void addChild(ModuleSettings oModuleNode)
	{
		mChilds.add(oModuleNode);
	}

	@JsonIgnore
	public List<ModuleSettings> getChildModules()
	{
		return mChilds;
	}

	@XmlElement(name="ModuleId")
	public String getModuleId()
	{
		return mModuleId;
	}

	public void setModuleId(String oModuleId)
	{
		mModuleId = oModuleId;
	}

	@XmlElement(name="ModuleName")
	public String getModuleName()
	{
		return mModuleName;
	}

	public void setModuleName(String oModuleName)
	{
		mModuleName = oModuleName;
	}

	@JsonIgnore
	public JPanel getConfigPanel()
	{
		return null;
	}

	@JsonIgnore
	protected void setDirty(boolean bDirty)
	{
		mDirty = bDirty;
	}

	@JsonIgnore
	public boolean isDirty()
	{
		return mDirty;
	}

	@JsonIgnore
	public void clearDirty()
	{
		mDirty = false;
	}

	@JsonIgnore
	public boolean canAdd()
	{
		return false;
	}

	@JsonIgnore
	public boolean addToParent()
	{
		return false;
	}

	@JsonIgnore
	public boolean canCopy()
	{
		return false;
	}

	@JsonIgnore
	public boolean canDelete()
	{
		return false;
	}

	@JsonIgnore
	public boolean canRename()
	{
		return false;
	}

	@JsonIgnore
	public boolean canRemove()
	{
		return false;
	}

	/**
	 * Create a new node. If oDefault is not null, then it should be used to create a copy
	 * with the same settings.
	 * 
	 * @param oParent
	 * @param oDefault
	 * @return
	 */
	@JsonIgnore
	public ModuleSettings createItem(Window oParent, ModuleSettings oDefault)
	{
		return null;
	}
}
