package javaSystem;

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
	System.out.print("请输入用户名：");String newName=info.next();System.out.println();
	System.out.print("请输入用户新密码：");String newPassword=info.next();System.out.println();
	System.out.print("请输入用户新身份：");String newRole=info.next();System.out.println();
	try {
		if(DataProcessing.searchUser(newName)==null) {
			System.out.println("系统中未找到用户"+newName);
			return;
		}else {
			User one=DataProcessing.searchUser(newName);
			if(one.getRole()==newRole&&one.getPassword()==newPassword)
			{
				System.out.println(newName+"的修改信息与原信息相同");
			    return;
			}
		}
	DataProcessing.update(newName, newPassword, newRole);
		System.out.println("修改成功");
	    DataProcessing.saveAll();
	
	    
		
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("未连接到数据库"+e.getMessage()); }
		else {System.out.println("数据更新错误"+e.getMessage()); }
	}
}
public static boolean delUser(String delName) { 
	
	try {
	if(DataProcessing.delete(delName))
	{
	System.out.println("已为您删除该用户");
	DataProcessing.saveAll();
	return true;
	}
	else System.out.println("未找到该用户");
	return false;
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("未连接到数据库"+e.getMessage()); }
		else {System.out.println("数据删除时错误"+e.getMessage()); }
	}
	return false;
	
}
public static void  addUser(String newName,String newCode ,String newRole) {
	try {
	if(DataProcessing.insert(newName, newCode, newRole))
		{System.out.println("添加用户成功"); DataProcessing.saveAll();}
	else  System.out.println("用户名重复，添加失败");
	}
	catch(SQLException e) {
		if(e.getMessage().equalsIgnoreCase("Not Connected to Database"))
		{ System.out.println("未连接到数据库"+e.getMessage()); }
		else {System.out.println("数据更新错误"+e.getMessage()); }
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
		{ System.out.println("未连接到数据库"+e.getMessage()); }
		else {System.out.println("执行查询时出错"+e.getMessage()); }
	}
 }

}
