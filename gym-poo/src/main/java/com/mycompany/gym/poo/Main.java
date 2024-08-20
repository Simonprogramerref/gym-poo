package com.mycompany.gym.poo;

import com.mycompany.gym.model.Usuario;
import com.mycompany.gym.model.Rutina;
import com.mycompany.gym.model.Coach;
import com.mycompany.gym.model.Membresia;
import com.mycompany.gym.model.TarjetaCredito;
import com.mycompany.gym.service.Gimnasio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Gimnasio gimnasio = new Gimnasio();

        //crear usuario por consola 
        Scanner scanner = new Scanner(System.in);
        Usuario user1 = new Usuario();

        // Validacion de nombre
        while (true) {
            System.out.println("¿Cual es tu nombre?");
            String nombre = scanner.nextLine();
            if (nombre.matches("[a-zA-Z]+")) { // verificar que solo tiene caracteres
                user1.setNombre(nombre);
                break;
            } else {
                System.out.println("Error el nombre debe contener solo caracteres");
            }
        }

        // Mensaje adicional
        System.out.println("Gracias: " + user1.getNombre() + "\nAhora, te pedire informacion adicional:" + "\n----->");

        // Validad edad 
        while (true) {
            System.out.println("¿Cual es tu edad");
            if (scanner.hasNextInt()) {
                int edad = scanner.nextInt();
                if (edad >= 0 && edad <= 120) {
                    user1.setEdad(edad);
                    break;
                } else {
                    System.out.println("Error: La edad debe ser un valor entre 0 y 120. Intentalo de nuevo. ");
                }
            } else {
                System.out.println("Error: Debes ingresar un numero entero para la edad");
                scanner.next();
            }
        }

        scanner.nextLine();

        // Validar disponibilidad horaria
        while (true) {
            System.out.println("¿Cuál es tu disponibilidad horaria? Refiríendome a las horas disponibles para entrenar");
            if (scanner.hasNextInt()) {
                int disponibilidad = scanner.nextInt();
                if (disponibilidad >= 0 && disponibilidad <= 168) { // Considerar el rango de horas en una semana
                    user1.setDisponibilidad(disponibilidad);
                    break; // Salir del bucle si la disponibilidad es válida
                } else {
                    System.out.println("Error: La disponibilidad debe ser un valor entre 0 y 168. Inténtalo de nuevo.");
                }
            } else {
                System.out.println("Error: Debes ingresar un número entero para la disponibilidad.");
                scanner.next();
            }
        }
        scanner.nextLine();

        List<String> preferencias = new ArrayList<>();

        preferencias.add("CARDIO");
        preferencias.add("YOGA");
        preferencias.add("FUERZA");
        preferencias.add("ZUMBA");
        
        //Mostrar preferencias
        System.out.println("Preferencias disponibles:");
        for (String preferencia: preferencias) {
            System.out.println(preferencia);
        }
        
        // Crear usuario
        Usuario usuario = new Usuario("Juan", 30, 8, preferencias);

        // Registrar usuario
        gimnasio.registrarUsuario(usuario);

        // Asignar rutina
        Rutina rutina = gimnasio.asignarRutina(usuario);
        System.out.println(rutina.mostrarRutina());

        // Crear Coach y agregar un ejercicio a la rutina del usuario
        List<String> horarioCoach = new ArrayList<>();
        horarioCoach.add("Lunes");
        horarioCoach.add("Miércoles");
        Coach coach = new Coach("Pedro", horarioCoach, 50.0, Coach.Especialidad.CARDIO, rutina.getEjercicios(), 45, "Media");

        // Mostrar horario del coach
        coach.mostrarHorario();

        // El coach añade un ejercicio a la rutina del usuario
        coach.agregarEjercicioARutina(rutina, "Bicicleta");

        // Imprimir la rutina actualizada
        System.out.println(rutina.mostrarRutina());

        // Crear membresía
        Membresia membresia = new Membresia("Estándar", 50.0);

        // Intentar procesar pago con presupuesto insuficiente
        double presupuesto = 40.0;
        double precioFinal = gimnasio.procesarPago(usuario, membresia, presupuesto);

        // Si el presupuesto es insuficiente, procesar pago con tarjeta de crédito
        if (precioFinal == 0) {
            TarjetaCredito tarjeta = new TarjetaCredito("1234-5678-9012-3456", "Juan Pérez", 1000.0);
            gimnasio.procesarPagoConTarjeta(usuario, membresia, tarjeta, 3);
        }
    }
}
