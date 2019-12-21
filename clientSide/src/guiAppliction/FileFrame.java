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
	JPanel panel_up,panel_down;//两个上传下载的面板
	JTextField DoctextField,filetextField;//用于保存文件上传下载路径文本框
	JTabbedPane tabbedPane ;//选项卡面板
	JTextArea textArea;
	
	public FileFrame() {		
		setTitle("文件管理界面");
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
		
		//文件下载卡片
		JPanel panel_down = new JPanel();
		tabbedPane.addTab("文件下载", null, panel_down, null);
		panel_down.setLayout(null);		
				
		JScrollPane scrollPane = new JScrollPane();//在下载面板中添加一个滚动面板
		panel_down.add(scrollPane);
		
		scrollPane.setBounds(10, 10, 359, 126);
		table = new JTable();	
		
		showFileInfoToTable();//将所有文件信息加入到		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	
		JButton downButton = new JButton("下载");
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downButtonActionPerformed(e);
			}
		});
		downButton.setBounds(121, 170, 65, 23);
		panel_down.add(downButton);
		
		JButton cancelButton_del = new JButton("返回");
		cancelButton_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButton_delActionPerformed(e);
			}
		});
		cancelButton_del.setBounds(187, 170, 65, 23);
		panel_down.add(cancelButton_del);
		//将所有下载组件添加至面板中
		//文件上传卡片
		panel_up = new JPanel();
		tabbedPane.addTab("文件上传", null, panel_up, null);
		panel_up.setLayout(null);
		
		JLabel DocLabel = new JLabel("档案号");
		DocLabel.setBounds(32, 28, 54, 15);
		panel_up.add(DocLabel);		
		
		JLabel DescriptionLabel = new JLabel("档案描述");
		DescriptionLabel.setBounds(32, 71, 54, 15);
		panel_up.add(DescriptionLabel);
		
		JLabel fileLabel = new JLabel("档案文件名");
		fileLabel.setBounds(32, 151, 65, 15);
		panel_up.add(fileLabel);
		
		DoctextField = new JTextField();//使用类中的变量
		DoctextField.setBounds(121, 25, 131, 21);
		panel_up.add(DoctextField);//加入上传面板中去
		DoctextField.setColumns(10);
		
		JButton upButton = new JButton("上传");
		upButton.addActionListener(new ActionListener() {//对上传进行监听
			public void actionPerformed(ActionEvent e) {
				upButtonActionPerformed(e);				
			}
		});
		upButton.setBounds(121, 188, 65, 23);
		panel_up.add(upButton);
		
		JButton cancelButton = new JButton("取消");
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
		
		JButton fileButton = new JButton("打开");
		fileButton.addActionListener(new ActionListener() {//为文件打开按钮添加点击事件
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
		
		
		tabbedPane.addChangeListener(new ChangeListener() {//为选项卡面板添加监听
			public void stateChanged(ChangeEvent evt) {
				jTabbedPaneStateChanged(evt);
			}
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		if (MainGui.currentUser.getRole().equalsIgnoreCase("Operator")) //判断角色
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
	
	public void setTabSeq(int index){//设置进入时的面板
		tabbedPane.setSelectedIndex(index);
	}
	
	private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//在选项卡面板状态改变时
		if(tabbedPane.getSelectedIndex()==0){
			showFileInfoToTable();
		}		
	}
	
	public void showFileInfoToTable() {//展示文件列表
		try {
			String[] colName = {"档案号", "创建者", "时间","文件名","描述"};
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
			tableModel = new DefaultTableModel(tableValue, colName);//对列表进行构造
			table.setModel(tableModel);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void upButtonActionPerformed(ActionEvent evt) {//对确定上传按钮的监听
	try{
		if (JOptionPane.showConfirmDialog(this,
				"确定上传档案吗？\t\n单击确定按钮将上传。", "确认对话框",
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
			textArea.setText("");//将所有文本框滞空
			JOptionPane.showMessageDialog(this, "上传成功！");	
		}
	}catch(Exception ex){
		ex.printStackTrace();
		JOptionPane.showMessageDialog(this,"上传文件失败！");
	}
}
	
	private void cancelButtonActionPerformed(ActionEvent evt){//取消按钮的监听
		DoctextField.setText("");
		filetextField.setText("");
		textArea.setText("");
	}
	
	private void downButtonActionPerformed(ActionEvent evt){//对下载按钮的监听
		try{
			if (JOptionPane.showConfirmDialog(this,
					"确定要下载档案吗？\t\n单击确定按钮将下载。", "确认对话框",
					JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				int currentRow = table.getSelectedRow();
				String documentSn = "";//文件ID
				String filename = "";						
				Object object = tableModel.getValueAt(currentRow, 0);
				if (object != null) {
					documentSn = object.toString();
					filename = (String)tableModel.getValueAt(currentRow, 3);
				}

				FileDialog dlg_save=new FileDialog(this," 保存文件对话框",FileDialog.SAVE);//生成文件选择对话窗
				dlg_save.setFile(filename);
				dlg_save.setVisible(true);
				if(dlg_save.getFile()!=null){  
		            File downfile=new File(dlg_save.getDirectory(),dlg_save.getFile());//获取下载路径
		            
//		            MainGui.currentUser.downloadFile(documentSn, downfile.getAbsolutePath());
                    DocumentServiceClient client =new  DocumentServiceClient();
                    client.downloadDocument(documentSn, downfile.getAbsolutePath());
		            
		            JOptionPane.showMessageDialog(this, "下载成功！");				
				}
			}
		}catch(Exception ex){
			 JOptionPane.showMessageDialog(this, "下载失败！");
			 System.out.print(ex.getMessage());
		}
	}
	
	private void fileButtonActionListener(ActionEvent evt){//对选择文件按钮的监听
		FileDialog dlg_open=new FileDialog(this,"打开文件对话框",FileDialog.LOAD);//创建一个文件对话框
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

