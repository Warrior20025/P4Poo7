package persistencia;

import model.Casa;
import model.PlacaSolar;
import model.Electrodomesticos;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ClassFile {
    ClassFile file = new ClassFile();
    String casasFile = "cases.txt";
    String placasFile = "plaques.txt";
    String electrosFile = "aparells.txt";
    String nameFolder = "dades";
    String folderPath = "." + File.separator + nameFolder;
    String casasPath = folderPath + File.separator + casasFile;
    String placasPath = folderPath + File.separator + placasFile;
    String electrosPath = folderPath + File.separator + electrosFile;

    public ClassFile() throws IOException {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File casas = new File(casasPath);
        if (!casas.exists()) {
            casas.createNewFile();
        }
        File placas = new File(placasPath);
        if (!placas.exists()) {
            placas.createNewFile();
        }
        File electros = new File(electrosPath);
        if (!electros.exists()) {
            electros.createNewFile();
        }
    }

    //writings
    public void writeCasaInFile(Casa c) throws IOException {        //write house in file method
        BufferedWriter writer = new BufferedWriter(new FileWriter(casasPath, true));
        writer.write(c.getNif() + "-" + c.getNombre() + "-" + c.getsTejado() + "-" + c.isInterruptor());
        writer.newLine();
        writer.close();
    }

    public void writePlacaInFile(PlacaSolar c, Casa s) throws IOException {     //write placa in file method
        BufferedWriter writer = new BufferedWriter(new FileWriter(placasPath, true));
        writer.write( s.getNif() + "-" + c.getSuperficie() + "-" + c.getPrecio() + "-" + c.getPotencia());
        writer.newLine();
        writer.close();
    }

    public void writeElectroInFile(Electrodomesticos c, Casa s) throws IOException {        //write appliance in file method
        BufferedWriter writer = new BufferedWriter(new FileWriter(electrosPath, true));
        writer.write(s.getNif() + "-" + c.getDescripcion() + "-" + c.getPotencia() + "-" + c.isInterruptor());
        writer.newLine();
        writer.close();
    }

    //rewrite
    public void reWriteElectrosInFile(ArrayList<Casa> casas) throws IOException {     //method to reWrite the solar panels objects in the text files
        BufferedWriter reWriterPlaca = new BufferedWriter(new FileWriter(electrosPath, false));
        for (Casa i : casas) {
            for (Electrodomesticos p: i.getElectros()) {
                file.writeElectroInFile(p, i);
            }
        }
        reWriterPlaca.close();
    }

    public void reWriteCasasInFile(ArrayList<Casa> casas) throws IOException {      //method to reWrite the houses objects in the text files
        BufferedWriter reWriterCasa = new BufferedWriter(new FileWriter(casasPath, false));
        for (Casa i : casas) {
            file.writeCasaInFile(i);
        }
        reWriterCasa.close();
    }

    //reading
    public ArrayList<Casa> readClass() throws IOException {     //reading from files method
        ArrayList<Casa> casas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(casasPath));
        String line;
        while((line = reader.readLine()) != null) {     //casas
            String[] data = line.split("-");
            String nif = data[0];
            String nombre = data[1];
            int sTejado = Integer.parseInt(data[2]);
            boolean inter = Boolean.parseBoolean(data[3]);      //interruptor
            Casa newHouse = new Casa(nif, nombre, sTejado, inter);
            casas.add(newHouse);
        }
        reader.close();
        reader = new BufferedReader(new FileReader(placasPath));
        while((line = reader.readLine()) != null) {        //placas
            String[] data = line.split("-");
            String nifPlaca = data[0];
            int superficie = Integer.parseInt(data[1]);
            double precio = Double.parseDouble(data[2]);
            int potencia = Integer.parseInt(data[3]);
            PlacaSolar newPlaca = new PlacaSolar(superficie, precio, potencia);
            int indexCasa = casas.indexOf(new Casa(nifPlaca));  //con el int cogemos el indice de la casa que tiene ese nif que ha escrito el usuario y si existe nos devuelve un numero mayor o igual a 0 y si no existe devuelve -1
            if (indexCasa >= 0) {
                Casa c = casas.get(indexCasa);
                c.añadirPlaca(newPlaca);
            }
        }
        reader.close();
        reader = new BufferedReader(new FileReader(electrosPath));
        while((line = reader.readLine()) != null) {     //electrodomesticos
            String[] data = line.split("-");
            String nifElectros = data[0];
            String description = data[1];
            int potencia = Integer.parseInt(data[2]);
            boolean inter = Boolean.parseBoolean(data[3]);
            Electrodomesticos newElectro = new Electrodomesticos(description, potencia, inter);
            int indexCasa = casas.indexOf(new Casa(nifElectros));  //con el int cogemos el indice de la casa que tiene ese nif que ha escrito el usuario y si existe nos devuelve un numero mayor o igual a 0 y si no existe devuelve -1
            if (indexCasa >= 0) {
                Casa c = casas.get(indexCasa);
                c.añadirElectro(newElectro);
            }
        }
        reader.close();
        return casas;
    }

}