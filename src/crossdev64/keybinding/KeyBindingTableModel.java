package crossdev64.keybinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.KeyStroke;
import javax.swing.table.AbstractTableModel;

import crossdev64.settings.GlobalSettings;

public class KeyBindingTableModel
	extends AbstractTableModel
//	extends DefaultTableModel
{
	private static final long serialVersionUID = 1L;
	private String[] mHeader;
	private List<KeyBindingConfig> mTotalRows = new ArrayList<>();
	private List<KeyBindingConfig> mRows = new ArrayList<>();
	private String mFilter = "";

	class Sorter
	implements Comparator<KeyBindingConfig> 
	{
		public int compare(KeyBindingConfig a, KeyBindingConfig b) 
		{
			return a.toString().compareTo(b.toString());
		}
	}

	public KeyBindingTableModel()
	{
		mHeader = new String[]
		{
			GlobalSettings.getResourceString("string.keybinding_name")
			, GlobalSettings.getResourceString("string.default_binding")
			, GlobalSettings.getResourceString("string.current_binding")
		};
	}

	@Override
	public int getRowCount()
	{
		return mRows.size();
	}

	@Override
	public int getColumnCount()
	{
		return mHeader.length;
	}

    public String getColumnName(int columnIndex)
    {
    	if(columnIndex < mHeader.length)
    	{
    		return mHeader[columnIndex];
    	}

    	return "MISSING";
    }

	@Override
	public Object getValueAt(int nRow, int nColumn)
	{
		KeyBindingConfig row = getDisplayRow(nRow);
		if(row == null)
			return "MISSING ROW";

		String val = "";
		switch(nColumn)
		{
			case 0:
				val = row.getActionId();
			break;

			case 1:
				val = KeyBinding.prepareToString(row.getDefault());
			break;

			case 2:
				val = KeyBinding.prepareToString(row.getOverride());
			break;

			default:
				val = "DEFAULT COLUMN";
		}

		if(val == null)
			val = "";

		return val;
	}

	public void setFilter(String oFilter)
	{
		mFilter = oFilter;
		if(mFilter == null)
			mFilter = "";

		filter();
	}

	protected void filter()
	{
		mRows.clear();
		for(KeyBindingConfig binding : mTotalRows)
			mRows.add(binding);

		Collections.<KeyBindingConfig>sort(mRows, new Sorter());
	}

	public void refresh()
	{
		filter();
		fireTableDataChanged();
	}

	public void addRow(KeyBindingConfig oBinding, boolean bRefresh)
	{
		if(oBinding != null)
			mTotalRows.add(oBinding);

		if(bRefresh)
			refresh();
	}

	public KeyBindingConfig getRow(int nRow)
	{
		if(nRow >= mTotalRows.size())
			return null;

		return mTotalRows.get(nRow);
	}

	public KeyBindingConfig getDisplayRow(int nRow)
	{
		if(nRow >= mRows.size())
			return null;

		return mRows.get(nRow);
	}

	/**
	 * Returns the index of the row which has the specified KeyStroke as the active binding.
	 * Returns -1 if no binding is found.
	 */
	public int getRowByKey(KeyStroke oKey)
	{
		if(oKey == null)
			return -1;

		int i = -1;
		for(KeyBindingConfig config : mTotalRows)
		{
			i++;
			KeyStroke ks = config.getKeyStroke();
			if(ks == null)
				continue;

			if(ks.equals(oKey))
				return i;
		}

		return -1;
	}
}
