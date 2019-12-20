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

import CookingClass.Client.StudentMain;
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
import java.awt.event.ActionEvent;

public class S_ShowMenu extends JFrame {

	private JPanel contentPane;
	private JTextField day;
	private JTable table;
	private DefaultTableModel dtm;
	Socket socket=null;
	OutputStream os=null;
	ObjectOutputStream oos=null;
	InputStream is=null;
	ObjectInputStream ois=null;
	StudentMain stu=new StudentMain();
	ArrayList <MenuList> menuList=new ArrayList<MenuList>(); 
   	ArrayList <Reserve> reserves=new ArrayList<Reserve>();
   	ArrayList <Recipe> recipes=new ArrayList<Recipe> ();
    ArrayList <Menu> menu=new ArrayList<Menu> ();
   	ArrayList <Applications> application=new ArrayList<Applications> ();
   	
   	ArrayList <ApplicationsList> applicationList=new ArrayList<ApplicationsList> ();
   	ArrayList<Show_menu> sm = new ArrayList<Show_menu>();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					S_ShowMenu frame = new S_ShowMenu();
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
	public S_ShowMenu() {	
			
		setTitle("\uC2E4\uC2B5\uBA54\uB274 \uC870\uD68C");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\uC77C\uC790 :");
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label.setBounds(125, 12, 64, 28);
		contentPane.add(label);
		
		day = new JTextField();
		day.setColumns(10);
		day.setBounds(202, 12, 280, 28);
		contentPane.add(day);
		
		JButton button = new JButton("\uD655\uC778");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					socket=new Socket("192.168.208.148",5003);
					
					os=socket.getOutputStream();
					
					oos=new ObjectOutputStream(os);
					
					is=socket.getInputStream();
					
					ois=new ObjectInputStream(is);	
					String str="M";
					oos.writeObject(str);
                    oos.flush();
                    menu=(ArrayList <Menu>) ois.readObject();
                 	stu.InsertMenu(menu);
                 	menuList=(ArrayList <MenuList>) ois.readObject();
                 	stu.InsertMenuList(menuList);
                 	String[] name=new String[3];
                    double[] price=new double[3];
                    int check=0;
                    int count=0;
                    String branch="";
                    String a="";                    
                    for (int i = 0; i < menu.size();i++ ){
                       if(menu.get(i).getDate() == Integer.parseInt(day.getText())){
                    	   if(check == 0) {
                    		   a=menu.get(i).getBranch();              
                    		   check++;
                    		   }
                    	   for(int j = 0; j < menuList.size();j++){
                 				if(menuList.get(j).getMenuID().equals(menu.get(i).getMenuID())){
                 					name[count]=menuList.get(j).getMenuName();
                 					price[count]=+menuList.get(j).getPrice();
                 					count++;
						break;
                 				}
              			}
                       }
                       
        		}
                    dtm.addRow(new Object[]{day.getText(),a,name[0],price[0],name[1],price[1],name[2],price[2]});
                     //sm.add(new Show_menu(Integer.parseInt(day.getText()),branch,names[0],prices[0],names[1],prices[1],names[2],prices[2]));
                    
				socket.close();
				}
				catch(UnknownHostException e) { System.err.println(e); }
				catch(IOException e) { System.err.println(e); }
				catch(Exception e) { System.err.println(e); }
				
			}
		});
		button.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		button.setBounds(496, 13, 88, 27);
		contentPane.add(button);
		
		JLabel lblEx = new JLabel("ex)181222");
		lblEx.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		lblEx.setBounds(431, 63, 165, 28);
		contentPane.add(lblEx);
		
		Panel panel = new Panel();
		panel.setBounds(78, 164, 647, 311);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		dtm = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"\uB0A0\uC9DC", "\uC7A5\uC18C", "\uBA54\uB274 1", "\uBA54\uB2741 \uAC00\uACA9", "\uBA54\uB274 2", "\uBA54\uB2742 \uAC00\uACA9", "\uBA54\uB274 3", "\uBA54\uB2743 \uAC00\uACA9"
				}); 
		table = new JTable(dtm);
		table.setModel(dtm);
		scrollPane.setViewportView(table);
	
		
	}
}
