package crossdev64.settings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement(name="XMLTest")
public class XMLTest
{
	private int mNotSerialized;
	private int mSerializedInt;
	private String mSerializedString;

	public XMLTest()
	{
		mNotSerialized = 1;
		mSerializedInt = 2;
		mSerializedString = "str";
	}

	@JsonIgnore
	public int getNotSerialized()
	{
		return mNotSerialized;
	}

	public void setNotSerialized(int nNotSerialized)
	{
		mNotSerialized = nNotSerialized;
	}

	@XmlElement(name="SerializedInt")
	public int getSerializedInt()
	{
		return mSerializedInt;
	}

	public void setSerializedInt(int nSerializedInt)
	{
		mSerializedInt = nSerializedInt;
	}

	@XmlElement(name="SerializedString")
	public String getSerializedstring()
	{
		return mSerializedString;
	}

	public void setSerializedString(String oSerializedString)
	{
		mSerializedString = oSerializedString;
	}
}
