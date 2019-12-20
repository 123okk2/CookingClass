package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout.Group;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Panel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class S_StudentMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					S_StudentMain frame = new S_StudentMain();
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
	public S_StudentMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(260, 393, 253, 37);
		contentPane.add(panel_1);
		
		JLabel label_1 = new JLabel("Ωƒ¿Á∑· Ω≈√ª");
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		panel_1.add(label_1);
		
		JLabel lblNewLabel = new JLabel("[ \uC218 \uAC15 \uC0DD ]");
		lblNewLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		lblNewLabel.setBounds(346, 0, 351, 103);
		contentPane.add(lblNewLabel);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//2.Ωƒ¿Á∑· Ω≈√ª
				S_ApplyRecipe secondFrame=new S_ApplyRecipe();
				secondFrame.setVisible(true);
				
			}
		});
		button_1.setBackground(Color.GRAY);
		button_1.setIcon(new ImageIcon("D:\\\uB2E4\uC6B4\uB85C\uB4DC\\harvest.png"));
		button_1.setBounds(249, 114, 271, 393);
		contentPane.add(button_1);
		
		Panel panel_2 = new Panel();
		panel_2.setBackground(Color.ORANGE);
		panel_2.setBounds(526, 393, 246, 37);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("\uC2DD\uC7AC\uB8CC \uCDE8\uC18C");
		lblNewLabel_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		lblNewLabel_1.setBackground(Color.YELLOW);
		panel_2.add(lblNewLabel_1);
		
		Panel panel = new Panel();
		panel.setBackground(Color.PINK);
		panel.setForeground(Color.WHITE);
		panel.setEnabled(false);
		panel.setBounds(10, 393, 233, 37);
		contentPane.add(panel);
		
		JLabel label = new JLabel("\uC2E4\uC2B5 \uBA54\uB274(\uC77C\uC790\uBCC4) \uC870\uD68C");
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		label.setBackground(Color.WHITE);
		panel.add(label);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//3.Ωƒ¿Á∑· √Îº“
				S_CancelRecipe secondFrame=new S_CancelRecipe();
				secondFrame.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon("D:\\\uB2E4\uC6B4\uB85C\uB4DC\\KakaoTalk_20181223_034130559.png"));
		button.setBackground(Color.ORANGE);
		button.setBounds(519, 114, 265, 393);
		contentPane.add(button);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1.Ω«Ω¿∏ﬁ¥∫ ¿œ¿⁄∫∞ ¡∂»∏
				S_ShowMenu secondFrame=new S_ShowMenu();
				secondFrame.setVisible(true);
				
			}
		});
		btnNewButton.setBackground(Color.PINK);
		
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\rnfyd\\Desktop\\KakaoTalk_20181223_022501326.png"));
		
		btnNewButton.setBounds(0,114, 254, 393);
		contentPane.add(btnNewButton);
		
	
		
	}
}
