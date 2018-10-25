package crossdev64.keybinding;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import crossdev64.settings.GlobalSettings;

public class KeyBindings
{
	private static KeyBindings mBindings;
	private static Map<String, KeyBindingConfig> mDefaultBindings;
	private static Map<String, KeyBindingConfig> mUserBindings;

	public static KeyBindings getInstance()
	{
		if(mBindings == null)
			mBindings = new KeyBindings();

		return mBindings;
	}

	protected KeyBindings()
	{
		loadDefaultBindings();
		reloadUserBindings();
	}

	protected void loadDefaultBindings()
	{
		if(mDefaultBindings == null)
		{
			mDefaultBindings = new HashMap<>();
			ResourceBundle bindings = ResourceBundle.getBundle("crossdev64.resources.DefaultKeybinding");
			Enumeration<String> keys = bindings.getKeys();
			while(keys.hasMoreElements())
			{
				String k = keys.nextElement();
				int pos = k.lastIndexOf('.');
				if(pos == -1)
					throw new RuntimeException("Invalid keybinding label for "+k);

				String type = k.substring(pos+1);
				String name = k.substring(0, pos);
				pos = name.lastIndexOf('.');
				if(pos == -1)
					throw new RuntimeException("Groupname missing for "+k);

				KeyBindingConfig binding = mDefaultBindings.get(name);
				if(binding == null)
				{
					binding = new KeyBindingConfig(name);
					mDefaultBindings.put(name, binding);

					// The label may not always make sense, so it doesn't have to exist.
					String value = null;
					try
					{
						value = bindings.getString(name+".label");
					}
					catch(Exception e)
					{
						value = "";
					}
					binding.setLabelId(value);
					if(!value.isEmpty())
						binding.setLabel(GlobalSettings.getResourceString(value));

					// The binding always have to exists, so if this doesn't exist
					// it triggers an exception which should not caught but fixed.
					value = bindings.getString(name+".binding");
					if(value == null)
						value = "";

					if(!value.isEmpty())
						binding.setKeyStroke(value);
				}

				if(!type.equals("binding") && !type.equals("label"))
					throw new RuntimeException("Invalid type for "+k);
			}
		}
	}

	public void reloadUserBindings()
	{
		mUserBindings = new HashMap<>();
	}

	public Map<String, KeyBindingConfig> getDefaults()
	{
		return mDefaultBindings;
	}

	public Map<String, KeyBindingConfig> getUser()
	{
		return mUserBindings;
	}

	public KeyBindingConfig getBinding(String oActionId)
	{
		KeyBindingConfig binding = KeyBindings.mUserBindings.get(oActionId);
		if(binding != null)
			return binding;

		return KeyBindings.mDefaultBindings.get(oActionId);
	}
}
