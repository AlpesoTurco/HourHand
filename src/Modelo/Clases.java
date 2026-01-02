package Modelo;

/**
 *
 * @author alvar
 */
public class Clases {

    private int IdClases;
    private int Materia;
    private String HoraEntrada;
    private String HoraSalida;
    private String DiaSemana;
    private int Estado;

    public Clases() {
        IdClases = 0;
        Materia = 0;
        HoraEntrada = "";
        HoraSalida = "";
        DiaSemana = "";
        Estado = 0;
    }

    public int getIdClases() {
        return IdClases;
    }

    public void setIdClases(int IdClases) {
        this.IdClases = IdClases;
    }

    public int getMateria() {
        return Materia;
    }

    public void setMateria(int Materia) {
        this.Materia = Materia;
    }

    public String getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(String HoraEntrada) {
        this.HoraEntrada = HoraEntrada;
    }

    public String getHoraSalida() {
        return HoraSalida;
    }

    public void setHoraSalida(String HoraSalida) {
        this.HoraSalida = HoraSalida;
    }

    public String getDiaSemana() {
        return DiaSemana;
    }

    public void setDiaSemana(String DiaSemana) {
        this.DiaSemana = DiaSemana;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

}
