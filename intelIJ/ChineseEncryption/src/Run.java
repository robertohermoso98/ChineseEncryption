import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;

public class Run {

    private boolean codifiesFlag;
    private boolean traceFlag;
    private String outpuFile;
    private String inputFile;


    public Run(){
        codifiesFlag=true;
        traceFlag=true;
        outpuFile="salidas.txt";
        inputFile="entradas.txt";
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

    @Override
    public String toString() {
        return "Run{" +
                "codifiesFlag=" + codifiesFlag +
                ", traceFlag=" + traceFlag +
                ", outpuFile='" + outpuFile + '\'' +
                ", inputFile='" + inputFile + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Run run = (Run) o;
        return codifiesFlag == run.codifiesFlag && traceFlag == run.traceFlag && Objects.equals(outpuFile, run.outpuFile) && Objects.equals(inputFile, run.inputFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codifiesFlag, traceFlag, outpuFile, inputFile);
    }

}
