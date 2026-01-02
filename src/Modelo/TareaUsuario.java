package Modelo;

/**
 *
 * @author alvar
 */
public class TareaUsuario {

    private int IdTareausuario;
    private String Usuario;
    private String UsuarioACompartir;
    private int Tarea;
    private int Estado;
    

    public TareaUsuario() {
        IdTareausuario = 0;
        Usuario = "";
        Tarea = 0;
        Estado = 0;
        UsuarioACompartir="";
    }

    public String getUsuarioACompartir() {
        return UsuarioACompartir;
    }

    public void setUsuarioACompartir(String UsuarioACompartir) {
        this.UsuarioACompartir = UsuarioACompartir;
    }

    public int getIdTareausuario() {
        return IdTareausuario;
    }

    public void setIdTareausuario(int IdTareausuario) {
        this.IdTareausuario = IdTareausuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public int getTarea() {
        return Tarea;
    }

    public void setTarea(int Tarea) {
        this.Tarea = Tarea;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
    

}
