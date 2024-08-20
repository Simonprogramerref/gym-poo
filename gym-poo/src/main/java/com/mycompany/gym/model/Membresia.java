package com.mycompany.gym.model;

public class Membresia {
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
            precio *= 1.1; // 10% de aumento para usuarios con alta disponibilidad
        }
        return precio;
    }

    public String mostrarDetalles() {
        return "Membresía: " + tipo + ", Precio base: $" + precioBase;
    }

    public double getPrecioBase() {
        return precioBase;
    }
}
