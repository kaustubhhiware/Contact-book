
import java.awt.Container;
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class displayContacts extends JFrame implements  Serializable{

	private JFrame frame;
	private JTable table;
	private JButton btnView;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public void forceExit()
	{
		frame.dispose();
	}
	public displayContacts(ArrayList<Acquaintance> contact_book,ArrayList<Relative> relative_book,ArrayList<Personal> personal_book
			,ArrayList<Professional> professional_book,ArrayList<Casual> casual_book,int canDelete,String filename) 
	{
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
		
		
		frame = new JFrame();
		if(canDelete==0)
		{
			frame.setTitle("Contacts");
		}
		else
		{
			frame.setTitle("Exterminate!");
		}
		frame.setBounds(100, 100, 480, 300);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
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
		btnBack.setBounds(20, 201,41, 41);
		frame.getContentPane().add(btnBack);
	
		table = new JTable(contact_object,columns);
		if(canDelete==1)
		{
			table.enableInputMethods(true);
		}
		table.setBounds(10, 41, 414, 160);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setLocation(10, 11);
		scrollPane.setSize(444, 190);
		frame.getContentPane().add(scrollPane);
		
		btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int checkOut = table.getSelectedRow();
				if(checkOut==-1)
				{
					JOptionPane.showMessageDialog(null, "Select a contact first!");
				}
				else
				{
					try{
						viewContact vC = new viewContact(contact_book.get(checkOut),relative_book,personal_book,professional_book,casual_book); 
					}
					catch(IndexOutOfBoundsException e)
					{
						JOptionPane.showMessageDialog(null, "Contact removed!");
						frame.dispose();
					}
					}
			
			}
		});
		btnView.setBounds(170, 200, 90, 40);
		frame.getContentPane().add(btnView);
		
		if(canDelete==1)
		{
			JButton btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					int discard = table.getSelectedRow();
					if(discard==-1)
					{
						JOptionPane.showMessageDialog(null, "Select a contact first!");
					}
					else
					{
						Acquaintance removed = contact_book.remove(discard);
						String nameX = removed.getname();
						String numX = removed.getnumber();
						String mailX = removed.getmail();
						int k;

						if(removed.gettype().equals("relative"))
						{
							for(k = 0;k < relative_book.size() ; k++)
							{
								if((relative_book.get(k).getname().equals(nameX))&&(relative_book.get(k).getnumber().equals(numX))&&(relative_book.get(k).getmail().equals(mailX)))
								{
									Relative cancelled = relative_book.remove(k);
									break;
								}
							}
						}
						else if(removed.gettype().equals("personal"))
						{
							for(k = 0;k < personal_book.size() ; k++)
							{
								if((personal_book.get(k).getname().equals(nameX))&&(personal_book.get(k).getnumber().equals(numX))&&(personal_book.get(k).getmail().equals(mailX)))
								{
									Personal cancelled = personal_book.remove(k);
									break;
								}
							}
						}
						else if(removed.gettype().equals("professional"))
						{
							for(k = 0;k < professional_book.size() ; k++)
							{
								if((professional_book.get(k).getname().equals(nameX))&&(professional_book.get(k).getnumber().equals(numX))&&(professional_book.get(k).getmail().equals(mailX)))
								{
									Professional cancelled = professional_book.remove(k);
									break;
								}
							}
						}
						else if(removed.gettype().equals("casual"))
						{
							for(k = 0;k < casual_book.size() ; k++)
							{
								if((casual_book.get(k).getname().equals(nameX))&&(casual_book.get(k).getnumber().equals(numX))&&(casual_book.get(k).getmail().equals(mailX)))
								{
									Casual cancelled = casual_book.remove(k);
									break;
								}
							}
						}
						
						ReadData r = new ReadData(contact_book,relative_book,personal_book,professional_book,casual_book,filename);
						JOptionPane.showMessageDialog(null,"Contact " + removed.getname() + " has been removed");
						frame.dispose();
						displayContacts dC2 = new displayContacts(contact_book,relative_book,personal_book,professional_book,casual_book,1,filename);
					}
				}
			});
			btnDelete.setBounds(340, 200, 80, 40);
			frame.getContentPane().add(btnDelete);
		}
	}
}
