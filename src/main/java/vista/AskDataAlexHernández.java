/*
 * Clase auxiliar para pedir datos al usuario
 */
package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author WarriorDescens
 */
public class AskDataAlexHernández {

    // atributo para pedir datos al usuario
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Método para pedir una cadena
    // Método que recibe un mensaje a mostrar al pedir el dato al usuario
    // devuelve un Strig con la respuesta
    public static String askString(String msg) {
        System.out.print(msg);
        String answer = "";
        try {
            answer = br.readLine();
            while (answer.isEmpty()) {
                System.out.println();
                System.out.println("No puedes dejar la respuesta en blanco");
                System.out.println();
                System.out.print(msg);
                answer = br.readLine();



            }
        } catch (IOException ex) {
            System.out.println("Ha ocurrido un error inesperado.");
        }
        return answer;
    }

    public static String askString(String msg, int size) {
        String answer;
        do {
            answer = askString(msg);
            if (answer.length() != size) {
                System.out.println("The length of the string must be " + size);
            }
        }while(answer.length() != size);
        return answer;
    }

    public static String askString(String msg, ArrayList<String> stringsAccepted) {
        String answer;
        boolean ok = false;
        do {
            answer = askString(msg);
            ok = stringsAccepted.contains(answer.toLowerCase());
            if (!ok) {
                System.out.println("Wrong answer");
                System.out.println("Your answer must be: ");
                for (String s: stringsAccepted) {
                    System.out.println(s);
                }
            }
        }while(!ok);
        return answer;
    }

    // Método que recibe un mensaje a mostrar al pedir el dato al usuario
    // devuelve un número entero introducido por el usuario
    public static int askInt(String msg) {
        int n = 0;
        boolean error = false;
        do {
            try {
                System.out.print(msg);
                n = Integer.parseInt(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Se ha producido un error no esperado!!");
                error = true; // Este no se dará nunca.
            } catch (NumberFormatException e) {
                System.out.println("Tienes que poner un número entero");
                error = true;
            }
        } while (error);
        return n;
    }
    
    // Método para pedir un entero con un min
    // Recibe Mensaje para mostrar al usuario
    // recibe mensaje de error al mostrar si el número no cumple mínimo
    // recibe un int como valor mínimo
    // devuelve un entero mayor o igual que el mínimo
    public static int askInt(String msg, String errorMsg, int min) {
        int n = askInt(msg);
        while (n < min) {
            System.out.println(errorMsg);
            n = askInt(msg);
        }
        return n;
    }
    
    // Método análogo al anterior pero con min y max
    public static int askInt(String msg, String errorMsg, int min, int max) {
        int n = askInt(msg);
        while (n < min || n > max) {
            System.out.println(errorMsg);
            n = askInt(msg);
        }
        return n;
    }

    // Método que recibe un mensaje a mostrar al pedir el dato al usuario
    // devuelve un número entero introducido por el usuario
    public static double askDouble(String msg) {
        double n = 0;
        boolean error = false;
        do {
            try {
                System.out.print(msg);
                n = Double.parseDouble(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Se ha producido un error no esperado!!");
                error = true; // Este no se dará nunca.
            } catch (NumberFormatException e) {
                System.out.println("Tienes que poner un número entero");
                error = true;
            }
        } while (error);
        return n;
    }

    public static double askDouble(String msg, String errorMsg, double min) {
        double n = askDouble(msg);
        while (n < min) {
            System.out.println(errorMsg);
            n = askDouble(msg);
        }
        return n;
    }

    // Método análogo al anterior pero con min y max
    public static double askDouble(String msg, String errorMsg, double min, double max) {
        double n = askDouble(msg);
        while (n < min || n > max) {
            System.out.println(errorMsg);
            n = askDouble(msg);
        }
        return n;
    }

    public static boolean askBoolean(String msg, String errorMsg, String stringTrue, String stringFalse) {
        String answer;
        do {
            answer = askString(msg);
            if (!answer.equalsIgnoreCase(stringTrue) && !answer.equalsIgnoreCase(stringFalse)) {
                System.out.println(errorMsg);
            }
        } while (!answer.equalsIgnoreCase(stringTrue) && !answer.equalsIgnoreCase(stringFalse));
        return answer.equalsIgnoreCase(stringTrue);
    }

    public static String askNif(String msg) {
        String answer;
        boolean ok = false;
        do {
            answer = askString(msg);
            ok = validateNif(answer.toUpperCase());
            if (!ok) {
                System.out.println("Tienes que introducir un Nif correcto.");
            }
        } while (!ok);
        return answer;
    }

    private static boolean validateNif(String nif) {
        Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
        String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
        String[] INVALIDOS = new String[]{"00000000T", "00000001R", "99999999R"};
        return Arrays.binarySearch(INVALIDOS, nif) < 0 // <1>
                && REGEXP.matcher(nif).matches() // <2>
                && nif.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(nif.substring(0, 8)) % 23);
    }
}
