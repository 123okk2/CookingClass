package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA_2_3.portable.OutputStream;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class M_loadRecipe_ReserveAdd extends JFrame {

	private JPanel contentPane;
	private JTextField reserve_id;
	private JTextField reserve_name;
	private JTextField unit;
	ArrayList<Reserve> reserve = new ArrayList<Reserve>(); // ½ÄÀç·á
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
					M_loadRecipe_ReserveAdd frame = new M_loadRecipe_ReserveAdd();
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
	public M_loadRecipe_ReserveAdd() {
		

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
		
		setTitle("\uC2DD\uC7AC\uB8CC \uCD94\uAC00");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC2DD\uC7AC\uB8CCID : ");
		lblNewLabel.setBounds(45, 45, 82, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC2DD\uC7AC\uB8CC\uBA85 : ");
		lblNewLabel_1.setBounds(45, 106, 82, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uB2E8  \uC704 : ");
		lblNewLabel_2.setBounds(45, 166, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		reserve_id = new JTextField();
		reserve_id.setBounds(176, 42, 116, 22);
		contentPane.add(reserve_id);
		reserve_id.setColumns(10);
		
		reserve_name = new JTextField();
		reserve_name.setBounds(176, 103, 116, 22);
		contentPane.add(reserve_name);
		reserve_name.setColumns(10);
		
		unit = new JTextField();
		unit.setBounds(176, 166, 116, 22);
		contentPane.add(unit);
		unit.setColumns(10);
		
		JButton btnNewButton = new JButton("\uCD94   \uAC00");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rID = reserve_id.getText();
				String rName = reserve_name.getText();
				String u = unit.getText();
				
				try {
					oos.writeObject("rs");
					oos.flush();
					reserve = insertReserve(rID,rName,u);
					oos.writeObject(reserve);
					oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}

			private ArrayList<Reserve> insertReserve(String rID, String rName, String u) {
				Reserve tmp = new Reserve(rID, rName, u);
				reserve.add(tmp);
				
				return reserve;
			}
		});
		btnNewButton.setBounds(338, 102, 82, 25);
		contentPane.add(btnNewButton);
	}

}
