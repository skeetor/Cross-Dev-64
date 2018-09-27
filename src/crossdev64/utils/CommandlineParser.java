package crossdev64.utils;

import java.util.ArrayList;
import java.util.List;

public class CommandlineParser
{
	private List<Option> mOptions;
	private int mErrorIndex;
	private String mErrorParam;

	public class Option
	{
		private String mName; // Long param used with '--'
		private String mParam; // Short param used with '-'
		private String mDescription;
		private boolean mOptional;
		private boolean mSingle;		// Option may appear only once. Additional occurrences overwrite previous ones.
		private boolean mPassThrough;  
		private int mMinArgs;
		private int mMaxArgs;
		private List<List<String>> mValues;

		/**
		 * If PassThrough is true, this means that commandline parsing is stopped and
		 * all arguments after it, are stored as is. 
		 */
		protected Option()
		{
			mSingle = true;
			mOptional = true;
			mMinArgs = 0;
			mMaxArgs = 0;
			mValues = null;			// The param doesn't exist. An empty list is created if the param was found in the argument parsing.
			mPassThrough = false;
		}

		public String toString()
		{
			String s = "[";
			if(mName == null)
				s += "null";
			else
				s += mName;

			s += ":";

			if(mParam== null)
				s += "null";
			else
				s += mParam;

			s += "]";
			
			return s;
		}

		/**
		 * If a parameter can occur multiple times, we need to keep track of each arguments individually
		 * @return
		 */
		protected List<String> addSection()
		{
			if(mValues == null)
				mValues = new ArrayList<>();

			List<String> l = new ArrayList<>();
			mValues.add(l);
			return l;
		}

		private List<String> getLatest()
		{
			if(mValues == null)
				return addSection();

			return mValues.get(mValues.size()-1);
		}

		public Option addValue(String oValue)
		{
			getLatest().add(oValue);
			return this;
		}

		public List<List<String>> values()
		{
			return mValues;
		}

		public Option name(String oName)
		{
			mName = oName;
			return this;
		}

		public String name()
		{
			return mName;
		}

		public Option param(String oParam)
		{
			mParam = oParam;
			return this;
		}

		public String param()
		{
			return mParam;
		}

		public Option description(String oDescription)
		{
			mDescription = oDescription;
			return this;
		}

		public String description()
		{
			return mDescription;
		}

		public Option single()
		{
			mSingle = true;
			return this;
		}

		public Option multiple()
		{
			mSingle = false;
			return this;
		}

		public Option passThrough(boolean bPassThrough)
		{
			mPassThrough = bPassThrough;
			return this;
		}

		public boolean isPassThrough()
		{
			return mPassThrough;
		}

		public boolean isSingle()
		{
			return mSingle;
		}

		public boolean isMultiple()
		{
			return !mSingle;
		}

		public Option optional()
		{
			mOptional = true;
			return this;
		}

		public Option mandatory()
		{
			mOptional = false;
			return this;
		}

		public boolean isOptional()
		{
			return mOptional;
		}

		public boolean isMandatory()
		{
			return !mOptional;
		}

		public Option arguments()
		{
			mMinArgs = 1;
			mMaxArgs = 1;
			return this;
		}

		public Option arguments(int nArguments)
		{
			mMinArgs = nArguments;
			mMaxArgs = nArguments;
			return this;
		}

		public Option arguments(int nMinArguments, int nMaxArguments)
		{
			mMinArgs = nMinArguments;
			mMaxArgs = nMaxArguments;
			return this;
		}

		public Option minArguments(int nMinArgs)
		{
			mMinArgs = nMinArgs;
			return this;
		}

		public Option maxArguments(int nMaxArgs)
		{
			mMaxArgs = nMaxArgs;
			return this;
		}

		public int minArguments()
		{
			return mMinArgs;
		}

		public int maxArguments()
		{
			return mMaxArgs;
		}
	}

	public CommandlineParser()
	{
		mOptions = new ArrayList<>();
		mOptions.add(new Option().name("").arguments(-1,  -1));
	}

	public int getErrorIndex()
	{
		return mErrorIndex;
	}

	public String getErrorParam()
	{
		return mErrorParam;
	}

	/**
	 * Returns a new list so that the data can not be changed.
	 * @return
	 */
	protected List<Option> getOptions()
	{
		return new ArrayList<Option>(mOptions);
	}

	public void help()
	{
		for(Option o : mOptions)
		{
			if(o.param() != null && o.name() != null)
				System.out.print(o.name() + " / "+ o.param());
			else if(o.param() != null)
				System.out.print(o.param());
			else
				System.out.print(o.name());

			System.out.print(":");

			if(o.isMandatory())
				System.out.print(" M");
			else
				System.out.print(" O");

			if(o.minArguments() != -1 || o.maxArguments() != -1)
			{
				if(o.minArguments() == -1)
					System.out.print(" * ... "+ o.maxArguments());
				else if(o.maxArguments() == -1)
					System.out.print(o.minArguments() + " ... N");
				else
					System.out.print(o.minArguments() + " ... "+o.maxArguments());
			}
				
			System.out.println(" " +o.description());
		}
	}

	protected Option findOption(String oName)
	{
		if(oName == null)
			return null;

		for(Option o : getOptions())
		{
			if(oName.equals(o.name()))
				return o;
		}

		return null;
	}
	
