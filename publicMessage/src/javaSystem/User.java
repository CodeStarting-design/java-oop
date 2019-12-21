package javaSystem;

import java.io.IOException;
import java.io.*;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

import exceptionBao.BaseException;
import exceptionBao.DaoException;
import sqlService.SQLForDoc;
import sqlService.SQLForUser;

public abstract class User implements Serializable {
	private String name;//�û���
	private String password;//����
	private String role;//���ڱ�ʶ���ֲ�ͬ�Ľ�ɫ
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	User(){
		name="";
		password="";
		role="";
	}
	
	public boolean changeSelfInfo(User currentUser , String newCode) throws SQLException, DaoException{
		//д�û���Ϣ���洢
		
//		if (DataProcessing.update(currentUser.getName(), newCode, currentUser.getRole())){
//			currentUser.setPassword(newCode);
//			System.out.println("�޸ĳɹ�");
//			DataProcessing.saveAll();
//			return true;
//		}else
//			return false;	
		SQLForUser sqlUser=new SQLForUser();
		currentUser.setPassword(newCode);
		if(sqlUser.update(currentUser)!=null) {
		return true;
		}
		else return false;
	}
	
	
	
	public boolean downloadFile(String fileID,String path) throws IOException, SQLException, BaseException  {
//		Scanner scan=new Scanner(System.in);
//		System.out.print("��������Ҫ���ص��ļ�id��");
//		String fileID=scan.next();
//		 Doc downFile=DataProcessing.searchDoc(fileID);
//		 if(downFile==null) { System.out.println("ϵͳ��δ�������ļ�"); return false;}
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new IOException( "Error in accessing file" );
		//System.out.println("�����ļ�... ...");
		SQLForDoc sql =new SQLForDoc();
		Doc downFile = sql.findById(fileID);
		 File f2=new File(downFile.getFilename());
		FileInputStream fileIn=new FileInputStream(f2);//��ʱ��getFilename��ȡ���Ǵ��ļ�����λ�õ��ļ���
		File f=new File(path);//����ָ��λ��,������ͬ���ļ�
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
			System.out.printf("%s:%-20s","�ļ���",Doc.getFileName(doc.getFilename()));
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

