package clientSide;

import exceptionBao.BaseException;

public class NetworkException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NetworkException(){
		super.message = NETWORK_EXCEPTION;
	}
	
	public static String NETWORK_EXCEPTION = "Õ¯¬Á∑√Œ “Ï≥£";
	
}