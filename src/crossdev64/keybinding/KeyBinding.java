package crossdev64.keybinding;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import crossdev64.settings.GlobalSettings;

public abstract class KeyBinding
{
	private KeyStroke mKeyStroke;
	private String mActionId;
	private AbstractButton mButton;

	public KeyBinding(AbstractButton oButton, String oKeyId)
	{
		init(oButton, oKeyId, "string.start_debugging", "F5");
	}

	protected void init(AbstractButton oButton, String oActionId, String oLabel, String oKeyBinding)
	{
		mButton = oButton;
		setKeyStroke(oKeyBinding);
		setActionId(oActionId);
	
		oButton.setText(GlobalSettings.getResourceString(oLabel));
		oButton.addActionListener(new java.awt.event.ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				KeyBinding.this.actionPerformed(e);
			}
		});
	}

	public KeyStroke getKeyStroke()
	{
		return mKeyStroke;
	}

	public void setKeyStroke(KeyStroke oKeyStroke)
	{
		mKeyStroke = oKeyStroke;
		updateAction();
	}

	public void setKeyStroke(String  oKeyBinding)
	{
		mKeyStroke = KeyStroke.getKeyStroke(oKeyBinding);
		updateAction();
	}

	public String getActionId()
	{
		return mActionId;
	}

	protected void updateAction()
	{
		if(mKeyStroke == null || mActionId == null || mButton == null)
			return;

		mButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(mKeyStroke, mActionId);
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

	public void setActionId(String oActionId)
	{
		mActionId = oActionId;
		updateAction();
	}

	public abstract void actionPerformed(ActionEvent oEvent);
}
