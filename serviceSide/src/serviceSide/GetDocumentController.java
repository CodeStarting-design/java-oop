package serviceSide;

import exceptionBao.BaseException;
import javaSystem.Doc;

public class GetDocumentController extends BaseController {
     public void service() {
    	 Doc document=null;
    	 String id =(String) message.getParameter("ID");
    	 try {
			document=docsql.findById(id);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 message.setData(document);
    	 onSuccess();
     }
}