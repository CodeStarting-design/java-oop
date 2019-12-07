package 多线程综合实验;

import javax.swing.JFrame;

public class MainGui extends JFrame {
	
	public static User currentUser;
	public static void main(String[] args) throws Exception {
		LoginFrame loginframe= new LoginFrame();
		loginframe.setVisible(true);
	}
	
}
