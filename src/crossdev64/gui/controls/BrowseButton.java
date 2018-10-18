package crossdev64.gui.controls;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class BrowseButton
	extends JButton
{
	private static final long serialVersionUID = 1L;
	
	private boolean mSaveMode = false;
	private boolean mFileBrowsing = true;
	private String mPath = "";

	public BrowseButton()
	{
		super();
		init();
	}

	public BrowseButton(String oText)
	{
		super(oText);
		init();
	}

	protected void init()
	{
		addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				onBrowse(e);
			}
		});
	}
	
	public void setPath(String oPath)
	{
		mPath = oPath;
	}

	public String getPath()
	{
		return mPath;
	}

	public void setFileBrowsing(boolean bFileBrowsing)
	{
		mFileBrowsing = bFileBrowsing;
	}

	protected boolean isFileBrowsing()
	{
		return mFileBrowsing;
	}

	public void setSafeMode(boolean bSaveMode)
	{
		mSaveMode = bSaveMode;
	}

	protected boolean isSaveMode()
	{
		return mSaveMode;
	}

	protected boolean onBrowse(ActionEvent oEvent)
	{
		JFileChooser chooser = new JFileChooser();

		if(!isFileBrowsing())
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		String path = getPath();
		if(path.length() > 0)
			chooser.setCurrentDirectory(new File(path));

		int choice = -1;
		if(isSaveMode() == false)
			choice = chooser.showOpenDialog(this);
		else
			choice = chooser.showSaveDialog(this);

		if(choice == JFileChooser.APPROVE_OPTION)
		{
			setPath(chooser.getSelectedFile().toString());
			return true;
		}

		return false;
	}
}
