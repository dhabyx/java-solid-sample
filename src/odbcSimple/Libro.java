/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package odbcSimple;

import java.util.List;

/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class Libro {
    protected String isbn;
    protected String nombre;
    protected String categoria;

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
    
    public Libro() {
        this.isbn=null;
        this.nombre=null;
        this.categoria=null;
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
     * Busca todas las categorías distintas y devuelve un Resultset, (no es la
     * manera mas óptima de hacerlo)
     *
     * @return ResultSet
     */
    public static List<String> buscarTodasLasCategorias() {
        String consultaSQL = "select distinct(categoria) as categoria from Libros";
        DataBaseHelper<String> helper = new DataBaseHelper<>();
        List<String> listaDeCategorias = helper.seleccionarRegistros(consultaSQL, String.class);
        return listaDeCategorias;
    }

     
    /**
     * Devuelve un ResultSet con todas las filas
     * @return Resultset
     */
    public static List<Libro> buscarTodos() {
        String consultaSQL = "select isbn,nombre,categoria from Libro";
        DataBaseHelper<Libro> helper = new DataBaseHelper<>();
        List<Libro> listaDeLibros= helper.seleccionarRegistros(consultaSQL, Libro.class);
        return listaDeLibros;
    }
}
