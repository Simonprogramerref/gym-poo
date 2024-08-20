import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebas
 */
public class GymPoo {

    // Clase interna Usuario
    class Usuario {
        private String nombre;
        private int edad;
        private int disponibilidad; // Disponibilidad en horas por semana
        private List<String> preferencias;

        // Constructor
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

        // Método para asignar un Coach al usuario basado en sus preferencias
        public void asignarCoach(Coach coach) {
            if (this.preferencias.contains(coach.getEspecialidad().name())) {
                System.out.println("Señor/Señora " + this.nombre + ", se le ha asignado el coach " + coach.getNombre()
                        + " con especialidad en " + coach.getEspecialidad());
            } else {
                System.out.println("No se encontró un coach que coincida con las preferencias de " + this.nombre);
            }
        }
    }

    // Clase interna Rutina
    class Rutina {
        private List<String> ejercicios;
        private int duracion; // Duración en minutos
        private String intensidad;

        // Constructor
        public Rutina(List<String> ejercicios, int duracion, String intensidad) {
            this.ejercicios = ejercicios;
            this.duracion = duracion;
            this.intensidad = intensidad;
        }

        // Genera una rutina basada en la disponibilidad del usuario
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

        // Permite agregar un ejercicio adicional a la rutina
        public void agregarEjercicio(String ejercicio) {
            ejercicios.add(ejercicio);
        }

        // Muestra la rutina generada
        public String mostrarRutina() {
            return "Rutina: " + ejercicios + ", Duración: " + duracion + " minutos, Intensidad: " + intensidad;
        }

        // Devuelve la lista de ejercicios
        public List<String> getEjercicios() {
            return ejercicios;
        }
    }

    // Clase interna TarjetaCredito
    class TarjetaCredito {
        private String numero;
        private String titular;
        private double limite;

        // Constructor
        public TarjetaCredito(String numero, String titular, double limite) {
            this.numero = numero;
            this.titular = titular;
            this.limite = limite;
        }

        // Verifica si la tarjeta tiene suficiente límite para un monto dado
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

    // Clase interna Membresia
    class Membresia {
        private String tipo;
        private double precioBase;

        // Constructor
        public Membresia(String tipo, double precioBase) {
            this.tipo = tipo;
            this.precioBase = precioBase;
        }

        // Calcula el precio final de la membresía basado en la edad y disponibilidad del usuario
        public double calcularPrecio(Usuario usuario) {
            double precio = precioBase;
            if (usuario.getEdad() < 25 || usuario.getEdad() > 60) {
                precio *= 0.9; // 10% de descuento
            }
            if (usuario.getDisponibilidad() > 10) {
                precio *= 1.1; // 10% de aumento para usuarios con alta disponibilidad
            }
            return precio;
        }

        // Muestra los detalles de la membresía
        public String mostrarDetalles() {
            return "Membresía: " + tipo + ", Precio base: $" + precioBase;
        }

        // Getter
        public double getPrecioBase() {
            return precioBase;
        }
    }

    // Clase interna Gimnasio
    class Gimnasio {
        private List<Usuario> usuarios;
        private List<Rutina> rutinas;
        private List<Membresia> membresias;

        // Constructor
        public Gimnasio() {
            this.usuarios = new ArrayList<>();
            this.rutinas = new ArrayList<>();
            this.membresias = new ArrayList<>();
        }

        // Registra un nuevo usuario en el gimnasio
        public void registrarUsuario(Usuario usuario) {
            usuarios.add(usuario);
        }

        // Asigna una rutina a un usuario basado en su disponibilidad
        public Rutina asignarRutina(Usuario usuario) {
            Rutina rutina = new Rutina(new ArrayList<>(), 0, "");
            rutina.generarRutina(usuario);
            rutinas.add(rutina);
            return rutina;
        }

        // Procesa el pago de una membresía si el presupuesto es suficiente
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

        // Procesa el pago con tarjeta de crédito en cuotas
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

    // Clase interna Coach, que hereda de Rutina
    class Coach extends Rutina {
        private String nombre;
        private List<String> horario; // Cambio a List<String>
        private double precio;
        private Especialidad especialidad;

        // Enum para las especialidades del Coach
        public enum Especialidad {
            CARDIO,
            YOGA,
            ZUMBA,
            FUERZA,
        }

        // Constructor
        public Coach(String nombre, List<String> horario, double precio, Especialidad especialidad, List<String> ejercicios, int duracion, String intensidad) {
            super(ejercicios, duracion, intensidad);
            this.nombre = nombre;
            this.horario = horario;
            this.precio = precio;
            this.especialidad = especialidad;
        }

        // Getters y métodos adicionales
        public Especialidad getEspecialidad() {
            return especialidad;
        }

        public String getNombre() {
            return nombre;
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

        // Método para que el coach agregue un ejercicio a la rutina de un usuario
        public void agregarEjercicioARutina(Rutina rutina, String ejercicio) {
            rutina.agregarEjercicio(ejercicio);
            System.out.println("Ejercicio " + ejercicio + " añadido a la rutina de " + rutina.getEjercicios());
        }
    }

    // Clase principal Main para ejecutar el programa
    public static void main(String[] args) {
        GymPoo gym = new GymPoo();
        Gimnasio gimnasio = gym.new Gimnasio();

        // Crear usuario
        List<String> preferencias = new ArrayList<>();
        preferencias.add("CARDIO");
        preferencias.add("YOGA");
        Usuario usuario = gym.new Usuario("Juan", 30, 8, preferencias);

        // Registrar usuario
        gimnasio.registrarUsuario(usuario);

        // Asignar rutina
        Rutina rutina = gimnasio.asignarRutina(usuario);
        System.out.println(rutina.mostrarRutina());

        // Crear Coach y agregar un ejercicio a la rutina del usuario
        List<String> horarioCoach = new ArrayList<>();
        horarioCoach.add("Lunes");
        horarioCoach.add("Miércoles");
        Coach coach = gym.new Coach("Pedro", horarioCoach, 50.0, Coach.Especialidad.CARDIO, rutina.getEjercicios(), 45, "Media");

        // Mostrar horario del coach
        coach.mostrarHorario();

        // El coach añade un ejercicio a la rutina del usuario
        coach.agregarEjercicioARutina(rutina, "Bicicleta");

        // Imprimir la rutina actualizada
        System.out.println(rutina.mostrarRutina());

        // Crear membresía
        Membresia membresia = gym.new Membresia("Estándar", 50.0);

        // Intentar procesar pago con presupuesto insuficiente
        double presupuesto = 40.0;
        double precioFinal = gimnasio.procesarPago(usuario, membresia, presupuesto);

        // Si el presupuesto es insuficiente, procesar pago con tarjeta de crédito
        if (precioFinal == 0) {
            TarjetaCredito tarjeta = gym.new TarjetaCredito("1234-5678-9012-3456", "Juan Pérez", 1000.0);
            gimnasio.procesarPagoConTarjeta(usuario, membresia, tarjeta, 3);
        }
    }
}