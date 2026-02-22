package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("CLIENT: Avvio client...");

        try (Socket socket = new Socket("localhost", 3000);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("CLIENT: Connesso al server.");

            String comando;
            while (true) {
                System.out.print("Inserisci comando (muovi/raccogli/attacca/fine): ");
                comando = scanner.nextLine();
                out.println(comando);

                String risposta = in.readLine();
                System.out.println("SERVER: " + risposta);

                if (comando.equalsIgnoreCase("fine")) {
                    System.out.println("CLIENT: Chiusura client.");
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("CLIENT: Errore di connessione. Controlla il server o il nome host.");
        }
    }
}