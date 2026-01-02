
package Controlador;

import Conexion.Conexion;
import Modelo.TareaUsuario;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author alvar
 */
public class Ctrl_TareaUsuario {
    
    
//    public boolean EliminarTareaCompartida(TareaUsuario Objeto, int IdTarea) {
//        boolean Respuesta = false;
//        Connection cn = Conexion.conectar();
//
//        try {
//
//            PreparedStatement consulta = cn.prepareStatement("DELETE FROM tb_usuariotarea WHERE Tarea = '" + IdTarea + "';");
//            consulta.executeUpdate();
//
//            if (consulta.executeUpdate() > 0) {
//                Respuesta = true;
//            }
//
//            cn.close();
//        } catch (SQLException e) {
//            System.out.println("Error al eliminar Tarea: " + e);
//        }
//        return Respuesta;
//    }
    
    public boolean GuardarRelacion(TareaUsuario Objeto){
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();
        
        try {
            PreparedStatement Consulta = cn.prepareStatement("INSERT INTO tb_usuariotarea VALUES (?,?,?,?,?)");
            Consulta.setInt(1,0);
            Consulta.setString(2, Objeto.getUsuario());
            Consulta.setInt(3, Objeto.getTarea());
            Consulta.setInt(4, Objeto.getEstado());
            Consulta.setString(5, Objeto.getUsuarioACompartir());
            
            
            if(Consulta.executeUpdate()>0){
                Respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar datos "+e);
        }
        
        return Respuesta;
    }
}
