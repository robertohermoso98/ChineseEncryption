import java.util.Objects;

/**
 * @author Roberto Hermoso Rivero
 * Esta clase esta destina al cifrado y descifrado de un String mediante el metodo chino,
 * para su uso hay que especificar que numero de columnas o filas se desean usar ( estos se usan como claves de cifrado)
 * @version 1.0
 */
public class ChineseEncryption implements Cloneable {

    private int numColum;
    private int numRows;
    private boolean selection;

    /**
     * Este es el contructor por defecto de la clase, iniciara como clave el numero de columnas con valor 3
     */
    public ChineseEncryption() {

        numColum = 3;
        numRows = 0;
        selection = true;
    }

    /**
     * Este es el contructor parametrizado de la clase deja indicar cual sera la clave (filas o columnas) y el numero de estas.
     * @param num : Es el numero de filas o de columnas que se desea usar como clave.
     * @param selection : Para iniciar las columnas como clave pasar un true, para iniciar las filas como clave pasar un false.
     */
    public ChineseEncryption(int num, boolean selection) {
        this.selection = selection;
        if(selection){
            numColum=num;
        }else{
            numRows=num;
        }
    }

    /**
     * Metodo get del atributo selection.
     * @return boolean : atributo selction.
     */
    public boolean isSelection() {
        return selection;
    }

    /**
     *  Metodo set del atributo selection.
     * @param selection : atributo selection.
     */
    public void setSelection(boolean selection) {
        this.selection = selection;
    }

    /**
     * Metodo get del atributo numColum - numero del columnas.
     * @return int : numColum.
     */
    public int getNumColum() {
        return numColum;
    }

    /**
     * Metodo get del atributo umRows - numero de filas.
     * @return int : numRows.
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Metodo set del atributo numColum - numero de columnas.
     * @param numColum : atributo numColum.
     */
    public void setNumColum(int numColum) {
        this.numColum = numColum;
    }

    /**
     * Metodo set del atributo numRows - numero de filas.
     * @param numRows : atributo numRows.
     */
    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    /**
     * Metodo para cifrar un texto plano mediante el metodo de transposicion china.
     * @param plainText : texto a cifrar.
     * @param imprimir : booleano para indicar si se desea imprimir la matriz de transposicion.
     * @return String : texto cifrado.
     */
    public String encrytion(String plainText, boolean imprimir) {
        Text text = new Text(); // creamo un objeto de la clase text
        String[][] arrayString = new String[numRows][numColum]; // creamo la matriz de transposicion.
        plainText = text.fillPlainText(plainText, numColum * numRows); // rellenamos el string con caracteres aleatorios.
        fillArray(arrayString, plainText);// rellenamos la matriz.
        return makeExitTextEncrypt(arrayString, imprimir);// obtenemos el text cifrado y lo retornamos.
    }

    /**
     * Metodo para calcular el numero de columnas o filas en funcion del cual de ellos sea eligido como clavey del la longitud del string.
     * @param text : texto a cifrar.
     */
    public void setColumRow(String text) {
        int size = text.split("").length;// calculamos el numero de columas o filas que vamos a necesitar
        if (selection) {
            numRows = techo(size, numColum);
        } else {
            numColum = techo(size, numRows);
        }
    }

    /**
     * Metodo para calcular el techo de la division de dos numeros.
     * @param a : divisor.
     * @param b : divendo.
     * @return int : resultado.
     */
    public int techo(int a, int b) {
        float fa = (float) a;
        float fb = (float) b;
        int result = a / b;
        if (fa % fb != 0) {
            result++;
        }
        return result;
    }

    /**
     * Metodo para descifra un texto.
     * @param plainText : es el texto que deseamos descifrar.
     * @param imprimir : es un booleano que nos indica si queremos mostrar la matriz.
     * @return String : es el texto descifrado.
     * @throws LengthMatrixException : es una exceptcion cuando el nuemro de filas y de columnas no coincide con la longitud del texto a descifrar.
     */
    public String description(String plainText, boolean imprimir) throws LengthMatrixException {
        String[] text = plainText.split(""); // spliteamos el texto
        String exit = ""; // iniciamlizamos la variable de salida
        String[][] textArray = new String[numRows][numColum];// inicializamos la matriz
        int cont = 0; //  contador auxiliar para recorre el split del texto inicial
        int tam = text.length; // tamaño del texto
        int tam2=numColum*numRows;// tamaño de la matriz
        if (tam != tam2) {// si los tamaños no coinciden lanzamos la excepcion
            throw new LengthMatrixException();
        } else {
            for (int i = 0; i < numRows; i++) { // recorremos la matriz y la rellenamos
                for (int e = 0; e < numColum; e++) {
                    textArray[i][e] = text[cont];
                    if (imprimir) { // si desamos imprimir imprimimos la matriz
                        System.out.print(text[cont] + " || ");
                    }
                    cont++;
                }
                if (imprimir) {// si deseamos imprimir imprimimos la matriz
                    System.out.println("");
                }
            }
        }
        exit = makeExitTextDescrytp(textArray); // construidmos el String de salida
        return exit;
    }

