package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JOptionPane;

import Model.NetManagement;
import View.Frame;
import View.Login;

public class CheckLoginAccount implements ActionListener, KeyListener {
	private Login  login;
	private NetManagement netManagement= NetManagement.getInstance();
	
	public CheckLoginAccount(Login login) {
		this.login = login;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = login.getUserNameText().getText();
		String password = new String(login.getPasswordField().getPassword());
		if (check(username,password)) {
			login.frame.getLblcurrentName().setText("Staff: " + username);
			login.frame.frame.setVisible(true);
			login.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "Wrong password or username!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean check(String userName, String password) {
		HashMap<String, String> accounts = netManagement.getAccounts();
		for (String key : accounts.keySet()) {
			if (key.equals(userName)) {
				if (accounts.get(key).equals(password)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String username = login.getUserNameText().getText();
			String password = new String(login.getPasswordField().getPassword());
			if (check(username,password)) {
				login.frame.getLblcurrentName().setText("Staff: " + username);
				login.frame.frame.setVisible(true);
				login.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Wrong password or username!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
