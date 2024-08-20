package com.mycompany.gym.poo;

import java.util.ArrayList;
import java.util.List;

public class GymPoo {

    static class Usuario {

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

        // Getters
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
    }

    static class Rutina {

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

    static class TarjetaCredito {

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

    static class Membresia {

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

    static class Coach {

        private String nombre;
        private ArrayList<String> horario;
        private double precio;
        private String tipoEntrenamiento;
        private Maquina maquinaAsociada;

        public Coach(String nombre, double precio, String tipoEntrenamiento) {
            this.nombre = nombre;
            this.precio = precio;
            this.horario = new ArrayList<>();
            this.tipoEntrenamiento = tipoEntrenamiento;
            this.maquinaAsociada = asignarMaquina(tipoEntrenamiento);
        }

        private Maquina asignarMaquina(String tipoEntrenamiento) {
            switch (tipoEntrenamiento.toLowerCase()) {
                case "cardio":
                    return new Maquina("Cinta de correr", "Cardio");
                case "fuerza":
                    return new Maquina("Banco de pesas", "Fuerza");
                case "flexibilidad":
                    return new Maquina("Máquina de estiramiento", "Flexibilidad");
                default:
                    return new Maquina("Máquina multifuncional", "General");
            }
        }

        public String getNombre() {
            return nombre;
        }

        public String getTipoEntrenamiento() {
            return tipoEntrenamiento;
        }

        public Maquina getMaquinaAsociada() {
            return maquinaAsociada;
        }

        public void agregarDia(String dia) {
            horario.add(dia);
        }

        public void mostrarHorario() {
            System.out.println("Horario de " + nombre + ":");
            for (String dia : horario) {
                System.out.println(dia);
            }
        }
    }

    static class Maquina {

        private String nombre;
        private String tipo;

        public Maquina(String nombre, String tipo) {
            this.nombre = nombre;
            this.tipo = tipo;
        }

        public String getNombre() {
            return nombre;
        }

        public String getTipo() {
            return tipo;
        }
    }

    static class Gimnasio {

        private List<Usuario> usuarios;
        private List<Rutina> rutinas;
        private List<Membresia> membresias;
        private List<Coach> coaches;

        public Gimnasio() {
            this.usuarios = new ArrayList<>();
            this.rutinas = new ArrayList<>();
            this.membresias = new ArrayList<>();
            this.coaches = new ArrayList<>();
        }

        public void agregarCoach(Coach coach) {
            coaches.add(coach);
        }

        public Coach asignarCoach(Usuario usuario, String nombreCoach) {
            for (Coach coach : coaches) {
                if (coach.getNombre().equalsIgnoreCase(nombreCoach)) {
                    System.out.println("Coach " + coach.getNombre() + " asignado a " + usuario.getNombre());
                    System.out.println("Tipo de entrenamiento: " + coach.getTipoEntrenamiento());
                    System.out.println("Máquina asignada: " + coach.getMaquinaAsociada().getNombre());
                    return coach;
                }
            }
            System.out.println("Coach no encontrado");
            return null;
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

    public static void main(String[] args) {
        Gimnasio gimnasio = new Gimnasio();

        // Crear usuario
        List<String> preferencias = new ArrayList<>();
        preferencias.add("Cardio");
        preferencias.add("Yoga");
        Usuario usuario = new Usuario("Juan", 30, 8, preferencias);

        // Registrar usuario
        gimnasio.registrarUsuario(usuario);

        // Crear y agregar coaches
        Coach simon = new Coach("Simon", 60.0, "Cardio");
        Coach maria = new Coach("Maria", 55.0, "Fuerza");
        gimnasio.agregarCoach(simon);
        gimnasio.agregarCoach(maria);

        // Asignar coach a usuario
        Coach coachAsignado = gimnasio.asignarCoach(usuario, "Simon");

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
