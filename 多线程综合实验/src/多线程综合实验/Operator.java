package ���߳��ۺ�ʵ��;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class Operator extends User{
       final static String uploadFile="D:\\OOPGit\\���߳��ۺ�ʵ��\\uploadfile";
	Operator(String name, String password, String role) {
		super(name, password, role);
		
	}
	public String getFileName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int split = filename.lastIndexOf('\\');  
            if (split == -1) {
            	split = filename.lastIndexOf('/'); 
            }
            if ((split >-1) && (split < (filename.length() - 1))) {   
                return filename.substring(split + 1);   
            }   
        }   
        return filename;   
    }

    public void uploadFile() throws SQLException, IOException { //ʹ���ļ���д�����ʹ���쳣����
    	System.out.print("������Դ�ļ�����");
    	Scanner inupt=new Scanner(System.in);
    	String fileName=inupt.next();System.out.println();
    	System.out.print("�����뵵���ţ�");
    	String fileID=inupt.next();System.out.println();
    	System.out.print("�����뵵��������");
    	String fileDes=inupt.next();System.out.println();
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
    	FileInputStream fileIn=new FileInputStream(fileName);
    	File f=new File(uploadFile+"\\"+Doc.getFileName(fileName));
    	FileOutputStream fileOut=new FileOutputStream(f,true);
		int i;
		while((i=fileIn.read())!=-1) {
			fileOut.write((byte)i);
		}
    	if(DataProcessing.insertDoc(fileID, getName(), timestamp, fileDes, uploadFile+"\\"+Doc.getFileName(fileName))) {	
    	System.out.println("�ϴ��ļ�...  ...");
    	System.out.println("�ϴ��ɹ�");
    	DataProcessing.saveAll();
    	}
    	else System.out.println("�ļ�ID�ظ����ϴ�ʧ��");
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
		if(flag==5) 
		exitSystem();
		scan.close();
	}
}
