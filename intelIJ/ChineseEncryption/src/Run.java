import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

public class Run {

    private boolean codifiesFlag;
    private boolean traceFlag;
    private String outpuFile;
    private String inputFile;
    private ChineseEncryption che;

    public Run() {
        codifiesFlag = true;
        traceFlag = true;
        outpuFile = new String("salida.txt");
        inputFile = new String("entrada.txt");
        che = new ChineseEncryption();
    }

    public Run(boolean codifiesFlag, boolean traceFlag) {
        this.codifiesFlag = codifiesFlag;
        this.traceFlag = traceFlag;
        outpuFile = new String("salida.txt");
        inputFile = new String("entrada.txt");
        che = new ChineseEncryption();
    }

    public void imprimir(String text) {
        if (traceFlag == true) {
            System.out.println(text);
        }
    }

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

    public void chooseAction(String cadena) {
        StringTokenizer st = new StringTokenizer(cadena);
        String[] stringArray = new String[st.countTokens()];
        int count = 0;
        while (st.hasMoreTokens()) {
            stringArray[count] = st.nextToken();
            count++;
        }
        if (stringArray[0].equals("@")) {
            modificarBandera(stringArray[1], stringArray[2]);
        } else {
            if (stringArray[0].equals("&")) {
                chooseMethod(stringArray);
            }
        }
    }

    public void modificarBandera(String st1, String st2) {
        boolean onOff = true;
        if (st2.equals("ON")) {
            onOff = true;
        } else {
            if (st2.equals("OFF")) {
                onOff = false;
            } else {
                imprimir("Error argumento de bandera no valido");
            }
        }
        if (st1.equals("codifica")) {
            codifiesFlag = onOff;
        } else {
            if (st1.equals("traza")) {
                traceFlag = onOff;
            } else {
                imprimir("Error bandera no balida");
            }
        }
    }

    public void chooseMethod(String[] stringArray) {
        String fuction = stringArray[1];
        if (fuction.equals("ficheroentrada")) {
            selectionInputFile(stringArray[2]);
        } else {
            if (fuction.equals("ficherosalida")) {
                selectionOutputFile(stringArray[2]);
            } else {
                if (fuction.equals("filas")) {
                    rows(stringArray[2]);
                } else {
                    if (fuction.equals("columnas")) {
                        colum(stringArray[2]);
                    } else {
                        if (fuction.equals("china")) {
                            china();
                        } else {
                            if (fuction.equals("formateaentrada")) {
                                formateentrada();
                            } else {
                                imprimir("Error comando no encontrado");
                            }
                        }
                    }
                }
            }
        }
    }

    public void rows(String number) {
        try {
            che.setNumRows(Integer.parseInt(number));
            che.setSelection(false);
            imprimir("Modificacion de las filas realizadas correctamente");
        } catch (Exception e) {
            imprimir("Error en la seleccion de las filas");
        }
    }

    public void colum(String number) {
        try {
            che.setNumColum(Integer.parseInt(number));
            che.setSelection(true);
            imprimir("Modificacion de las columnas realizadas correctamente");
        } catch (Exception e) {
            imprimir("Error en la seleccion de las columnas");
        }
    }

    public void selectionOutputFile(String file) {
        File outpuFile = new File(file);
        this.outpuFile = new String(file);
        if (outpuFile.exists()) {
            imprimir("Fichero de salida seleccionado correctamente");
        } else {
            imprimir("Error no exite el fichero selecionado");
        }

    }

    public void selectionInputFile(String file) {
        File inputFile = new File(file);
        this.inputFile = new String(file);
        if (inputFile.exists()) {
            imprimir("Fichero de entrada seleccionado correctamente");
        } else {
            imprimir("Error no exite el fichero selecionado");
        }
    }

    public void formateentrada() {
        File f = new File(inputFile);
        if (!f.exists()) {
            imprimir("No existe ningun fichero de entrada con el nombre proporcionado");
        } else {
            String entrada = format();
            File fichero = new File(outpuFile);
            if (fichero.exists()) {
                if (fichero.delete()) {
                    imprimir("El fichero ha sido borrado satisfactoriamente");
                } else {
                    imprimir("El fichero no pudó ser borrado");
                }
            }
            try (FileWriter fw = new FileWriter(outpuFile, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(entrada);
                imprimir("Fichero formateado correctamente");
            } catch (IOException e) {
                imprimir("Error al escribir en el fichero");

            }
        }
    }

    public String format() {
        Text text = new Text();
        String cadena = "";
        String entrada = "";
        try {
            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);
            while ((cadena = br.readLine()) != null) {
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


    public void china() {
        File f = new File(inputFile);
        File f1 = new File(outpuFile);
        if (f.exists() && f1.exists()) {
            try {
                FileReader fr = new FileReader(inputFile);
                String cadenaActual = "";
                String cadenaDespues = "";
                String axu;
                BufferedReader br = new BufferedReader(fr);
                while ((cadenaActual = br.readLine()) != null) {
                    ///if no es cadena vacia
                    che.setColumRow(cadenaActual);
                    if (codifiesFlag) {
                        imprimir("Texto en claro : " + cadenaActual);
                        axu = che.encrytion(cadenaActual, traceFlag);
                        imprimir("Texto cifrado : " + axu);
                        cadenaDespues = cadenaDespues + axu + "\n";
                    } else {
                        imprimir("Texto cifrado : " + cadenaActual);
                        axu = che.description(cadenaActual, traceFlag);
                        imprimir("Texto descifrado : " + axu);
                        cadenaDespues = cadenaDespues + axu + "\n";
                    }
                }

                try (FileWriter fw = new FileWriter(outpuFile, true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    out.println(cadenaDespues);
                    imprimir("Algoritmo realizado con exito");
                } catch (IOException e) {
                    imprimir("Error al escribir en el fichero");

                }
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            imprimir("Error no hay existe un fichero con ese nombre");
        }

    }

    public void defaultFile() {
        File in = new File(inputFile);
        if (!in.exists()) {
            System.out.println("no escite entrada");
        }
        File ou = new File(outpuFile);
        if (!ou.exists()) {
            System.out.println("no existe salida");
        }
    }

    public void formatInput(String fileInput) {


    }

    public void show(String str) {
        if (traceFlag) {
            System.out.println(str);
        }
    }

    public void setChe(ChineseEncryption che) {
        this.che = che.clone();
    }

    public void setCodifiesFlag(boolean codifiesFlag) {
        this.codifiesFlag = codifiesFlag;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutpuFile(String outpuFile) {
        this.outpuFile = outpuFile;
    }

    public void setTraceFlag(boolean traceFlag) {
        this.traceFlag = traceFlag;
    }

    public boolean isCodifiesFlag() {
        return codifiesFlag;
    }

    public boolean isTraceFlag() {
        return traceFlag;
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getOutpuFile() {
        return outpuFile;
    }

    public ChineseEncryption getChe() {
        return che;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Run run = (Run) o;
        return codifiesFlag == run.codifiesFlag && traceFlag == run.traceFlag && Objects.equals(outpuFile, run.outpuFile) && Objects.equals(inputFile, run.inputFile) && Objects.equals(che, run.che);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codifiesFlag, traceFlag, outpuFile, inputFile, che);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

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
