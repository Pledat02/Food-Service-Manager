package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Model.AItem;
import Model.BottledDrink;
import Model.Combo;
import Model.Drink;
import Model.Food;
import Model.NetManagement;
import View.Frame;
import View.TransactionHistory;

public class BagController implements ActionListener {
	private HashMap<String, Double> itemOrders = new HashMap<String, Double>();
	private Frame frame;
	private NetManagement netManagement = NetManagement.getInstance();

	public BagController(Frame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TableModel model = frame.history.getQuantityTable().getModel();
		boolean isOrder = true;
		if (e.getActionCommand().equals("Remove All")) {
			removeAll_Cart();
		} else if (e.getActionCommand().equals("Order")) {
			if (frame.getScrollpanepanel().getComponentCount() == 0) {
				JOptionPane.showMessageDialog(frame, "Your cart is empty!","Error", JOptionPane.ERROR_MESSAGE);
			} else {
				frame.getPaymentFrame().getLbAmountMomo()
						.setText("Amount: " + netManagement.getDiscountMomo(Double.valueOf(frame.getTextField_Amount()
								.getText().substring(0, frame.getTextField_Amount().getText().length() - 1))) + "$");
				frame.getPaymentFrame().getLbAmountBA()
						.setText("Amount: " + netManagement.getDiscountBA(Double.valueOf(frame.getTextField_Amount()
								.getText().substring(0, frame.getTextField_Amount().getText().length() - 1))) + "$");
				frame.getPaymentFrame().getLbAmountCash()
						.setText("Amount: " + netManagement.getDiscountCash(Double.valueOf(frame.getTextField_Amount()
								.getText().substring(0, frame.getTextField_Amount().getText().length() - 1))) + "$");
				for (Entry<String, Double> o : netManagement.getCartItems().entrySet()) {
					String[] strs = o.getKey().split("  ");
					if (netManagement.searchItem(o.getKey()) instanceof Combo) {
						Combo combo = (Combo) netManagement.searchItem(o.getKey());
						itemOrders.put(combo.getFood().getName(), (double) combo.getQuantity());
						itemOrders.put(combo.getDrink().getName(), (double) combo.getQuantity());

					} else {
						itemOrders.put(strs[0], o.getValue());
					}
				}

				for (int j = 0; j < model.getRowCount(); j++) {
					for (Entry<String, Double> o : itemOrders.entrySet()) {
						if (model.getValueAt(j, 0).equals(o.getKey())) {
							if (Double.valueOf(model.getValueAt(j, 1) + "") < o.getValue()) {
								JOptionPane.showMessageDialog(null,
										"The remaining quantity is not enough! " + "\n\n" + "Item: "
												+ model.getValueAt(j, 0) + ", RemainQuantity: "
												+ model.getValueAt(j, 1),
										"Error", JOptionPane.ERROR_MESSAGE);
								isOrder = false;
								break;
							}
						}
					}
				}
				if (isOrder) {
					int choose = JOptionPane.showConfirmDialog(frame, "Cofirm your order ?", "Confirm",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (choose == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "Choose your payment method!");
						frame.getPaymentFrame().setVisible(true);

						for (int i = 0; i < model.getRowCount(); i++) {
							for (Entry<String, Double> o : itemOrders.entrySet()) {
								if (model.getValueAt(i, 0).equals(o.getKey())) {
									frame.history.getQuantityTable().getModel().setValueAt(
											(Double.valueOf(model.getValueAt(i, 1) + "") - o.getValue()) + "", i, 1);
								}

							}
						}
					}
				}
				itemOrders.clear();
			}
		} else {
			addToCart(e.getActionCommand());
		}

	}

	public void removeAll_Cart() {
		frame.getScrollpanepanel().removeAll();
		netManagement.getCartItems().clear();
		netManagement.getPriceHashMap().clear();
		frame.getScrollpanepanel().updateUI();
		frame.getTextField_Amount().setText("0");
		frame.getScrollpanepanel().setName(null);
	}

	public void addToCart(String actionCommand) {
		AItem item = null;
		frame.getScrollpanepanel().setName("unNull");
		String[] strs = actionCommand.split("/");
		if (frame.getCurrent().equals("COMBO")) {
			if (strs[3].equalsIgnoreCase("Choose Drink")) {
				JOptionPane.showMessageDialog(null, "Please choose a beverage", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				String name = strs[0];
				String nameFood = strs[1];
				String foodprice = strs[2];
				Food food = new Food(nameFood, Double.valueOf(foodprice), null);
				String nameDrink = strs[3];
				String drinkprice = strs[4];
				Drink drink = new BottledDrink(nameDrink, Double.valueOf(drinkprice), null);
				item = new Combo(name, food, drink);
			}
		} else if (frame.getCurrent().equals("FOOD")) {
			String name = strs[0];
			String price = strs[1];
			item = new Food(name, Double.valueOf(price), null);

		} else if (frame.getCurrent().equals("DRINK")) {
			String name = strs[0];
			String price = strs[1];
			item = new BottledDrink(name, Double.valueOf(price), null);
		}
		if (frame.getCurrent().equals("COMBO") && strs[3].equalsIgnoreCase("Choose Drink")) {
		} else {
			netManagement.addToCart(item);
			frame.refreshCart();

		}
	}
//	public void setEnableFalseComponent(String panelName,JPanel Panel) {
//		for (int i = 0; i < Panel.getComponentCount(); i++) {
//			if(panelName.equals(Panel.getComponent(i).getName())) {
//				Panel.getComponent(i).setEnabled(false);
//				System.out.println(Panel.getComponent(i).getName());
//				break;
//			}
//		}
//	}

}
