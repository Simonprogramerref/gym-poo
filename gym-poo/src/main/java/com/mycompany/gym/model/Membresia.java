package com.mycompany.gym.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Membresia {

    private String tipo;
    private double precioBase;
    private NumberFormat formatoMoneda;

    public Membresia(String tipo, double precioBase) {
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO")); // Formato para pesos colombianos
        this.formatoMoneda.setMaximumFractionDigits(0); // Sin decimales
    }

    public double calcularPrecio(Usuario usuario) {
        double precio = precioBase;
        if (usuario.getEdad() < 25 || usuario.getEdad() > 60) {
            precio *= 0.9; // 10% de descuento
            
            System.out.print("Si usted está fuera del rango de edad de 25 a 60, se le aplica un descuento del 10%");
            System.out.println("Por lo anterior, le queda en: " + formatoMoneda.format(precio));
        } else {
            precio *= 1.1; // 10% de aumento para usuarios con alta disponibilidad
            System.out.println("Por estar tan disponible para el entrenamiento, la tarifa tiende a incrementar \n");
            System.out.println("Por la alta disponibilidad le queda en: " + formatoMoneda.format(precio));
        }
        
        return precio; 
    }

    public String mostrarDetalles() {
        return "Membresía: " + tipo + ", Precio base: " + formatoMoneda.format(precioBase);
    }

    public double getPrecioBase() {
        return precioBase;
    }

    // Métodos específicos para cada tipo de membresía
    public boolean puedeEntrenar(String dia, int coachPermitidos) {
        switch (tipo) {
            case "SUPERDEVELOPED":
                return true; // Puede entrenar todos los días y escoger cualquier coach
            case "MEDIODEVELOP":
                return !dia.equalsIgnoreCase("Sábado") && !dia.equalsIgnoreCase("Domingo") && coachPermitidos <= 3;
            case "YOPROGRAMANDO":
                return !dia.equalsIgnoreCase("Lunes") && !dia.equalsIgnoreCase("Miércoles") && !dia.equalsIgnoreCase("Sábado") && coachPermitidos == 1;
            default:
                return false; // No permitido por defecto
        }
    }
}
