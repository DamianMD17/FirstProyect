package javaserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUserAuthenticationNoEncrypt {

    private static final String FILE_PATH = "users.txt";

    // Método para verificar la contraseña
    private String verifyPassword(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Linea: " + line);
                String[] parts = line.split(",");
                System.out.println(parts);
                String storedUsername = parts[0];
                String storedPassword = parts[1];
                String rol = parts[2];

                // Verifica si el usuario y la contraseña coinciden
                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    return "true-" + rol; // Autenticación exitosa
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de usuarios: " + e.getMessage());
        }
        return "false,"; // Autenticación fallida
    }

    // Método para autenticar un usuario
    public String authenticateUser(String username, String password) {
        return verifyPassword(username, password);
    }

    // Método para registrar un nuevo usuario
    public void registerUser(String username, String password, String rol) {
        System.out.println(username + " " + password);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Escribe el nuevo usuario en el archivo
            writer.write(username + "," + password + "," + rol);
            writer.newLine();
            System.out.println("Usuario registrado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de usuarios: " + e.getMessage());
        }
    }

}
