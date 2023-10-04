import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class wikiMulti {
    private static final int threads = 2;
    private static final int paginas = 50;

    public static void main(String[] args) {
        System.out.println("Executando com threads:");

        long comeco = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<String>> resultados = new ArrayList<>();

        for (int i = 1; i <= paginas; i++) {
            String wikiPageUrl = "https://en.wikipedia.org/wiki/" + i;
            Callable<String> tarefa = () -> getWikiPageExistence(wikiPageUrl);
            Future<String> result = executor.submit(tarefa);
            resultados.add(result);
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            for (Future<String> result : resultados) {
                System.out.println(result.get());
            }

            long fim = System.currentTimeMillis();
            double tempoTotal = (fim - comeco) / 1000.0;
            System.out.println("Tempo de execucao: " + tempoTotal + "s");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
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
                return wikiPageUrl + " - Não existe";
            } else {
                return wikiPageUrl + " - Desconhecido";
            }
        } catch (IOException e) {
            return wikiPageUrl + " - error: " + e.getMessage();
        }
    }
}
