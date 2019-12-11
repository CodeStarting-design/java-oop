package ���߳��ۺ�ʵ��;

import java.util.Enumeration;
import java.util.Scanner;
import java.io.IOException;
import java.sql.*;
public class Administrator extends User {
public Administrator(String Name,String Password,String Role) {
	super(Name,Password,Role);
}
public Administrator(){

}
public void changeUserInfo() {
	Scanner info = new Scanner(System.in);
	System.out.print("�������û�����");String newName=info.next();System.out.println();
	System.out.print("�������û������룺");String newPassword=info.next();System.out.println();
	System.out.print("�������û�����ݣ�");String newRole=info.next();System.out.println();
	try {
		if(DataProcessing.searchUser(newName)==null) {
			System.out.println("ϵͳ��δ�ҵ��û�"+newName);
			return;
		}else {
			User one=DataProcessing.searchUser(newName);
			if(one.getRole()==newRole&&one.getPassword()==newPassword)
			{
				System.out.println(newName+"���޸���Ϣ��ԭ��Ϣ��ͬ");
			    return;
			}
		}
	DataProcessing.update(newName, newPassword, newRole);
		System.out.println("�޸ĳɹ�");
	    DataProcessing.saveAll();
	
	    
		
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("δ���ӵ����ݿ�"+e.getMessage()); }
		else {System.out.println("���ݸ��´���"+e.getMessage()); }
	}
}
public static boolean delUser(String delName) { 
	
	try {
	if(DataProcessing.delete(delName))
	{
	System.out.println("��Ϊ��ɾ�����û�");
	DataProcessing.saveAll();
	return true;
	}
	else System.out.println("δ�ҵ����û�");
	return false;
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("δ���ӵ����ݿ�"+e.getMessage()); }
		else {System.out.println("����ɾ��ʱ����"+e.getMessage()); }
	}
	return false;
	
}
public static void  addUser(String newName,String newCode ,String newRole) {
	try {
	if(DataProcessing.insert(newName, newCode, newRole))
		{System.out.println("����û��ɹ�"); DataProcessing.saveAll();}
	else  System.out.println("�û����ظ������ʧ��");
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("δ���ӵ����ݿ�"+e.getMessage()); }
		else {System.out.println("���ݸ��´���"+e.getMessage()); }
	}
	
}
public static User CreateUser(String name,String password ,String role) {
	User user =null;
	if (role.equalsIgnoreCase("administrator"))
		user = new Administrator(name,password, role);
	else if (role.equalsIgnoreCase("operator"))
		user = new Operator(name,password, role);
	else
		user = new Browser(name,password, role);
	return user;
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
	AdminGui admGui= new AdminGui();
	admGui.setVisible(true);
//	String tip_system="��������Ա�˵�";
//	String tip_menu="��ѡ��˵���";
//	String infos="****��ӭ����"+tip_system+"***\n\t   "+
//	"1.�޸��û�\n  \t   2.ɾ���û�\n   \t   3.�����û�\n  \t   4.�г��û�\n   \t   5.�����ļ�\n  \t   6.�ļ��б�\n  \t   7:�޸�����\n  \t   8:�˳�\n"+
//			"*************************";
//	System.out.println(infos);
//	System.out.print(tip_menu);
//	Scanner scan=new Scanner(System.in);
//	int flag;
//	      while(true) {
//	    	  try {
//	      flag=scan.nextInt();
//	      System.out.println();
//	    switch(flag) {
//	    case 1:changeUserInfo();break;
//	    case 2:delUser();break;
//	    case 3:addUser();break;
//	    case 4:listUser();break;
//	    case 5:downloadFile();break;
//	    case 6:showFileList();break;
//	    case 7:this.changeSelfInfo();break;
//	    default : System.out.println("�������ֲ��Ϸ���������ѡ����");
//	  }
//	    if(flag==8) 
//	    { 
//	    	DataProcessing.saveAll();
//	    	exitSystem();break; }
//	    }
//	    	  catch(IOException e) {
//	    			System.out.println("�����ļ�ʱ����"+e.getMessage());
//	    		}
//	    		 catch(SQLException e) {
//	    			 System.out.println("�����ļ����ݿ�ʱ����"+e.getMessage());
//	    		 }
//	    System.out.println(infos);
//	    System.out.print(tip_menu);
//        }//while����
  }
}
