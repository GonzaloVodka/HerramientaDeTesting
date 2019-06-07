import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;

public class Metodo {

    private final int NOT_FOUND = -1;
    private String codigo;
    private int nodosPredicados;
    private int cantLineas;
    private int lineasComentadas;

    private String palabrasClave = "while|if|for|foreach|case|default|continue|goto|&&|catch|\\?";

    public Metodo(String str) {
        codigo = str;
        nodosPredicados = cantLineas = lineasComentadas = 0;
        this.analizar();
    }

    private void analizar() {
        if (nonNull(codigo)) {
            Scanner sc = new Scanner(codigo);
            String linea, copia, lineaSinComentarios;
            List<String> codigoEditado = new ArrayList<>();
            // aplicados.
            while (sc.hasNextLine()) {
                copia = linea = sc.nextLine();
                if (linea.contains(Constantes.COMENTARIO_UNA_LINEA) || linea.contains(Constantes.APERTURA_DE_COMENTARIO) || linea.contains(Constantes.CIERRE_DE_COMENTARIO)) {
                    lineasComentadas++;
                }

                linea = contarYResaltarPredicados(linea, removerComentarios(linea));

                codigoEditado.add(linea);
                cantLineas++;

            }
            cantLineas--;
            this.codigo = String.join(Constantes.SALTO_DE_LINEA, codigoEditado);
            sc.close();
        }
    }

    private String removerComentarios(String linea) {
        int indiceComentario = linea.indexOf(Constantes.COMENTARIO_UNA_LINEA);
        if (indiceComentario != -1) {
            linea = linea.substring(0, indiceComentario);
        }

        int indiceApertura = linea.indexOf(Constantes.APERTURA_DE_COMENTARIO);
        int indiceCerrado = linea.indexOf(Constantes.CIERRE_DE_COMENTARIO);
        if (indiceApertura != NOT_FOUND || indiceCerrado != NOT_FOUND) {
            if (indiceApertura != NOT_FOUND && indiceCerrado != NOT_FOUND) {
                String comentario = linea.substring(indiceApertura, indiceCerrado);
                linea = linea.replace(comentario, "");
            } else if (indiceApertura != NOT_FOUND) {
                linea = linea.substring(0, indiceApertura);
            } else {
                linea = linea.substring(indiceCerrado);
            }
        }

        return linea;
    }

    private String contarYResaltarPredicados(String original, String analizar) {
        String regex = "((" + palabrasClave + ")(?=[\\s\\(]))";
        Pattern p = Pattern.compile(regex);                            //encuentra cualquier palabra de la lista separada por OR |
        Matcher m = p.matcher(analizar);                            //solo si esta seguida de un espacio \\s o un parentesis abierto \\(
        while (m.find())                                                //https://www.freeformatter.com/java-regex-tester.html
            nodosPredicados++;
        original = original.replaceAll(regex, "<b><font color=\"red\">$1</b></font>");//$1 es el elemento encontrado
        return original;
    }

    public String getLineas() {
        return Integer.toString(cantLineas);
    }

    public String getComentarios() {
        return Integer.toString(lineasComentadas);
    }

    public String getPorcentaje() {
        return String.format("%.2f", ((double) (lineasComentadas * 100) / cantLineas)) + "%";
    }

    public String getComplejidad() {
        return Integer.toString(nodosPredicados + 1);
    }
    
    public String getCodigo() {
        return codigo;
    }
}
