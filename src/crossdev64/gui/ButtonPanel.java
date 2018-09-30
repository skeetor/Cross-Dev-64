package crossdev64.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import crossdev64.utils.GraphicTools;

public class ButtonPanel
	extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JButton mNewBtn;
	private JButton mDeleteBtn;
	private JButton mCopyBtn;

	public ButtonPanel()
	{
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(2);
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		mNewBtn = new JButton();
		putIcon(mNewBtn, "/crossdev64/resources/icons/small-icons-com/add.png", "Add");
		mNewBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				onNew();
			}
		});
		add(mNewBtn);
		
		mDeleteBtn = new JButton();
		putIcon(mDeleteBtn, "/crossdev64/resources/icons/small-icons-com/delete.png", "Delete");
		mDeleteBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				onDelete();
			}
		});
		add(mDeleteBtn);
		
		mCopyBtn = new JButton();
		putIcon(mCopyBtn, "/crossdev64/resources/icons/small-icons-com/copy.png", "Delete");
		mCopyBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				onCopy();
			}
		});
		add(mCopyBtn);

	}

	private void putIcon(JButton oButton, String oResource, String oDefault)
	{
		try
		{
			oButton.setIcon(GraphicTools.loadIcon(oResource, 16, 16));
		}
		catch(Exception e)
		{
			oButton.setText(oDefault);
		}
	}

	protected JButton getNewBtn()
	{
		return mNewBtn;
	}

	protected JButton getDeleteBtn()
	{
		return mDeleteBtn;
	}

	protected JButton getCopyBtn()
	{
		return mCopyBtn;
	}

	public void enableNew(boolean bEnabled)
	{
			mNewBtn.setEnabled(bEnabled);
	}

	public void enableDelete(boolean bEnabled)
	{
			mDeleteBtn.setEnabled(bEnabled);
	}

	public void enableCopy(boolean bEnabled)
	{
			mCopyBtn.setEnabled(bEnabled);
	}

	protected void onNew()
	{
	}

	protected void onDelete()
	{
	}

	protected void onCopy()
	{
	}
}
