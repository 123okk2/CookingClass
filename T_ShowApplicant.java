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
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class T_ShowApplicant extends JFrame {

	private JPanel contentPane;
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
	ArrayList <Applications> applications = new ArrayList <Applications> ();
	ArrayList <totalApplications> a = new ArrayList <totalApplications> ();
	ArrayList <ApplicationsList> applicationslist = new ArrayList <ApplicationsList> ();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					T_ShowApplicant frame = new T_ShowApplicant();
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
	public T_ShowApplicant() {
		try {
			System.out.println("a");
			socket=new Socket("192.168.208.148",5002);
			os=socket.getOutputStream();
			oos=new ObjectOutputStream(os);
			is=socket.getInputStream();
			ois=new ObjectInputStream(is);
			String str="A";
			oos.writeObject(str);
			oos.flush();
			System.out.println("a");
			
			
			
			
			applications=(ArrayList <Applications>) ois.readObject();
			System.out.println("°³¼ö : " + applications.size());
			tea.InsertApplications(applications);
			applicationslist=(ArrayList <ApplicationsList>) ois.readObject();
			System.out.println("°³¼ö : " + applicationslist.size());
			tea.InsertApplicationsList(applicationslist);
			menulist=(ArrayList <MenuList>) ois.readObject();
			System.out.println("°³¼ö : " + menulist.size());
			tea.InsertMenuList(menulist);
			tea.InsertApplications(applications);
			a=tea.showApplicationss();
			int i=0;
			
			
		setTitle("\uC2DD\uC7AC\uB8CC \uC2E0\uCCAD\uC790 \uBA85\uB2E8 \uC870\uD68C");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(78, 164, 647, 311);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		dmt=new DefaultTableModel(
				new Object[][] {
		},
		new String[] {
			"\uC2E0\uCCAD\uBC88\uD638", "\uC218\uC5C5\uC77C", "\uC774\uB984", "\uC804\uD654\uBC88\uD638", "\uBA54\uB274\uBA85", "\uC785\uAE08\uC77C"
		}
	);
		table = new JTable(dmt);
		table.setModel(dmt);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("<\uC2DD\uC7AC\uB8CC \uC2E0\uCCAD\uC790 \uBA85\uB2E8 \uC870\uD68C>");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		lblNewLabel.setBounds(261, 18, 289, 28);
		contentPane.add(lblNewLabel);
		while(true)
		{
			System.out.println(i);
			dmt.addRow(new Object [] {a.get(i).getNum(),a.get(i).getDate(),a.get(i).getName(),a.get(i).getPhoneNum(),a.get(i).getMenuName(),a.get(i).getDate2()} );
			i++;
			if(i>a.size()) {break;}
		}
		socket.close();
	}catch(UnknownHostException e) { System.err.println(e); }
		catch(IOException e) { System.err.println(e); }
		catch(Exception e) { System.err.println(e); }
		
	}
}
