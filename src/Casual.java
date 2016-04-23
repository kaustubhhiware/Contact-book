
import java.io.Serializable;

//import java.util.Date;

public class Casual extends Acquaintance implements  Serializable{
	
    
	private String when;
	private String where;
	private String circumstance;
	private String otherInfo;
	
	public void setwhen(String on)
	{
		this.when = on;
	};
	public String getwhen()
	{
		return when;
	};
	
	public void setwhere(String place)
	{
		this.where = place;
	};
	public String getwhere()
	{
		return where;
	};
	

	public void setcircumstance(String condition)
	{
		this.circumstance = condition;
	};
	public String getcircumstance()
	{
		return circumstance;
	};
	
	public void setotherInfo(String Information)
	{
		this.otherInfo = Information;
	};
	public String getotherInfo()
	{
		return otherInfo;
	};
}
