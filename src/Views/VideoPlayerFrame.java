package Views;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;


public class VideoPlayerFrame extends javax.swing.JFrame {

    private MediaPlayer mediaPlayer;
    private final String selectedVideo;

    private final JFXPanel jfxpanel = new JFXPanel();

    public VideoPlayerFrame(String selectedVideo) {
        this.selectedVideo = selectedVideo;

        initComponents();

        //createScene();
        setTitle("Reproduciendo " + selectedVideo);
        File file = new File("download/" + selectedVideo);

        if (file != null) {
            String relativePath = file.toURI().toString();
            String abosolutePath = relativePath.substring(6);
            System.out.println("direccion del archivo: "+abosolutePath);// Ruta relativa al archivo de video en tu proyecto
           
            playVideoWithWindowsMediaPlayer(abosolutePath);
        }
        
        setResizable(false);
        setLocationRelativeTo(null);
        this.setLocationRelativeTo(null);

        setupComponents(selectedVideo);
        initializeMediaPlayer(selectedVideo);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                stopVideo();
                dispose();
            }
        });
    }


    public static void playVideoWithWindowsMediaPlayer(String videoFilePath) {
        System.out.println(videoFilePath);
        try {
            File videoFile = new File(videoFilePath);
            if (!videoFile.exists()) {
                System.out.println("El archivo de video no existe: " + videoFilePath);
                return;
            }

            // Construir el comando para abrir el reproductor de video de Windows
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start", "wmplayer", videoFilePath);
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeMediaPlayer(String videoFilePath) {

    }

    private void setupComponents(String selectedSong) {
        Title.setText(selectedSong);
    }

    private void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 255));

        Title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(Title)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    // End of variables declaration//GEN-END:variables

}
