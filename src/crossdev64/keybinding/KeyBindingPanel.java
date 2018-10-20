package crossdev64.keybinding;

import crossdev64.gui.DialogBasePanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

public class KeyBindingPanel
	extends DialogBasePanel
{
	private static final long serialVersionUID = 1L;

	public KeyBindingPanel()
	{
		super();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTest = new JLabel("test");
		GridBagConstraints gbc_lblTest = new GridBagConstraints();
		gbc_lblTest.gridx = 0;
		gbc_lblTest.gridy = 0;
		add(lblTest, gbc_lblTest);
	}
}
