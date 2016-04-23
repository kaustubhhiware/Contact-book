
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.io.*;
import java.io.*;  

public class visual  implements  Serializable{

	private JFrame frame;
    static ArrayList<Acquaintance> contact_book = new ArrayList<Acquaintance>();
    static ArrayList<Relative> relative_book = new ArrayList<Relative>();
    static ArrayList<Personal> personal_book = new ArrayList<Personal>();
    static ArrayList<Professional> professional_book = new ArrayList<Professional>();
    static ArrayList<Casual> casual_book = new ArrayList<Casual>();

    String filename = "Phone.dat";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visual window = new visual();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * This is the main file . Offers GUI for all basic operations
	 * @return 
	 * @throws ClassNotFoundException 
	 */
	public visual(){
		try {
			initialize();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException {
		
		int size = 0;
		try   {
            FileInputStream infile = new FileInputStream(filename);
            ObjectInputStream outfile = new ObjectInputStream(infile);
            
            //additional parameter size used to differentiate between different arraylist
            contact_book = (ArrayList<Acquaintance>) outfile.readObject();
            size = (int) outfile.readInt();
            relative_book = (ArrayList<Relative>) outfile.readObject();
            size = (int) outfile.readInt();
            personal_book = (ArrayList<Personal>) outfile.readObject();
            size = (int) outfile.readInt();
            professional_book = (ArrayList<Professional>) outfile.readObject();
            size = (int) outfile.readInt();
            casual_book = (ArrayList<Casual>) outfile.readObject();
            size = (int) outfile.readInt();

			infile.close();
			outfile.close();
		}
        catch (FileNotFoundException e) 
		{
           // System.err.println("File not found");
            try{
                File f = new File("Contacts.dat");
                
                boolean bool = false;
                bool = f.createNewFile();
                while(bool==false)
                {
                	f.delete();
                	bool = f.createNewFile();
                }
             }catch(Exception ex){
                e.printStackTrace();
             }
        }  
        catch (IOException e)
		{ 
        //	System.err.println("Read failed"); 
        }

	
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Welcome to Contact Book !");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
				//JOptionPane.showMessageDialog(null,String.format("%s",event.getActionCommand()))
				createContact cc = new createContact(contact_book,relative_book,personal_book,professional_book,casual_book,filename);
			}
		});
		btnNewButton.setBounds(20, 26, 77, 66);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
					displayContacts dCx = new displayContacts(contact_book,relative_book,personal_book,professional_book,casual_book,1,filename);//0 for un-delete-able ;

			}
		});
		btnNewButton_1.setBounds(339, 26, 85, 66);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("All contacts");
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				displayContacts dC = new displayContacts(contact_book,relative_book,personal_book,professional_book,casual_book,0,filename);
			}
		});
		btnNewButton_2.setBounds(107, 26, 106, 66);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("View groups");
		btnNewButton_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				displayType dT = new displayType(contact_book,relative_book,personal_book,professional_book,casual_book,filename);
			}
		});
		btnNewButton_3.setBounds(223, 26, 106, 66);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Search");
		btnNewButton_4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Nemo ="   ";// JOptionPane.showInputDialog("Enter name of contact to be searched");
				//JOptionPane.showMessageDialog(null, Nemo+" visual");
				searchContact find = new searchContact(contact_book,relative_book,personal_book,professional_book,casual_book,"");
			}
		});
		btnNewButton_4.setBounds(20, 103, 404, 39);
		frame.getContentPane().add(btnNewButton_4);
		
		Icon default_image = new ImageIcon(getClass().getResource("contact.png"));
		JButton btnNewButton_5 = new JButton("image",default_image);
		btnNewButton_5.setBounds(121, 153, 159, 93);
		btnNewButton_5.setBorderPainted( false );
		btnNewButton_5.setFocusPainted( false );

		frame.getContentPane().add(btnNewButton_5);
	}
}
