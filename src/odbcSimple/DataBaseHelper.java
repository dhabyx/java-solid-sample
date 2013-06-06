/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package odbcSimple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class DataBaseHelper<T> {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/libros";
    private static final String USUARIO = "libros";
    private static final String CLAVE = "libros123";

    public int modificarRegistro(String consultaSQL) {
        Connection conexion = null;
        Statement sentencia = null;
        int filasAfectadas = 0;
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL,
                    USUARIO, CLAVE);
            sentencia = conexion.createStatement();
            filasAfectadas = sentencia.executeUpdate(consultaSQL);
        } catch (ClassNotFoundException e) {
            System.out.println("Error driver" + e.getMessage());

        } catch (SQLException e) {

            System.out.println("Error de SQL" + e.getMessage());
        } finally {
            if (sentencia != null) {
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
            return filasAfectadas;
        }
    }
    
    public List<T> seleccionarRegistros(String consultaSQL, Class clase) {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet filas = null;
        List<T> listaDeObjetos = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL,
                    USUARIO, CLAVE);
            sentencia = conexion.createStatement();
            filas = sentencia.executeQuery(consultaSQL);
            while (filas.next()) {
                Class c = Class.forName("odbcSimple.Libro");
                T objeto = (T) c.newInstance();
                Method[] metodos = objeto.getClass().getDeclaredMethods();
                for (int i = 0; i < metodos.length; i++) {
                    if (metodos[i].getName().startsWith("set")) {
                        metodos[i].invoke(objeto,
                                filas.getString(metodos[i].getName().substring(3)));
                        System.out.println(metodos[i].getName());
                    }
                    if (objeto.getClass().getName().equals("java.lang.String")) {
                        objeto = (T) filas.getString(1);
                    }
                }
                listaDeObjetos.add(objeto);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error de clase no encontrada: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de SQL " + e.getMessage());
        } catch (InstantiationException ex) {
            System.out.println("Error al instanciar la clase: "+ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.out.println("Error de acceso a la clase: "+ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Error, argumento no encontrado: "+ex.getMessage());
        } catch (InvocationTargetException ex) {
            System.out.println("Eroor de invocación de método: "+ex.getMessage());
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                }
            }
        }

        return listaDeObjetos;
    }

}
