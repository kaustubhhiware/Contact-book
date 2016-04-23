
import java.awt.Container;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class newProfessional extends JFrame implements  Serializable{

	private JFrame frame;
	private JTextField textField_1;
	static int numLimit = 4;
	String temp = " ";

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
	 * Create new Professional Friend
	 */
	public newProfessional(ArrayList<Acquaintance> contact_book,ArrayList<Relative> relative_book,ArrayList<Personal> personal_book
			,ArrayList<Professional> professional_book,ArrayList<Casual> casual_book,String filename) {		
		
		frame = new JFrame();
		frame.setTitle("New Professional Friend");
		frame.setBounds(100, 100, 450, 275);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		Professional newContact = new Professional();
		newContact.settype("professional");
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(71, 11, 81, 30);
		frame.getContentPane().add(lblName);

		JTextField textField = new JTextField();		
		textField.setBounds(178, 11, 232, 30);
		textField.setColumns(10);
		frame.getContentPane().add(textField);
		
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(71, 56, 81, 30);
		frame.getContentPane().add(lblNumber);

		textField_1 = new JTextField(10);
		textField_1.setColumns(10);
		textField_1.setBounds(178, 56, 232, 30);
		frame.getContentPane().add(textField_1);
			
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(71, 97, 81, 30);
		frame.getContentPane().add(lblEmail);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(178, 97, 232, 30);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		JLabel lblCommonInterests = new JLabel("Common Interests");
		lblCommonInterests.setBounds(24, 138, 128, 30);
		frame.getContentPane().add(lblCommonInterests);
		
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(178, 138, 232, 30);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		
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
		btnBack.setBounds(34, 179, 45, 45);
		frame.getContentPane().add(btnBack);
		
		JButton btnSaveContact = new JButton("Save contact");
		btnSaveContact.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int flag = 1;
				if(textField.getText().isEmpty()||textField_1.getText().isEmpty()||textField_2.getText().isEmpty()||textField_3.getText().isEmpty())
				{		
					flag = 0;
					JOptionPane.showMessageDialog(null,"All fields are mandatory !");
				}
				String temp = textField_1.getText();
				if((temp.length()<numLimit)||!(isNumeric(temp)))
				{
					flag=0;
					JOptionPane.showMessageDialog(null,"Enter atleast a  "+numLimit+" digit number !");
				}
				
		        if(textField_3.getText().length()>100)
		        {
		        	flag = 0;
					JOptionPane.showMessageDialog(null,"Max chars is 100 !");
		        }
		        
				if(!(textField.getText().isEmpty())&&!(textField_1.getText().isEmpty())&&!(textField_2.getText().isEmpty())&&!(textField_3.getText().isEmpty())&&(flag==1))
				{
					
					newContact.setname(textField.getText());
					newContact.setnumber(textField_1.getText());
					newContact.setmail(textField_2.getText());
					newContact.setinterest(textField_3.getText());
					
					contact_book.add(newContact);
					professional_book.add(newContact);
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
		btnSaveContact.setBounds(178, 186, 121, 30);
		frame.getContentPane().add(btnSaveContact);
		
	}
}
