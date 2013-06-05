/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package odbcSimple;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 * clase de prueba para consultas
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class Consulta {
    
    Connection conexion = null;
    Statement sentencia = null;
    
    /**
     * Ejecuta un sql de prueba
     * @param String isbn
     * @param String nombre
     * @param String categoria
     */
    public void insertar(String isbn, String nombre, String categoria){
        int numFilas = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/libros",
                    "libros",
                    "libros123");
            sentencia = conexion.createStatement();
            String consultaSQL = "insert into Libro(isbn,nombre,categoria) values";
            consultaSQL += "('"+isbn+"', '"+nombre+"', '"+categoria+"')";
            numFilas = sentencia.executeUpdate(consultaSQL);
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error en la carga del driver:"
                    +e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error accediendo a la Base de Datos: "
                    +e.getMessage());
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    System.out.println("Error cerrando la sentencia"
                            +e.getMessage());
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println("Error cerrando la conexión"
                            +e.getMessage());
                }
            }
                
        }
        
    }
    
    /**
     * Busca información de la tabla Libro
     */
    public void buscar() {
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/libros",
                    "libros",
                    "libros123");
            sentencia = conexion.createStatement();
            String consultaSQL = "select isbn, nombre, categoria from Libro";
            rs=sentencia.executeQuery(consultaSQL);
            while (rs.next()){
                System.out.println(rs.getString("isbn"));
                System.out.println(rs.getString("nombre"));
                System.out.println(rs.getString("categoria"));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error en la carga del driver:"
                    +e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error accediendo a la Base de Datos: "
                    +e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Error cerrando el resultset"
                            +e.getMessage());
                }
            }
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    System.out.println("Error cerrando la sentencia"
                            +e.getMessage());
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println("Error cerrando la conexión"
                            +e.getMessage());
                }
            }
        }
    }
}
