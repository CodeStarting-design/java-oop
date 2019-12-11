package ���߳��ۺ�ʵ��;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import sqlService.SQLForUser;


public class LoginFrame extends JFrame {
	private JLabel jLabel1;
	private JTextField nameTextField;
	private JLabel jLabel2;
	private JPasswordField passwordField;	
	private JButton okButton,cancelButton;
	private JPanel panel;
	
	public LoginFrame() {//���췽��
		setTitle("ϵͳ��¼");
		
		setSize(380, 200);
		Toolkit toolkit = getToolkit();                    // ���Toolkit����
		Dimension dimension = toolkit.getScreenSize();     // ���Dimension����
		int screenHeight = dimension.height;               // �����Ļ�ĸ߶�
		int screenWidth = dimension.width;                 // �����Ļ�Ŀ��
		int frm_Height = this.getHeight();                 // ��ô���ĸ߶�
		int frm_width = this.getWidth();                   // ��ô���Ŀ��
		this.setLocation((screenWidth - frm_width) / 2,
				(screenHeight - frm_Height) / 2);          // ʹ�ô��������ʾ
		setAlwaysOnTop(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		jLabel1 = new JLabel();
		jLabel1.setText("�û���");
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel1.setBounds(new Rectangle(86, 33, 60, 20));		
		panel.add(jLabel1);
				
		nameTextField = new JTextField();
		nameTextField.setBounds(new Rectangle(156, 33, 100, 20));
		nameTextField.setColumns(8);
		panel.add(nameTextField, null);
		
		jLabel2 = new JLabel();
		jLabel2.setBounds(new Rectangle(86, 63, 60, 20));
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel2.setText("����");
		panel.add(jLabel2);		
		
		passwordField = new JPasswordField();		
		passwordField.setBounds(new Rectangle(156, 63, 100, 20));
		passwordField.requestFocus();
		panel.add(passwordField, null);		
		
		okButton = new JButton();
		okButton.setText("ȷ��");
		okButton.setSize(new Dimension(60, 23));
		okButton.setLocation(new Point(86, 101));
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okButtonActionPerformed(e);
			}
		});
				
		panel.add(okButton, null);
		
		cancelButton= new JButton();
		cancelButton.setText("ȡ��");
		cancelButton.setSize(new Dimension(60, 23));
		cancelButton.setLocation(new Point(194, 101));	
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed(e);
			}
		});		
		
		panel.add(cancelButton, null);
	}
	private void okButtonActionPerformed(ActionEvent e) {	
		String name=nameTextField.getText();
		String password=String.valueOf(passwordField.getPassword());
		
//		try {
//			User user = userService.login(name, password);
//			Application.currentUser = user;
//			hideFrame();
//			MainFrame mainframe=new MainFrame();
////			mainframe.setCurrentUser(user);
//			mainframe.setVisible(true);
//		} catch (Exception e1) {
//			JOptionPane.showMessageDialog(panel,e1.getMessage(),"���뷴��",JOptionPane.YES_NO_OPTION);
//		}
		
			try {
				SQLForUser sql=new SQLForUser();
				User one=sql.login(name, password);
				if(one==null) {
				hideFrame();
				fail failIn =new fail();
				failIn.setVisible(true);
				}
				else 
					hideFrame();
					one.showMenu();
					MainGui.currentUser=one;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
	}

	private void cancelButtonActionPerformed(ActionEvent e){
		nameTextField.setText(null);
		passwordField.setText(null);
	}

	private void hideFrame(){
		this.dispose();
		//this.setVisible(false);
	}

	
}
