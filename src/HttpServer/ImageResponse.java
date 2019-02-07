package HttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ImageResponse implements Executor {

	public ImageResponse() {
	}


	@Override
	public void execute(Socket socket, String path) {
		try (OutputStream out = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));) {

			File file = new File(path);
			long fileLength = file.length();
			pw.println("HTTP/1.1 200 OK");
			pw.println("Content-Type: image/jpeg");
			pw.println("Content-Length: " + fileLength);
			pw.println("Connection: close");
			pw.println("");
			pw.flush();

			FileInputStream fis = new FileInputStream(file);

			byte[] b = new byte[1024];
			int read = 0;
			while ((read = fis.read(b)) != -1) {
				out.write(b, 0, read);
				out.flush();
			}

			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
