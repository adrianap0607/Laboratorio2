import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Universidad universidad = new Universidad();

        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Cargar archivo de salones");
            System.out.println("2. Cargar archivo de cursos");
            System.out.println("3. Asignar salones a cursos");
            System.out.println("4. Ver informe");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del archivo de salones (Ejemplo: salones.csv): ");
                    String archivoSalones = scanner.nextLine();
                    universidad.cargarArchivoSalones(archivoSalones);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del archivo de cursos (Ejemplo: cursos.csv): ");
                    String archivoCursos = scanner.nextLine();
                    universidad.cargarArchivoCursos(archivoCursos);
                    break;
                case 3:
                    universidad.asignarSalonesACursos();
                    System.out.println("Salones asignados a cursos.");
                    break;
                case 4:
                    universidad.generarInforme();
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
