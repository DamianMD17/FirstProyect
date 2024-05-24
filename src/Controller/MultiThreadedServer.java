/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author damia
 */
import java.io.*;
import java.net.*;

public class MultiThreadedServer {
    public static void main(String[] args) {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado");
                new ClientHandler(clientSocket).start(); // Método nuevo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() { // Método nuevo
        try (
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String inputLine, outputLine;

            // Enviar mensaje inicial al cliente
            out.println("Hola cliente");

            // Comunicación constante
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Cliente: " + inputLine);
                if (inputLine.equalsIgnoreCase("hola")) {
                    out.println("Hola cliente");
                }
                outputLine = stdIn.readLine();
                if (outputLine != null) {
                    out.println(outputLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
