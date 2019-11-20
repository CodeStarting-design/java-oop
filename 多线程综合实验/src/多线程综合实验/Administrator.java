package 多线程综合实验;

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
	System.out.print("请输入用户名：");String newName=info.next();System.out.println();
	System.out.print("请输入用户新密码：");String newPassword=info.next();System.out.println();
	System.out.print("请输入用户新身份：");String newRole=info.next();System.out.println();
	try {
	if(DataProcessing.update(newName,newPassword,newRole)) {
		System.out.println("修改成功");
		User change=DataProcessing.searchUser(newName);
		change.setName(newName);
		change.setPassword(newPassword);
		change.setRole(newRole);
	
	}
	else System.out.println("修改失败");
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("未连接到数据库"+e.getMessage()); }
		else {System.out.println("数据更新错误"+e.getMessage()); }
	}
}
public void delUser() { 
	Scanner del=new Scanner(System.in);
	System.out.print("请输入需要删除的用户名：");String delName=del.next();System.out.println();
	try {
	if(DataProcessing.delete(delName))
	System.out.println("已为您删除该用户");
	else System.out.println("未找到该用户");
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("未连接到数据库"+e.getMessage()); }
		else {System.out.println("数据删除时错误"+e.getMessage()); }
	}
	
}
public void addUser() {
	Scanner add =new Scanner(System.in);
	System.out.print("请输入需要添加的用户名：");String newName=add.next();System.out.println();
	System.out.print("请输入需要添加的用户口令：");String newCode=add.next();System.out.println();
	System.out.print("请输入需要添加的用户身份：");String newRole=add.next();System.out.println();
	try {
	if(DataProcessing.insert(newName, newCode, newRole))
		System.out.println("添加用户成功");
	else  System.out.println("，用户名重复，添加失败");
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("未连接到数据库"+e.getMessage()); }
		else {System.out.println("数据更新错误"+e.getMessage()); }
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
		{ System.out.println("未连接到数据库"+e.getMessage()); }
		else {System.out.println("执行查询时出错"+e.getMessage()); }
	}
}
@Override
public void showMenu() {
	String tip_system="档案管理员菜单";
	String tip_menu="请选择菜单：";
	String infos="****欢迎进入"+tip_system+"***\n\t   "+
	"1.修改用户\n  \t   2.删除用户\n   \t   3.新增用户\n  \t   4.列出用户\n   \t   5.下载文件\n  \t   6.文件列表\n  \t   7:修改密码\n  \t   8:退出\n"+
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
	    default : System.out.println("输入数字不合法，请重新选择功能");
	  }
	    if(flag==8) 
	    { exitSystem();break; }
	    	  }
	    	  catch(IOException e) {
	    			System.out.println("访问文件时出错"+e.getMessage());
	    		}
	    		 catch(SQLException e) {
	    			 System.out.println("访问文件数据库时出错"+e.getMessage());
	    		 }
	    System.out.println(infos);
	    System.out.print(tip_menu);
        }//while结束
  }
}
