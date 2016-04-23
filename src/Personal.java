
import java.io.Serializable;
import java.util.Date;

public class Personal extends Acquaintance implements  Serializable{

	private String context;
	private String acquainted_on;
	private String specific;
	
	public void setcontext(String occasion)
	{
	this.context = occasion;
	};
	public String getcontext()
	{
		return context;
	};
	
	public void setdate(String date)
	{
	this.acquainted_on = date;
	};
	public String getdate()
	{
		return acquainted_on;
	};
	public void setspecific(String event)
	{
	this.specific = event;
	};
	public String getspecific()
	{
		return specific;
	};
}
