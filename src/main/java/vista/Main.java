package vista;


import model.Casa;
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

    private static void createHouse() throws IOException {
        String nif = AskDataAlexHernández.askNif("NIF del client propietari de la casa: ");
        if (casas.contains(new Casa(nif))) {
            System.out.println("Ja existeix una casa amb aquest NIF de propietari.");
        } else {
            String name = AskDataAlexHernández.askString("Nom del client: ");
            int supTeja = AskDataAlexHernández.askInt("Superfície de la teulada: ", "Digues una superfície correcta (min 10)", 10);
            Casa casa1 = new Casa(nif, name, supTeja);
            casas.add(casa1);
            file.writeClassInFile(casa1);
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