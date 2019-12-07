package 多线程综合实验;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class OperatorGui extends JFrame {

	private JPanel contentPane;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OperatorGui frame = new OperatorGui();
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
	public OperatorGui() {
		setTitle("\u6863\u6848\u5F55\u5165\u5458\u754C\u9762");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u7528\u6237\u7BA1\u7406");
		mnNewMenu.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("\u6863\u6848\u7BA1\u7406");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u6863\u6848\u4E0A\u4F20");
		mntmNewMenuItem.addActionListener(new ActionListener() {//上传按钮的监听
			public void actionPerformed(ActionEvent e) {
				FileFrame fileup=new FileFrame();
				fileup.setVisible(true);
				fileup.setTabSeq(1);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u6863\u6848\u4E0B\u8F7D");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFrame filedown=new FileFrame();
				filedown.setVisible(true);
				filedown.setTabSeq(0);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_3 = new JMenu("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u4FE1\u606F\u4FEE\u6539");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelfFrame operSelf=new SelfFrame();
				operSelf.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
