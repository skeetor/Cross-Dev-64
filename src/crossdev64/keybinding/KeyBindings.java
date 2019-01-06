package crossdev64.keybinding;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import crossdev64.settings.GlobalSettings;
import crossdev64.utils.Stack;

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

	protected void restoreBinding(Map<String, String> oKeys, Map<String, KeyBindingConfig> oMap)
	{
		for (Map.Entry<String, String> entry : oKeys.entrySet())
		{
			String k = entry.getKey();
			int pos = k.lastIndexOf('.');
			if(pos == -1)
				throw new RuntimeException("Invalid keybinding label for "+k);

			String type = k.substring(pos+1);

			// Labels will be built when the binding is processed. A label alone doesn't make sense.
			if(type.equals("label"))
				continue;

			if(!type.equals("binding"))
				throw new RuntimeException(Stack.getSourcePosition()+": Invalid type ["+type+"] for "+k);

			String name = k.substring(0, pos);
			pos = name.lastIndexOf('.');
			if(pos == -1)
				throw new RuntimeException(Stack.getSourcePosition()+": Groupname missing for "+k);

			KeyBindingConfig binding = oMap.get(name);
			if(binding == null)
			{
				binding = new KeyBindingConfig(name);
				oMap.put(name, binding);

				// The label may not always make sense, so it doesn't have to exist.
				String value = oKeys.get(name+".label");
				if(value == null)
					value = "";

				binding.setLabelId(value);
				if(!value.isEmpty())
					binding.setLabel(GlobalSettings.getResourceString(value));

				// The binding always have to exists, so if this doesn't exist
				// it triggers an exception which should not caught but fixed.
				value = oKeys.get(name+".binding");
				if(value == null)
					value = "";

				if(!value.isEmpty())
					binding.setKeyStroke(value);
			}
		}
	}
	
	protected void loadBindings(String oFilename, Map<String, KeyBindingConfig> oMap)
	{
		ResourceBundle bindings = ResourceBundle.getBundle(oFilename);
		Enumeration<String> keys = bindings.getKeys();
		Map<String, String> values = new HashMap<>();
		while(keys.hasMoreElements())
		{
			String k = keys.nextElement();
			try
			{
				String value = bindings.getString(k);
				values.put(k, value);
			}
			catch(Exception e)
			{
			}
		}
		restoreBinding(values,  mDefaultBindings);
	}

	protected void loadDefaultBindings()
	{
		if(mDefaultBindings == null)
		{
			mDefaultBindings = new HashMap<>();
			loadBindings("crossdev64.resources.DefaultKeybinding", mDefaultBindings);
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
