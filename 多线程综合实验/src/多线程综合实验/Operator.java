package 多线程综合实验;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class Operator extends User{
       final static String uploadFile="D:\\OOPGit\\多线程综合实验\\uploadfile";
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

    public void uploadFile() throws SQLException, IOException { //使用文件读写则必须使用异常处理
    	System.out.print("请输入源文件名：");
    	Scanner inupt=new Scanner(System.in);
    	String fileName=inupt.next();System.out.println();
    	System.out.print("请输入档案号：");
    	String fileID=inupt.next();System.out.println();
    	System.out.print("请输入档案描述：");
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
    	System.out.println("上传文件...  ...");
    	System.out.println("上传成功");
    	DataProcessing.saveAll();
    	}
    	else System.out.println("文件ID重复，上传失败");
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
		if(flag==5) 
		exitSystem();
		scan.close();
	}
}
