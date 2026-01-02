package Modelo;

/**
 *
 * @author alvar
 */
public class Materia {

    private int IdMateria;
    private String Nombre;
    private String Prioridad;
    private String Descripcion;
    private String Usuario;
    private int Estado;

    //Constructor
    public Materia() {
        IdMateria = 0;
        Nombre = "";
        Prioridad = "";
        Descripcion = "";
        Usuario = "";
        Estado = 0;
    }

    public int getIdMateria() {
        return IdMateria;
    }

    public void setIdMateria(int IdMateria) {
        this.IdMateria = IdMateria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPrioridad() {
        return Prioridad;
    }

    public void setPrioridad(String Prioridad) {
        this.Prioridad = Prioridad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

}
