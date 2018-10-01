package crossdev64.settings;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import crossdev64.gui.ButtonPanel;
import crossdev64.gui.DialogBasePanel;

public class GlobalSettingsPanel
	extends DialogBasePanel
{
	private static final long serialVersionUID = 1L;

	private JTree mModuleTree;
	private ButtonPanel mButtonPanel;
	private JPanel mConfigPanel;
	private SettingsTreeModel mSettingsModel;
	private Window mParent;

	public GlobalSettingsPanel(Window oParent)
	{
		mParent = oParent;

		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {212};
		gbl_contentPanel.rowHeights = new int[] {30};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0};
		setLayout(gbl_contentPanel);
		{
			JSplitPane splitPane = new JSplitPane();
			GridBagConstraints gbc_splitPane = new GridBagConstraints();
			gbc_splitPane.fill = GridBagConstraints.BOTH;
			gbc_splitPane.gridx = 0;
			gbc_splitPane.gridy = 0;
			add(splitPane, gbc_splitPane);
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
						private static final long serialVersionUID = 1L;

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
					};

					mButtonPanel.enableNew(false);
					mButtonPanel.enableDelete(false);
					mButtonPanel.enableCopy(false);

					GridBagConstraints gbc_mButtonPanel = new GridBagConstraints();
					gbc_mButtonPanel.insets = new Insets(0, 0, 5, 0);
					gbc_mButtonPanel.anchor = GridBagConstraints.NORTHWEST;
					gbc_mButtonPanel.gridx = 0;
					gbc_mButtonPanel.gridy = 0;
					panel.add(mButtonPanel, gbc_mButtonPanel);
				}
				{
					mSettingsModel = new SettingsTreeModel();
					mModuleTree = new JTree(mSettingsModel);
					mModuleTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
					mModuleTree.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					mModuleTree.addTreeSelectionListener
					(
						new TreeSelectionListener()
						{
							@Override
							public void valueChanged(TreeSelectionEvent e)
							{
								onTreenodeSelected(e);
							}
						}
					);
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
	}

	@Override
	public String getTitle()
	{
		return GlobalSettings.getInstance().getResourceString("settings.global_preferences");
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
		TreePath path = mModuleTree.getSelectionPath();
		SettingsNode node = (SettingsNode)path.getLastPathComponent();
		SettingsNode newNode = node.createItem(mParent);
		if(newNode != null)
		{
			node.add(newNode);
			mSettingsModel.nodeStructureChanged(node);
			mModuleTree.expandPath(path);
		}
	}

	protected void onRemoveItem()
	{
		SettingsNode node = (SettingsNode)mModuleTree.getLastSelectedPathComponent();
		System.out.println("Remove:"+node);
	}

	protected void onCopyItem()
	{
		SettingsNode node = (SettingsNode)mModuleTree.getLastSelectedPathComponent();
		System.out.println("Copy:"+node);
	}

	protected void onTreenodeSelected(TreeSelectionEvent oEvent)
	{
		SettingsNode node = (SettingsNode)mModuleTree.getLastSelectedPathComponent();

		mButtonPanel.enableNew(node.canAdd());
		mButtonPanel.enableDelete(node.canDelete());
		mButtonPanel.enableCopy(node.canCopy());
	}
}
