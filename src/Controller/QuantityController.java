package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.AItem;
import Model.NetManagement;
import View.Frame;

public class QuantityController implements ActionListener,KeyListener {
	private Frame frame;
	private NetManagement netManagement = NetManagement.getInstance();

	
	public QuantityController(Frame frame) {
		this.frame = frame;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().startsWith("add")) {
			plus(e.getActionCommand());
		}
		else if(e.getActionCommand().startsWith("sub")) {
			sub(e.getActionCommand());
		}
}
	public void plus(String actionCommand) {
		frame.getTextField().setText((Double.valueOf(frame.getTextField().getText()) + 1) + "");
		netManagement.addToCart(netManagement.searchItem(actionCommand.substring(3)));

		frame.refreshCart();
	}

	public void sub(String actionCommand) {

		frame.getTextField().setText((Double.valueOf(frame.getTextField().getText()) - 1) + "");
		netManagement.removeFromCart(netManagement.searchItem(actionCommand.substring(3)));
		AItem item = netManagement.searchItem(actionCommand.substring(3));
		if (item.getQuantity() <= 0) {
			netManagement.getCartItems().remove(actionCommand.substring(3));
		}
		frame.refreshCart();

	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (frame.getTextField().getText().equals("")) {
			frame.getTextField().setText("0");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (Integer.valueOf(frame.getTextField().getText()) < 0) {
				frame.getTextField().setText("1.0");
			} else if (Integer.valueOf(frame.getTextField().getText()) == 0) {
				netManagement.getCartItems().remove(frame.getObject().getKey());
				frame.getScrollpanepanel().remove(frame.getPanel_AItemInYourBill());
				frame.getScrollpanepanel().updateUI();
				;
			} else if (Integer.valueOf(frame.getTextField().getText()) % 1 != 0) {
				frame.getTextField().setText(Double.valueOf(frame.getTextField().getText()) / 1 + "");
			} else if (Integer.valueOf(frame.getTextField().getText()) % 1 == 0) {
				frame.getTextField().setText(Integer.valueOf(frame.getTextField().getText()) + "");
				netManagement.searchItem(frame.getObject().getKey()).setQuantity(Integer.valueOf(frame.getTextField().getText()));
				frame.getObject().setValue(Double.valueOf(frame.getTextField().getText()));
				netManagement.getPriceHashMap().putAll(netManagement.getCartItems());
				frame.getTextField_Amount().setText((double) Math.round(netManagement.getAmount() * 100) / 100 + "$");
			}

		}

	}


}
