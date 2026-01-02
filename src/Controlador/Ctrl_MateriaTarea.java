
package Controlador;

import Conexion.Conexion;
import Modelo.Materia;
import Modelo.TareaMateria;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author alvar
 */
public class Ctrl_MateriaTarea {
    public static int IdMateriaSeleccionda;
    
    public boolean GuardarRelacion(TareaMateria Objeto){
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();
        
        try {
            PreparedStatement Consulta = cn.prepareStatement("INSERT INTO tb_tareamateria VALUES (?,?,?,?)");
            Consulta.setInt(1,0);
            Consulta.setInt(2, Objeto.getTarea());
            Consulta.setInt(3, Objeto.getMateria());
            Consulta.setInt(4, Objeto.getEstado());
            
            if(Consulta.executeUpdate()>0){
                Respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar datos "+e);
        }
        
        return Respuesta;
    }


    //Materia Seleccionada
    public void MateriaSeleccionada(Materia Objeto) {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pat = cn.prepareStatement(
                    "SELECT IdMateria FROM tb_materias WHERE Nombre = ?");
            pat.setString(1, Objeto.getNombre());
            ResultSet rs = pat.executeQuery();

            if (rs.next()) {
                IdMateriaSeleccionda = rs.getInt("IdMateria");
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error de busqueda de Materia: " + e);
        }
        System.out.println(IdMateriaSeleccionda);
    }
}
