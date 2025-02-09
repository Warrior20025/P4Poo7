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


}
