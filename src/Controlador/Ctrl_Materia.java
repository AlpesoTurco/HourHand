package Controlador;

import Conexion.Conexion;
import static Controlador.Ctrl_Usuario.IdUsuarioActual;
import Modelo.Materia;
import Vista.GestionarMaterias;
import static Vista.GestionarMaterias.TblaMaterias;
import Vista.IngresarClases;
import static Vista.IngresarClases.CbxMateria;
import Vista.TareaNueva;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alvar
 */
public class Ctrl_Materia {
public static int IdMateria;
    //Metodo para registrar Categoria
    public boolean Guardar(Materia Objeto) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_materias VALUES(?,?,?,?,?,?)");
            consulta.setInt(1, 0);
            consulta.setString(2, Objeto.getNombre());
            consulta.setString(3, Objeto.getPrioridad());
            consulta.setString(4, Objeto.getDescripcion());
            consulta.setString(5, Objeto.getUsuario());
            consulta.setInt(6, Objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar materia: " + e);
        }
        return Respuesta;
    }
    //Consulta si ya existe una materia
    public boolean ExisteMateria(String Materia) {
        boolean Respuesta = false;
        String sql = "SELECT Nombre FROM tb_materias WHERE Nombre = '" + Materia + "' AND Usuario = '" + IdUsuarioActual + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar materia: " + e);
        }
        return Respuesta;
    }
    //Metodo Actualizar 
    public boolean Actualizar(Materia Objeto, int IdMateria) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("UPDATE tb_materias SET Nombre = ? WHERE IdMateria = '" + IdMateria + "'");
            consulta.setString(1, Objeto.getNombre());

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar materia: " + e);
        }
        return Respuesta;
    }
    //Eliminar
    public boolean Eliminar(int IdMateria) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("DELETE FROM tb_materias WHERE IdMateria = '" + IdMateria + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar materia: " + e);
        }
        return Respuesta;
    }
    
    
    /*metodo para mostrar todas las materia*/
    public void CargarTablaMateria() {
        Connection cn = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT IdMateria,Nombre,Prioridad,Descripcion,Estado FROM tb_materias WHERE Usuario = '" + IdUsuarioActual + "';";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            GestionarMaterias.TblaMaterias = new JTable(model);
            GestionarMaterias.jScrollPane1.setViewportView(GestionarMaterias.TblaMaterias);

            model.addColumn("IdMateria");
            model.addColumn("Nombre");
            model.addColumn("Prioridad");
            model.addColumn("Descripcion");
            model.addColumn("Estado");

            while (rs.next()) {
                Object fila[] = new Object[5];
                for (int i = 0; i < 5; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla: " + e);
        }

        GestionarMaterias.TblaMaterias.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila_point = GestionarMaterias.TblaMaterias.rowAtPoint(e.getPoint());
                int fila_columna = 0;

                if (fila_point > -1) {
                    IdMateria = (int) model.getValueAt(fila_point, fila_columna);
                    EnviarDatosMateriaSeleccionada(IdMateria);
                }
            }
        });

    }

    private void EnviarDatosMateriaSeleccionada(int IdMateria) {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pat = cn.prepareStatement(
                    "SELECT * FROM tb_materias WHERE IdMateria = '" + IdMateria + "'");
            ResultSet rs = pat.executeQuery();

            if (rs.next()) {
                GestionarMaterias.TfDescripcion.setText(rs.getString("Nombre"));
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error cargar categoria: " + e);
        }
    }
    public void CargarComboBox() {
        Connection cn = Conexion.conectar();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        String sql = "SELECT Nombre FROM tb_materias WHERE Usuario = '" + IdUsuarioActual + "';";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Object fila[] = new Object[1];
                for (int i = 0; i < 1; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addElement(fila[0]);
            }
            TareaNueva.CbxMateria.setModel(model);
            
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar el combo box: " + e);
        }
    }
    public void CargarComboBox2() {
        Connection cn = Conexion.conectar();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        String sql = "SELECT Nombre FROM tb_materias WHERE Usuario = '" + IdUsuarioActual + "';";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Object fila[] = new Object[1];
                for (int i = 0; i < 1; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addElement(fila[0]);
            }
            IngresarClases.CbxMateria.setModel(model);
            
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar el combo box: " + e);
        }
    }
    
}
