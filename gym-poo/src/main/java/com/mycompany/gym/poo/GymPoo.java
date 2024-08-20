/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.gym.poo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebas
 */
public class GymPoo {

    class Usuario {

        private String nombre;
        private int edad;
        private int disponibilidad; // en horas por semana
        private List<String> preferencias;

        public Usuario(String nombre, int edad, int disponibilidad, List<String> preferencias) {
            this.nombre = nombre;
            this.edad = edad;
            this.disponibilidad = disponibilidad;
            this.preferencias = preferencias;
        }

        // Getters y setters
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

        //creamos una funcion para asignar Coach
        public void asignarCoach(Coach coach) {
            if (this.preferencias.contains(coach.getEspecialidad().toString())) {
                System.out.println("Señor/Señora " + this.nombre + ", se le ha asignado el coach " + coach.getNombre()
                        + " con especialidad en " + coach.getEspecialidad());
            } else {
                System.out.println("No se encontró un coach que coincida con las preferencias de " + this.nombre);
            }
        }

        class Rutina {

            private List<String> ejercicios;
            private int duracion;
            private String intensidad;

            public Rutina(List<String> ejercicios, int duracion, String intensidad) {
                this.ejercicios = ejercicios;
                this.duracion = duracion;
                this.intensidad = intensidad;
            }

            public void generarRutina(Usuario usuario) {
                this.ejercicios = new ArrayList<>();
                if (usuario.getDisponibilidad() < 5) {
                    this.duracion = 30;
                    this.intensidad = "Baja";
                    this.ejercicios.add("Caminata");
                    this.ejercicios.add("Estiramientos");
                } else if (usuario.getDisponibilidad() < 10) {
                    this.duracion = 45;
                    this.intensidad = "Media";
                    this.ejercicios.add("Trote");
                    this.ejercicios.add("Pesas ligeras");
                } else {
                    this.duracion = 60;
                    this.intensidad = "Alta";
                    this.ejercicios.add("Carrera");
                    this.ejercicios.add("Pesas pesadas");
                }
            }

            public String mostrarRutina() {
                return "Rutina: " + ejercicios + ", Duración: " + duracion + " minutos, Intensidad: " + intensidad;
            }
        }

        class TarjetaCredito {

            private String numero;
            private String titular;
            private double limite;

            public TarjetaCredito(String numero, String titular, double limite) {
                this.numero = numero;
                this.titular = titular;
                this.limite = limite;
            }

            public boolean tieneLimite(double monto) {
                return limite >= monto;
            }

            // Getters
            public String getNumero() {
                return numero;
            }

            public String getTitular() {
                return titular;
            }
        }

        class Membresia {

            private String tipo;
            private double precioBase;

            public Membresia(String tipo, double precioBase) {
                this.tipo = tipo;
                this.precioBase = precioBase;
            }

            public double calcularPrecio(Usuario usuario) {
                double precio = precioBase;
                if (usuario.getEdad() < 25 || usuario.getEdad() > 60) {
                    precio *= 0.9; // 10% de descuento
                }
                if (usuario.getDisponibilidad() > 10) {
                    precio *= 1.1; // 10% de aumento para usuarios que usan más el gimnasio
                }
                return precio;
            }

            public String mostrarDetalles() {
                return "Membresía: " + tipo + ", Precio base: $" + precioBase;
            }

            // Getter
            public double getPrecioBase() {
                return precioBase;
            }
        }

        class Gimnasio {

            private List<Usuario> usuarios;
            private List<Rutina> rutinas;
            private List<Membresia> membresias;

            public Gimnasio() {
                this.usuarios = new ArrayList<>();
                this.rutinas = new ArrayList<>();
                this.membresias = new ArrayList<>();
            }

            public void registrarUsuario(Usuario usuario) {
                usuarios.add(usuario);
            }

            public Rutina asignarRutina(Usuario usuario) {
                Rutina rutina = new Rutina(new ArrayList<>(), 0, "");
                rutina.generarRutina(usuario);
                rutinas.add(rutina);
                return rutina;
            }

            public double procesarPago(Usuario usuario, Membresia membresia, double presupuesto) {
                double precio = membresia.calcularPrecio(usuario);
                if (presupuesto >= precio) {
                    System.out.println("Pago completo procesado para " + usuario.getNombre() + ": $" + precio);
                    return precio;
                } else {
                    System.out.println("Presupuesto insuficiente. Se requiere pago con tarjeta de crédito.");
                    return 0;
                }
            }

            public void procesarPagoConTarjeta(Usuario usuario, Membresia membresia, TarjetaCredito tarjeta, int cuotas) {
                double precio = membresia.calcularPrecio(usuario);
                if (tarjeta.tieneLimite(precio)) {
                    double montoCuota = precio / cuotas;
                    System.out.println("Pago con tarjeta procesado para " + usuario.getNombre() + ":");
                    System.out.println("Total: $" + precio);
                    System.out.println("Número de cuotas: " + cuotas);
                    System.out.println("Monto por cuota: $" + String.format("%.2f", montoCuota));
                } else {
                    System.out.println("La tarjeta no tiene suficiente límite para procesar el pago.");
                }
            }
        }

        class Coach extends Rutina {

            private String nombre;
            public ArrayList<String> horario; // Cambio a ArrayList<String>
            private double precio;
            private Especialidad especialidad;

            public enum Especialidad {
                CARDIO,
                YOGA,
                ZUMBA,
                FUERZA,
            }

            Coach coach = new Coach("Nombre del Coach", horario, precio, Coach.Especialidad.CARDIO, ejercicios, duracion, intensidad);

            usuario.asignarCoach (coach);

            // Coach Jorge_coach = Coach(
            // Constructor
            public Coach(String nombre, <any>  horario, double precio, Especialidad especialidad, <any>  ejercicios, int duracion, String intensidad) {
                super(ejercicios, duracion, intensidad);
                this.nombre = nombre;
                this.horario = horario;
                this.precio = precio;
                this.especialidad = especialidad;
            }

        }
// creo un geter para especialidad

        public Especialidad getEspecialidad() {
            return especialidad;
        }

        // Método para agregar un día al horario
        public void agregarDia(String dia) {
            horario.add(dia);
        }

        // Método para mostrar el horario completo
        public void mostrarHorario() {
            System.out.println("Horario de " + nombre + ":");
            for (String dia : horario) {
                System.out.println(dia);
            }
        }

        // Método principal para probar la clase Coach
        public static void main(String[] args) {
            Coach coach = new Coach("Sebastian", 50.0);

            // Agregar días al horario
            coach.agregarDia("Lunes");
            coach.agregarDia("Miércoles");
            coach.agregarDia("Viernes");

            // Mostrar el horario del coach
            coach.mostrarHorario();
        }
    }

    public class Main {

        public static void main(String[] args) {
            Gimnasio gimnasio = new Gimnasio();

            // Crear usuario
            List<String> preferencias = new ArrayList<>();
            preferencias.add("Cardio");
            preferencias.add("Yoga");
            Usuario usuario = new Usuario("Juan", 30, 8, preferencias);

            // Registrar usuario
            gimnasio.registrarUsuario(usuario);

            // Asignar rutina
            Rutina rutina = gimnasio.asignarRutina(usuario);
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
}

// Comentario Simon
