package Modelo;

/**
 *
 * @author alvar
 */
public class Tareas {

    //Hacemos las variables
    private int IdTarea;
    private String Nombre;
    private String Descripcion;
    private String FechaEntrega;
    private String HoraEntrega;
    private String Estatus;
    private int Estado;
    private String Usuario;

    //Inicializamos las variables
    public Tareas() {
        IdTarea = 0;
        Nombre = "";
        Descripcion = "";
        FechaEntrega = "";
        HoraEntrega = "";
        Estatus = "";
        Estado = 0;
        Usuario = "";
    }
//Ponemos los Set y Get

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public int getIdTarea() {
        return IdTarea;
    }

    public void setIdTarea(int IdTarea) {
        this.IdTarea = IdTarea;
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

    public String getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(String FechaEntrega) {
        this.FechaEntrega = FechaEntrega;
    }

    public String getHoraEntrega() {
        return HoraEntrega;
    }

    public void setHoraEntrega(String HoraEntrega) {
        this.HoraEntrega = HoraEntrega;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
}
