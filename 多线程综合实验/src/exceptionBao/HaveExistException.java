package exceptionBao;

public class HaveExistException extends BaseException {

	/**
	 * ʵ�����Ѿ�����ʱ�׳��쳣
	 */
	private static final long serialVersionUID = 6652098398262607292L;
	
	
	public HaveExistException(String objectName){
		super.message = objectName + Object_NAME_HASEXIST;
	}
	
	public static String Object_NAME_HASEXIST = "�Ѿ����ڣ���";
	
}