package guiAppliction;

import javax.swing.JFrame;

import javaSystem.User;



public class BaseFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected User currentUser = MainGui.currentUser;	
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}
