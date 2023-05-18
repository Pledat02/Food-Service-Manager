package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Controller.SubDrinkController;
import Model.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.Color;

public class SubDrinkFrame extends JFrame {

	private JPanel contentPane;
	public JLabel lbldescription;
	private JCheckBox chckbxNewCheckBox;
	private NetManagement netManagement = new NetManagement();
	public ButtonGroup checkboxGroup_Drink, checkboxGroup_Size;
	public JCheckBox chckbx_Soy, chckbx_Flan, chckbx_Jelly, chckbx_BlackBubble;
	public JButton btnConfirm;
	public JPanel drinkPanel, sizePanel, toppingPanel;
	private JCheckBox chckbxS, chckbxM, chckbxL;
	private String isDrinkChose = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubDrinkFrame subframe = new SubDrinkFrame();
					subframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Color bg = Color.lightGray;
	Color textColor = Color.black;

	public Border getTitleBorder(String name) {
		Border lineborder = BorderFactory.createLineBorder(Color.WHITE, 2);
		return new TitledBorder(lineborder, name, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new Font("Arial Black", Font.BOLD, 20), new Color(220, 20, 60));
	}

	public SubDrinkFrame() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {

		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.black);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Choose Drink");
		
		SouthPanel();
		CenterPenl();

	}

	Font priceFont = new Font("Arial", Font.BOLD, 15);

	@SuppressWarnings("deprecation")
	private void CenterPenl() {
		SubDrinkController subDrinkC = new SubDrinkController(this);
		JPanel CenterPanel = new JPanel();
		CenterPanel.setBackground(bg);
		contentPane.add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(null);

		drinkPanel = new JPanel();
		drinkPanel.setBounds(45, 20, 728, 162);
		drinkPanel.setBorder(this.getTitleBorder("Choose Your Drink"));
		drinkPanel.setLayout(new GridLayout(0, 4, 0, 0));
		drinkPanel.setBackground(bg);
		checkboxGroup_Drink = new ButtonGroup();

		for (int i = 0; i < netManagement.getDrinkList().size(); i++) {
			chckbxNewCheckBox = new JCheckBox(netManagement.getDrinkList().get(i).getName());
			JLabel priceDrink = new JLabel("                                " + netManagement.getDrinkList().get(i).getPrice() + "$");
			priceDrink.setForeground(new Color(153, 51, 51));
			chckbxNewCheckBox.add(priceDrink);
			chckbxNewCheckBox.setName(netManagement.getDrinkList().get(i).getName());
			priceDrink.setFont(priceFont);
			chckbxNewCheckBox.setPreferredSize(new Dimension(54, 32));
			chckbxNewCheckBox.setFont(new Font("Arial", Font.PLAIN, 17));
			chckbxNewCheckBox.setBackground(bg);
			chckbxNewCheckBox.setForeground(textColor);
			chckbxNewCheckBox.addActionListener(subDrinkC.getDrinkController());
			checkboxGroup_Drink.add(chckbxNewCheckBox);
			drinkPanel.add(chckbxNewCheckBox);
		}
		CenterPanel.add(drinkPanel);

		sizePanel = new JPanel();
		sizePanel.setBackground(bg);
		sizePanel.setBorder(this.getTitleBorder("Choose It's Size"));
		sizePanel.setLocation(45, 202);
		sizePanel.setSize(729, 88);
		CenterPanel.add(sizePanel);
		sizePanel.setLayout(null);
		checkboxGroup_Size = new ButtonGroup();

		chckbxS = new JCheckBox("S");
		JLabel priceS = new JLabel("           Initial Price");
		priceS.setForeground(new Color(153, 51, 51));
		priceS.setFont(priceFont);
		chckbxS.add(priceS);
		chckbxS.setPreferredSize(new Dimension(54, 32));
		chckbxS.setBackground(bg);
		chckbxS.setForeground(textColor);
		chckbxS.setFont(new Font("Arial Narrow", Font.PLAIN, 22));
		chckbxS.setBounds(30, 40, 146, 32);
		checkboxGroup_Size.add(chckbxS);
		sizePanel.add(chckbxS);
		chckbxS.addActionListener(subDrinkC.getSizeController());

		chckbxM = new JCheckBox("M");
		JLabel priceM = new JLabel("            Price*1.2");
		priceM.setForeground(new Color(153, 51, 51));
		priceM.setFont(priceFont);
		chckbxM.add(priceM);
		chckbxM.setBackground(bg);
		chckbxM.setForeground(textColor);
		chckbxM.setPreferredSize(new Dimension(54, 32));
		chckbxM.setFont(new Font("Arial Narrow", Font.PLAIN, 22));
		chckbxM.setBounds(228, 40, 135, 32);
		checkboxGroup_Size.add(chckbxM);
		sizePanel.add(chckbxM);
		chckbxM.addActionListener(subDrinkC.getSizeController());

		chckbxL = new JCheckBox("L");
		JLabel pricel = new JLabel("           Price*1.4");
		pricel.setForeground(new Color(153, 51, 51));
		pricel.setFont(priceFont);
		chckbxL.add(pricel);
		chckbxL.setBackground(bg);
		chckbxL.setForeground(textColor);
		chckbxL.setPreferredSize(new Dimension(54, 32));
		chckbxL.setFont(new Font("Arial Narrow", Font.PLAIN, 22));
		chckbxL.setBounds(390, 40, 135, 32);
		checkboxGroup_Size.add(chckbxL);
		sizePanel.add(chckbxL);
		chckbxL.addActionListener(subDrinkC.getSizeController());

		toppingPanel = new JPanel();
		toppingPanel.setBorder(this.getTitleBorder("Choose It's Topping"));
		toppingPanel.setBounds(45, 320, 728, 58);
		CenterPanel.add(toppingPanel);
		toppingPanel.setBackground(bg);
		toppingPanel.setLayout(new GridLayout(0, 4, 0, 0));

		chckbx_Soy = new JCheckBox("Soy");
		JLabel priceSoy = new JLabel("                0.19$");
		priceSoy.setForeground(new Color(153, 51, 51));
		priceSoy.setFont(priceFont);
		chckbx_Soy.add(priceSoy);
		chckbx_Soy.setBackground(bg);
		chckbx_Soy.setForeground(textColor);
		chckbx_Soy.setPreferredSize(new Dimension(54, 32));
		chckbx_Soy.setFont(new Font("Arial", Font.PLAIN, 20));
		chckbx_Soy.addActionListener(subDrinkC.getToppingController());
		toppingPanel.add(chckbx_Soy);

		chckbx_Flan = new JCheckBox("Flan");
		JLabel priceFlan = new JLabel("                 0.19$");
		priceFlan.setForeground(new Color(153, 51, 51));
		priceFlan.setFont(priceFont);
		chckbx_Flan.add(priceFlan);
		chckbx_Flan.setBackground(bg);
		chckbx_Flan.setForeground(textColor);
		chckbx_Flan.setFont(new Font("Arial", Font.PLAIN, 20));
		chckbx_Flan.addActionListener(subDrinkC.getToppingController());
		toppingPanel.add(chckbx_Flan);

		chckbx_Jelly = new JCheckBox("Jelly");
		JLabel priceJelly = new JLabel("                 0.19$");
		priceJelly.setForeground(new Color(153, 51, 51));
		priceJelly.setFont(priceFont);
		chckbx_Jelly.add(priceJelly);
		chckbx_Jelly.setBackground(bg);
		chckbx_Jelly.setForeground(textColor);
		chckbx_Jelly.setFont(new Font("Arial", Font.PLAIN, 20));
		chckbx_Jelly.addActionListener(subDrinkC.getToppingController());
		toppingPanel.add(chckbx_Jelly);

		chckbx_BlackBubble = new JCheckBox("BlackBubble");
		JLabel priceBubble = new JLabel("                                  0.1$");
		priceBubble.setForeground(new Color(153, 51, 51));
		priceBubble.setFont(priceFont);
		chckbx_BlackBubble.add(priceBubble);
		chckbx_BlackBubble.setBackground(bg);
		chckbx_BlackBubble.setForeground(textColor);
		chckbx_BlackBubble.setFont(new Font("Arial", Font.PLAIN, 19));
		chckbx_BlackBubble.addActionListener(subDrinkC.getToppingController());
		toppingPanel.add(chckbx_BlackBubble);

		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnConfirm.setBounds(335, 400, 142, 37);
		CenterPanel.add(btnConfirm);
		sizePanel.setEnabled(false);
		toppingPanel.setEnabled(false);
	}

	private void SouthPanel() {
		JPanel SouthPanel = new JPanel();
		SouthPanel.setBackground(bg);
		contentPane.add(SouthPanel, BorderLayout.SOUTH);
		SouthPanel.setLayout(new BorderLayout(0, 0));

		lbldescription = new JLabel("Description");
		lbldescription.setForeground(new Color(220, 20, 60));
		lbldescription.setHorizontalAlignment(SwingConstants.CENTER);
		lbldescription.setFont(new Font("Arial Narrow", Font.PLAIN, 17));
		SouthPanel.add(lbldescription, BorderLayout.NORTH);
		SouthPanel.setBackground(Color.white);
	}

	public JLabel getLbldescription() {
		return lbldescription;
	}

	public void setLbldescription(JLabel lbldescription) {
		this.lbldescription = lbldescription;
	}

	Drink newDrink = null;
	Size size = Size.SMALL;
	int count = 0;

	public Drink searchDrink(String actioncommand) {
		Drink drink = null;
		sizePanel.setEnabled(true);
		toppingPanel.setEnabled(true);
		for (Drink o : netManagement.getDrinkList()) {
			if (o.getName().equals(actioncommand)) {
				if (o instanceof BottledDrink) {
					isDrinkChose = "unNull";
					drink = o;
					sizePanel.setEnabled(false);
					toppingPanel.setEnabled(false);
				} else if (o instanceof PreparedDrink) {
					drink = new PreparedDrink(o.getName(), o.getPrice(), o.getSrcImage(), size);
					isDrinkChose = "unNull";
					if (count == 0) {
						chckbxS.setSelected(true);
						count++;
					}
				}
			}
		}
		if (sizePanel.isEnabled() == false) {
			for (int i = 0; i < sizePanel.getComponentCount(); i++) {
				sizePanel.getComponent(i).setEnabled(false);
			}
		} else {
			for (int i = 0; i < sizePanel.getComponentCount(); i++) {
				sizePanel.getComponent(i).setEnabled(true);
			}
		}
		if (drinkPanel.isEnabled() == false) {
			for (int i = 0; i < drinkPanel.getComponentCount(); i++) {
				drinkPanel.getComponent(i).setEnabled(false);
			}
		} else {
			for (int i = 0; i < drinkPanel.getComponentCount(); i++) {
				drinkPanel.getComponent(i).setEnabled(true);
			}
		}
		if (toppingPanel.isEnabled() == false) {
			for (int i = 0; i < toppingPanel.getComponentCount(); i++) {
				toppingPanel.getComponent(i).setEnabled(false);
			}
		} else {
			for (int i = 0; i < toppingPanel.getComponentCount(); i++) {
				toppingPanel.getComponent(i).setEnabled(true);
			}
		}
		lbldescription.setText("Total :  " + (double) Math.round(drink.getPrice() * 100) / 100 + "$");
		newDrink = drink;
		return result;
	}

	int countSoy = 0, countFlan = 0, countJelly = 0, countBubble = 0;

	public void searchSize(String actioncommand) {
		Size size = this.size;
		if (actioncommand.equals("S")) {
			size = Size.SMALL;
		} else if (actioncommand.equals("L")) {
			size = Size.LARGE;
		} else if (actioncommand.equals("M")) {
			size = Size.MEDIUM;
		}
		countSoy = 0;
		countFlan = 0;
		countJelly = 0;
		countBubble = 0;
		newDrink.setSize(size);
		this.size = size;
		searchDrink(newDrink.getName());
		chckbx_BlackBubble.setSelected(false);
		chckbx_Soy.setSelected(false);
		chckbx_Flan.setSelected(false);
		chckbx_Jelly.setSelected(false);
		lbldescription.setText("Total :  " + (double) Math.round(newDrink.getPrice() * 100) / 100 + "$");

	}

	Drink currentDrink = newDrink;
	Drink result;

	public Drink addTopping() {
		result = newDrink;

		if (chckbx_Soy.isSelected() && countSoy % 2 == 0) {
			result = new Soy(newDrink);
			countSoy++;
			System.out.println(countSoy);
		} else if (!(chckbx_Soy.isSelected()) && countSoy % 2 == 1) {
			result.setPrice(result.getPrice() - 0.19);
			countSoy++;
		}

		if (chckbx_Flan.isSelected() && countFlan % 2 == 0) {
			result = new Flan(newDrink);
			countFlan++;
			System.out.println(result.getPrice());
		} else if (!(chckbx_Flan.isSelected()) && countFlan % 2 == 1) {
			result.setPrice(result.getPrice() - 0.19);
			countFlan++;

		}

		if (chckbx_Jelly.isSelected() && countJelly % 2 == 0) {
			result = new Jelly(newDrink);
			countJelly++;
		} else if (!(chckbx_Jelly.isSelected()) && countJelly % 2 == 1) {
			result.setPrice(result.getPrice() - 0.19);
			countJelly++;
		}

		if (chckbx_BlackBubble.isSelected() && countBubble % 2 == 0) {
			result = new BlackBubble(newDrink);
			countBubble++;
		} else if (!(chckbx_BlackBubble.isSelected()) && countBubble % 2 == 1) {
			result.setPrice(result.getPrice() - 0.1);
			countBubble++;
		}
		if (chckbx_BlackBubble.isSelected() || chckbx_Soy.isSelected() || chckbx_Jelly.isSelected()
				|| chckbx_Flan.isSelected()) {
			lbldescription.setText("Total :  " + (double) Math.round(result.getPrice() * 100) / 100 + "$");

		} else {
			lbldescription.setText("Total :  " + (double) Math.round(newDrink.getPrice() * 100) / 100 + "$");
		}
		newDrink = result;
		return result;
	}

	public void refreshByConfirm() {
		countBubble = 0;
		countSoy = 0;
		countJelly = 0;
		countFlan = 0;
		checkboxGroup_Drink.clearSelection();
		drinkPanel.setEnabled(true);
		checkboxGroup_Size.clearSelection();
		for (int i = 0; i < drinkPanel.getComponentCount(); i++) {
			drinkPanel.getComponent(i).setEnabled(true);
		}
		chckbx_BlackBubble.setSelected(false);
		chckbx_Soy.setSelected(false);
		chckbx_Flan.setSelected(false);
		chckbx_Jelly.setSelected(false);
	}

	public void ischeck(String name) {
		for (int i = 0; i < drinkPanel.getComponentCount(); i++) {
			if (drinkPanel.getComponent(i).getName().equals(name)) {
				checkboxGroup_Drink.setSelected((ButtonModel) drinkPanel.getComponent(i), true);
			}
		}
	}
	
	public String getIsDrinkChose() {
		return isDrinkChose;
	}
}
