package Views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;

import javaserver.ClientCommunication;

public final class Client extends javax.swing.JFrame {

    private final String username;
    private final ClientCommunication clientCommunication;
    private final Socket socket;
    private static final String SERVER_ADDRESS = "10.235.63.54"; // Cambia esto con la dirección IP de tu servidor
    private static final int SERVER_PORT = 8081;

    public Client(String username, ClientCommunication clientCommunication) {
        this.username = username;
        this.clientCommunication = clientCommunication;
        socket = Login.sharedSocket;
        System.out.println("Socket compartido desde el login con Client: " + socket);
        System.out.println("Rol admin? " + Login.shareRol);
        if (socket == null) {
            // Maneja el caso en el que el socket sea nulo
            System.err.println("El socket es nulo");
            return;
        }
        initComponents();
        connectToServer();
        initializeJavaFX();
        updateWelcomeMessage(username);
        MusicList.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                // Obtiene la canción seleccionada
                String selectedSong = MusicList.getSelectedValue();
                if (selectedSong != null) {
                    try {
                        downloadFileFromServer(selectedSong, "MUSIC");
                        showMusicPlayer(selectedSong);
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

        VideoList.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                // Obtiene la canción seleccionada
                String selectedVideo = VideoList.getSelectedValue();
                if (selectedVideo != null) {
                    try {
                        // Muestra la interfaz para reproducir la canción
                        downloadFileFromServer(selectedVideo, "VIDEO");
                        showVideoPlayer(selectedVideo);
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

        DocList.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                // Obtiene la canción seleccionada
                String selectedFile = DocList.getSelectedValue();
                if (selectedFile != null) {
                    try {
                        // Muestra la interfaz para reproducir la canción
                        downloadFileFromServer(selectedFile, "FILE");
                        showFilePlayer(selectedFile);
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        VideoList = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MusicList = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DocList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenido al servidor");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Videos");

        jScrollPane3.setViewportView(VideoList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 228,
                                                Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                                .addGap(18, 18, 18)));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(240, 0));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Música");

        jScrollPane2.setViewportView(MusicList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0,
                                                Short.MAX_VALUE)
                                        .addContainerGap())));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel3)
                                .addContainerGap(377, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 353,
                                                Short.MAX_VALUE)
                                        .addGap(16, 16, 16))));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Documentos");

        jScrollPane1.setViewportView(DocList);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230,
                                                Short.MAX_VALUE))
                                .addContainerGap()));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                                .addGap(17, 17, 17)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 411,
                                                Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectToServer() {
        try {
            // Enviar solicitud al servidor para obtener la lista de música
            clientCommunication.sendMessage("GET_MUSIC" + "-" + Login.shareRol + "-"  + Login.sharedAuth);

            // Recibir respuesta del servidor
            String serverResponse = clientCommunication.receiveMessage();
            System.out.println("Respuesta del servidor para el cliente: " + serverResponse);
            // Procesar la respuesta del servidor y actualizar la lista de música
            ListModel<String> songs = processServerResponse(serverResponse);
            updateSongList(songs);

            // Enviar solicitud al servidor para obtener la lista de archivos
            clientCommunication.sendMessage("GET_FILES-" + Login.shareRol + "-"  + Login.sharedAuth );

            // Recibir respuesta del servidor
            String serverResponseFiles = clientCommunication.receiveMessage();
            System.out.println("Files from server: " + serverResponseFiles);
            // Procesar la respuesta del servidor y actualizar la lista de archivos
            ListModel<String> fileListModel = processServerResponse(serverResponseFiles);
            updateFileList(fileListModel);

            // Enviar solicitud al servidor para obtener la lista de videos
            clientCommunication.sendMessage("GET_VIDEOS-" + Login.shareRol + "-"  + Login.sharedAuth);

            // Recibir respuesta del servidor
            String serverResponseVideos = clientCommunication.receiveMessage();
            System.out.println("Videos from server: " + serverResponseVideos);
            // Procesar la respuesta del servidor y actualizar la lista de videos
            ListModel<String> videoListModel = processServerResponse(serverResponseVideos);
            updateVideoList(videoListModel);

            System.out.println("Lista de musica: " + songs);
            System.out.println("Lista de archivos: " + fileListModel);
            System.out.println("Lista de videos: " + videoListModel);

            /*downloadFromServer(songs, "MUSIC");
            downloadFromServer(fileListModel, "FILE");
            downloadFromServer(videoListModel, "VIDEO");*/
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar errores de conexión
        }
    }

    //Esta función sirve para descargar toda una lista de archivos
    private void downloadFromServer(ListModel<String> lista, String tipo) throws IOException {
        int TIMEOUT = 500;

        for (int i = 0; i < lista.getSize(); i++) {
            System.out.println("Archivo: " + lista.getElementAt(i));

            // Verificar si el usuario está autenticado
            if (Login.sharedAuth) {

                // Crear una nueva instancia de ClientCommunication y un nuevo socket
                try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT); PrintWriter out = new PrintWriter(socket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); OutputStream binaryOut = socket.getOutputStream(); InputStream binaryIn = socket.getInputStream()) {

                    ClientCommunication clientCommunication = new ClientCommunication(socket, out, in, binaryOut, binaryIn);
                    clientCommunication.sendMessage("DOWNLOAD_" + tipo + "_" + lista.getElementAt(i) + "-" + Login.shareRol + "-" + Login.sharedAuth);
                    clientCommunication.receiveFileFromServer("download/" + lista.getElementAt(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Usuario no autenticado, no se puede descargar el archivo
                System.err.println("El usuario no está autenticado.");
            }

            // Agregar un tiempo de espera entre cada solicitud
            try {
                TimeUnit.MILLISECONDS.sleep(TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    //Esta función sirve para descargar un unico archivo
    private void downloadFileFromServer(String file, String tipo) throws IOException {
        int TIMEOUT = 500;

        // Verificar si el usuario está autenticado
        if (Login.sharedAuth) {

            // Crear una nueva instancia de ClientCommunication y un nuevo socket
            try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT); PrintWriter out = new PrintWriter(socket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); OutputStream binaryOut = socket.getOutputStream(); InputStream binaryIn = socket.getInputStream()) {

                ClientCommunication clientCommunication = new ClientCommunication(socket, out, in, binaryOut, binaryIn);
                clientCommunication.sendMessage("DOWNLOAD_" + tipo + "_" + file + "-" + Login.shareRol + "-" + Login.sharedAuth);
                clientCommunication.receiveFileFromServer("download/" + file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Usuario no autenticado, no se puede descargar el archivo
            System.err.println("El usuario no está autenticado.");
        }

        // Agregar un tiempo de espera entre cada solicitud
        try {
            TimeUnit.MILLISECONDS.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void showMusicPlayer(String selectedSong) throws IOException {
        // Mostrar la interfaz para reproducir la canción seleccionada
        MusicPlayerFrame playerFrame = new MusicPlayerFrame(selectedSong, clientCommunication);
        playerFrame.setVisible(true);
    }

    private void showFilePlayer(String selectedFile) {
        // Mostrar la interfaz para visualizar el archivo seleccionado
        FilesPlayerFrame playerFrame = new FilesPlayerFrame(selectedFile);
        playerFrame.setVisible(true);
    }

    private void showVideoPlayer(String selectedVideo) {
        // Mostrar la interfaz para reproducir el video seleccionado
        VideoPlayerFrame playerFrame = new VideoPlayerFrame(selectedVideo);
        playerFrame.setVisible(true);
    }

    public void updateSongList(ListModel<String> songs) {
        // Actualizar la lista de canciones en la interfaz de usuario
        MusicList.setModel(songs);
    }

    public void updateFileList(ListModel<String> files) {
        // Actualizar la lista de archivos en la interfaz de usuario
        DocList.setModel(files);
    }

    public void updateVideoList(ListModel<String> videos) {
        // Actualizar la lista de videos en la interfaz de usuario
        VideoList.setModel(videos);
    }

    private ListModel<String> processServerResponse(String response) {
        // Procesar la respuesta del servidor y crear un modelo de lista
        DefaultListModel<String> songListModel = new DefaultListModel<>();
        String[] songsArray = response.split(";"); // Suponiendo que ';' es el delimitador

        for (String song : songsArray) {
            if (song.equals("END_OF_LIST")) {
                break; // Detener el ciclo si encuentra la cadena "END_OF_LIST"
            }
            songListModel.addElement(song);
        }

        return songListModel;
    }

    public void updateWelcomeMessage(String username) {
        // Actualizar el mensaje de bienvenida en la interfaz de usuario
        String welcomeMessage = "Bienvenido al servidor, " + username + "!";
        jLabel1.setText(welcomeMessage);
    }

    private void initializeJavaFX() {
        // Inicializar JavaFX si es necesario
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {
                // No es necesario hacer nada aquí
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JList<String> DocList;
    public javax.swing.JList<String> MusicList;
    public javax.swing.JList<String> VideoList;
    public javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
