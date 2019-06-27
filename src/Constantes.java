public class Constantes {
    public static final String CIERRE_DE_COMENTARIO = "*/";
    public static final String COMENTARIO_UNA_LINEA = "//";
    public static final String APERTURA_DE_COMENTARIO = "/*";
    public static final String PUNTO_Y_COMA = ";";
    public static final String SALTO_DE_LINEA = "\n";
    //necesito separar los operadores de halstead segun la expresion que voy a usar para encontrarlos
    public static final String OPERADORES_PALABRA ="while|if|for|foreach|case|default|continue|goto|catch";//se los cuenta si estan antes de un espacio o parentesis
    public static final String OPERADORES_COMPUESTOS ="\\+\\+|--|==|&&|<=|>=|\\|\\|!=";//"operadores que se construyen a partir de otros" cuentan su aparicion sin condiciones.
    public static final String OPERADORES_CARACTER = "\\?|!|=|\\*|/|\\+|-|<|>";// se cuentan cuando aparecen solos y no como parte de un operador compuesto
    public static final String TIPOS_DE_DATO = "int|string|float|char|double|Integer|String|Character|Double|bool|Boolean|File";
    
}