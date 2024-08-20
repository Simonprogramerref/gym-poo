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
            precio *= 0.01; // 10% de descuento
            
            
            System.out.print("si usted está entre el rango de edad de los 25 a los 60, se le aplica un descuento del 10%");
            System.out.println("Por lo anterior, le queda en: " + precio);
        }
        else {
            precio *= 1.1; // 10% de aumento para usuarios con alta disponibilidad
            System.out.println("por estar tan disponible para el entrenamiento, la tarifa tiende a incrementar \n");
            System.out.println("Por la alta disponibilidad le queda en: "+ precio);
        }
        
        return precio; 
    }

    public String mostrarDetalles() {
        return "Membresía: " + tipo + ", Precio base: $" + precioBase;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    // Métodos específicos para cada tipo de membresía
    public boolean puedeEntrenar(String dia, int coachPermitidos) {
        switch (tipo) {
            case "SUPERDELVELOPED":
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
