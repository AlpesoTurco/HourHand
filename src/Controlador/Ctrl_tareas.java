package Controlador;

import Conexion.Conexion;
import static Controlador.Ctrl_Usuario.IdUsuarioActual;
import Modelo.TareaUsuario;
import Modelo.Tareas;
import Vista.CompartirTarea;
import Vista.GestionTareas;
import static Vista.GestionTareas.CbxFiltrar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alvar
 */
public class Ctrl_tareas {

    public static int IdTareaActual, IdTarea, IdTareaSeleccionada;
    public static String DescripcionTarea = "", NombreTareaActual = "";

    //Metodo Actualizar 
    public boolean ActualizarTarea(Tareas Objeto, int IdTarea) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("UPDATE tb_Tareas SET Nombre = ?, Descripcion = ?, Estatus = ? WHERE IdTarea = '" + IdTarea + "';");
            consulta.setString(1, Objeto.getNombre());
            consulta.setString(2, Objeto.getDescripcion());
            consulta.setString(3, Objeto.getEstatus());

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar Tarea: " + e);
        }
        return Respuesta;
    }

    public boolean EliminarTarea(Tareas Objeto, int IdTarea) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("UPDATE tb_Tareas SET Estado = 0 WHERE IdTarea = '" + IdTarea + "';");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar Tarea: " + e);
        }
        return Respuesta;
    }

    public boolean EliminarTareaCompartida(TareaUsuario Objeto, int IdTarea) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("DELETE FROM tb_usuariotarea WHERE Tarea = '" + IdTarea + "';");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar Tarea: " + e);
        }
        return Respuesta;
    }

    /*metodo para mostrar todas las tarea*/
    public void CargarTablaTareas() {
        Connection cn = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT tb_tareas.IdTarea, tb_tareas.Nombre, tb_tareas.FechaEntrega, Tb_materias.Nombre, tb_tareas.Estatus\n"
                + "FROM tb_tareas\n"
                + "INNER JOIN tb_tareamateria ON tb_tareas.IdTarea = tb_tareamateria.Tarea\n"
                + "INNER JOIN Tb_materias ON tb_tareamateria.Materia = Tb_materias.IdMateria\n"
                + "WHERE tb_tareas.Usuario = '" + IdUsuarioActual + "' AND tb_tareas.Estado = 1";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            GestionTareas.TablaTareas = new JTable(model);
            GestionTareas.jScrollPane1.setViewportView(GestionTareas.TablaTareas);

            model.addColumn("Id Tarea");
            model.addColumn("Tarea");
            model.addColumn("Fecha De Entrega");
            model.addColumn("Materia");
            model.addColumn("Estatus");

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

        GestionTareas.TablaTareas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila_point = GestionTareas.TablaTareas.rowAtPoint(e.getPoint());
                int fila_columna = 0;

                if (fila_point > -1) {
                    IdTarea = (int) model.getValueAt(fila_point, fila_columna);
                    EnviarDatosMateriaSeleccionada(IdTarea);
                }
            }
        });

    }

    private void EnviarDatosMateriaSeleccionada(int IdMateria) {
        CompartirTarea DetTareas = new CompartirTarea();

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pat = cn.prepareStatement(
                    "SELECT * FROM tb_tareas WHERE IdTarea= '" + IdMateria + "'");
            ResultSet rs = pat.executeQuery();

            if (rs.next()) {
                GestionTareas.LbVisaualizacion.setText(rs.getString("Nombre"));
                GestionTareas.TaDetalleRequisitos.setText(rs.getString("Descripcion"));
                GestionTareas.LbFechaEntrega.setText(rs.getString("FechaEntrega"));
                GestionTareas.LbHoraEntrega.setText(rs.getString("HoraEntrega"));
            }
            IdTareaSeleccionada = IdMateria;
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error cargar categoria: " + e);
        }
    }

    /*Este metodo filtra tareas sin hacer*/
    public void CargarTablaTareasSinHacer() {

        Connection cn = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT tb_tareas.IdTarea, tb_tareas.Nombre, tb_tareas.FechaEntrega, Tb_materias.Nombre, tb_tareas.Estatus\n"
                + "FROM tb_tareas\n"
                + "INNER JOIN tb_tareamateria ON tb_tareas.IdTarea = tb_tareamateria.Tarea\n"
                + "INNER JOIN Tb_materias ON tb_tareamateria.Materia = Tb_materias.IdMateria\n"
                + "WHERE tb_tareas.Usuario = '" + IdUsuarioActual + "' AND tb_tareas.Estado = 1 AND tb_tareas.Estatus = '" + CbxFiltrar.getSelectedItem().toString() + "'";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            GestionTareas.TablaTareas = new JTable(model);
            GestionTareas.jScrollPane1.setViewportView(GestionTareas.TablaTareas);

            model.addColumn("Id Tarea");
            model.addColumn("Tarea");
            model.addColumn("Fecha De Entrega");
            model.addColumn("Materia");
            model.addColumn("Estatus");

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

        GestionTareas.TablaTareas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila_point = GestionTareas.TablaTareas.rowAtPoint(e.getPoint());
                int fila_columna = 0;

                if (fila_point > -1) {
                    IdTarea = (int) model.getValueAt(fila_point, fila_columna);
                    EnviarDatosMateriaSeleccionada(IdTarea);
                }
            }
        });

    }

    public void CargarTablaTareasCompartida() {

        Connection cn = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT\n"
                + "  tb_tareas.IdTarea, \n"
                + "  u2.Usuario AS CompartidoPor, \n"
                + "  tb_tareas.Nombre, \n"
                + "  tb_tareas.FechaEntrega, \n"
                + "  tb_materias.Nombre, \n"
                + "  tb_tareas.Estatus\n"
                + "FROM \n"
                + "  tb_tareas\n"
                + "  INNER JOIN tb_tareamateria ON tb_tareas.IdTarea = tb_tareamateria.Tarea\n"
                + "  INNER JOIN Tb_materias ON tb_tareamateria.Materia = Tb_materias.IdMateria\n"
                + "  INNER JOIN tb_usuariotarea ON tb_usuariotarea.Tarea = tb_tareas.IdTarea\n"
                + "  INNER JOIN tb_usuarios u1 ON u1.IdUsuario = tb_usuariotarea.UsuarioACompartir\n"
                + "  INNER JOIN tb_usuarios u2 ON u2.IdUsuario = tb_usuariotarea.Usuario\n"
                + "WHERE \n"
                + "  tb_usuariotarea.UsuarioACompartir = '" + IdUsuarioActual + "' AND \n"
                + "  tb_tareas.Estado = 1;";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            GestionTareas.TablaTareas = new JTable(model);
            GestionTareas.jScrollPane1.setViewportView(GestionTareas.TablaTareas);

            model.addColumn("Id Tarea");
            model.addColumn("Compartido Por:");
            model.addColumn("Tarea");
            model.addColumn("Fecha De Entrega");
            model.addColumn("Materia");
            model.addColumn("Estatus");

            while (rs.next()) {
                Object fila[] = new Object[6];
                for (int i = 0; i < 6; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla: " + e);
        }

        GestionTareas.TablaTareas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila_point = GestionTareas.TablaTareas.rowAtPoint(e.getPoint());
                int fila_columna = 0;

                if (fila_point > -1) {
                    IdTarea = (int) model.getValueAt(fila_point, fila_columna);
                    EnviarDatosMateriaSeleccionada(IdTarea);
                }
            }
        });

    }

    public boolean Guardar(Tareas Objeto) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_tareas VALUES(?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);
            consulta.setString(2, Objeto.getNombre());
            consulta.setString(3, Objeto.getDescripcion());
            consulta.setString(4, Objeto.getFechaEntrega());
            consulta.setInt(5, Objeto.getEstado());
            consulta.setString(6, Objeto.getEstatus());
            consulta.setString(7, Objeto.getHoraEntrega());
            consulta.setString(8, Objeto.getUsuario());

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar tarea: " + e);
        }
        return Respuesta;
    }

    public boolean ExisteTarea(String Tarea) {
        boolean Respuesta = false;
        String sql = "SELECT Nombre FROM tb_tareas WHERE Nombre = '" + Tarea + "' AND Estado = 1 AND Usuario = '"+IdUsuarioActual+"';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar tarea: " + e);
        }
        return Respuesta;
    }

    public void TareaActual(Tareas Objeto) {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pat = cn.prepareStatement(
                    "SELECT IdTarea,Nombre,Descripcion FROM tb_tareas WHERE IdTarea = (SELECT MAX(IdTarea) FROM tb_tareas)");
            ResultSet rs = pat.executeQuery();

            if (rs.next()) {
                IdTareaActual = rs.getInt("IdTarea");
                NombreTareaActual = rs.getString("Nombre");
                DescripcionTarea = rs.getString("Descripcion");
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error de busqueda de Tarea: " + e);
        }
        System.out.println(IdTareaActual);
        System.out.println(NombreTareaActual);
        System.out.println(DescripcionTarea);
    }
}
