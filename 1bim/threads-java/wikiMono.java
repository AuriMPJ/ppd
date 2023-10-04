import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class wikiMono {
    private static final String WIKI_BASE_URL = "https://en.wikipedia.org/wiki/";
    private static final int paginas = 50;

    public static void main(String[] args) {
        System.out.println("Executando mono:");

        long comeco = System.currentTimeMillis();

        for (int i = 1; i <= paginas; i++) {
            String wikiPageUrl = WIKI_BASE_URL + i;
            String pageStatus = getWikiPageExistence(wikiPageUrl);
            System.out.println(pageStatus);
        }

        long fim = System.currentTimeMillis();
        double tempoTotal = (fim - comeco) / 1000.0;

        System.out.println("Tempo de execucao: " + tempoTotal + "s");
    }

    private static String getWikiPageExistence(String wikiPageUrl) {
        try {
            URL url = new URL(wikiPageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                return wikiPageUrl + " - Existe";
            } else if (responseCode == 404) {
                return wikiPageUrl + " - Nao Existe";
            } else {
                return wikiPageUrl + " - Desconhecido";
            }
        } catch (IOException e) {
            return wikiPageUrl + " - error: " + e.getMessage();
        }
    }
}
