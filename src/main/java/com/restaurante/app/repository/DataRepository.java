package com.restaurante.app.repository;

import com.restaurante.app.model.Empleado;
import com.restaurante.app.model.Plato;
import com.restaurante.app.model.Restaurante;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private static List<Restaurante> restaurantes = new ArrayList<>();
    private static List<Plato> platos = new ArrayList<>();
    private static List<Empleado> empleados = new ArrayList<>();

    private static final String FILE_PATH = "datos_restaurante.dat";

    // --- RESTAURANTES ---
    public static List<Restaurante> getRestaurantes() { return restaurantes; }
    public static void addRestaurante(Restaurante r) {
        restaurantes.add(r);
        saveData();
    }
    public static void removeRestaurante(Restaurante r) {
        restaurantes.remove(r);
        saveData();
    }

    // --- PLATOS ---
    public static List<Plato> getPlatos() { return platos; }
    public static void addPlato(Plato p) {
        platos.add(p);
        saveData();
    }
    public static void removePlato(Plato p) {
        platos.remove(p);
        saveData();
    }

    // --- EMPLEADOS ---
    public static List<Empleado> getEmpleados() { return empleados; }
    public static void addEmpleado(Empleado e) {
        empleados.add(e);
        saveData();
    }
    public static void removeEmpleado(Empleado e) {
        empleados.remove(e);
        saveData();
    }

    // --- GUARDADO ---
    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(new ArrayList<>(restaurantes));
            oos.writeObject(new ArrayList<>(platos));
            oos.writeObject(new ArrayList<>(empleados));
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    // --- CARGA ---
    @SuppressWarnings("unchecked")
    public static void loadData() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            restaurantes = (List<Restaurante>) ois.readObject();
            platos = (List<Plato>) ois.readObject();
            empleados = (List<Empleado>) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error al cargar: " + e.getMessage());
        }
    }
}


