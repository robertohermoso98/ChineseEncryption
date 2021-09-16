import java.util.Random;

public class Main {
    public static void main(String [] args){
        ChineseEncryption ce = new ChineseEncryption();
       // ce.encrytion("hola",2,2) ;

        Text t = new Text();
        String i= t.format("PRIMERA PRACTICA");
        System.out.println(i);
        i = t.fillPlainText(i, 16);
        System.out.println(i);
        i = ce.encrytion(i,4,4);
        System.out.println(i);
        i = ce.description(i,4,4);
        System.out.println(i);
    }
}
