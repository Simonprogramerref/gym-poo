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
import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Gimnasio gimnasio = new Gimnasio();
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

        System.out.println("═══════════════════════════════════════════════════\n");

        // Validación de presupuesto
        double presupuesto = 0;
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO")); // Formato para pesos colombianos
        formatoMoneda.setMaximumFractionDigits(0); // Sin decimales

        while (true) {
            System.out.print("→ ¿Cuál es tu presupuesto? (para pagar la mensualidad del gym SAY): ");
            String input = scanner.nextLine();

            if (input.matches("\\d+")) { // Verifica que la entrada sea solo dígitos
                presupuesto = Double.parseDouble(input);
                if (presupuesto >= 0) {
                    // Redondear el presupuesto a la unidad más cercana
                    presupuesto = Math.round(presupuesto);
                    // Imprimir presupuesto formateado en pesos colombianos
                    System.out.println("Tu presupuesto es: " + formatoMoneda.format(presupuesto));
                    user1.setDisponibilidad((int) presupuesto);
                    break;
                } else {
                    System.out.println("Error: El presupuesto debe ser un valor mayor o igual a cero.");
                }
            } else {
                System.out.println("Error: Ingresa un valor numérico válido.");
            }
        }

        // Membresías disponibles
        Membresia superDeveloped = new Membresia("SUPERDEVELOPED", 500.0);
        Membresia medioDevelop = new Membresia("MEDIODEVELOP", 200.0);
        Membresia yoProgramando = new Membresia("YOPROGRAMANDO", 100.0);

        System.out.println("\nElige tu membresía:");
        System.out.println("──────────────────────────────");
        System.out.println("1. SUPERDEVELOPED - Entrena todos los días de la semana, puede escoger a todos los coaches");
        System.out.println("2. MEDIODEVELOP - Solo puede escoger 3 entrenadores y no entrena los fines de semana");
        System.out.println("3. YOPROGRAMANDO - Solo entrena 1 coach, no entrena los lunes, miércoles ni sábados");

        Membresia seleccionada = null;
        while (seleccionada == null) {
            System.out.print("→ Elige una opción (1, 2, 3): ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    seleccionada = superDeveloped;
                    break;
                case 2:
                    seleccionada = medioDevelop;
                    break;
                case 3:
                    seleccionada = yoProgramando;
                    break;
                default:
                    System.out.println("Error: Opción inválida. Por favor, elige un número válido.");
                    break;
            }
        }

        System.out.println("\nHas seleccionado la membresía: " + seleccionada.mostrarDetalles());

        // Verificar si el presupuesto es suficiente
        double precioFinal = seleccionada.getPrecioBase();

        if (presupuesto < precioFinal) {
            System.out.println("El presupuesto no es suficiente para pagar la membresía seleccionada.");
            System.out.println("¿Tienes tarjeta de crédito? (escribe 'true' o 'false')");
            String tarjetaInput = scanner.nextLine().trim().toLowerCase();
            boolean tieneTarjeta = tarjetaInput.equals("true");

            if (!tieneTarjeta) {
                System.out.println("Lamentamos informarle que no podrá acceder a ninguna membresía.");
            } else {
                System.out.println("Afortunadamente cuentas con una tarjeta que lo respalda para acceder a nuestros servicios.");

                System.out.print("¿De cuánto es el monto de su tarjeta de crédito? ");
                double monto = scanner.nextDouble();
                scanner.nextLine();

                if (monto < precioFinal) {
                    System.out.println("Lamentamos informarle que no podrá acceder a ninguna membresía.");
                } else {
                    // Procesar pago con tarjeta de crédito
                    TarjetaCredito tarjetaCredito = new TarjetaCredito("1234-5678-9012-3456", "Nombre del Usuario", monto);
                    gimnasio.procesarPagoConTarjeta(user1, seleccionada, tarjetaCredito, 3);
                    System.out.println("¡Pago realizado con éxito!");
                }
            }
        } else {
            System.out.println("¡Con el presupuesto ingresado puedes pagar la membresía seleccionada!");

        }

        //guardamos en en usuario el tipo de membresia que quería 
        user1.setMembresiaUsuario(seleccionada.getTipo());

        System.out.println("El valor final a pagar será de: " + formatoMoneda.format(precioFinal));

        System.out.println("\n Gracias, " + user1.getNombre() + " por la información");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("Ahora vamos a lo más importante, tus entrenadores. ");
        System.out.println("═══════════════════════════════════════════════════\n");

        //segumos interactuando con el usuario pero ahora dependiendo de la memebresia 
        System.out.println("\n Ahora que ya sabemos que tu membresia es " + user1.getMembresiaUsuario() + " te daremos más información acorde a esta: ");
        if (user1.getMembresiaUsuario().equals("SUPERDEVELOPED")) {
            System.out.println("Usted puede entrenar todos los días, ahorita a la hora de escoger horario, puede hacerlo a diario");
            System.out.println("Así mismo, usted podría escoger a todos los coachs");
            System.out.println("Recuerda que tu prefrencia fue" + "");

        }

        // Mostrar preferencias disponibles
        List<String> preferencias = new ArrayList<>();
        preferencias.add("CARDIO");
        preferencias.add("YOGA");
        preferencias.add("FUERZA");
        preferencias.add("ZUMBA");

        //validar la preferencia 
        while (true) {
            System.out.println("──────────────────────────────");
            System.out.println("Preferencias disponibles: escoge una escribiendo el número correspondiente a tu preferencia");

            for (int i = 0; i < preferencias.size(); i++) {
                System.out.println((i + 1) + ". " + preferencias.get(i));
            }

            String seleccionPreferencia = scanner.nextLine();
            if (seleccionPreferencia.matches("\\d") && Integer.parseInt(seleccionPreferencia) > 0 && Integer.parseInt(seleccionPreferencia) <= preferencias.size()) {
                int index = Integer.parseInt(seleccionPreferencia) - 1;
                user1.setPreferencias(List.of(preferencias.get(index)));
                break;
            } else {
                System.out.println("Error: Selecciona un número válido de la lista de preferencias.");
            }
        }

        // Registrar usuario
        gimnasio.registrarUsuario(user1);

        // Asignar rutina
        Rutina rutina = gimnasio.asignarRutina(user1);
        System.out.println("\nRutina asignada:");
        System.out.println(rutina.mostrarRutina());

        // Crear Coach y agregar un ejercicio a la rutina del usuario
        List<String> horarioCoach = new ArrayList<>();

        Coach Pedro = new Coach("Pedro", horarioCoach, 50.0, Coach.Especialidad.CARDIO, rutina.getEjercicios(), 60, "Media");
        // Mostrar horario del coach PEDRO 
        //System.out.println("\nHorario del Coach " + Pedro.getNombre() + ":");

        //las jornadas son de 12 horas, si trabajan medio dia trabajan 6 
        Pedro.getHorario().set(0, "Lunes AM");
        Pedro.getHorario().set(1, "Martes PM");
        Pedro.getHorario().set(2, "Miercoles AM");
        Pedro.getHorario().set(3, "Jueves AM");
        //Pedro.horarioCompleto(Pedro.getHorario(), Pedro.getNombre());

        Coach Juanita = new Coach("Juanita", horarioCoach, 80.0, Coach.Especialidad.YOGA, rutina.getEjercicios(), 66, "Alta");
        Juanita.getHorario().set(3, "Jueves PM");
        Juanita.getHorario().set(5, "Sábado AM");
        Juanita.getHorario().set(6, "Domingo PM");
        //Juanita.horarioCompleto(Juanita.getHorario(), Juanita.getNombre());

        Coach Byron = new Coach("Byron", horarioCoach, 200.0, Coach.Especialidad.FUERZA, rutina.getEjercicios(), 72, "Máxima");
        Byron.getHorario().set(0, "Lunes PM");
        Byron.getHorario().set(6, "Domingo AM");
        //Byron.horarioCompleto(Byron.getHorario(), Byron.getNombre());

        Coach Juan = new Coach("Juan", horarioCoach, 30.0, Coach.Especialidad.ZUMBA, rutina.getEjercicios(), 48, "Mínima");
        Juan.quitarDia("Lunes");
        Juan.getHorario().set(3, "Martes AM");
        Juan.getHorario().set(4, "Miercoles PM");
        Juan.getHorario().set(5, "Viernes PM");
        Juan.quitarDia("Domingo");

        //Juan.horarioCompleto(Juan.getHorario(), Juan.getNombre());
        // El coach añade un ejercicio a la rutina del usuario
        Pedro.agregarEjercicioARutina(rutina, "Bicicleta");

        // Imprimir la rutina actualizada
        System.out.println("\nRutina actualizada:");
        System.out.println(rutina.mostrarRutina());

        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("                 ¡REGISTRO COMPLETADO!             ");
        System.out.println("═══════════════════════════════════════════════════");
    }
}
