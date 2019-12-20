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

public class T_ShowMenu extends JFrame  {

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
	totalMenus t=null;
	ArrayList <totalApplications> a = new ArrayList <totalApplications> ();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					T_ShowMenu frame = new T_ShowMenu();
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
	public T_ShowMenu() {
		try {
		socket=new Socket("192.168.208.148",5002);
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
		
		JLabel label = new JLabel("\uC77C\uC790 :");
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label.setBounds(75, 66, 64, 28);
		contentPane.add(label);
		
		day = new JTextField();
		day.setColumns(10);
		day.setBounds(152, 66, 280, 28);
		contentPane.add(day);
		
		JButton button = new JButton("\uD655\uC778");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int date=Integer.parseInt(day.getText());
				try {
				String str="M";
				oos.writeObject(str);
				oos.flush();
				menu=(ArrayList <Menu>) ois.readObject();
				tea.InsertMenu(menu);
				menulist=(ArrayList <MenuList>) ois.readObject();
				tea.InsertMenuList(menulist);
				t=tea.showMenus(date);
				
				dmt.addRow(new Object[] {t.getDate(),t.getMenuName(),t.getBranch1(),t.getBranch2(),t.getBranch3()});
				socket.close();
			}catch(UnknownHostException e1) { System.err.println(e1); }
				catch(IOException e1) { System.err.println(e1); }
				catch(Exception e1) { System.err.println(e1); }
		}
		});
		button.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		button.setBounds(446, 67, 88, 27);
		contentPane.add(button);
		
		JLabel lblEx = new JLabel("ex)181222");
		lblEx.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		lblEx.setBounds(381, 117, 165, 28);
		contentPane.add(lblEx);
		
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
			"\uB0A0\uC9DC", "\uC7A5\uC18C", "\uBA54\uB274 1", "\uBA54\uB274 2", "\uBA54\uB274 3"
		}
	);
		table = new JTable(dmt);
		table.setModel(dmt);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("<\uC2E4\uC2B5\uBA54\uB274 \uC77C\uC790\uBCC4 \uC870\uD68C>");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		lblNewLabel.setBounds(204, 12, 262, 28);
		contentPane.add(lblNewLabel);
	}
		catch(UnknownHostException e) { System.err.println(e); }
		catch(IOException e) { System.err.println(e); }
		catch(Exception e) { System.err.println(e); }
	}
	
}
