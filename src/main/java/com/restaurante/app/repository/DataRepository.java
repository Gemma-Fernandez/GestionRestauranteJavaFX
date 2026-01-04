package com.restaurante.app.repository;

import com.restaurante.app.model.Empleado;
import com.restaurante.app.model.Plato;
import com.restaurante.app.model.Restaurante;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    // Listas en memoria
    private static List<Restaurante> restaurantes = new ArrayList<>();
    private static List<Plato> platos = new ArrayList<>();
    private static List<Empleado> empleados = new ArrayList<>();

    private static final String FILE_PATH = "datos_gourmet.dat";

    // --- MÉTODOS CRUD ---

    // Restaurantes
    public static void addRestaurante(Restaurante r) {
        restaurantes.add(r);
        saveData();
    }
    public static List<Restaurante> getRestaurantes() { return restaurantes; }
    public static void removeRestaurante(Restaurante r) {
        restaurantes.remove(r);
        saveData();
    }

    // Platos
    public static void addPlato(Plato p) {
        platos.add(p);
        saveData();
    }
    public static List<Plato> getPlatos() { return platos; }
    public static void removePlato(Plato p) {
        platos.remove(p);
        saveData();
    }

    // Empleados
    public static void addEmpleado(Empleado e) {
        empleados.add(e);
        saveData();
    }
    public static List<Empleado> getEmpleados() { return empleados; }
    public static void removeEmpleado(Empleado e) {
        empleados.remove(e);
        saveData();
    }

    // PERSISTENCIA (GUARDAR Y CARGAR)

    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(restaurantes);
            oos.writeObject(platos);
            oos.writeObject(empleados);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    public static void loadData() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            restaurantes = (List<Restaurante>) ois.readObject();
            platos = (List<Plato>) ois.readObject();
            empleados = (List<Empleado>) ois.readObject();
            System.out.println("Datos cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

