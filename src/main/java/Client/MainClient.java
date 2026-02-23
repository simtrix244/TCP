package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) {
        System.out.println("CLIENT: Avvio client...");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il nome host del server: ");
        String host = scanner.nextLine();

        try (Socket socket = new Socket(host, 3000);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("CLIENT: Connesso al server " + host);


                System.out.print("Inserisci il messaggio da inviare: ");
                String messaggio = scanner.nextLine();

            // Lettura risposta
            String risposta = br.readLine();
            System.out.println("CLIENT: Risposta del server: " + risposta);
                while(!Objects.equals(messaggio, "Addios") || !Objects.equals(risposta, "Addios")) {


                    // Invio richiesta
                    out.println(messaggio);

                    // Lettura risposta
                    risposta = br.readLine();
                    System.out.println("CLIENT: Risposta del server: " + risposta);
                }


            System.out.println("CLIENT: Chiusura comunicazione.");

        } catch (UnknownHostException e) {
            System.err.println("CLIENT: Host non trovato. Controlla il nome inserito.");
        } catch (IOException e) {
            System.err.println("CLIENT: Errore di connessione. Il server potrebbe non essere disponibile.");
        }

        System.out.println("CLIENT: Fine esecuzione.");
    }
}