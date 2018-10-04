package crossdev64.settings;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import crossdev64.gui.ButtonPanel;
import crossdev64.gui.DialogBasePanel;
import crossdev64.gui.TreeNodeBase;

public class GlobalSettingsPanel
	extends DialogBasePanel
{
	private static final long serialVersionUID = 1L;


	// This variable is needed because the node selection comes before the mouse click event.
	// So we need to differentiate these events.
	private boolean mNodeSelected;

	private JTree mSettingsTree;
	private ButtonPanel mButtonPanel;
	private JPanel mConfigPanel;
	private GridBagLayout mConfigPanelLayout;
	private SettingsTreeModel mSettingsModel;
	private GlobalSettingsDlg mParent;

	public GlobalSettingsPanel()
	{
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
					mSettingsTree = new JTree(mSettingsModel);
					mSettingsTree.setEditable(true);
					mSettingsTree.setCellEditor(getEditor());
					mSettingsTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
					mSettingsTree.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					mSettingsTree.addMouseListener
					(
						new MouseAdapter()
						{
							@Override
						    public void mouseClicked(MouseEvent me)
						    {
								boolean nodeSelected = mNodeSelected;
								mNodeSelected = false;
							    TreePath tp = mSettingsTree.getPathForLocation(me.getX(), me.getY());
							    if(tp == null)
							    	return;

							    // If the mouseclick was because of a node selection, we can ignore it.
							    if(nodeSelected)
							    	return;

							    onNodeClicked(tp);
						    }
						}
					);
					mSettingsTree.addTreeSelectionListener
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
					panel.add(mSettingsTree, gbc_mModuleTree);
				}
			}
			{
				mConfigPanel = new JPanel();
				mConfigPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				splitPane.setRightComponent(mConfigPanel);
				mConfigPanelLayout = new GridBagLayout();
				mConfigPanelLayout.columnWidths = new int[] {0};
				mConfigPanelLayout.rowHeights = new int[] {0};
				mConfigPanelLayout.columnWeights = new double[]{1.0};
				mConfigPanelLayout.rowWeights = new double[]{1.0};
				mConfigPanel.setLayout(mConfigPanelLayout);
			}
		}
	}

	private TreeCellEditor getEditor()
	{
		return new DefaultTreeCellEditor(mSettingsTree, (DefaultTreeCellRenderer)mSettingsTree.getCellRenderer())
		{
			@Override
			public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row)
			{
				return super.getTreeCellEditorComponent(tree, value, isSelected, expanded,leaf, row);
			}

			@Override
			public boolean isCellEditable(EventObject event)
			{
				boolean returnValue = super.isCellEditable(event);
				if (returnValue)
				{
					TreeNodeBase node = (TreeNodeBase)tree.getLastSelectedPathComponent();
					if (node != null)
						return node.canRename();
				}

				return false;
			}
		};
	}


	public void setDialogParent(GlobalSettingsDlg oParent)
	{
		mParent = oParent;
	}

	@Override
	public String getTitle()
	{
		return GlobalSettings.getResourceString("settings.global_preferences");
	}

	protected JPanel getConfigPanel()
	{
		return mConfigPanel;
	}

	protected JTree getModuleTree()
	{
		return mSettingsTree;
	}

	protected ButtonPanel getButtonPanel()
	{
		return mButtonPanel;
	}

	protected void createNode(TreeNodeBase oDefault)
	{
		TreePath path = mSettingsTree.getSelectionPath();
		TreeNodeBase node = (TreeNodeBase)path.getLastPathComponent();
		TreeNodeBase newNode;

		if(node.addByParent())
		{
			path = path.getParentPath();
			node = (TreeNodeBase)path.getLastPathComponent();
		}

		newNode = node.createItem(mParent, oDefault);
		if(newNode != null)
		{
			node.add(newNode);
			mSettingsModel.nodeStructureChanged(node);
			mSettingsTree.expandPath(path);
			path = path.pathByAddingChild(newNode);
			mSettingsTree.setSelectionPath(path);
		}
	}

	protected void onAddItem()
	{
		createNode(null);
	}

	protected void onRemoveItem()
	{
		TreePath path = mSettingsTree.getSelectionPath();
		TreeNodeBase node = (TreeNodeBase)path.getLastPathComponent();
		if(node != null && node.canRemove())
		{
			mSettingsModel.removeNodeFromParent(node);

			path = path.getParentPath();
			node = (TreeNodeBase)path.getLastPathComponent();
			mSettingsModel.nodeStructureChanged(node);
		}
	}

	protected void onCopyItem()
	{
		TreeNodeBase node = (TreeNodeBase)mSettingsTree.getLastSelectedPathComponent();
		createNode(node);
	}

	protected void onTreenodeSelected(TreeSelectionEvent oEvent)
	{
		TreeNodeBase node = (TreeNodeBase)mSettingsTree.getLastSelectedPathComponent();

		// Treenode was collapsed
		if(node == null)
			return;

		mNodeSelected = true;

		mButtonPanel.enableNew(node.canAdd());
		mButtonPanel.enableDelete(node.canDelete());
		mButtonPanel.enableCopy(node.canCopy());

		JPanel panel = node.getConfigPanel();
		if(panel == null)
			return;

		mConfigPanel.removeAll();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		mConfigPanel.add(panel, gbc);
		mConfigPanel.revalidate();
		mConfigPanel.repaint();
	}

	protected void onNodeClicked(TreePath oPath)
	{
		TreeNodeBase node = (TreeNodeBase)oPath.getLastPathComponent();

		if(!node.canRename())
			return;

		mSettingsTree.startEditingAtPath(oPath);
	}
}
