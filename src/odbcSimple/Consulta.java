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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * clase de prueba para consultas
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class Consulta {
    
    /**
     * Ejecuta un sql de prueba
     * @param String isbn
     * @param String nombre
     * @param String categoria
     */
    public void insertar(String isbn, String nombre, String categoria) {
        int numFilas = 0;
        DataBaseHelper helper = new DataBaseHelper();
        String consultaSQL = "insert into Libro(isbn,nombre,categoria) values";
        consultaSQL += "('" + isbn + "', '" + nombre + "', '" + categoria + "')";
        numFilas = helper.modificarRegistro(consultaSQL);
    }

    /**
     * Busca información de la tabla Libro
     */
    public void buscar() {
        ResultSet rs = null;
        DataBaseHelper helper = new DataBaseHelper();
        String consultaSQL = "select isbn, nombre, categoria from Libro";
        rs = helper.seleccionarRegistros(consultaSQL);
        try {

            while (rs.next()) {
                System.out.println(rs.getString("isbn"));
                System.out.println(rs.getString("nombre"));
                System.out.println(rs.getString("categoria"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
