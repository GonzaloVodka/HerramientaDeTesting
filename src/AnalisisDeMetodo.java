import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalisisDeMetodo {

    private ArrayList<String> clases;
    private ArrayList<String> NombreClases;
    private ArrayList<Metodo> metodos;
    private ArrayList<String> NombreMetodos;

    public AnalisisDeMetodo() {
        clases = new ArrayList<String>();
        NombreClases = new ArrayList<String>();
        NombreMetodos = new ArrayList<String>();
        metodos = new ArrayList<Metodo>();
    }

    public ArrayList<String> encontrarClases(File fuente) throws IOException {
        Pattern p = Pattern.compile("(\\s\\w+)");
        Matcher m;
        Scanner sc = new Scanner(new FileReader(fuente));
        String linea, copia, clase = new String();
        boolean esComentario = false, claseEncontrada = false, nuevaClase;
        clases.clear();
        NombreClases.clear();
        while (sc.hasNextLine()) {
            nuevaClase = false;
            copia = linea = sc.nextLine();
            if (esComentario) {    //la peor forma posible de encontrar comentarios e ignorarlos
                if (linea.contains("*/")) {
                    esComentario = false;
                    linea = linea.substring(linea.indexOf("*/"));
                } else
                    continue;
            }
            if (linea.contains("//"))
                linea = linea.substring(0, linea.indexOf("//"));

            if (linea.contains("/*")) {
                linea = linea.substring(0, linea.indexOf("/*"));
                esComentario = true;
            }
            m = p.matcher(linea.substring(linea.indexOf(" class ") + 1));
            if (linea.contains(" class ") && m.find()) {
                NombreClases.add(m.group().trim());
                if (claseEncontrada)
                    nuevaClase = true;
                claseEncontrada = true;
            }
            if (nuevaClase) {
                clases.add(clase);
                clase = new String();
                nuevaClase = false;
            }
            if (claseEncontrada)
                clase = clase.concat(copia + "\n");
        }
        clases.add(clase);
        sc.close();

        return NombreClases;
    }

    public List<String> encontrarMetodos(int index) {
        String clase = clases.get(index);
        String metodo = null;
        String linea, copia;
        Scanner sc = new Scanner(clase);
        Pattern p = Pattern.compile("\\w+[(]");
        Matcher m;
        boolean esComentario = false;
        metodos.clear();
        NombreMetodos.clear();

        while (sc.hasNextLine()) {
            copia = linea = sc.nextLine();
            if (esComentario) {// igual que en el otro metodo
                if (linea.contains("*/")) {
                    esComentario = false;
                    linea = linea.substring(linea.indexOf("*/"));
                } else
                    continue;
            }
            if (linea.contains("//"))
                linea = linea.substring(0, linea.indexOf("//"));

            if (linea.contains("/*")) {
                linea = linea.substring(0, linea.indexOf("/*"));
                esComentario = true;
            }
            m = p.matcher(linea);
            linea = linea.trim();
            if ((linea.startsWith("public") || linea.startsWith("private") || linea.startsWith("protected")) && m.find() && !linea.endsWith(";")) {
                NombreMetodos.add(m.group().substring(0, m.group().length() - 1));
                if (metodo != null)
                    metodos.add(new Metodo(metodo));
                metodo = "";
            }
            if (metodo != null) {
                metodo = metodo.concat(copia + "\n");
            }

        }
        metodos.add(new Metodo(metodo));
        sc.close();
        return NombreMetodos;
    }

    public Metodo getMetodo(int index) {
        return metodos.get(index);
    }


}
