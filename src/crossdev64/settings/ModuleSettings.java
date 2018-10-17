package crossdev64.settings;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ModuleSettings
{
	private static List<Class<?>> ModuleClassList = new ArrayList<>();
	private static Class<?> ModuleClasses[] = null;

	private List<ModuleSettings> mChilds = new ArrayList<>();

	private String mModuleId;
	private String mModuleName;

	@JsonIgnore
	private boolean mDirty;

	public ModuleSettings(String oModuleId, String oModuleName)
	{
		setId(oModuleId);
		setName(oModuleName);
	}

	public String toString()
	{
		return mModuleName;
	}

	@JsonIgnore
	public static boolean registerModule(Class<? extends ModuleSettings> oModuleClass)
	{
		ModuleClassList.add(oModuleClass);
		ModuleClasses = null;

		return true;
	}

	@JsonIgnore
	private static Class<?>[] getRegisteredModules()
	{
		if(ModuleClasses == null)
		{
			// Remove duplicates.
			Set<Class<?>> clset = new HashSet<>();
			for(Class<?> cl : ModuleClassList)
				clset.add(cl);

			ModuleClasses = new Class<?>[clset.size()];
			clset.toArray(ModuleClasses);
		}

		return ModuleClasses;
	}

	@JsonIgnore
	public void addChild(ModuleSettings oModuleNode)
	{
		mChilds.add(oModuleNode);
	}

	@XmlAnyElement(lax=true)
	public List<ModuleSettings> getChildModules()
	{
		return mChilds;
	}

	@XmlAttribute(name="ModuleId")
	public String getId()
	{
		return mModuleId;
	}

	public void setId(String oModuleId)
	{
		mModuleId = oModuleId;
	}

	@XmlAttribute(name="ModuleName")
	public String getName()
	{
		return mModuleName;
	}

	public void setName(String oModuleName)
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
	 * Update the module with the settings from the source. Needs to be overridden
	 * by classes which are actually changeable.
	 * 
	 * @param oSource
	 */
	@JsonIgnore
	public void copy(ModuleSettings oSource)
	{
	}

	@JsonIgnore
	public boolean isNode()
	{
		return true;
	}

	/**
	 * Find a node with the specified ID or returns null.
	 * 
	 * @param oId
	 * @return
	 */
	@JsonIgnore
	public ModuleSettings find(String oId)
	{
		if(getId().equals(oId))
			return this;

		for(ModuleSettings m : getChildModules())
		{
			ModuleSettings result = m.find(oId);
			if(result != null)
				return result;
		}

		return null;
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
	public ModuleSettings createItem(ModuleSettings oDefault)
	{
		return null;
	}

	@JsonIgnore
	public static ModuleSettings load(String oSettings) throws JAXBException
	{
		StringReader sr = new StringReader(oSettings);
		JAXBContext jaxbContext = JAXBContext.newInstance(ModuleSettings.getRegisteredModules());
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ModuleSettings settings = (ModuleSettings)jaxbUnmarshaller.unmarshal(sr);
		return settings;
	}
	
	@JsonIgnore
	public String save() throws JAXBException
	{
		StringWriter sw = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(ModuleSettings.getRegisteredModules());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);	// Pretty print
		jaxbMarshaller.marshal(this, sw);
		return sw.toString();
	}
}
