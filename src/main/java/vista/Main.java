package vista;


import model.Casa;
import model.PlacaSolar;
import persistencia.ClassFile;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Casa> casas;
    private static ClassFile file;

    public static void main(String[] args)  {
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
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
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

    private static void createPlaca() throws IOException {     //creamos placa
        if (!emptyHouseList()) {
            System.out.println("*** Nova Placa Solar ***");
            String nifPlaca = AskDataAlexHernández.askNif("Nif del propietari de la casa: ");
            int indexCasa = casas.indexOf(new Casa(nifPlaca));  //con el int cogemos el indice de la casa que tiene ese nif que ha escrito el usuario y si existe nos devuelve un numero mayor o igual a 0 y si no existe devuelve -1
            if (indexCasa >= 0) {
                int superficiePlaca = AskDataAlexHernández.askInt("Superfície de la placa: ", "No pot posar un valor inferior a 1", 1);
                int potenciaPlaca = AskDataAlexHernández.askInt("Potència: ", "No pot posar un valor inferior a 1", 1);
                double preuPlaca = AskDataAlexHernández.askDouble("Preu de la placa: ", "No pots posar un valor inferior a 1", 1);
                Casa c = casas.get(indexCasa);
                if (c.cabePlaca(superficiePlaca)) {
                    PlacaSolar placa = new PlacaSolar(superficiePlaca, preuPlaca, potenciaPlaca);
                    c.añadirPlaca(placa);
                    file.writePlacaInFile(placa, c);
                    System.out.println("OK: Placa afegida a la casa.");
                }else {
                    System.out.println("La placa no cap a la casa.");
                }
            }else {
                System.out.println("No existeix cap casa amb el nif indicat.");
            }
        }
    }

    private static boolean emptyHouseList() {
        if (casas.isEmpty()) {
            System.out.println("No hi ha cap casa registrada.");
            return true;
        } else {
            return false;
        }
    }

    private static void createHouse() throws IOException {
        String nif = AskDataAlexHernández.askNif("NIF del client propietari de la casa: ");
        if (casas.contains(new Casa(nif))) {
            System.out.println("Ja existeix una casa amb aquest NIF de propietari.");
        } else {
            String name = AskDataAlexHernández.askString("Nom del client: ");
            int supTeja = AskDataAlexHernández.askInt("Superfície de la teulada: ", "Digues una superfície correcta (min 10)", 10); //todo en el enunciado que tiene que ser mas grande que 10 por tanto el minimo es 11
            Casa casa1 = new Casa(nif, name, supTeja);
            casas.add(casa1);
            file.writeCasaInFile(casa1);
        }
    }

    private static void menu() {
        System.out.println("=== Endolls Solars ===\n" +
                "1. Afegir casa.\n" +
                "2. Afegir placa.\n" +
                "3. Afegir aparell.\n" +
                "4. Encendre interruptor general de la casa.\n" +
                "5. Encentre un aparell.\n" +
                "6. Apagar un aparell.\n" +
                "7. Veure les cases.\n" +
                "8. Veure informació d’una casa.\n" +
                "9. Sortir.");
    }
}