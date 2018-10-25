package crossdev64.gui.controls;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

import crossdev64.keybinding.AbstractKeyBinding;

public abstract class KeyMenuItem
	extends JMenuItem
{
	private static final long serialVersionUID = 1L;

	private AbstractKeyBinding mKeyBinder;

	public KeyMenuItem(String oKeyId)
	{
		super();

		mKeyBinder = new AbstractKeyBinding(this, oKeyId)
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				KeyMenuItem.this.actionPerformed(e);
			}
		};
				
		setAccelerator(mKeyBinder.getKeyStroke());
	}

	public abstract void actionPerformed(ActionEvent oEvent);
}
