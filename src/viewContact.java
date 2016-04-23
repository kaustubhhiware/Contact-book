
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;


public class viewContact implements  Serializable{



	/**
	 * Create the application.
	 * Create new Casual Acquaintance
	 */
	public viewContact(Acquaintance savedContact,ArrayList<Relative> relative_book,ArrayList<Personal> personal_book
			,ArrayList<Professional> professional_book,ArrayList<Casual> casual_book) throws IndexOutOfBoundsException
	{
		String type  = savedContact.gettype();
		String name = savedContact.getname();
		String number = savedContact.getnumber();
		String mail = savedContact.getmail();
		
		int k;

		if(savedContact.gettype().equals("relative"))
		{
			for(k = 0;k < relative_book.size() ; k++)
			{
				if((relative_book.get(k).getname().equals(name))&&(relative_book.get(k).getnumber().equals(number))&&(relative_book.get(k).getmail().equals(mail)))
				{
					viewRelative vR = new viewRelative(relative_book.get(k));
					break;
				}
			}
		}
		else if(savedContact.gettype().equals("personal"))
		{
			for(k = 0;k < personal_book.size() ; k++)
			{
				if((personal_book.get(k).getname().equals(name))&&(personal_book.get(k).getnumber().equals(number))&&(personal_book.get(k).getmail().equals(mail)))
				{
					viewPersonal vPe = new viewPersonal(personal_book.get(k));
					break;
				}
			}
		}
		else if(savedContact.gettype().equals("professional"))
		{
			for(k = 0;k < professional_book.size() ; k++)
			{
				if((professional_book.get(k).getname().equals(name))&&(professional_book.get(k).getnumber().equals(number))&&(professional_book.get(k).getmail().equals(mail)))
				{
					viewProfessional vPr = new viewProfessional(professional_book.get(k));
					break;
				}
			}
		}
		else if(savedContact.gettype().equals("casual"))
		{
			for(k = 0;k < casual_book.size() ; k++)
			{
				if((casual_book.get(k).getname().equals(name))&&(casual_book.get(k).getnumber().equals(number))&&(casual_book.get(k).getmail().equals(mail)))
				{
					viewCasual vC = new viewCasual(casual_book.get(k));
					break;
				}
			}
		}
		

	}
}
