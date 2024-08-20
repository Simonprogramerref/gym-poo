package com.mycompany.gym.model;

import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private List<String> ejercicios;
    private int duracion;
    private String intensidad;

    public Rutina(List<String> ejercicios, int duracion, String intensidad) {
        this.ejercicios = ejercicios;
        this.duracion = duracion;
        this.intensidad = intensidad;
    }

    public void generarRutina(Usuario usuario) {
        this.ejercicios = new ArrayList<>();
        if (usuario.getDisponibilidad() < 5) {
            this.duracion = 30;
            this.intensidad = "Baja";
            this.ejercicios.add("Caminata");
            this.ejercicios.add("Estiramientos");
        } else if (usuario.getDisponibilidad() < 10) {
            this.duracion = 45;
            this.intensidad = "Media";
            this.ejercicios.add("Trote");
            this.ejercicios.add("Pesas ligeras");
        } else {
            this.duracion = 60;
            this.intensidad = "Alta";
            this.ejercicios.add("Carrera");
            this.ejercicios.add("Pesas pesadas");
        }
    }

    public void agregarEjercicio(String ejercicio) {
        ejercicios.add(ejercicio);
    }

    public String mostrarRutina() {
        return "Rutina: " + ejercicios + ", Duración: " + duracion + " minutos, Intensidad: " + intensidad;
    }

    public List<String> getEjercicios() {
        return ejercicios;
    }
}
