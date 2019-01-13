package crossdev64.keybinding;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import crossdev64.utils.Stack;

public class KeyBindings
{
	private static KeyBindings mBindingsInstance;
	private static Map<String, KeyBindingConfig> mBindings;

	public static KeyBindings getInstance()
	{
		if(mBindingsInstance == null)
			mBindingsInstance = new KeyBindings();

		return mBindingsInstance;
	}

	protected KeyBindings()
	{
		loadBindings();
	}

	protected void restoreBinding(Map<String, String> oKeys, Map<String, KeyBindingConfig> oMap, boolean bDefault)
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

				binding.setLabel(value);

				// The binding always have to exists, so if this doesn't exist
				// it triggers an exception which should not caught but fixed.
				value = oKeys.get(name+".binding");
				if(value == null)
					value = "";

				if(!value.isEmpty())
				{
					if(bDefault)
						binding.setDefault(value);
					else
						binding.setOverride(value);
				}
			}
		}
	}
	
	protected void loadBindings(String oFilename, Map<String, KeyBindingConfig> oMap, boolean bDefault)
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
		restoreBinding(values,  mBindings, bDefault);
	}

	protected void loadBindings()
	{
		if(mBindings == null)
		{
			mBindings = new HashMap<>();
			loadBindings("crossdev64.resources.DefaultKeybinding", mBindings, true);
		}

		loadUserBindings();
	}

	public void loadUserBindings()
	{
		System.out.println(Stack.getSourcePosition()+": Load user bindings");
	}

	public Map<String, KeyBindingConfig> getBindings()
	{
		return mBindings;
	}

	public KeyBindingConfig getBinding(String oActionId)
	{
		return mBindings.get(oActionId);
	}
}
