package serviceSide;

import java.io.IOException;

public class Main {
	public static void main(String[] args)  {
		Listener listener = new Listener();
		try {
			listener.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
