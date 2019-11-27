package 多线程综合实验;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
public class Browser extends User {

	Browser(String name, String password, String role) {
		super(name, password, role);
		
	}

	@Override
	public void showMenu() {
		String tip_system="档案浏览员菜单";
		String tip_menu="请选择菜单：";
		String infos="****欢迎进入"+tip_system+"***\n\t   "+
		"1.下载文件\n  \t   2.文件列表\n  \t   3:修改密码\n  \t   4:退出\n"+
				"*************************";
		System.out.println(infos);
		System.out.print(tip_menu);
		Scanner scan=new Scanner(System.in);
		int flag=scan.nextInt();System.out.println();
		while(flag!=4) {
			try {
		switch(flag) {
		case 1:downloadFile();break;
		case 2:showFileList();break;
		case 3:this.changeSelfInfo();break;
		default : System.out.println("输入数字不合法，请重新选择功能");
		}
			}
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
		if(flag==4) 
		exitSystem();
		scan.close();
	
	}

}
