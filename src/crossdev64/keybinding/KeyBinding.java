package crossdev64.keybinding;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class KeyBinding
{
	private KeyStroke mKeyStroke;
	private String mActionId;
	private AbstractButton mButton;
	private String mLabel;		// localized text if applicable.

	public KeyBinding()
	{
	}

	public KeyBinding(AbstractButton oButton, String oActionName)
	{
		init(oButton, oActionName, null, (KeyStroke)null);
	}

	public KeyBinding(AbstractButton oButton, String oActionName, String oLabel)
	{
		init(oButton, oActionName, oLabel, (KeyStroke)null);
	}

	public KeyBinding(AbstractButton oButton, String oActionName, String oLabel, String oKeyBinding)
	{
		init(oButton, oActionName, oLabel, oKeyBinding);
	}

	public KeyBinding(AbstractButton oButton, String oActionName, String oLabel, KeyStroke oKeyBinding)
	{
		init(oButton, oActionName, oLabel, oKeyBinding);
	}

	protected void init(AbstractButton oButton, String oActionName, String oLabel, String oKeyBinding)
	{
		KeyStroke ks = null;
		if(oKeyBinding != null && !oKeyBinding.isEmpty())
			ks = KeyStroke.getKeyStroke(oKeyBinding);

		init(oButton, oActionName, oLabel, ks);
	}

	protected void init(AbstractButton oButton, String oActionName, String oLabel, KeyStroke oKeyBinding)
	{
		mButton = oButton;
		setActionId(oActionName);
		setLabel(oLabel);
		setKeyStroke(oKeyBinding);

		oButton.setText(getLabel());
	}

	@Override
	public String toString()
	{
		return ""+mActionId;
	}

	public String getLabel()
	{
		return mLabel;
	}

	public void setLabel(String oLabel)
	{
		mLabel = oLabel;
	}

	public void setButton(AbstractButton oButton)
	{
		mButton = oButton;
		updateAction(mKeyStroke);
	}
	
	public KeyStroke getKeyStroke()
	{
		return mKeyStroke;
	}

	public void setKeyStroke(KeyStroke oKeyStroke)
	{
		updateAction(oKeyStroke);
	}

	public void setKeyStroke(String oKeyBinding)
	{
		if(oKeyBinding == null || oKeyBinding.isEmpty())
			setKeyStroke((KeyStroke)null);
		else
			setKeyStroke(KeyStroke.getKeyStroke(oKeyBinding));
	}

	public String getActionId()
	{
		return mActionId;
	}

	protected void updateAction(KeyStroke oKeyStroke)
	{
		if(mActionId == null || mButton == null)
		{
			mKeyStroke = oKeyStroke;
			return;
		}

		if(mKeyStroke != null)
			mButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(mKeyStroke);

		if(oKeyStroke != null)
			mButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(oKeyStroke, mActionId);

		if(mButton.getActionMap().get(mActionId) == null)
		{
			mButton.getActionMap().put(mActionId, new AbstractAction()
			{
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e)
				{
					KeyBinding.this.actionPerformed(e);
				}
			});
		}
	}

	public void setActionId(String oActionId)
	{
		mActionId = oActionId;
		updateAction(getKeyStroke());
	}

	public void actionPerformed(ActionEvent oEvent)
	{
	}

	public static String prepareToString(KeyStroke oKeyStroke)
	{
		if(oKeyStroke == null)
			return null;

		String mod = "";
		String s  = oKeyStroke.toString().toUpperCase();
		s = s.replaceAll("PRESSED ", "");
		s = s.replaceAll("RELEASED ", "");

		if(s.indexOf("META") != -1)
		{
			s = s.replaceAll("META ", "");
			mod = "META";
		}

		if(s.indexOf("CTRL") != -1)
		{
			s = s.replaceAll("CTRL ", "");
			if(!mod.isEmpty())
				mod += "+";
			mod += "CTRL";
		}

		if(s.indexOf("SHIFT") != -1)
		{
			s = s.replaceAll("SHIFT ", "");
			if(!mod.isEmpty())
				mod += "+";
			mod += "Shift";
		}

		if(!mod.isEmpty())
			mod += " ";

		return mod+s;
	}
}
