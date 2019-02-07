import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class apache {

	public static void main(String[] args) {

		URL url = null;
		InputStream in = null;

		try {
			url = new URL("https://www.naver.com");
			in = url.openStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(org.apache.commons.io.IOUtils.toString(url, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
