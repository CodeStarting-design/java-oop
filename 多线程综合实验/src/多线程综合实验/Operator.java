package ���߳��ۺ�ʵ��;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Operator extends User{

	Operator(String name, String password, String role) {
		super(name, password, role);
		
	}
    public void uploadFile() { 
    	System.out.print("������Դ�ļ�����");
    	Scanner inupt=new Scanner(System.in);
    	String inuptName=inupt.next();System.out.println();
    	System.out.print("�����뵵���ţ�");
    	String fileNum=inupt.next();System.out.println();
    	System.out.print("�����뵵��������");
    	String fileDec=inupt.next();System.out.println();
    	System.out.println("�ϴ��ļ�...  ...");
    	System.out.println("�ϴ��ɹ�");
    }
	@Override
	public void showMenu() {
		String tip_system="����¼��Ա�˵�";
		String tip_menu="��ѡ��˵���";
		String infos="****��ӭ����"+tip_system+"***\n\t   "+
		"1.�ϴ��ļ�\n  \t   2.�����ļ�\n  \t   3.�ļ��б�\n  \t   4:�޸�����\n  \t   5:�˳�\n"+
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
		default : System.out.println("�������ֲ��Ϸ���������ѡ����");
		}
		}//try����
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
		if(flag==5) exitSystem();
		scan.close();
	}
}
