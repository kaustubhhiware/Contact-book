
import java.io.Serializable;
import java.util.Date;

public class Relative extends Acquaintance implements  Serializable{

	private String birthday;
	private String last_met;
	public String type = "relative";
	public void setbday(String date)
	{
		this.birthday = date;
	};
	public String getbday()
	{
		return birthday;
	};

	public void setlastmeet(String date)
	{
	this.last_met = date;
	};
	public String getlastmeet()
	{
		return last_met;
	};
}
