package model;

import java.util.ArrayList;

public class Casa {
    private String nif;
    private String nombre;
    private int sTejado;
    private boolean interruptor;
    private ArrayList<PlacaSolar> placas;
    private ArrayList<Electrodomesticos> electros;

    //constructors
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

    //methods
    public void añadirPlaca(PlacaSolar p) {     //add panel to the arraylist of the house method
        placas.add(p);
    }

    public void añadirElectro(Electrodomesticos e) {    //add appliance to the arraylist of the class method
        electros.add(e);
    }

    public Electrodomesticos getElectro(String description) {       //method to return the object
        int indexElectro = electros.indexOf(new Electrodomesticos(description));
        Electrodomesticos e = electros.get(indexElectro);
        return e;
    }

    public boolean electroExists(String description) {      //method to check if the object exists
        int indexElectro = electros.indexOf(new Electrodomesticos(description));
        if (indexElectro >= 0) {
            return true;
        }
        return false;
    }

    public boolean saltanPlomos() {     //check if the power of the appliances is higher of the solar panels income power and turn off all in that case method
        if (potenciaElectros() > potenciaPlacas()) {
            for (Electrodomesticos e: electros) {
                e.setInterruptor(false);
            }
            interruptor = false;
            return true;
        }
        return false;
    }

    private int potenciaPlacas() {      //take all the power income of the solar panels method
        int sumaPotencia = 0;
        for (PlacaSolar p: placas) {
            sumaPotencia += p.getPotencia();
        }
        return sumaPotencia;
    }

    private int potenciaElectros() {        //get all the power use of the appliances method
        int sumaPotencia = 0;
        for (Electrodomesticos e: electros) {
            if (e.isInterruptor()) {
                sumaPotencia += e.getPotencia();
            }
        }
        return sumaPotencia;
    }

    public boolean cabePlaca(int superficiePlaca) {     //check if the solar panel fits in the roof of the house method
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

    public int getsTejadoDisp() {       //get the surface that's free method
        for (PlacaSolar p: placas) {
            sTejado -= p.getSuperficie();
        }
        return sTejado;
    }

    public boolean isInterruptor() {
        return interruptor;
    }

    public ArrayList<PlacaSolar> getPlacas() {
        return placas;
    }

    public ArrayList<Electrodomesticos> getElectros() {
        return electros;
    }

    //setters
    public void turnOnHouse() {
        interruptor = true;
    }

    //methods
    public String showList() {      //show the specific house method in the class to use it's information method
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

    private boolean hayEncendidos() {       //check if there's any appliance on method
        for (Electrodomesticos e: electros) {
            if (e.isInterruptor()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {      //override of "toString" method
        String atributos = "Client: " + nif +
                " - " + nombre +
                "\nSuperfície de teulada: " + sTejado +
                "\nSuperfície disponible: " + getsTejadoDisp() + "\n";
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
