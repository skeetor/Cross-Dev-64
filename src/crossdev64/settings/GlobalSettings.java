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
			Application.MessageBox("Invalid commandline", getResourceString("settings.invalid_parameters", Arrays.toString(mParams)).toString());
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

		String s = getResourceString("test1", Arrays.toString(args).toString());
		s = getResourceString("settings.invalid_home_description", Arrays.toString(args).toString());
		System.out.println("Test: "+s);

		if(!home.isDirectory() || !home.canWrite())
		{
			// TODO: Should we enforce that a home directory has to be writable?
			// What about if the user just wants to debug something without storing any settiongs? Is this a valid use case for us?
			Application.MessageBox(getResourceString("invalid_home"), getResourceString("settings.invalid_home_description", home.getAbsolutePath()));
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
		// We intentionally don't catch this, as this should be a reminder that a string is missing in the locale.
		String s = mStrings.getString(oKey);

		if(oParams == null || oParams.length == 0)
			return s;

		int translated = 0;
		String str = "";
		do
		{
			int pos = s.indexOf('%');
			if(pos == -1)
			{
				str += s;
				break;
			}

			str += s.substring(0,  pos);
			pos++;

			// The '%' was the last character of the string which may be valid.
			if(pos >= s.length())
			{
				str += s;
				break;
			}

			char c = s.charAt(pos);
			if(c == '%')
			{
				pos++;
				str += "%";
			}

			int index = 0;
			
			s = s.substring(pos);
		}
		while(true);

		if(translated != oParams.length)
		{
			// An untranslated parameter means that there is something wrong with the translation and the developer should fix it.
			throw new RuntimeException("The resource key ["+oKey+"] contains untranslated parameters: "+ translated + " != " + oParams.length);
		}
		return str;
	}
}
