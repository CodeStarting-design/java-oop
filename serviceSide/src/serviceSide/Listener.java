package serviceSide;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener {//���ڶԿͻ��˽��м�������
	public void init() throws IOException {
		ServerSocket serviceSocket =null;
		try {
			serviceSocket = new ServerSocket(12341,100);
			System.out.println("Listening on " + serviceSocket);
			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serviceSocket.accept();
				System.out.println(client + " connected");
				// �����������
				new HandlerThread(client);
			}

		} catch (Exception e) {
			System.out.println("�������쳣: " + e.getMessage());
		} finally {
			serviceSocket.close();
		}
	}

	private class HandlerThread implements Runnable {//����һ���̶߳���
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
				Dispatchers.forward(in, out);//ͨ���÷�����ʵ�ִ��ݲ�ͬ��messageʵ�ֲ�ͬ�Ĺ���
			} catch (Exception e) {
				System.out.println("������ run �쳣: " + e.getMessage());
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
						System.out.println("����� finally �쳣:" + e.getMessage());
					}
				}
			}
		}
		
	}
}
