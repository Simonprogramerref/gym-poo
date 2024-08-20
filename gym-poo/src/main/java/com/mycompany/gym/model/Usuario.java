package com.mycompany.gym.model;

import java.util.List;

public class Usuario {
    private String nombre;
    private int edad;
    private int disponibilidad;
    private List<String> preferencias;

    public Usuario(String nombre, int edad, int disponibilidad, List<String> preferencias) {
        this.nombre = nombre;
        this.edad = edad;
        this.disponibilidad = disponibilidad;
        this.preferencias = preferencias;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public List<String> getPreferencias() {
        return preferencias;
    }

    public void asignarCoach(Coach coach) {
        if (this.preferencias.contains(coach.getEspecialidad().name())) {
            System.out.println("Señor/Señora " + this.nombre + ", se le ha asignado el coach " + coach.getNombre()
                    + " con especialidad en " + coach.getEspecialidad());
        } else {
            System.out.println("No se encontró un coach que coincida con las preferencias de " + this.nombre);
        }
    }
}
