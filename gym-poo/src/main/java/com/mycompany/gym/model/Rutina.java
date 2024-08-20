package com.mycompany.gym.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rutina {

    private List<String> ejercicios;
    private int duracion;
    private String intensidad;

    private static final Map<Integer, Rutina> RUTINAS_POR_DISPONIBILIDAD = new HashMap<>();

    static {
        RUTINAS_POR_DISPONIBILIDAD.put(0, new Rutina(List.of("Caminata", "Estiramientos"), 30, "Baja"));
        RUTINAS_POR_DISPONIBILIDAD.put(5, new Rutina(List.of("Trote", "Pesas ligeras"), 45, "Media"));
        RUTINAS_POR_DISPONIBILIDAD.put(10, new Rutina(List.of("Carrera", "Pesas pesadas"), 60, "Alta"));
    }

    public Rutina(List<String> ejercicios, int duracion, String intensidad) {
        this.ejercicios = new ArrayList<>(ejercicios);
        this.duracion = duracion;
        this.intensidad = intensidad;
    }

    public void generarRutina(Usuario usuario) {
        Rutina rutina = RUTINAS_POR_DISPONIBILIDAD.entrySet().stream()
                .filter(entry -> usuario.getDisponibilidad() >= entry.getKey())
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(RUTINAS_POR_DISPONIBILIDAD.get(0));

        this.ejercicios = new ArrayList<>(rutina.getEjercicios());
        this.duracion = rutina.getDuracion();
        this.intensidad = rutina.getIntensidad();
    }

    public void agregarEjercicio(String ejercicio) {
        ejercicios.add(ejercicio);
    }

    public void setEjercicios(List<String> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public String mostrarRutina() {
        return "Rutina: " + ejercicios + ", Duración: " + duracion + " minutos, Intensidad: " + intensidad;
    }

    public List<String> getEjercicios() {
        return new ArrayList<>(ejercicios);
    }

    public int getDuracion() {
        return duracion;
    }

    public String getIntensidad() {
        return intensidad;
    }
}