package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Panel;
import javax.swing.border.TitledBorder;

import CookingClass.Client.Manager;

import java.awt.Label;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
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
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class M_loadRecipe2 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField a_id;
	private JTextField a_name;
	private JTextField a_price;
	private JTextField b_id;
	private JTextField b_name;
	private JTextField b_unit;
	private JTextField c_id;
	private JTextField c_id2;
	private JTextField c_amount;
	Socket socket = null;
	OutputStream os = null;
	ObjectOutputStream oos = null;
	InputStream is = null;
	ObjectInputStream ois = null;
	ArrayList<Menu> menu = new ArrayList<Menu>();
	ArrayList<MenuList> menulist = new ArrayList<MenuList>();
	ArrayList<Applications> applications = new ArrayList<Applications>();
	ArrayList<ApplicationsList> applicationslist = new ArrayList<ApplicationsList>();
	ArrayList<Recipe> recipe = new ArrayList<Recipe>();
	ArrayList<Reserve> reserve = new ArrayList<Reserve>();
	ArrayList<Schedule> schedule = new ArrayList<Schedule>();
	ArrayList<MenuList> ml = new ArrayList<MenuList>();
	ArrayList<Reserve> rs = new ArrayList<Reserve>();
	ArrayList<Recipe> r = new ArrayList<Recipe>(); // ·¹½ÃÇÇ
	Manager m;
	private JTextField id;
	private JTextField id2;
	private JTextField amount;
	private JTextField d_id;
	private JTextField d_id2;
	private JTable table_1;
	private JTable table_2;
	private DefaultTableModel dtm;
	private DefaultTableModel dtm2;
	private DefaultTableModel dtm3;
	JRadioButton rdbtnNewRadioButton;
	JRadioButton radioButton;
	JRadioButton radioButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M_loadRecipe2 frame = new M_loadRecipe2();
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
	public M_loadRecipe2() {
		try {
			socket = new Socket("192.168.208.148", 5001);
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			Manager man = new Manager();
			String str = "R";
			oos.writeObject(str);
			oos.flush();
			// ·¹½ÃÇÇ, ½Ä´Ü, ½ÄÀç·á ¹Þ¾Æ¿È
			reserve = (ArrayList<Reserve>) ois.readObject();
			menulist = (ArrayList<MenuList>) ois.readObject();
			recipe = (ArrayList<Recipe>) ois.readObject();
			man.loadRecipe(recipe, menulist, reserve);

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 629, 612);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(5, 5, 608, 555);
			contentPane.add(tabbedPane);

			JPanel panel_1 = new JPanel();
			tabbedPane.addTab("Ãß°¡", null, panel_1, null);
			panel_1.setLayout(null);

			JPanel panel_5 = new JPanel();
			panel_5.setBounds(0, 0, 581, 538);
			panel_1.add(panel_5);
			panel_5.setLayout(null);

			JPanel panel_6 = new JPanel();
			panel_6.setBorder(new TitledBorder(null, "\uC2DD\uB2E8 \uCD94\uAC00", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panel_6.setBounds(0, 0, 567, 160);
			panel_5.add(panel_6);
			panel_6.setLayout(null);

			rdbtnNewRadioButton = new JRadioButton("\uC120\uD0DD");
			rdbtnNewRadioButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (rdbtnNewRadioButton.isEnabled() == true) {
						a_id.setEditable(true);
						a_name.setEditable(true);
						a_price.setEditable(true);
						b_id.setEditable(false);
						b_name.setEditable(false);
						b_unit.setEditable(false);
						c_id.setEditable(false);
						c_id2.setEditable(false);
						c_amount.setEditable(false);
					}
				}
			});
			rdbtnNewRadioButton.setBounds(10, 24, 51, 25);
			panel_6.add(rdbtnNewRadioButton);

			Label label = new Label("\uC2DD\uB2E8ID");
			label.setBounds(97, 24, 70, 24);
			panel_6.add(label);

			Label label_1 = new Label("\uAC00\uACA9");
			label_1.setBounds(97, 80, 70, 24);
			panel_6.add(label_1);

			Label label_2 = new Label("\uC2DD\uB2E8\uBA85");
			label_2.setBounds(322, 25, 70, 24);
			panel_6.add(label_2);

			JButton btnNewButton = new JButton("\uB4F1\uB85D");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ½Ä´Üµî·Ï
					try {
						oos.writeObject("insert");
						oos.flush();
						oos.writeObject("ml");
						oos.flush();
						ml.clear();
						MenuList tmp = new MenuList(a_id.getText(), a_name.getText(),
								Integer.parseInt(a_price.getText()));
						ml.add(tmp);
						System.out.println(ml.size());
						oos.writeObject(ml);
						oos.flush();
						dispose();
					} catch (UnknownHostException e1) {
						System.err.println(e1);
					} catch (IOException e1) {
						System.err.println(e1);
					} catch (Exception e1) {
						System.err.println(e1);
					}

				}
			});
			btnNewButton.setBounds(452, 101, 101, 25);
			panel_6.add(btnNewButton);

			a_id = new JTextField();
			a_id.setEditable(false);
			a_id.setBounds(173, 25, 116, 22);
			panel_6.add(a_id);
			a_id.setColumns(10);

			a_name = new JTextField();
			a_name.setEditable(false);
			a_name.setBounds(404, 25, 116, 22);
			panel_6.add(a_name);
			a_name.setColumns(10);

			a_price = new JTextField();
			a_price.setEditable(false);
			a_price.setBounds(173, 82, 116, 22);
			panel_6.add(a_price);
			a_price.setColumns(10);

			JPanel panel_7 = new JPanel();
			panel_7.setLayout(null);
			panel_7.setBorder(
					new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC2DD\uC7AC\uB8CC \uCD94\uAC00",
							TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_7.setBounds(0, 184, 567, 153);
			panel_5.add(panel_7);

			radioButton = new JRadioButton("\uC120\uD0DD");
			radioButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (radioButton.isEnabled() == true) {
						a_id.setEditable(false);
						a_name.setEditable(false);
						a_price.setEditable(false);
						b_id.setEditable(true);
						b_name.setEditable(true);
						b_unit.setEditable(true);
						c_id.setEditable(false);
						c_id2.setEditable(false);
						c_amount.setEditable(false);
					}
				}
			});
			radioButton.setBounds(10, 24, 51, 25);
			panel_7.add(radioButton);

			Label label_3 = new Label("\uC2DD\uC7AC\uB8CCID");
			label_3.setBounds(97, 24, 70, 24);
			panel_7.add(label_3);

			Label label_4 = new Label("\uB2E8\uC704");
			label_4.setBounds(97, 80, 70, 24);
			panel_7.add(label_4);

			Label label_5 = new Label("\uC2DD\uC7AC\uB8CC\uBA85");
			label_5.setBounds(322, 25, 70, 24);
			panel_7.add(label_5);

			JButton button = new JButton("\uB4F1\uB85D");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ½ÄÀç·á µî·Ï
					try {
						oos.writeObject("insert");
						oos.flush();
						oos.writeObject("rs");
						oos.flush();
						Reserve tmp = new Reserve(b_id.getText(), b_name.getText(), b_unit.getText());
						rs.add(tmp);
						oos.writeObject(rs);
						oos.flush();
						dispose();
					} catch (UnknownHostException e1) {
						System.err.println(e1);
					} catch (IOException e1) {
						System.err.println(e1);
					} catch (Exception e1) {
						System.err.println(e1);
					}

				}
			});
			button.setBounds(452, 101, 101, 25);
			panel_7.add(button);

			b_id = new JTextField();
			b_id.setColumns(10);
			b_id.setBounds(173, 25, 116, 22);
			panel_7.add(b_id);

			b_name = new JTextField();
			b_name.setColumns(10);
			b_name.setBounds(404, 25, 116, 22);
			panel_7.add(b_name);

			b_unit = new JTextField();
			b_unit.setColumns(10);
			b_unit.setBounds(173, 82, 116, 22);
			panel_7.add(b_unit);

			JPanel panel_8 = new JPanel();
			panel_8.setLayout(null);
			panel_8.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Recipe \uCD94\uAC00",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_8.setBounds(0, 374, 581, 153);
			panel_5.add(panel_8);

			radioButton_1 = new JRadioButton("\uC120\uD0DD");
			radioButton_1.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (radioButton_1.isEnabled() == true) {
						a_id.setEditable(false);
						a_name.setEditable(false);
						a_price.setEditable(false);
						b_id.setEditable(false);
						b_name.setEditable(false);
						b_unit.setEditable(false);
						c_id.setEditable(true);
						c_id2.setEditable(true);
						c_amount.setEditable(true);
					}
				}
			});
			radioButton_1.setBounds(10, 24, 51, 25);
			panel_8.add(radioButton_1);

			Label label_6 = new Label("\uC2DD\uB2E8ID");
			label_6.setBounds(97, 24, 70, 24);
			panel_8.add(label_6);

			Label label_7 = new Label("\uC18C\uC694\uB7C9");
			label_7.setBounds(97, 80, 70, 24);
			panel_8.add(label_7);

			Label label_8 = new Label("\uC2DD\uC7AC\uB8CCID");
			label_8.setBounds(322, 25, 70, 24);
			panel_8.add(label_8);

			JButton button_1 = new JButton("\uB4F1\uB85D");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// recipeµî·Ï
					try {
						oos.writeObject("insert");
						oos.flush();
						oos.writeObject("r");
						oos.flush();
						Recipe tmp = new Recipe(c_id.getText(), c_id2.getText(), Integer.parseInt(c_amount.getText()));
						r.add(tmp);
						oos.writeObject(r);
						oos.flush();
						dispose();
					} catch (UnknownHostException e1) {
						System.err.println(e1);
					} catch (IOException e1) {
						System.err.println(e1);
					} catch (Exception e1) {
						System.err.println(e1);
					}
				}
			});
			button_1.setBounds(452, 101, 101, 25);
			panel_8.add(button_1);

			c_id = new JTextField();
			c_id.setColumns(10);
			c_id.setBounds(173, 25, 116, 22);
			panel_8.add(c_id);

			c_id2 = new JTextField();
			c_id2.setColumns(10);
			c_id2.setBounds(404, 25, 116, 22);
			panel_8.add(c_id2);

			c_amount = new JTextField();
			c_amount.setColumns(10);
			c_amount.setBounds(173, 82, 116, 22);
			panel_8.add(c_amount);

			JPanel panel_2 = new JPanel();
			tabbedPane.addTab("¼öÁ¤", null, panel_2, null);
			panel_2.setLayout(null);

			JLabel lblNewLabel = new JLabel("\uC2DD\uB2E8 ID");
			lblNewLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
			lblNewLabel.setBounds(14, 52, 159, 42);
			panel_2.add(lblNewLabel);

			id = new JTextField();
			id.setBounds(187, 49, 328, 45);
			panel_2.add(id);
			id.setColumns(10);

			JLabel lblId = new JLabel("\uC2DD\uC7AC\uB8CC ID");
			lblId.setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
			lblId.setBounds(14, 164, 159, 42);
			panel_2.add(lblId);

			id2 = new JTextField();
			id2.setColumns(10);
			id2.setBounds(187, 161, 328, 45);
			panel_2.add(id2);

			JLabel label_10 = new JLabel("\uC18C\uC694\uB7C9");
			label_10.setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
			label_10.setBounds(14, 267, 159, 42);
			panel_2.add(label_10);

			amount = new JTextField();
			amount.setColumns(10);
			amount.setBounds(187, 264, 328, 45);
			panel_2.add(amount);

			JButton btnNewButton_1 = new JButton("\uC218\uC815");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ¼öÁ¤
					try {
						oos.writeObject("modify");
						oos.flush();
						recipe = m.modifyRecipe(id.getText(), id2.getText(), Double.parseDouble(amount.getText()));
						oos.writeObject(recipe);
						oos.flush();
						dispose();
					} catch (UnknownHostException e1) {
						System.err.println(e1);
					} catch (IOException e1) {
						System.err.println(e1);
					} catch (Exception e1) {
						System.err.println(e1);
					}

				}
			});
			btnNewButton_1.setBounds(480, 461, 101, 42);
			panel_2.add(btnNewButton_1);

			JPanel panel_3 = new JPanel();
			tabbedPane.addTab("»èÁ¦", null, panel_3, null);
			panel_3.setLayout(null);

			JLabel label_9 = new JLabel("\uC2DD\uB2E8 ID");
			label_9.setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
			label_9.setBounds(32, 44, 159, 42);
			panel_3.add(label_9);

			d_id = new JTextField();
			d_id.setColumns(10);
			d_id.setBounds(205, 41, 328, 45);
			panel_3.add(d_id);

			JLabel lblId_1 = new JLabel("\uC2DD\uC7AC\uB8CC ID");
			lblId_1.setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
			lblId_1.setBounds(32, 145, 159, 42);
			panel_3.add(lblId_1);

			d_id2 = new JTextField();
			d_id2.setColumns(10);
			d_id2.setBounds(205, 142, 328, 45);
			panel_3.add(d_id2);

			JButton btnNewButton_2 = new JButton("\uC0AD\uC81C");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// »èÁ¦
					try {
						oos.writeObject("delete");
						oos.flush();
						
						String mID = d_id.getText();
						String rID = d_id2.getText();
						//r = man.deleteRecipe(d_id.getText(), d_id2.getText());
						
						for(Recipe tmp: r) {
							if(mID.equals(tmp.getMenuID())&&rID.equals(tmp.getReserveID())) {
								r.remove(tmp);
								Recipe sendR = new Recipe(mID, rID, tmp.getRequires());
								recipe.add(sendR);
								break;
							}
						}
						oos.writeObject(recipe);
						oos.flush();
						dispose();
					} catch (UnknownHostException e1) {
						System.err.println(e1);
					} catch (IOException e1) {
						System.err.println(e1);
					} catch (Exception e1) {
						System.err.println(e1);
					}
				}
			});
			btnNewButton_2.setBounds(466, 454, 101, 42);
			panel_3.add(btnNewButton_2);
			ButtonGroup grp = new ButtonGroup();
			grp.add(rdbtnNewRadioButton);
			grp.add(radioButton);
			grp.add(radioButton_1);

			JPanel panel = new JPanel();
			tabbedPane.addTab("Á¶È¸", null, panel, null);
			panel.setLayout(null);

			JPanel panel_4 = new JPanel();
			panel_4.setBounds(0, 0, 307, 262);
			panel.add(panel_4);
			panel_4.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane = new JScrollPane();
			panel_4.add(scrollPane, BorderLayout.CENTER);
			dtm = new DefaultTableModel(new Object[][] {},
					new String[] { "\uC2DD\uB2E8ID", "\uC2DD\uC7AC\uB8CCID", "\uC18C\uC694\uB7C9" });
			table = new JTable(dtm);
			table.setModel(dtm);
			scrollPane.setViewportView(table);

			JPanel panel_9 = new JPanel();
			panel_9.setBounds(0, 263, 307, 262);
			panel.add(panel_9);
			panel_9.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane_1 = new JScrollPane();
			panel_9.add(scrollPane_1, BorderLayout.CENTER);

			dtm2 = new DefaultTableModel(new Object[][] {},
					new String[] { "\uC2DD\uB2E8ID", "\uC2DD\uB2E8\uBA85", "\uAC00\uACA9" });
			table_1 = new JTable(dtm2);
			table_1.setModel(dtm2);
			scrollPane_1.setViewportView(table_1);

			JPanel panel_10 = new JPanel();
			panel_10.setBounds(328, 0, 275, 514);
			panel.add(panel_10);
			panel_10.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane_2 = new JScrollPane();
			panel_10.add(scrollPane_2, BorderLayout.CENTER);

			dtm3 = new DefaultTableModel(new Object[][] {},
					new String[] { "\uC2DD\uC7AC\uB8CCID", "\uC2DD\uB2E8\uBA85", "\uB2E8\uC704" });
			table_2 = new JTable(dtm3);
			table_2.setModel(dtm3);
			scrollPane_2.setViewportView(table_2);
			for (int i = 0; i < menulist.size(); i++) {
				dtm.addRow(new Object[] { menulist.get(i).getMenuID(), menulist.get(i).getMenuName(),
						menulist.get(i).getPrice() });
			}

			for (int i = 0; i < recipe.size(); i++) {
				dtm2.addRow(new Object[] { recipe.get(i).getMenuID(), recipe.get(i).getReserveID(),
						recipe.get(i).getRequires() });
			}

			for (int i = 0; i < reserve.size(); i++) {
				dtm3.addRow(new Object[] { reserve.get(i).getReserveID(), reserve.get(i).getReserveName(),
						reserve.get(i).getUnit(), reserve.get(i).toString() });
			}
		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
