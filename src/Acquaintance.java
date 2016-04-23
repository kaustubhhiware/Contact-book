
import java.io.Serializable;

public class Acquaintance implements  Serializable {

	private String name;
	private String phone;
	private String email;
	private String type;
	
	public void settype(String type)
	{
		this.type = type;
	};
	public String gettype()
	{
		return type;
	};
	
	public void setname(String naam)
	{
		this.name = naam;
	};
	public String getname()
	{
		return name;
	};
	public void setnumber(String num)
	{
		this.phone = num;
	};
	public String getnumber()
	{
		return phone;
	};
	
	public void setmail(String mail)
	{
		this.email = mail;
	};
	public String getmail()
	{
		return email;
	};
	
	
}
