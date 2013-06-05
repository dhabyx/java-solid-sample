/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package patterndesignsample;
import odbcSimple.Consulta;

/**
 *
 * @author dhabyx
 */
public class PatternDesignSample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Consulta consulta = new Consulta();
        consulta.insertar("1232", "nombre2", "ficción");
        consulta.buscar();
    }
}
