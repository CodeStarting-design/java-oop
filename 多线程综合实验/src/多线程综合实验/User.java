package ���߳��ۺ�ʵ��;

import java.io.IOException;
import java.io.*;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

public abstract class User implements Serializable {
	private String name;//�û���
	private String password;//����
	private String role;//���ڱ�ʶ���ֲ�ͬ�Ľ�ɫ
	final static String filePath="D:\\OOPGit\\���߳��ۺ�ʵ��\\downloadfile";
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo() throws SQLException{
		//д�û���Ϣ���洢
		Scanner scan=new Scanner(System.in);
		System.out.print("�����������룺");
		String newCode=scan.next();
		if (DataProcessing.update(this.name, newCode, this.role)){
			this.password=newCode;
			System.out.println("�޸ĳɹ�");
			DataProcessing.saveAll();
			return true;
		}else
			return false;	
	}
	
	public abstract void showMenu();
	
	public boolean downloadFile() throws IOException, SQLException  {
		Scanner scan=new Scanner(System.in);
		System.out.print("��������Ҫ���ص��ļ�id��");
		String fileID=scan.next();
		 Doc downFile=DataProcessing.searchDoc(fileID);
		 if(downFile==null) { System.out.println("ϵͳ��δ�������ļ�"); return false;}
		double ranValue=Math.random();
		if (ranValue>0.5)
			throw new IOException( "Error in accessing file" );
		System.out.println("�����ļ�... ...");
		FileInputStream fileIn=new FileInputStream(downFile.getFilename());//��ʱ��getFilename��ȡ���Ǵ��ļ�����λ�õ��ļ���
		File f=new File(filePath+"\\"+Doc.getFileName(downFile.getFilename()));//����ָ��λ��,������ͬ���ļ�
		FileOutputStream fileOut=new FileOutputStream(f,true);
		int i;
		while((i=fileIn.read())!=-1) {
			fileOut.write((byte)i);
		}
		System.out.println("���سɹ�");
		fileIn.close();
		fileOut.close();
		return true;
	}
	
	public void showFileList() throws SQLException{
		double ranValue=Math.random();
		if (ranValue>0.5)
			throw new SQLException( "Error in accessing file DB" );
		System.out.println("�б�... ...");
		Enumeration<Doc> enumeration=DataProcessing.getAllDocs();
		while(enumeration.hasMoreElements()) {
			Doc doc= enumeration.nextElement();
			System.out.printf("%s:%-18s","�ļ���",doc.getFilename());
			System.out.printf("%s:%-26s","�ļ�����",doc.getDescription());
			System.out.printf("%s:%-12s","�ļ��ϴ���",doc.getCreator());
			System.out.printf("%s:%-8s","�ļ�ID",doc.getID());
			System.out.println();
		}
		
	}
	
	public void exitSystem(){
		System.out.println("ϵͳ�˳�, ллʹ�� ! ");
		System.exit(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}

