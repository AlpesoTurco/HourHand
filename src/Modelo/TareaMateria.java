package Modelo;

/**
 *
 * @author alvar
 */
public class TareaMateria {

    private int IdTareaMateria;
    private int Tarea;
    private int Materia;
    private int Estado;

    //Inicializamos las variables en un constructor
    public TareaMateria() {
        IdTareaMateria = 0;
        Tarea = 0;
        Materia = 0;
        Estado = 0;
    }

    //Set y Get
    public int getIdTareaMateria() {
        return IdTareaMateria;
    }

    public void setIdTareaMateria(int IdTareaMateria) {
        this.IdTareaMateria = IdTareaMateria;
    }

    public int getTarea() {
        return Tarea;
    }

    public void setTarea(int Tarea) {
        this.Tarea = Tarea;
    }

    public int getMateria() {
        return Materia;
    }

    public void setMateria(int Materia) {
        this.Materia = Materia;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

}
