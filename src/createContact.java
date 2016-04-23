
import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class createContact extends JFrame implements  Serializable{

	private JFrame frame;

	/**
	 * Create the application.
	 * This file provides GUI for create contact menu . It then proceeds to new<Type>.java for 
	 * saving <Type> contact 
	 */
	public createContact(ArrayList<Acquaintance> contact_book,ArrayList<Relative> relative_book,ArrayList<Personal> personal_book
			,ArrayList<Professional> professional_book,ArrayList<Casual> casual_book,String filename) 
	{
		frame = new JFrame();
		frame.setTitle("Create contact");
		frame.setBounds(100, 100, 450, 300);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWhatTypeOf = new JLabel("What type of contact do you wish to make ?");
		lblWhatTypeOf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWhatTypeOf.setBounds(33, 31, 325, 30);
		frame.getContentPane().add(lblWhatTypeOf);
		
		
		JButton btnRelative = new JButton("Relative");
		btnRelative.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
			//	JOptionPane.showMessageDialog(null,String.format("%s",event.getActionCommand()));
			
				newRelative r = new newRelative(contact_book,relative_book,personal_book,professional_book,casual_book,filename);
			}
		});
		btnRelative.setBounds(33, 65, 157, 36);
		frame.getContentPane().add(btnRelative);
		
		JButton btnPersonalFriend = new JButton("Personal friend");
		btnPersonalFriend.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				newPersonal pe = new newPersonal(contact_book,relative_book,personal_book,professional_book,casual_book,filename); 
			}
		});
		btnPersonalFriend.setBounds(33, 114, 157, 36);
		frame.getContentPane().add(btnPersonalFriend);
		
		JButton btnProfessionalFriend = new JButton("Professional friend");
		btnProfessionalFriend.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				newProfessional pr = new newProfessional(contact_book,relative_book,personal_book,professional_book,casual_book,filename);
			}
		});
		btnProfessionalFriend.setBounds(33, 161, 157, 36);
		frame.getContentPane().add(btnProfessionalFriend);
		
		JButton btnCasualAcquaintance = new JButton("Casual Acquaintance");
		btnCasualAcquaintance.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				newCasual c = new newCasual(contact_book,relative_book,personal_book,professional_book,casual_book,filename);
			}
		});
		btnCasualAcquaintance.setBounds(33, 208, 157, 36);
		frame.getContentPane().add(btnCasualAcquaintance);
		
		
		Icon new_contact = new ImageIcon(getClass().getResource("new_contact.png"));
		JButton btnNewButton = new JButton("New contact",new_contact);
		btnNewButton.setBounds(247, 72, 136, 109);
		btnNewButton.setBorderPainted( false );
		btnNewButton.setFocusPainted( false );

		frame.getContentPane().add(btnNewButton);
		
		Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton(back);
		btnBack.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent arg1)
				{
					Container frame2 = btnBack.getParent();
		            do 
		                frame2 = frame2.getParent(); 
		            while (!(frame instanceof JFrame));                                      
		            ((JFrame) frame).dispose();
				}
		});
		btnBack.setBounds(295, 205, 43, 43);
		frame.getContentPane().add(btnBack);
		frame.setVisible(true);
	}
}
