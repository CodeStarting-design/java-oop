package 多线程综合实验;

import java.io.*;
import java.sql.Timestamp;

class Doc implements Serializable{
	private String ID;//文件的标识
	private String creator;//创建者
	private Timestamp timestamp;//时间记录
	private String description;//文件描述
	private String filename;//源文件名
	
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
	 public static String getFileName(String filename) {   //获取文件名
        if ((filename != null) && (filename.length() > 0)) {   
            int split = filename.lastIndexOf('\\');  //由于\\属于转义符，所以\\实际为\在字符床长度计算中只算一
            //且索引从0开始
            if (split == -1) {//如果字符串中无\\则获取/的位置
            	split = filename.lastIndexOf('/'); 
            }
            if ((split >-1) && (split < (filename.length() - 1))) {   
                return filename.substring(split + 1);   
            }   
        }   
        return filename;   
    }
}