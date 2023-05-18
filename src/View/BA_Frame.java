package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BA_Frame extends JFrame {

	private JPanel contentPane;
	 JButton btn_back;
	 JButton btnConfirm;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BA_Frame frame = new BA_Frame();
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
	public BA_Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn_back = new JButton("Back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_back.setFont(new Font("Arial", Font.BOLD, 18));
		btn_back.setBounds(10, 22, 103, 37);
		contentPane.add(btn_back);
		
		JLabel lblNewLabel = new JLabel("Bank Account");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 28));
		lblNewLabel.setBounds(240, 22, 245, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon((Toolkit.getDefaultToolkit()
				.createImage(Frame.class.getResource("..\\Image\\ba.png")))));
		lblNewLabel_1.setBounds(33, 76, 671, 599);
		contentPane.add(lblNewLabel_1);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setActionCommand("Confirm_ba");
		btnConfirm.setFont(new Font("Arial", Font.BOLD, 18));
		btnConfirm.setBounds(404, 10, 111, 38);;
		btnConfirm.setBounds(551, 696, 124, 37);
		contentPane.add(btnConfirm);
	}
}
