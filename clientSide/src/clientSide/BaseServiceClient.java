package clientSide;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import exceptionBao.BaseException;
import javaSystem.Doc;
import javaSystem.Message;

public abstract class  BaseServiceClient {
	protected Socket client;
	protected ObjectOutputStream output;
	protected ObjectInputStream input;
	
	protected void checkStatusCode(Message message) throws IOException {
		if (message.getCode() == 404) {
			throw new IOException(message.getMsg());
		}
	}
  protected Message send(Message message) throws BaseException { //用于与服务器端进行通信,将message对象写入流进行传递,并将服务端传递的message返回
		try {
			client = new Socket(InetAddress.getByName("localhost"), 12341);			
			output = new ObjectOutputStream(client.getOutputStream());
			output.writeObject(message);
			output.flush(); 			
			input = new ObjectInputStream(client.getInputStream());
			message = (Message)input.readObject();
			checkStatusCode(message);	
			output.close();
			input.close();
			client.close();
		} catch (IOException e) {
			throw new NetworkException();
		} catch (ClassNotFoundException e) {
			throw new NetworkException();
		}
		return message;
	}
	protected Message sendDocument(Message message) throws BaseException {//用于上传
		try {
			client = new Socket(InetAddress.getByName("localhost"), 12341);			
			output = new ObjectOutputStream(client.getOutputStream());
			output.writeObject(message);
			output.flush();
			
			Doc document = (Doc)message.getParameter("document");
			sendFile(document.getFilename());
			
			input = new ObjectInputStream(client.getInputStream());
			message = (Message)input.readObject();
			checkStatusCode(message);	
			output.close();
			input.close();
			client.close();
		} catch (IOException e) {
			throw new NetworkException();
		} catch (ClassNotFoundException e) {
			throw new NetworkException();
		}
		return message;
	}
	
	protected Message receiveDocument(Message message, String targetPath) throws BaseException {//用于下载
		try {
			client = new Socket(InetAddress.getByName("localhost"),12341);			
			output = new ObjectOutputStream(client.getOutputStream());
			output.writeObject(message);
			output.flush(); 
			
			input = new ObjectInputStream(client.getInputStream());
			message = (Message)input.readObject();
			checkStatusCode(message);	
			
			Doc document = (Doc)message.getData();
			receiveFile(Doc.getFileName(document.getFilename()), targetPath);
			
			output.close();
			input.close();
			client.close();
		} catch (IOException e) {
			throw new NetworkException();
		} catch (ClassNotFoundException e) {
			throw new NetworkException();
		}
		return message;
	}
	
	protected void sendFile(String sourcePath) throws IOException{	
		File file = new File(sourcePath);
		FileInputStream fis =new FileInputStream(file);
                     
        //文件长度
		output.writeLong(file.length());
		output.flush();
         
        //传输文件
        byte[] buffer =new byte[1024];            
        while(true){
        	int read = 0;  
        	if (fis != null) {  
        		read = fis.read(buffer);                   
        	}  
        	if (read == -1)   
        		break;            
        	output.write(buffer,0, read); 
        	output.flush();
        }       
        fis.close();
	}
	
	protected void receiveFile(String filename, String targetPath) throws IOException{
		long fileLength = input.readLong();
		targetPath = targetPath != null ? targetPath : "D:\\javaOOP\\java-oop\\多线程综合实验\\downloadfile";//在目标路径为空时写入默认路径
		FileOutputStream fos = new FileOutputStream(targetPath);
		
        byte[] buffer =new byte[1024];
        int transLen =0;
        System.out.println("----开始接收文件<" + filename +">,文件大小为<" + fileLength +">----");
        while(transLen<fileLength){
            int read =0;
            read = input.read(buffer);
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
	}
}

	
	
	
	

