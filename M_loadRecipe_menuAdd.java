package CookingClass;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA_2_3.portable.OutputStream;

import CookingClass.Client.Manager;

public class M_loadRecipe_menuAdd extends JFrame {

	private JPanel contentPane;
	private JTextField menu_id;
	private JTextField menu_name;
	private JTextField reserve_m;
	ArrayList <MenuList> menulist = new ArrayList<MenuList>();
	Socket socket=null;
	OutputStream os=null;
	ObjectOutputStream oos=null;
	InputStream is=null;
	ObjectInputStream ois=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M_loadRecipe_menuAdd frame = new M_loadRecipe_menuAdd();
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
	public M_loadRecipe_menuAdd() {
		try {
			socket=new Socket("192.168.208.148",5001);
			os=(OutputStream) socket.getOutputStream();
			oos=new ObjectOutputStream(os);
			is=socket.getInputStream();
			ois=new ObjectInputStream(is);
		
	    				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		setTitle("\uC2DD\uB2E8 \uCD94\uAC00");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menu_id = new JTextField();
		menu_id.setBounds(144, 53, 116, 22);
		contentPane.add(menu_id);
		menu_id.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\uC2DD\uB2E8ID : ");
		lblNewLabel.setBounds(58, 56, 61, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\uCD94  \uAC00");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mID = menu_id.getText();
				String mName = menu_name.getText();
				int p = Integer.parseInt(reserve_m.getText());
				
				try {
					oos.writeObject("ml");
					oos.flush();
					menulist = insertMenuList(mID, mName, p);
					oos.writeObject(menulist);
					oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			}

			private ArrayList<MenuList> insertMenuList(String mID, String mName, int p) {
				MenuList tmp = new MenuList(mID, mName, p);
				menulist.add(tmp);
				return menulist;
			}
		});
		btnNewButton.setBounds(315, 109, 78, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("\uC2DD\uB2E8\uBA85 :");
		lblNewLabel_1.setBounds(58, 113, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		menu_name = new JTextField();
		menu_name.setBounds(144, 110, 116, 22);
		contentPane.add(menu_name);
		menu_name.setColumns(10);
		
		reserve_m = new JTextField();
		reserve_m.setBounds(144, 173, 116, 22);
		contentPane.add(reserve_m);
		reserve_m.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uC2DD\uC7AC\uB8CC\uBE44 : ");
		lblNewLabel_2.setBounds(58, 176, 72, 16);
		contentPane.add(lblNewLabel_2);
	}
}
