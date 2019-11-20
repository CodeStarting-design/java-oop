package 多线程综合实验;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class User {
	private String name;//用户名
	private String password;//密码
	private String role;//用于标识三种不同的角色
	
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo() throws SQLException{
		//写用户信息到存储
		Scanner scan=new Scanner(System.in);
		System.out.print("请输入新密码：");
		String newCode=scan.next();
		if (DataProcessing.update(this.name, newCode, this.role)){
			this.password=newCode;
			System.out.println("修改成功");
			return true;
		}else
			return false;	
	}
	
	public abstract void showMenu();
	
	public boolean downloadFile() throws IOException {
		Scanner scan=new Scanner(System.in);
		System.out.print("请输入需要下载的文件名：");
		String fileName=scan.next();
		double ranValue=Math.random();
		if (ranValue>0.5)
			throw new IOException( "Error in accessing file" );
		System.out.println("下载文件... ...");
		return true;
	}
	
	public void showFileList() throws SQLException{
		double ranValue=Math.random();
		if (ranValue>0.5)
			throw new SQLException( "Error in accessing file DB" );
		System.out.println("列表... ...");
	}
	
	public void exitSystem(){
		System.out.println("系统退出, 谢谢使用 ! ");
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

