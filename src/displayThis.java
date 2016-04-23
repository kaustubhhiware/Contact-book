
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;

public class displayThis extends JFrame implements  Serializable{

	private JFrame frame;
	private JTable table;
	private JLabel lblName;
	private JLabel lblEmail;
	private JLabel lblNumber;
	private JButton btnView;
	/**
	 * Create the application.
	 * Display any type of contact
	 */
	public displayThis(ArrayList<Acquaintance> contact_book,ArrayList<Relative> relative_book,ArrayList<Personal> personal_book
			,ArrayList<Professional> professional_book,ArrayList<Casual> casual_book,String thisType,String filename)
	{

		String[] columns = {"Name", "Number", "E-mail"};
		int size = contact_book.size();
		Object[][] contact_object  = new String[size][3];
		
		int i;
		int j = 0;
		for(i = 0; i < size ; i++)
		{
			if(contact_book.get(i).gettype().equals(thisType))
			{
			contact_object[j][0] = contact_book.get(i).getname();
			contact_object[j][1] = contact_book.get(i).getnumber();
			contact_object[j][2] = contact_book.get(i).getmail();
			j++;
			}
		}
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
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
		
		JScrollPane scrollPane = new JScrollPane(table);
		frame.getContentPane().add(btnBack);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(contact_object,columns);

		table.setBounds(10, 41, 414, 149);
		frame.getContentPane().add(table);
		
		lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 91, 19);
		frame.getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(354, 11, 70, 19);
		frame.getContentPane().add(lblEmail);
		
		lblNumber = new JLabel("Number");
		lblNumber.setBounds(166, 11, 70, 19);
		frame.getContentPane().add(lblNumber);
		
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
					viewContact vC = new viewContact(contact_book.get(checkOut),relative_book,personal_book,professional_book,casual_book); 
				}
			
			}
		});
		btnView.setBounds(170, 200, 90, 40);
		frame.getContentPane().add(btnView);

		
	}
}
