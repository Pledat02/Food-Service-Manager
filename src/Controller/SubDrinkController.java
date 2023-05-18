package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;

import View.Frame;
import View.SubDrinkFrame;

public class SubDrinkController {
	private SubDrinkFrame subDrinkFrame;
	private ActionListener sizeController, drinkController, toppingController;
	private boolean isChoose = false;

	public SubDrinkController(SubDrinkFrame subDrinkFrame) {
		this.subDrinkFrame = subDrinkFrame;
		sizeController = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (subDrinkFrame.getIsDrinkChose() == null) {
					JOptionPane.showMessageDialog(null, "Please choose a beverage", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					subDrinkFrame.searchSize(e.getActionCommand());
				}
			}
		};
		drinkController = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				subDrinkFrame.searchDrink(e.getActionCommand());
			}
		};

		toppingController = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					subDrinkFrame.addTopping();
			}
		};

	}

	public ActionListener getToppingController() {
		return toppingController;
	}

	public boolean isChoose() {
		return isChoose;
	}

	public SubDrinkFrame getSubDrinkFrame() {
		return subDrinkFrame;
	}

	public ActionListener getSizeController() {
		return sizeController;
	}

	public ActionListener getDrinkController() {
		return drinkController;
	}

}
