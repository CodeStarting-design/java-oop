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
	
	public void onSuccess() {//���ڽ����У�����ǰ�����message����д��
		try {
			out.writeObject(message);
			out.flush(); 
//			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	protected void sendFile(String absolutePath) throws IOException{//���յ���������ʱ����Ӧ���ļ����ݸ��ͻ���	
		System.out.println("�ͻ������ڽ����ļ�����---");
		File file = new File(absolutePath);
		FileInputStream fis =new FileInputStream(file);
                     
        //�ļ�����
		out.writeLong(file.length());
		out.flush();
         
        //�����ļ�
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
	
	protected String receiveTempFile(String filename) throws IOException{//���ϴ��ļ�ʱ�����ļ����н���,��ʱ���ļ�����Ҫ��ת��			
		long fileLength = in.readLong();
//		String extName = documentService.getExtensionName(filename);
//		String tempPath = Constants.UPLOAD_TEMP_PATH + System.currentTimeMillis() + "." + extName;
		String tempPath = Operator.uploadFile + filename;
		File file = new File(tempPath);
		FileOutputStream fos =new FileOutputStream(file);
        byte[] buffer =new byte[1024];
        int transLen =0;
        System.out.println("----��ʼ�����ļ�<" + filename +">,�ļ���СΪ<" + fileLength +">----");
        while(transLen<fileLength){
            int read =0;
            read = in.read(buffer);
            System.out.println(read);
            if(read == -1)
                break;
            transLen += read;
            System.out.println("�����ļ�����" +100 * transLen/fileLength +"%..."+ transLen);
            fos.write(buffer,0, read);
            fos.flush();
        }
        fos.close();
        System.out.println("----�����ļ�<" + filename +">�ɹ�-------");		
        return tempPath;
	} 
	
}
