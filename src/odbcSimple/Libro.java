/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package odbcSimple;

import java.sql.ResultSet;

/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class Libro {
    /**
     *  Busca todas las categorías distintas y devuelve un Resultset, 
     * (no es la manera mas óptima de hacerlo)
     * @return ResultSet 
     */
    public static ResultSet buscarTodasLasCategorias() {
        String consultaSQL = "select distinct(categoria) from Libros";
        DataBaseHelper helper = new DataBaseHelper();
        ResultSet rs = helper.seleccionarRegistros(consultaSQL);
        return rs;
    }

    /**
     * Inserta una tupla en la tabla Libros
     * @param String isbn
     * @param String titulo
     * @param String categoria
     */
    public static void insertar(String isbn, String titulo, String categoria) {
        String consultaSQL = "insert into Libros (isbn,nombre,categoria) values ";
        consultaSQL += "('" + isbn + "','" + titulo + "','" + categoria + "')";
        DataBaseHelper helper = new DataBaseHelper();
        helper.modificarRegistro(consultaSQL);
    }

    /**
     * Devuelve un ResultSet con todas las filas
     * @return Resultset
     */
    public static ResultSet buscarTodos() {
        String consultaSQL = "select isbn,nombre,categoria from Libros";
        DataBaseHelper helper = new DataBaseHelper();
        ResultSet rs = helper.seleccionarRegistros(consultaSQL);
        return rs;
    }
    
}
