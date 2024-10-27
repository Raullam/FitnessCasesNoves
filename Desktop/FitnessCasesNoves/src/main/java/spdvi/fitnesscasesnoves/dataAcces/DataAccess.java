/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.fitnesscasesnoves.dataAcces;

import spdvi.fitnesscasesnoves.dto.Usuari;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleja
 */
// DAO Data access object
public class DataAccess {
    private Connection getConnection() {
        Connection connection = null;
        // acer otro metodo para modificar
        // Cadena de conexión corregida
        String connectionString = "jdbc:sqlserver://localhost;database=simulapdb;trustServerCertificate=true;user=sa;password=1234;";
        //String connectionString2 = "jdbc:sqlserver://localhost;database=simulapdb;user=user;password=1234;";
        
        //despues de hacer focus que se haga el update
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    public ArrayList<Usuari> getUsuaris() {
        ArrayList<Usuari> usuaris = new ArrayList<>(); // mos cream s'objecte
        String sql = "select * from usuaris"; // feim sa query
        
        Connection connection = getConnection(); // mos connectam
        try {
            PreparedStatement selectStatement = connection.prepareStatement(sql); // preparam la query y la enviamos al servidor
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
        Usuari user = new Usuari(
        resultSet.getInt("Id"),
        resultSet.getString("Nom"),
        resultSet.getString("Email"),
        resultSet.getString("PasswordHash"),
        //resultSet.getBytes("Foto"),
        resultSet.getBoolean("IsInstructor")
        );
    usuaris.add(user);
}
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuaris;
        
    }
    
    // intents 
public ArrayList<String> getIntentosByUserId(int idUsuario) {
    ArrayList<String> descripciones = new ArrayList<>(); // Lista para almacenar las descripciones de los ejercicios
    String sql = "SELECT e.Descripcio " +
             "FROM Intents i " +
             "JOIN Exercicis e ON i.IdExercici = e.Id " +
             "LEFT JOIN Review r ON i.Id = r.IdIntent " +
             "WHERE i.IdUsuari = ? AND r.Valoracio IS NULL";
 // Consulta para obtener las descripciones de ejercicios por ID de usuario

    Connection connection = getConnection(); // Obtener la conexión a la base de datos
    try {
        PreparedStatement selectStatement = connection.prepareStatement(sql); // Preparar la consulta
        selectStatement.setInt(1, idUsuario); // Establecer el valor del parámetro idUsuario
        ResultSet resultSet = selectStatement.executeQuery(); // Ejecutar la consulta

        // Iterar sobre los resultados y agregar las descripciones de los ejercicios a la lista
        while (resultSet.next()) {
            descripciones.add(resultSet.getString("Descripcio")); // Obtener la descripción del ejercicio
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (connection != null) connection.close(); // Cerrar la conexión después de usarla
        } catch (SQLException e) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    return descripciones; // Retornar la lista de descripciones de ejercicios
}

 




    public boolean updateUsuaris(Usuari user) {
    String sql = "UPDATE usuaris SET Nom = ?, Email = ?, PasswordHash = ?, Foto = ?, IsInstructor = ? WHERE Id = ?";
    boolean updated = false; // Variable para controlar si se actualizó

    try (Connection connection = getConnection(); 
         PreparedStatement updateStatement = connection.prepareStatement(sql)) {

        // Asignar valores a los parámetros del PreparedStatement
        updateStatement.setString(1, user.getNom());
        updateStatement.setString(2, user.getEmail());
        updateStatement.setString(3, user.getPasswordHash()); // Suponiendo que tienes un método getPassword en Usuari
        updateStatement.setBytes(4, user.getFoto());
        updateStatement.setBoolean(5, user.isInstructor()); // Usar el nuevo método isInstructor
        updateStatement.setInt(6, user.getId());

        // Ejecutar la actualización
        int rowsAffected = updateStatement.executeUpdate();
        if (rowsAffected > 0) { 
            updated = true; // La actualización fue exitosa
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
    }

    return updated; // Retornar el estado de la actualización
}

    public Usuari getUsuarioByUsername(String username) {
    Usuari user = null; // Inicializa el objeto Usuari a null
    String sql = "SELECT * FROM usuaris WHERE Nom = ?"; // Query para buscar el usuario por nombre

    try (Connection connection = getConnection(); 
         PreparedStatement selectStatement = connection.prepareStatement(sql)) {
        
        // Establecer el parámetro en la consulta
        selectStatement.setString(1, username);
        
        // Ejecutar la consulta
        ResultSet resultSet = selectStatement.executeQuery();
        
        // Si el usuario existe, obtener los datos y crear un objeto Usuari
        if (resultSet.next()) {
            user = new Usuari(
                resultSet.getInt("Id"),
                resultSet.getString("Nom"),
                resultSet.getString("Email"),
                resultSet.getString("PasswordHash"),
                //resultSet.getBytes("Foto"),
                resultSet.getBoolean("IsInstructor")
            );
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    // Retornar el objeto Usuari si se encontró, o null si no
    return user;
}
    
    public int registerUser(Usuari u) {
        Connection connection = getConnection();
        String sql = "INSERT INTO Usuaris(Nom, Email, PasswordHash, IsInstructor) "
                + " VALUES (?, ?, ?, ?);";
        
        try {
            PreparedStatement insertStatement = connection.prepareStatement(sql);
            insertStatement.setString(1,u.getNom());
            insertStatement.setString(2,u.getEmail());
            insertStatement.setString(3,u.getPasswordHash());
            insertStatement.setBoolean(4, u.isInstructor()); 
            insertStatement.executeUpdate();
            connection.close();
            
            int newUserId = getLastId();
            return newUserId;
           
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    public int getLastId() {
    int lastId = 0; // Inicializa el ID a 0 (o a otro valor que consideres adecuado)

    // Define la consulta SQL para obtener el máximo ID
    String sql = "SELECT MAX(id) AS maxId FROM Usuaris"; // Ajusta el nombre del campo si es necesario

    // Manejo de la conexión y los recursos
    try (Connection connection = getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        // Verifica si hay un resultado
        if (resultSet.next()) {
            lastId = resultSet.getInt("maxId"); // Obtiene el máximo ID
        }
    } catch (SQLException ex) {
        Logger.getLogger(Usuari.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return lastId; // Retorna el último ID encontrado
}

    
}

// hacer un listener de la celula y añadirle el evento
