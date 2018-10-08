package crossdev64.settings;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import crossdev64.main.Application;
import crossdev64.utils.CommandlineParser;
import crossdev64.utils.Stack;

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

	private static GlobalSettings mSettings;
	private SettingsParser mParser;
	private File mHome;
	private ResourceBundle mStrings;
	private GlobalSettingsNode mRootNode;

	public static void create(String[] args)
	{
		if(mSettings == null)
			mSettings = new GlobalSettings(args);
	}

	public static GlobalSettings getInstance()
	{
		if(mSettings == null)
			mSettings = new GlobalSettings(null);

		return mSettings;
	}

	protected GlobalSettings(String args[])
	{
		initLanguage(null, null);
		createOptions(args);

		if(args != null && !mParser.parse(args))
		{
			// This will not return and terminate the application.
			mParser.help();
		}

		if(mParser.hasArgument("locale"))
		{
			String loc = mParser.getArgument("locale").get(0).get(0);
			Locale l = new Locale(loc);

			// restart, so that the user may get localized error messages.
			initLanguage(loc, l);
			createOptions(args);

			// We already know that parsing worked if we reached here, so we don't need to check it again.
			mParser.parse(args);
		}

		// Check if the home directory is to be overridden
		if(mParser.hasArgument("settings"))
			mHome = new File(mParser.getArgument("settings").get(0).get(0));

		File home = getHome();
		if(home == null)
		{
			// TODO: Should we enforce that a home directory has to exist?
			// What about if the user just wants to debug something without storing any settings? Is this a valid use case for us?
			Application.MessageBox(getResourceString("invalid_home"), getResourceString("settings.invalid_home_directory"));
			System.exit(-1);
		}

		if(!home.isDirectory() || !home.canWrite())
		{
			// TODO: Should we enforce that a home directory has to be writable?
			// What about if the user just wants to debug something without storing any settings? Is this a valid use case for us?
			Application.MessageBox(getResourceString("invalid_home"), getResourceString("settings.invalid_home_description", home.getAbsolutePath()));
			System.exit(-1);
		}
	}

	private void initLanguage(String oLocaleName, Locale oLocale)
	{
		if(oLocale == null)
			oLocale = Locale.getDefault();

		if(mStrings != null)
			ResourceBundle.clearCache();

		if(oLocaleName != null && oLocaleName.toLowerCase().equals("default"))
		{
			// Force to use the default settings. This is necessary, because otherwise
			// ResourceBundle would always load the default setting instead.
			mStrings = ResourceBundle.getBundle("crossdev64.resources.language.MessageResources",
			    new ResourceBundle.Control()
				{
			        @Override
			        public List<Locale> getCandidateLocales(String name,
			                                                Locale locale) {
			            return Collections.singletonList(Locale.ROOT);
			        }
			    }
			);
		}
		else
		{
			try
			{
				mStrings = ResourceBundle.getBundle("crossdev64.resources.language.MessageResources", oLocale);
			}
			catch(MissingResourceException e)
			{
				// Ignore because it will use the default langauge file. 
			}
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

		mParser.addOption("locale", 
			getResourceString("cmdline.locale_description"))
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

	/**
	 * Fetch a string from the resources. The resources can contain positional parameters which are
	 * set by '%n'. In order to use the '%' character itself a second '%' has to follow. Otherwise
	 * a number 1...N follows the '%' which indicates the position in the array that should be used in the string.
	 * Thus the parameters can be freely positioned within a string, which may be required depending on the
	 * translation, as not all languages will have the same order of wording.
	 * 
	 * In case of errors or unused parameters in the translation, a RuntimeException is thrown. This should
	 * NOT be caught and should remind the developer that he forgot to add a correct translation.
	 * 
	 * @param oKey
	 * @param oParams
	 * @return
	 */
	public static String getResourceString(String oKey, String...oParams)
	{
		String s;
		
		try
		{
			s = GlobalSettings.mSettings.mStrings.getString(oKey);
		}
		catch(Exception e)
		{
			return oKey;
		}

		if(oParams == null || oParams.length == 0)
			return s;

		boolean translated[] = new boolean[oParams.length];
		for(int i = 0; i < translated.length; i++)
			translated[i] = false;

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

			String num = "";
			while(true)
			{
				if(pos >= s.length())
					break;

				char ch = s.charAt(pos);
				if(ch < '0' || ch > '9')
					break;

				num += ch;
				pos++;
			}

			if(num.length() == 0)
				throw new RuntimeException("The resource key ["+oKey+"] contains no parameter index!");

			int index = Integer.parseInt(num)-1;
			if(index < 0 || index >= oParams.length)
				throw new RuntimeException("The resource key ["+oKey+"] contains an invalid index! %"+(index+1) + "/"+oParams.length);

			translated[index] = true;
			str += oParams[index];
			s = s.substring(pos);
		}
		while(true);

		for(int i = 0; i < translated.length; i++)
		{
			if(translated[i] == false)
			{
				// An untranslated parameter means that there is something wrong with the translation and the developer should fix it.
				throw new RuntimeException("The resource key ["+oKey+"] contains untranslated parameters: "+ (i+1));
			}
		}

		return str;
	}

	public GlobalSettingsNode getRootNode()
	{
		if(mRootNode == null)
			mRootNode = new GlobalSettingsNode();

		return mRootNode;
	}

	public void load()
	{
	}

	public void save()
	{
		try
		{
			GlobalSettingsModule module = getRootNode().getModule();
			XmlMapper mapper = new XmlMapper();
			String s = mapper.writeValueAsString(module);

			//root.put("module_id", treeRoot.getModuleId());

			//String s = mapper.writeValueAsString(root);
//			ObjectNode data = treeRoot.save(root);
			System.out.println(Stack.getSourcePosition()+"XML\n"+s);
		}
		catch(Throwable e)
		{
			System.out.println(Stack.getSourcePosition()+"Exception in saving:"+e.getMessage());
			e.printStackTrace();
		}
	}
}
