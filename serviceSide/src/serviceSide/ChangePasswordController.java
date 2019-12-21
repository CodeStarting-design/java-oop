package serviceSide;

import exceptionBao.BaseException;
import exceptionBao.DaoException;
import javaSystem.User;

public class ChangePasswordController extends BaseController {

	@Override
	public void service()  {
		// TODO Auto-generated method stub
		User user = (User)message.getParameter("User");
		try {
			message.setData(usersql.update(user));
			System.out.println("”√ªß√‹¬Î–ﬁ∏ƒ---");
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		onSuccess();
	}

}

