package compito.aser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // Crea un socket server che resta in ascolto sulla porta 3000
        ServerSocket serverSocket = new ServerSocket(3000);

        // Ciclo infinito di accettazione connessioni
        while (true) {
            Socket clientSocket = serverSocket.accept();

            // Crea un thread dedicato per il client
            ServerThread serverThread = new ServerThread(clientSocket);
            serverThread.start();
        }
    }
}