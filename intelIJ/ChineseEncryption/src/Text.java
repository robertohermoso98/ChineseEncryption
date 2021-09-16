import java.util.Random;

// esta es la clase que se encarga de las operaciones de con los textos
public class Text {

    public Text (){ }

    public String fillPlainText(String text, int size){
        String [] splitText = text.split("");
        String exitText = new String();
        for (int i = 0 ; i<size; i++) {
            if (i >= splitText.length) {
                exitText = exitText + (randomLowercaseLetter());
            } else {
                exitText = exitText + (splitText[i]);
            }
        }
        return exitText;
    }

    public String randomLowercaseLetter(){
        String exit = "";
        Random rnd = new Random();
        exit+= (char)(rnd.nextInt(122-97+1)+97);
        return exit;
    }

    public String randomLetter(){
        String exit = "";
        Random rnd = new Random();
        int num =rnd.nextInt(255-32+1)+32;
        while(num==127){ // EL 127 no es ningun char
           num =  rnd.nextInt(255-32+1)+32;
        }
        exit+= (char)(num);
        return exit;
    }

    public String format(String input){
        String output = new String();
        String [] inputSplit = input.split("");
        for (int i = 0 ; i < inputSplit.length ; i ++ ){
            int code = inputSplit[i].charAt(0);
            if(code>=65 && code<=90) {// si es mayuscula se hace minuscula
                code = code + 32;
                String aux = String.valueOf((char) code);
                inputSplit[i]=aux;
                if (code <= 122 && code >= 97) {
                    output = output + (inputSplit[i]);
                }
            }
        }
        return output;
    }
}
