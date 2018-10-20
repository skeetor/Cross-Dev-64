package crossdev64.settings;

import java.awt.Window;

import crossdev64.gui.DialogBaseDlg;

public class GlobalSettingsDlg
	extends DialogBaseDlg<GlobalSettingsPanel>
{
	private static final long serialVersionUID = 1L;

	public GlobalSettingsDlg(Window oParent)
	{
		super(oParent, new GlobalSettingsPanel());

		setBounds(100, 100, 530, 421);
	}

	public void onApply()
	{
		getPanel().onApply();
	}
}
