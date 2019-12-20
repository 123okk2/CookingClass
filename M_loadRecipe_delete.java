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

public class M_loadRecipe_delete extends JFrame {

	private JPanel contentPane;
	private JTextField menu_id;
	private JTextField reserve_id;
	ArrayList<Recipe> r = new ArrayList<Recipe>();
	ArrayList<Recipe> emptyR = new ArrayList<Recipe>();
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
					M_loadRecipe_delete frame = new M_loadRecipe_delete();
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
	public M_loadRecipe_delete() {
		
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
		
		setTitle("\uC0AD\uC81C");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC2DD\uB2E8 ID :");
		lblNewLabel.setBounds(33, 75, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC2DD\uC7AC\uB8CC ID : ");
		lblNewLabel_1.setBounds(33, 147, 79, 16);
		contentPane.add(lblNewLabel_1);
		
		menu_id = new JTextField();
		menu_id.setBounds(139, 72, 116, 22);
		contentPane.add(menu_id);
		menu_id.setColumns(10);
		
		reserve_id = new JTextField();
		reserve_id.setBounds(139, 144, 116, 22);
		contentPane.add(reserve_id);
		reserve_id.setColumns(10);
		
		JButton btnNewButton = new JButton("\uC0AD   \uC81C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mID = menu_id.getText();
				String rID = reserve_id.getText();
				
				try {
					oos.writeObject("delete");
					oos.flush();

					emptyR = deleteRecipe(mID, rID);
					oos.writeObject(emptyR);
					oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

			private ArrayList<Recipe> deleteRecipe(String mID, String rID) {
				for(Recipe tmp: r) {
					if(mID.equals(tmp.getMenuID())&&rID.equals(tmp.getReserveID())) {
						r.remove(tmp);
						Recipe sendR = new Recipe(mID, rID, tmp.getRequires());
						emptyR.add(sendR);
						break;
					}
					return emptyR;
				}
				return null;
			}
		});
		btnNewButton.setBounds(313, 107, 79, 25);
		contentPane.add(btnNewButton);
	}

}
