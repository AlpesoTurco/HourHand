package Controlador;

import Conexion.Conexion;
import Conexion.Conexion;
import static Controlador.Ctrl_tareas.IdTareaSeleccionada;
import Modelo.TareaUsuario;
import Vista.CompartirTarea;
import java.sql.*;

/**
 *
 * @author alvar
 */
public class Ctrl_Compartir {
    
    public boolean Guardar(TareaUsuario Objeto) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_usuariotarea VALUES (?,?,?,?,?);");
            consulta.setInt(1, 0);
            consulta.setString(2, Objeto.getUsuario());
            consulta.setInt(3, Objeto.getTarea());
            consulta.setInt(4, Objeto.getEstado());
            consulta.setString(5, Objeto.getUsuarioACompartir());

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }
            
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al compartir: " + e);
        }
        return Respuesta;
    }
}
