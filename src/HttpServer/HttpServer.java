package HttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

	private final int port;
	private ServerSocket listener;
	private ExecutorService executorService;

	public HttpServer(int port) {
		this.port = port;
	}

	public void init() {
		try {
			listener = new ServerSocket(port);
			executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void execute() {
		Socket socket;
		try {
			while (true) {
				System.out.println("접속 대기중");
				socket = listener.accept();
				System.out.printf("New Client Connect! Connected IP : %s, Port : %d\n", socket.getInetAddress(),
						socket.getPort());
				HandleSocket s = new HandleSocket(socket);
				executorService.submit(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer(8080);
		httpServer.init();
		httpServer.execute();
	}
}

//
//ServerSocket listener = null;
//try {
//	listener = new ServerSocket(8080);
//	System.out.println("접속대기");
//	while (true) {
//		Socket socket = listener.accept();
//		System.out.printf("New Client Connect! Connected IP : %s, Port : %d\n", socket.getInetAddress(),
//				socket.getPort());
//
//		new Thread(() -> {
//			try {
//				new HandleSocket(socket).makeResponse();
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		}).start();
//	}
//
//} catch (Exception ex) {
//	ex.printStackTrace();
//	try {
//		listener.close();
//	} catch (Exception e) {
//	}
//}
