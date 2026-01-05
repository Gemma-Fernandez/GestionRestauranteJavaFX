package com.restaurante.app.repository;

import com.restaurante.app.model.Empleado;
import com.restaurante.app.model.Plato;
import com.restaurante.app.model.Restaurante;


import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    // Listas en memoria
    private static List<Restaurante> restaurantes = new ArrayList<>();
    private static List<Plato> platos = new ArrayList<>();
    private static List<Empleado> empleados = new ArrayList<>();


    // --- MÉTODOS CRUD ---

    // Restaurantes
    public static List<Restaurante> getRestaurantes() { return restaurantes; }

    public static void addRestaurante(Restaurante r) {
        restaurantes.add(r);
    }

    public static void removeRestaurante(Restaurante r) {
        restaurantes.remove(r);
    }

    // Platos
    public static List<Plato> getPlatos() { return platos; }

    public static void addPlato(Plato p) {
        platos.add(p);
    }

    public static void removePlato(Plato p) {
        platos.remove(p);
    }

    // Empleados
    public static List<Empleado> getEmpleados() { return empleados; }

    public static void addEmpleado(Empleado e) {
        empleados.add(e);
    }
    public static void removeEmpleado(Empleado e) {
        empleados.remove(e);
    }

    // intento de guardar datos en csv

    public static void saveData() {

    }

    public static void loadData() {
    }
}

