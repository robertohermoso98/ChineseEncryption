

import java.io.File;
import java.util.StringTokenizer;

public class Main {
    public static void main(String [] args){


        ChineseEncryption ch = new ChineseEncryption();
        String nom=  "primerapractica";
        ch.setNumColum(5);
        ch.setSelection(true);
        ch.setColumRow(nom);
        System.out.println(ch.encrytion(nom));
        //System.out.println(ch.encrytion(ch.encrytion(ch.encrytion(ch.encrytion(ch.encrytion(ch.encrytion(nom)))))));
        System.out.println(ch.description(ch.encrytion(nom)));

         Run run = new Run();
        run.runPlay("config.txt");
        /*
        ChineseEncryption che = new ChineseEncryption(4,4);
        System.out.println(che.encrytion("PRIMERAPRACTICA"));

        StringTokenizer st = new StringTokenizer("hol como estas");
        System.out.println(st.countTokens());
        while (st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }

         */

    }
}
