import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * @author Roberto Hermoso Rivero
 * @version 1.1.0
 */
public class Run {

    private boolean codifiesFlag;
    private boolean traceFlag;
    private String outpuFile;
    private String inputFile;
    private ChineseEncryption che;

    /**
     * Contructor por defecto de la clase
     */
    public Run() {
        codifiesFlag = true;
        traceFlag = true;
        outpuFile = new String("salida.txt");
        inputFile = new String("entrada.txt");
        che = new ChineseEncryption();
    }

    /**
     * Constructor parametrizado de la clase
     *
     * @param codifiesFlag : Bandera de codificacion
     * @param traceFlag    : Bandera de traza
     */
    public Run(boolean codifiesFlag, boolean traceFlag) {
        this.codifiesFlag = codifiesFlag;
        this.traceFlag = traceFlag;
        outpuFile = new String("salida.txt");
        inputFile = new String("entrada.txt");
        che = new ChineseEncryption();
    }

    /**
     * Este metodo se usa para imprimir por pantalla cualquier cosa que se dese imprimir, (Esta en funcion de la bandera de traza)
     *
     * @param text : Es el texto que se desea imprimir
     */
    public void imprimir(String text) {
        if (traceFlag == true) {
            System.out.println(text);
        }
    }

    /**
     * Este metodo inciializa el programa
     *
     * @param fillName : Es el nombre del fichero de configuracion
     */
    public void runPlay(String fillName) {
        String cadena;
        //fillName=fillName+".txt";
        try {
            FileReader f = new FileReader(fillName);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                if (!cadena.equals("")) {
                    chooseAction(cadena);
                }
            }
            b.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para selecionar la accion que hacer segun la linea que corresponda en el fichero de configuracion
     *
     * @param cadena : es linea del fichero del configuracion a interpretar
     */
    public void chooseAction(String cadena) {
        StringTokenizer st = new StringTokenizer(cadena);
        String[] stringArray = new String[st.countTokens()];
        int count = 0;
        while (st.hasMoreTokens()) {
            stringArray[count] = st.nextToken();
            count++;
        }
        if (stringArray[0].equals("@")) {
            if (stringArray.length == 3) {
                modifyFlag(stringArray[1], stringArray[2]);
            } else {
                imprimir("Numero de argumentos no validos");
            }
        } else {
            if (stringArray[0].equals("&")) {
                if (stringArray.length > 1) {
                    chooseMethod(stringArray, cadena);
                } else {
                    imprimir("Error numero de argumentos no validos");
                }
            } else {
                if (!stringArray[0].equals("#")) {
                    imprimir(cadena + " -> Error comando no detectado");
                }
            }
        }
    }

    /**
     * Este metodo sirve para modificar las diferentesbanderas
     *
     * @param st1 : es el tipo de bandera a modificar
     * @param st2 : es la opcion de la bandera a modificar
     */
    public void modifyFlag(String st1, String st2) {
        boolean onOff;
        if (st1.equals("codifica")) { // buscamos el tipo de bandera
            if (st2.equals("ON")) { // buscamos si busca true o false
                codifiesFlag = true;
                imprimir("Codificacion en estado activo");
            } else {
                if (st2.equals("OFF")) {
                    codifiesFlag = false;
                    imprimir("Codificacion en estado incativo");
                } else {
                    imprimir(st2 + " -> Error argumento de bandera no valido");
                }
            }

        } else {
            if (st1.equals("traza")) {
                if (st2.equals("ON")) {
                    traceFlag = true;
                    imprimir("Traza en estado acitvo");
                } else {
                    if (st2.equals("OFF")) {
                        traceFlag = false;
                    } else {
                        imprimir(st2 + " -> Error argumento de bandera no valido");
                    }
                }
            } else {
                imprimir(st1 + " -> Error bandera no conocida");
            }
        }


    }

    /**
     * Este metodo seleciona el tipo de comando que se va ejecutar
     *
     * @param stringArray : es el array con la cadena tokenizada
     * @param cadena      : es la cadena, se usa para mostrar el comando en caso de error
     */
    public void chooseMethod(String[] stringArray, String cadena) {
        String fuction = stringArray[1];
        // mediante if-else encadenados buscamos la opcion a elegir y llamamos la metodo que corresponda
        // si no exite ninguna opcion se muestra mensaje de error

        if (fuction.equals("ficheroentrada")) {
            if (stringArray.length > 2) {
                selectionInputFile(stringArray[2]);
            } else {
                imprimir(cadena + " -> Error comando no valido");
            }
        } else {
            if (fuction.equals("ficherosalida")) {
                if (stringArray.length > 2) {
                    selectionOutputFile(stringArray[2]);
                } else {
                    imprimir(cadena + " -> Error comando no valido");
                }
            } else {
                if (fuction.equals("filas")) {
                    if (stringArray.length > 2) {
                        rows(stringArray[2]);
                    } else {
                        imprimir(cadena + " -> Error comando no valido");
                    }
                } else {
                    if (fuction.equals("columnas")) {
                        if (stringArray.length > 2) {
                            colum(stringArray[2]);
                        } else {
                            imprimir(cadena + " -> Error comando no valido");
                        }
                    } else {
                        if (fuction.equals("china")) {
                            china();
                        } else {
                            if (fuction.equals("formateaentrada")) {
                                formatInput();
                            } else {
                                imprimir(cadena + " -> Error comando no valido");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Este metodo se encarga de modificar el numero de filas de la matriz
     *
     * @param number : es el numero de filas de la matriz
     */
    public void rows(String number) {
        try { // buscamos la ecepcion si no es un numero
            che.setNumRows(Integer.parseInt(number));
            che.setSelection(false);
            imprimir("Modificacion de las filas realizadas correctamente");
        } catch (Exception e) {
            imprimir(number + " -> Error en la seleccion de las filas");
        }
    }

    /**
     * Este metodo se encarga de modificar el numero de columnas de la matriz
     *
     * @param number : es el numero de columnas de la matriz
     */
    public void colum(String number) {
        try { // buscamo la excepcion si no es numero
            che.setNumColum(Integer.parseInt(number));
            che.setSelection(true);
            imprimir("Modificacion de las columnas realizadas correctamente");
        } catch (Exception e) {
            imprimir(number + " -> Error en la seleccion de las columnas");
        }
    }

    /**
     * Este metodo sirve para selecionar el fichero de salida
     *
     * @param file : es el nombre del fichero de salida
     */
    public void selectionOutputFile(String file) {
        File outpuFile = new File(file);
        this.outpuFile = (file);
        if (outpuFile.exists()) {
            imprimir(file + " -> Fichero de salida seleccionado correctamente");
        } else {
            imprimir(file + " -> Atencion no exite el fichero selecionado, se crear uno.");
        }

    }


    /**
     * Este metodo sirve para seleccionar el fichero de entrada
     *
     * @param file : es el nombre del fichero de entrada
     */
    public void selectionInputFile(String file) {
        File inputFile = new File(file);
        this.inputFile = (file);
        if (inputFile.exists()) {
            imprimir(file + " -> Fichero de entrada seleccionado correctamente");
        } else {
            imprimir(file + " -> Error no exite el fichero selecionado");
        }
    }

    /**
     * Este metodo sirve para formatear la entrada
     */
    public void formatInput() {
        // busca si exite el fichero
        File f = new File(inputFile);
        if (!f.exists()) {
            imprimir("No existe ningun fichero de entrada con el nombre :" + inputFile);
        } else {// si exite lo borra para sobreescribirlo

            String entrada = format();
            try { // borramos el fichero de entrada para sobreescribir
                BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile));
                bw.write("");
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // luego escribimos la salida formateada en el fichero de entrada
            try (FileWriter fw = new FileWriter(inputFile, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(entrada);
                imprimir("Fichero formateado correctamente para evitar fallos en el cifrado-descifrado");
            } catch (IOException e) {
                imprimir("Error al escribir en el fichero de salida");
            }
        }
    }

    /**
     * Este metodo devuelve el contenido del fichero de entrada formateado
     *
     * @return String : es el contenido del fichero de entrada formateado
     */
    public String format() {
        Text text = new Text();
        String cadena = "";
        String entrada = "";
        try {
            FileReader fr = new FileReader(inputFile);// buscamos y abrimos el fichero
            BufferedReader br = new BufferedReader(fr);
            while ((cadena = br.readLine()) != null && (cadena + entrada).length() < 1000) {
                // recorremos el fichero siempre que tenga la longitud adecuada y vamos concadenado
                // en la variavle eentrada las lineas forateadas
                if (!cadena.equals("")) {
                    if (cadena.length() + entrada.length() <= 1000) {
                        cadena = text.format(cadena);
                        entrada = entrada + cadena + "\n";
                    } else {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entrada;
    }

    /**
     * Este metod es el encargado de realizar el cifraodo descifrado segun corresponaa
     */
    public void china() {
        // lo primero es ver si existen los ficheros exiten en caso sontrario no hacer nada
        File f = new File(inputFile);
        File f1 = new File(outpuFile);
        f1.delete();
        if (f.exists()) { // comprobamos quel fichero de entrada existe
            try {
                String axu = outpuFile;
                outpuFile = inputFile;
                formatInput(); // lo formateamos para evitar problemas
                outpuFile = axu;
                String cadenaDespues = chineseFile();// guardamos en un strng todas las slaidas
                // codificadas o decodificadas segun toque
                writeFile(cadenaDespues);// escribimos esas salidas en le fichero de salida
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            imprimir("Error no hay existe un fichero con ese nombre : " + inputFile);
        }
    }

    /**
     * Este metodo es el encargado de realizar al accion de cifrar y descifrar el texto que se encuentra en el fichero de entrada
     *
     * @return String : es el contenido del fichero de entrada cifrado o descifrado segun corresponda
     */
    public String chineseFile() {
        String cadenaActual = "";
        String cadenaDespues = "";
        String axu = "";
        FileReader fr = null;
        try {
            fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);
            while ((cadenaActual = br.readLine()) != null) { // se lee contenido de las lineas una a una
                if (!cadenaActual.equals("")) { // si no es linea vacia se procede
                    System.out.println("-- Usando el metodo chino para el cifrado y descifrado --");
                    che.setColumRow(cadenaActual); // ajustamos el tamaño de la matriz interna
                    if (codifiesFlag) { // comprobamos si hay que cifrar o descifrar
                        System.out.println("Texto en claro : " + cadenaActual);
                        axu = che.encrytion(cadenaActual, traceFlag); // relaizamos el cifreado
                        System.out.println("Texto cifrado : " + axu);
                        cadenaDespues = cadenaDespues + axu + "\n"; // concatenamos el resultado
                    } else {
                        System.out.println("Texto cifrado : " + cadenaActual);
                        try { // tenemos cuidado con que no se haya usado una clave correcta
                            axu = che.description(cadenaActual, traceFlag);
                            System.out.println("Texto claro : " + axu);
                            cadenaDespues = cadenaDespues + axu + "\n";
                        } catch (LengthMatrixException e) {
                            imprimir(e.getMessage());
                        }

                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cadenaDespues;
    }

    /**
     * Este metodo escirbe el el fichero e salida el resultado de la operacion de cifrado o descifrado
     *
     * @param s : es el resultado de la operacion de cifrado o descifrado
     */
    public void writeFile(String s) {
        try (FileWriter fw = new FileWriter(outpuFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(s);
            imprimir("Algoritmo realizado con exito");
        } catch (IOException e) {
            imprimir("Error al escribir en el fichero");

        }
    }


    /**
     * Metodo set del atributo che ( ChineseEcncryption )
     *
     * @param che : atributo che de la clase
     */
    public void setChe(ChineseEncryption che) {
        this.che = che.clone();
    }

    /**
     * Metodo set del atributo codifiesFlag
     *
     * @param codifiesFlag : atributo cosdifiesFla de lc clase
     */
    public void setCodifiesFlag(boolean codifiesFlag) {
        this.codifiesFlag = codifiesFlag;
    }


    /**
     * Metodo set del atributo inputFile
     *
     * @param inputFile : Atributo inpout de la clase:
     */
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Metodo set del atributo outputFile
     *
     * @param outpuFile: Atributo outputFile de la clase:
     */
    public void setOutpuFile(String outpuFile) {
        this.outpuFile = outpuFile;
    }

    /**
     * Metodo set del atributo traceFlag
     *
     * @param traceFlag : Atributo traceFlag de la clase
     */
    public void setTraceFlag(boolean traceFlag) {
        this.traceFlag = traceFlag;
    }

    /**
     * Metodo is del atributo codifiesFlag
     *
     * @return Boolean : aributo codifiesFlag de la clase
     */
    public boolean isCodifiesFlag() {
        return codifiesFlag;
    }

    /**
     * Metodo is del atributo traceFlag
     *
     * @return Boolean : atributo traceFlag de la clase
     */
    public boolean isTraceFlag() {
        return traceFlag;
    }

    /**
     * Metodo get del atributo inputFile
     *
     * @return String : atributo inputFile de la clase
     */
    public String getInputFile() {
        return inputFile;
    }

    /**
     * Metodo get del atributo outputFile
     *
     * @return String : atributo outputFile de la clase
     */
    public String getOutpuFile() {
        return outpuFile;
    }

    /**
     * Metodo get del atributo che
     *
     * @return ChineseEncryption : atributo che de la clase
     */
    public ChineseEncryption getChe() {
        return che;
    }

    /**
     * Metodo equals de la clase
     *
     * @param o : objeto a comparar
     * @return boolean : resultado de la operacion
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Run run = (Run) o;
        return codifiesFlag == run.codifiesFlag && traceFlag == run.traceFlag && Objects.equals(outpuFile, run.outpuFile) && Objects.equals(inputFile, run.inputFile) && Objects.equals(che, run.che);
    }

    /**
     * Metodo hashCode de la clase
     *
     * @return int : resultado de la operacion
     */
    @Override
    public int hashCode() {
        return Objects.hash(codifiesFlag, traceFlag, outpuFile, inputFile, che);
    }


    /**
     * Metodo toString de la clase
     *
     * @return String : resultado de la operacion
     */
    @Override
    public String toString() {
        return "Run{" +
                "codifiesFlag=" + codifiesFlag +
                ", traceFlag=" + traceFlag +
                ", outpuFile='" + outpuFile + '\'' +
                ", inputFile='" + inputFile + '\'' +
                ", che=" + che.toString() +
                '}';
    }
}
