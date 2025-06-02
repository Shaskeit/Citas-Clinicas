
import com.google.gson.reflect.TypeToken;
import utils.ArchivoUtils;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorCitas {
    private List<Doctor> doctores = new ArrayList<>();
    private List<Paciente> pacientes = new ArrayList<>();
    private List<Cita> citas = new ArrayList<>();
    private Administrador administrador = new Administrador();

    public GestorCitas() {
        Type tipoDoctor = new TypeToken<List<Doctor>>(){}.getType();
        Type tipoPaciente = new TypeToken<List<Paciente>>(){}.getType();
        Type tipoCita = new TypeToken<List<Cita>>(){}.getType();

        List<Doctor> d = ArchivoUtils.cargarDatos("db/doctores.json", tipoDoctor);
        List<Paciente> p = ArchivoUtils.cargarDatos("db/pacientes.json", tipoPaciente);
        List<Cita> c = ArchivoUtils.cargarDatos("db/citas.json", tipoCita);

        if (d != null) doctores = d;
        if (p != null) pacientes = p;
        if (c != null) citas = c;
    }

    public boolean loginAdministrador() {
        return administrador.login();
    }

    public void altaDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID Doctor: ");
        String id = scanner.nextLine();
        System.out.print("Nombre Doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Especialidad Doctor: ");
        String especialidad = scanner.nextLine();

        doctores.add(new Doctor(id, nombre, especialidad));
        ArchivoUtils.guardarDatos(doctores, "db/doctores.json");
        System.out.println("Doctor agregado correctamente.");
    }

    public void altaPaciente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID Paciente: ");
        String id = scanner.nextLine();
        System.out.print("Nombre Paciente: ");
        String nombre = scanner.nextLine();

        pacientes.add(new Paciente(id, nombre));
        ArchivoUtils.guardarDatos(pacientes, "db/pacientes.json");
        System.out.println("Paciente agregado correctamente.");
    }

    public void crearCita() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID Cita: ");
        String id = scanner.nextLine();
        System.out.print("Fecha y Hora de la Cita (formato yyyy-MM-ddTHH:mm): ");
        String fechaHora = scanner.nextLine();
        System.out.print("Motivo de la Cita: ");
        String motivo = scanner.nextLine();

        System.out.print("ID Doctor: ");
        String idDoctor = scanner.nextLine();
        Doctor doctor = findDoctorById(idDoctor);

        System.out.print("ID Paciente: ");
        String idPaciente = scanner.nextLine();
        Paciente paciente = findPacienteById(idPaciente);

        if (doctor != null && paciente != null) {
            Cita cita = new Cita(id, LocalDateTime.parse(fechaHora), motivo, doctor, paciente);
            citas.add(cita);
            ArchivoUtils.guardarDatos(citas, "db/citas.json");
            System.out.println("Cita creada correctamente.");
        } else {
            System.out.println("Error: Doctor o Paciente no encontrado.");
        }
    }

    private Doctor findDoctorById(String id) {
        for (Doctor doctor : doctores) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    private Paciente findPacienteById(String id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }
}
