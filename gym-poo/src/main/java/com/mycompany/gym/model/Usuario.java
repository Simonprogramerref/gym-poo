package com.mycompany.gym.model;

import java.util.List;
import java.util.regex.Pattern;

public class Usuario extends TarjetaCredito{

    private String nombre;
    private int edad;
    private int disponibilidad;
    private List<String> preferencias;
    private double presupuesto; 
    public boolean tarjeta; 
    private String membresiaUsuario;
    
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

    public String getMembresiaUsuario() {
        return membresiaUsuario;
    }

    //Setters
    public void setNombre(String nombre) {
        if (Pattern.matches("[a-zA-Z]+", nombre)) {
            this.nombre = nombre;
        } else {
            System.out.println("Error: El nombre debe contener solo caracteres alfabéticos.");
        }
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

    public void setMembresiaUsuario(String membresiaUsuario) {
        this.membresiaUsuario = membresiaUsuario;
    }

    public void setTarjeta(boolean tarjeta) {
        this.tarjeta = tarjeta;
    }

    public boolean isTarjeta() {
        return tarjeta;
    }

    public void asignarCoach(Coach coach) {
        if (this.preferencias.contains(coach.getEspecialidad().name())) {
            System.out.println("Señor/Señora " + this.nombre + ", se le ha asignado el coach " + coach.getNombre()
                    + " con especialidad en " + coach.getEspecialidad());
        } else {
            System.out.println("No se encontró un coach que coincida con las preferencias de " + this.nombre);
            // Buscar un coach que se ajuste mejor a las preferencias del usuario
            List<Coach.Especialidad> especialidadesPreferidas = this.preferencias.stream()
                    .map(Coach.Especialidad::valueOf)
                    .toList();
            Coach coachSugerido = getCoachRecomendado(especialidadesPreferidas);
            if (coachSugerido != null) {
                System.out.println("Sin embargo, se le ha asignado el coach " + coachSugerido.getNombre()
                        + " con especialidad en " + coachSugerido.getEspecialidad());
            } else {
                System.out.println("No se encontró un coach adecuado para sus preferencias.");
            }
        }
    }

    private Coach getCoachRecomendado(List<Coach.Especialidad> especialidadesPreferidas) {
        // Lógica para buscar y devolver el coach más adecuado
        // Implementación específica de la estrategia de recomendación
        return null;
    }
}