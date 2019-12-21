package clientSide;

import java.io.IOException;
import java.util.List;

import exceptionBao.BaseException;
import javaSystem.Controller;
import javaSystem.Doc;
import javaSystem.Message;

public class DocumentServiceClient extends BaseServiceClient {
	public Doc uploadDocument(Doc document) throws BaseException {//用于在上传
		Message message = new Message();
		message.setController(Controller.UPLOAD_DOCUMENT_CONTROLLER);
		message.setParameter("document", document);
		message = sendDocument(message);
		document = (Doc)message.getData();
		return document;
	}
	public Doc getDocument(String ID) throws BaseException {//根据ID获取某个文件对象
		Message message = new Message();
		message.setController(Controller.GET_DOCUMENT_CONTROLLER);
		message.setParameter("ID", ID);
		message = send(message);//返回的message中存储了文件对象
		Doc document = (Doc)message.getData();
		return document;
	}
	public List<Doc> getAllDocuments() throws BaseException {
		Message message = new Message();
		message.setController(Controller.GET_ALL_DOCUMENTS_CONTROLLER);
		message = send(message);
		List<Doc> documents = (List<Doc>)message.getData();
		return documents;
	}
	public Doc downloadDocument(String ID, String targetPath) throws BaseException, IOException {//传入文件ID及下载路径进行下载
		Message message = new Message();
		message.setController(Controller.DOWNLOAD_DOCUMENT_CONTROLLER);
		message.setParameter("ID", ID);
		message = receiveDocument(message, targetPath);//从服务器端进行接收
		Doc document = (Doc)message.getData();//将需要下载的文件对象返回出来
		return document;
	}
}
