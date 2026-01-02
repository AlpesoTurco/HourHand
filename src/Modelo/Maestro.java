package Modelo;

/**
 *
 * @author alvar
 */
public class Maestro {

    private int IdMaestro;
    private String Nombre;
    private String Descripcion;
    private String UsuarioM;
    private int Estado;

    //Iniciamos las variables;
    public Maestro() {
        IdMaestro = 0;
        Nombre = "";
        Descripcion = "";
        UsuarioM = "";
        Estado = 0;
    }

    //Ponemos set y get
    public int getIdMaestro() {
        return IdMaestro;
    }

    public void setIdMaestro(int IdMaestro) {
        this.IdMaestro = IdMaestro;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getUsuarioM() {
        return UsuarioM;
    }

    public void setUsuarioM(String UsuarioM) {
        this.UsuarioM = UsuarioM;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

}
