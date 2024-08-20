package com.mycompany.gym.model;

import java.util.List;

public class Usuario extends TarjetaCredito{

    private String nombre;
    private int edad;
    private int disponibilidad;
    private List<String> preferencias;
    private double presupuesto; 
    public boolean tarjeta; 
    
    public Usuario(){ }

    public Usuario(String nombre, int edad, int disponibilidad, List<String> preferencias, double presupuesto) {
        this.nombre = nombre;
        this.edad = edad;
        this.disponibilidad = disponibilidad;
        this.preferencias = preferencias;
        this.presupuesto = presupuesto; 
    }
    

    //getters
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

    public double getPresupuesto() {
        return presupuesto;
    }
    
    
    
    //Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setPreferencias(List<String> preferencias) {
        this.preferencias = preferencias;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }
    
    
    
    

    public void asignarCoach(Coach coach) {
        if (this.preferencias.contains(coach.getEspecialidad().name())) {
            System.out.println("Señor/Señora " + this.nombre + ", se le ha asignado el coach " + coach.getNombre()
                    + " con especialidad en " + coach.getEspecialidad());
        } else {
            System.out.println("No se encontró un coach que coincida con las preferencias de " + this.nombre);
        }
    }

    public void setTarjeta(boolean tarjeta) {
        this.tarjeta = tarjeta;
    }

    public boolean isTarjeta() {
        return tarjeta;
    }

    
}
