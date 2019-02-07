import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpGetSocket {

	public void requestGet(String host, int port, String requestURI) {

		try (Socket socket = new Socket(host, port);
				PrintWriter pWriter = new PrintWriter(socket.getOutputStream());
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

			pWriter.println("GET /" + requestURI + " HTTP/1.1");
			pWriter.println("Accept: */*");
			pWriter.println("Accpet-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
			pWriter.println("User-Agent: Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
			pWriter.println("Host: " + host);
			pWriter.println("Connection: close");
			pWriter.println("");
			pWriter.flush();

			String result;

			while ((result = bufferedReader.readLine()) != null) {
				System.out.println(result);
			}

		} catch (Exception e) {

		}

	}

	public static void main(String[] args) {

		HttpGetSocket httpGetSocket = new HttpGetSocket();
		httpGetSocket.requestGet("www.naver.com", 80, "");
		// httpGetSocket.requestGet("search.naver.com", 80,
		// "/search.naver?where=nexearch&query=영화+1987&sm=top_lve&ie=utf8");

	}

}


//
