package com.mycompany.gym.model;

public class TarjetaCredito {

    private String numero;
    private String titular;
    private double limite;
    private boolean tarjeta;

    public TarjetaCredito(String numero, String titular, double limite) {
        this.numero = numero;
        this.titular = titular;
        this.limite = limite;
    }
    
     public TarjetaCredito(String numero, String titular) {
        this.numero = numero;
        this.titular = titular;
    }
     
    public TarjetaCredito(){};
    
    public TarjetaCredito(boolean tarjeta){
        this.tarjeta= tarjeta;
    }
    
        public TarjetaCredito(boolean tarjeta, double limite){
        this.tarjeta= tarjeta;
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
    
    //setter para la tarjeta  y getter

    public void setTarjeta(boolean tarjeta) {
        this.tarjeta = tarjeta;
    }

    public boolean isTarjeta() {
        return tarjeta;
    }
    
     //setter para la limite y getter 

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
    
    
    
  
    
    
    
}


