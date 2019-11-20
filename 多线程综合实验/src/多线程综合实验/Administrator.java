package ���߳��ۺ�ʵ��;

import java.util.Enumeration;
import java.util.Scanner;
import java.io.IOException;
import java.sql.*;
public class Administrator extends User {
public Administrator(String Name,String Password,String Role) {
	super(Name,Password,Role);
}
public void changeUserInfo() {
	Scanner info = new Scanner(System.in);
	System.out.print("�������û�����");String newName=info.next();System.out.println();
	System.out.print("�������û������룺");String newPassword=info.next();System.out.println();
	System.out.print("�������û�����ݣ�");String newRole=info.next();System.out.println();
	try {
	if(DataProcessing.update(newName,newPassword,newRole)) {
		System.out.println("�޸ĳɹ�");
		User change=DataProcessing.searchUser(newName);
		change.setName(newName);
		change.setPassword(newPassword);
		change.setRole(newRole);
	
	}
	else System.out.println("�޸�ʧ��");
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("δ���ӵ����ݿ�"+e.getMessage()); }
		else {System.out.println("���ݸ��´���"+e.getMessage()); }
	}
}
public void delUser() { 
	Scanner del=new Scanner(System.in);
	System.out.print("��������Ҫɾ�����û�����");String delName=del.next();System.out.println();
	try {
	if(DataProcessing.delete(delName))
	System.out.println("��Ϊ��ɾ�����û�");
	else System.out.println("δ�ҵ����û�");
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("δ���ӵ����ݿ�"+e.getMessage()); }
		else {System.out.println("����ɾ��ʱ����"+e.getMessage()); }
	}
	
}
public void addUser() {
	Scanner add =new Scanner(System.in);
	System.out.print("��������Ҫ��ӵ��û�����");String newName=add.next();System.out.println();
	System.out.print("��������Ҫ��ӵ��û����");String newCode=add.next();System.out.println();
	System.out.print("��������Ҫ��ӵ��û���ݣ�");String newRole=add.next();System.out.println();
	try {
	if(DataProcessing.insert(newName, newCode, newRole))
		System.out.println("����û��ɹ�");
	else  System.out.println("���û����ظ������ʧ��");
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("δ���ӵ����ݿ�"+e.getMessage()); }
		else {System.out.println("���ݸ��´���"+e.getMessage()); }
	}
	
}
public void listUser() {
	try {
	Enumeration<User> enumeration=DataProcessing.getAllUser();
	while(enumeration.hasMoreElements()) {
		User user= enumeration.nextElement();
		System.out.printf("%s:%-8s","Name",user.getName());
		System.out.printf("%s:%-8s","Password",user.getPassword());
		System.out.printf("%s:%-12s","Rloe",user.getRole());
		System.out.println();
	}
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("δ���ӵ����ݿ�"+e.getMessage()); }
		else {System.out.println("ִ�в�ѯʱ����"+e.getMessage()); }
	}
}
@Override
public void showMenu() {
	String tip_system="��������Ա�˵�";
	String tip_menu="��ѡ��˵���";
	String infos="****��ӭ����"+tip_system+"***\n\t   "+
	"1.�޸��û�\n  \t   2.ɾ���û�\n   \t   3.�����û�\n  \t   4.�г��û�\n   \t   5.�����ļ�\n  \t   6.�ļ��б�\n  \t   7:�޸�����\n  \t   8:�˳�\n"+
			"*************************";
	System.out.println(infos);
	System.out.print(tip_menu);
	Scanner scan=new Scanner(System.in);
	int flag;
	      while(true) {
	    	  try {
	      flag=scan.nextInt();
	      System.out.println();
	    switch(flag) {
	    case 1:changeUserInfo();break;
	    case 2:delUser();break;
	    case 3:addUser();break;
	    case 4:listUser();break;
	    case 5:downloadFile();break;
	    case 6:showFileList();break;
	    case 7:this.changeSelfInfo();break;
	    default : System.out.println("�������ֲ��Ϸ���������ѡ����");
	  }
	    if(flag==8) 
	    { exitSystem();break; }
	    	  }
	    	  catch(IOException e) {
	    			System.out.println("�����ļ�ʱ����"+e.getMessage());
	    		}
	    		 catch(SQLException e) {
	    			 System.out.println("�����ļ����ݿ�ʱ����"+e.getMessage());
	    		 }
	    System.out.println(infos);
	    System.out.print(tip_menu);
        }//while����
  }
}
