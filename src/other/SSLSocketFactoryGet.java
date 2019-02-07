package other;

import javax.net.ssl.SSLSocketFactory;

public class SSLSocketFactoryGet {

	public static void main(String[] args) {

		String host = "www.naver.com";
		int port = 80;
		
		SSLSocketFactory socketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
	}

}
