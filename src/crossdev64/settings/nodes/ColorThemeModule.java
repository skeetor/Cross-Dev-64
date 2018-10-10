package crossdev64.settings.nodes;

import javax.xml.bind.annotation.XmlRootElement;

import crossdev64.settings.GlobalSettings;
import crossdev64.settings.ModuleSettings;

@XmlRootElement(name="ColorTheme")
public class ColorThemeModule
	extends ModuleSettings
{
	public static final String MODULE_ID = "C3E68A0E-8262-463E-87B2-279B13D47722";
	protected static final boolean REGISTERED = ModuleSettings.registerModule(ColorThemeModule.class);

	public ColorThemeModule()
	{
		super(MODULE_ID, GlobalSettings.getResourceString("string.theme"));
	}
}
