package com.mycompany.gym.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Coach extends Rutina {

    private String nombre;
    private List<String> horario;
    private double precio;
    private Especialidad especialidad;
    private List<String> mediaJornada;
    private List<String> jornadaCompleta;

    public enum Especialidad {
        CARDIO, YOGA, ZUMBA, FUERZA
    }

    // Constructor
    public Coach(String nombre, double precio, Especialidad especialidad, List<String> ejercicios, int duracion, String intensidad) {
        super(ejercicios, duracion, intensidad);
        this.nombre = nombre;
        this.horario = new ArrayList<>(Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"));
        this.precio = precio;
        this.especialidad = especialidad;
        this.mediaJornada = new ArrayList<>();
        this.jornadaCompleta = new ArrayList<>();
    }

    public Coach(String nombre, List<String> horario, double precio, Especialidad especialidad, List<String> ejercicios, int duracion, String intensidad) {
        super(ejercicios, duracion, intensidad);
        this.nombre = nombre;
        this.horario = new ArrayList<>(Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"));
        this.precio = precio;
        this.especialidad = especialidad;
    }

    public Coach(String nombre, List<String> horario, double precio, Especialidad especialidad, List<String> mediaJornada, List<String> jornadaCompleta, List<String> ejercicios, int duracion, String intensidad) {
        super(ejercicios, duracion, intensidad);
        this.nombre = nombre;
        this.horario = horario;
        this.precio = precio;
        this.especialidad = especialidad;
        this.mediaJornada = mediaJornada; 
        this.jornadaCompleta = jornadaCompleta;
    }

    // GETTERS
    public String getNombre() {
        return nombre;
    }

    public List<String> getHorario() {
        return horario;
    }

    public double getPrecio() {
        return precio;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public List<String> getMediaJornada() {
        return mediaJornada;
    }

    public List<String> getJornadaCompleta() {
        return jornadaCompleta;
    }

    // SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorario(List<String> horario) {
        this.horario = horario;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    // Métodos de la clase
    public void agregarDia(String dia) {
        horario.add(dia);
    }

    public void quitarDia(String dia) {
        for (int i = 0; i < horario.size(); i++) {
            if (horario.get(i).equals(dia)) {
                horario.remove(i);
                i--;
            }
        }
    }

    public void clasificarHorario() {
        if (mediaJornada == null) {
            mediaJornada = new ArrayList<>();
        }
        if (jornadaCompleta == null) {
            jornadaCompleta = new ArrayList<>();
        }

        mediaJornada.clear();
        jornadaCompleta.clear();

        for (String dia : horario) {
            if (dia.contains("AM")) {
                mediaJornada.add(dia);
            } else {
                jornadaCompleta.add(dia);
            }
        }
    }

    // Método simplificado para mostrar el horario del coach
    public void mostrarHorario() {
        System.out.println("Horario de " + nombre + ":");
        for (String dia : horario) {
            String turno = dia.contains("AM") ? "Media Jornada" : "Jornada Completa";
            System.out.println(dia.replace("AM", "").replace("PM", "") + " - " + turno);
        }
    }

    public void agregarEjercicioARutina(Rutina rutina, String ejercicio) {
        List<String> nuevosEjercicios = new ArrayList<>(rutina.getEjercicios());
        nuevosEjercicios.add(ejercicio);
        rutina.setEjercicios(nuevosEjercicios);
        System.out.println("Ejercicio " + ejercicio + " añadido a la rutina.");
        System.out.println("Rutina actualizada: " + rutina.mostrarRutina());
    }
}