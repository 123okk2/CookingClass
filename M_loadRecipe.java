package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import CookingClass.*;
import CookingClass.Client.Manager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Panel;

public class M_loadRecipe extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel dtm;
	private DefaultTableModel dtm2;
	private DefaultTableModel dtm3;
	
//	Socket socket=null;
//	OutputStream os=null;
//	ObjectOutputStream oos=null;
//	InputStream is=null;
//	ObjectInputStream ois=null;
//	
	
	private int Sel_Menu=1;
	ArrayList <MenuList> menulist = new ArrayList <MenuList> ();
	ArrayList <Recipe> recipe = new ArrayList <Recipe> ();
	ArrayList <Reserve> reserve = new ArrayList <Reserve> ();
	ArrayList <Schedule> schedule = new ArrayList <Schedule> ();
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M_loadRecipe frame = new M_loadRecipe();
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
	public M_loadRecipe() {
	
		
//		try {
//			socket=new Socket("192.168.208.148",5001);
//			oos=new ObjectOutputStream(os);
//			os=socket.getOutputStream();
//			is=socket.getInputStream();
//			ois=new ObjectInputStream(is);
//			Manager man=new Manager();
//	    				Sel_Menu=Manager.SelectMenu();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
    	
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		try {
//			reserve = (ArrayList<Reserve>) ois.readObject();
//			menulist = (ArrayList<MenuList>) ois.readObject();
//			recipe = (ArrayList<Recipe>) ois.readObject();
//			
//			
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
		//loadRecipe(recipe, menulist, reserve);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 109, 23);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("\uBA54\uB274");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("\uCD94\uAC00");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\uC2DD\uB2E8");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_loadRecipe_menuAdd secondFrame = new M_loadRecipe_menuAdd();
				secondFrame.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\uC2DD\uC7AC\uB8CC");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_loadRecipe_ReserveAdd secondFrame_2 = new M_loadRecipe_ReserveAdd();
				secondFrame_2.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\uB808\uC2DC\uD53C");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_loadRecipe_RecipeAdd secondFrame_3 = new M_loadRecipe_RecipeAdd();
				secondFrame_3.setVisible(true);
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\uC218\uC815");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_loadRecipe_modify secondFrame_4 = new M_loadRecipe_modify();
				secondFrame_4.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\uC0AD\uC81C");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				M_loadRecipe_delete secondFrame_5 = new M_loadRecipe_delete();
				secondFrame_5.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
		
		Panel panel = new Panel();
		panel.setBounds(10, 60, 419, 189);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		dtm = new DefaultTableModel(
				new Object[][] {
		},
		new String[] {
			"\uC2DD\uB2E8ID", "\uC2DD\uB2E8\uBA85", "\uC2DD\uC7AC\uB8CC\uBE44"
		});
		table = new JTable(dtm);
		table.setModel(dtm);
		scrollPane.setViewportView(table);
		
		
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(10, 275, 419, 189);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		dtm2=new DefaultTableModel(
				new Object[][] {
		},
		new String[] {
			"\uC2DD\uC7AC\uB8CCID", "\uC2DD\uB2E8\uBA85", "\uB2E8\uC704"
		});
		table_1 = new JTable(dtm2);
		table_1.setModel(dtm2);
		scrollPane_1.setViewportView(table_1);
		
		Panel panel_2 = new Panel();
		panel_2.setBounds(468, 60, 306, 412);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2, BorderLayout.CENTER);
		
		table_2 = new JTable(dtm3);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\uC2DD\uB2E8ID", "\uC2DD\uC7AC\uB8CCID", "\uC18C\uC694\uB7C9"
			}
		));
		scrollPane_2.setViewportView(table_2);
		dtm3=new DefaultTableModel(new Object[][] {
		},
		new String[] {
			"\uC2DD\uC7AC\uB8CCID", "\uC2DD\uB2E8\uBA85", "\uB2E8\uC704"
		});
		
		
		for(int i = 0; i<=menulist.size();i++)
		{
			dtm.addRow(new Object[] {menulist.get(i).getMenuID(),menulist.get(i).getMenuName(),menulist.get(i).getPrice()});
		}
		
		for(int i = 0; i<=recipe.size();i++)
		{
			dtm2.addRow(new Object[] {recipe.get(i).getMenuID(),recipe.get(i).getReserveID(),recipe.get(i).getRequires()});
		}
		
		for(int i = 0; i<=reserve.size();i++)
		{
			dtm3.addRow(new Object[] {reserve.get(i).getReserveID(),reserve.get(i).getReserveName(),reserve.get(i).getUnit(),reserve.get(i).toString()});
		}
	}
}
