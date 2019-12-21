package serviceSide;

import java.util.HashMap;
import java.io.*;


import exceptionBao.BaseException;
import javaSystem.Controller;
import javaSystem.Message;

public class Dispatchers {
	private static HashMap<String, BaseController> controllers = new HashMap<String, BaseController>();//���ڴ洢Controller�����message�е�controller
	static {
		for (String controllerClassname : Controller.CONTROLLER_CLASSNAMES) {
			BaseController baseController;
			try {
				baseController = (BaseController) Class.forName(controllerClassname).newInstance();
				//class.forName���������Ǽ������ж�Ӧ���������ٵ���newInstance�������ж���ʵ��
				controllers.put(baseController.getClass().getName(), baseController);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void forward(ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException, BaseException {
		Message message = (Message)in.readObject(); // read new message
		BaseController controller = controllers.get(message.getController());//�ӹ�ϣ�����ȡ��Ӧ�Ķ���
		controller.setOut(out);
		controller.setIn(in);
		controller.setMessage(message);
		System.out.println(controller.getClass().getSimpleName() + " is serving ......");
		controller.service();//���ö�Ӧ��service
}
}