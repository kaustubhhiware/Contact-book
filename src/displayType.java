
import java.awt.Container;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class displayType extends JFrame implements  Serializable{

	private JFrame frame;
	private ButtonGroup group;
	/**
	 * Create the application.
	 * Basic GUI for displaying options of each group .Each button leads to displayThis.java for
	 * displaying that type of contact returned by this program as a String thisType.
	 */
	public displayType(ArrayList<Acquaintance> contact_book,ArrayList<Relative> relative_book,ArrayList<Personal> personal_book
			,ArrayList<Professional> professional_book,ArrayList<Casual> casual_book,String filename)
	{
		getContentPane().setLayout(null);
		
		ReadData r = new ReadData(contact_book,relative_book,personal_book,professional_book,casual_book,filename);

		frame = new JFrame();
		frame.setTitle("Groups");
		frame.setBounds(100, 100, 450, 300);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		
	/*	
		JButton btngroup = new JButton("group",group);
		btngroup.setBounds(0, 0, 434,261);

		frame.getContentPane().add(btngroup);
		*/
		
		Icon group = new ImageIcon(getClass().getResource("group.png"));
		JButton btnGroup = new JButton(group);
		btnGroup.setBorderPainted( false );
		btnGroup.setFocusPainted( false );
		btnGroup.setBounds(43, 45, 151, 153);
		frame.getContentPane().add(btnGroup);

		
		JLabel lblSelectAGroup = new JLabel("Select a group");
		lblSelectAGroup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectAGroup.setBounds(253, 23, 119, 35);
		frame.getContentPane().add(lblSelectAGroup);
		
		JButton btnRelative = new JButton("Relative");
		btnRelative.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				displayThis r = new displayThis(contact_book,relative_book,personal_book,professional_book,casual_book,"relative",filename);
			}
		});
		btnRelative.setBounds(253, 69, 171, 33);
		frame.getContentPane().add(btnRelative);
		
		JButton btnPersonalFriend = new JButton("Personal Friend");
		btnPersonalFriend.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
				displayThis pe = new displayThis(contact_book,relative_book,personal_book,professional_book,casual_book,"personal",filename);
			}
		});
		
		btnPersonalFriend.setBounds(253, 106, 171, 33);
		frame.getContentPane().add(btnPersonalFriend);
		
		JButton btnProfessionalFriend = new JButton("Professional Friend");
		btnProfessionalFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				displayThis pr = new displayThis(contact_book,relative_book,personal_book,professional_book,casual_book,"professional",filename);
			}
		});
		btnProfessionalFriend.setBounds(253, 145, 171, 33);
		frame.getContentPane().add(btnProfessionalFriend);
		
		JButton btnCasualAcquaintance = new JButton("Casual Acquaintance");
		btnCasualAcquaintance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				displayThis c = new displayThis(contact_book,relative_book,personal_book,professional_book,casual_book,"casual",filename);
			}
		});
		btnCasualAcquaintance.setBounds(253, 186, 171, 33);
		frame.getContentPane().add(btnCasualAcquaintance);		
		
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
		btnBack.setBounds(103, 209, 41, 41);
		frame.getContentPane().add(btnBack);
		
		


	}
}
