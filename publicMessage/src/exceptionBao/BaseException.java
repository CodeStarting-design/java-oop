package exceptionBao;

public class BaseException extends Exception {
	
    protected String message;

	private static final long serialVersionUID = 1326790579993179244L;
	
	public BaseException(){
		message="“µŒÒ“Ï≥£";
	}
	
	public String getMessage(){
		return this.message;
	}

}