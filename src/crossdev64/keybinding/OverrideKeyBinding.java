package crossdev64.keybinding;

import javax.swing.AbstractButton;
import javax.swing.KeyStroke;

public class OverrideKeyBinding
	extends LocalizedKeyBinding
{
	private KeyStroke mOverride;
	private KeyStroke mDefault;

	public OverrideKeyBinding()
	{
	}

	public OverrideKeyBinding(AbstractButton oButton, String oActionName, String oResourceLabel)
	{
		super(oButton, oActionName, oResourceLabel);
	}

	public KeyStroke getOverride()
	{
		return mOverride;
	}

	public void setOverride(KeyStroke oOverride)
	{
		mOverride = oOverride;
		if(oOverride != null)
			super.updateAction(oOverride);
	}

	public void setOverride(String oKeyBinding)
	{
		if(oKeyBinding == null || oKeyBinding.isEmpty())
			setOverride((KeyStroke)null);
		else
			setOverride(KeyStroke.getKeyStroke(oKeyBinding));
	}

	public KeyStroke getDefault()
	{
		return mDefault;
	}

	public void setDefault(KeyStroke oKeyStroke)
	{
		mDefault = oKeyStroke;
		if(mOverride == null)
			setKeyStroke(oKeyStroke);
	}

	public void setDefault(String oKeyBinding)
	{
		if(oKeyBinding == null || oKeyBinding.isEmpty())
			setDefault((KeyStroke)null);
		else
			setDefault(KeyStroke.getKeyStroke(oKeyBinding));
	}

	@Override
	public KeyStroke getKeyStroke()
	{
		if(mOverride != null)
			return mOverride;

		return super.getKeyStroke();
	}
}
