package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.PaymentController;
import Model.NetManagement;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;

public class PaymentFrame extends JFrame {

	private JPanel contentPane;
	 BA_Frame ba_Frame;
	 MomoFrame momoFrame;
	private JRadioButton cash_RadioBtn,ba_RadioBtn,momo_RadioBtn;
	private ButtonGroup buttonGroup;
	private String paymentMethod;
	private JButton btnConfirm ;
	private JLabel lbAmountMomo,lbAmountCash,lbAmountBA;
	private JLabel lbldescription_BA;
	private JLabel lbldescription_Momo;
	private JLabel lbldescription_Cash;
	private NetManagement netManagement = NetManagement.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentFrame frame = new PaymentFrame();
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
	public PaymentFrame() {
		setTitle("Payment Method");

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			
		}
		Image image = Toolkit.getDefaultToolkit().createImage(Login.class.getResource("..\\Image\\foodTitle.png"));
		setIconImage(image);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 428);
		setLocationRelativeTo(null);
		momoFrame = new MomoFrame();
		momoFrame.setDefaultCloseOperation(Frame.DISPOSE_ON_CLOSE);
		momoFrame.setLocationRelativeTo(null);
		ba_Frame = new BA_Frame();
		ba_Frame.setDefaultCloseOperation(Frame.DISPOSE_ON_CLOSE);
		ba_Frame.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Payment Method");
		title.setFont(new Font("Gill Sans MT", Font.BOLD | Font.ITALIC, 40));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(title);
		
		JPanel panel_BA = new JPanel();
		panel_BA.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel_BA);
		 panel_BA.setLayout(null);
		
		 ba_RadioBtn = new JRadioButton("Bank Account");
		 ba_RadioBtn.setBackground(Color.LIGHT_GRAY);
		 ba_RadioBtn.setBounds(0, 0, 199, 39);
		 ba_RadioBtn.setName("Bank Account");
		ba_RadioBtn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		panel_BA.add(ba_RadioBtn);
		
		JPanel panel_momo = new JPanel();
		panel_momo.setBackground(Color.LIGHT_GRAY);
		panel_momo.setLayout(null);
		contentPane.add(panel_momo);
		
		 momo_RadioBtn = new JRadioButton("Momo");
		 momo_RadioBtn.setBackground(Color.LIGHT_GRAY);
		 momo_RadioBtn.setName("Momo");
		momo_RadioBtn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		momo_RadioBtn.setBounds(0, 0, 104, 39);
		panel_momo.add(momo_RadioBtn);
		
		JPanel panel_cash = new JPanel();
		panel_cash.setBackground(Color.LIGHT_GRAY);
		panel_cash.setLayout(null);
		contentPane.add(panel_cash);
		
		cash_RadioBtn = new JRadioButton("Cash");
		cash_RadioBtn.setBackground(Color.LIGHT_GRAY);
		cash_RadioBtn.setName("Cash");
		cash_RadioBtn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		cash_RadioBtn.setBounds(0, 0, 91, 39);
		panel_cash.add(cash_RadioBtn);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(ba_RadioBtn);
		
		lbAmountBA = new JLabel("");
		lbAmountBA.setFont(new Font("Sylfaen", Font.PLAIN, 22));
		lbAmountBA.setBounds(20, 45, 179, 32);
		panel_BA.add(lbAmountBA);
		
		lbldescription_BA = new JLabel(netManagement.getBAdescription());
		lbldescription_BA.setFont(new Font("Verdana", Font.PLAIN, 17));
		lbldescription_BA.setBounds(205, 0, 146, 39);
		panel_BA.add(lbldescription_BA);
		buttonGroup.add(momo_RadioBtn);
		
		lbAmountMomo = new JLabel("");
		lbAmountMomo.setFont(new Font("Sylfaen", Font.PLAIN, 22));
		
		lbAmountMomo.setBounds(20, 45, 179, 32);
		panel_momo.add(lbAmountMomo);
		
		lbldescription_Momo = new JLabel(netManagement.getMomodescription());
		lbldescription_Momo.setFont(new Font("Verdana", Font.PLAIN, 17));
		lbldescription_Momo.setBounds(115, 0, 146, 37);
		panel_momo.add(lbldescription_Momo);
		buttonGroup.add(cash_RadioBtn);
		
		lbAmountCash = new JLabel("");
		lbAmountCash.setFont(new Font("Sylfaen", Font.PLAIN, 22));
		lbAmountCash.setBounds(23, 48, 179, 32);
		panel_cash.add(lbAmountCash);
		
		lbldescription_Cash = new JLabel(netManagement.getCashdescription());
		lbldescription_Cash.setFont(new Font("Verdana", Font.PLAIN, 17));
		lbldescription_Cash.setBounds(112, 0, 146, 39);
		panel_cash.add(lbldescription_Cash);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setPreferredSize(new Dimension(-20,-30));
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFocusable(false);
		btnConfirm.setFont(new Font("Arial", Font.BOLD, 18));
		btnConfirm.setBounds(340, 10, 111, 38);
		panel.add(btnConfirm);
	}
	public BA_Frame getBa_Frame() {
		return ba_Frame;
	}
	public void setBa_Frame(BA_Frame ba_Frame) {
		this.ba_Frame = ba_Frame;
	}
	public MomoFrame getMomoFrame() {
		return momoFrame;
	}
	public void setMomoFrame(MomoFrame momoFrame) {
		this.momoFrame = momoFrame;
	}
	public JRadioButton getCash_RadioBtn() {
		return cash_RadioBtn;
	}
	public void setCash_RadioBtn(JRadioButton cash_RadioBtn) {
		this.cash_RadioBtn = cash_RadioBtn;
	}
	public JRadioButton getBa_RadioBtn() {
		return ba_RadioBtn;
	}
	public void setBa_RadioBtn(JRadioButton ba_RadioBtn) {
		this.ba_RadioBtn = ba_RadioBtn;
	}
	public JRadioButton getMomo_RadioBtn() {
		return momo_RadioBtn;
	}
	public void setMomo_RadioBtn(JRadioButton momo_RadioBtn) {
		this.momo_RadioBtn = momo_RadioBtn;
	}
	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}
	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String methodPayment) {
		this.paymentMethod = methodPayment;
	}
	public JButton getBtnConfirm() {
		return btnConfirm;
	}
	public JLabel getLbAmountMomo() {
		return lbAmountMomo;
	}
	public JLabel getLbAmountCash() {
		return lbAmountCash;
	}
	public void setLbAmountCash(JLabel lbAmountCash) {
		this.lbAmountCash = lbAmountCash;
	}
	public JLabel getLbAmountBA() {
		return lbAmountBA;
	}
	public void setLbAmountBA(JLabel lbAmountBA) {
		this.lbAmountBA = lbAmountBA;
	}
	
}
