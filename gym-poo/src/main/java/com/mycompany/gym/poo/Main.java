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

        System.out.println("Cuál es tu nombre???");
        user1.setNombre(scanner.nextLine());

        System.out.println("Gracias: " + user1.getNombre() + "\nAhora, te pediré información adicional:" + "\n----->");

        System.out.println("¿Cuál es tu edad?");
        int edad = scanner.nextInt();
        if (edad < 0 || edad < 100) {
            System.out.println("Valor incorrecto, debe ser un entero, haga las cosas bien.");
        } else {

            user1.setEdad(scanner.nextInt());

        }
        scanner.nextLine();

        System.out.println("¿Cuál es tu disponibilidad horaria? Refiríendome a las horas disponibles para entrenar");
        int disponibilidad = scanner.nextInt();
        if (disponibilidad < 0 || disponibilidad > 168) {
            System.out.println("Nos halaga saber que estás así de motivado, pero el día tiene 24 horas y la semana 168. Escoge un valor realista.");
        } else {
            user1.setDisponibilidad(disponibilidad);
        }
        scanner.nextLine();

        List<String> preferencias = new ArrayList<>();

        preferencias.add("CARDIO");
        preferencias.add("YOGA");
        preferencias.add("FUERZA");
        preferencias.add("ZUMBA");

        // Crear usuario
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
