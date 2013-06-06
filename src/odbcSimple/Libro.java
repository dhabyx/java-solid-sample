/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package odbcSimple;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class Libro {
    private String isbn;
    private String nombre;
    private String categoria;

    /**
     *
     * @return
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     *
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getCategoria() {
        return this.categoria;
    }

    /**
     *
     * @param categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Constructor de la clase Libro
     * @param isbn
     * @param nombre
     * @param categoria
     */
    public Libro(String isbn, String nombre, String categoria) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    /**
     *  Busca todas las categorías distintas y devuelve un Resultset, 
     * (no es la manera mas óptima de hacerlo)
     * @return ResultSet 
     */
    public static ResultSet buscarTodasLasCategorias() {
        String consultaSQL = "select distinct(categoria) from Libro";
        DataBaseHelper helper = new DataBaseHelper();
        ResultSet rs = helper.seleccionarRegistros(consultaSQL);
        return rs;
    }

    /**
     * Inserta una tupla en la tabla Libros
     */
    public void insertar() {
        String consultaSQL = "insert into Libro (isbn,nombre,categoria) values ";
        consultaSQL += "('" + this.isbn + "','" + this.nombre + "','" + this.categoria + "')";
        DataBaseHelper helper = new DataBaseHelper();
        helper.modificarRegistro(consultaSQL);
    }

    /**
     * Devuelve un ResultSet con todas las filas
     * @return Resultset
     */
    public static ArrayList<Libro> buscarTodos() {
        String consultaSQL = "select isbn,nombre,categoria from Libro";
        DataBaseHelper helper = new DataBaseHelper();
        ResultSet rs = helper.seleccionarRegistros(consultaSQL);
        ArrayList<Libro> listaDeLibros= new ArrayList<>();
        try {

            while (rs.next()) {
                listaDeLibros.add(new Libro(
                        rs.getString("isbn"),
                        rs.getString("nombre"),
                        rs.getString("categoria")
                        ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer el ResultSet: "+ex.getMessage());
        }
        return listaDeLibros;
    }
    
}
