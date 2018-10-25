package crossdev64.keybinding;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import crossdev64.gui.DialogBasePanel;
import crossdev64.settings.GlobalSettings;
import crossdev64.utils.Stack;

public class KeyBindingPanel
	extends DialogBasePanel
{
	private static final long serialVersionUID = 1L;

	private JTable mShortcutTable;
	private DefaultTableModel mTableModel;
	private JTextField mCurrentTxt;
	private JTextField mShortcutTxt;
	private JTextField mFilterTxt;
	private JCheckBox mTogglePressedCheck;

	public KeyBindingPanel()
	{
		super();

		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel(GlobalSettings.getResourceString("string.filter"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		mFilterTxt = new JTextField();
		GridBagConstraints gbc_mFilterTxt = new GridBagConstraints();
		gbc_mFilterTxt.gridwidth = 2;
		gbc_mFilterTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_mFilterTxt.anchor = GridBagConstraints.NORTH;
		gbc_mFilterTxt.insets = new Insets(0, 0, 5, 0);
		gbc_mFilterTxt.gridx = 0;
		gbc_mFilterTxt.gridy = 1;
		add(mFilterTxt, gbc_mFilterTxt);
		mFilterTxt.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(getShortcutTable());
		
		JLabel lblNewLabel_1 = new JLabel(GlobalSettings.getResourceString("string.currently_assigned"));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		mCurrentTxt = new JTextField();
		mCurrentTxt.setEnabled(false);
		mCurrentTxt.setEditable(false);
		GridBagConstraints gbc_mCurrentTxt = new GridBagConstraints();
		gbc_mCurrentTxt.anchor = GridBagConstraints.NORTH;
		gbc_mCurrentTxt.insets = new Insets(0, 0, 5, 5);
		gbc_mCurrentTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_mCurrentTxt.gridx = 0;
		gbc_mCurrentTxt.gridy = 4;
		add(mCurrentTxt, gbc_mCurrentTxt);
		mCurrentTxt.setColumns(10);
		
		JButton mRemoveBtn = new JButton(GlobalSettings.getResourceString("string.remove"));
		GridBagConstraints gbc_mRemoveBtn = new GridBagConstraints();
		gbc_mRemoveBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_mRemoveBtn.anchor = GridBagConstraints.NORTH;
		gbc_mRemoveBtn.insets = new Insets(0, 0, 5, 0);
		gbc_mRemoveBtn.gridx = 1;
		gbc_mRemoveBtn.gridy = 4;
		add(mRemoveBtn, gbc_mRemoveBtn);
		
		JLabel lblPressKeys = new JLabel(GlobalSettings.getResourceString("string.press_shortcut"));
		GridBagConstraints gbc_lblPressKeys = new GridBagConstraints();
		gbc_lblPressKeys.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblPressKeys.insets = new Insets(0, 0, 5, 5);
		gbc_lblPressKeys.gridx = 0;
		gbc_lblPressKeys.gridy = 5;
		add(lblPressKeys, gbc_lblPressKeys);
		
		mTogglePressedCheck = new JCheckBox(GlobalSettings.getResourceString("string.toggle_pressed"));
		GridBagConstraints gbc_mTogglePressedCheck = new GridBagConstraints();
		gbc_mTogglePressedCheck.anchor = GridBagConstraints.NORTHWEST;
		gbc_mTogglePressedCheck.insets = new Insets(0, 0, 5, 0);
		gbc_mTogglePressedCheck.gridx = 1;
		gbc_mTogglePressedCheck.gridy = 5;
		add(mTogglePressedCheck, gbc_mTogglePressedCheck);
		
		mShortcutTxt = new JTextField();
		GridBagConstraints gbc_mShortcutTxt = new GridBagConstraints();
		gbc_mShortcutTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_mShortcutTxt.anchor = GridBagConstraints.NORTH;
		gbc_mShortcutTxt.insets = new Insets(0, 0, 0, 5);
		gbc_mShortcutTxt.gridx = 0;
		gbc_mShortcutTxt.gridy = 6;
		add(mShortcutTxt, gbc_mShortcutTxt);
		mShortcutTxt.setColumns(10);
		
		JButton mAssignBtn = new JButton(GlobalSettings.getResourceString("string.assign"));
		GridBagConstraints gbc_mAssignBtn = new GridBagConstraints();
		gbc_mAssignBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_mAssignBtn.anchor = GridBagConstraints.NORTH;
		gbc_mAssignBtn.gridx = 1;
		gbc_mAssignBtn.gridy = 6;
		add(mAssignBtn, gbc_mAssignBtn);
	}

	private JTable getShortcutTable()
	{
		if(mShortcutTable == null)
		{
			mTableModel = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					GlobalSettings.getResourceString("string.keybinding_name")
					, GlobalSettings.getResourceString("string.default_binding")
					, GlobalSettings.getResourceString("string.current_binding")
				}
			)
			{
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int nRow, int nColumn)
				{
					return false;
				}
			};

			mShortcutTable = new JTable(mTableModel);
			mShortcutTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			mShortcutTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
			{
				@Override
				public void valueChanged(ListSelectionEvent oEvent)
				{
					onRowSelected(oEvent);
				}
			});
			mShortcutTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

			reloadBindings();
		}

		return mShortcutTable;
	}

	protected String prepareKeyStroke(KeyStroke oKeyStroke)
	{
		String mod = "";
		String s  = oKeyStroke.toString().toUpperCase();
		s = s.replaceAll("PRESSED ", "");
		s = s.replaceAll("RELEASED ", "");

		if(s.indexOf("META") != -1)
		{
			s = s.replaceAll("META ", "");
			mod = "META";
		}
		
		if(s.indexOf("CTRL") != -1)
		{
			s = s.replaceAll("CTRL ", "");
			if(!mod.isEmpty())
				mod += "+";
			mod += "CTRL";
		}
		
		if(s.indexOf("SHIFT") != -1)
		{
			s = s.replaceAll("SHIFT ", "");
			if(!mod.isEmpty())
				mod += "+";
			mod += "Shift";
		}

		if(!mod.isEmpty())
			mod += " ";
	
		return mod+s;
	}

	/**
	 * Reloads the current bindings from the resource and user settings file.
	 */
	public void reloadBindings()
	{
		mTableModel.setRowCount(0);

		Map<String, KeyBindingConfig> defaults = KeyBindings.getInstance().getDefaults();

		int columns = mTableModel.getColumnCount();
		String[] row = new String[columns];
		for(KeyBindingConfig binding : defaults.values())
		{
			row[0] = binding.getActionId();
			row[1] = prepareKeyStroke(binding.getKeyStroke());

			mTableModel.addRow(row);
		}
	}

	protected void updateShortcutInfo(AbstractKeyBinding oBinding)
	{
		if(oBinding == null)
		{
			mCurrentTxt.setText("");
			mShortcutTxt.setText("");
			mTogglePressedCheck.setSelected(false);
		}
		else
		{
			KeyStroke keystroke = oBinding.getKeyStroke();
			
			mShortcutTxt.setText("");
			mCurrentTxt.setText(prepareKeyStroke(keystroke));
			mTogglePressedCheck.setSelected(!keystroke.isOnKeyRelease());
		}
	}
	
	protected void onRowSelected(ListSelectionEvent oEvent)
	{
		if(oEvent.getValueIsAdjusting())
			return;

		int row = mShortcutTable.getSelectionModel().getMinSelectionIndex();
		if(row == -1)
		{
			updateShortcutInfo(null);
			return;
		}

		String action = mTableModel.getValueAt(row, 0).toString();
		KeyBindingConfig binding = KeyBindings.getInstance().getBinding(action);
		updateShortcutInfo(binding);
	}
}
