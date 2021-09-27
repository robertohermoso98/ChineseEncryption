import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Locale;
import java.util.Objects;
import java.util.StringTokenizer;

public class Run {

    private boolean codifiesFlag;
    private boolean traceFlag;
    private String outpuFile;
    private String inputFile;
    private ChineseEncryption che;


    public Run(){
        codifiesFlag=true;
        traceFlag=true;
        outpuFile="salidas.txt";
        inputFile="entradas.txt";
        che=new ChineseEncryption();
    }

    public void runPlay(String fillName){
        String cadena;
        fillName=fillName+".txt";
        try {
            FileReader f = new FileReader(fillName);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                if(!cadena.equals("")){
                    chooseAction(cadena);
                }

            }
            b.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void chooseAction(String cadena){
        StringTokenizer st = new StringTokenizer(cadena);
        String [] stringArray = new String[st.countTokens()];
        int count =0;
        while( st.hasMoreTokens()){
            stringArray[count]=st.nextToken();
            count++;
        }
        if(stringArray[0].equals("@")){
            modificarBandera(stringArray[1],stringArray[2]);
        }else{
            chooseMethod(stringArray);
        }
    }

    public void modificarBandera(String st1 , String st2){
        boolean onOff;
        if(st2.equals("ON")){
            onOff=true;
        }else{
            onOff=false;
        }
        if(st1.equals("codifica")){
            codifiesFlag=onOff;
        }else{
            traceFlag=onOff;
        }
    }

   public void chooseMethod(String [] stringArray){
       String fuction = stringArray[1];
       if(fuction.equals("ficheroentrada")){
           inputFile=stringArray[2];
       }else{
           if(fuction.equals("ficherosalida")){
               outpuFile=stringArray[2];
           }else{
              if(fuction.equals("filas")){
                  che.setNumRows(Integer.parseInt(stringArray[2]));
               }else{
                 if(fuction.equals("columnas")){
                    che.setNumColum(Integer.parseInt(stringArray[2]));
                 }else{
                     china();
                 }
              }
           }
       }
    }

    public void china (){

    }

    public void formatInput(String fileInput){


    }
    public void show(String str){
        if(traceFlag){
            System.out.println(str);
        }
    }

    public void setChe(ChineseEncryption che) {
        this.che = che;
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
