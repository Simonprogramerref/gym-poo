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

        // Crear usuario por consola 
        Scanner scanner = new Scanner(System.in);
        Usuario user1 = new Usuario();

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("             BIENVENIDO AL GIMNASIO                ");
        System.out.println("═══════════════════════════════════════════════════");

        // Validación de nombre
        while (true) {
            System.out.print("→ ¿Cuál es tu nombre?: ");
            String nombre = scanner.nextLine();
            if (nombre.matches("[a-zA-Z]+")) {
                user1.setNombre(nombre);
                break;
            } else {
                System.out.println(" Error: El nombre debe contener solo caracteres alfabéticos.");
            }
        }

        System.out.println("\n Gracias, " + user1.getNombre() + ".");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("Te pediré información adicional para completar tu registro.");
        System.out.println("═══════════════════════════════════════════════════\n");

        // Validación de edad
        while (true) {
            System.out.print("→ ¿Cuál es tu edad?: ");
            String input = scanner.nextLine();

            if (input.matches("\\d+")) {
                int edad = Integer.parseInt(input);
                if (edad >= 0 && edad <= 50) {
                    user1.setEdad(edad);
                    break;
                } else {
                    System.out.println(" Error: La edad debe ser un valor entre 0 y 50. Inténtalo de nuevo.");
                }
            } else {
                System.out.println(" Error: Debes ingresar un número entero para la edad.");
            }
        }

        // Validación de disponibilidad horaria
        while (true) {
            System.out.print("→ ¿Cuál es tu disponibilidad horaria? (horas disponibles para entrenar a la semana): ");
            String input = scanner.nextLine();

            if (input.matches("\\d+")) {
                int disponibilidad = Integer.parseInt(input);
                if (disponibilidad >= 0 && disponibilidad <= 168) {
                    user1.setDisponibilidad(disponibilidad);
                    break;
                } else {
                    System.out.println(" Error: La disponibilidad debe ser un valor entre 0 y 168. Inténtalo de nuevo.");
                }
            } else {
                System.out.println(" Error: Debes ingresar un número entero para la disponibilidad horaria.");
            }
        }

        // Mostrar preferencias disponibles
        System.out.print("---------->\nAhora necesitamos que nos hables de tus preferencias.\n");
        List<String> preferencias = new ArrayList<>();

        preferencias.add("CARDIO");
        preferencias.add("YOGA");
        preferencias.add("FUERZA");
        preferencias.add("ZUMBA");

        System.out.println("\nPreferencias disponibles:");
        System.out.println("──────────────────────────────");
        int tamaño = preferencias.size();
        for (int i = 0; i < tamaño; i++) {
            // Imprimir el número y la preferencia
            System.out.println((i + 1) + ". " + preferencias.get(i));
        }

        // Crear usuario
        Usuario usuario = new Usuario("Juan", 30, 8, preferencias);

        // Registrar usuario
        gimnasio.registrarUsuario(usuario);

        // Asignar rutina
        Rutina rutina = gimnasio.asignarRutina(usuario);
        System.out.println("\nRutina asignada:");
        System.out.println(rutina.mostrarRutina());

        // Crear Coach y agregar un ejercicio a la rutina del usuario
        List<String> horarioCoach = new ArrayList<>();
        horarioCoach.add("Lunes");
        horarioCoach.add("Miércoles");
        Coach coach = new Coach("Pedro", horarioCoach, 50.0, Coach.Especialidad.CARDIO, rutina.getEjercicios(), 45, "Media");

        // Mostrar horario del coach
        System.out.println("\nHorario del Coach " + coach.getNombre() + ":");
        coach.mostrarHorario();

        // El coach añade un ejercicio a la rutina del usuario
        coach.agregarEjercicioARutina(rutina, "Bicicleta");

        // Imprimir la rutina actualizada
        System.out.println("\nRutina actualizada:");
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

        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("                 ¡REGISTRO COMPLETADO!             ");
        System.out.println("═══════════════════════════════════════════════════");
    }
}
