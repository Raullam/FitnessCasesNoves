package spdvi.fitnesscasesnoves.gui;

import DataAcces.DataAccess;
import dtos.Usuari;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication333.TerceraVentana;
import javax.swing.JOptionPane;

/**
 *
 * @author Rulox
 */
public class RegistreUsuaris extends javax.swing.JFrame {
    
    // Variables para almacenar los valores de jTextPane1 y jPasswordField1
private String username; // Para almacenar el valor de jTextPane1
private String password; // Para almacenar el valor de jPasswordField1

   
    public RegistreUsuaris() {
        initComponents();
        this.setLocationRelativeTo(null);// esto es para que se centre en la pantalla

        
        // Añadir FocusListener a jTextPane1 para capturar el valor cuando se pierda el foco
    jTextPane1.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            // Guardar el valor en la variable username cuando se pierda el foco
            username = jTextPane1.getText();
        
            
        }
    });
    
    // Añadir FocusListener a jPasswordField1 para capturar el valor cuando se pierda el foco
    jPasswordField1.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            // Guardar el valor en la variable password cuando se pierda el foco
            password = new String(jPasswordField1.getPassword()); // Convierte a String
            
            }
    });

    // Acción para el botón jButton1
   // jButton1.addActionListener(e -> validarUsuario());
}

// Método que valida el usuario contra la base de datos
private void validarUsuario() {
    
    // Llamar a la base de datos para verificar si el usuario existe
    DataAccess da = new DataAccess();
    Usuari user = da.getUsuarioByUsername(username); // Método que consulta el usuario por nombre
    // Verifica si el campo username está vacío
    if (username == null || username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo de usuario no puede estar vacío.");
        // Mostrar un modal con el nuevo valor
      
        return;
    }

    

    System.out.println("Usuario existe.");

    if (user != null) {
        // Comparar la contraseña ingresada con la contraseña de la base de datos
        if (user.getPassword().equals(password)) {
            // Si coinciden, mostrar el mensaje y pasar al siguiente JFrame
            JOptionPane.showMessageDialog(this, "Acceso concedido. Puedes pasar al siguiente JFrame." + username);
            
            // si todo bien entra en el jframe LOKEANDO2
            
            TerceraVentana terceraVentana = new TerceraVentana(); //instancia de la siguiente clase 'ventana'
            terceraVentana.setVisible(true);
            this.setVisible(false);
           // NewJFrame newJF = new NewJFrame();
            // newJF();
            // Aquí puedes abrir el nuevo JFrame
            // new SiguienteJFrame().setVisible(true);
            // this.dispose(); // Opcional: cierra el JFrame actual
        } else {
            // Si la contraseña no coincide, mostrar mensaje de error
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta.");
        }
    } else {
        // Si el usuario no existe, mostrar mensaje de error
        JOptionPane.showMessageDialog(this, "El usuario no existe.");
    }
    }
    public Usuari getUsuarioByUsername(String username) {
    String sql = "SELECT * FROM usuaris WHERE Nom = ?";
    Usuari user = null;

    try (Connection connection = getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        
        stmt.setString(1, username); // Asignar el nombre de usuario al query
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            // Crear el objeto Usuario con los datos recuperados de la base de datos
            user = new Usuari(
                resultSet.getInt("Id"),
                resultSet.getString("Nom"),
                resultSet.getString("Email"),
                resultSet.getString("PasswordHash"),
                resultSet.getBytes("Foto"),
                resultSet.getBoolean("IsInstructor")
            );
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
    }

    return user;
}
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Entrar a la app");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTextPane1);

        jPasswordField1.setText("jPasswordField1");
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuari");

        jLabel2.setText("Contrasenya");

        jLabel3.setText("Registre d'usuaris");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addGap(68, 68, 68))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(113, 113, 113)))))
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        validarUsuario();
    }//GEN-LAST:event_jButton1ActionPerformed
         /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistreUsuaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistreUsuaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistreUsuaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistreUsuaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistreUsuaris().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

    private Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}