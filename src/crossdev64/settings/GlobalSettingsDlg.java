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
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import crossdev64.gui.DialogBaseDlg;

public class GlobalSettingsDlg
	extends DialogBaseDlg
{
	private static final long serialVersionUID = 1L;
	private GlobalSettings mSettings = GlobalSettings.getInstance();

	private JPanel mConfigPanel;
	private JTree mModuleTree;

	public GlobalSettingsDlg(Window parent)
	{
		super(parent);

		setOK(false);

		setTitle(mSettings.getResourceString("settings.global_preferences"));
		setBounds(100, 100, 450, 300);
		final JPanel contentPanel = new JPanel();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{212, 212, 0};
		gbl_contentPanel.rowHeights = new int[]{218, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			mModuleTree = new JTree();
			GridBagConstraints gbc_mModuleTree = new GridBagConstraints();
			gbc_mModuleTree.anchor = GridBagConstraints.WEST;
			gbc_mModuleTree.fill = GridBagConstraints.VERTICAL;
			gbc_mModuleTree.insets = new Insets(0, 0, 0, 5);
			gbc_mModuleTree.gridx = 0;
			gbc_mModuleTree.gridy = 0;
			contentPanel.add(mModuleTree, gbc_mModuleTree);
			mModuleTree.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		{
			mConfigPanel = new JPanel();
			GridBagConstraints gbc_mConfigPanel = new GridBagConstraints();
			gbc_mConfigPanel.fill = GridBagConstraints.BOTH;
			gbc_mConfigPanel.gridx = 1;
			gbc_mConfigPanel.gridy = 0;
			contentPanel.add(mConfigPanel, gbc_mConfigPanel);
			GridBagLayout gbl_mConfigPanel = new GridBagLayout();
			gbl_mConfigPanel.columnWidths = new int[]{0, 0, 0, 0};
			gbl_mConfigPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_mConfigPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_mConfigPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			mConfigPanel.setLayout(gbl_mConfigPanel);
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
}
