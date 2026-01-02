/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author alvar
 */
public class ModeloTareaUsuarioRel {
    private int IdTareaUsuario;
    private int Tarea;
    private int Usuario;
    private int Estado;

    public ModeloTareaUsuarioRel() {
        IdTareaUsuario = 0;
        Tarea = 0;
        Usuario = 0;
        Estado = 0;
    }

    public int getIdTareaUsuario() {
        return IdTareaUsuario;
    }

    public void setIdTareaUsuario(int IdTareaUsuario) {
        this.IdTareaUsuario = IdTareaUsuario;
    }

    public int getTarea() {
        return Tarea;
    }

    public void setTarea(int Tarea) {
        this.Tarea = Tarea;
    }

    public int getUsuario() {
        return Usuario;
    }

    public void setUsuario(int Usuario) {
        this.Usuario = Usuario;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
}
