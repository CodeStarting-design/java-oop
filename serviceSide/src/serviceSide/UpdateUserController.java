package serviceSide;

import exceptionBao.DaoException;
import javaSystem.User;

public class UpdateUserController extends BaseController {
	@Override
	public void service()  {
		User user =(User)message.getParameter("User");
		try {
			message.setData(usersql.update(user));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		onSuccess();
	}
}