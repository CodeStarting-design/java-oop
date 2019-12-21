package clientSide;

import java.util.List;

import exceptionBao.BaseException;
import javaSystem.Controller;
import javaSystem.Message;
import javaSystem.User;

public class UserServiceClient extends BaseServiceClient {
     public User changeSelfPassword(User user) throws BaseException {
    	 Message message=new Message();
    	 message.setController(Controller.CHANGE_PASSWORD_CONTROLLER);
    	 message.setParameter("User",user );
    	 message=send(message);
    	 user=(User)message.getData();
    	 return user;
     }
     public List<User> getAllUsers() throws BaseException{
    	 Message message=new Message();
    	 message.setController(Controller.GET_ALL_USERS_CONTROLLER);
    	 message=send(message);
    	 List<User> users=(List<User>)message.getData();
    	 return users;
     }
     public User createUser(User user) throws BaseException {
    	 Message message=new Message();
    	 message.setController(Controller.CREATE_USER_CONTROLLER);
    	 message.setParameter("User", user);
    	 message=send(message);
    	 user = (User) message.getData();
    	 return user;
     }
     public User deleteUser(User user) throws BaseException {
    	 Message message=new Message();
    	 message.setController(Controller.DELETE_USER_CONTROLLER);
    	 message.setParameter("User", user);
    	 message=send(message);
    	 user=(User)message.getData();
    	 return user;
     }
     public User updateUser(User user) throws BaseException {
    	 Message message=new Message();
    	 message.setController(Controller.UPDATE_USER_CONTROLLER);
    	 message.setParameter("User", user);
    	 message=send(message);
    	 user =(User)message.getData();
    	 return user;
     }
   
     public User login(String name ,String password) throws BaseException {
    	Message message=new Message();
   	    message.setController(Controller.LOGIN_CONTROLLER);
   	    message.setParameter("name", name);
   	    message.setParameter("password", password);
   	    message=send(message);
   	    User user=(User) message.getData();
   	    return user;
    }
     public User findUser(String name) throws BaseException {
    	 Message message=new Message();
    	 message.setController(Controller.GET_USER_CONTROLLER);
    	 message.setParameter("name", name);
    	 message=send(message);
    	 User user=(User)message.getData();
    	 return user;
    	 
     }
}


