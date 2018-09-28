package crossdev64.settings;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;

import crossdev64.gui.ButtonPanel;
import crossdev64.gui.DialogBaseDlg;

public class GlobalSettingsDlg
	extends DialogBaseDlg
{
	private static final long serialVersionUID = 1L;
	private GlobalSettings mSettings = GlobalSettings.getInstance();

	private JTree mModuleTree;
	private ButtonPanel mButtonPanel;
	private JPanel mConfigPanel;

	public GlobalSettingsDlg(Window parent)
	{
		super(parent);

		setOK(false);

		setTitle(mSettings.getResourceString("settings.global_preferences"));
		setBounds(100, 100, 530, 421);
		final JPanel contentPanel = new JPanel();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {212};
		gbl_contentPanel.rowHeights = new int[] {30};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JSplitPane splitPane = new JSplitPane();
			GridBagConstraints gbc_splitPane = new GridBagConstraints();
			gbc_splitPane.fill = GridBagConstraints.BOTH;
			gbc_splitPane.gridx = 0;
			gbc_splitPane.gridy = 0;
			contentPanel.add(splitPane, gbc_splitPane);
			{
				JPanel panel = new JPanel();
				splitPane.setLeftComponent(panel);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{0, 0};
				gbl_panel.rowHeights = new int[]{0, 0, 0};
				gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
				panel.setLayout(gbl_panel);
				{
					mButtonPanel = new ButtonPanel()
					{
						@Override
						protected void onNew()
						{
							onAddItem();
						}

						@Override
						protected void onDelete()
						{
							onRemoveItem();
						}

						@Override
						protected void onCopy()
						{
							onCopyItem();
						}
					}
					;
					GridBagConstraints gbc_mButtonPanel = new GridBagConstraints();
					gbc_mButtonPanel.insets = new Insets(0, 0, 5, 0);
					gbc_mButtonPanel.anchor = GridBagConstraints.NORTHWEST;
					gbc_mButtonPanel.gridx = 0;
					gbc_mButtonPanel.gridy = 0;
					panel.add(mButtonPanel, gbc_mButtonPanel);
				}
				{
					mModuleTree = new JTree();
					mModuleTree.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					GridBagConstraints gbc_mModuleTree = new GridBagConstraints();
					gbc_mModuleTree.fill = GridBagConstraints.BOTH;
					gbc_mModuleTree.gridx = 0;
					gbc_mModuleTree.gridy = 1;
					panel.add(mModuleTree, gbc_mModuleTree);
				}
			}
			{
				mConfigPanel = new JPanel();
				splitPane.setRightComponent(mConfigPanel);
			}
		}
		{
			JPanel mButtonPane = new JPanel();
			mButtonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(mButtonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(mSettings.getResourceString("string.ok"));
				okButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						onOK();
					}
				});
				mButtonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(mSettings.getResourceString("string.cancel"));
				cancelButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						onCancel();
					}
				});
				mButtonPane.add(cancelButton);
			}
		}
	}

	protected JPanel getConfigPanel()
	{
		return mConfigPanel;
	}

	protected JTree getModuleTree()
	{
		return mModuleTree;
	}

	protected ButtonPanel getButtonPanel()
	{
		return mButtonPanel;
	}

	protected void onAddItem()
	{
		System.out.println("Add");
	}

	protected void onRemoveItem()
	{
		System.out.println("Remove");
	}

	protected void onCopyItem()
	{
		System.out.println("Copy");
	}
}
