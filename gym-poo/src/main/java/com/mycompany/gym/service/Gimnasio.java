package com.mycompany.gym.service;

import com.mycompany.gym.model.Usuario;
import com.mycompany.gym.model.Rutina;
import com.mycompany.gym.model.Membresia;
import com.mycompany.gym.model.TarjetaCredito;
import com.mycompany.gym.model.Coach;

import java.util.ArrayList;
import java.util.List;

public class Gimnasio {

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
