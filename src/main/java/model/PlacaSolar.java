package model;

public class PlacaSolar {
    private int superficie;
    private double precio;
    private int potencia;

    public PlacaSolar(int superficie, double precio, int potencia) {
        this.superficie = superficie;
        this.precio = precio;
        this.potencia = potencia;
    }

    public int getSuperficie() {
        return superficie;
    }

    public double getPrecio() {
        return precio;
    }

    public int getPotencia() {
        return potencia;
    }
}
