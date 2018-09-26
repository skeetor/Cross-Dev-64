package crossdev64.settings;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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
	private ResourceBundle mStrings;

	public GlobalSettings(String args[])
	{
		initLanguage(null);
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
			// TODO: Should we enforce that a home directory has to exist?
			// What about if the user just wants to debug something without storing any settiongs? Is this a valid use case for us?
			Application.MessageBox(getResourceString("invalid_home"), getResourceString("settings.invalid_home_directory"));
			System.exit(-1);
		}

		if(!home.isDirectory() || !home.canWrite())
		{
			// TODO: Should we enforce that a home directory has to be writable?
			// What about if the user just wants to debug something without storing any settiongs? Is this a valid use case for us?
			Application.MessageBox(getResourceString("invalid_home"), getResourceString("invalid_home", home.getAbsolutePath()));
			System.exit(-1);
		}
	}

	private void initLanguage(Locale oLocale)
	{
		if(oLocale == null)
			oLocale = Locale.getDefault();

		try
		{
			mStrings = ResourceBundle.getBundle("crossdev64.resources.language.MessageResources", oLocale);
		}
		catch(MissingResourceException e)
		{
			// Ignore because it will use the default langauge file. 
		}
	}

	private void createOptions(String args[])
	{
		mParser = new SettingsParser(args);

		mParser.addOption("settings", 
			getResourceString("cmdline.settings_description"))
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

	public String getResourceString(String oKey, String...oParams)
	{
		// We intentionlly don't catch this, as this should be a reminder that a string is missing in the locale.
		String s = mStrings.getString(oKey);

		if(oParams == null)
			return s;

		return s;
	}
}
