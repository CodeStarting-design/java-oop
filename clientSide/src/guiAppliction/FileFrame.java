package guiAppliction;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import clientSide.DocumentServiceClient;
import javaSystem.Doc;
import sqlService.SQLForDoc;


public class FileFrame extends JFrame{
	private DefaultTableModel tableModel;
	private JTable table;
	JPanel panel_up,panel_down;//�����ϴ����ص����
	JTextField DoctextField,filetextField;//���ڱ����ļ��ϴ�����·���ı���
	JTabbedPane tabbedPane ;//ѡ����
	JTextArea textArea;
	
	public FileFrame() {		
		setTitle("�ļ��������");
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
		
		//�ļ����ؿ�Ƭ
		JPanel panel_down = new JPanel();
		tabbedPane.addTab("�ļ�����", null, panel_down, null);
		panel_down.setLayout(null);		
				
		JScrollPane scrollPane = new JScrollPane();//��������������һ���������
		panel_down.add(scrollPane);
		
		scrollPane.setBounds(10, 10, 359, 126);
		table = new JTable();	
		
		showFileInfoToTable();//�������ļ���Ϣ���뵽		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	
		JButton downButton = new JButton("����");
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downButtonActionPerformed(e);
			}
		});
		downButton.setBounds(121, 170, 65, 23);
		panel_down.add(downButton);
		
		JButton cancelButton_del = new JButton("����");
		cancelButton_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButton_delActionPerformed(e);
			}
		});
		cancelButton_del.setBounds(187, 170, 65, 23);
		panel_down.add(cancelButton_del);
		//�����������������������
		//�ļ��ϴ���Ƭ
		panel_up = new JPanel();
		tabbedPane.addTab("�ļ��ϴ�", null, panel_up, null);
		panel_up.setLayout(null);
		
		JLabel DocLabel = new JLabel("������");
		DocLabel.setBounds(32, 28, 54, 15);
		panel_up.add(DocLabel);		
		
		JLabel DescriptionLabel = new JLabel("��������");
		DescriptionLabel.setBounds(32, 71, 54, 15);
		panel_up.add(DescriptionLabel);
		
		JLabel fileLabel = new JLabel("�����ļ���");
		fileLabel.setBounds(32, 151, 65, 15);
		panel_up.add(fileLabel);
		
		DoctextField = new JTextField();//ʹ�����еı���
		DoctextField.setBounds(121, 25, 131, 21);
		panel_up.add(DoctextField);//�����ϴ������ȥ
		DoctextField.setColumns(10);
		
		JButton upButton = new JButton("�ϴ�");
		upButton.addActionListener(new ActionListener() {//���ϴ����м���
			public void actionPerformed(ActionEvent e) {
				upButtonActionPerformed(e);				
			}
		});
		upButton.setBounds(121, 188, 65, 23);
		panel_up.add(upButton);
		
		JButton cancelButton = new JButton("ȡ��");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed(e);
			}
		});
		cancelButton.setBounds(187, 188, 65, 23);
		panel_up.add(cancelButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(123, 67, 129, 60);
		panel_up.add(scrollPane_1);
		
		textArea = new JTextArea();
//		textArea.setBounds(123, 67, 129, 60);
//		scrollPane_1.add(textArea);
		scrollPane_1.setViewportView(textArea);
		
		JButton fileButton = new JButton("��");
		fileButton.addActionListener(new ActionListener() {//Ϊ�ļ��򿪰�ť��ӵ���¼�
			public void actionPerformed(ActionEvent e) {
				fileButtonActionListener(e);
			}
		});
		fileButton.setBounds(262, 147, 65, 23);
		panel_up.add(fileButton);
		
		filetextField = new JTextField();
		filetextField.setBounds(121, 148, 131, 21);
		panel_up.add(filetextField);
		filetextField.setColumns(255);
		
		
		tabbedPane.addChangeListener(new ChangeListener() {//Ϊѡ������Ӽ���
			public void stateChanged(ChangeEvent evt) {
				jTabbedPaneStateChanged(evt);
			}
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		if (MainGui.currentUser.getRole().equalsIgnoreCase("Operator")) //�жϽ�ɫ
			tabbedPane.setEnabledAt(1, true);
		else
			tabbedPane.setEnabledAt(1, false);
	}
	
	public static void launch() {	   
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FileFrame().setVisible(true);
			}
		});
	}
	
	public void setTabSeq(int index){//���ý���ʱ�����
		tabbedPane.setSelectedIndex(index);
	}
	
	private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//��ѡ����״̬�ı�ʱ
		if(tabbedPane.getSelectedIndex()==0){
			showFileInfoToTable();
		}		
	}
	
	public void showFileInfoToTable() {//չʾ�ļ��б�
		try {
			String[] colName = {"������", "������", "ʱ��","�ļ���","����"};
			String[][] tableValue = new String[10][5];
			DocumentServiceClient client =new DocumentServiceClient();
			//List<Doc> documents = DataProcessing.getListDoc();
			//List<Doc> documents = sql.findAllOnes();
			List<Doc> documents = client.getAllDocuments();
			for (int row = 0; row < documents.size(); row++) {
				Doc doc = documents.get(row);
				tableValue[row][0]=doc.getID();
				tableValue[row][1]=doc.getCreator();
				tableValue[row][2]=(doc.getTimestamp()).toString();
				tableValue[row][3]=Doc.getFileName(doc.getFilename());
				tableValue[row][4]=doc.getDescription();
			}
			tableModel = new DefaultTableModel(tableValue, colName);//���б���й���
			table.setModel(tableModel);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void upButtonActionPerformed(ActionEvent evt) {//��ȷ���ϴ���ť�ļ���
	try{
		if (JOptionPane.showConfirmDialog(this,
				"ȷ���ϴ�������\t\n����ȷ����ť���ϴ���", "ȷ�϶Ի���",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			String ID=DoctextField.getText();
			String filename=filetextField.getText();
			String description=(String)textArea.getText();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
			Doc document =new Doc(ID,MainGui.currentUser.getName(),timestamp,description,filename);
			//Operator.uploadFile(ID, description, filename,MainGui.currentUser );
			DocumentServiceClient client =new DocumentServiceClient();
			client.uploadDocument(document);
			DoctextField.setText("");
			filetextField.setText("");
			textArea.setText("");//�������ı����Ϳ�
			JOptionPane.showMessageDialog(this, "�ϴ��ɹ���");	
		}
	}catch(Exception ex){
		ex.printStackTrace();
		JOptionPane.showMessageDialog(this,"�ϴ��ļ�ʧ�ܣ�");
	}
}
	
	private void cancelButtonActionPerformed(ActionEvent evt){//ȡ����ť�ļ���
		DoctextField.setText("");
		filetextField.setText("");
		textArea.setText("");
	}
	
	private void downButtonActionPerformed(ActionEvent evt){//�����ذ�ť�ļ���
		try{
			if (JOptionPane.showConfirmDialog(this,
					"ȷ��Ҫ���ص�����\t\n����ȷ����ť�����ء�", "ȷ�϶Ի���",
					JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				int currentRow = table.getSelectedRow();
				String documentSn = "";//�ļ�ID
				String filename = "";						
				Object object = tableModel.getValueAt(currentRow, 0);
				if (object != null) {
					documentSn = object.toString();
					filename = (String)tableModel.getValueAt(currentRow, 3);
				}

				FileDialog dlg_save=new FileDialog(this," �����ļ��Ի���",FileDialog.SAVE);//�����ļ�ѡ��Ի���
				dlg_save.setFile(filename);
				dlg_save.setVisible(true);
				if(dlg_save.getFile()!=null){  
		            File downfile=new File(dlg_save.getDirectory(),dlg_save.getFile());//��ȡ����·��
		            
//		            MainGui.currentUser.downloadFile(documentSn, downfile.getAbsolutePath());
                    DocumentServiceClient client =new  DocumentServiceClient();
                    client.downloadDocument(documentSn, downfile.getAbsolutePath());
		            
		            JOptionPane.showMessageDialog(this, "���سɹ���");				
				}
			}
		}catch(Exception ex){
			 JOptionPane.showMessageDialog(this, "����ʧ�ܣ�");
			 System.out.print(ex.getMessage());
		}
	}
	
	private void fileButtonActionListener(ActionEvent evt){//��ѡ���ļ���ť�ļ���
		FileDialog dlg_open=new FileDialog(this,"���ļ��Ի���",FileDialog.LOAD);//����һ���ļ��Ի���
		dlg_open.setVisible(true);
		if(dlg_open.getFile()!=null){  
			//File file=new File(dlg_open.getDirectory(),dlg_open.getFile());  
            filetextField.setText(dlg_open.getDirectory()+dlg_open.getFile());
        }  
		
	}
	
	private void cancelButton_delActionPerformed(ActionEvent evt){
		this.dispose();
	}
	
	public static void main(String[] args) {
		FileFrame.launch();				
	}
}

