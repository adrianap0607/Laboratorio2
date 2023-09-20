import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Salon {
    private int idSede;
    private char edificio;
    private int nivel;
    private int idSalon;
    private int capacidad;
    private Map<String, Map<Integer, Boolean>> horariosOcupados;

    public Salon(int idSede, char edificio, int nivel, int idSalon, int capacidad) {
        this.idSede = idSede;
        this.edificio = edificio;
        this.nivel = nivel;
        this.idSalon = idSalon;
        this.capacidad = capacidad;
        this.horariosOcupados = new HashMap<>();
    }

    public int getIdSede() {
        return idSede;
    }

    public char getEdificio() {
        return edificio;
    }

    public int getNivel() {
        return nivel;
    }

    public int getIdSalon() {
        return idSalon;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean horarioDisponible(int horarioInicio, int duracion, String dia) {
        if (!horariosOcupados.containsKey(dia)) {
            horariosOcupados.put(dia, new HashMap<>());
        }

        Map<Integer, Boolean> horariosDia = horariosOcupados.get(dia);

        for (int i = horarioInicio; i < horarioInicio + duracion; i++) {
            if (horariosDia.containsKey(i) && horariosDia.get(i)) {
                return false; // El horario está ocupado
            }
        }

        for (int i = horarioInicio; i < horarioInicio + duracion; i++) {
            horariosDia.put(i, true);
        }

        return true; // El horario está disponible
    }

    public void reservarHorario(int horarioInicio, int duracion, List<String> dias) {
        for (String dia : dias) {
            if (!horariosOcupados.containsKey(dia)) {
                horariosOcupados.put(dia, new HashMap<>());
            }

            Map<Integer, Boolean> horariosDia = horariosOcupados.get(dia);

            for (int i = horarioInicio; i < horarioInicio + duracion; i++) {
                horariosDia.put(i, true);
            }
        }
    }

    @Override
    public String toString() {
        return "Sede: " + idSede +
               "\nEdificio: " + edificio +
               "\nNivel: " + nivel +
               "\nid_salon: " + idSalon +
               "\nCapacidad: " + capacidad;
    }
}
