package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.NetManagement;
import View.Frame;

public class ChangeSlideController implements ActionListener{
	private Frame frame;
	private NetManagement netManagement= NetManagement.getInstance();
	
	public ChangeSlideController(Frame frame) {
		this.frame = frame;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		changeSlide(e.getActionCommand());
	}
	int countCombo = 1;
	int countFood = 1;
	int countDrink = 1;

	public void changeSlide(String actionCommand) {

		if (frame.getCurrent().equals("COMBO")) {

			if (actionCommand.equals("Next")) {
				frame.getCardLayout_Combo().next(frame.getCardPanel_Combo());
				countCombo++;
				if (countCombo > 1) {
					frame.getBtnPreviousCombo().setEnabled(true);
				}
				if (netManagement.countPanel(netManagement.getComboList(), 4) == countCombo) {
					frame.getBtnNextCombo().setEnabled(false);
				}
			}
			if (actionCommand.equals("Previous")) {
				frame.getCardLayout_Combo().previous(frame.getCardPanel_Combo());
				countCombo--;
				if (countCombo < netManagement.countPanel(netManagement.getComboList(), 4)) {
					frame.getBtnNextCombo().setEnabled(true);
				}
				if (1 == countCombo) {
					frame.getBtnPreviousCombo().setEnabled(false);
				}
			}

		} else if (frame.getCurrent().equals("FOOD")) {

			if (actionCommand.equals("Next")) {
				frame.getCardLayout_Food().next(frame.getCardPanel_Food());
				countFood++;
				if (countFood > 1) {
					frame.getBtnPreviousFood().setEnabled(true);
				}
				if (netManagement.countPanel(netManagement.getFoodList(), 6) == countFood) {
					frame.getBtnNextFood().setEnabled(false);
				}
			} else if (actionCommand.equals("Previous")) {
				frame.getCardLayout_Food().previous(frame.getCardPanel_Food());
				countFood--;
				if (countFood < netManagement.countPanel(netManagement.getFoodList(), 6)) {
					frame.getBtnNextFood().setEnabled(true);
				}
				if (1 == countFood) {
					frame.getBtnPreviousFood().setEnabled(false);
				}
			}

		} else if (frame.getCurrent().equals("DRINK")) {
			if (actionCommand.equals("Next")) {
				frame.getCardLayout_Drink().next(frame.getCardPanel_Drink());
				countDrink++;
				if (countDrink > 1) {
					frame.getBtnPreviousDrink().setEnabled(true);
				}
				if (netManagement.countPanel(netManagement.getDrinkList(), 8) == countDrink) {
					frame.getBtnNextDrink().setEnabled(false);
				}
			} else if (actionCommand.equals("Previous")) {
				frame.getCardLayout_Drink().previous(frame.getCardPanel_Drink());
				countDrink--;
				if (countDrink < netManagement.countPanel(netManagement.getDrinkList(), 8)) {
					frame.getBtnNextDrink().setEnabled(true);
				}
				if (1 == countDrink) {
					frame.getBtnPreviousDrink().setEnabled(false);
				}
			}
		}

	}

}
