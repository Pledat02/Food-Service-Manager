package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;

import Model.Combo;
import Model.Drink;
import Model.NetManagement;
import View.Frame;

public class ChooseDrinkController {
	private Frame frame;
	private NetManagement netManagement = NetManagement.getInstance();
	private ActionListener chooseDrinkController, chooseDrinkControllerForDrink, confirmControllerForDrink,
			confirmControllerForCombo;

	public ChooseDrinkController(Frame frame) {
		this.frame = frame;
		chooseDrinkController = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.subDrinkFrame.setVisible(true);
				frame.nameCombo = e.getActionCommand();

			}
		};
		confirmControllerForCombo = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmForCombo();
			}
		};
		confirmControllerForDrink = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmForDrink();
			}
		};
		chooseDrinkControllerForDrink = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkDrink(e.getActionCommand());
				frame.subDrinkFrame.setVisible(true);
				frame.nameCombo = e.getActionCommand();
			}
		};
	}

	public void checkDrink(String actioncommand) {
		frame.getSubDrinkFrame().drinkPanel.setEnabled(false);
		for (int i = 0; i < frame.getSubDrinkFrame().drinkPanel.getComponentCount(); i++) {
			frame.getSubDrinkFrame().drinkPanel.getComponent(i).setEnabled(false);
		}

		Enumeration<AbstractButton> e = frame.getSubDrinkFrame().checkboxGroup_Drink.getElements();
		AbstractButton b = null;
		for (int i = 0; i <= frame.getSubDrinkFrame().checkboxGroup_Drink.getButtonCount(); i++) {
			if (e.hasMoreElements()) {
				b = e.nextElement();
				if (b.getName().equals(actioncommand)) {
					b.setSelected(true);
					frame.getSubDrinkFrame().searchDrink(actioncommand);
				}
			}

		}
	}

	public void confirmForCombo() {
		Drink replacedDrink = frame.getSubDrinkFrame().addTopping();
		for (Combo combo : netManagement.getComboList()) {
			if (combo.getName().equals(frame.nameCombo)) {
				replacedDrink.setSrcImage(netManagement.searchDrink(replacedDrink.getName()).getSrcImage());
				combo.setDrink(replacedDrink);
				combo.setPrice(combo.getPriceCombo());
			}
		}
		frame.getCardPanel_Combo().removeAll();
		frame.refreshCarPanel_Combo();
		frame.getCardPanel_Combo().updateUI();
		frame.getSubDrinkFrame().dispose();
		frame.getSubDrinkFrame().refreshByConfirm();
	}

	public void confirmForDrink() {
		Drink replacedDrink = frame.getSubDrinkFrame().addTopping();
		for (int i = 0; i < netManagement.getDrinkList().size(); i++) {
			if (netManagement.getDrinkList().get(i).getName().equals(frame.nameCombo)) {
				netManagement.getDrinkList().get(i).setPrice((double) Math.round(replacedDrink.getPrice() * 100) / 100);
				break;
			}
		}
		frame.getCardPanel_Drink().removeAll();
		frame.refreshCardPanel_Drink();
		frame.getCardPanel_Drink().updateUI();
		frame.getSubDrinkFrame().dispose();
		frame.getSubDrinkFrame().refreshByConfirm();
	}

	public ActionListener getChooseDrinkControllerForDrink() {
		return chooseDrinkControllerForDrink;
	}

	public ActionListener getChooseDrinkController() {
		return chooseDrinkController;
	}

	public ActionListener getConfirmControllerForDrink() {
		return confirmControllerForDrink;
	}

	public ActionListener getConfirmControllerForCombo() {
		return confirmControllerForCombo;
	}

}
