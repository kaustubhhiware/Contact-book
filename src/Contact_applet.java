
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Contact_applet extends JApplet   implements  Serializable{

	static ArrayList<Acquaintance> contact_book = new ArrayList<Acquaintance>();
	static int numLimit = 4;
    String filename = "Contacts.dat";

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
    
    
	public Contact_applet() throws InvocationTargetException{
		
		
	/*	try   {//Even though I have added file reading and saving , it is unlikely to work as browser(applet) can not access files on computer
            FileInputStream infile = new FileInputStream(filename);
            ObjectInputStream outfile = new ObjectInputStream(infile);
            
            try {
				contact_book = (ArrayList<Acquaintance>) outfile.readObject();
			} catch (ClassNotFoundException e1) {
				//e1.printStackTrace();
			}

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
		*/
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 300);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
				CreateContact(contact_book,panel);
			}
		});
		btnNewButton.setBounds(20, 26, 77, 66);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
					displayContacts(contact_book,1,panel);
			}
		});
		btnNewButton_1.setBounds(339, 26, 85, 66);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("All contacts");
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				displayContacts(contact_book,0,panel);
			}
		});
		btnNewButton_2.setBounds(107, 26, 106, 66);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("View groups");
		btnNewButton_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				displayType(contact_book,panel);
			}
		});
		btnNewButton_3.setBounds(223, 26, 106, 66);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Search");
		btnNewButton_4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Nemo ="   ";
				
				searchContact(contact_book,"",panel);
			}
		});
		btnNewButton_4.setBounds(20, 103, 404, 39);
		panel.add(btnNewButton_4);
		
		/*Icon default_image = new ImageIcon(getClass().getResource("contact.png"));
		JButton btnNewButton_5 = new JButton("image");//,default_image);
		btnNewButton_5.setBounds(121, 153, 159, 93);
		btnNewButton_5.setBorderPainted( false );
		btnNewButton_5.setFocusPainted( false );

		panel.add(btnNewButton_5);
*/
		panel.setVisible(true);
	}

	
