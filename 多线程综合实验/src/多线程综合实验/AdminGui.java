package 多线程综合实验;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AdminGui extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminGui frame = new AdminGui();
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
	public AdminGui() {
		setTitle("\u7CFB\u7EDF\u7BA1\u7406\u5458\u754C\u9762");
         
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u7528\u6237\u7BA1\u7406");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u4FEE\u6539\u7528\u6237");
		mntmNewMenuItem.addActionListener(new ActionListener() {//对修改用户的监听
			public void actionPerformed(ActionEvent e) {
				UserFrame userOper =new UserFrame();
				userOper.setVisible(true);
				userOper.setTabSeq(1);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u5220\u9664\u7528\u6237");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {//对删除用户
			public void actionPerformed(ActionEvent e) {
				UserFrame userOper =new UserFrame();
				userOper.setVisible(true);
				userOper.setTabSeq(2);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u65B0\u589E\u7528\u6237");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {//对新增用户
			public void actionPerformed(ActionEvent e) {
				UserFrame userOper =new UserFrame();
				userOper.setVisible(true);
				userOper.setTabSeq(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("\u6863\u6848\u7BA1\u7406");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u6863\u6848\u4E0A\u4F20");
		mntmNewMenuItem_3.setBackground(Color.LIGHT_GRAY);
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u6863\u6848\u4E0B\u8F7D");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {//对文件下载按钮的监听
			public void actionPerformed(ActionEvent e) {
				FileFrame filedown=new FileFrame();
				filedown.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_2 = new JMenu("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("\u4FE1\u606F\u4FEE\u6539");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelfFrame adminSelf=new SelfFrame();
				adminSelf.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
