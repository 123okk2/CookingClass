package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CookingClass.Client.StudentMain;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class S_ApplyRecipe extends JFrame {

	private JPanel panel;
	private JTextField name;
	private JTextField phon;
	private JTextField day;
	private JTextField place;
	private JTextField menu1;
	private JTextField price;
	private JTextField menu_name;
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
					S_ApplyRecipe frame = new S_ApplyRecipe();
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
	public S_ApplyRecipe() {
		try {
			socket=new Socket("192.168.208.148",5003);
			
			os=socket.getOutputStream();			
			oos=new ObjectOutputStream(os);			
			is=socket.getInputStream();			
			ois=new ObjectInputStream(is);
			String str="A";
			oos.writeObject(str);
            oos.flush();
            applications=(ArrayList <Applications>) ois.readObject();
			stu.InsertApplications(applications);
			applicationslist=(ArrayList <ApplicationsList>) ois.readObject();
			stu.InsertApplicationsList(applicationslist);
			menu=(ArrayList <Menu>) ois.readObject();
			stu.InsertMenu(menu);
			menulist=(ArrayList <MenuList>) ois.readObject();
			stu.InsertMenuList(menulist);
			
		setTitle("\uC2DD\uC7AC\uB8CC \uC2E0\uCCAD");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 521);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("< ½Å Ã» >");
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label.setBounds(227, 12, 89, 41);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("\uC774\uB984 :");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		lblNewLabel.setBounds(42, 76, 55, 34);
		panel.add(lblNewLabel);
		
		name = new JTextField();
		name.setBounds(109, 84, 141, 24);
		panel.add(name);
		name.setColumns(10);
		
		phon = new JTextField();
		phon.setColumns(10);
		phon.setBounds(109, 142, 141, 24);
		panel.add(phon);
		
		JLabel label_1 = new JLabel("\uC804\uD654\uBC88\uD638 :");
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		label_1.setBounds(14, 134, 83, 34);
		panel.add(label_1);
		
		day = new JTextField();
		day.setColumns(10);
		day.setBounds(109, 201, 141, 24);
		panel.add(day);
		
		JLabel label_2 = new JLabel("\uB0A0\uC9DC :");
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		label_2.setBounds(42, 193, 55, 34);
		panel.add(label_2);
		
		place = new JTextField();
		place.setColumns(10);
		place.setBounds(109, 261, 141, 24);
		panel.add(place);
		
		JLabel label_3 = new JLabel("\uC7A5\uC18C :");
		label_3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		label_3.setBounds(42, 253, 55, 34);
		panel.add(label_3);
		
		JLabel lblNewLabel_1 = new JLabel("\uC218\uB7C9");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(353, 202, 48, 23);
		panel.add(lblNewLabel_1);
		
		menu1 = new JTextField();
		menu1.setBounds(415, 201, 69, 24);
		panel.add(menu1);
		menu1.setColumns(10);
		
		JButton sumbtn = new JButton("\uD569\uACC4");
		sumbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ÇÕ°è
				
				try {
					
				String menuName=menu_name.getText();
				
				String menuID = stu.MenuID(menuName);
				
				double amount=Double.parseDouble(menu1.getText());
				
				double total=stu.TotalPrice(menuID, amount);
				price.setText(Double.toString(total));
				
				}
				
				catch(Exception e) { System.err.println(e);System.out.println("a"); }
			}
		});
		sumbtn.setBounds(379, 260, 105, 27);
		panel.add(sumbtn);
		
		JLabel lblNewLabel_2 = new JLabel("\uD569\uACC4 : ");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(57, 352, 55, 37);
		panel.add(lblNewLabel_2);
		
		price = new JTextField();
		price.setBounds(126, 354, 293, 35);
		panel.add(price);
		price.setColumns(10);
		
		JButton applybtn = new JButton("\uC2E0\uCCAD");
		applybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Ãß°¡
				try {
					Applications app = new Applications(place.getText(),Integer.parseInt(day.getText()),name.getText(),phon.getText());
					oos.writeObject(app);
					oos.flush();				
					int appNum = app.getAppNum();
					String menuName=menu_name.getText();					
					String menuID = stu.MenuID(menuName);					
					double amount=Double.parseDouble(menu1.getText());					
					double total=stu.TotalPrice(menuID, amount);					
					ApplicationsList app2 = new ApplicationsList(appNum, menuID,amount,total);
					oos.writeObject(app2);
					oos.flush();					
				}
				catch(UnknownHostException e) { System.err.println(e);System.out.println("1"); }
				catch(IOException e) { System.err.println(e);System.out.println("2"); }
				catch(Exception e) { System.err.println(e); System.out.println("3");}

				dispose();
			}
		});
		applybtn.setBounds(436, 435, 105, 27);
		panel.add(applybtn);
		
		JLabel label_6 = new JLabel("\uC6D0");
		label_6.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		label_6.setBounds(420, 352, 55, 37);
		panel.add(label_6);
		
		JLabel label_4 = new JLabel("\uBA54\uB274\uBA85");
		label_4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		label_4.setBounds(350, 140, 69, 24);
		panel.add(label_4);
		
		menu_name = new JTextField();
		menu_name.setBounds(414, 142, 116, 24);
		panel.add(menu_name);
		menu_name.setColumns(10);
		
		
	}
		catch(UnknownHostException e) { System.err.println(e);System.out.println("1"); }
		catch(IOException e) { System.err.println(e);System.out.println("2"); }
		catch(Exception e) { System.err.println(e);System.out.println("3"); }
	}
}
