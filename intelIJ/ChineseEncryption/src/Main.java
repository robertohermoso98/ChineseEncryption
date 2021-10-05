

import java.io.File;
import java.util.StringTokenizer;

/**
 * @author Roberto Hermos Rivero
 * @version 1.1.0
 */
public class Main {

    /**
     * Metodo main, incia el programa
     * @param args : argumentos del programa
     */
    public static void main(String[] args) {
        String mensajeError = "Error en el numero de argumentos\nPara buscar ayude ejecute elargumento -h\n" +
                "Para ejecutar el programa ejcute el argumento -f seguido del nombre del fichero de configuracion";
        if (args.length < 1 || args.length > 2) {
            System.out.println(mensajeError);
        } else {
            if (args.length == 1 && args[0].equals("-h")) {
                mensajeAyuda();
            } else {
                init(args);
            }
        }
    }

    /**
     * Ejecuta el programa
     * @param args : argumentos para el programa
     */
    public static void init(String [] args) {
        if (args.length == 2 && args[0].equals("-f")) {
            File f = new File(args[1]);
            if (f.exists()) {
                Run r = new Run();
                r.runPlay(args[1]);
            } else {
                System.out.println("El fichero de configuracion no existe");
            }
        }
    }

    /**
     * Muestra el mensaje de ayuda
     */
    public static void mensajeAyuda() {
        System.out.println("- Ayuda - ");
        System.out.println("Para ejecutar el programa debe de realizar un fichero de configuracion\n" +
                "este fichero tiene tres tipos de comandos:\n- # - los cuales son comentariso\n" +
                "- @ - el cual sirve para modificar las banderas (traza o codifica) con un argumento que puede ser ON o Off" +
                "- & - el cual ira seguido de algunos de los comandos que quieras ejecutar\n" +
                "Lista comandos :\n" +
                "- china : Ejecuta el cifrado o descifrado segun este la bandera cifra\n" +
                "- formateaentrada : Ejecuta el formateo para la limpieza del fichero de entrada\n" +
                "- ficherosalida <nomFicheroSalida> : Selecciona el fichero de salida (salida.txt por defecto)\n" +
                "- ficheroentrada <nomFicheroEntrada> : Selecciona el fichero de entrada (entrada.txt por defecto)\n" +
                "- columas : Selecciona la cantidad de columas y las gurada como clave\n" +
                "- filas : Selecciona la cantidad de filas y las guarda como clave");
    }
}
