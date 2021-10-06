import java.util.Random;

/**
 * @author Roberto Hermoso Rivero
 *
 * @version 1.0
 */
public class Text {

    /**
     * Contructor por defecto de la clase.
     */
    public Text (){ }

    /**
     * Este metoodo sirve para rellenar con caracteres aletorios el string, hasta el tamaño que se le pase como parametro.
     * @param text : Es el String que se quiere rellenar.
     * @param size : Es el tamaño final del String.
     * @return String : Es el String rellando con el tamaño necesario.
     */
    public String fillPlainText(String text, int size){
        String [] splitText = text.split("");
        String exitText = new String();
        for (int i = 0 ; i<size; i++) { // iteremaos (size) veces, vamos agregando el caracter del String original si o uno aletorio segun proceda
            if (i >= splitText.length) {
                exitText = exitText + (randomLowercaseLetter());
            } else {
                exitText = exitText + (splitText[i]);
            }
        }
        return exitText;
    }

    /**
     * Este meotodo genera una String con un caracter aletorio. Solo genera letras mayusculas y minusculas.
     * @return String : Es el string con el caracter aletorio.
     */
    public String randomLowercaseLetter(){
        String exit = "";
        Random rnd = new Random();
        exit+= (char)(rnd.nextInt(122-97+1)+97);
        return exit;
    }

    /**
     * Esete metooo elimina de un String culaquier caracter que no sea una letra mayusculo o minuscula.
     * @param input : Es el String que se desea formatear.
     * @return String : Es el String formateado.
     */
    public String format(String input){
        String output = new String();
        String [] inputSplit = input.split("");
        for (int i = 0 ; i < inputSplit.length ; i ++ ){
            int code = inputSplit[i].charAt(0);
            if((code>=65 && code<=90) || (code <= 122 && code >= 97) ){// si es mayuscula se hace minuscula
                output=output+inputSplit[i];
            }
        }
        return output;
    }
}
