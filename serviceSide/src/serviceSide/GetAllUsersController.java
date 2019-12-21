package serviceSide;

import java.util.List;

import exceptionBao.BaseException;
import javaSystem.User;

public class GetAllUsersController extends BaseController {

	@Override
	public void service()  {
		// TODO Auto-generated method stub
		List<User> users=null;
		try {
			users=usersql.findAllOnes();
			System.out.println("正在向客户端导出用户列表---");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message.setData(users);
		onSuccess();
	}
       
}