	protected Option findParam(String oName)
	{
		if(oName == null)
			return null;

		for(Option o : getOptions())
		{
			if(oName.equals(o.param()))
				return o;
		}

		return null;
	}

	public boolean insertOption(Option oOption)
	{
		// One of them must be set.
		if(oOption.name() == null && oOption.param() == null)
			return false;

		if(findOption(oOption.name()) != null)		// Already exists
			return false;

		if(findParam(oOption.param()) != null)		// Already exists
			return false;

		mOptions.add(oOption);

		return true;
	}

	public Option addOption(String oOption, String oDescription)
	{
		if(oOption == null)
			return null;

		Option o = new Option();
		o.name(oOption);
		o.description(oDescription);
		if(insertOption(o) == false)
			return null;

		return o;
	}

	public Option addOption(String oOption, String oParam, String oDescription)
	{
		if(oOption == null)
			return null;

		Option o = new Option();
		o.name(oOption);
		o.param(oParam);
		o.description(oDescription);
		if(insertOption(o) == false)
			return null;

		return o;
	}
	
	public Option addParam(String oParam, String oDescription)
	{
		if(oParam == null)
			return null;

		Option o = new Option();
		o.param(oParam);
		o.description(oDescription);
		if(insertOption(o) == false)
			return null;

		return o;
	}

	public boolean hasArgument(String oName)
	{
		return getArgument(oName) != null;
	}

	/**
	 * If the parameter was given on the commandline, it returns the list of parameters, otherwise null.
	 * 
	 * @param oName
	 * @return
	 */
	public List<List<String>> getArgument(String oName)
	{
		Option o = findOption(oName);
		if(o == null)
			o = findParam(oName);
		
		if(o == null)
			return null;

		return o.values();
	}

	/**
	 * Returns the nth argument or null.
	 * 
	 * @param oName
	 * @param nIndex
	 * @return
	 */
	public List<String> getArgument(String oName, int nIndex)
	{
		List<List<String>> params = getArgument(oName);
		if(params == null)
			return null;

		if(nIndex >= params.size())
			return null;

		return params.get(nIndex);
	}

	/**
	 * Validates if the number of the arguments of the option matches.
	 * 
	 * @param oOption
	 * @param nAdditionals Number of additional arguments which may have been added but were assigned to undefined.
	 * @return
	 */
	protected boolean validateOptionParamCount(Option oOption, int nAdditionals)
	{
		List<List<String>> values = oOption.values();

		// Option doesn't allow multiple occurences
		if(values.size() > 1 && oOption.isSingle())
			return false;

		int minargs = oOption.minArguments();
		int maxargs = oOption.maxArguments();

		// This option allows any number of parameters 0..N, so it is always ok.
		if(minargs == -1 && maxargs == -1)
			return true;

		for(List<String> params : values)
		{
			int size = params.size();
			if(minargs != -1 && size < minargs)
				return false;

			if(maxargs != -1 && size > maxargs)
				return false;
		}

		// If we have additionals we also check if the last section contains the correct numbers if the
		// additionals were added because this is what the command line would look like
		// I.E. --only_one_argument valid_argument wrong_argument --next_option
		// would mean that we have one additional here which would be invalid.
		if(nAdditionals > 0)
		{
			List<String> params = values.get(values.size()-1);
			int size = params.size()+nAdditionals;
			if(minargs != -1 && size < minargs)
				return false;

			if(maxargs != -1 && size > maxargs)
				return false;
		}
		
		return true;
	}

	/**
	 * Parse the argument according to the options specified.
	 * 
	 * @param args
	 * @return
	 */
	public boolean parse(String[] args)
	{
		mErrorIndex = -1;
		mErrorParam = null;
		
		if(args == null)
			return false;
		
		Option undefined = findOption("");		// unknown params go here.
		undefined.addSection();
		Option current = undefined;
		Option prev = undefined;
		int arg_count = 0;

		for(int i = 0; i < args.length; i++)
		{
			String param = args[i];

			// If this is a passthrough parameter, we always store the args without parsing.
			if(current.isPassThrough())
			{
				current.getLatest().add(param);
				continue;
			}

			if(param.startsWith("-"))
			{
				String name;
				Option o;
				if(param.startsWith("--"))
				{
					name = param.substring(2);
					if(name.length() == 0)
						name = "--";

					o = findOption(name);
				}
				else
				{
					name = param.substring(1);
					if(name.length() == 0)
						name = "-";

					o = findParam(name);
				}

				// Validate the option we are leaving before we switch to a new one.
				if(o == null || !validateOptionParamCount(current, 0) || !validateOptionParamCount(prev, arg_count))
				{
					mErrorIndex = i;
					mErrorParam = param;
					return false;
				}

				arg_count = 0;
				prev = undefined;
				current = o;
				current.addSection();
				continue;
			}

			int max = current.maxArguments();
			if(max != -1 && current.getLatest().size() >= max)
			{
				arg_count = 0;
				prev = current;
				current = undefined;
			}

			// Count how many arguments we would have passed to the previous option
			if(current.name().equals(""))
				arg_count++;

			current.getLatest().add(param);

			if(!validateOptionParamCount(current, arg_count))
			{
				mErrorIndex = i;
				mErrorParam = param;
				return false;
			}
		}

		return true;
	}
}
