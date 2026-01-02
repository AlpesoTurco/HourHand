package Controlador;

import Conexion.Conexion;
import static Controlador.Ctrl_Usuario.IdUsuarioActual;
import Modelo.Maestro;
import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author alvar
 */
public class Ctrl_Maestro {

    public boolean GuardarMaestro(Maestro Objeto) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement Consulta = cn.prepareStatement("INSERT INTO tb_maestros VALUES(?,?,?,?,?);");
            Consulta.setInt(1, 0);
            Consulta.setString(2, Objeto.getNombre());
            Consulta.setString(3, Objeto.getDescripcion());
            Consulta.setString(4, Objeto.getUsuarioM());
            Consulta.setInt(5, Objeto.getEstado());

            if (Consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e);
            System.out.println("Error al guardar: " + e);
        }
        return Respuesta;
    }
    //Checar si ya tengo ese profe registrado
    public boolean ExisteMaestro(String Maestro) {
        boolean Respuesta = false;
        String sql = "SELECT Nombre FROM tb_maestros WHERE Nombre = '" + Maestro + "' AND Usuario = '" + IdUsuarioActual + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar maestro: " + e);
        }
        return Respuesta;
    }

    //Metodo Actualizar 
    public boolean ActualizarMaestro(Maestro Objeto, int IdMaestro) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("UPDATE tb_maestros SET Descripcion = ? WHERE IdMaestro = '" + IdMaestro + "'");
            consulta.setString(1, Objeto.getDescripcion());

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar Maestro: " + e);
        }
        return Respuesta;
    }

    //Eliminar
    public boolean EliminarMaestro(int IdMaestro) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("DELETE FROM tb_maestros WHERE IdMaestro = '" + IdMaestro + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar maestro: " + e);
        }
        return Respuesta;
    }
}

