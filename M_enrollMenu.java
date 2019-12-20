package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CookingClass.Client.TeacherMain;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class M_enrollMenu extends JFrame  {

	private JPanel contentPane;
	private JTextField day;
	private JTable table;
	private DefaultTableModel dmt;
	Socket socket=null;
	OutputStream os=null;
	ObjectOutputStream oos=null;
	InputStream is=null;
	ObjectInputStream ois=null;
	TeacherMain tea=new TeacherMain();
	ArrayList <Menu> menu = new ArrayList <Menu> ();
	ArrayList <MenuList> menulist = new ArrayList <MenuList> ();
	//totalMenus t=null;
	//ArrayList <totalApplications> a = new ArrayList <totalApplications> ();
	private JTextField bT;
	private JTextField m1;
	private JTextField m2;
	private JTextField m3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M_enrollMenu frame = new M_enrollMenu();
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
	public M_enrollMenu() {
		try {
		//socket=new Socket("192.168.208.148",5002);
		socket=new Socket("192.168.208.148",5001);
		os=socket.getOutputStream();
		oos=new ObjectOutputStream(os);
		is=socket.getInputStream();
		ois=new ObjectInputStream(is);
		setTitle("\uC2E4\uC2B5\uBA54\uB274 \uC870\uD68C");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		day = new JTextField();
		day.setColumns(10);
		day.setBounds(40, 86, 88, 28);
		contentPane.add(day);
		
		JButton button = new JButton("\uD655\uC778");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				
//				try {
//				
//				
//				dmt.addRow(new Object[] {t.getDate(),t.getMenuName(),t.getBranch1(),t.getBranch2(),t.getBranch3()});
//				socket.close();
//			}catch(UnknownHostException e1) { System.err.println(e1); }
//				catch(IOException e1) { System.err.println(e1); }
//				catch(Exception e1) { System.err.println(e1); }
				String b = bT.getText();
				int d = Integer.parseInt(day.getText());
				String mm1 = m1.getText();
				String mm2 = m2.getText();
				String mm3 = m3.getText();
				
				try {
					
					ArrayList<Menu> menus=new ArrayList<Menu>();
					System.out.println("A");
					Menu tmp = new Menu(b, d, mm1);
					menus.add(tmp);
					dmt.addRow(new Object[] {tmp.getDate(),tmp.getBranch(),tmp.getMenuID()});
					tmp = new Menu(b, d, mm2);
					menus.add(tmp);
					dmt.addRow(new Object[] {tmp.getDate(),tmp.getBranch(),tmp.getMenuID()});
					tmp = new Menu(b, d, mm3);
					menus.add(tmp);
					dmt.addRow(new Object[] {tmp.getDate(),tmp.getBranch(),tmp.getMenuID()});
					//enrollMenu(b,d,mm1,mm2,mm3);
					System.out.println("A");
					oos.writeObject(menus);
					oos.flush();
					System.out.println("A");
					
					socket.close();
					
				}
			catch(UnknownHostException e1) { System.err.println(e1); }
			catch(IOException e1) { System.err.println(e1); }
			catch(Exception e1) { System.err.println(e1); }
				
		}
		});
		
		button.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		button.setBounds(526, 83, 88, 27);
		contentPane.add(button);
		
		Panel panel = new Panel();
		panel.setBounds(27, 164, 587, 224);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		dmt=new DefaultTableModel(
				new Object[][] {
		},
		new String[] {
				"\uB0A0\uC9DC", "\uC7A5\uC18C", "\uC2DD\uB2E8ID"
		}
	);
		table = new JTable(dmt);
		table.setModel(dmt);
		scrollPane.setViewportView(table);
		JLabel lblNewLabel = new JLabel("<\uC2E4\uC2B5\uBA54\uB274 \uB4F1\uB85D>");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		lblNewLabel.setBounds(204, 12, 262, 28);
		contentPane.add(lblNewLabel);
		
		bT = new JTextField();
		bT.setBounds(143, 85, 88, 31);
		contentPane.add(bT);
		bT.setColumns(10);
		
		m1 = new JTextField();
		m1.setBounds(442, 87, 50, 26);
		contentPane.add(m1);
		m1.setColumns(10);
		
		m2 = new JTextField();
		m2.setBounds(308, 87, 50, 27);
		contentPane.add(m2);
		m2.setColumns(10);
		
		m3 = new JTextField();
		m3.setBounds(378, 87, 50, 26);
		contentPane.add(m3);
		m3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\uB0A0\uC9DC");
		lblNewLabel_1.setBounds(52, 55, 62, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC7A5\uC18C");
		lblNewLabel_2.setBounds(154, 56, 62, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel menuL1 = new JLabel("\uBA54\uB274 1");
		menuL1.setBounds(308, 67, 62, 18);
		contentPane.add(menuL1);
		
		JLabel menuL2 = new JLabel("\uBA54\uB274 2");
		menuL2.setBounds(378, 67, 62, 18);
		contentPane.add(menuL2);
		
		JLabel menuL3 = new JLabel("\uBA54\uB274 3");
		menuL3.setBounds(450, 67, 62, 18);
		contentPane.add(menuL3);
		
		System.out.println("A");
		String str22="l";
		System.out.println(str22);
		oos.writeObject(str22);
		oos.flush();
		System.out.println("A");
		menu = (ArrayList<Menu>) ois.readObject();
		for(Menu t: menu) {
			
			dmt.addRow(new Object[] {t.getDate(),t.getBranch(),t.getMenuID()});
			
		}
	}
		catch(UnknownHostException e) { System.err.println(e); }
		catch(IOException e) { System.err.println(e); }
		catch(Exception e) { System.err.println(e); }
	}
}
