package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Frame;
import View.Frame;

public class ChangeItemController implements ActionListener {
	private Frame frame;

	public ChangeItemController(Frame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		change(e.getActionCommand());
	}

	public void change(String actionCommand) {
		frame.getCardLayout().show(frame.getPanel_Card(), actionCommand);
		if (actionCommand.equals("COMBO") || actionCommand.equals("DRINK") || actionCommand.equals("FOOD"))
			frame.setCurrent(actionCommand);

	}
}
