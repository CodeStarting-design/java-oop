package serviceSide;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener {//用于对客户端进行监听的类
	public void init() throws IOException {
		ServerSocket serviceSocket =null;
		try {
			serviceSocket = new ServerSocket(12341,100);
			System.out.println("Listening on " + serviceSocket);
			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serviceSocket.accept();
				System.out.println(client + " connected");
				// 处理这次连接
				new HandlerThread(client);
			}

		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		} finally {
			serviceSocket.close();
		}
	}

	private class HandlerThread implements Runnable {//创建一个线程对象
		private Socket socket;
		private ObjectInputStream in;
		private ObjectOutputStream out;

		public HandlerThread(Socket client) {
			socket = client;
			new Thread(this).start();
		}

		public void run() {
			try {
				System.out.println(this + "is running ......");
				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());
				Dispatchers.forward(in, out);//通过该方法，实现传递不同的message实现不同的功能
			} catch (Exception e) {
				System.out.println("服务器 run 异常: " + e.getMessage());
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						out.close();
						in.close();
						socket.close();
						System.out.println("close");
					} catch (Exception e) {
						socket = null;
						System.out.println("服务端 finally 异常:" + e.getMessage());
					}
				}
			}
		}
		
	}
}
