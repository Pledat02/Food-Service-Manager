
package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Controller.BagController;
import Controller.ChangeItemController;
import Controller.QuantityController;
import Controller.ChooseDrinkController;
import Controller.ExcelController;
import Controller.HistoryController;
import Controller.LogOutController;
import Controller.PaymentController;
import Controller.ChangeSlideController;
import Model.Combo;
import Model.Drink;
import Model.Food;
import Model.NetManagement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.CardLayout;

import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {
	public JFrame frame;
	private PaymentFrame paymentFrame;
	JButton btnLogOut;
	private JLabel lblcurrentName;
	public SubDrinkFrame subDrinkFrame;
	public TransactionHistory history;
	public static boolean isShowSubDrinkFrame = false;
	private JTextField textField_Amount, txtCombo, textField;
	private CardLayout cardLayout = new CardLayout();
	private CardLayout cardLayout_Combo = new CardLayout();
	private CardLayout cardLayout_Food = new CardLayout();
	private CardLayout cardLayout_Drink = new CardLayout();
	private JPanel cardPanel_Combo, cardPanel_Food, cardPanel_Drink;
	private JPanel panel_Card, ComboCover_panel;
	private JPanel backgroundPanel, CoverPanel, panel_right;
	private JPanel panel_Combo, panel_Drink, panel_Food;
	private NetManagement netManagement = NetManagement.getInstance();
	private JScrollPane scroll;
	public JPanel Scrollpanepanel = new JPanel();
	public boolean isFist = true;
	JButton btnPreviousCombo, btnPreviousFood, btnPreviousDrink;
	JButton btnNextCombo, btnNextFood, btnNextDrink;
	boolean isHasOneSlide_Combo, isHasOneSlide_Food, isHasOneSlide_Drink;
	public Drink newDrink = null;
	private JPanel panel_Combo_Child, panel_Child_Drink, panel_Child_Food;
	private JLabel lbl_DrinkName_Combo, lbTotalPriceCB_Child;
	int count = 0;
	int countChildPanel;

	// color
	Color bg = new Color(57, 57, 57);
	Color title = new Color(239, 215, 123);
	Color buyButton = new Color(85, 146, 214);
	Color rightBg = new Color(103, 101, 103);
	Border itemComboBorder = BorderFactory.createLineBorder(Color.WHITE, 1);
	Border comboBorder = BorderFactory.createLineBorder(Color.WHITE, 2);
	ChangeSlideController SlideC = new ChangeSlideController(this);
	ChooseDrinkController chooseDrinkC = new ChooseDrinkController(this);
	BagController bagController = new BagController(this);
	HistoryController historyController = new HistoryController(this);
	PaymentController paymentController = new PaymentController(this);
	ExcelController exitController = new ExcelController(this);

	public Frame() {
		initialize();
		topPanel();
		centerPanel();
		rightPanel();
		frame.setTitle("Menu");
		Image image = Toolkit.getDefaultToolkit().createImage(Login.class.getResource("..\\Image\\foodTitle.png"));
		frame.setIconImage(image);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {

		paymentFrame = new PaymentFrame();
		paymentFrame.setDefaultCloseOperation(Frame.DISPOSE_ON_CLOSE);
		paymentFrame.setLocationRelativeTo(null);
		paymentFrame.getBtnConfirm().addActionListener(paymentController);
		paymentFrame.ba_Frame.btn_back.addActionListener(paymentController);
		paymentFrame.momoFrame.btn_back.addActionListener(paymentController);
		paymentFrame.momoFrame.btnConfirm.addActionListener(paymentController);
		paymentFrame.ba_Frame.btnConfirm.addActionListener(paymentController);
		subDrinkFrame = new SubDrinkFrame();
		subDrinkFrame.setDefaultCloseOperation(Frame.DISPOSE_ON_CLOSE);
		subDrinkFrame.setLocationRelativeTo(null);
		history = new TransactionHistory();
		history.setDefaultCloseOperation(Frame.DISPOSE_ON_CLOSE);
		frame = new JFrame();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.getContentPane().setForeground(UIManager.getColor("inactiveCaptionBorder"));
		frame.getContentPane().setFont(new Font("Arial", Font.BOLD, 16));
		frame.setSize((int) dim.getWidth(), (int) dim.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(exitController);

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {

		}

		// background
		backgroundPanel = new JPanel();
		backgroundPanel.setSize((int) dim.getWidth(), (int) dim.getHeight());
		backgroundPanel.setLayout(null);

		CoverPanel = new JPanel(null);
		CoverPanel.setForeground(Color.WHITE);
		CoverPanel.setBorder(new LineBorder(new Color(239, 215, 123), 2));
		CoverPanel.setBounds(75, 43, 1362, 745);
		CoverPanel.setBackground(bg);
// Next Slide Item -----------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------
		backgroundPanel.add(CoverPanel);
		JLabel label = new JLabel(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(Frame.class.getResource("..\\Image\\menu.png"))));
		label.setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
		backgroundPanel.add(label);
		frame.getContentPane().add(backgroundPanel);
	}

	private void topPanel() {

		JLabel lbMenuLabel = new JLabel("MENU");
		lbMenuLabel.setForeground(title);
		lbMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lbMenuLabel.setFont(new Font("Dialog", Font.BOLD, 68));
		lbMenuLabel.setBounds(555, 27, 300, 66);
		CoverPanel.add(lbMenuLabel);
	}

	private void rightPanel() {

		panel_right = new JPanel();
		panel_right.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel_right.setBackground(rightBg);
		panel_right.setBounds(1008, 203, 295, 386);
		CoverPanel.add(panel_right);
		panel_right.setLayout(null);

		// Your Bill
		JLabel lblYourBill = new JLabel("Your Bill");
		lblYourBill.setForeground(title);
		lblYourBill.setBounds(99, 21, 89, 24);
		lblYourBill.setAlignmentY(3.0f);
		lblYourBill.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblYourBill.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourBill.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		panel_right.add(lblYourBill);

		JLabel namee = new JLabel("Name");
		namee.setForeground(title);
		namee.setBounds(14, 50, 89, 24);
		namee.setAlignmentY(3.0f);
		namee.setAlignmentX(Component.CENTER_ALIGNMENT);
		namee.setHorizontalAlignment(SwingConstants.CENTER);
		namee.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
		panel_right.add(namee);

		JLabel price = new JLabel("Price");
		price.setForeground(title);
		price.setBounds(112, 50, 50, 24);
		price.setAlignmentY(3.0f);
		price.setAlignmentX(Component.CENTER_ALIGNMENT);
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
		panel_right.add(price);

		JLabel amount = new JLabel("Quantity");
		amount.setForeground(title);
		amount.setBounds(178, 50, 89, 24);
		amount.setAlignmentY(3.0f);
		amount.setAlignmentX(Component.CENTER_ALIGNMENT);
		amount.setHorizontalAlignment(SwingConstants.CENTER);
		amount.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
		panel_right.add(amount);

		JPanel panel_ThanhTien = new JPanel();
		panel_ThanhTien.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel_ThanhTien.setBackground(bg);
		panel_ThanhTien.setBounds(0, 332, 295, 54);
		panel_right.add(panel_ThanhTien);
		panel_ThanhTien.setLayout(null);

		textField_Amount = new JTextField();
		textField_Amount.setEditable(false);
		textField_Amount.setBounds(73, 7, 86, 41);
		panel_ThanhTien.add(textField_Amount);
		textField_Amount.setText("");
		textField_Amount.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Amount.setFont(new Font("Arial Black", Font.PLAIN, 20));
		textField_Amount.setColumns(10);

		JLabel lblNewLabel = new JLabel("Total");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(5, 7, 64, 41);
		panel_ThanhTien.add(lblNewLabel);

		JButton btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.addActionListener(bagController);
		btnRemoveAll.setBackground(new Color(118, 118, 118));
		btnRemoveAll.setFocusable(false);
		btnRemoveAll.setBounds(170, 7, 120, 41);
		panel_ThanhTien.add(btnRemoveAll);
		btnRemoveAll.setFont(new Font("Arial", Font.BOLD, 16));

		Scrollpanepanel.setBounds(0, 73, 280, 187);

		scroll = new JScrollPane(Scrollpanepanel);
		scroll.setVisible(true);
		Scrollpanepanel.setLayout(null);
		Scrollpanepanel.setBackground(rightBg);
		Scrollpanepanel.setName(null);

		scroll.setSize(295, 261);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		scroll.setLocation(0, 73);
		scroll.setPreferredSize(new Dimension(316, 187));
		panel_right.add(scroll);

		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(bagController);
		btnOrder.setBackground(new Color(118, 118, 118));
		btnOrder.setFocusable(false);
		btnOrder.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		btnOrder.setBounds(1203, 594, 98, 41);
		CoverPanel.add(btnOrder);

		// format menu
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		textArea.setForeground(Color.WHITE);
		textArea.setBounds(88, 100, 1192, 5);
		CoverPanel.add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEnabled(false);
		textArea_1.setEditable(false);
		textArea_1.setBounds(88, 19, 1192, 5);
		CoverPanel.add(textArea_1);

		JLabel formatMenu1 = new JLabel(new ImageIcon(
				(Toolkit.getDefaultToolkit().createImage(Frame.class.getResource("..\\Image\\formatMenu1.png")))));
		formatMenu1.setBounds(262, 26, 150, 70);
		CoverPanel.add(formatMenu1);

		JLabel formatMenu2 = new JLabel(new ImageIcon(
				(Toolkit.getDefaultToolkit().createImage(Frame.class.getResource("..\\Image\\formatMenu2.png")))));
		formatMenu2.setBounds(985, 25, 150, 70);
		CoverPanel.add(formatMenu2);

		btnLogOut = new JButton("Log Out");

		btnLogOut.setForeground(new Color(239, 215, 123));
		btnLogOut.setFont(new Font("Arial", Font.BOLD, 22));
		btnLogOut.setFocusable(false);
		btnLogOut.setBorderPainted(false);
		btnLogOut.setBackground(new Color(103, 101, 103));
		btnLogOut.setBounds(1165, 139, 128, 41);
		CoverPanel.add(btnLogOut);

		JButton btnLSGD = new JButton("Transaction history");
		btnLSGD.addActionListener(historyController);
		btnLSGD.setForeground(new Color(239, 215, 123));
		btnLSGD.setFont(new Font("Arial", Font.BOLD, 22));
		btnLSGD.setFocusable(false);
		btnLSGD.setBorderPainted(false);
		btnLSGD.setBackground(new Color(103, 101, 103));
		btnLSGD.setBounds(1008, 650, 295, 41);
		CoverPanel.add(btnLSGD);

		lblcurrentName = new JLabel("username");
		lblcurrentName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblcurrentName.setForeground(new Color(220, 20, 60));
		lblcurrentName.setHorizontalAlignment(SwingConstants.CENTER);
		lblcurrentName.setBounds(1143, 120, 160, 19);
		CoverPanel.add(lblcurrentName);

	}

	private void centerPanel() {
		ChangeItemController itemC = new ChangeItemController(this);
		// CARD LAYOUT TO ADD 3 PANEL(COMBO_PANEL;FOOD_PANEL;DRINK_PANEL)

		panel_Card = new JPanel(cardLayout);
		panel_Card.setBounds(50, 160, 960, 580);
		panel_Card.setBackground(bg);

		JButton btnFoodButton = new JButton("FOOD");
		btnFoodButton.setBorderPainted(false);
		btnFoodButton.setForeground(title);
		btnFoodButton.setBackground(new Color(103, 101, 103));
		btnFoodButton.setFont(new Font("Arial", Font.BOLD, 22));
		btnFoodButton.setFocusable(false);
		btnFoodButton.addActionListener(itemC);
		btnFoodButton.setBounds(452, 120, 128, 41);
		CoverPanel.add(btnFoodButton);

		JButton btnComboButton = new JButton("COMBO");
		btnComboButton.setBorderPainted(false);
		btnComboButton.setForeground(title);
		btnComboButton.setBackground(new Color(103, 101, 103));
		btnComboButton.setFont(new Font("Arial", Font.BOLD, 22));
		btnComboButton.setFocusable(false);
		btnComboButton.addActionListener(itemC);
		btnComboButton.setBounds(188, 120, 128, 41);

		CoverPanel.add(btnComboButton);

		JButton btnDrinkButton = new JButton("DRINK");
		btnDrinkButton.setBorderPainted(false);
		btnDrinkButton.setForeground(title);
		btnDrinkButton.setBackground(new Color(103, 101, 103));
		btnDrinkButton.setFont(new Font("Arial", Font.BOLD, 22));
		btnDrinkButton.setFocusable(false);
		btnDrinkButton.addActionListener(itemC);
		btnDrinkButton.setBounds(729, 120, 128, 41);

		CoverPanel.add(btnDrinkButton);

		ComboPanel();
		FoodPanel();
		DrinkPanel();

		CoverPanel.add(panel_Card);
	}

	private void ComboPanel() {
		subDrinkFrame.btnConfirm.addActionListener(chooseDrinkC.getConfirmControllerForCombo());
		btnNextCombo = new JButton(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(Frame.class.getResource("..\\Image\\next.png"))));
		btnNextCombo.setForeground(Color.RED);
		btnNextCombo.setFont(new Font("Arial Narrow", Font.BOLD, 18));

		btnNextCombo.setBorderPainted(false);
		btnNextCombo.setBackground(new Color(57, 57, 57));
		btnNextCombo.setBounds(908, 535, 45, 40);
		btnNextCombo.addActionListener(SlideC);
		btnNextCombo.setActionCommand("Next");

		btnPreviousCombo = new JButton(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Frame.class.getResource("..\\Image\\previous.png"))));
		btnPreviousCombo.setEnabled(false);

		btnPreviousCombo.setForeground(Color.RED);
		btnPreviousCombo.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		btnPreviousCombo.setBorderPainted(false);
		btnPreviousCombo.setBackground(new Color(57, 57, 57));
		btnPreviousCombo.setBounds(5, 535, 45, 40);
		btnPreviousCombo.addActionListener(SlideC);
		btnPreviousCombo.setActionCommand("Previous");

		ComboCover_panel = new JPanel();
		ComboCover_panel.setBackground(bg);
		ComboCover_panel.setBounds(35, 160, 902, 575);
		ComboCover_panel.setLayout(null);
		ComboCover_panel.add(btnNextCombo);
		ComboCover_panel.add(btnPreviousCombo);

		cardPanel_Combo = new JPanel();
		cardPanel_Combo.setSize(857, 560);
		cardPanel_Combo.setLocation(50, 0);
		cardPanel_Combo.setLayout(cardLayout_Combo);
		ComboCover_panel.add(cardPanel_Combo);

		panel_Card.add("COMBO", ComboCover_panel);

		if (netManagement.countPanel(netManagement.getComboList(), 4) == 1) {
			isHasOneSlide_Combo = true;
		}

		if (isHasOneSlide_Combo) {
			btnNextCombo.setEnabled(false);
			btnPreviousCombo.setEnabled(false);
		}

		refreshCarPanel_Combo();
	}

	public void refreshCarPanel_Combo() {
		count = 0;
		while (count < netManagement.countPanel(netManagement.getComboList(), 4)) {
			countChildPanel = 0;
			panel_Combo = new JPanel();
			panel_Combo.setBackground(bg);
			panel_Combo.setLayout(new GridLayout(2, 2, 0, 0));
			panel_Combo.setBounds(0, 0, 857, 604);

			panel_Combo.setBorder(new TitledBorder(comboBorder, "ComBo", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Gill Sans MT", Font.BOLD, 35), title));

			ArrayList<Combo> comboList = (ArrayList<Combo>) netManagement.getComboList();
			for (int i = count * 4; i < (count + 1) * 4 && i < comboList.size(); i++, countChildPanel++) {
				panel_Combo_Child = new JPanel();
				panel_Combo_Child.setName(netManagement.getComboList().get(i).getFood().getName());

				panel_Combo_Child.setBackground(bg);
				panel_Combo_Child.setLayout(null);
				panel_Combo.add(panel_Combo_Child);
				panel_Combo_Child.setBorder(new TitledBorder(itemComboBorder, comboList.get(i).getName(),
						TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
						new Font("Gill Sans MT", Font.BOLD, 20), title));

				JLabel lblFood_Price = new JLabel(comboList.get(i).getFood().getPrice() + "$");
				lblFood_Price.setHorizontalAlignment(SwingConstants.CENTER);
				lblFood_Price.setForeground(new Color(220, 20, 60));
				lblFood_Price.setFont(new Font("Nirmala UI", Font.BOLD | Font.ITALIC, 22));
				lblFood_Price.setBounds(177, 175, 58, 35);
				panel_Combo_Child.add(lblFood_Price);

				lbTotalPriceCB_Child = new JLabel((double) Math.round(comboList.get(i).getPrice() * 100) / 100 + "$");
				lbTotalPriceCB_Child.setBackground(new Color(255, 255, 255));
				lbTotalPriceCB_Child.setHorizontalAlignment(SwingConstants.CENTER);
				lbTotalPriceCB_Child.setForeground(Color.RED);
				lbTotalPriceCB_Child.setFont(new Font("Nirmala UI", Font.BOLD | Font.ITALIC, 32));
				lbTotalPriceCB_Child.setBounds(300, 168, 96, 35);
				panel_Combo_Child.add(lbTotalPriceCB_Child);

				// 1 BUTTON_BUYNOW
				JButton btnBuyNow_Combo_Child = new JButton("Add To Cart");
				btnBuyNow_Combo_Child.addActionListener(bagController);

				btnBuyNow_Combo_Child.setActionCommand(comboList.get(i).getName() + "/"
						+ comboList.get(i).getFood().getName() + "/" + comboList.get(i).getFood().getPrice() + "/"
						+ comboList.get(i).getDrink().getName() + "/" + comboList.get(i).getDrink().getPrice() + "/");
				btnBuyNow_Combo_Child.setBackground(new Color(240, 240, 240));
				btnBuyNow_Combo_Child.setForeground(Color.RED);
				btnBuyNow_Combo_Child.setBorderPainted(false);
				btnBuyNow_Combo_Child.setFocusable(false);
				btnBuyNow_Combo_Child.setFont(new Font("Arial Narrow", Font.BOLD, 18));
				btnBuyNow_Combo_Child.setBounds(285, 207, 120, 36);
				panel_Combo_Child.add(btnBuyNow_Combo_Child);

				// 1 LABEL_IMAGE_FOOD

				JLabel label_Food_Combo = new JLabel(new ImageIcon((Toolkit.getDefaultToolkit()
						.createImage(Frame.class.getResource(comboList.get(i).getFood().getSrcImage())))));
				label_Food_Combo.setBorder(itemComboBorder);
				label_Food_Combo.setBounds(32, 27, 201, 148);
				panel_Combo_Child.add(label_Food_Combo);

				// 1 BUTTON_IMAGE_DRINK
				JButton btn_ThucUong_Child_Cb = new JButton(new ImageIcon((Toolkit.getDefaultToolkit()
						.createImage(Frame.class.getResource(comboList.get(i).getDrink().getSrcImage())))));
				btn_ThucUong_Child_Cb.addActionListener(chooseDrinkC.getChooseDrinkController());
				btn_ThucUong_Child_Cb.setActionCommand(comboList.get(i).getName());
				btn_ThucUong_Child_Cb.setBounds(270, 30, 116, 107);
				panel_Combo_Child.add(btn_ThucUong_Child_Cb);

				JLabel lblFood_BP__Child = new JLabel(comboList.get(i).getFood().getName());
				lblFood_BP__Child.setHorizontalAlignment(SwingConstants.CENTER);
				lblFood_BP__Child.setFont(new Font("Monotype Corsiva", Font.ITALIC, 28));
				lblFood_BP__Child.setBounds(30, 175, 151, 43);
				lblFood_BP__Child.setForeground(title);
				panel_Combo_Child.add(lblFood_BP__Child);

				lbl_DrinkName_Combo = new JLabel(comboList.get(i).getDrink().getName());
				lbl_DrinkName_Combo.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_DrinkName_Combo.setFont(new Font("Monotype Corsiva", Font.ITALIC, 20));
				lbl_DrinkName_Combo.setBounds(270, 132, 116, 30);
				lbl_DrinkName_Combo.setForeground(title);
				panel_Combo_Child.add(lbl_DrinkName_Combo);
				panel_Combo.add(panel_Combo_Child);

				cardPanel_Combo.add(panel_Combo);
			}
			while (countChildPanel < 4) {
				JPanel supPanel = new JPanel();
				supPanel.setBackground(bg);
				panel_Combo.add(supPanel);
				countChildPanel++;
			}
			count++;
		}
	}

	private void FoodPanel() {
		btnNextFood = new JButton(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(Frame.class.getResource("..\\Image\\next.png"))));
		btnNextFood.setForeground(Color.RED);
		btnNextFood.setFont(new Font("Arial Narrow", Font.BOLD, 18));

		btnNextFood.setBorderPainted(false);
		btnNextFood.setBackground(new Color(57, 57, 57));
		btnNextFood.setBounds(908, 535, 45, 40);
		btnNextFood.addActionListener(SlideC);
		btnNextFood.setActionCommand("Next");

		btnPreviousFood = new JButton(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Frame.class.getResource("..\\Image\\previous.png"))));
		btnPreviousFood.setEnabled(false);

		btnPreviousFood.setForeground(Color.RED);
		btnPreviousFood.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		btnPreviousFood.setBorderPainted(false);
		btnPreviousFood.setBackground(new Color(57, 57, 57));
		btnPreviousFood.setBounds(5, 535, 45, 40);
		btnPreviousFood.addActionListener(SlideC);
		btnPreviousFood.setActionCommand("Previous");

		JPanel foodCover_panel = new JPanel();
		foodCover_panel.setBackground(bg);
		foodCover_panel.setBounds(35, 160, 902, 575);
		foodCover_panel.setLayout(null);
		foodCover_panel.add(btnNextFood);

		foodCover_panel.add(btnPreviousFood);

		cardPanel_Food = new JPanel();
		cardPanel_Food.setSize(857, 560);
		cardPanel_Food.setLocation(50, 0);
		cardPanel_Food.setBackground(bg);
		cardPanel_Food.setLayout(cardLayout_Food);
		foodCover_panel.add(cardPanel_Food);

		panel_Card.add("FOOD", foodCover_panel);
		count = 0;
		if (netManagement.countPanel(netManagement.getFoodList(), 6) == 1) {
			isHasOneSlide_Food = true;
		}
		if (isHasOneSlide_Food) {
			btnNextFood.setEnabled(false);
			btnPreviousFood.setEnabled(false);
		}
		while (count < netManagement.countPanel(netManagement.getFoodList(), 6)) {

			panel_Food = new JPanel();
			panel_Food.setSize(857, 604);
			panel_Food.setBackground(bg);
			panel_Food.setLayout(new GridLayout(0, 3, 0, 0));
			panel_Food.setBorder(new TitledBorder(comboBorder, "Food", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Gill Sans MT", Font.BOLD, 35), title));
			countChildPanel = 0;
			ArrayList<Food> foodList = (ArrayList<Food>) netManagement.getFoodList();
			for (int i = count * 6; i < (count + 1) * 6 && i < foodList.size(); i++, countChildPanel++) {
				panel_Child_Food = new JPanel();
				panel_Child_Food.setName(netManagement.getFoodList().get(i).getName());
				panel_Child_Food.setBackground(bg);
				panel_Child_Food.setLayout(null);
				panel_Child_Food.setBorder(itemComboBorder);

				JLabel label_Child_Food = new JLabel(new ImageIcon((Toolkit.getDefaultToolkit()
						.createImage(Frame.class.getResource(foodList.get(i).getSrcImage())))));
				label_Child_Food.setBounds(41, 10, 201, 148);
				label_Child_Food.setBorder(new LineBorder(new Color(255, 255, 225), 1));
				panel_Child_Food.add(label_Child_Food);

				JLabel lblFood_Name = new JLabel(foodList.get(i).getName());
				lblFood_Name.setForeground(title);
				lblFood_Name.setFont(new Font("Monotype Corsiva", Font.ITALIC, 25));
				lblFood_Name.setHorizontalAlignment(SwingConstants.LEADING);
				lblFood_Name.setBounds(41, 160, 151, 43);
				panel_Child_Food.add(lblFood_Name);

				JLabel lblFood_Price_1 = new JLabel(foodList.get(i).getPrice() + "$");
				lblFood_Price_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblFood_Price_1.setForeground(new Color(220, 20, 60));
				lblFood_Price_1.setFont(new Font("Nirmala UI", Font.BOLD | Font.ITALIC, 22));
				lblFood_Price_1.setBounds(180, 161, 62, 35);
				panel_Child_Food.add(lblFood_Price_1);

				JButton btnBuyNow_Food = new JButton("Add To Cart");
				btnBuyNow_Food.addActionListener(bagController);
				btnBuyNow_Food.setActionCommand(foodList.get(i).getName() + "/" + foodList.get(i).getPrice());
				btnBuyNow_Food.setForeground(Color.RED);
				btnBuyNow_Food.setFont(new Font("Arial Narrow", Font.BOLD, 18));
				btnBuyNow_Food.setFocusable(false);
				btnBuyNow_Food.setBorderPainted(false);
				btnBuyNow_Food.setBackground(SystemColor.menu);
				btnBuyNow_Food.setBounds(85, 207, 120, 36);
				panel_Child_Food.add(btnBuyNow_Food);
				panel_Food.add(panel_Child_Food);

				cardPanel_Food.add(panel_Food);
			}
			while (countChildPanel < 6) {
				JPanel supPanel = new JPanel();
				supPanel.setBackground(bg);
				panel_Food.add(supPanel);
				countChildPanel++;
			}
			count++;
		}

	}

	private void DrinkPanel() {
		subDrinkFrame.btnConfirm.addActionListener(chooseDrinkC.getConfirmControllerForDrink());
		btnNextDrink = new JButton(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(Frame.class.getResource("..\\Image\\next.png"))));
		btnNextDrink.setForeground(Color.RED);
		btnNextDrink.setFont(new Font("Arial Narrow", Font.BOLD, 18));

		btnNextDrink.setBorderPainted(false);
		btnNextDrink.setBackground(new Color(57, 57, 57));
		btnNextDrink.setBounds(908, 535, 45, 40);
		btnNextDrink.addActionListener(SlideC);
		btnNextDrink.setActionCommand("Next");

		btnPreviousDrink = new JButton(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Frame.class.getResource("..\\Image\\previous.png"))));
		btnPreviousDrink.setEnabled(false);

		btnPreviousDrink.setForeground(Color.RED);
		btnPreviousDrink.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		btnPreviousDrink.setBorderPainted(false);
		btnPreviousDrink.setBackground(new Color(57, 57, 57));
		btnPreviousDrink.setBounds(5, 535, 45, 40);
		btnPreviousDrink.addActionListener(SlideC);
		btnPreviousDrink.setActionCommand("Previous");

		JPanel DrinkCover_panel = new JPanel();
		DrinkCover_panel.setBackground(bg);
		DrinkCover_panel.setBounds(35, 160, 902, 575);
		DrinkCover_panel.setLayout(null);
		DrinkCover_panel.add(btnNextDrink);

		DrinkCover_panel.add(btnPreviousDrink);

		cardPanel_Drink = new JPanel();
		cardPanel_Drink.setSize(857, 560);
		cardPanel_Drink.setLocation(50, 0);
		cardPanel_Drink.setBackground(bg);
		cardPanel_Drink.setLayout(cardLayout_Drink);
		DrinkCover_panel.add(cardPanel_Drink);

		panel_Card.add("DRINK", DrinkCover_panel);

		if (netManagement.countPanel(netManagement.getDrinkList(), 8) == 1) {
			isHasOneSlide_Drink = true;
		}
		if (isHasOneSlide_Drink) {
			btnNextDrink.setEnabled(false);
			btnPreviousDrink.setEnabled(false);
		}

		refreshCardPanel_Drink();
	}

	public void refreshCardPanel_Drink() {
		count = 0;

		while (count < netManagement.countPanel(netManagement.getDrinkList(), 8)) {
			countChildPanel = 0;
			panel_Drink = new JPanel();
			panel_Drink.setSize(857, 604);
			panel_Drink.setBackground(bg);

			panel_Drink.setLayout(new GridLayout(0, 4, 0, 0));
			panel_Drink.setBorder(new TitledBorder(comboBorder, "Drink", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Gill Sans MT", Font.BOLD, 35), title));

			ArrayList<Drink> drinkList = (ArrayList<Drink>) netManagement.getDrinkList();
			for (int i = count * 8; i < (count + 1) * 8 && i < drinkList.size(); i++, countChildPanel++) {

				panel_Child_Drink = new JPanel();
				panel_Child_Drink.setName(netManagement.getDrinkList().get(i).getName());
				panel_Child_Drink.setBackground(bg);
				panel_Child_Drink.setLayout(null);
				panel_Child_Drink.setBorder(itemComboBorder);
				panel_Drink.add(panel_Child_Drink);

				JLabel lbDrink_Price = new JLabel(drinkList.get(i).getPrice() + "$");
				lbDrink_Price.setHorizontalAlignment(SwingConstants.CENTER);
				lbDrink_Price.setForeground(SystemColor.window);
				lbDrink_Price.setFont(new Font("Nirmala UI", Font.BOLD | Font.ITALIC, 17));
				lbDrink_Price.setBounds(125, 20, 52, 35);
				panel_Child_Drink.add(lbDrink_Price);

				JLabel lblDrink = new JLabel(drinkList.get(i).getName());
				lblDrink.setForeground(title);
				lblDrink.setHorizontalAlignment(SwingConstants.CENTER);
				lblDrink.setFont(new Font("Monotype Corsiva", Font.ITALIC, 28));
				lblDrink.setBounds(2, 172, 209, 29);
				panel_Child_Drink.add(lblDrink);

				JButton label_Child_Drink = new JButton(new ImageIcon((Toolkit.getDefaultToolkit()
						.createImage(Frame.class.getResource(drinkList.get(i).getSrcImage())))));
				label_Child_Drink.setBounds(18, 5, 170, 169);
				label_Child_Drink.setMargin(getInsets());
				label_Child_Drink.setBackground(bg);
//				subDrinkFrame.ischeck(label_Child_Drink.getActionCommand());
				label_Child_Drink.addActionListener(chooseDrinkC.getChooseDrinkControllerForDrink());
				label_Child_Drink.setActionCommand(drinkList.get(i).getName());

				panel_Child_Drink.add(label_Child_Drink);

				JButton btnBuyNow_Drink = new JButton("Add To Cart");
				btnBuyNow_Drink.addActionListener(bagController);
				btnBuyNow_Drink.setActionCommand(drinkList.get(i).getName() + "/" + drinkList.get(i).getPrice());
				btnBuyNow_Drink.setForeground(Color.RED);
				btnBuyNow_Drink.setFont(new Font("Arial Narrow", Font.BOLD, 18));
				btnBuyNow_Drink.setFocusable(false);
				btnBuyNow_Drink.setBorderPainted(false);
				btnBuyNow_Drink.setBackground(SystemColor.menu);
				btnBuyNow_Drink.setBounds(47, 209, 120, 36);
				panel_Child_Drink.add(btnBuyNow_Drink);

				cardPanel_Drink.add(panel_Drink);
			}
			while (countChildPanel < 6) {
				JPanel supPanel = new JPanel();
				supPanel.setBackground(bg);
				panel_Drink.add(supPanel);
				countChildPanel++;
			}
			count++;
		}

	}

	// ====================== CAC FUNCTION DE XU LI SU KIEN ======================
	String current = "COMBO";

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getCurrent() {
		return current;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public JPanel getPanel_Card() {
		return panel_Card;
	}

	public CardLayout getCardLayout_Combo() {
		return cardLayout_Combo;
	}

	public CardLayout getCardLayout_Food() {
		return cardLayout_Food;
	}

	public JPanel getCardPanel_Drink() {
		return cardPanel_Drink;
	}

	public JButton getBtnNextCombo() {
		return btnNextCombo;
	}

	public JButton getBtnNextDrink() {
		return btnNextDrink;
	}

	public JButton getBtnNextFood() {
		return btnNextFood;
	}

	public JButton getBtnPreviousCombo() {
		return btnPreviousCombo;
	}

	public JButton getBtnPreviousDrink() {
		return btnPreviousDrink;
	}

	public JButton getBtnPreviousFood() {
		return btnPreviousFood;
	}

	public JPanel getCardPanel_Combo() {
		return cardPanel_Combo;
	}

	public JPanel getCardPanel_Food() {
		return cardPanel_Food;
	}

	public CardLayout getCardLayout_Drink() {
		return cardLayout_Drink;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JPanel getScrollpanepanel() {
		return Scrollpanepanel;
	}

	public JTextField getTextField_Amount() {
		return textField_Amount;
	}

	public JPanel getPanel_AItemInYourBill() {
		return panel_AItemInYourBill;
	}

	public SubDrinkFrame getSubDrinkFrame() {
		return subDrinkFrame;
	}

	int x = 1, y = 0, width = 294, height = 30;
	public int countItem = 0;
	public String nameCombo = "";

	JPanel panel_AItemInYourBill;

	private Entry<String, Double> object = null;

	public Entry<String, Double> getObject() {
		return object;

	}

	public void refreshCart() {
		textField_Amount.setText((double) Math.round(netManagement.getAmount() * 100) / 100 + "$");
		Scrollpanepanel.removeAll();
		Scrollpanepanel.updateUI();
		countItem = 0;
		QuantityController quantityController = new QuantityController(this);
		for (Entry<String, Double> o : netManagement.getCartItems().entrySet()) {
			object = o;
			netManagement.getPriceHashMap().put(o.getKey(), o.getValue());
			panel_AItemInYourBill = new JPanel();
			panel_AItemInYourBill.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
			panel_AItemInYourBill.setSize(width, height);

			Scrollpanepanel.add(panel_AItemInYourBill);
			Scrollpanepanel.setPreferredSize(new Dimension(width - 22, height + 32 * countItem));
			panel_AItemInYourBill.setLayout(null);
			panel_AItemInYourBill.setBounds(x - 1, y + 32 * countItem, width - 8, height);
			String[] strs = o.getKey().split("  ");
			String name = strs[0];
			String price = strs[1];
			txtCombo = new JTextField();
			txtCombo.setEditable(false);
			txtCombo.setBounds(0, 0, 121, 30);
			txtCombo.setHorizontalAlignment(SwingConstants.CENTER);
			txtCombo.setFont(new Font("Monospaced", Font.PLAIN, 15));
			txtCombo.setText(name);
			panel_AItemInYourBill.add(txtCombo);
			txtCombo.setColumns(10);

			JButton btnsub = new JButton("-");
			btnsub.addActionListener(quantityController);
			btnsub.setActionCommand("sub" + o.getKey());
			btnsub.setFocusable(false);
			btnsub.setForeground(Color.RED);
			btnsub.setBackground(Color.WHITE);
			btnsub.setFont(new Font("Arial Black", Font.BOLD, 14));
			btnsub.setBounds(164, 0, 38, 30);
			panel_AItemInYourBill.add(btnsub);

			JTextField textField_Price = new JTextField();
			textField_Price.setText((double) Math.round(Double.valueOf(price) * 100) / 100 + "");
			textField_Price.setHorizontalAlignment(SwingConstants.CENTER);
			textField_Price.setFont(new Font("Monospaced", Font.PLAIN, 15));
			textField_Price.setEditable(false);
			textField_Price.setColumns(10);
			textField_Price.setBounds(119, 0, 48, 30);
			panel_AItemInYourBill.add(textField_Price);

			textField = new JTextField();
			textField.setEditable(true);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("Arial Narrow", Font.BOLD, 15));
			double value = o.getValue();
			textField.setText((int) value + "");
			textField.addKeyListener(quantityController);

			textField.setBounds(198, 0, 42, 30);
			panel_AItemInYourBill.add(textField);
			textField.setColumns(10);

			JButton btnPlus = new JButton("+");
			btnPlus.addActionListener(quantityController);
			btnPlus.setActionCommand("add" + o.getKey());
			btnPlus.setFocusable(false);
			btnPlus.setForeground(Color.RED);
			btnPlus.setFont(new Font("Arial Black", Font.BOLD, 14));
			btnPlus.setBounds(237, 0, 38, 30);
			panel_AItemInYourBill.add(btnPlus);
			countItem++;
		}

	}

	public PaymentFrame getPaymentFrame() {
		return paymentFrame;
	}

	public JLabel getLblcurrentName() {
		return lblcurrentName;
	}

	public void setLblcurrentName(JLabel lblcurrentName) {
		this.lblcurrentName = lblcurrentName;
	}

	public void setPaymentFrame(PaymentFrame paymentFrame) {
		this.paymentFrame = paymentFrame;
	}

	public JPanel getPanel_Combo_Child() {
		return panel_Combo_Child;
	}

	public JPanel getPanel_Combo() {
		return panel_Combo;
	}

	public JPanel getPanel_Drink() {
		return panel_Drink;
	}

	public JPanel getPanel_Food() {
		return panel_Food;
	}

	public JPanel getPanel_Child_Drink() {
		return panel_Child_Drink;
	}

	public JPanel getPanel_Child_Food() {
		return panel_Child_Food;
	}

}