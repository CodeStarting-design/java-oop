package 多线程综合实验;
import java.util.*;
import java.sql.*;
public class Main {
public static void main(String[] args) {
	String tip_system="档案系统";
	String tip_menu="请选择菜单：";
	String tip_exit="系统退出，谢谢使用！";
	String infos="****欢迎进入"+tip_system+"***\n\t   "+
	"1.登录\n  \t   2.退出\n"+
			"*************************";
	Scanner a=new Scanner(System.in);
	while(true) {
		System.out.println(infos);
		System.out.print(tip_menu);
		int flag=a.nextInt();
		System.out.println();
	if(flag==2) {
		System.out.println(tip_exit);break;
		}
	else if(flag==1){
		System.out.print("请输入用户名：");
		String Name=a.next();
		System.out.println();
		System.out.print("请输入口令：");
		String Code=a.next();
		System.out.println();
		try {
		User one = DataProcessing.search(Name, Code);
		if(one==null) { System.out.println("账号密码输入错误");}
		else {
		one.showMenu();
		}
		}
		catch(SQLException e) { 
			System.out.println("数据库错误"+e.getMessage());
		}
	}
	else {
		System.out.println("输入不合法，已退出");break;
        }
	}//while结束
    a.close();
  }
}
