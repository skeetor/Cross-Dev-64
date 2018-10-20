package crossdev64.gui.controls;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

public abstract class KeyMenuItem
	extends JMenuItem
{
	private static final long serialVersionUID = 1L;

	private KeyBinding mKeyBinder;

	public KeyMenuItem(String oKeyId)
	{
		super();

		mKeyBinder = new KeyBinding(this, oKeyId)
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
