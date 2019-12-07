package 多线程综合实验;
import java.util.*;
import java.io.*;
import java.sql.*;

public  class DataProcessing {
	private static boolean connectToDB=false;
	final static String userPath="D:\\OOPGit\\多线程综合实验\\useranddoc\\user.bin";
	final static String docPath="D:\\OOPGit\\多线程综合实验\\useranddoc\\Doc.bin";
	static Hashtable<String, User> users;
	static Hashtable<String, Doc> docs;//文件id与文件对象的映射

	static {
		users = new Hashtable<String, User>();
		docs = new Hashtable<String,Doc>();
		try {
			File fp1=new File(userPath);
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(fp1));
			 @SuppressWarnings("unchecked")
			Hashtable<String, User> user2=(Hashtable<String, User>)in.readObject();
			 users=user2;
			in.close();
			}catch(EOFException e){
				users.put("jack", new Operator("jack","123","operator"));
				users.put("rose", new Browser("rose","123","browser"));
				users.put("kate", new Administrator("kate","123","administrator"));}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Init();
		try {
			File fp1=new File(docPath);
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(fp1));
			 @SuppressWarnings("unchecked")
			Hashtable<String, Doc> doc2=(Hashtable<String, Doc>)in.readObject();
			 docs=doc2;
			in.close();
			}catch(EOFException e){}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
//		docs.put("0001",new Doc("0001","jack",timestamp,"Doc Source Java","Doc.java"));
		
		
	}
	
	public static  void Init(){
		// connect to database
		
		// update database connection status
		if (Math.random()>0.2)
			connectToDB = true;
		else
			connectToDB = false;
		
	}	
	public static Doc searchDoc(String ID) throws SQLException {//根据id查找文件
		if (docs.containsKey(ID)) {
			Doc temp =docs.get(ID);
			return temp;
		}
		return null;
	}
	
	public static Enumeration<Doc> getAllDocs() throws SQLException{		
		Enumeration<Doc> e  = docs.elements();
		return e;
	} 
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
		Doc doc;		
	
		if (docs.containsKey(ID))
			return false;
		else{
			doc = new Doc(ID,creator,timestamp,description,filename);
			docs.put(ID, doc);
			return true;
		}
	} 
	public static User searchUser(String name) throws SQLException{
//		if ( !connectToDB ) 
//			throw new SQLException( "Not Connected to Database" );
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Query" );
		
		if (users.containsKey(name)) {
			return users.get(name);			
		}
		return null;
	}
	
	public static User search(String name, String password) throws SQLException {
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Query" );
//		
		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}
	
	public static Enumeration<User> getAllUser() throws SQLException{
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Query" );
		
		Enumeration<User> e  = users.elements();
		return e;
	}
	
	
	
	public static boolean update(String name, String password, String role) throws SQLException{
		User user;
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Update" );
//		
		if (users.containsKey(name)) {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}else
			return false;
	}
	
	public static boolean insert(String name, String password, String role) throws SQLException{
		User user;
		
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Insert" );
//		
		if (users.containsKey(name))
			return false;
		else{
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}
	}
	
	public static boolean delete(String name) throws SQLException{
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Delete" );
//		
		if (users.containsKey(name)){
			users.remove(name);
			return true;
		}else
			return false;
		
	}	
            
	public void disconnectFromDB() {
//		if ( connectToDB ){
//			// close Statement and Connection            
//			try{
//			    if (Math.random()>0.5)
//					throw new SQLException( "Error in disconnecting DB" );
//			}catch ( SQLException sqlException ){                                            
//			    sqlException.printStackTrace();           
//			}finally{                                            
//				connectToDB = false;              
//			}                             
//		} 
   }           
  public static void saveAll() {
	  try {
		  File fp1=new File(userPath);
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(fp1));
				out.writeObject(users);
			out.close();
	  }
	  catch (IOException e) {
		  System.out.println(e.getMessage());
	  }
	  try {
			File fp1=new File(docPath);
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(fp1));
				out.writeObject(docs);
			out.close();
			System.out.println("已保存相应操作");
	  
		}
	  catch(Exception e) {}
  }
   
	public static List<User> getListUser() throws SQLException{
    	
    	  List<User> userList = new ArrayList();
    	  Enumeration<User> enumeration=DataProcessing.getAllUser();
    	  while(enumeration.hasMoreElements()) {
    		User user= enumeration.nextElement();
    		userList.add(user);
    	   }
    	 return userList;
    	 
    }
	public static List<Doc> getListDoc() throws SQLException{
		List<Doc> docList =new ArrayList();
		Enumeration<Doc> enumeration=DataProcessing.getAllDocs();
		 while(enumeration.hasMoreElements()) {
			 Doc docL =enumeration.nextElement();
			 docList.add(docL);
		 }
		return docList;
	} 
	public static void main(String[] args) {		

	}
	
}
