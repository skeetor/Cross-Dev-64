package crossdev64.keybinding;

import java.awt.event.ActionEvent;

public class KeyBindingConfig
	extends OverrideKeyBinding
{
	public KeyBindingConfig(String oName)
	{
		setActionId(oName);
	}

	@Override
	public void actionPerformed(ActionEvent oEvent)
	{
	}
}
