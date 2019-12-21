package serviceSide;

import java.io.IOException;

import exceptionBao.BaseException;
import javaSystem.Doc;

public class DownloadDocumentController extends BaseController {

	@Override
	public void service() throws BaseException {
		// TODO Auto-generated method stub
		String ID =(String) message.getParameter("ID");
		Doc document =docsql.findById(ID);
		message.setData(document);
		onSuccess();
		try {
			sendFile(document.getFilename());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
