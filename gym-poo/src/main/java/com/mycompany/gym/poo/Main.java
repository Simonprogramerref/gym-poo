package com.mycompany.gym.poo;

import com.mycompany.gym.model.Usuario;
import com.mycompany.gym.model.Rutina;
import com.mycompany.gym.model.Coach;
import com.mycompany.gym.model.Membresia;
import com.mycompany.gym.model.TarjetaCredito;
import com.mycompany.gym.service.Gimnasio;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gimnasio gimnasio = new Gimnasio();

        // Crear usuario
        List<String> preferencias = new ArrayList<>();
        preferencias.add("CARDIO");
        preferencias.add("YOGA");
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