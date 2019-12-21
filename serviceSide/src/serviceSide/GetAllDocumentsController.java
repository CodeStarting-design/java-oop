package serviceSide;



import java.util.List;

import exceptionBao.BaseException;
import javaSystem.Doc;

public class GetAllDocumentsController extends BaseController {
	@Override
   public void service() {
	   List<Doc>documents=null;
	   try {
		documents =docsql.findAllOnes();
		System.out.println("正在向客户端导出文件列表---");
	} catch (BaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   message.setData(documents);
	   onSuccess();
   }
}
