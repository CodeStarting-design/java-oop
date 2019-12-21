package guiAppliction;

import javax.swing.JFrame;

import javaSystem.User;

public class MainGui extends JFrame {
	
	public static User currentUser;
	public static void main(String[] args) throws Exception {
		LoginFrame loginframe= new LoginFrame();
		loginframe.setVisible(true);
	}
	
}
