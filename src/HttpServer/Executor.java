package HttpServer;

import java.net.Socket;

public interface Executor {
	public void execute(Socket socket, String path);
}
