package crossdev64.keybinding;

import java.awt.event.ActionEvent;

public class KeyBindingConfig
	extends AbstractKeyBinding
{
	private String mLabelId;

	public KeyBindingConfig(String oName)
	{
		setActionId(oName);
	}

	@Override
	public void actionPerformed(ActionEvent oEvent)
	{
	}

	public String getLabelId()
	{
		return mLabelId;
	}

	public void setLabelId(String oLabelId)
	{
		mLabelId = oLabelId;
	}
}
