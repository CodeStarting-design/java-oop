package serviceSide;

import java.io.*;


import exceptionBao.BaseException;
import javaSystem.Message;
import javaSystem.Operator;
import sqlService.SQLForDoc;
import sqlService.SQLForUser;

public abstract class BaseController {
	protected SQLForDoc docsql =new SQLForDoc();
	protected SQLForUser usersql =new SQLForUser();
	protected Message message;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	
	public abstract void service() throws BaseException;
	
	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}
	
	public void setIn(ObjectInputStream in) {
		this.in = in;
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public void onException(BaseException e) {
		message.setCode(404);
		message.setMsg(e.getMessage());
		
		try {
			out.writeObject(message);
			out.flush(); 
//			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void onSuccess() {//正在进行中，将当前对象的message对象写出
		try {
			out.writeObject(message);
			out.flush(); 
//			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	protected void sendFile(String absolutePath) throws IOException{//接收到下载请求时将相应的文件传递给客户端	
		System.out.println("客户端正在进行文件下载---");
		File file = new File(absolutePath);
		FileInputStream fis =new FileInputStream(file);
                     
        //文件长度
		out.writeLong(file.length());
		out.flush();
         
        //传输文件
        byte[] buffer =new byte[1024];            
        while(true){
        	int read = 0;  
        	if (fis != null) {  
        		read = fis.read(buffer);                   
        	}  
        	if (read == -1)   
        		break;            
        	out.write(buffer,0, read); 
        	out.flush();
        }       
        fis.close();
	}
	
	protected String receiveTempFile(String filename) throws IOException{//在上传文件时，对文件进行接收,此时的文件名需要做转化			
		long fileLength = in.readLong();
//		String extName = documentService.getExtensionName(filename);
//		String tempPath = Constants.UPLOAD_TEMP_PATH + System.currentTimeMillis() + "." + extName;
		String tempPath = Operator.uploadFile + filename;
		File file = new File(tempPath);
		FileOutputStream fos =new FileOutputStream(file);
        byte[] buffer =new byte[1024];
        int transLen =0;
        System.out.println("----开始接收文件<" + filename +">,文件大小为<" + fileLength +">----");
        while(transLen<fileLength){
            int read =0;
            read = in.read(buffer);
            System.out.println(read);
            if(read == -1)
                break;
            transLen += read;
            System.out.println("接收文件进度" +100 * transLen/fileLength +"%..."+ transLen);
            fos.write(buffer,0, read);
            fos.flush();
        }
        fos.close();
        System.out.println("----接收文件<" + filename +">成功-------");		
        return tempPath;
	} 
	
}
