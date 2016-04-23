
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class viewProfessional extends JFrame implements  Serializable{

	private JFrame frame;


	/**
	 * Create the application.
	 * Create new Casual Acquaintance
	 */
	public viewProfessional(Professional savedContact) 
	{
		
		frame = new JFrame();
		frame.setTitle("View Professional Friend");	
		frame.setBounds(100, 100, 450, 275);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(71, 11, 81, 30);
		frame.getContentPane().add(lblName);
		
		JTextField textField = new JTextField(savedContact.getname());
		textField.setBounds(178, 11, 232, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(71, 56, 81, 30);
		frame.getContentPane().add(lblNumber);
		
		JTextField textField_1 = new JTextField(savedContact.getnumber());
		textField_1.setBounds(178, 56, 232, 30);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(71, 97, 81, 30);
		frame.getContentPane().add(lblEmail);
		
		JTextField textField_2 = new JTextField(savedContact.getmail());
		textField_2.setBounds(178, 97, 232, 30);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		JLabel lblCommonInterests = new JLabel("Common Interests");
		lblCommonInterests.setBounds(24, 138, 128, 30);
		frame.getContentPane().add(lblCommonInterests);
		
		JTextField textField_3 = new JTextField(savedContact.getinterest());
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

	}
}
