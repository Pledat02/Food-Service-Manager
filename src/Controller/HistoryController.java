package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Frame;

public class HistoryController implements ActionListener {
	Frame frame;
	
	
	public HistoryController(Frame frame) {
		this.frame = frame;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		frame.history.setVisible(true);
	}

}
