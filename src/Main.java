import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorCitas gestorCitas = new GestorCitas();
        Scanner scanner = new Scanner(System.in);


        if (!gestorCitas.loginAdministrador()) {
            System.out.println("Acceso denegado.");
            return;
        }


        while (true) {
            System.out.println("\n--- Sistema de Administración de Citas ---");
            System.out.println("1. Alta de Doctor");
            System.out.println("2. Alta de Paciente");
            System.out.println("3. Crear Cita");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    gestorCitas.altaDoctor();
                    break;
                case 2:
                    gestorCitas.altaPaciente();
                    break;
                case 3:
                    gestorCitas.crearCita();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
