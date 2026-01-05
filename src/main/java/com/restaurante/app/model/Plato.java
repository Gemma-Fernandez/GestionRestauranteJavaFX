package com.restaurante.app.model;

public class Plato {
    private String nombre;
    private double precio;
    private int calorias;
    private boolean vegetariano;
    private String dificultad;    //

    //CONSTRUCTOR
    public Plato(){}

    public Plato(String nombre, double precio, int calorias, boolean vegetariano, String dificultad) {
        this.nombre = nombre;
        this.precio = precio;
        this.calorias = calorias;
        this.vegetariano = vegetariano;
        this.dificultad = dificultad;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCalorias() { return calorias; }
    public void setCalorias(int calorias) { this.calorias = calorias; }

    public boolean isVegetariano() { return vegetariano; }
    public void setVegetariano(boolean vegetariano) { this.vegetariano = vegetariano; }

    public String getDificultad() { return dificultad; }
    public void setDificultad(String dificultad) { this.dificultad = dificultad; }

    @Override
    public String toString() {
        return nombre;
    }
}
