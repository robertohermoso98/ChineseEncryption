import java.io.BufferedReader;
import java.io.FileReader;

public class Run {

    private boolean codifiesFlag=true;
    private boolean traceFlag=true;
    private String outpuFile="salida.txt";
    private String inputFile="entrada.txt";

    public Run(){
        codifiesFlag=
    }

    public void runPlay(String fillName){
        String cadena;
        fillName=fillName+".txt";
        try {
            FileReader f = new FileReader(fillName);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                if(!cadena.equals(""))
                    System.out.println(cadena);
            }
            b.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void formatInput(String fileInput){


    }
    public void show(String str){
        if(traceFlag){
            System.out.println(str);
        }
    }
}
