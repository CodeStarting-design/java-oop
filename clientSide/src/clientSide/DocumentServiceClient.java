package clientSide;

import java.io.IOException;
import java.util.List;

import exceptionBao.BaseException;
import javaSystem.Controller;
import javaSystem.Doc;
import javaSystem.Message;

public class DocumentServiceClient extends BaseServiceClient {
	public Doc uploadDocument(Doc document) throws BaseException {//�������ϴ�
		Message message = new Message();
		message.setController(Controller.UPLOAD_DOCUMENT_CONTROLLER);
		message.setParameter("document", document);
		message = sendDocument(message);
		document = (Doc)message.getData();
		return document;
	}
	public Doc getDocument(String ID) throws BaseException {//����ID��ȡĳ���ļ�����
		Message message = new Message();
		message.setController(Controller.GET_DOCUMENT_CONTROLLER);
		message.setParameter("ID", ID);
		message = send(message);//���ص�message�д洢���ļ�����
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
	public Doc downloadDocument(String ID, String targetPath) throws BaseException, IOException {//�����ļ�ID������·����������
		Message message = new Message();
		message.setController(Controller.DOWNLOAD_DOCUMENT_CONTROLLER);
		message.setParameter("ID", ID);
		message = receiveDocument(message, targetPath);//�ӷ������˽��н���
		Doc document = (Doc)message.getData();//����Ҫ���ص��ļ����󷵻س���
		return document;
	}
}
