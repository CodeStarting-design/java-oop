package ���߳��ۺ�ʵ��;
import java.util.*;
import java.sql.*;
public class Main {
public static void main(String[] args) {
	String tip_system="����ϵͳ";
	String tip_menu="��ѡ��˵���";
	String tip_exit="ϵͳ�˳���ллʹ�ã�";
	String infos="****��ӭ����"+tip_system+"***\n\t   "+
	"1.��¼\n  \t   2.�˳�\n"+
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
		System.out.print("�������û�����");
		String Name=a.next();
		System.out.println();
		System.out.print("��������");
		String Code=a.next();
		System.out.println();
		try {
		User one = DataProcessing.search(Name, Code);
		if(one==null) { System.out.println("�˺������������");}
		else {
		one.showMenu();
		}
		}
		catch(SQLException e) { 
			System.out.println("���ݿ����"+e.getMessage());
		}
	}
	else {
		System.out.println("���벻�Ϸ������˳�");break;
        }
	}//while����
    a.close();
  }
}
