import java.util.Scanner;

public class Administrador {
    private String usuario = "admin";
    private String contrasena = "1234";

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Usuario: ");
        String usuarioIngreso = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenaIngreso = scanner.nextLine();

        return usuario.equals(usuarioIngreso) && contrasena.equals(contrasenaIngreso);
    }
}
