package com.restaurante.app.model;
import java.io.Serializable;
import java.time.LocalDate;


public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellidos;     // Obligatorio
    private float sueldo;         // Obligatorio
    private LocalDate fechaContrato;
    private String horario;       // (Mañana, Tarde)

    public Empleado(String nombre, String apellidos, float sueldo, LocalDate fechaContrato, String horario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sueldo = sueldo;
        this.fechaContrato = fechaContrato;
        this.horario = horario;
    }

        // Getters y Setters
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getApellidos() { return apellidos; }
        public void setApellidos(String apellidos) { this.apellidos = apellidos; }

        public float getSueldo() { return sueldo; }
        public void setSueldo(float sueldo) { this.sueldo = sueldo; }

        public LocalDate getFechaContrato() { return fechaContrato; }
        public void setFechaContrato(LocalDate fechaContrato) { this.fechaContrato = fechaContrato; }

        public String getHorario() { return horario; }
        public void setHorario(String horario) { this.horario = horario; }

        @Override
        public String toString() { return nombre + " " + apellidos; }
    }

