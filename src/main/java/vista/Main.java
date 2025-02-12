package vista;

//imports
import model.Casa;
import model.Electrodomesticos;
import model.PlacaSolar;
import persistencia.ClassFile;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Casa> casas;
    private static ClassFile file;

    public static void main(String[] args)  {       //main method
        casas = new ArrayList<>();
        try {
            file = new ClassFile();
            casas = file.readClass();
            int opcion;
            do {
                menu();
                opcion = AskDataAlexHernández.askInt("Què vols fer? ", "Digues una opció correcta.", 1, 9);
                switch (opcion) {
                    case 1:
                        createHouse();
                        break;
                    case 2:
                        createPlaca();
                        break;
                    case 3:
                        createElectro();
                        break;
                    case 4:
                        turnOnHouse();
                        break;
                    case 5:
                        turnOnElectros();
                        break;
                    case 6:
                        turnOffElectros();
                        break;
                    case 7:
                        showHouses();
                        break;
                    case 8:
                        showTheHouse();
                        break;
                    case 9:
                        System.out.println("Sortint !!!");
                        break;
                }
            } while (opcion != 9);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void showHouses() {      //show all the houses method
        if (!emptyHouseList()) {
            System.out.println("--- Endolls Solars, S.L. ---" +
                    "\nCases enregistrades: " + casas.size());
            int num = 1;
            for (Casa c: casas) {
                System.out.println("\nCasa " + num);
                System.out.println(c);
                num ++;
            }
        }
    }

    private static void showTheHouse() {        //show a specific house method
        if (!emptyHouseList()) {
            int i = preguntarnif();
            if (i >= 0) {
                Casa c = casas.get(i);
                System.out.println(c.showList());
            }
        }
    }

    private static void turnOffElectros() throws IOException {     //turn off appliance with a certain description method
        if (!emptyHouseList()) {
            System.out.println("*** Apagar Aparell ***");
            int i = preguntarnif();
            if (i >= 0) {
                Casa c = casas.get(i);
                if (c.isInterruptor()) {
                    String descripElectro = AskDataAlexHernández.askString("Descripció de l'aparell: ");
                    if (c.electroExists(descripElectro)) {
                        Electrodomesticos electro = c.getElectro(descripElectro);
                        if (electro.isInterruptor()) {
                            electro.setInterruptor(false);
                            System.out.println("OK: Aparell apagat.");
                            file.reWriteElectrosInFile(casas);
                        }else {
                            System.out.println("L'aparell ja estava apagat.");
                        }
                    }else {
                        System.out.println("No hi ha cap aparell amb aquesta descripció.");
                    }
                }else {
                    System.out.println("La casa está apagada.");
                }
            }
        }
    }

    private static void turnOnElectros() throws IOException {      //turn on an appliance with a certain description method
        if (!emptyHouseList()) {
            System.out.println("*** Encendre Aparell ***");
            int i = preguntarnif();
            if (i >= 0) {
                Casa c = casas.get(i);
                if (c.isInterruptor()) {
                    String descripElectro = AskDataAlexHernández.askString("Descripció de l'aparell: ");
                    if (c.electroExists(descripElectro)) {
                        Electrodomesticos electro = c.getElectro(descripElectro);
                        if (electro.isInterruptor()) {
                            System.out.println("L'aparell ja estava encès.");
                        }else {
                            electro.setInterruptor(true);
                            System.out.println("OK: Aparell encès.");
                            file.reWriteElectrosInFile(casas);
                            if (c.saltanPlomos()) {
                                System.out.println("Han saltat els ploms. La casa ha quedat completament apagada.");
                                file.reWriteCasasInFile(casas);
                            }
                        }
                    }else {
                        System.out.println("No hi ha cap aparell amb aquesta descripció.");
                    }
                }else {
                    System.out.println("La casa está apagada.");
                }
            }
        }
    }

    private static void turnOnHouse() throws IOException {     //thurn on the house asked method
        if (!emptyHouseList()) {
            System.out.println("*** Encendre Casa ***");
            int i = preguntarnif();
            if (i >= 0) {
                Casa c = casas.get(i);
                if (c.isInterruptor()) {
                    System.out.println("La casa ja està encesa.");
                }else {
                    c.setInterruptor(true);
                    System.out.println("OK: Interruptor general activat.");
                    file.reWriteCasasInFile(casas);
                }
            }
        }
    }

    private static void createElectro() throws IOException {        //create appliance
        if (!emptyHouseList()) {
            System.out.println("*** Nou Aparell ***");
            int i = preguntarnif();
            if (i >= 0) {
                String descripElectro = AskDataAlexHernández.askString("Descripció de l'aparell: ");
                int potenciaElectro = AskDataAlexHernández.askInt("Potència: ", "No pot posar un valor inferior a 1", 1);
                Casa c = casas.get(i);
                Electrodomesticos electro = new Electrodomesticos(descripElectro, potenciaElectro);
                c.añadirElectro(electro);
                file.writeElectroInFile(electro, c);
                System.out.println("OK: Aparell afegit a la casa.");
            }
        }
    }

    private static int preguntarnif() {
        String nifElectro = AskDataAlexHernández.askNif("Nif del propietari de la casa: ");
        int indexCasa = casas.indexOf(new Casa(nifElectro));  //con el int cogemos el indice de la casa que tiene ese nif que ha escrito el usuario y si existe nos devuelve un numero mayor o igual a 0 y si no existe devuelve -1
        if (indexCasa < 0) {
            System.out.println("No existeix cap casa amb el nif indicat.");
        }
        return indexCasa;
    }

    private static void createPlaca() throws IOException {     //create solar panel method
        if (!emptyHouseList()) {
            System.out.println("*** Nova Placa Solar ***");
            int i = preguntarnif();
            if (i >= 0) {
                int superficiePlaca = AskDataAlexHernández.askInt("Superfície de la placa: ", "No pot posar un valor inferior a 1", 1);
                Casa c = casas.get(i);
                if (c.cabePlaca(superficiePlaca)) {
                    int potenciaPlaca = AskDataAlexHernández.askInt("Potència: ", "No pot posar un valor inferior a 1", 1);
                    double preuPlaca = AskDataAlexHernández.askDouble("Preu de la placa: ", "No pots posar un valor inferior a 1", 1);
                    PlacaSolar placa = new PlacaSolar(superficiePlaca, preuPlaca, potenciaPlaca);
                    c.añadirPlaca(placa);
                    file.writePlacaInFile(placa, c);
                    System.out.println("OK: Placa afegida a la casa.");
                }else {
                    System.out.println("La placa no cap a la casa.");
                }
            }
        }
    }

    private static boolean emptyHouseList() {       //checks if the list is empty
        if (casas.isEmpty()) {
            System.out.println("No hi ha cap casa registrada.");
            return true;
        } else {
            return false;
        }
    }

    private static void createHouse() throws IOException {      //create house method
        String nif = AskDataAlexHernández.askNif("NIF del client propietari de la casa: ");
        if (casas.contains(new Casa(nif))) {
            System.out.println("Ja existeix una casa amb aquest NIF de propietari.");
        } else {
            String name = AskDataAlexHernández.askString("Nom del client: ");
            int supTeja = AskDataAlexHernández.askInt("Superfície de la teulada: ", "Digues una superfície correcta (min 10)", 11);
            Casa casa1 = new Casa(nif, name, supTeja);
            casas.add(casa1);
            file.writeCasaInFile(casa1);
            System.out.println("OK: Casa registrada.");
        }
    }

    private static void menu() {        //menu output
        System.out.println("\n=== Endolls Solars ===\n" +
                "1. Afegir casa.\n" +
                "2. Afegir placa.\n" +
                "3. Afegir aparell.\n" +
                "4. Encendre casa.\n" +
                "5. Encentre aparell.\n" +
                "6. Apagar aparell.\n" +
                "7. Veure les cases.\n" +
                "8. Veure informació d’una casa.\n" +
                "9. Sortir.");
    }
}