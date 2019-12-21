package serviceSide;

import java.sql.SQLException;

import exceptionBao.BaseException;
import javaSystem.User;

public class LoginController extends BaseController {

	@Override
	public void service()  {
		// TODO Auto-generated method stub
		String userName=(String) message.getParameter("name");
		String password =(String)message.getParameter("password");
		User user=null;
		try {
			user=usersql.login(userName, password);
			System.out.println("当前登录用户："+user.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message.setData(user);
		onSuccess();
	}

}
