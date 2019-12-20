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

public class M_loadRecipe_modify extends JFrame {

	private JPanel contentPane;
	private JTextField menu_id;
	private JTextField reserve_id;
	private JTextField use;
	ArrayList<Recipe> recipe = new ArrayList<Recipe>();
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
					M_loadRecipe_modify frame = new M_loadRecipe_modify();
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
	public M_loadRecipe_modify() {
		

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
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC2DD\uB2E8ID  : ");
		lblNewLabel.setBounds(61, 35, 69, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC2DD\uC7AC\uB8CCID  : ");
		lblNewLabel_1.setBounds(61, 94, 81, 16);
		contentPane.add(lblNewLabel_1);
		
		menu_id = new JTextField();
		menu_id.setBounds(156, 32, 116, 22);
		contentPane.add(menu_id);
		menu_id.setColumns(10);
		
		reserve_id = new JTextField();
		reserve_id.setBounds(156, 91, 116, 22);
		contentPane.add(reserve_id);
		reserve_id.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uC218\uC815\uD560 \uC18C\uC694\uB7C9\uAC12  : ");
		lblNewLabel_2.setBounds(33, 150, 129, 16);
		contentPane.add(lblNewLabel_2);
		
		use = new JTextField();
		use.setBounds(156, 147, 116, 22);
		contentPane.add(use);
		use.setColumns(10);
		
		JButton btnNewButton = new JButton("\uC218  \uC815");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String m = menu_id.getText();
				String g = reserve_id.getText();
				double q = Double.parseDouble(use.getText());
				
				try {
					oos.writeObject("modify");
					oos.flush();
					recipe = modifyRecipe(m,g,q);
					oos.writeObject(recipe);
					oos.flush();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//setVisible(false);
			}

			private ArrayList<Recipe> modifyRecipe(String m, String g, double q) {
				for (Recipe tmp : recipe) {
					if (m.equals(tmp.getMenuID()) && g.equals(tmp.getReserveID())) {
						recipe.clear(); // ºó arrayList
						Recipe tmp1 = new Recipe(m, g, q);
						recipe.add(tmp1);
						return recipe;
					}
				}
				return null;
			}
		});
		btnNewButton.setBounds(314, 205, 89, 25);
		contentPane.add(btnNewButton);
	}

}
