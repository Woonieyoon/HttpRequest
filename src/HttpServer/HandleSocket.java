package HttpServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HandleSocket implements Runnable {

	private final Socket socket;

	public HandleSocket(Socket socket) {
		this.socket = socket;
	}

	public void makeResponse() {

		try (OutputStream out = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
				InputStream in = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in));) {

			System.out.println("ss");
			String header = br.readLine();

			String[] data = header.split(" ");
			String path = data[1];
			System.out.println(header);

			File file = null;
			FileInputStream fis = null;

			if (path.endsWith(".html")) {
				file = new File("C:/httpserver/index.html");
				long fileLength = file.length();
				pw.println("HTTP/1.1 200 OK");
				pw.println("Content-Type: text/html; charset=UTF-8");
				pw.println("Content-Length: " + fileLength);
				pw.println("");
			} else {
				file = new File("C:/httpserver/Penguins.jpg");
				long fileLength = file.length();
				pw.println("HTTP/1.1 200 OK");
				pw.println("Content-Type: image/jpeg");
				pw.println("Content-Length: " + fileLength);
				pw.println("");
			}
			pw.flush();

			fis = new FileInputStream(file);

			byte[] b = new byte[1024];
			int read = 0;
			while ((read = fis.read(b)) != -1) {
				out.write(b, 0, read);
				System.out.println(read);
				out.flush();
			}

			fis.close();

		} catch (Exception e) {

		}
	}

	@Override
	public void run() {
		makeResponse();
	}
}
