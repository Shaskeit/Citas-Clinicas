package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class ArchivoUtils {
    private static final Gson gson = new Gson();

    public static void guardarDatos(Object datos, String archivo) {
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(datos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public static <T> T cargarDatos(String archivo, Type tipoLista) {
        try (FileReader reader = new FileReader(archivo)) {
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            System.out.println("Archivo no encontrado o vacío: " + archivo);
            return null;
        }
    }
}
