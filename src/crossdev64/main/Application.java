package crossdev64.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import crossdev64.settings.GlobalSettings;

public class Application
{
	protected static Application main;
	protected static String[] mParams;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		mParams = args;

		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					new Application(Application.mParams);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		);
	}

	public Application(String[] args)
	{
		main = this;
		String theme = UIManager.getSystemLookAndFeelClassName();
		try
		{
			UIManager.setLookAndFeel(theme);
		}
		catch (Exception e)
		{
			System.err.println("MESSAGE:" + new Throwable().getStackTrace()[0]);
			System.err.println(e.getMessage());
			System.err.println(theme);
		}

		GlobalSettings.create(args);

		MainFrame frame = new MainFrame();
		try
		{
			SwingUtilities.updateComponentTreeUI(frame);
		}
		catch (Exception e)
		{
			System.err.println("MESSAGE:" + new Throwable().getStackTrace()[0]);
			System.err.println(e.getMessage());
			System.err.println(theme);
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void MessageBox(String oTitle, String oMessage)
	{
		JOptionPane.showMessageDialog(null, oMessage, "CrossDev64: " + oTitle, JOptionPane.INFORMATION_MESSAGE);
	}
}
