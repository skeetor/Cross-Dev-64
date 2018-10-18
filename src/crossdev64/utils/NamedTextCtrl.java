package crossdev64.utils;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NamedTextCtrl
	extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JLabel mNameLabel = null;
	private JTextField mInputText = null;

	/**
	 * This is the default constructor
	 */
	public NamedTextCtrl()
	{
		super();
		initialize();
	}

	public NamedTextCtrl(LayoutManager arg0)
	{
		super(arg0);
		initialize();
	}

	public NamedTextCtrl(boolean arg0)
	{
		super(arg0);
		initialize();
	}

	public NamedTextCtrl(LayoutManager arg0, boolean arg1)
	{
		super(arg0, arg1);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.fill = GridBagConstraints.BOTH;
		gridBagConstraints7.gridy = 0;
		gridBagConstraints7.weightx = 1.0;
		gridBagConstraints7.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints7.insets = new Insets(3, 3, 3, 3);
		gridBagConstraints7.gridx = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.ipadx = 20;
		gridBagConstraints.insets = new Insets(3, 3, 3, 3);
		gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints.gridy = 0;
		this.setSize(300, 32);
		this.setLayout(new GridBagLayout());
		this.add(getMNameLabel(), gridBagConstraints);
		this.add(getMInputText(), gridBagConstraints7);
	}

	/**
	 * This method initializes mFilepathText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JLabel getMNameLabel()
	{
		if (mNameLabel == null)
		{
			mNameLabel = new JLabel();
			mNameLabel.setText("Input");
		}
		return mNameLabel;
	}

	/**
	 * This method initializes mInputText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getMInputText()
	{
		if (mInputText == null)
		{
			mInputText = new JTextField();
		}
		return mInputText;
	}

	public String getLabelText()
	{
		return getMNameLabel().getText();
	}

	public void setLabelText(String oLabelText)
	{
		getMNameLabel().setText(oLabelText);
	}

	public String getText()
	{
		return getMInputText().getText();
	}

	public void setText(String oPath)
	{
		getMInputText().setText(oPath);
	}

	public void setEnableText(boolean bEnabled)
	{
		getMInputText().setEnabled(bEnabled);
	}

	public boolean isEnabledText()
	{
		return getMInputText().isEnabled();
	}

	public void setEditableText(boolean bEditable)
	{
		getMInputText().setEditable(bEditable);
	}

	public boolean isEditableText()
	{
		return getMInputText().isEditable();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
