package Server;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) {
        System.out.println("SERVER: Avvio server...");

        try (ServerSocket server = new ServerSocket(3000)) {
            System.out.println("SERVER: In attesa di un client...");

            Socket clientSocket = server.accept();
            System.out.println("SERVER: Client connesso: " + clientSocket.getInetAddress());

            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Legge richiesta del client
            String richiesta = br.readLine();
            System.out.println("SERVER: Richiesta ricevuta: " + richiesta);

            // Invia risposta al client
            out.println("Richiesta '" + richiesta + "' ricevuta dal server.");

            // Chiusura comunicazione con client
            clientSocket.close();
            System.out.println("SERVER: Connessione chiusa con il client.");

        } catch (BindException be) {
            System.err.println("SERVER: Porta già in uso. Avvio server fallito.");
        } catch (IOException e) {
            System.err.println("SERVER: Errore nella comunicazione.");
            e.printStackTrace();
        }

        System.out.println("SERVER: Servizio terminato.");
    }
}