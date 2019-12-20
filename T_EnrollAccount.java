package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CookingClass.Client.TeacherMain;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class T_EnrollAccount extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel dtm;
	Socket socket=null;
	OutputStream os=null;
	ObjectOutputStream oos=null;
	InputStream is=null;
	ObjectInputStream ois=null;
	TeacherMain tea=new TeacherMain();
	private JTextField number;
	private JTextField price;
	private JTextField day;
	ArrayList <Applications> applications = new ArrayList <Applications> ();
	ArrayList <Applications> a = new ArrayList <Applications> ();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					T_EnrollAccount frame = new T_EnrollAccount();
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
	public T_EnrollAccount() {
		try {
			socket=new Socket("192.168.208.148",5002);
			os=socket.getOutputStream();
			oos=new ObjectOutputStream(os);
			is=socket.getInputStream();
			ois=new ObjectInputStream(is);
			String str="E";
			oos.writeObject(str);
			oos.flush();
			applications=(ArrayList <Applications>) ois.readObject();
			tea.InsertApplications(applications);
			a=tea.showDepositedApplicationss();		
		setTitle("\uC774\uCCB4\uB0B4\uC5ED \uB4F1\uC7AC");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 12, 777, 304);
		contentPane.add(scrollPane);
		dtm=new DefaultTableModel(
				new Object[][] {
		},
		new String[] {
			"\uC2E0\uCCAD\uBC88\uD638", "\uC218\uC5C5\uC77C", "\uC2E0\uCCAD\uC790\uBA85", "\uC804\uD654\uBC88\uD638", "\uC785\uAE08\uC77C", "\uC785\uAE08\uC561"
		});
		table = new JTable(dtm);
		table.setModel(dtm);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=0;
				while(true) {
				dtm.addRow(new Object[] {a.get(i).getAppNum(),a.get(i).getDate(),a.get(i).getStudentName(),a.get(i).getPhoneNum(),a.get(i).getDepositDay(),a.get(i).getDepositMoney()});
				i++;
				if(i>=a.size()) {break;}
					
				}
			}
		});
		btnNewButton.setBounds(679, 328, 89, 27);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("\uC2E0\uCCAD\uBC88\uD638 :");
		label.setBounds(14, 395, 71, 18);
		contentPane.add(label);
		
		number = new JTextField();
		number.setBounds(94, 392, 116, 24);
		contentPane.add(number);
		number.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\uC785\uAE08\uC561 ");
		lblNewLabel.setBounds(246, 395, 53, 18);
		contentPane.add(lblNewLabel);
		
		price = new JTextField();
		price.setBounds(313, 392, 116, 24);
		contentPane.add(price);
		price.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\uC785\uAE08\uB0A0\uC9DC");
		lblNewLabel_1.setBounds(485, 395, 62, 18);
		contentPane.add(lblNewLabel_1);
		
		day = new JTextField();
		day.setBounds(561, 392, 116, 24);
		contentPane.add(day);
		day.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("\uB4F1\uB85D");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int[] num = new int[3];
				num[0]=Integer.parseInt(number.getText());
				num[1]=Integer.parseInt(price.getText());
				num[2]=Integer.parseInt(day.getText());
				try {
				oos.writeObject(num);
				oos.flush();
				applications=(ArrayList <Applications>) ois.readObject();
				tea.InsertApplications(applications);
				a=tea.showDepositedApplicationss();	
				int i=0;
				while(true) {
				dtm.addRow(new Object[] {a.get(i).getAppNum(),a.get(i).getDate(),a.get(i).getStudentName(),a.get(i).getPhoneNum(),a.get(i).getDepositDay(),a.get(i).getDepositMoney()});
				i++;
				if(i>=a.size()) {break;}
					
				}
				}
				catch(UnknownHostException e1) { System.err.println(e1); }
				catch(IOException e1) { System.err.println(e1); }
				catch(Exception e1) { System.err.println(e1); }
			}
		});
		btnNewButton_1.setBounds(672, 448, 105, 27);
		contentPane.add(btnNewButton_1);
		int i=0;
		while(true) {
		dtm.addRow(new Object[] {a.get(i).getAppNum(),a.get(i).getDate(),a.get(i).getStudentName(),a.get(i).getPhoneNum(),a.get(i).getDepositDay(),a.get(i).getDepositMoney()});
		i++;
		if(i>=a.size()) {break;}
			
		}
	}
		catch(UnknownHostException e) { System.err.println(e); }
		catch(IOException e) { System.err.println(e); }
		catch(Exception e) { System.err.println(e); }
		
	}
}
