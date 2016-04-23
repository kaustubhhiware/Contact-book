
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class viewPersonal extends JFrame implements Serializable {

	private JFrame frame;

	/**
	 * Create the application. Create new Casual Acquaintance
	 */
	public viewPersonal(Personal savedContact) {

		frame = new JFrame();
		frame.setTitle("View Personal Friend");
		frame.setBounds(100, 100, 450, 350);
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

		JLabel lblContext = new JLabel("Context when met");
		lblContext.setBounds(36, 138, 116, 30);
		frame.getContentPane().add(lblContext);

		JTextField textField_3 = new JTextField(savedContact.getcontext());
		textField_3.setBounds(178, 138, 232, 30);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		JLabel lblAcquaintedOn = new JLabel("Acquainted on (date)");
		lblAcquaintedOn.setBounds(24, 179, 128, 30);
		frame.getContentPane().add(lblAcquaintedOn);

		String convention = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(convention);

		//String date = sdf.format(savedContact.getdate());
		JTextField textField_4 = new JTextField(savedContact.getdate());
		textField_4.setBounds(178, 179, 232, 30);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);

		JLabel lblSpecificEvents = new JLabel("Specific Events");
		lblSpecificEvents.setBounds(36, 219, 116, 30);
		frame.getContentPane().add(lblSpecificEvents);

		JTextField textField_5 = new JTextField(savedContact.getspecific());
		textField_5.setBounds(178, 219, 232, 30);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);

		Icon back = new ImageIcon(getClass().getResource("back.png"));
		JButton btnBack = new JButton(back);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Container frame2 = btnBack.getParent();
				do
					frame2 = frame2.getParent();
				while (!(frame instanceof JFrame));
				((JFrame) frame).dispose();
			}
		});
		btnBack.setBounds(36, 260, 43, 43);
		frame.getContentPane().add(btnBack);

	}
}
