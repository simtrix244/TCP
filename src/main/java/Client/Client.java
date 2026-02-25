package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    String nome;
    String colore;
    Socket socket;



    Client(String nome, String colore){
        this.nome=nome;
        this.colore=colore;

    }

    Client(String nome){
        this.nome=nome;

    }

  public int connetti(String nomeServer,int portaServer) throws IOException {
      System.out.print("Inserisci il nome host del server: ");
      Scanner scanner = null;
      String host = scanner.nextLine();

      try (Socket socket = new Socket(host, 3000);
           BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

          System.out.println("CLIENT: Connesso al server " + host);
      } catch (UnknownHostException e) {
          System.err.println("CLIENT: Host non trovato. Controlla il nome inserito.");
      } catch (IOException e) {
          System.err.println("CLIENT: Errore di connessione. Il server potrebbe non essere disponibile.");
      }
      return portaServer;
  }


  public void scrivi(){
      System.out.print("Inserisci il messaggio da inviare: ");
      Scanner scanner = null;
      String messaggio = scanner.nextLine();
  }



  public void leggi(String messaggio) throws IOException {
      BufferedReader br = null;
      String risposta = br.readLine();
      System.out.println("CLIENT: Risposta del server: " + risposta);

      }


      public void chiudi() throws IOException {
          BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }
  }
