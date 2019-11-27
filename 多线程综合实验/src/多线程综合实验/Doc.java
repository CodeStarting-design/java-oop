package ���߳��ۺ�ʵ��;

import java.io.*;
import java.sql.Timestamp;

class Doc implements Serializable{
	private String ID;//�ļ��ı�ʶ
	private String creator;//������
	private Timestamp timestamp;//ʱ���¼
	private String description;//�ļ�����
	private String filename;//Դ�ļ���
	
	public Doc(String ID, String creator, Timestamp timestamp, String description, String filename) {
		super();
		this.ID = ID;
		this.creator = creator;
		this.timestamp = timestamp;
		this.description = description;
		this.filename=filename;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	 public static String getFileName(String filename) {   //��ȡ�ļ���
        if ((filename != null) && (filename.length() > 0)) {   
            int split = filename.lastIndexOf('\\');  //����\\����ת���������\\ʵ��Ϊ\���ַ������ȼ�����ֻ��һ
            //��������0��ʼ
            if (split == -1) {//����ַ�������\\���ȡ/��λ��
            	split = filename.lastIndexOf('/'); 
            }
            if ((split >-1) && (split < (filename.length() - 1))) {   
                return filename.substring(split + 1);   
            }   
        }   
        return filename;   
    }
}