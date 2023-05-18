package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.CheckLoginAccount;
import Controller.LogOutController;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userNameText;
	private JPasswordField passwordField;
	public Frame frame ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login();
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		frame = new Frame();
		setTitle("Login");
		Image image = Toolkit.getDefaultToolkit().createImage(Login.class.getResource("..\\Image\\foodTitle.png"));
		setIconImage(image);
		CheckLoginAccount checkLoginAccount = new CheckLoginAccount(this);
		LogOutController logOutController = new LogOutController(this);
		frame.btnLogOut.addActionListener(logOutController);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		Panel panel = new Panel();
		panel.setBounds(5, 77, 398, 94);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 10));
		
		Label username_label = new Label("User name ");
		username_label.setFont(new Font("Vladimir Script", Font.BOLD | Font.ITALIC, 22));
		username_label.setAlignment(Label.CENTER);
		panel.add(username_label);
		
		userNameText = new JTextField();
		userNameText.addKeyListener(checkLoginAccount);
		panel.add(userNameText);
		userNameText.setColumns(10);
		
		Label password_lb = new Label("Password  ");
		password_lb.setFont(new Font("Vladimir Script", Font.BOLD | Font.ITALIC, 22));
		password_lb.setAlignment(Label.CENTER);
		panel.add(password_lb);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(checkLoginAccount);
		panel.add(passwordField);
		
		JButton btn_Login = new JButton("Login");
		btn_Login.setFocusable(false);
		btn_Login.setFont(new Font("Century Schoolbook", Font.PLAIN, 22));
		btn_Login.setBounds(265, 192, 138, 41);
		btn_Login.setPreferredSize(new Dimension(50, 50));
		btn_Login.addActionListener(checkLoginAccount);
		contentPane.add(btn_Login);
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(5, 5, 426, 59);
		panel_1.setPreferredSize(new Dimension(20, 50));
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);
	}	


	public JTextField getUserNameText() {
		return userNameText;
	}

	public void setUserNameText(JTextField userNameText) {
		this.userNameText = userNameText;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
	
}
