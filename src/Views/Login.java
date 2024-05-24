package Views;

import javaserver.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.net.*;

public class Login extends javax.swing.JFrame {

    public static Socket sharedSocket;
    public static boolean sharedAuth;
    public static boolean shareRol;

    private static final String SERVER_ADDRESS = "10.235.63.54"; // Cambia esto con la dirección IP de tu servidor
    private static final int SERVER_PORT = 8081; // Cambia esto con el puerto en el que tu servidor está escuchando

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        NewUserName = new javax.swing.JTextField();
        NewPassword = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        RegisterButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        UserRol = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        UserName = new javax.swing.JTextField();
        Password = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenido");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Por favor registrate o inicia sesión");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 244));

        NewUserName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NewUserName.setToolTipText("User");
        NewUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewUserNameActionPerformed(evt);
            }
        });

        NewPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NewPassword.setToolTipText("Contraseña");
        NewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewPasswordActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Contraseña");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Usuario");

        RegisterButton.setText("Registrarse");
        RegisterButton.setPreferredSize(new java.awt.Dimension(125, 24));
        RegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterButtonActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Registro");

        UserRol.setText("Usuario Admin");
        UserRol.setPreferredSize(new java.awt.Dimension(125, 24));
        UserRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserRolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 45, Short.MAX_VALUE)
                .addComponent(RegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UserRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(NewUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(NewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addGap(41, 41, 41)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NewUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 244));

        UserName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UserName.setToolTipText("User");
        UserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserNameActionPerformed(evt);
            }
        });

        Password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Password.setToolTipText("Contraseña");
        Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordActionPerformed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Contraseña");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Usuario");

        LoginButton.setText("Iniciar Sesión");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Inicio de sesión");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(LoginButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 75, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LoginButton)
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                .addGap(0, 33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UserRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserRolActionPerformed
        if (shareRol) {
            shareRol = false;
        } else {
            shareRol = true;
        }
        
        System.out.println("Rol admin: " + shareRol);
    }//GEN-LAST:event_UserRolActionPerformed

    private void RegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String username = NewUserName.getText(); // Obtiene el nombre de usuario del campo de texto
        String password = NewPassword.getText(); // Obtiene la contraseña del campo de texto

        // Verifica si el usuario o la contraseña están vacíos
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre de usuario y una contraseña");
        } else {
            try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT); PrintWriter out = new PrintWriter(socket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); OutputStream binaryOut = socket.getOutputStream(); InputStream binaryIn = socket.getInputStream()) {                // Crear instancia de ClientCommunication para manejar la comunicación con el servidor
                ClientCommunication clientCommunication = new ClientCommunication(socket, out, in, binaryOut, binaryIn);

                // Envía el nombre de usuario, la contraseña y el indicador de autenticación compartida al servidor
                clientCommunication.sendMessage("1," + username + "," + password +",-"+shareRol+ "-" + Login.sharedAuth);

                // Recibir respuesta del servidor
                String response = clientCommunication.receiveMessage();

                // Si la autenticación fue exitosa, procede a abrir la ventana del cliente
                if (response.startsWith("auth exitoso")) {
                    boolean isAuthenticated = Boolean.parseBoolean(response.substring(13));
                    if (isAuthenticated) {
                        // Establecer la autenticación compartida a verdadera
                        Login.sharedSocket = socket;
                        Login.sharedAuth = true;
                        // Crear la instancia de la ventana del cliente
                        Client client = new Client(username, clientCommunication);
                        // Mostrar la ventana del cliente
                        client.setVisible(true);
                        // Cerrar la ventana de registro
                        this.dispose();
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al conectar con el servidor: " + e.getMessage());
                JOptionPane.showMessageDialog(this, "Error al conectar con el servidor");
            }
        }

        // Limpiar los campos de texto después del registro
        NewUserName.setText("");
        NewPassword.setText("");
    }

    private void NewPasswordActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_NewPasswordActionPerformed
    }// GEN-LAST:event_NewPasswordActionPerformed

    private void NewUserNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_NewUserNameActionPerformed
    }// GEN-LAST:event_NewUserNameActionPerformed

    private void UserNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_UserNameActionPerformed
    }// GEN-LAST:event_UserNameActionPerformed

    private void PasswordActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_PasswordActionPerformed
    }// GEN-LAST:event_PasswordActionPerformed

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String username = UserName.getText();
        String password = Password.getText();

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT); PrintWriter out = new PrintWriter(socket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); OutputStream binaryOut = socket.getOutputStream(); InputStream binaryIn = socket.getInputStream()) {
            System.out.println("Dirección del socket en Login: " + socket.getLocalAddress() + ":" + socket.getLocalPort());

            // Crear instancia de ClientCommunication para manejar la comunicación con el servidor
            ClientCommunication clientCommunication = new ClientCommunication(socket, out, in, binaryOut, binaryIn);

            // Enviar mensaje al servidor para autenticación
            clientCommunication.sendMessage("0," + username + "," + password +",-"+shareRol+ "-" + Login.sharedAuth);

            // Recibir respuesta del servidor
            String response = clientCommunication.receiveMessage();
            String finalResponse = response.substring(13);
            System.out.println("Respuesta: "+response);
            if (response.startsWith("auth exitoso")) {
                String[] Userparts = finalResponse.split("-");
                boolean isAuthenticated = Boolean.parseBoolean(Userparts[0]);
                shareRol = Boolean.parseBoolean(Userparts[1]);
                if (isAuthenticated) {
                    Login.sharedSocket = socket;
                    Login.sharedAuth = true;
                    Client client = new Client(username, clientCommunication);
                    // Pasar la instancia de ClientCommunication al constructor de Client
                    client.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Usuario o contraseña incorrectos");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error: Fallo al autenticar con el servidor");
            }

        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor");
        }
    }

    public static void main(String args[]) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println("Dirección IP del servidor: " + inetAddress.getHostAddress());
        } catch (UnknownHostException e) {
            System.err.println("No se pudo obtener la dirección IP del servidor: " + e.getMessage());
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);

            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginButton;
    private javax.swing.JTextField NewPassword;
    private javax.swing.JTextField NewUserName;
    private javax.swing.JTextField Password;
    private javax.swing.JButton RegisterButton;
    private javax.swing.JTextField UserName;
    private javax.swing.JRadioButton UserRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
