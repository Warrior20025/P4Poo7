package model;

import java.util.ArrayList;

public class Casa {
    private String nif;
    private String nombre;
    private int sTejado;
    private boolean interruptor;
    private ArrayList<PlacaSolar> placas;
    private ArrayList<Electrodomesticos> electros;

    public Casa(String nif) {
        this.nif = nif;
    }

    public Casa(String nif, String nombre, int sTejado) {
        this.nif = nif;
        this.nombre = nombre;
        this.sTejado = sTejado;
        interruptor = true;
        placas = new ArrayList<>();
        electros = new ArrayList<>();
    }

    public Casa(String nif, String nombre, int sTejado, boolean inter) {
        this.nif = nif;
        this.nombre = nombre;
        this.sTejado = sTejado;
        this.interruptor = inter;
        placas = new ArrayList<>();
        electros = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        Casa p = (Casa) obj;
        return this.nif.equalsIgnoreCase(p.getNif());
    }

    public void añadirPlaca(PlacaSolar p) {
        placas.add(p);
    }

    public void añadirElectro(Electrodomesticos e) {
        electros.add(e);
    }

    public boolean cabePlaca(int superficiePlaca) {
        int suma = sTejado;
        for (PlacaSolar p: placas) {
            suma -= p.getSuperficie();
        }
        return suma >= superficiePlaca;
    }

    //getters
    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public int getsTejado() {
        return sTejado;
    }

    public boolean isInterruptor() {
        return interruptor;
    }

    //setters
    public void setInterruptor(boolean interruptor) {
        this.interruptor = interruptor;
    }

    @Override
    public String toString() {
        String atributos = "Casa{" +
                "nif='" + nif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sTejado=" + sTejado;
        if (interruptor) {
            atributos += ", interruptor= encendido}";
        }else {
            atributos += ", interruptor= apagado}";
        }
        return atributos;
    }
}
