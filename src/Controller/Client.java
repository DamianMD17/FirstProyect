/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;
//llll

        try (
            Socket socket = new Socket(hostname, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput, serverResponse;

            // Leer mensaje inicial del servidor
            System.out.println("Servidor dice: " + in.readLine());

            // Comunicación constante
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                serverResponse = in.readLine();
                if (serverResponse != null) {
                    System.out.println("Servidor: " + serverResponse);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("No se conoce el host " + hostname);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
