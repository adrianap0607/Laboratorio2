import java.io.*;
import java.util.*;

public class Universidad {
    private Map<Integer, Sede> sedes;
    private Map<Integer, List<Salon>> salonesPorSede;
    private List<Curso> cursos;
    private List<Curso> cursosSinSalon;

    public Universidad() {
        sedes = new HashMap<>();
        salonesPorSede = new HashMap<>();
        cursos = new ArrayList<>();
        cursosSinSalon = new ArrayList<>();
    }

  // Método para cargar el archivo CSV de salones
public void cargarArchivoSalones(String archivoSalones) {
    try (Scanner scanner = new Scanner(new FileReader(archivoSalones))) {
        // Saltar la primera línea (encabezados)
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            int idSede = Integer.parseInt(parts[0].trim());
            char edificio = parts[1].trim().charAt(0);
            int nivel = Integer.parseInt(parts[2].trim());
            int idSalon = Integer.parseInt(parts[3].trim());
            int capacidad = Integer.parseInt(parts[4].trim());

            // Crear e agregar el salon a la lista de salones
            Salon salon = new Salon(idSede, edificio, nivel, idSalon, capacidad);

            if (!sedes.containsKey(idSede)) {
                sedes.put(idSede, new Sede(idSede, nombreCurso));

            }

            if (!salonesPorSede.containsKey(idSede)) {
                salonesPorSede.put(idSede, new ArrayList<>());
            }

            salonesPorSede.get(idSede).add(salon);
        }
        System.out.println("Archivo de salones cargado exitosamente.");
    } catch (IOException e) {
        System.err.println("Error al cargar el archivo de salones: " + e.getMessage());
    }
}

// Método para cargar el archivo CSV de cursos
public void cargarArchivoCursos(String archivoCursos) {
    try (Scanner scanner = new Scanner(new FileReader(archivoCursos))) {
        // Saltar la primera línea (encabezados)
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            int idCurso = Integer.parseInt(parts[0].trim());
            int idSede = Integer.parseInt(parts[1].trim());
            String nombreCurso = parts[2].trim();
            int horario = Integer.parseInt(parts[3].trim());
            int duracion = Integer.parseInt(parts[4].trim());
            String[] dias = parts[5].trim().split(",");
            int cantidadEstudiantes = Integer.parseInt(parts[6].trim());

            // Crear e agregar el curso a la lista de cursos
            Curso curso = new Curso(idCurso, idSede, nombreCurso, horario, duracion, Arrays.asList(dias), cantidadEstudiantes);
            cursos.add(curso);
        }
        System.out.println("Archivo de cursos cargado exitosamente.");
    } catch (IOException e) {
        System.err.println("Error al cargar el archivo de cursos: " + e.getMessage());
    }
}

    // Método para asignar salones a cursos según las reglas
    public void asignarSalonesACursos() {
        cursosSinSalon = new ArrayList<>();

        for (Curso curso : cursos) {
            int idSedeCurso = curso.getIdSede();

            // Verificar si la sede del curso existe
            if (sedes.containsKey(idSedeCurso)) {
                List<Salon> salonesSede = salonesPorSede.get(idSedeCurso);
                boolean salonAsignado = false;

                for (Salon salon : salonesSede) {
                    boolean horarioDisponible = true;

                    for (String dia : curso.getDias()) {
                        if (!horarioDisponible(salon, curso.getHorario(), curso.getDuracion(), dia)) {
                            horarioDisponible = false;
                            break;
                        }
                    }

                    if (horarioDisponible && salon.getCapacidad() >= curso.getCantidadEstudiantes()) {
                        asignarSalon(curso, salon);
                        salonAsignado = true;
                        break;
                    }
                }

                if (!salonAsignado) {
                    cursosSinSalon.add(curso);
                }
            } else {
                System.err.println("La sede del curso (ID: " + idSedeCurso + ") no existe.");
            }
        }
    }

    // Método para verificar si un salón tiene horario disponible
    private boolean horarioDisponible(Salon salon, int horarioCurso, int duracionCurso, String dia) {
        // Verificar la disponibilidad de horario en el salón usando la lógica implementada en Salon
        return salon.horarioDisponible(horarioCurso, duracionCurso, dia);
    }

    // Método para asignar un salón a un curso
    private void asignarSalon(Curso curso, Salon salon) {
        curso.setSalonAsignado(salon);
        salon.reservarHorario(curso.getHorario(), curso.getDuracion(), curso.getDias());
    }

    // Método para generar el informe
    public void generarInforme() {
        System.out.println("Cursos asignados:");
        for (Curso curso : cursos) {
            if (curso.getSalonAsignado() != null) {
                System.out.println("ID Curso: " + curso.getIdCurso());
                System.out.println("Nombre Curso: " + curso.getNombreCurso());
                System.out.println("Sede: " + sedes.get(curso.getIdSede()).getNombre());
                System.out.println("Salón: " + curso.getSalonAsignado().getIdSalon());
                System.out.println("Horario: " + curso.getHorario());
                System.out.println("Días: " + String.join(", ", curso.getDias()));
                System.out.println("Cantidad Estudiantes: " + curso.getCantidadEstudiantes());
                System.out.println();
            }
        }

        System.out.println("Cursos sin asignación:");
        for (Curso curso : cursosSinSalon) {
            System.out.println("ID Curso: " + curso.getIdCurso());
            System.out.println("Nombre Curso: " + curso.getNombreCurso());
            System.out.println("Sede: " + sedes.get(curso.getIdSede()).getNombre());
            System.out.println("Cantidad Estudiantes: " + curso.getCantidadEstudiantes());
            System.out.println();
        }
    }
}
