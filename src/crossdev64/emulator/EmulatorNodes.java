package crossdev64.emulator;

import java.awt.Window;

import crossdev64.gui.StringInputDlg;
import crossdev64.settings.GlobalSettings;
import crossdev64.settings.SettingsModuleNode;

public class EmulatorNodes
	extends SettingsModuleNode
{
	private static final long serialVersionUID = 1L;

	public EmulatorNodes()
	{
		super(GlobalSettings.getInstance().getResourceString("string.emulator"));
	}

	@Override
	public boolean canAdd()
	{
		return true;
	}

	@Override
	public SettingsModuleNode createItem(Window oParent)
	{
		StringInputDlg dlg = new StringInputDlg(oParent, "Emulator node name", "Please provide a name for referencing this entry", "VICE");
		if(!dlg.showModal())
			return null;

		return null;
	}
}
