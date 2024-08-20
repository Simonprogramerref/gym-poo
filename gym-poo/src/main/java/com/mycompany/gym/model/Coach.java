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
    private String preferenciaUsuario;

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

    public String getPreferenciaUsuario() {
        return preferenciaUsuario;
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

    public void setPreferenciaUsuario(String preferenciaUsuario) {
        this.preferenciaUsuario = preferenciaUsuario;
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
    
    //creamos una función para que el horario de los coaches nos imprima los días que están disponibles
    public void horarioCompleto(List<String> horario, String nombre) {
        System.out.println(getNombre() + " está disponible a jornada completa o parcial los días:   ");

        List<String> mediaJornada = new ArrayList<>();
        List<String> jornadaCompleta = new ArrayList<>();

        for (int i = 0; i < horario.size(); i++) {
            String dia = horario.get(i);

            if (dia.contains("AM") || dia.contains("PM")) {
                // Quitar "AM" o "PM"
                dia = dia.replace("AM", "").replace("PM", "").trim();
                mediaJornada.add(dia);
                System.out.println(dia + " - Media Jornada");
            } else {
                jornadaCompleta.add(dia);
                System.out.println(dia + " - Jornada Completa");
            }
        }
    }


        public void mostrarHorario(){
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
    
       