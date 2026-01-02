
package Controlador;

import Conexion.Conexion;
import Modelo.Clases;
import Modelo.Materia;
import Vista.GestionarHorario;
import static Vista.GestionarHorario.CbxDiaSemana;
import Vista.IngresarClases;
import static Vista.IngresarClases.CbxMateria;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alvar
 */
public class Ctrl_Clases {
    public static int IdMateriaSelec=22;
    public static int IdClases;
    
    
    //Eliminar Clase
    public boolean EliminarClase(Clases Objeto, int IdClase) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("DELETE FROM tb_clases WHERE IdClase = '" + IdClase + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar la clase: " + e);
        }
        return Respuesta;
    }
    
    public boolean Guardar(Clases Objeto) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_clases VALUES(?,?,?,?,?,?)");
            consulta.setInt(1, 0);
            consulta.setInt(2, Objeto.getMateria());
            consulta.setString(3, Objeto.getHoraEntrada());
            consulta.setString(4, Objeto.getHoraSalida());
            consulta.setString(5, Objeto.getDiaSemana());
            consulta.setInt(6, Objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar La Clase: " + e);
        }
        return Respuesta;
    }
    
    //Materia Seleccionada
    public void MateriaSeleccionada(Materia Objeto) {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pat = cn.prepareStatement(
                    "SELECT IdMateria FROM tb_materias WHERE Nombre = '"+CbxMateria.getSelectedItem().toString()+"'");
            ResultSet rs = pat.executeQuery();

            if (rs.next()) {
                IdMateriaSelec = rs.getInt("IdMateria");
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error de busqueda de Materia: " + e);
        }
        System.out.println(IdMateriaSelec);
    } 
    
    
    
    /*metodo para mostrar todas las materia*/
    public void CargarTablaClases() {
        
        Connection cn = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT tb_clases.IdClase,tb_materias.Nombre, tb_clases.HoraEntrada, tb_clases.HoraSalida \n" +
            "FROM tb_materias \n" +
            "INNER JOIN tb_clases ON tb_materias.IdMateria = tb_clases.Materia \n";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            GestionarHorario.TablaHorario = new JTable(model);
            GestionarHorario.jScrollPane1.setViewportView(GestionarHorario.TablaHorario);
            
            model.addColumn("Id");
            model.addColumn("Materia");
            model.addColumn("Hora Entrada");
            model.addColumn("Hora Salida");

            while (rs.next()) {
                Object fila[] = new Object[4];
                for (int i = 0; i < 4; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla: " + e);
        }

        GestionarHorario.TablaHorario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = GestionarHorario.TablaHorario.rowAtPoint(e.getPoint());
                int fila_columna = 0;

                if (fila_point > -1) {
                    IdClases = (int) model.getValueAt(fila_point, fila_columna);
                    EnviarDatosMateriaSeleccionada(IdClases);
                }
            }
        });

    }

    private void EnviarDatosMateriaSeleccionada(int IdClass) {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pat = cn.prepareStatement(
                    "SELECT * FROM tb_clases WHERE IdClase = '" + IdClass + "'");
            ResultSet rs = pat.executeQuery();

            if (rs.next()) {
                GestionarHorario.TfClase.setText(rs.getString("HoraEntrada"));
                
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error cargar ---: " + e);
        }
    }
    
    public void CargarTablaClasesConFiltro() {
        
        Connection cn = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT tb_clases.IdClase,tb_materias.Nombre, tb_clases.HoraEntrada, tb_clases.HoraSalida \n" +
            "FROM tb_materias \n" +
            "INNER JOIN tb_clases ON tb_materias.IdMateria = tb_clases.Materia \n" +
                "WHERE tb_clases.DiaSemana = '"+CbxDiaSemana.getSelectedItem().toString()+"'";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            GestionarHorario.TablaHorario = new JTable(model);
            GestionarHorario.jScrollPane1.setViewportView(GestionarHorario.TablaHorario);
            
            model.addColumn("Id");
            model.addColumn("Materia");
            model.addColumn("Hora Entrada");
            model.addColumn("Hora Salida");

            while (rs.next()) {
                Object fila[] = new Object[4];
                for (int i = 0; i < 4; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla: " + e);
        }

        GestionarHorario.TablaHorario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = GestionarHorario.TablaHorario.rowAtPoint(e.getPoint());
                int fila_columna = 0;

                if (fila_point > -1) {
                    IdClases = (int) model.getValueAt(fila_point, fila_columna);
                    EnviarDatosMateriaSeleccionada(IdClases);
                }
            }
        });

    }

}
