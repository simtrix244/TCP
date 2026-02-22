package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("SERVER: Avvio server...");

        try (ServerSocket server = new ServerSocket(3000)) {
            System.out.println("SERVER: In attesa di un client...");
            Socket clientSocket = server.accept();
            System.out.println("SERVER: Client connesso: " + clientSocket.getInetAddress());

            // stream di lettura/scrittura
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String comando;
            while ((comando = in.readLine()) != null) {
                System.out.println("SERVER: Comando ricevuto: " + comando);

                String risposta;
                switch (comando.toLowerCase()) {
                    case "muovi":
                        risposta = "Sei riuscito a muoverti!";
                        break;
                    case "raccogli":
                        risposta = "Hai raccolto uno strumento!";
                        break;
                    case "attacca":
                        risposta = "Hai attaccato il drago!";
                        break;
                    case "fine":
                        risposta = "Chiusura connessione...";
                        out.println(risposta);
                        clientSocket.close();
                        System.out.println("SERVER: Connessione chiusa.");
                        return;
                    default:
                        risposta = "Comando non riconosciuto.";
                }

                out.println(risposta);
            }

        } catch (IOException e) {
            System.err.println("SERVER: Errore connessione o avvio server.");
            e.printStackTrace();
        }
    }
}