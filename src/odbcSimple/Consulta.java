/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package odbcSimple;

import java.sql.SQLException;
import java.sql.ResultSet;

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
        Libro.insertar("asdf123", "Titulo1", "Historia");
    }

    /**
     * Busca información de la tabla Libro
     */
    public void buscar() {
        ResultSet rs = null;
        rs = Libro.buscarTodos();
        try {

            while (rs.next()) {
                System.out.println(rs.getString("isbn"));
                System.out.println(rs.getString("nombre"));
                System.out.println(rs.getString("categoria"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer el ResultSet: "+ex.getMessage());
        }

    }
}
