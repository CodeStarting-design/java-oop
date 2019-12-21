package javaSystem;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

import exceptionBao.BaseException;
import sqlService.SQLForDoc;

public class Operator extends User{
    public final static String uploadFile="D:\\javaFinally\\uploadFile";
	public Operator(String name, String password, String role) {
		super(name, password, role);
		
	}
public	Operator(){
		
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

    public static void uploadFile(String fileID,String fileDes ,String fileName,User cUser) throws SQLException, IOException, BaseException { //ʹ���ļ���д�����ʹ���쳣����
//    	System.out.print("������Դ�ļ�����");
//    	Scanner inupt=new Scanner(System.in);
//    	String fileName=inupt.next();System.out.println();
//    	System.out.print("�����뵵���ţ�");
//    	String fileID=inupt.next();System.out.println();
//    	System.out.print("�����뵵��������");
//    	String fileDes=inupt.next();System.out.println();
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
    	FileInputStream fileIn=new FileInputStream(fileName);
    	File f=new File(uploadFile+"\\"+Doc.getFileName(fileName));
    	FileOutputStream fileOut=new FileOutputStream(f,true);
		int i;
		while((i=fileIn.read())!=-1) {
			fileOut.write((byte)i);
		}
		Doc newDoc =new Doc(fileID, cUser.getName(), timestamp, fileDes, uploadFile+"\\"+Doc.getFileName(fileName));
		SQLForDoc sqlDoc =new SQLForDoc();
		sqlDoc.insert(newDoc);
//    	if(DataProcessing.insertDoc(fileID, cUser.getName(), timestamp, fileDes, uploadFile+"\\"+Doc.getFileName(fileName))) {	
//    	System.out.println("�ϴ��ļ�...  ...");
//    	System.out.println("�ϴ��ɹ�");
//    	DataProcessing.saveAll();
//    	}
 //   	else System.out.println("�ļ�ID�ظ����ϴ�ʧ��");
    }

}
