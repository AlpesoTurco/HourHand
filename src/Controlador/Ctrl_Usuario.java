package Controlador;

import Conexion.Conexion;
import Modelo.Usuario;
import Vista.MiUsuario;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author alvar
 */
public class Ctrl_Usuario {

    public static String IdUsuarioActual = "";
    public static String NombreUsuarioActual = "";
    public static String NickNameUsuarioActual = "";
    public static String ApellidosUsuarioActual = "";
    public static String TipoUsuarioActual = "";

    public boolean LoginUser(Usuario Objeto) {

        boolean Respuesta = false;

        Connection cn = Conexion.conectar();
        String sql = "SELECT Usuario, Contraseña FROM tb_Usuarios WHERE Usuario = '" + Objeto.getUsuario() + "' and Contraseña = '" + Objeto.getPassword() + "' AND Estado = 1";
        Statement st;

        try {

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesion" + e);
            JOptionPane.showMessageDialog(null, "Error al iniciar sesion " + e);
        }
        return Respuesta;
    }

    /*
***********************************************
Metodo para sacar el usuario Actual    
***********************************************    
     */
    public void UsuarioActual(Usuario Objeto) {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pat = cn.prepareStatement(
                    "SELECT IdUsuario,TipoUsuario,Usuario FROM tb_Usuarios WHERE Usuario = ? AND Contraseña = ?");
            pat.setString(1, Objeto.getUsuario());
            pat.setString(2, Objeto.getPassword());
            ResultSet rs = pat.executeQuery();

            if (rs.next()) {
                IdUsuarioActual = rs.getString("IdUsuario");
                TipoUsuarioActual = rs.getString("TipoUsuario");
                NickNameUsuarioActual = rs.getString("Usuario");
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error de busqueda de Usuario: " + e);
        }
        System.out.println(IdUsuarioActual);
        System.out.println(TipoUsuarioActual);
    }

    public void PersonaActual() {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pat = cn.prepareStatement(
                    "SELECT Nombre, Apellidos FROM tb_persona,tb_usuarios WHERE tb_persona.IdPersona = tb_usuarios.Persona AND IdUsuario = '" + IdUsuarioActual + "'");
            ResultSet rs = pat.executeQuery();

            if (rs.next()) {
                NombreUsuarioActual = rs.getString("Nombre");
                ApellidosUsuarioActual = rs.getString("Apellidos");
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error de busqueda de Usuario: " + e);
        }
        System.out.println(NombreUsuarioActual);
        System.out.println(ApellidosUsuarioActual);
    }

    /*
***********************************************
Metodo para nuevo usuario 
***********************************************    
     */
    public boolean NuevoUsuario(Usuario Objeto) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement Cons = cn.prepareStatement("INSERT INTO tb_usuarios VALUES (?,?,?,?,?,?)");
            Cons.setString(1, Objeto.getIdUsuario());
            Cons.setString(2, Objeto.getTipoUsuario());
            Cons.setString(3, Objeto.getUsuario());
            Cons.setString(4, Objeto.getPassword());
            Cons.setInt(5, Objeto.getEstado());
            Cons.setString(6, Objeto.getPersona());

            if (Cons.executeUpdate() > 0) {
                Respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar los datos" + e);
            System.out.println("Error al registrar los datos" + e);
        }
        return Respuesta;
    }

    //Comprobamos si existe un usuario
    public boolean ExisteUsuario(String Usuario) {
        boolean Respuesta = false;
        String sql = "SELECT Nombre FROM tb_usuarios WHERE IdUsuario = '" + Usuario + "'";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar Usuario: " + e);
        }
        return Respuesta;
    }

    public boolean ActualizarContraseña(Usuario Objeto) {
        boolean Respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("UPDATE tb_usuarios SET Contraseña = ? WHERE IdUsuario = ?");
            consulta.setString(1, Objeto.getPassword());
            consulta.setString(2, IdUsuarioActual);
            if (consulta.executeUpdate() > 0) {
                Respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar la contraseña: " + e);
        }
        return Respuesta;
    }

    
}
