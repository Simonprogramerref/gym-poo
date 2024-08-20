package com.mycompany.gym.model;

public class TarjetaCredito {
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

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }
}
