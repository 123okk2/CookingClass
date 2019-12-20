package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Panel;
import java.awt.Label;

public class T_TeacherMain extends JFrame {

	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					T_TeacherMain frame = new T_TeacherMain();
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
	public T_TeacherMain() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("< \uAC15 \uC0AC >");
		lblNewLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		lblNewLabel.setBounds(352, 24, 97, 45);
		contentPane.add(lblNewLabel);
		
		Panel panel_3 = new Panel();
		panel_3.setBackground(new Color(0, 0, 128));
		panel_3.setBounds(404, 381, 189, 71);
		contentPane.add(panel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uC2DD\uC7AC\uB8CC \uC18C\uC694\uB7C9 \uBAA9\uB85D");
		lblNewLabel_4.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		panel_3.add(lblNewLabel_4);
		
		Label label = new Label("(\uC77C\uC790\uBCC4,\uAE30\uAC04\uBCC4)");
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		panel_3.add(label);
		
		Panel panel_2 = new Panel();
		panel_2.setBackground(new Color(169, 169, 169));
		panel_2.setBounds(206, 379, 189, 52);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("\uC2DD\uC7AC\uB8CC\uC2E0\uCCAD\uC790\r\n\uBA85\uB2E8 \uC870\uD68C");
		lblNewLabel_3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 18));
		lblNewLabel_3.setBounds(0, 0, 198, 40);
		panel_2.add(lblNewLabel_3);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon("C:\\Users\\mkjh9\\Desktop\\cookingclass\\img\\KakaoTalk_20181223_182241677.png"));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//2.Ωƒ¿Á∑· Ω≈√ª¿⁄ ∏Ì¥‹ ¡∂»∏
				T_ShowApplicant second=new T_ShowApplicant();
				second.setVisible(true);
			}
		});
		button_1.setBackground(new Color(169, 169, 169));
		button_1.setBounds(198, 110, 200, 393);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon("C:\\Users\\mkjh9\\Desktop\\cookingclass\\img\\KakaoTalk_20181223_182310863.png"));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//3.√— º“ø‰∑Æ ∏Ò∑œ(¿œ¿⁄∫∞, ±‚∞£∫∞)
				T_ShowRequires second=new T_ShowRequires();
				second.setVisible(true);
			}
		});
		
		Panel panel = new Panel();
		panel.setBackground(Color.PINK);
		panel.setBounds(0, 379, 200, 35);
		contentPane.add(panel);
		
		Label label_1 = new Label("\uC2E4\uC2B5\uBA54\uB274(\uC77C\uC790\uBCC4) \uC870\uD68C");
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 17));
		panel.add(label_1);
		button_2.setBackground(new Color(0, 0, 128));
		button_2.setBounds(396, 110, 200, 393);
		contentPane.add(button_2);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\mkjh9\\Desktop\\cookingclass\\img\\KakaoTalk_20181223_182241450.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1.Ω«Ω¿∏ﬁ¥∫ ¿œ¿⁄∫∞ ¡∂»∏0
				T_ShowMenu second=new T_ShowMenu();
				second.setVisible(true);
			}
		});
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(176, 196, 222));
		panel_1.setBounds(600, 396, 172, 35);
		contentPane.add(panel_1);
		
		Label label_2 = new Label("\uC774\uCCB4\uB0B4\uC5ED \uB4F1\uC7AC");
		label_2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		panel_1.add(label_2);
		button.setBackground(Color.PINK);
		button.setBounds(0, 110, 200, 393);
		contentPane.add(button);
		
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon("C:\\Users\\mkjh9\\Desktop\\cookingclass\\img\\KakaoTalk_20181223_182241113.png"));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//4.¿Ã√º≥ªø™ µÓ¿Á
				T_EnrollAccount second=new T_EnrollAccount();
				second.setVisible(true);
			}
		});
		button_3.setBackground(new Color(176, 196, 222));
		button_3.setBounds(593, 110, 189, 393);
		contentPane.add(button_3);
		
	}
	}
