package crossdev64.utils;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class PathDialogCtrl
	extends NamedTextCtrl
{
	private static final long serialVersionUID = 1L;

	private JButton jButton = null;
	private boolean mSaveMode = false;

	public PathDialogCtrl()
	{
		super();
		setLabelText("Filename");
		initialize();
	}

	public PathDialogCtrl(LayoutManager arg0)
	{
		super(arg0);
		initialize();
	}

	public PathDialogCtrl(boolean arg0)
	{
		super(arg0);
		initialize();
	}

	public PathDialogCtrl(LayoutManager arg0, boolean arg1)
	{
		super(arg0, arg1);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize()
	{
		this.setSize(new Dimension(317, 32));
		
		GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
		gbc_btnBrowse.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBrowse.gridx = 2;
		gbc_btnBrowse.insets = new Insets(3, 3, 3, 3);
		gbc_btnBrowse.gridy = 0;
		this.add(getJButton(), gbc_btnBrowse);
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton()
	{
		if (jButton == null)
		{
			jButton = new JButton();
			jButton.setText("Browse");
			jButton.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					onBrowse(e);
				}
			});
		}
		return jButton;
	}

	public boolean isSaveMode()
	{
		return mSaveMode;
	}

	public void setSaveMode(boolean oSaveMode)
	{
		mSaveMode = oSaveMode;
	}

	@Override
	public void setText(String oPath)
	{
		super.setText(oPath);
		actionPerformed();
	}

	public void setBrowseText(String oBrowse)
	{
		getJButton().setText(oBrowse);
	}

	public String getBrowseText()
	{
		return getJButton().getText();
	}

	protected boolean onBrowse(ActionEvent oEvent)
	{
		JFileChooser chooser = new JFileChooser();

		if(getText().length() > 0)
			chooser.setCurrentDirectory(new File(new File(getText()).getParent()));

		int choice = -1;
		if(isSaveMode() == false)
			choice = chooser.showOpenDialog(this);
		else
			choice = chooser.showSaveDialog(this);

		if(choice == JFileChooser.APPROVE_OPTION)
		{
			setText(chooser.getSelectedFile().toString());
			return true;
		}

		return false;
	}

	public void addActionListener(ActionListener oListener)
	{
		listenerList.add(ActionListener.class, oListener);
	}

	public void removeActionListener(ActionListener oListener)
	{
		listenerList.remove(ActionListener.class, oListener);
	}

	protected void fireActionPerformed(ActionEvent event)
	{
		Object[] listeners = listenerList.getListenerList();
		ActionEvent e = null;
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length-2; i>=0; i-=2)
		{
			if (listeners[i]==ActionListener.class)
			{
				// Lazily create the event:
				if (e == null)
				{
					String actionCommand = event.getActionCommand();

					e = new ActionEvent(this,
							ActionEvent.ACTION_PERFORMED,
							actionCommand,
							event.getWhen(),
							event.getModifiers());
					e.setSource(event.getSource());
				}
				((ActionListener)listeners[i+1]).actionPerformed(e);
			}
		}
	}

	protected void actionPerformed()
	{
		ActionEvent e = new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED,
				jButton.getText(),
				Calendar.getInstance().getTimeInMillis(),
				0);
		e.setSource(this);

		fireActionPerformed(e);
	}

	public void setEnabled(boolean bEnabled)
	{
		getJButton().setEnabled(bEnabled);
		super.setEnableText(bEnabled);
		super.setEditableText(bEnabled);
		super.setEnabled(bEnabled);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
