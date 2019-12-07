package 多线程综合实验;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BrowserGui extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BrowserGui frame = new BrowserGui();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public BrowserGui() {
		setTitle("\u6863\u6848\u6D4F\u89C8\u5458\u754C\u9762");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_2 = new JMenu("\u7528\u6237\u7BA1\u7406");
		mnNewMenu_2.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_1 = new JMenu("\u6863\u6848\u7BA1\u7406");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u6863\u6848\u4E0A\u4F20");
		mntmNewMenuItem_1.setBackground(Color.LIGHT_GRAY);
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u6863\u6848\u4E0B\u8F7D");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFrame filedown=new FileFrame();
				filedown.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenu mnNewMenu = new JMenu("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u4FE1\u606F\u4FEE\u6539");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelfFrame broSelf=new SelfFrame();
			        broSelf.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
