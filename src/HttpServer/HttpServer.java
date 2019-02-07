package HttpServer;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	public static void main(String[] args) {
		ServerSocket listener = null;
		try {
			listener = new ServerSocket(8080);
			System.out.println("접속대기");
			while (true) {
				Socket socket = listener.accept();
				System.out.printf("New Client Connect! Connected IP : %s, Port : %d\n", socket.getInetAddress(),
						socket.getPort());

				new Thread(() -> {
					try {
						new HandleSocket(socket).makeResponse();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}).start();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				listener.close();
			} catch (Exception e) {
			}
		}
	}
}
