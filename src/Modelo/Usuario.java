package Modelo;

import java.util.Random;

public class Usuario {

    //Variables
    public static String IdUsu;
    private int Id;
    private String IdUsuario;
    private String TipoUsuario;
    private String Usuario;
    private String Password;
    private String Persona;
    private int Estado;

    //Constructor
    public Usuario() {
        IdUsuario = "";
        this.Randomizador();
        TipoUsuario = "";
        Usuario = "";
        Password = "";
        Persona = "";
        Estado = 0;
    }

    //Set y Get

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(String TipoUsuario) {
        this.TipoUsuario = TipoUsuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPersona() {
        return Persona;
    }

    public void setPersona(String Persona) {
        this.Persona = Persona;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
    
    
    public void Randomizador(){
        IdUsu="";
        Random Rdm = new Random();
        int NumeroRandom = Rdm.nextInt(999999);
        Id = 210000000 + NumeroRandom;
        IdUsu = IdUsu + Id;
    }
}
