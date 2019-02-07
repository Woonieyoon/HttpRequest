package HttpServer;

import java.util.HashMap;
import java.util.Map;

public class CashManager {

	private Map<String, Executor> cashMap;
	
	public CashManager() {
		cashMap = new HashMap<>();
	}

	public void init() {
		cashMap.put("html", new HtmlResponse());
		cashMap.put("jpg", new ImageResponse());
	}
	
	
}
