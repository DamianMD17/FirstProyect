package javaserver;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class FileUserAuthentication {
    private static final String FILE_PATH = "users.txt";
    
    // Genera una sal (salt) aleatoria
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    // Hashea la contraseña junto con la sal utilizando el algoritmo SHA-256
    private String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    // Verifica si la contraseña ingresada coincide con la contraseña almacenada
    private boolean verifyPassword(String password, String hashedPassword, byte[] salt) throws NoSuchAlgorithmException {
        String hashedAttempt = hashPassword(password, salt);
        System.out.println(hashedAttempt.equals(hashedPassword));
        return hashedAttempt.equals(hashedPassword);
    }

    // Método para autenticar un usuario
    public String authenticateUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Divide la línea en usuario, contraseña hasheada y sal usando un delimitador (por ejemplo, coma)
                String[] parts = line.split(",");
                System.out.println("Linea: "+line);
                String storedUsername = parts[0];
                String hashedPassword = parts[1];
                byte[] salt = decodeSalt(parts[2]);
                String rol = parts[3];

                // Verifica si el usuario y la contraseña coinciden
                if (username.equals(storedUsername) && verifyPassword(password, hashedPassword, salt)) {
                    return "true-" + rol; // Autenticación exitosa
                }
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("Error al leer el archivo de usuarios: " + e.getMessage());
        }
        return "false"; // Autenticación fallida
    }

    // Método para registrar un nuevo usuario
    public void registerUser(String username, String password, String rol) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Genera una sal aleatoria
            byte[] salt = generateSalt();
            // Hashea la contraseña junto con la sal
            String hashedPassword = hashPassword(password, salt);
            // Codifica la sal en una cadena Base64
            String encodedSalt = encodeSalt(salt);
            // Escribe el nuevo usuario en el archivo
            writer.write(username + "," + hashedPassword + "," + encodedSalt + "," + rol);
            writer.newLine();
            System.out.println("Usuario registrado con éxito.");
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("Error al escribir en el archivo de usuarios: " + e.getMessage());
        }
    }

    // Método para codificar la sal en una cadena Base64
    private String encodeSalt(byte[] salt) {
        return Base64.getEncoder().encodeToString(salt);
    }

    // Método para decodificar la sal desde una cadena Base64
    private byte[] decodeSalt(String encodedSalt) {
        return Base64.getDecoder().decode(encodedSalt);
    }
}