/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package odbcSimple;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        Libro libro = new Libro("asdf1234a","Titulo2","Ciencias");
        libro.insertar();
    }

    /**
     * Busca información de la tabla Libro
     */
    public void buscar() {        
        List<Libro> listaDeLibros = null;
        listaDeLibros = Libro.buscarTodos();
        for (Libro libro:listaDeLibros) {
            System.out.println(libro.getIsbn());
            System.out.println(libro.getNombre());
            System.out.println(libro.getCategoria());
            System.out.println("----");
        }
    }
}
