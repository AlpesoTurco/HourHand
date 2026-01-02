package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author alvar
 */
public class Conexion {

    public static Connection conectar() {

        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/Horario_DB", "root", "12345678");
            return cn;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion local " + e);
            System.out.println("Error en la conexion local " + e);
        }
        return null;
    }
}
