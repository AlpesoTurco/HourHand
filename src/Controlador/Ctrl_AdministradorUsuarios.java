package Controlador;

import Conexion.Conexion;
import static Controlador.Ctrl_Usuario.IdUsuarioActual;
import Modelo.Usuario;
import Vista.AdminUsuarios;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alvar
 */
public class Ctrl_AdministradorUsuarios {

    public static String IdUsuario, ContraseñaOculta = "", MostrarAsteriscos = "";

//Metodo para dar de alta a un usuario
    public boolean PrivilegiosAdmin(Usuario Objeto, String IdUsuario) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("UPDATE tb_usuarios SET TipoUsuario = ? WHERE IdUsuario = '" + IdUsuario + "'");
            consulta.setString(1, Objeto.getTipoUsuario());
            
            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al dar o quitar privilegios: " + e);
        }
        return Respuesta;
    }
//Metodo para dar de alta a un usuario
    public boolean DarDeAlta(Usuario Objeto, String IdUsuario) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("UPDATE tb_usuarios SET Estado = 1 WHERE IdUsuario = '" + IdUsuario + "'");

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al dar de alta: " + e);
        }
        return Respuesta;
    }
//Metodo para dar de baja a un usuario

    public boolean DarDeBaja(Usuario Objeto, String IdUsuario) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("UPDATE tb_usuarios SET Estado = 0 WHERE IdUsuario = '" + IdUsuario + "'");

            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al dar de baja: " + e);
        }
        return Respuesta;
    }

    /*metodo para mostrar todas las materia*/
    public void CargarTablaUsuaios() {
        Connection cn = Conexion.conectar();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "SELECT IdUsuario,Nombre,Apellidos,Usuario,TipoUsuario,Estado FROM tb_usuarios,tb_Persona WHERE IdPersona = Persona;";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            AdminUsuarios.TablaUsuarios = new JTable(model);
            AdminUsuarios.jScrollPane1.setViewportView(AdminUsuarios.TablaUsuarios);

            model.addColumn("IdUsuario");
            model.addColumn("Nombre");
            model.addColumn("Apellidos");
            model.addColumn("Usuario");
            model.addColumn("TipoUsuario");
            model.addColumn("Estado");

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

        AdminUsuarios.TablaUsuarios.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila_point = AdminUsuarios.TablaUsuarios.rowAtPoint(e.getPoint());
                int fila_columna = 0;

                if (fila_point > -1) {
                    IdUsuario = (String) model.getValueAt(fila_point, fila_columna);
                    EnviarDatosMateriaSeleccionada(IdUsuario);
                }
            }
        });

    }

    private void EnviarDatosMateriaSeleccionada(String IdUsuario) {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pat = cn.prepareStatement(
                    "SELECT * FROM tb_usuarios WHERE IdUsuario = '" + IdUsuario + "'");
            ResultSet rs = pat.executeQuery();

            if (rs.next()) {
                AdminUsuarios.LbNameUsuario.setText(rs.getString("IdUsuario"));
                ContraseñaOculta = rs.getString("Contraseña");
                //Mostrar Una Contraseña en asteriscos
                String Asteriscos = "";
                for (int i = 0; i < ContraseñaOculta.length(); i++) {
                    Asteriscos = Asteriscos + "*";
                }
                MostrarAsteriscos = Asteriscos;
                AdminUsuarios.LbContraseña.setText(Asteriscos);
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error cargar categoria: " + e);
        }
    }

    public void VerContraseña() {
        AdminUsuarios.LbContraseña.setText(ContraseñaOculta);
    }

    public void NoVerContraseña() {
        AdminUsuarios.LbContraseña.setText(MostrarAsteriscos);
    }
}