public void displayContacts(ArrayList<Acquaintance> contact_book,int canDelete,JPanel panel) 
{
		panel.setVisible(false);
		JPanel paneldCx = new JPanel();
		paneldCx.setBounds(0, 0, 450, 300);
		getContentPane().add(paneldCx);
		paneldCx.setLayout(null);
		JLabel lblName;
		JLabel lblType;
		JLabel lblNumber;
		JButton btnView;
					
		String[] columns = {"Name", "Number", "Type"};
		int size = contact_book.size();
		Object[][] contact_object  = new String[size][3];
					
		int i;
		for(i = 0; i < size ; i++)
		{
			contact_object[i][0] = contact_book.get(i).getname();
			contact_object[i][1] = contact_book.get(i).getnumber();
			contact_object[i][2] = contact_book.get(i).gettype();
		}
					
					
		//Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton("<-");//,back);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				paneldCx.setVisible(false);
				panel.setVisible(true);
			}
		});
		btnBack.setBounds(20, 201,60, 60);
					
		final JTable table = new JTable(contact_object,columns);
		table.setBounds(10, 41, 414, 149);
					
		JScrollPane scrollPane = new JScrollPane(table);
		paneldCx.add(btnBack);
		paneldCx.add(scrollPane);
					
		lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 91, 19);
		paneldCx.add(lblName);
					
		lblType = new JLabel("Type");
		lblType.setBounds(354, 11, 70, 19);
		paneldCx.add(lblType);
					
		lblNumber = new JLabel("Number");
		lblNumber.setBounds(166, 11, 70, 19);
		paneldCx.add(lblNumber);


		btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(table.getSelectedRow()==-1)
				{
					JOptionPane.showMessageDialog(null, "Select a contact first!");
					//displayMessage("Select a contact first!",paneldCx);
					//displayMessage(null, "Select a contact first!");
				}
				else
				{
					viewContact(contact_book.get(table.getSelectedRow()),paneldCx); 
				}
						
			}
		});
		btnView.setBounds(170, 200, 90, 40);
		paneldCx.add(btnView);	
		if(canDelete==1)
		{
			JButton btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{	
							
					if(table.getSelectedRow()==-1)
					{						
						JOptionPane.showMessageDialog(null, "Select a contact first!");
						//displayMessage("Select a contact first!",paneldCx);
					}
					else
					{
						Acquaintance removed = contact_book.remove(table.getSelectedRow());
					//	ReadData(contact_book,filename);
						JOptionPane.showMessageDialog(null, "Contact " + removed.getname() + " has been removed");
						//displayMessage("Contact " + removed.getname() + " has been removed",paneldCx);
						paneldCx.setVisible(false);
						panel.setVisible(true);
					}
				}
			});
			btnDelete.setBounds(340, 200, 80, 40);
			paneldCx.add(btnDelete);
		}		
			paneldCx.add(table);	
			paneldCx.setVisible(true);
			}
	
	
	public void displayType(ArrayList<Acquaintance> contact_book,JPanel panel)
	{
		
		panel.setVisible(false);
		JPanel paneldT = new JPanel();
		paneldT.setBounds(0, 0, 450, 300);
		getContentPane().add(paneldT);
		paneldT.setLayout(null);
		
	/*	Icon group = new ImageIcon(getClass().getResource("group.png"));
		JButton btnGroup = new JButton(group);
		btnGroup.setBorderPainted( false );
		btnGroup.setFocusPainted( false );
		btnGroup.setBounds(44, 45, 151, 153);
		paneldT.add(btnGroup);
*/
		
		JLabel lblSelectAGroup = new JLabel("Select a group");
		lblSelectAGroup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectAGroup.setBounds(253, 23, 119, 35);
		paneldT.add(lblSelectAGroup);
		
		JButton btnRelative = new JButton("Relative");
		btnRelative.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				displayThis(contact_book,"relative",paneldT);
			}
		});
		btnRelative.setBounds(253, 69, 171, 33);
		paneldT.add(btnRelative);
		
		JButton btnPersonalFriend = new JButton("Personal Friend");
		btnPersonalFriend.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
				displayThis(contact_book,"personal",paneldT);
			}
		});
		
		btnPersonalFriend.setBounds(253, 106, 171, 33);
		paneldT.add(btnPersonalFriend);
		
		JButton btnProfessionalFriend = new JButton("Professional Friend");
		btnProfessionalFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				displayThis(contact_book,"professional",paneldT);
			}
		});
		btnProfessionalFriend.setBounds(253, 145, 171, 33);
		paneldT.add(btnProfessionalFriend);
		
		JButton btnCasualAcquaintance = new JButton("Casual Acquaintance");
		btnCasualAcquaintance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				displayThis(contact_book,"casual",paneldT);
			}
		});
		btnCasualAcquaintance.setBounds(253, 186, 171, 33);
		paneldT.add(btnCasualAcquaintance);		
		
		//Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton("<-");//back);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				paneldT.setVisible(false);
				panel.setVisible(true);
			}
		});
		btnBack.setBounds(103, 209, 60, 60);
		paneldT.add(btnBack);
		paneldT.setVisible(true);
		
	}

	public void displayThis(ArrayList<Acquaintance> contact_book,String thisType,JPanel paneldT)
	{

		String[] columns = {"Name", "Number", "E-mail"};
		int typeSize = 0;
		int i;
		for(i = 0; i < contact_book.size() ; i++)
		{
			if(contact_book.get(i).gettype()==thisType)
			{
				typeSize++;
			}
		}
		
		Object[][] contact_object  = new String[typeSize][3];
		int j =0;
		for(i = 0; i < contact_book.size(); i++)
		{
			if(contact_book.get(i).gettype()==thisType)
			{
				contact_object[j][0] = contact_book.get(i).getname();
				contact_object[j][1] = contact_book.get(i).getnumber();
				contact_object[j][2] = contact_book.get(i).getmail();
				j++;
			} 
		}
		paneldT.setVisible(false);
		JPanel paneldTT = new JPanel();
		paneldTT.setBounds(0, 0, 450, 300);
		getContentPane().add(paneldTT);
		paneldTT.setLayout(null);	
		
		//Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton("<-");//back);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				paneldTT.setVisible(false);
				paneldT.setVisible(true);
			}
		});
		btnBack.setBounds(10, 209, 60, 60);
		paneldTT.add(btnBack);	

		JTable table = new JTable(contact_object,columns);
		table.setBounds(10, 41, 414, 149);
		
		JScrollPane scrollPane = new JScrollPane(table);
		paneldTT.add(scrollPane);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 91, 19);
		paneldTT.add(lblName);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(175, 11, 70, 19);
		paneldTT.add(lblNumber);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(354, 11, 70, 19);
		paneldTT.add(lblEmail);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int checkOut = table.getSelectedRow();
				if(checkOut==-1)
				{
					JOptionPane.showMessageDialog(null, "Select a contact first!");
					//displayMessage("Select a contact first!",paneldTT);
				}
				else
				{
					viewContact(contact_book.get(checkOut),paneldTT); 
				}
			
			}
		});
		btnView.setBounds(160, 200, 100, 50);
		paneldTT.add(btnView);
		
		paneldTT.add(table);
		paneldTT.setVisible(true);

	}

	public void viewContact(Acquaintance savedContact,JPanel parentPanel)
	{
		
		parentPanel.setVisible(false);
		JPanel panelvC = new JPanel();
		panelvC.setBounds(0, 0, 450, 300);
		getContentPane().add(panelvC);
		panelvC.setLayout(null);
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(71, 11, 81, 30);
		panelvC.add(lblName);
		
		JTextField textField = new JTextField(savedContact.getname());
		textField.setBounds(178, 11, 232, 30);
		panelvC.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(71, 56, 81, 30);
		panelvC.add(lblNumber);
		
		JTextField textField_1 = new JTextField(savedContact.getnumber());
		textField_1.setBounds(178, 56, 232, 30);
		panelvC.add(textField_1);
		textField_1.setColumns(10);
		
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(71, 97, 81, 30);
		panelvC.add(lblEmail);
		
		JTextField textField_2 = new JTextField(savedContact.getmail());
		textField_2.setBounds(178, 97, 232, 30);
		panelvC.add(textField_2);
		textField_2.setColumns(10);
		

		
		//Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton("<-");//back);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				panelvC.setVisible(false);
				parentPanel.setVisible(true);
			}
		});
		btnBack.setBounds(30, 140, 60, 60);
		panelvC.add(btnBack);
	}
	
	public void searchContact(ArrayList<Acquaintance> contact_book,String Nemo,JPanel panel) 
	{
		
		String[] columns = {"Name", "Number", "Type"};
		int foundSize = 0;
		int i;
		for(i = 0; i < contact_book.size() ; i++)
		{
			if(contact_book.get(i).getname().contains(Nemo.toLowerCase()))
			{
				foundSize++;
			}
		}
		if(foundSize==0)
		{
			//JOptionPane.showMessageDialog(null,"No match found !" );
			//displayMessage(null,"No match found !");
			//return;
		}
			
		Object[][] contact_object  = new String[foundSize][3];
		int j =0;
		
		for(i = 0; i < contact_book.size(); i++)
		{
			if (contact_book.get(i).getname().contains(Nemo.toLowerCase())) 
            { 
                Acquaintance found = contact_book.get(i);
                //displayMessage(null,"Contact " + found.getname() + " has been found");
                
                contact_object[j][0] = found.getname();
                contact_object[j][1] = found.getnumber();
                contact_object[j][2] = found.gettype();
                j++;
                //writeContact(file);
            }
        }

		panel.setVisible(false);
		JPanel panelSearch = new JPanel();
		panelSearch.setBounds(0, 0, 450, 300);
		getContentPane().add(panelSearch);
		panelSearch.setLayout(null);

		JTextField textField = new JTextField();
		textField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Dory = textField.getText();
				panelSearch.setVisible(false);
				searchContact(contact_book,Dory,panel);
			}
		});
		textField.setBounds(10, 11, 415, 41);
		panelSearch.add(textField);
		textField.setColumns(10);
	
		
		
		//Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton("<");//back);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				panelSearch.setVisible(false);
				panel.setVisible(true);
			}
		});
		btnBack.setBounds(10, 259, 41, 41);
		panelSearch.add(btnBack);	
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 70, 100, 20);
		panelSearch.add(lblName);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(175, 70, 70, 20);
		panelSearch.add(lblNumber);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(355, 70, 70, 20);
		panelSearch.add(lblEmail);

		
		JTable table = new JTable(contact_object,columns);
		table.setBounds(10, 99, 414, 149);
		
		JScrollPane scrollPane = new JScrollPane(table);
		panelSearch.add(btnBack);
		panelSearch.add(scrollPane);
		panelSearch.add(table);
		panelSearch.setVisible(true);
		
	}
	
	public void CreateContact(ArrayList<Acquaintance> contact_book,JPanel panel)
	{
		panel.setVisible(false);
		JPanel panelCC = new JPanel();
		panelCC.setBounds(0, 0, 450, 300);
		getContentPane().add(panelCC);
		panelCC.setLayout(null);
		
		JLabel lblWhatTypeOf = new JLabel("What type of contact do you wish to make ?");
		lblWhatTypeOf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWhatTypeOf.setBounds(33, 31, 325, 30);
		panelCC.add(lblWhatTypeOf);
		
		
		JButton btnRelative = new JButton("Relative");
		btnRelative.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
				newRelative(contact_book,panelCC);
			}
		});
		btnRelative.setBounds(33, 65, 157, 36);
		panelCC.add(btnRelative);

		
		JButton btnPersonalFriend = new JButton("Personal friend");
		btnPersonalFriend.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				newPersonal(contact_book,panelCC);
			}
		});
		btnPersonalFriend.setBounds(33, 114, 157, 36);
		panelCC.add(btnPersonalFriend);
		

		JButton btnProfessionalFriend = new JButton("Professional friend");
		btnProfessionalFriend.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				newProfessional(contact_book,panelCC);
			}
		});
		btnProfessionalFriend.setBounds(33, 161, 157, 36);
		panelCC.add(btnProfessionalFriend);
		
		
		//	Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton("<-");//back);
		btnBack.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent arg1)
				{
					panelCC.setVisible(false);
					panel.setVisible(true);
				}
		});
		btnBack.setBounds(295, 205, 60, 60);
		panelCC.add(btnBack);		
		
		
		JButton btnCasualAcquaintance = new JButton("Casual Acquaintance");
		btnCasualAcquaintance.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				newCasual(contact_book,panelCC);
			}	
		});
		btnCasualAcquaintance.setBounds(33, 208, 157, 36);
		panelCC.add(btnCasualAcquaintance);
		
	/*	
		Icon new_contact = new ImageIcon(getClass().getResource("new_contact.png"));
		JButton btnNewButton = new JButton("New contact",new_contact);
		btnNewButton.setBounds(247, 72, 136, 109);
		btnNewButton.setBorderPainted( false );
		btnNewButton.setFocusPainted( false );

		panelCC.add(btnNewButton);
*/

		panelCC.setVisible(true);
	
	}
	
	
	public void newProfessional(ArrayList<Acquaintance> contact_book,JPanel panelCC)
	{
		panelCC.setVisible(false);
		JPanel panelNewProfessional = new JPanel();
		panelNewProfessional.setBounds(0, 0, 450, 300);
		getContentPane().add(panelNewProfessional);
		panelNewProfessional.setLayout(null);
		
		Professional newContact = new Professional();
		newContact.settype("professional");
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(71, 11, 81, 30);
		panelNewProfessional.add(lblName);

		JTextField textField = new JTextField();		
		textField.setBounds(178, 11, 232, 30);
		textField.setColumns(10);
		panelNewProfessional.add(textField);
		
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(71, 56, 81, 30);
		panelNewProfessional.add(lblNumber);

		JTextField textField_1 = new JTextField(10);
		textField_1.setColumns(10);
		textField_1.setBounds(178, 56, 232, 30);
		panelNewProfessional.add(textField_1);
			
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(71, 97, 81, 30);
		panelNewProfessional.add(lblEmail);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(178, 97, 232, 30);
		panelNewProfessional.add(textField_2);
		textField_2.setColumns(10);
		
		
		JLabel lblCommonInterests = new JLabel("Common Interests");
		lblCommonInterests.setBounds(24, 138, 128, 30);
		panelNewProfessional.add(lblCommonInterests);
		
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(178, 138, 232, 30);
		panelNewProfessional.add(textField_3);
		textField_3.setColumns(10);

		
		//Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton("<-");//back);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				panelNewProfessional.setVisible(false);
				panelCC.setVisible(true);
			}
		});
		btnBack.setBounds(34, 179, 45, 45);
		panelNewProfessional.add(btnBack);
		
		JButton btnSaveContact = new JButton("Save contact");
		btnSaveContact.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int flag = 1;
				if(textField.getText().isEmpty()||textField_1.getText().isEmpty()||textField_2.getText().isEmpty()||textField_3.getText().isEmpty())
				{		
					flag = 0;
					JOptionPane.showMessageDialog(null, "All fields are mandatory !");
					//displayMessage("All fields are mandatory !",panelNewProfessional);

				}
				String temp = textField_1.getText();
				if((temp.length()<numLimit)||!(isNumeric(temp)))
				{
					flag=0;
					JOptionPane.showMessageDialog(null, "Enter atleast a  "+numLimit+" digit number !");
				//	displayMessage("Enter atleast a  "+numLimit+" digit number !",panelNewProfessional);

				}
				
		        if(textField_3.getText().length()>100)
		        {
		        	flag = 0;
					JOptionPane.showMessageDialog(null, "Max chars is 100 !");
					//displayMessage("Max chars is 100 !",panelNewProfessional);
		        }
		        
				if(!(textField.getText().isEmpty())&&!(textField_1.getText().isEmpty())&&!(textField_2.getText().isEmpty())&&!(textField_3.getText().isEmpty())&&(flag==1))
				{
					
					newContact.setname(textField.getText());
					newContact.setnumber(textField_1.getText());
					newContact.setmail(textField_2.getText());
					newContact.setinterest(textField_3.getText());
					
					contact_book.add(newContact);
				//	ReadData(contact_book,filename);
					JOptionPane.showMessageDialog(null, "Contact saved for "+newContact.getname()+" !");
					//displayMessage("Contact saved for "+newContact.getname()+" !",panelNewProfessional);
					panelNewProfessional.setVisible(false);
					panelCC.setVisible(true);
				}
				

			}
		});
		btnSaveContact.setBounds(178, 186, 121, 30);
		panelNewProfessional.add(btnSaveContact);
		panelNewProfessional.setVisible(true);
	
	}
	
	public void newRelative(ArrayList<Acquaintance> contact_book,JPanel panelCC)
	{
		panelCC.setVisible(false);
		JPanel panelNewRelative = new JPanel();
		panelNewRelative.setBounds(0, 0, 450, 300);
		getContentPane().add(panelNewRelative);
		panelNewRelative.setLayout(null);
		
		Relative newContact = new Relative();
		newContact.settype("relative");
		
		JTextField textField;
		JTextField textField_1;
		JTextField textField_2;
		JTextField textField_3;
		JTextField textField_4;
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(71, 11, 81, 30);
		panelNewRelative.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(178, 11, 232, 30);
		panelNewRelative.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(71, 56, 81, 30);
		panelNewRelative.add(lblNumber);
		
		textField_1 = new JTextField(10);
		textField_1.setBounds(178, 56, 232, 30);
		panelNewRelative.add(textField_1);
		textField_1.setColumns(10);
		
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(71, 97, 81, 30);
		panelNewRelative.add(lblEmail);
		
		textField_2 = new JTextField();
		textField_2.setBounds(178, 97, 232, 30);
		panelNewRelative.add(textField_2);
		textField_2.setColumns(10);
		
		
		JLabel lblBirthdate = new JLabel("Birthdate");
		lblBirthdate.setBounds(71, 138, 81, 30);
		panelNewRelative.add(lblBirthdate);
		
		textField_3 = new JTextField();
		textField_3.setBounds(178, 138, 232, 30);
		panelNewRelative.add(textField_3);
		textField_3.setColumns(10);
		
		
		JLabel lblLastMetOn = new JLabel("Last met on");
		lblLastMetOn.setBounds(71, 179, 81, 30);
		panelNewRelative.add(lblLastMetOn);
		
		textField_4 = new JTextField();
		textField_4.setBounds(178, 179, 232, 30);
		panelNewRelative.add(textField_4);
		textField_4.setColumns(10);
		
		//Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton("<-");//back);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				panelNewRelative.setVisible(false);
				panelCC.setVisible(true);
			}
		});
		btnBack.setBounds(24, 207, 60, 60);
		panelNewRelative.add(btnBack);
		
		JButton btnSaveContact = new JButton("Save contact");
		btnSaveContact.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int flag = 1;
				if(textField.getText().isEmpty()||textField_1.getText().isEmpty()||textField_2.getText().isEmpty()||textField_3.getText().isEmpty()||textField_4.getText().isEmpty())
				{		
					flag = 0;
					JOptionPane.showMessageDialog(null, "All fields are mandatory !");
					//	panelNewRelative.setVisible(false);
					//displayMessage("All fields are mandatory !",panelNewRelative);
				}
				String temp = textField_1.getText();
				if((temp.length()<numLimit)||!(isNumeric(temp)))
				{
					flag=0;		
					JOptionPane.showMessageDialog(null, "Enter atleast a "+numLimit+" digit number !");
					//panelNewRelative.setVisible(false);
					//displayMessage("Enter atleast a "+numLimit+" digit number !",panelNewRelative);
				}
				
				Date date = new Date();
				String convention = "dd/MM/yyyy";
		        SimpleDateFormat sdf = new SimpleDateFormat(convention);
		        try 
		        {
		             date = sdf.parse(textField_3.getText());
		            
		        } catch (ParseException ex) 
		        {
		            flag = 0;
					JOptionPane.showMessageDialog(null, "Invalid Date Format !");
					//panelNewRelative.setVisible(false);
					//displayMessage("Invalid Date Format !",panelNewRelative);
		        }
		        
		        try 
		        {
		             date = sdf.parse(textField_4.getText());
		            
		        } catch (ParseException ex) 
		        {
		            flag = 0;
					JOptionPane.showMessageDialog(null, "Invalid Date Format !");
					//panelNewRelative.setVisible(false);
					//displayMessage("Invalid Date Format !",panelNewRelative);
		        }

				if(!(textField.getText().isEmpty())&&!(textField_1.getText().isEmpty())&&!(textField_2.getText().isEmpty())&&!(textField_3.getText().isEmpty())&&!(textField_4.getText().isEmpty())&&(flag==1))
				{/*
					 *All storage is done here as it is more convenient.
					 *Since input is taken through TextField , the user will have to press enter every time an entry is made.
					 *This way ,  it is more convenient.
					 */
					
					newContact.setname(textField.getText());
					newContact.setnumber(textField_1.getText());
					newContact.setmail(textField_2.getText());
					newContact.setbday(textField_3.getText());
					newContact.setlastmeet(textField_4.getText());

					contact_book.add(newContact);
					//ReadData(contact_book,filename);
					JOptionPane.showMessageDialog(null, "Contact saved for "+newContact.getname()+" !");

				//	panelNewRelative.setVisible(false);
					//displayMessage("Contact saved for "+newContact.getname()+" !",panelNewRelative);
					panelNewRelative.setVisible(false);
					panelCC.setVisible(true);
				}
				
			}
		});
		btnSaveContact.setBounds(178, 220, 121, 30);
		panelNewRelative.add(btnSaveContact);
		panelNewRelative.setVisible(true);
		
	}
	
	
	public void newPersonal(ArrayList<Acquaintance> contact_book,JPanel panelCC)
	{
		panelCC.setVisible(false);
		JPanel panelNewPersonal = new JPanel();
		panelNewPersonal.setBounds(0, 0, 450, 300);
		getContentPane().add(panelNewPersonal);
		panelNewPersonal.setLayout(null);
		
		Personal newContact = new Personal();
		newContact.settype("personal");
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(71, 11, 81, 30);
		panelNewPersonal.add(lblName);

		
		JTextField textField = new JTextField();
		textField.setBounds(178, 11, 232, 30);
		panelNewPersonal.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(71, 56, 81, 30);
		panelNewPersonal.add(lblNumber);
		
		JTextField textField_1 = new JTextField(10);
		textField_1.setBounds(178, 56, 232, 30);
		panelNewPersonal.add(textField_1);
		textField_1.setColumns(10);
		
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(71, 97, 81, 30);
		panelNewPersonal.add(lblEmail);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(178, 97, 232, 30);
		panelNewPersonal.add(textField_2);
		textField_2.setColumns(10);
		
		
		JLabel lblContext = new JLabel("Context when met");
		lblContext.setBounds(36, 138, 116, 30);
		panelNewPersonal.add(lblContext);
		
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(178, 138, 232, 30);
		panelNewPersonal.add(textField_3);
		textField_3.setColumns(10);

		
		JLabel lblAcquaintedOn = new JLabel("Acquainted on (date)");
		lblAcquaintedOn.setBounds(24, 179, 128, 30);
		panelNewPersonal.add(lblAcquaintedOn);
		
		JTextField textField_4 = new JTextField();
		textField_4.setBounds(178, 179, 232, 30);
		panelNewPersonal.add(textField_4);
		textField_4.setColumns(10);


		JLabel lblSpecificEvents = new JLabel("Specific Events");
		lblSpecificEvents.setBounds(36, 219, 116, 30);
		panelNewPersonal.add(lblSpecificEvents);
		
		JTextField textField_5 = new JTextField();
		textField_5.setBounds(178, 219, 232, 30);
		panelNewPersonal.add(textField_5);
		textField_5.setColumns(10);
		
		
		//Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton("<-");//back);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				panelNewPersonal.setVisible(false);
				panelCC.setVisible(true);
			}
		});
		btnBack.setBounds(36, 260, 60, 60);
		panelNewPersonal.add(btnBack);
		
		
		JButton btnSaveContact = new JButton("Save contact");
		btnSaveContact.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int flag = 1;
				if(textField.getText().isEmpty()||textField_1.getText().isEmpty()||textField_2.getText().isEmpty()||textField_3.getText().isEmpty()||textField_4.getText().isEmpty()||textField_5.getText().isEmpty())
				{		
					flag = 0;
					JOptionPane.showMessageDialog(null, "All fields are mandatory !");
				//	displayMessage("All fields are mandatory !",panelNewPersonal);
				}
				
				String temp = textField_1.getText();
				if((temp.length()<numLimit)||!(isNumeric(temp)))
				{
					flag=0;
					JOptionPane.showMessageDialog(null, "Enter atleast a "+numLimit+" digit number !");
					//displayMessage("Enter atleast a "+numLimit+" digit number !",panelNewPersonal);
				}
				
				Date date = new Date();
				String convention = "dd/MM/yyyy";
		        SimpleDateFormat sdf = new SimpleDateFormat(convention);
		        try 
		        {
		             date = sdf.parse(textField_4.getText());
		            
		        } catch (ParseException ex) 
		        {
		            flag = 0;
					JOptionPane.showMessageDialog(null, "Invalid Date Format !");
					//displayMessage("Invalid Date Format !",panelNewPersonal);
		        }
		        
		        if(textField_3.getText().length()>100||textField_5.getText().length()>100)
		        {
		        	flag = 0;
					JOptionPane.showMessageDialog(null, "Max chars is 100 !");
					//displayMessage("Max chars is 100 !",panelNewPersonal);
		        }
		        
				if(!(textField.getText().isEmpty())&&!(textField_1.getText().isEmpty())&&!(textField_2.getText().isEmpty())&&!(textField_3.getText().isEmpty())&&!(textField_4.getText().isEmpty())&&!(textField_5.getText().isEmpty())&&(flag==1))
				{
					
					newContact.setname(textField.getText());
					newContact.setnumber(textField_1.getText());
					newContact.setmail(textField_2.getText());
					newContact.setcontext(textField_3.getText());
					newContact.setdate(textField_4.getText());
					newContact.setspecific(textField_5.getText());

					contact_book.add(newContact);
					//ReadData(contact_book,filename);
					JOptionPane.showMessageDialog(null, "Contact saved for "+newContact.getname()+" !");

					//displayMessage("Contact saved for "+newContact.getname()+" !",panelNewPersonal);
					panelNewPersonal.setVisible(false);
					panelCC.setVisible(true);
				}
			}
		});
		btnSaveContact.setBounds(178, 260, 121, 30);
		panelNewPersonal.add(btnSaveContact);
		panelNewPersonal.setVisible(true);
	
	}
	
	public void newCasual(ArrayList<Acquaintance> contact_book,JPanel panelCC)
	{
		panelCC.setVisible(false);
		JPanel panelNewCasual= new JPanel();
		panelNewCasual.setBounds(0, 0, 450, 400);
		getContentPane().add(panelNewCasual);
		panelNewCasual.setLayout(null);
		
		
		Casual newContact = new Casual(); 
		newContact.settype("casual");
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(71, 11, 81, 30);
		panelNewCasual.add(lblName);
		
		JTextField textField = new JTextField();
		textField.setBounds(178, 11, 232, 30);
		panelNewCasual.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(71, 56, 81, 30);
		panelNewCasual.add(lblNumber);
		
		JTextField textField_1 = new JTextField(10);
		textField_1.setBounds(178, 56, 232, 30);
		panelNewCasual.add(textField_1);
		textField_1.setColumns(10);
		
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(71, 97, 81, 30);
		panelNewCasual.add(lblEmail);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(178, 97, 232, 30);
		panelNewCasual.add(textField_2);
		textField_2.setColumns(10);
		
		
		JLabel lblDateWhenMet = new JLabel("Date when met");
		lblDateWhenMet.setBounds(36, 138, 116, 30);
		panelNewCasual.add(lblDateWhenMet);
		
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(178, 138, 232, 30);
		panelNewCasual.add(textField_3);
		textField_3.setColumns(10);
		
		
		JLabel lblPlaceWhereMet = new JLabel("Place where met");
		lblPlaceWhereMet.setBounds(36, 179, 116, 30);
		panelNewCasual.add(lblPlaceWhereMet);
		
		JTextField textField_4 = new JTextField();
		textField_4.setBounds(178, 179, 232, 30);
		panelNewCasual.add(textField_4);
		textField_4.setColumns(10);

		
		JLabel lblCircumstancesWhenMet = new JLabel("Circumstances when met");
		lblCircumstancesWhenMet.setBounds(10, 219, 158, 30);
		panelNewCasual.add(lblCircumstancesWhenMet);
		
		JTextField textField_5 = new JTextField();
		textField_5.setBounds(178, 219, 232, 30);
		panelNewCasual.add(textField_5);
		textField_5.setColumns(10);

		
		JLabel lblOtherInformation = new JLabel("Other information");
		lblOtherInformation.setBounds(20, 260, 132, 30);
		panelNewCasual.add(lblOtherInformation);
		
		JTextField textField_6 = new JTextField();
		textField_6.setBounds(178, 260, 232, 30);
		panelNewCasual.add(textField_6);
		textField_6.setColumns(10);
		
	//	Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton("<-");//back);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				panelNewCasual.setVisible(false);
				panelCC.setVisible(true);
			}
		});
		btnBack.setBounds(36, 301, 60, 60);
		panelNewCasual.add(btnBack);
		
		JButton btnSaveContact = new JButton("Save contact");
		btnSaveContact.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				int flag = 1;
				if(textField.getText().isEmpty()||textField_1.getText().isEmpty()||textField_2.getText().isEmpty()||textField_3.getText().isEmpty()||textField_4.getText().isEmpty()||textField_5.getText().isEmpty()||textField_6.getText().isEmpty())
				{	flag = 0;
					JOptionPane.showMessageDialog(null, "All fields are mandatory !");
					//displayMessage("All fields are mandatory !",panelNewCasual);
				}
				
				
				String temp = textField_1.getText();
				if((temp.length()<numLimit)||!(isNumeric(temp)))
				{
					flag=0;
					JOptionPane.showMessageDialog(null, "Enter atleast a "+numLimit+" digit number !");
					//displayMessage("Enter atleast a  "+numLimit+" digit number !",panelNewCasual);
				}
				
				Date date = new Date();
				String convention = "dd/MM/yyyy";
		        SimpleDateFormat sdf = new SimpleDateFormat(convention);
		        try 
		        {
		             date = sdf.parse(textField_3.getText());
		            
		        } catch (ParseException ex) 
		        {
		            flag = 0;
					JOptionPane.showMessageDialog(null, "Invalid Date Format !");
					//displayMessage("Invalid Date Format !",panelNewCasual);
		        }
		        
		        if(textField_4.getText().length()>100||textField_5.getText().length()>100||textField_6.getText().length()>100)
		        {
		        	flag = 0;
					JOptionPane.showMessageDialog(null, "Max chars is 100 !");
					//displayMessage("Max chars is 100 !",panelNewCasual);
		        }
		        
				if(!(textField.getText().isEmpty())&&!(textField_1.getText().isEmpty())&&!(textField_2.getText().isEmpty())&&!(textField_3.getText().isEmpty())&&!(textField_4.getText().isEmpty())&&!(textField_5.getText().isEmpty())&&!(textField_6.getText().isEmpty())&&(flag==1))
				{
					
					newContact.setname(textField.getText());
					newContact.setnumber(textField_1.getText());
					newContact.setmail(textField_2.getText());
					newContact.setwhen(textField_3.getText());
					newContact.setwhere(textField_4.getText());
					newContact.setcircumstance(textField_5.getText());
					newContact.setotherInfo(textField_6.getText());

					contact_book.add(newContact);
					//ReadData(contact_book,filename);
					JOptionPane.showMessageDialog(null, "Contact saved for "+newContact.getname()+" !");

					//displayMessage("Contact saved for "+newContact.getname()+" !",panelNewCasual);
					 panelNewCasual.setVisible(false);
					 panelCC.setVisible(true);
				}
				

			}
		});
		btnSaveContact.setBounds(178, 302, 128, 30);
		panelNewCasual.add(btnSaveContact);
		panelNewCasual.setVisible(true);
		
	}
	
	
	  public void ReadData(ArrayList<Acquaintance> contact_book,String file)
	    {
	        try {
	        	FileOutputStream fileOut = new FileOutputStream(file); 
	        	ObjectOutputStream Output = new ObjectOutputStream(fileOut);
	            
	        	Output.writeObject(contact_book);


	           // System.out.println("Stored in database"); 
	            fileOut.close(); 
	            Output.close();
	        }
	        catch (FileNotFoundException e) 
	        { 
	            //System.err.println("File not found");
	        }
	        catch (IOException e)
	        {
	        	//System.err.println("Read or write failed");
	        }
	    }
	  
	  
	//Tried as an alternative to JOptionPane.showMessageDialog but unused due to incorrect functioning
	public void displayMessage(String message,JPanel parentPanel)
	{
		parentPanel.setVisible(false);
		JPanel panelMessage= new JPanel();
		panelMessage.setBounds(0, 0, 450, 400);
		getContentPane().add(panelMessage);
		panelMessage.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(message);
		lblNewLabel.setBounds(30, 80, 350, 50);
		panelMessage.add(lblNewLabel);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				panelMessage.setVisible(false);
				parentPanel.setVisible(true);
			}
		});
		btnOk.setBounds(160, 160, 90, 35);
		panelMessage.add(btnOk);
	}
	
}

