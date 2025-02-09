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

    public Electrodomesticos getElectro(String description) {
        int indexElectro = electros.indexOf(new Electrodomesticos(description));
        Electrodomesticos e = electros.get(indexElectro);
        return e;
    }

    public boolean electroExists(String description) {
        int indexElectro = electros.indexOf(new Electrodomesticos(description));        //todo da -1 aunque existe el objeto preguntar
        if (indexElectro >= 0) {
            return true;
        }
        return false;
    }

    public boolean saltanPlomos() {
        if (potenciaElectros() > potenciaPlacas()) {
            for (Electrodomesticos e: electros) {
                e.setInterruptor(false);
            }
            setInterruptor(false);
            return true;
        }
        return false;
    }

    private int potenciaPlacas() {
        int sumaPotencia = 0;
        for (PlacaSolar p: placas) {
            sumaPotencia += p.getPotencia();
        }
        return sumaPotencia;
    }

    private int potenciaElectros() {
        int sumaPotencia = 0;
        for (Electrodomesticos e: electros) {
            if (e.isInterruptor()) {
                sumaPotencia += e.getPotencia();
            }
        }
        return sumaPotencia;
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
        for (PlacaSolar p: placas) {
            sTejado -= p.getSuperficie();
        }
        return sTejado;
    }

    public boolean isInterruptor() {
        return interruptor;
    }

    //setters
    public void setInterruptor(boolean interruptor) {
        this.interruptor = interruptor;
    }

    public String showList() {
        String listaDeCaracteres = "Client: " + nif +
                " - " + nombre +
                "\nPlaques solars instal·lades: " + placas.size() +
                "\nPotència total: ";
        int num = 0;
        int potenciaP = 0;
        double inversion = 0;
        for (PlacaSolar p: placas) {
            num ++;
            potenciaP += p.getPotencia();
            inversion += p.getPrecio();
        }
        listaDeCaracteres += potenciaP + "\nInversió total: " + inversion + "€" +
        "\nAparells registrats: " + electros.size();

        if (hayEncendidos() && electros.size() > 0) {
            int consum = 0;
            String descriptions = "";
            for (Electrodomesticos e: electros) {
                if (e.isInterruptor()) {
                    consum += e.getPotencia();
                    descriptions += "\n\t\t- " + e.getDescripcion();
                }
            }
            listaDeCaracteres += "\nConsum actual: " + consum + "W\nAparells encesos:" + descriptions;
        }else {
            listaDeCaracteres += "\nConsum actual: 0W";
        }
        return listaDeCaracteres;
    }

    private boolean hayEncendidos() {
        for (Electrodomesticos e: electros) {
            if (e.isInterruptor()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String atributos = "Client: " + nif +
                " - " + nombre +
                "\nSuperfície de teulada: " + sTejado +
                "\nSuperfície disponible: " + getsTejado() + "\n";
        if (interruptor) {
            atributos += "Interruptor general: encès";
        }else {
            atributos += "Interruptor general: apagat";
        }

        atributos += "\n";
        if (placas.size() > 0) {
            atributos += "Plaques solars instalades: " + placas.size();
        }else {
            atributos += "No té plaques solars instal·lades.";
        }

        atributos += "\n";
        if (electros.size() > 0) {
            atributos += "Aparells registrats: " + electros.size();
        }else {
            atributos += "No té aparell elèctric registrat.";
        }

        return atributos;
    }
}
