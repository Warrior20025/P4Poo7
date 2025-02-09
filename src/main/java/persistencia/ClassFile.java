package persistencia;

import model.Casa;
import model.PlacaSolar;
import model.Electrodomesticos;

import java.io.*;
import java.util.ArrayList;

public class ClassFile {

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

    public void writeCasaInFile(Casa c) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(casasPath, true));
        writer.write(c.getNif() + "-" + c.getNombre() + "-" + c.getsTejado() + "-" + c.isInterruptor());
        writer.newLine();
        writer.close();
    }

    public void writePlacaInFile(PlacaSolar c, Casa s) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(placasPath, true));
        writer.write( s.getNif()+ "-" + c.getSuperficie() + "-" + c.getPrecio() + "-" + c.getPotencia());
        writer.newLine();
        writer.close();
    }
//
//    public void writeClassInFile(Electrodomesticos c) throws IOException {
//        BufferedWriter writer = new BufferedWriter(new FileWriter(electrosPath, true));
//        writer.write(c.getNomProjecte() + "," + c.getNomClient() + "," + c.getNomFreelance() + "," + c.getDurada());
//        writer.newLine();
//        writer.close();
//    }

//    public void reWriteClassesInFile(ArrayList<Projecte> projects) throws IOException {
//        ClassFile file = new ClassFile();
//        BufferedWriter reWriter = new BufferedWriter(new FileWriter(filePath, false));
//        for (Projecte i : projects) {
//            file.writeClassInFile(i);
//        }
//        reWriter.close();
//    }

    public ArrayList<Casa> readClass() throws IOException {
        ArrayList<Casa> clase = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(casasPath));
        String line;
        while((line = reader.readLine()) != null) {
            String[] data = line.split("-");
            String nif = data[0];
            String nombre = data[1];
            int sTejado = Integer.parseInt(data[2]);
            boolean inter = Boolean.parseBoolean(data[3]);
            Casa newHouse = new Casa(nif, nombre, sTejado, inter);
            clase.add(newHouse);
        }
        return clase;
    }

}