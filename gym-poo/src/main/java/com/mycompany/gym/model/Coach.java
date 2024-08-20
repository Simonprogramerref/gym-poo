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

    // GETTERS DE COACH 
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

    //STTERS DE COACH 
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
    
    

    public void agregarDia(String dia) {
        horario.add(dia);
    }
    
    public void quitarDia(List<String> horario, String dia) {
        // recorremos la lista
        for (int i = 0; i < horario.size(); i++) {
            if (horario.get(i).equals(dia)) {
                // Eliminar el día cuando se encuentra
                horario.remove(i);
                i--; }
        }
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
