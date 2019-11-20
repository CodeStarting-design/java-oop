package 多线程综合实验;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Operator extends User{

	Operator(String name, String password, String role) {
		super(name, password, role);
		
	}
    public void uploadFile() { 
    	System.out.print("请输入源文件名：");
    	Scanner inupt=new Scanner(System.in);
    	String inuptName=inupt.next();System.out.println();
    	System.out.print("请输入档案号：");
    	String fileNum=inupt.next();System.out.println();
    	System.out.print("请输入档案描述：");
    	String fileDec=inupt.next();System.out.println();
    	System.out.println("上传文件...  ...");
    	System.out.println("上传成功");
    }
	@Override
	public void showMenu() {
		String tip_system="档案录入员菜单";
		String tip_menu="请选择菜单：";
		String infos="****欢迎进入"+tip_system+"***\n\t   "+
		"1.上传文件\n  \t   2.下载文件\n  \t   3.文件列表\n  \t   4:修改密码\n  \t   5:退出\n"+
				"*************************";
		System.out.println(infos);
		System.out.print(tip_menu);
		Scanner scan=new Scanner(System.in);
		int flag=scan.nextInt();System.out.println();
		
		while(flag!=5) {
			try {
		switch(flag) {
		case 1:uploadFile();break;
		case 2:downloadFile();break;
		case 3:showFileList();break;
		case 4:this.changeSelfInfo();break;
		default : System.out.println("输入数字不合法，请重新选择功能");
		}
		}//try结束
			catch(IOException e) {
				System.out.println("访问文件时出错"+e.getMessage());
			}
			catch(SQLException e) {
				 System.out.println("访问文件数据库时出错"+e.getMessage());
			 }
		System.out.println(infos);
		System.out.print(tip_menu);
		flag=scan.nextInt();
		System.out.println();
	  }//while结束
		if(flag==5) exitSystem();
		scan.close();
	}
}
