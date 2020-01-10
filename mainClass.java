package kafka2;

public class mainClass {

	public static void main(String[] args) {
		System.out.println("Beggining");
		System.setProperty("http.proxyHost", "webguard.gjensidige.no");
		System.setProperty("http.proxyPort", "8080");
		TwitterController twitterController = new TwitterController();
		System.out.println("THE END");
	}

}
