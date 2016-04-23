
import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class newPersonal extends JFrame implements  Serializable{

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_5;
	static int numLimit = 4;
	   public static boolean isNumeric(String str)  
	    {  
	      try  
	      {  
	        double d = Double.parseDouble(str);  
	      }  
	      catch(NumberFormatException nfe)  
	      {  
	        return false;  
	      }  
	      return true;  
	    }
	/**
	 * Create the application.
	 * Create new personal Friend
	 */
	public newPersonal(ArrayList<Acquaintance> contact_book,ArrayList<Relative> relative_book,ArrayList<Personal> personal_book
			,ArrayList<Professional> professional_book,ArrayList<Casual> casual_book,String filename) 
	{
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 350);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("New Personal Friend");
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		Personal newContact = new Personal();
		newContact.settype("personal");
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(71, 11, 81, 30);
		frame.getContentPane().add(lblName);

		
		JTextField textField = new JTextField();
		textField.setBounds(178, 11, 232, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(71, 56, 81, 30);
		frame.getContentPane().add(lblNumber);
		
		textField_1 = new JTextField(10);
		textField_1.setBounds(178, 56, 232, 30);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(71, 97, 81, 30);
		frame.getContentPane().add(lblEmail);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(178, 97, 232, 30);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		JLabel lblContext = new JLabel("Context when met");
		lblContext.setBounds(36, 138, 116, 30);
		frame.getContentPane().add(lblContext);
		
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(178, 138, 232, 30);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		
		JLabel lblAcquaintedOn = new JLabel("Acquainted on (date)");
		lblAcquaintedOn.setBounds(24, 179, 128, 30);
		frame.getContentPane().add(lblAcquaintedOn);

		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(178, 179, 232, 30);
		frame.getContentPane().add(dateChooser);

		JLabel lblSpecificEvents = new JLabel("Specific Events");
		lblSpecificEvents.setBounds(36, 219, 116, 30);
		frame.getContentPane().add(lblSpecificEvents);
		
		textField_5 = new JTextField();
		textField_5.setBounds(178, 219, 232, 30);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		
		Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton(back);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Container frame2 = btnBack.getParent();
	            do 
	                frame2 = frame2.getParent(); 
	            while (!(frame instanceof JFrame));                                      
	            ((JFrame) frame).dispose();
			}
		});
		btnBack.setBounds(36, 260, 43, 43);
		frame.getContentPane().add(btnBack);
		
		
		JButton btnSaveContact = new JButton("Save contact");
		btnSaveContact.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int flag = 1;
				if(textField.getText().isEmpty()||textField_1.getText().isEmpty()||textField_2.getText().isEmpty()||textField_3.getText().isEmpty()||textField_5.getText().isEmpty())
				{		
					flag = 0;
					JOptionPane.showMessageDialog(null,"All fields are mandatory !");
				}
				
				String temp = textField_1.getText();
				if((temp.length()<numLimit)||!(isNumeric(temp)))
				{
					flag=0;
					JOptionPane.showMessageDialog(null,"Enter atleast a "+numLimit+" digit number !");
				}
				
				Date date = new Date();
				String convention = "dd/MM/yyyy";
		        SimpleDateFormat sdf = new SimpleDateFormat(convention);
				String jolly = sdf.format(date);
		        try 
		        {
		        	date = dateChooser.getDate();
					jolly = sdf.format(date);
		             //date = sdf.parse(textField_3.getText());
		            
		        }
					 catch (NullPointerException npe)//ParseException ex) 
		        {
		            flag = 0;
					JOptionPane.showMessageDialog(null,"Choose a date! ");
		        }
		        
		        if(textField_3.getText().length()>100||textField_5.getText().length()>100)
		        {
		        	flag = 0;
					JOptionPane.showMessageDialog(null,"Max chars is 100 !");
		        }
		        
				if(!(textField.getText().isEmpty())&&!(textField_1.getText().isEmpty())&&!(textField_2.getText().isEmpty())&&!(textField_3.getText().isEmpty())&&!(textField_5.getText().isEmpty())&&(flag==1))
				{
					
					newContact.setname(textField.getText());
					newContact.setnumber(textField_1.getText());
					newContact.setmail(textField_2.getText());
					newContact.setcontext(textField_3.getText());
					newContact.setdate(jolly);
					newContact.setspecific(textField_5.getText());

					contact_book.add(newContact);
					personal_book.add(newContact);
					ReadData r = new ReadData(contact_book,relative_book,personal_book,professional_book,casual_book,filename);
					JOptionPane.showMessageDialog(null,"Contact saved for "+newContact.getname()+" !");
					 Container frame2 = btnSaveContact.getParent();
			            do 
			                frame2 = frame2.getParent(); 
			            while (!(frame instanceof JFrame));                                      
			            ((JFrame) frame).dispose();
				}
			}
		});
		btnSaveContact.setBounds(178, 260, 121, 30);
		frame.getContentPane().add(btnSaveContact);
		

	}
}
