package ���߳��ۺ�ʵ��;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
public class Browser extends User {

	Browser(String name, String password, String role) {
		super(name, password, role);
		
	}

	@Override
	public void showMenu() {
		String tip_system="�������Ա�˵�";
		String tip_menu="��ѡ��˵���";
		String infos="****��ӭ����"+tip_system+"***\n\t   "+
		"1.�����ļ�\n  \t   2.�ļ��б�\n  \t   3:�޸�����\n  \t   4:�˳�\n"+
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
		default : System.out.println("�������ֲ��Ϸ���������ѡ����");
		}
			}
			catch(IOException e) {
				System.out.println("�����ļ�ʱ����"+e.getMessage());
			}
			 catch(SQLException e) {
				 System.out.println("�����ļ����ݿ�ʱ����"+e.getMessage());
			 }
		System.out.println(infos);
		System.out.print(tip_menu);
		flag=scan.nextInt();
		System.out.println();
	  }//while����
		if(flag==4) 
		exitSystem();
		scan.close();
	
	}

}
