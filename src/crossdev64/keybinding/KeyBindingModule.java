package crossdev64.keybinding;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.ModuleSettings;
import crossdev64.utils.Stack;

@XmlRootElement(name="KeyBindings")
public class KeyBindingModule
	extends ModuleSettings
{
	public static final String MODULE_ID = "2BBB0AEC-25D4-4EA2-A5DE-A2AF4E63D375";
	protected static final boolean REGISTERED = ModuleSettings.registerModule(KeyBindingModule.class);

	public KeyBindingModule()
	{
		super(MODULE_ID, GlobalSettings.getResourceString("string.key_bindings"), new KeyBindingPanel());
	}

	@Override
	@JsonIgnore
	public boolean allowChilds()
	{
		return false;
	}

	@Override
	@JsonIgnore
	public void onApply()
	{
		System.out.println(Stack.getSourcePosition()+"onApply");
	}

	@Override
	@JsonIgnore
	public void prepareVisible(boolean bVisible)
	{
		KeyBindingPanel panel = (KeyBindingPanel)getConfigPanel();
		panel.prepareVisible(bVisible);
	}
}
