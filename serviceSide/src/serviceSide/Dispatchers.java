package serviceSide;

import java.util.HashMap;
import java.io.*;


import exceptionBao.BaseException;
import javaSystem.Controller;
import javaSystem.Message;

public class Dispatchers {
	private static HashMap<String, BaseController> controllers = new HashMap<String, BaseController>();//用于存储Controller对象和message中的controller
	static {
		for (String controllerClassname : Controller.CONTROLLER_CLASSNAMES) {
			BaseController baseController;
			try {
				baseController = (BaseController) Class.forName(controllerClassname).newInstance();
				//class.forName（。。）是加括号中对应的类名，再调用newInstance方法进行对象实例
				controllers.put(baseController.getClass().getName(), baseController);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void forward(ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException, BaseException {
		Message message = (Message)in.readObject(); // read new message
		BaseController controller = controllers.get(message.getController());//从哈希表里读取相应的对象
		controller.setOut(out);
		controller.setIn(in);
		controller.setMessage(message);
		System.out.println(controller.getClass().getSimpleName() + " is serving ......");
		controller.service();//调用对应的service
}
}