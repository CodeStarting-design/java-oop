package serviceSide;

import exceptionBao.BaseException;
import javaSystem.User;

public class GetUserController extends BaseController {
public void service() {
	String name = (String) message.getParameter("name");
	User userOne;
	try {
		 userOne =usersql.findByName(name);
		 message.setData(userOne);
		onSuccess();
	} catch (BaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