    /**
     * Metodo para contruir el texto de salida al descifrar
     * @param textArray : es la matriz para conseguir el texto a descifrar
     * @return String : es el texto descifrado
     */
    public String makeExitTextDescrytp(String[][] textArray) {
        String exit = "";
        String axu;

            boolean sentido = true;
            // recorremos la matric en un sentido por columnas y en sentidos opuestos por filas
            for (int i = numColum; i > 0; i--) {
                if (sentido) {
                    for (int e = numRows; e > 0; e--) {
                        axu = (textArray[e - 1][i - 1]);
                        exit = exit + axu;
                    }
                    sentido = !sentido;
                } else {
                    for (int e = 0; e < numRows; e++) {
                        axu = textArray[e][i - 1];
                        exit = exit + (axu);
                    }
                    sentido = !sentido;
                }
            }

        return exit;
    }


    /**
     * Este metodo es el ecardo de rellenar la matriz.
     * @param arrayString : es la matriz que queremos rellenar.
     * @param plainText : es el String con el que queremos rellenar la matriz
     */
    public void fillArray(String[][] arrayString, String plainText) { // primer for recorre la X, el sgundo la Y
        boolean sentido = true; //cambio el sentido del recorrido de las columnas
        String[] arrayPlainText = plainText.split(""); // spliteo la frase para ir cogindo sus letras
        int position = 0;
        for (int i = numColum; i > 0; i--) { // el primer for recorre las filas
            if (sentido) {  // los otros dos for recorren las columnas se le cambia el sentido a cada recorrido
                for (int e = numRows; e > 0; e--) {
                    arrayString[e - 1][i - 1] = arrayPlainText[position];
                    position++;
                }
                sentido = !sentido;
            } else {
                for (int e = 0; e < numRows; e++) {
                    arrayString[e][i - 1] = arrayPlainText[position];
                    position++;
                }
                sentido = !sentido;
            }
        }
    }

    /**
     * Metodo para obtener el texto cifrado de la matric
     * @param ss : matriz de la que sacaremos el texo cifrado
     * @param imprimir : booleano que nos indicara si deseamos imprimir la matriz
     * @return String : el cual es el texto cifrado
     */
    public String makeExitTextEncrypt(String[][] ss, boolean imprimir) {
        String s = new String();
        String imp;
        // lo unico que se hace es recorrer la matriz y ir concadenado sus elementos para formar el texo de salida
        for (int i = 0; i < numRows; i++) {
            for (int e = 0; e < numColum; e++) {
                imp = ss[i][e];
                s = s + (imp);
                if (imprimir) {
                    System.out.print(imp + " || ");
                }
            }
            if (imprimir) {
                System.out.println(" ");
            }
        }
        return s;
    }

    /**
     * Metodo equals de la clase
     * @param o : es el objeto a comparar
     * @return boolean : el cual nos indica sin ambos obejtos son iguales
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChineseEncryption that = (ChineseEncryption) o;
        return numColum == that.numColum && numRows == that.numRows && selection == that.selection;
    }

    /**
     * Metodo hashcode de la clae, sirve para generar el numero hash del objeto
     * @return int : es el hash del objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(numColum, numRows, selection);
    }

    /**
     * Metodo tosString de la clase, nos indica los parametos del objeto
     * @return String : son los parametros del objeto
     */
    @Override
    public String toString() {
        return "ChineseEncryption{" +
                "numColum=" + numColum +
                ", numRows=" + numRows +
                ", selection=" + selection +
                '}';
    }

    /**
     * Metodo clone de la clase, no genera un objeto con los mismos parametro
     * @return ChineseEncryption : es el objeto clonado
     */
    @Override
    public ChineseEncryption clone() {
        ChineseEncryption ch = new ChineseEncryption();
        ch.setNumColum(numColum);
        ch.setSelection(selection);
        ch.setNumRows(numRows);
        return ch;
    }

}
