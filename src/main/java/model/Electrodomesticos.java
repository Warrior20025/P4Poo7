package model;

public class Electrodomesticos {

    private String descripcion;
    private int potencia;
    private boolean interruptor;

    public Electrodomesticos(String descripcion, int potencia) {
        this.descripcion = descripcion;
        this.potencia = potencia;
        interruptor = false;
    }

    public Electrodomesticos(String descripcion, int potencia, boolean interruptor) {
        this.descripcion = descripcion;
        this.potencia = potencia;
        this.interruptor = interruptor;
    }

    //getters

    public String getDescripcion() {
        return descripcion;
    }

    public int getPotencia() {
        return potencia;
    }

    public boolean isInterruptor() {
        return interruptor;
    }
}
