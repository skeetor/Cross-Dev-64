package crossdev64.settings;

import java.io.File;
import java.util.Arrays;

import crossdev64.main.Application;
import crossdev64.utils.CommandlineParser;

public class GlobalSettings
{
	public static String APP_NAME = "CrossDev64";

	class SettingsParser
		extends CommandlineParser
	{
		private String[] mParams;

		public SettingsParser(String[] args)
		{
			mParams = args;
		}

		public void help()
		{
			String s = "Invalid parameters in the commandline. Terminating!\n"+Arrays.toString(mParams);
			Application.MessageBox("Invalid commandline", s);
			System.exit(-1);
		}

		public boolean parse()
		{
			return super.parse(mParams);
		}
	};

	private SettingsParser mParser;
	private File mHome;

	public GlobalSettings(String args[])
	{
		createOptions(args);

		if(!mParser.parse(args))
		{
			// This will not return and terminate the application.
			mParser.help();
		}

		// Check if the home directory is to be overridden
		if(mParser.hasArgument("settings"))
			mHome = new File(mParser.getArgument("settings").get(0).get(0));

		File home = getHome();
		if(home == null)
		{
			// TODO: Should we enforce that a home directory has to exists?
			// What about if the user just wants to debug something without storing any settiongs? Is this a valid use case for us?
			Application.MessageBox("Invalid home directory", "The home directory can not be determined. Aborting!");
			System.exit(-1);
		}

		if(!home.isDirectory() || !home.canWrite())
		{
			// TODO: Should we enforce that a home directory has to be writable?
			// What about if the user just wants to debug something without storing any settiongs? Is this a valid use case for us?
			Application.MessageBox("Invalid home directory", "The home "+ home.getAbsolutePath()+" directory can not be used for storing settings. Aborting!");
			System.exit(-1);
		}
	}

	private void createOptions(String args[])
	{
		mParser = new SettingsParser(args);

		mParser.addOption("settings",
			"Set the home directory where the settings are stored."
					+ " Default is to use the systems home directory."
			)
			.arguments()
			.optional()
		;
	}

	public File getHome()
	{
		if(mHome == null)
		{
			File sFile;
			if (!new File(System.getProperty("user.home") + java.io.File.separatorChar + "."+APP_NAME).exists())
			{
				try
				{
					if (new File(System.getProperty("user.home") + java.io.File.separatorChar + "."+APP_NAME).mkdirs())
					{
						sFile = new File(System.getProperty("user.home")
								+ java.io.File.separatorChar + "."+APP_NAME
								+ java.io.File.separatorChar);
					}
					else
					{
						sFile = new File(System.getProperty("user.home") + java.io.File.separatorChar);
					}
				}
				catch (SecurityException ex)
				{
					sFile = null;
				}
			}
			else
			{
				sFile = new File(System.getProperty("user.home")
						+ java.io.File.separatorChar + "."+APP_NAME
						+ java.io.File.separatorChar);
			}

			mHome = sFile;
		}

		return mHome;
	}
}
