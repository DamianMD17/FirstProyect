package javaserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaServer {

    private static final int PORT = 8081;
    private static final int MAX_THREADS = 10;
    private static final ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

    private static final FileUserAuthentication fileUserAuthentication = new FileUserAuthentication();

    private static final FileUserAuthenticationNoEncrypt fileUserAuthenticationNoEncrypt = new FileUserAuthenticationNoEncrypt();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT, 0, InetAddress.getLocalHost())) {
            System.out.println("Servidor escuchando en localhost en el puerto " + PORT);
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println("Dirección IP del servidor: " + inetAddress.getHostAddress());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());
                Runnable clientHandler = new ClientHandler(clientSocket, fileUserAuthentication);
                executor.execute(clientHandler);
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    static class ClientHandler implements Runnable {

        private final Socket clientSocket;
        private final FileUserAuthentication fileUserAuthentication;

        public ClientHandler(Socket clientSocket, FileUserAuthentication fileUserAuthentication) {
            this.clientSocket = clientSocket;
            this.fileUserAuthentication = fileUserAuthentication;
        }

        @Override
        public void run() {
            try (
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); OutputStream fileOut = clientSocket.getOutputStream()) {
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Mensaje del cliente: " + inputLine);
                    String[] AuthParts = inputLine.split("-");
                    if (!Boolean.parseBoolean(AuthParts[2])) {
                        handleAuthenticationRequest(inputLine, out);
                    } else {
                        handleOtherRequests(AuthParts[0], out, fileOut, AuthParts[1]);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al manejar la conexión con el cliente: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el socket del cliente: " + e.getMessage());
                }
            }
        }

        private void handleAuthenticationRequest(String inputLine, PrintWriter out) throws IOException {
            String[] parts = inputLine.split(",");
            
            System.out.println("Autenticacion: " + inputLine);
            int operationCode = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            
            String Rolparts = parts[3].substring(1);
            System.out.println("Partes del rol: " + Rolparts);
            String[] AuthUserparts =  parts[3].split("-");
            
            String rol = AuthUserparts[0];
            
            System.out.println("Rol del usuario: " + rol);
            
            switch (operationCode) {
                case 0:
                    String isAuthenticated = fileUserAuthentication.authenticateUser(username, password);

                    String[] Userparts = isAuthenticated.split("-");
                    System.out.println("Partes: " + isAuthenticated);
                    if (Boolean.parseBoolean(Userparts[0])) {
                        out.println("auth exitoso true-" + Userparts[1]);
                    } else {
                        out.println("auth exitoso false");
                        clientSocket.close();
                    }
                    break;
                case 1:
                    fileUserAuthentication.registerUser(username, password, rol);
                    out.println("auth exitoso true");
                    break;
                default:
                    System.err.println("Código de operación no válido");
                    break;
            }
        }

        private void handleOtherRequests(String inputLine, PrintWriter out, OutputStream fileOut, String rol) {
            if (inputLine.startsWith("GET_")) {
                String requestType = inputLine.substring(4);
                switch (requestType) {
                    case "VIDEOS":
                        List<File> videos = MediaManager.getVideos();
                        List<File> combinedVideos = new ArrayList<>(videos);

                        if (Boolean.parseBoolean(rol)) {
                            List<File> videosAdmin = MediaManager.getVideosAdmin();
                            combinedVideos.addAll(videosAdmin);
                        }
                        sendFilesList(out, combinedVideos);

                        break;

                    case "FILES":
                        List<File> files = MediaManager.getFiles();
                        sendFilesList(out, files);
                        break;
                    case "MUSIC":
                        List<File> music = MediaManager.getMusic();
                        sendFilesList(out, music);
                        break;
                    default:
                        System.err.println("Tipo de solicitud no válido: " + requestType);
                        break;
                }
            } else if (inputLine.startsWith("DOWNLOAD_")) {

                String requestType = inputLine.substring(9);
                if (requestType.startsWith("MUSIC_")) {
                    String song = requestType.substring(6);
                    String songFilePath = "media/musica/" + song;

                    String AdminsongFilePath = "media/musicaAdmin/" + song;

                    sendFileToClient(songFilePath, AdminsongFilePath, rol, fileOut);

                } else if (requestType.startsWith("VIDEO_")) {
                    String video = requestType.substring(6);
                    String videoFilePath = "media/videos/" + video;

                    String AdminvideoFilePath = "media/videosAdmin/" + video;

                    sendFileToClient(videoFilePath, AdminvideoFilePath, rol, fileOut);
                } else if (requestType.startsWith("FILE_")) {
                    String file = requestType.substring(5);
                    String fileFilePath = "media/documentos/" + file;

                    String AdminfileFilePath = "media/documentosAdmin/" + file;

                    sendFileToClient(fileFilePath, AdminfileFilePath, rol, fileOut);
                } else {
                    System.out.println("Tipo de dato no existe");
                }
            }
        }

        private void sendFilesList(PrintWriter out, List<File> files) {
            StringBuilder sb = new StringBuilder();
            for (File file : files) {
                sb.append(file.getName()).append(";");
            }
            sb.append("END_OF_LIST");
            out.println(sb.toString());
        }

        private void sendFileToClient(String filePath, String adminPath, String rol, OutputStream out) {
            File file = new File(filePath);
            if (!file.exists()) {

                if (Boolean.parseBoolean(rol)) {
                    file = new File(adminPath);
                } else {
                    System.out.println("El archivo no existe");
                    return;
                }

            }

            try (FileInputStream fileInputStream = new FileInputStream(file); BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

                byte[] fileData = new byte[(int) file.length()];
                bufferedInputStream.read(fileData, 0, fileData.length);

                out.write(fileData, 0, fileData.length);
                out.flush();

                System.out.println("Archivo enviado: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // Cerrar el flujo de salida
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
