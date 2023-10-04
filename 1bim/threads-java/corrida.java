import java.util.Random;

class WorkerThread extends Thread {
    private String nomeThread;

    public WorkerThread(String nomeThread) {
        this.nomeThread = nomeThread;
    }

    public void run() {
        int posicaoAtual = 0;
        int passosTotais = 0;

        System.out.println("Thread " + nomeThread + " - Iniciando");

        while (posicaoAtual < 50) {
            int passos = gerarNumeroAleatorio();
            passosTotais += passos;
            posicaoAtual += passos;

            System.out.println("Vez da Thread " + nomeThread);
            System.out.println("Número sorteado: " + passos);
            System.out.println("Thread " + nomeThread + " andou " + passos + " passos");
            System.out.println("Posição atual da Thread " + nomeThread + " - " + posicaoAtual);

            esperarMS(1000);
        }

        System.out.println("Thread " + nomeThread + " - chegou a 50");
        corrida.updateResults(nomeThread, passosTotais);
    }

    private int gerarNumeroAleatorio() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

    private void esperarMS(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class corrida {
    private static final int numThreads = 2;
    private static int threadsFinalizadas = 0;
    private static String[] resultadoThread = new String[numThreads];
    private static int[] threadpassos = new int[numThreads];

    public static void main(String[] args) {
        System.out.println("Executando corrida de threads:");

        for (int i = 0; i < numThreads; i++) {
            WorkerThread thread = new WorkerThread("Thread " + (i + 1));
            thread.start();
        }
    }

    public static synchronized void updateResults(String nomeThread, int passosTotais) {
        int threadIndex = Integer.parseInt(nomeThread.split(" ")[1]) - 1;
        resultadoThread[threadIndex] = "Thread " + threadIndex + " venceu com " + passosTotais + " passos";
        threadpassos[threadIndex] = passosTotais;

        threadsFinalizadas++;

        if (threadsFinalizadas == numThreads) {
            int vencedorIndex = findWinner();
            System.out.println(resultadoThread[vencedorIndex]);
        }
    }

    private static int findWinner() {
        int vencedorIndex = 0;
        int minpassos = threadpassos[0];

        for (int i = 1; i < numThreads; i++) {
            if (threadpassos[i] < minpassos) {
                vencedorIndex = i;
                minpassos = threadpassos[i];
            }
        }

        return vencedorIndex;
    }
}
