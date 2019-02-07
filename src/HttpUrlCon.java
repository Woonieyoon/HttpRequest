import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlCon {
		
	public static void main(String[] args) {
	
		String request = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=dm+s";
		try {
			HttpURLConnection con = (HttpURLConnection)new URL(request).openConnection();
			BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
			
			String result;
			while((result=input.readLine())!=null) {
				System.out.println(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
