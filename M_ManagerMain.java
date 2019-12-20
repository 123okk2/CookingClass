package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CookingClass.Client.Manager;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;
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
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;

public class M_ManagerMain extends JFrame {

	private JPanel contentPane;
	
	
	//ÇÊ¿äÇÑ °Í ¼±¾ð
	
	ArrayList <Menu> menu = new ArrayList <Menu> ();
	ArrayList <MenuList> menulist = new ArrayList <MenuList> ();
	ArrayList <Applications> applications = new ArrayList <Applications> ();
	ArrayList <ApplicationsList> applicationslist = new ArrayList <ApplicationsList> ();
	ArrayList <Recipe> recipe = new ArrayList <Recipe> ();
	ArrayList <Reserve> reserve = new ArrayList <Reserve> ();
	ArrayList <Schedule> schedule = new ArrayList <Schedule> ();
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M_ManagerMain frame = new M_ManagerMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public M_ManagerMain() {
		try {
//		socket=new Socket("192.168.208.148",5001);
//		os=socket.getOutputStream();
//		oos=new ObjectOutputStream(os);
//		is=socket.getInputStream();
//		ois=new ObjectInputStream(is);
		//Manager man=new Manager();
    	
					
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel_2 = new Panel();
		panel_2.setEnabled(false);
		panel_2.setBackground(Color.ORANGE);
		panel_2.setBounds(528, 390, 246, 31);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uAC15\uC758\uC77C\uC790\uBCC4 \uC2E4\uC2B5 \uBA54\uB274 \uB4F1\uB85D");
		lblNewLabel_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel_2.add(lblNewLabel_3);
		
		Panel panel_1 = new Panel();
		panel_1.setEnabled(false);
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(297, 390, 183, 31);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uAC15\uC758\uC77C\uC815\uD45C Load");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_loadSchedule secondFrame = new M_loadSchedule();
				secondFrame.setVisible(true);
			}
		});
		
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\\uC624\uC9C0\uC218\\Desktop\\\uC544\uC774\uCF58\\load schedule.png"));
		btnNewButton_1.setBounds(249, 114, 273, 393);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\\uC624\uC9C0\uC218\\Desktop\\\uC544\uC774\uCF58\\menu.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_enrollMenu secondFrame = new M_enrollMenu();
				secondFrame.setVisible(true);
			}
		});
		btnNewButton.setBounds(519, 114, 265, 393);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("[ \uAD00 \uB9AC \uC790 ]");
		lblNewLabel.setBounds(339, 0, 445, 103);
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 26));
		contentPane.add(lblNewLabel);
		
		Panel panel = new Panel();
		panel.setEnabled(false);
		panel.setBackground(Color.PINK);
		panel.setBounds(46, 390, 153, 31);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Recipe Load");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBackground(Color.PINK);
		btnNewButton_2.setForeground(Color.PINK);
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\\uC624\uC9C0\uC218\\Desktop\\\uC544\uC774\uCF58\\load recipe.PNG"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_loadRecipe2 secondFrame = new M_loadRecipe2();
				secondFrame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(-13, 114, 265, 393);
		contentPane.add(btnNewButton_2);
		}
//		}catch(UnknownHostException e) { System.err.println(e); }
//		catch(IOException e) { System.err.println(e); }
		catch(Exception e) { System.err.println(e); }
	}
}
