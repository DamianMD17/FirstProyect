package javaserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCommunication {

    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;
    private final OutputStream binaryOut;
    private final InputStream binaryIn;

    public ClientCommunication(Socket socket, PrintWriter out, BufferedReader in, OutputStream binaryOut,
            InputStream binaryIn) throws IOException {
        this.socket = socket;
        this.out = out;
        this.in = in;
        this.binaryOut = binaryOut;
        this.binaryIn = socket.getInputStream();
    }

    // Método para enviar mensajes al servidor
    public void sendMessage(String message) {
        if (out != null) {
            System.out.println("Enviando mensaje al servidor: " + message);
            out.println(message);
            out.flush(); // Asegúrate de que el mensaje se envíe inmediatamente
        } else {
            System.err.println("PrintWriter no inicializado");
        }
    }

    public String receiveMessage() throws IOException {
        if (in != null) {
            String response = in.readLine();
            System.out.println("Mensaje recibido del servidor: " + response);
            return response;
        } else {
            System.err.println("BufferedReader no inicializado");
            return null;
        }
    }

    // Método para recibir archivos del servidor
    public void receiveFileFromServer(String filePath) {
        System.out.println("Recibiendo archivo: " + filePath);
        byte[] buffer = new byte[8192];
        try (FileOutputStream fos = new FileOutputStream(filePath);
                BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            int bytesRead;
            System.out.println("Iniciando bucle de lectura de datos...");
            while ((bytesRead = binaryIn.read(buffer)) != -1) {
                // Imprimir el número de bytes leídos en cada iteración
                bos.write(buffer, 0, bytesRead);
            }
            System.out.println("Saliendo del bucle while...");
            

            bos.flush();
            System.out.println("Archivo recibido y guardado en: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar la conexión con el servidor
    public void close() {

        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }
}
