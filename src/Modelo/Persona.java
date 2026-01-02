
package Modelo;

import java.util.Random;

/**
 *
 * @author alvar
 */
public class Persona {
    public static int Id;
    public static String Perso;
    
    private String IdPersona;
    private String Nombre;
    private String Apellidos;
    private String Numero;
 
    public Persona(){
        IdPersona = "";
        
        this.Randomizador();
        Nombre = "";
        Apellidos = "";
        Numero = "";
    }

    public String getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(String IdPersona) {
        this.IdPersona = IdPersona;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }
    public void Randomizador(){
        Perso="";
        Random Rdm = new Random();
        int NumeroRandom = Rdm.nextInt(999999);
        Id = 210000000 + NumeroRandom;
        Perso = Perso + Id;
    }
}
