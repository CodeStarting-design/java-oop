package serviceSide;

import exceptionBao.BaseException;
import javaSystem.User;

public class DeleteUserController extends BaseController {

	@Override
	public void service()  {
		// TODO Auto-generated method stub
		User user=(User)message.getParameter("User");
		try {
			message.setData(usersql.delete(user));
			System.out.println("É¾³ýÓÃ»§ÖÐ---");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onSuccess();
	}

}
