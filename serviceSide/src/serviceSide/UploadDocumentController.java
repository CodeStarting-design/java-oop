package serviceSide;

import java.io.IOException;

import exceptionBao.BaseException;
import javaSystem.Doc;

public class UploadDocumentController extends BaseController {

	@Override
	public void service()  {
		// TODO Auto-generated method stub
		Doc document=null;
		document=(Doc) message.getParameter("document");
		try {
			receiveTempFile(Doc.getFileName(document.getFilename()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			document=docsql.insert(document);
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		message.setData(document);
		onSuccess();
	}

}

