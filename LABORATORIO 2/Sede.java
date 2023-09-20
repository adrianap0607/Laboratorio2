public class Sede {
    private int idSede;
    private String nombre;

    public Sede(int idSede, String nombre) {
        this.idSede = idSede;
        this.nombre = nombre;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
