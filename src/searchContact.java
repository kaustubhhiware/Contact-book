
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class searchContact extends JFrame {//implements  Serializable{

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public searchContact(ArrayList<Acquaintance> contact_book,ArrayList<Relative> relative_book,ArrayList<Personal> personal_book
			,ArrayList<Professional> professional_book,ArrayList<Casual> casual_book,String Nemo) 
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


		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 450, 350);
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
		btnBack.setBounds(10, 259, 41, 41);
		frame.getContentPane().add(btnBack);	

		

		table = new JTable(contact_object,columns);
		table.setBounds(10, 99, 414, 149);
		frame.getContentPane().add(table);
		
		
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setLocation(10, 63);
		scrollPane.setSize(414, 190);
		frame.getContentPane().add(scrollPane);
		/*
		 * 1 textfield to find a name .
		 * Ex : Search for r, ra , ram yields different results like in phone
		 * The user has to press enter in the textfield for searching
		 */
		JTextField textField = new JTextField(Nemo);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0)
			{
				frame.dispose();
				String Dory = textField.getText();
				searchContact sC = new searchContact(contact_book,relative_book,personal_book,professional_book,casual_book,Dory);	
			}
		});/*
		textField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				String Dory = textField.getText();
				searchContact sC = new searchContact(contact_book,relative_book,personal_book,professional_book,casual_book,Dory);
			}
		});*/
		textField.setBounds(10, 11, 415, 41);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				int checkOut = table.getSelectedRow();
				if(checkOut==-1)
				{
					JOptionPane.showMessageDialog(null, "Select a contact first!");
				}
				else
				{
					viewContact vC = new viewContact(contact_book.get(checkOut),relative_book,personal_book,professional_book,casual_book); 
				}
			}
		});
		btnView.setBounds(175, 259, 89, 41);
		frame.getContentPane().add(btnView);
		

		

	}
}
