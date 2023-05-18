package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.NetManagement;
import View.Login;

public class LogOutController implements ActionListener {
	private Login login;
	
	public LogOutController(Login login) {
		this.login = login;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		login.getUserNameText().setText(null);
		login.getPasswordField().setText(null);
		login.frame.frame.setVisible(false);
		login.setVisible(true);
	}
}
