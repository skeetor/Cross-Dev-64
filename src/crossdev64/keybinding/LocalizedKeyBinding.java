package crossdev64.keybinding;

import javax.swing.AbstractButton;
import javax.swing.KeyStroke;

import crossdev64.settings.GlobalSettings;

public class LocalizedKeyBinding
	extends KeyBinding
{
	private String mResourceLabel;

	public LocalizedKeyBinding()
	{
	}

	public LocalizedKeyBinding(AbstractButton oButton, String oActionName)
	{
		super(oButton, oActionName);
	}

	public LocalizedKeyBinding(AbstractButton oButton, String oActionName, String oResourceLabel)
	{
		super(oButton, oActionName, oResourceLabel);
	}

	public LocalizedKeyBinding(AbstractButton oButton, String oActionName, String oResourceLabel, String oKeyBinding)
	{
		super(oButton, oActionName, oResourceLabel, oKeyBinding);
	}

	public LocalizedKeyBinding(AbstractButton oButton, String oActionName, String oResourceLabel, KeyStroke oKeyBinding)
	{
		super(oButton, oActionName, oResourceLabel, oKeyBinding);
	}

	@Override
	public void setLabel(String oLabel)
	{
		mResourceLabel = oLabel;

		if(oLabel != null)
			oLabel = GlobalSettings.getResourceString(oLabel);

		super.setLabel(oLabel);
	}

	public String getResourceLabel()
	{
		return mResourceLabel;
	}
}
