package crossdev64.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import crossdev64.settings.GlobalSettingsDlg;

public class ButtonPanel
	extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JButton mNewBtn;
	private JButton mDeleteBtn;
	private JButton mCopyBtn;

	/**
	 * Create the panel.
	 */
	public ButtonPanel()
	{
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(2);
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		mNewBtn = new JButton();
		mNewBtn.setIcon(new ImageIcon(GlobalSettingsDlg.class.getResource("/crossdev64/resources/icons/small-icons-com/add.png")));
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
		mDeleteBtn.setIcon(new ImageIcon(GlobalSettingsDlg.class.getResource("/crossdev64/resources/icons/small-icons-com/delete.png")));
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
		mCopyBtn.setIcon(new ImageIcon(GlobalSettingsDlg.class.getResource("/crossdev64/resources/icons/small-icons-com/copy.png")));
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
