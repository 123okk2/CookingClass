package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	static Socket socket=null;
	static OutputStream os=null;
	static ObjectOutputStream oos=null;
	static InputStream is=null;
	static ObjectInputStream ois=null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public Login() {
		try {
		socket=new Socket("192.168.208.148",5000);
		os=socket.getOutputStream();
		oos=new ObjectOutputStream(os);
		is=socket.getInputStream();
		ois=new ObjectInputStream(is);

		setTitle("Log-in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		lblId.setBounds(42, 75, 62, 18);
		contentPane.add(lblId);
		
		
		JLabel lblPw = new JLabel("PW :");
		lblPw.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		lblPw.setBounds(42, 152, 62, 18);
		contentPane.add(lblPw);
		
		textField = new JTextField();
		textField.setBounds(99, 75, 267, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(98, 152, 268, 26);
		contentPane.add(passwordField);
		
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//·Î±×ÀÎ È®ÀÎ¹öÆ°
				try {
					
				String id=textField.getText();
				String pw=new String(passwordField.getPassword());
				oos.writeObject(id);
				oos.flush();
				oos.writeObject(pw);
				oos.flush();
				String type=(String) ois.readObject();
				os.close(); is.close(); oos.close(); ois.close();
				socket.close();
				
			
				ArrayList <Schedule> schedule = new ArrayList <Schedule> ();

				if(type.equals("Manager"))
				{
					socket.close();					
					M_ManagerMain frame=new M_ManagerMain();
					frame.setVisible(true);
					dispose();

				
				}
				else if(type.equals("Teacher"))
				{
					socket.close();
					T_TeacherMain frame=new T_TeacherMain();
					frame.setVisible(true);
					dispose();
					
				}
				else if(type.equals("Student"))
				{
					socket.close();					
					S_StudentMain frame=new S_StudentMain();
					frame.setVisible(true);
					dispose();
				}
				
				else
				{
					JOptionPane.showMessageDialog(null,"Æ²·È½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä",null , JOptionPane.INFORMATION_MESSAGE);
									}
				
			}
				catch(UnknownHostException e1) { System.err.println(e1);System.out.println("1"); }
				catch(IOException e1) { System.err.println(e1); System.out.println("2");}
				catch(Exception e1) { System.err.println(e1); System.out.println("3");}
				/*finally{
					try {
						oos.close();
						oos.close();
						socket.close();
						ois.close();
						is.close();
					}
					catch(IOException e1) { System.err.println(e1); }
		}*/
		}
		}
			);
		btnNewButton.setBounds(298, 214, 105, 27);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("<\uC2DD\uC790\uC7AC \uC18C\uC694\uB7C9 \uC0B0\uCD9C \uC2DC\uC2A4\uD15C>");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		lblNewLabel.setBounds(99, 12, 250, 32);
		contentPane.add(lblNewLabel);
		}
		catch(UnknownHostException e) { System.err.println(e);System.out.println("1"); }
		catch(IOException e) { System.err.println(e);System.out.println("2"); }
		catch(Exception e) { System.err.println(e); System.out.println("3");}
	}
	
	
	
}
