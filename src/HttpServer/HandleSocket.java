package HttpServer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class HandleSocket implements Runnable {

	private final String WEB_ROOT = "C:/httpserver";
	private final Socket socket;

	public HandleSocket(Socket socket) {
		this.socket = socket;
	}

	public void makeResponse() {

		try (InputStream in = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in));) {

			String header = br.readLine();
			String[] data = header.split(" ");
			String path = data[1];

			// path는 절대경로, 상대경로가 있다.
			// www.naver.com/index.html index.html은 디스크에 어디에 저장되어 있다.
			// webroot가 필요
			// c://webroot/index.html

			String fileName = path.substring(path.lastIndexOf("/"));
			String[] fileNameAndExtension = fileName.split("\\.");
			boolean hasExtenstion = fileNameAndExtension.length >= 2;

			String extension = null;
			if (hasExtenstion) {
				extension = fileNameAndExtension[fileNameAndExtension.length - 1];
			}

			System.out.println(extension);

			// 확장자가 없는 경우는 어떻게 할꺼니
			if (!hasExtenstion || extension.equals("html")) {
				new HtmlResponse().execute(socket, WEB_ROOT + path);
			} else {
				new ImageResponse().execute(socket, WEB_ROOT + path);
			}

		} catch (Exception e) {

		}
	}

	@Override
	public void run() {
		makeResponse();
	}
}
