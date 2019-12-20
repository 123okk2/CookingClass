package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CookingClass.Client.StudentMain;

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
import java.awt.event.ActionEvent;
import java.util.Date;

public class S_CancelRecipe extends JFrame {

	private JPanel contentPane;
	private final JLabel label = new JLabel("< \uCDE8 \uC18C >");
	private JTextField name;
	private JTextField phon;
	private JTextField day;
	private JTextField place;
	private JTextField foodname;
	private JButton btnNewButton;
	Socket socket=null;
	OutputStream os=null;
	ObjectOutputStream oos=null;
	InputStream is=null;
	ObjectInputStream ois=null;
	StudentMain stu=new StudentMain();
	ArrayList <Menu> menu = new ArrayList <Menu> ();
	ArrayList <MenuList> menulist = new ArrayList <MenuList> ();
	ArrayList <Applications> applications = new ArrayList <Applications> ();
	ArrayList <ApplicationsList> applicationslist = new ArrayList <ApplicationsList> ();
	ArrayList <Recipe> recipe = new ArrayList <Recipe> ();
	ArrayList <Reserve> reserve = new ArrayList <Reserve> ();
	ArrayList <Schedule> schedule = new ArrayList <Schedule> ();
	ArrayList <MenuList> menuList=new ArrayList<MenuList>(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					S_CancelRecipe frame = new S_CancelRecipe();
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
	public S_CancelRecipe() {
		try {
		socket=new Socket("192.168.208.148",5003);
		
		os=socket.getOutputStream();
		
		oos=new ObjectOutputStream(os);
		
		is=socket.getInputStream();
		
		ois=new ObjectInputStream(is);	
		String str = "C";
		oos.writeObject(str);
          				oos.flush();
     					
     	menulist=(ArrayList <MenuList>) ois.readObject();
     					stu.InsertMenuList(menulist);
		applications=(ArrayList <Applications>) ois.readObject();
    	 				stu.InsertApplications(applications);
		applicationslist=(ArrayList <ApplicationsList>) ois.readObject();
     					stu.InsertApplicationsList(applicationslist);
		setTitle("\uC2DD\uC7AC\uB8CC \uCDE8\uC18C");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		label.setBounds(158, 12, 97, 36);
		contentPane.add(label);
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		
		JLabel label_1 = new JLabel("\uC774\uB984 : ");
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		label_1.setBounds(27, 93, 50, 18);
		contentPane.add(label_1);
		
		name = new JTextField();
		name.setBounds(120, 93, 134, 24);
		contentPane.add(name);
		name.setColumns(10);
		
		phon = new JTextField();
		phon.setColumns(10);
		phon.setBounds(120, 151, 134, 24);
		contentPane.add(phon);
		
		JLabel label_2 = new JLabel("\uC804\uD654\uBC88\uD638 : ");
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		label_2.setBounds(27, 152, 92, 23);
		contentPane.add(label_2);
		
		day = new JTextField();
		day.setColumns(10);
		day.setBounds(120, 212, 134, 24);
		contentPane.add(day);
		
		JLabel label_3 = new JLabel("\uB0A0\uC9DC : ");
		label_3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		label_3.setBounds(27, 212, 50, 18);
		contentPane.add(label_3);
		
		place = new JTextField();
		place.setColumns(10);
		place.setBounds(120, 271, 134, 24);
		contentPane.add(place);
		
		JLabel label_4 = new JLabel("\uC7A5\uC18C : ");
		label_4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		label_4.setBounds(27, 271, 50, 18);
		contentPane.add(label_4);
		
		foodname = new JTextField();
		foodname.setColumns(10);
		foodname.setBounds(120, 326, 134, 24);
		contentPane.add(foodname);
		
		JLabel label_5 = new JLabel("\uC2DD\uB2E8\uBA85 : ");
		label_5.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		label_5.setBounds(27, 327, 67, 18);
		contentPane.add(label_5);
		
		btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ãë¼Ò ¹öÆ°
				//int cancelDay = Integer.parseInt(day.getText());
				Date date=new Date();
			    int cancelDay=(1900+date.getYear())*10000+(1+date.getMonth())*100+(date.getDay()+23);
					try {
					int[] cancle = stu.Cancle(name.getText(),phon.getText(),Integer.parseInt(day.getText()),place.getText(),foodname.getText(),cancelDay);
					oos.writeObject(cancle);
					oos.flush();
				dispose();
					}
					catch(UnknownHostException e1) { System.err.println(e1); }
					catch(IOException e1) { System.err.println(e1); }
					catch(Exception e1) { System.err.println(e1); }
			}
		});
		btnNewButton.setBounds(300, 386, 105, 27);
		contentPane.add(btnNewButton);
	}
		catch(UnknownHostException e) { System.err.println(e); }
		catch(IOException e) { System.err.println(e); }
		catch(Exception e) { System.err.println(e); }
	}

}
