import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
    public static void main(String [] args){

       // Run run = new Run();
        // run.runPlay("config");
        ChineseEncryption che = new ChineseEncryption(4,4);
        System.out.println(che.encrytion("PRIMERAPRACTICA"));

        StringTokenizer st = new StringTokenizer("hol como estas");
        System.out.println(st.countTokens());
        while (st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }

    }
}
