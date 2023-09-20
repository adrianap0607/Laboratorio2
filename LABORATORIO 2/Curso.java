import java.util.List;

public class Curso {
    private int idCurso;
    private int idSede;
    private String nombreCurso;
    private int horario;
    private int duracion;
    private List<String> dias;
    private int cantidadEstudiantes;
    private Salon salonAsignado; 

    public Curso(int idCurso, int idSede, String nombreCurso, int horario, int duracion, List<String> dias, int cantidadEstudiantes) {
        this.idCurso = idCurso;
        this.idSede = idSede;
        this.nombreCurso = nombreCurso;
        this.horario = horario;
        this.duracion = duracion;
        this.dias = dias;
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<String> getDias() {
        return dias;
    }

    public void setDias(List<String> dias) {
        this.dias = dias;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    public void setCantidadEstudiantes(int cantidadEstudiantes) {
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    public Salon getSalonAsignado() {
        return salonAsignado;
    }

    public void setSalonAsignado(Salon salonAsignado) {
        this.salonAsignado = salonAsignado;
    }
}
