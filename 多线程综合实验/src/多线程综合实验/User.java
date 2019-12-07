package 多线程综合实验;

import java.io.IOException;
import java.io.*;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

public abstract class User implements Serializable {
	private String name;//用户名
	private String password;//密码
	private String role;//用于标识三种不同的角色
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo(User currentUser , String newCode) throws SQLException{
		//写用户信息到存储
		
		if (DataProcessing.update(currentUser.getName(), newCode, currentUser.getRole())){
			currentUser.setPassword(newCode);
			System.out.println("修改成功");
			DataProcessing.saveAll();
			return true;
		}else
			return false;	
	}
	
	public abstract void showMenu();
	
	public boolean downloadFile(String fileID,String path) throws IOException, SQLException  {
//		Scanner scan=new Scanner(System.in);
//		System.out.print("请输入需要下载的文件id：");
//		String fileID=scan.next();
		 Doc downFile=DataProcessing.searchDoc(fileID);
//		 if(downFile==null) { System.out.println("系统中未包含该文件"); return false;}
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new IOException( "Error in accessing file" );
		//System.out.println("下载文件... ...");
		 File f2=new File(downFile.getFilename());
		FileInputStream fileIn=new FileInputStream(f2);//此时的getFilename获取的是带文件储存位置的文件名
		File f=new File(path);//存入指定位置,并创建同名文件
		FileOutputStream fileOut=new FileOutputStream(f,true);
		int i;
		while((i=fileIn.read())!=-1) {
			fileOut.write((byte)i);
		}
		System.out.println("下载成功");
		fileIn.close();
		fileOut.close();
		return true;
	}
	
	public void showFileList() throws SQLException{
		double ranValue=Math.random();
		if (ranValue>0.5)
			throw new SQLException( "Error in accessing file DB" );
		System.out.println("列表... ...");
		Enumeration<Doc> enumeration=DataProcessing.getAllDocs();
		while(enumeration.hasMoreElements()) {
			Doc doc= enumeration.nextElement();
			System.out.printf("%s:%-20s","文件名",Doc.getFileName(doc.getFilename()));
			System.out.printf("%s:%-26s","文件描述",doc.getDescription());
			System.out.printf("%s:%-12s","文件上传者",doc.getCreator());
			System.out.printf("%s:%-8s","文件ID",doc.getID());
			System.out.println();
		}
		
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

