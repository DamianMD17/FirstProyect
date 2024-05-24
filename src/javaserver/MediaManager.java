package javaserver;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MediaManager {

    // Ruta de la carpeta de medios
    private static final String MEDIA_FOLDER_PATH = "media";

    // Función para obtener la lista de imágenes en la carpeta de medios
    public static List<File> getImages() {
        List<File> images = new ArrayList<>();
        File mediaFolder  = new File(MEDIA_FOLDER_PATH);
        File subFolder = new File(mediaFolder, "imagenes");
        if (subFolder.exists() && subFolder.isDirectory()) {
            File[] files = subFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && isImage(file.getName())) {
                        images.add(file);
                    }
                }
            }
        }
        return images;
    }

    public static List<File> getMusic() {
        List<File> musicFiles = new ArrayList<>();
        File musicFolder = new File(MEDIA_FOLDER_PATH); // Carpeta principal de medios
        File subFolder = new File(musicFolder, "musica");
        // Verificar si la carpeta de música existe y es un directorio
        if (subFolder.exists() && subFolder.isDirectory()) {
            // Obtener la lista de archivos en la carpeta de música
            File[] files = subFolder.listFiles();
            if (files != null) {
                // Iterar sobre los archivos para filtrar los archivos de música
                for (File file : files) {
                    if (file.isFile() && isMusic(file.getName())) {
                        musicFiles.add(file);
                    }
                }
            }
        }
        return musicFiles;
    }

    // Función para obtener la lista de videos en la carpeta de medios
    public static List<File> getVideos() {
        List<File> videos = new ArrayList<>();
        File Videosfolder = new File(MEDIA_FOLDER_PATH);
        File subFolder = new File(Videosfolder, "videos");
        if (subFolder.exists() && subFolder.isDirectory()) {
            File[] files = subFolder.listFiles();
            System.out.println("archivos: "+ files);
            if (files != null) {
                for (File file : files) {
                    System.out.println("Video encontrado: "+ file.getName());
                    if (file.isFile() && isVideo(file.getName())) {
                        System.out.println("Video añadido: "+ file.getName());
                        videos.add(file);
                    }
                }
            }
        }
        return videos;
    }
    
    
        public static List<File> getVideosAdmin() {
        List<File> videos = new ArrayList<>();
        File Videosfolder = new File(MEDIA_FOLDER_PATH);
        File subFolder = new File(Videosfolder, "videosAdmin");
        if (subFolder.exists() && subFolder.isDirectory()) {
            File[] files = subFolder.listFiles();
            System.out.println("archivos: "+ files);
            if (files != null) {
                for (File file : files) {
                    System.out.println("Video encontrado: "+ file.getName());
                    if (file.isFile() && isVideo(file.getName())) {
                        System.out.println("Video añadido: "+ file.getName());
                        videos.add(file);
                    }
                }
            }
        }
        return videos;
    }

    // Función para obtener la lista de archivos en la carpeta de medios
    public static List<File> getFiles() {
        List<File> files = new ArrayList<>();
        File Filesfolder = new File(MEDIA_FOLDER_PATH);
        File subFolder = new File(Filesfolder, "documentos");
        
        if (subFolder.exists() && subFolder.isDirectory()) {
            File[] allFiles = subFolder.listFiles();
            if (allFiles != null) {
                for (File file : allFiles) {
                    if (file.isFile()) {
                        files.add(file);
                    }
                }
            }
        }
        return files;
    }

    // Función auxiliar para verificar si un archivo es una imagen
    private static boolean isImage(String fileName) {
        String[] imageExtensions = {".jpg", ".jpeg", ".png", ".gif"};
        for (String ext : imageExtensions) {
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMusic(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        // Aquí puedes agregar o modificar las extensiones de archivo de música que deseas admitir
        return extension.equalsIgnoreCase("mp3") || extension.equalsIgnoreCase("wav")
                || extension.equalsIgnoreCase("flac");
    }

    // Función auxiliar para verificar si un archivo es un video
    private static boolean isVideo(String fileName) {
        String[] videoExtensions = {".mp4", ".avi", ".mkv", ".mov", ".webm"};
        for (String ext : videoExtensions) {
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}
