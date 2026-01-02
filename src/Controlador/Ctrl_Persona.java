
package Controlador;

import Conexion.Conexion;
import Modelo.Persona;
import java.sql.*;

/**
 *
 * @author alvar
 */
public class Ctrl_Persona {
    public static String IdPersonaActual;
    
    
    public boolean Guardar(Persona Objeto) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_persona VALUES(?,?,?,?)");
            consulta.setString(1, Objeto.getIdPersona());
            consulta.setString(2, Objeto.getNombre());
            consulta.setString(3, Objeto.getApellidos());
            consulta.setString(4, Objeto.getNumero());

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar persona: " + e);
        }
        return Respuesta;
    }
    
    
    public void PersonaActual(Persona Objeto) {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pat = cn.prepareStatement(
                    "SELECT IdPersona FROM tb_persona WHERE Nombre = ?");
            pat.setString(1, Objeto.getNombre());
            ResultSet rs = pat.executeQuery();

            if (rs.next()) {
                IdPersonaActual = rs.getString("IdPersona");
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error de busqueda de Usuario: " + e);
        }
        System.out.println(IdPersonaActual);
    }
}
