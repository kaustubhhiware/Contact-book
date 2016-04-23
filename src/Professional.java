
import java.io.Serializable;

public class Professional extends Acquaintance implements  Serializable{

	private String interest;
	
	public void setinterest(String hobby)
	{
	this.interest = hobby;
	};
	public String getinterest()
	{
		return interest;
	};
}
