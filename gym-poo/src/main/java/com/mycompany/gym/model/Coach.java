package com.mycompany.gym.model;

import java.util.List;

public class Coach extends Rutina {
    private String nombre;
    private List<String> horario;
    private double precio;
    private Especialidad especialidad;

    public enum Especialidad {
        CARDIO, YOGA, ZUMBA, FUERZA
    }

    public Coach(String nombre, List<String> horario, double precio, Especialidad especialidad, List<String> ejercicios, int duracion, String intensidad) {
        super(ejercicios, duracion, intensidad);
        this.nombre = nombre;
        this.horario = horario;
        this.precio = precio;
        this.especialidad = especialidad;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarDia(String dia) {
        horario.add(dia);
    }

    public void mostrarHorario() {
        System.out.println("Horario de " + nombre + ":");
        for (String dia : horario) {
            System.out.println(dia);
        }
    }

    public void agregarEjercicioARutina(Rutina rutina, String ejercicio) {
        rutina.agregarEjercicio(ejercicio);
        System.out.println("Ejercicio " + ejercicio + " añadido a la rutina de " + rutina.getEjercicios());
    }
}
