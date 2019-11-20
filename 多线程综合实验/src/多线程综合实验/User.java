package ���߳��ۺ�ʵ��;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class User {
	private String name;//�û���
	private String password;//����
	private String role;//���ڱ�ʶ���ֲ�ͬ�Ľ�ɫ
	
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
			return true;
		}else
			return false;	
	}
	
	public abstract void showMenu();
	
	public boolean downloadFile() throws IOException {
		Scanner scan=new Scanner(System.in);
		System.out.print("��������Ҫ���ص��ļ�����");
		String fileName=scan.next();
		double ranValue=Math.random();
		if (ranValue>0.5)
			throw new IOException( "Error in accessing file" );
		System.out.println("�����ļ�... ...");
		return true;
	}
	
	public void showFileList() throws SQLException{
		double ranValue=Math.random();
		if (ranValue>0.5)
			throw new SQLException( "Error in accessing file DB" );
		System.out.println("�б�... ...");
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

