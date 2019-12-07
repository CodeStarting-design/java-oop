package ���߳��ۺ�ʵ��;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
//import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;


public class UserFrame extends JFrame {
	
	private DefaultTableModel tableModel;
	private JTable table;
	JPanel panel_add,panel_mod,panel_del;
	JTextField nametextField;
	JPasswordField passwordField, passwordField_mod;
	JComboBox<String> comboBox,namecomboBox,rolecomboBox;
	JTabbedPane tabbedPane ;
	
	public UserFrame() {		
		setTitle("�û��������");
		setSize(400, 300);
		Toolkit toolkit = getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenHeight = dimension.height;
		int screenWidth = dimension.width;
		int frm_Height = this.getHeight();
		int frm_width = this.getWidth();
		this.setLocation((screenWidth - frm_width) / 2,
				(screenHeight - frm_Height) / 2);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				jTabbedPaneStateChanged(evt);
			}
		});
		
		//�����û���Ƭ
		panel_add = new JPanel();
		tabbedPane.addTab("�����û�", null, panel_add, null);
		panel_add.setLayout(null);
		
		JLabel nameLabel = new JLabel("�û���");
		nameLabel.setBounds(32, 28, 54, 15);
		panel_add.add(nameLabel);
		
		JLabel keyLabel = new JLabel("����");
		keyLabel.setBounds(32, 71, 54, 15);
		panel_add.add(keyLabel);
		
		JLabel roleLabel = new JLabel("��ɫ");
		roleLabel.setBounds(32, 121, 54, 15);
		panel_add.add(roleLabel);
		
		nametextField = new JTextField();
		nametextField.setBounds(121, 25, 131, 21);
		panel_add.add(nametextField);
		nametextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(121, 68, 131, 21);
		panel_add.add(passwordField);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(121, 118, 131, 21);
		comboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] {"Administrator", "Operator","Browser"}));
		comboBox.setSelectedIndex(2);
		panel_add.add(comboBox);
		
		JButton addButton = new JButton("����");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addButtonActionPerformed(e);				
			}
		});
		addButton.setBounds(121, 170, 65, 23);
		panel_add.add(addButton);
		
		JButton cancelButton = new JButton("ȡ��");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed(e);
			}
		});
		cancelButton.setBounds(187, 170, 65, 23);
		panel_add.add(cancelButton);
		
		//�޸��û���Ƭ
		JPanel panel_mod = new JPanel();
		tabbedPane.addTab("�޸��û�", null, panel_mod, null);
		panel_mod.setLayout(null);
		
		nameLabel = new JLabel("�û���");
		nameLabel.setBounds(32, 28, 54, 15);
		panel_mod.add(nameLabel);
		
		keyLabel = new JLabel("����");
		keyLabel.setBounds(32, 71, 54, 15);
		panel_mod.add(keyLabel);
		
		roleLabel = new JLabel("��ɫ");
		roleLabel.setBounds(32, 121, 54, 15);
		panel_mod.add(roleLabel);
		
		namecomboBox = new JComboBox<String>();
		namecomboBox.setBounds(121, 25, 131, 21);
		namecomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				namecomboBoxItemStateChanged(evt);
			}
		});
		panel_mod.add(namecomboBox);
		
		passwordField_mod = new JPasswordField();
		passwordField_mod.setBounds(121, 68, 131, 21);
		panel_mod.add(passwordField_mod);
		
		rolecomboBox = new JComboBox<String>();
		rolecomboBox.setBounds(121, 118, 131, 21);
		rolecomboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "Administrator", "Operator","Browser" }));			
		panel_mod.add(rolecomboBox);
		
		JButton modButton = new JButton("�޸�");
		modButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modButtonActionPerformed(e) ;
			}
		});
		modButton.setBounds(121, 170, 65, 23);
		panel_mod.add(modButton);
		
		JButton cancelButton_mod = new JButton("ȡ��");
		cancelButton_mod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButton_modActionPerformed(e);
			}
		});
		cancelButton_mod.setBounds(187, 170, 65, 23);
		panel_mod.add(cancelButton_mod);
		
		//ɾ���û���Ƭ
		JPanel panel_del = new JPanel();
		tabbedPane.addTab("ɾ���û�", null, panel_del, null);
		panel_del.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_del.add(scrollPane);
		
		scrollPane.setBounds(10, 10, 359, 126);
		table = new JTable();
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	
		JButton delButton = new JButton("ɾ��");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delButtonActionPerformed(e);//��ɾ����ť�ļ���
			}
		});
		delButton.setBounds(121, 170, 65, 23);
		panel_del.add(delButton);
		
		JButton cancelButton_del = new JButton("����");
		cancelButton_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButton_delActionPerformed(e);//�Է��ذ�ť�ļ���
			}
		});
		cancelButton_del.setBounds(187, 170, 65, 23);
		panel_del.add(cancelButton_del);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
	}
	
	public static void launch() { 
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new UserFrame().setVisible(true);
			}
		});
	}
	
	public void setTabSeq(int index){//�������ý������ʱ����ʾ�����
		tabbedPane.setSelectedIndex(index);
	}
	
	private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {
		if(tabbedPane.getSelectedIndex()==0){
			
		}
		if(tabbedPane.getSelectedIndex()==1){
			addUserToComboBox();			
		}
		if(tabbedPane.getSelectedIndex()==2){
			showUserInfoToTable();
		}
	}
	
	public void showUserInfoToTable() {
		try {
			String[] colName = {"�û���", "����", "��ɫ"};
			String[][] tableValue = new String[10][3];
			List<User> users = DataProcessing.getListUser();
			for (int row = 0; row < users.size(); row++) {
				User user = users.get(row);
				tableValue[row][0]=user.getName();
				tableValue[row][1]=user.getPassword();
				tableValue[row][2]=user.getClass().getSimpleName();
			}
			tableModel = new DefaultTableModel(tableValue, colName);
			table.setModel(tableModel);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void addUserToComboBox() {//�����е���Ϣ������
		try {
			namecomboBox.removeAllItems();

			List<User> users = DataProcessing.getListUser();
			for (User user : users) {
				namecomboBox.addItem(user.getName());
			}

		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void addButtonActionPerformed(ActionEvent evt) {
		try{
			if (JOptionPane.showConfirmDialog(this,
					"ȷ�������û���\t\n����ȷ����ť��������", "ȷ�϶Ի���",
					JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				String name=nametextField.getText();
				String password=new String(passwordField.getPassword());
				String role=(String)comboBox.getSelectedItem();
				
//				User user = null;
//				switch (role) {
//				case "Administrator" :
//					user = new Administrator(name,password,role);
//					break;
//				case "Operator" :
//					user = new Operator(name,password,role);
//					break;
//				case "Browser" :
//					user = new Browser(name,password,role);
//				}
//				
//				user.setName(name);
//				user.setPassword(password);
				
				Administrator.addUser(name,password,role);
				JOptionPane.showMessageDialog(this,"�����û��ɹ���");
				
				nametextField.setText("");
				passwordField.setText("");
				comboBox.setSelectedIndex(-1);
			}
		}catch(Exception ex){
			
		}
	}
	
	private void cancelButtonActionPerformed(ActionEvent evt){
		nametextField.setText("");
		passwordField.setText("");
		comboBox.setSelectedIndex(-1);
	}
	
	private void namecomboBoxItemStateChanged(ItemEvent evt){
		User user;
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			try{
				user = DataProcessing.searchUser((String)evt.getItem());
				passwordField_mod.setText(user.getPassword());
				rolecomboBox.setSelectedItem(user.getClass().getSimpleName());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	private void modButtonActionPerformed(ActionEvent evt){
		try{
			if (JOptionPane.showConfirmDialog(this,
					"ȷ��Ҫ�޸���Ϣ��\t\n����ȷ����ť���޸ġ�", "ȷ�϶Ի���",
					JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				String name=(String)namecomboBox.getSelectedItem();
				String password=new String(passwordField_mod.getPassword());			
				String role=(String)rolecomboBox.getSelectedItem();
				
			
				DataProcessing.update(name, password, role);
				
				JOptionPane.showMessageDialog(this,"�û���Ϣ�޸ĳɹ���");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void cancelButton_modActionPerformed(ActionEvent evt){
		User user;
		try{
			String name=(String)namecomboBox.getSelectedItem();
			user = DataProcessing.searchUser(name);
			passwordField_mod.setText(user.getPassword());
			rolecomboBox.setSelectedItem(user.getClass().getSimpleName());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void delButtonActionPerformed(ActionEvent evt){
		try{
			if (JOptionPane.showConfirmDialog(this,
					"ȷ��Ҫɾ���û���\t\n����ȷ����ť��ɾ����", "ȷ�϶Ի���",
					JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				int currentRow = table.getSelectedRow();
				String username = "";
				Object object = tableModel.getValueAt(currentRow, 0);
				System.out.println(currentRow);
				if (object != null) {
					username = object.toString();
				}

				if (Administrator.delUser(username)!=false){					
					JOptionPane.showMessageDialog(this, "ɾ���ɹ���");
					showUserInfoToTable();
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void cancelButton_delActionPerformed(ActionEvent evt){
		this.dispose();
	}

	public static void main(String[] args) {
		UserFrame.launch();				
	}
}
