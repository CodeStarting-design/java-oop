package serviceSide;

import exceptionBao.BaseException;
import javaSystem.User;

public class CreateUserController extends BaseController {

	@Override
	public void service()  {
		// TODO Auto-generated method stub
		User user =(User) message.getParameter("User");
		try {
			message.setData(usersql.insert(user));
			System.out.println("新增用户中---");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onSuccess();
	}

}
