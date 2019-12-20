package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CookingClass.Client.TeacherMain;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class T_ShowRequires extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField start;
	private JTextField end;
	private JTextField day;
	private DefaultTableModel dmt1;
	private DefaultTableModel dmt2;
	Socket socket=null;
	OutputStream os=null;
	ObjectOutputStream oos=null;
	InputStream is=null;
	ObjectInputStream ois=null;
	TeacherMain tea=new TeacherMain();
	ArrayList <Menu> menu = new ArrayList <Menu> ();
	ArrayList <MenuList> menulist = new ArrayList <MenuList> ();
	totalMenus t=null;
	ArrayList <Recipe> recipe = new ArrayList <Recipe> ();
	ArrayList <Reserve> reserve = new ArrayList <Reserve> ();
	ArrayList <Applications> applications = new ArrayList <Applications> ();
	ArrayList <totalApplications> a = new ArrayList <totalApplications> ();
	ArrayList <ApplicationsList> applicationslist = new ArrayList <ApplicationsList> ();
	ArrayList <totalRequires> tr=new ArrayList <totalRequires> ();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					T_ShowRequires frame = new T_ShowRequires();
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
	public T_ShowRequires() {
		try {
			socket=new Socket("192.168.208.148",5002);
			os=socket.getOutputStream();
			oos=new ObjectOutputStream(os);
			is=socket.getInputStream();
			ois=new ObjectInputStream(is);
		String str="R";
		oos.writeObject(str);
		oos.flush();
		
		applications=(ArrayList <Applications>) ois.readObject();
		tea.InsertApplications(applications);
		
		menulist=(ArrayList <MenuList>) ois.readObject();
		tea.InsertMenuList(menulist);
		
		recipe=(ArrayList <Recipe>) ois.readObject();
		tea.InsertRecipe(recipe);
		
		reserve=(ArrayList <Reserve>) ois.readObject();
		
		tea.InsertReserve(reserve);
		
		applicationslist=(ArrayList <ApplicationsList>) ois.readObject();
		tea.InsertApplicationsList(applicationslist);
		setTitle("\uC2DD\uC7AC\uB8CC \uC18C\uC694\uB7C9 \uBAA9\uB85D");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<\uC2DD\uC7AC\uB8CC \uC18C\uC694\uB7C9 \uBAA9\uB85D(\uC77C\uC790\uBCC4,\uAE30\uAC04\uBCC4)>");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		lblNewLabel.setBounds(238, 0, 384, 27);
		contentPane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 782, 503);
		contentPane.add(tabbedPane);
		
		Panel panel = new Panel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("ÀÏÀÚº°", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(166, 0, 611, 471);
		panel.add(scrollPane);
		dmt1=new DefaultTableModel(
				new Object[][] {
		},
		new String[] {
			"\uC2DD\uC7AC\uB8CC \uBA85", "\uCD1D \uC18C\uC694\uB7C9", "\uB2E8\uC704"
		}
	);
		table = new JTable(dmt1);
		table.setModel(dmt1);
		scrollPane.setViewportView(table);
		
		day = new JTextField();
		day.setBounds(14, 40, 116, 24);
		panel.add(day);
		day.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\uB0A0\uC9DC");
		lblNewLabel_3.setBounds(14, 8, 62, 18);
		panel.add(lblNewLabel_3);
		
		JButton daybtn = new JButton("\uAC80\uC0C9");
		daybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dmt1.setRowCount(0);
				int startdate=Integer.parseInt(day.getText());
				System.out.println(startdate);
				tr=tea.showRequires(startdate);
				System.out.println(tr.size());
				int i=0;
				while(true)
				{
				
				if(i>=tr.size()){break;}
				dmt1.addRow(new Object[] {tr.get(i).getName(),tr.get(i).getAmount(),tr.get(i).getUnit()});
				i=i+1;
				}
				
			}
		});
		daybtn.setBounds(25, 135, 105, 27);
		panel.add(daybtn);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("±â°£º°", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(166, 0, 611, 471);
		panel_1.add(scrollPane_1);
		
		dmt2=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"\uC2DD\uC7AC\uB8CC \uBA85", "\uCD1D \uC18C\uC694\uB7C9", "\uB2E8\uC704"
				});
		table_1 = new JTable(dmt2);
		table_1.setModel(dmt2);
		scrollPane_1.setViewportView(table_1);
		
		start = new JTextField();
		start.setBounds(14, 33, 116, 24);
		panel_1.add(start);
		start.setColumns(10);
		
		end = new JTextField();
		end.setBounds(14, 98, 116, 24);
		panel_1.add(end);
		end.setColumns(10);
		
		JButton btnNewButton = new JButton("\uC870\uD68C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dmt2.setRowCount(0);
				int startdate=Integer.parseInt(start.getText());
				int enddate=Integer.parseInt(end.getText());
				tr=tea.showRequires(startdate,enddate);
				System.out.println(tr.size());
				int i=0;
				while(true)
				{
				dmt2.addRow(new Object[] {tr.get(i).getName(),tr.get(i).getAmount(),tr.get(i).getUnit()});
				i++;
				if(i>=tr.size())
				{break;}
				}
				
			}
		});
		btnNewButton.setBounds(25, 237, 105, 27);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("~");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		lblNewLabel_2.setBounds(65, 68, 20, 18);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("\uAE30\uAC04");
		lblNewLabel_1.setBounds(14, 8, 37, 18);
		panel_1.add(lblNewLabel_1);
		
	
	}
		catch(UnknownHostException e) { System.err.println(e); }
		catch(IOException e) { System.err.println(e); }
		catch(Exception e) { System.err.println(e); }
	}
}
