import java.util.Objects;

public class ChineseEncryption  implements Cloneable {

    private int numColum;
    private int numRows;
    private boolean selection;

    public ChineseEncryption(){

        numColum=3;
        numRows=0;
        selection=true;
    }
    public ChineseEncryption(int colum, int rows){
        numColum=colum;
        numRows=rows;
        selection=true;
    }

    public boolean isSelection() { return selection; }

    public void setSelection(boolean selection) { this.selection = selection; }

    public int getNumColum() {
        return numColum;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumColum(int numColum) {
        this.numColum = numColum;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public String encrytion(String plainText, boolean imprimir){

        Text text = new Text();
        String [] [] arrayString = new String [numRows][numColum];
        plainText=text.fillPlainText(plainText, numColum*numRows);
        fillArray(arrayString, plainText);
       return makeExitTextEncrypt(arrayString);
    }

    public void setColumRow(String text){
        int size = text.split("").length;
        if(selection){
            numRows=techo(size,numColum);
        }else{
            numColum=techo(size,numRows);
        }
    }

    public int techo(int a, int b){
        float fa=(float) a;
        float fb=(float) b;
        int result=a/b;
        if(fa%fb!=0){
            result++;
        }
        return result;
    }

   public String description(String plainText, boolean imprimir){
        String [] text = plainText.split("");
        String [][] textArray= new String[numRows][numColum];
        int cont=0;
        for (int i =0 ; i < numRows ; i++){
            for ( int e =0 ; e < numColum ; e++){
                textArray[i][e]=text[cont];
                if(imprimir){
                    System.out.print(text[cont]);
                }
                cont++;
            }
            if(imprimir){
                System.out.println("");
            }
        }
        return makeExitTextDescrytp(textArray);
    }

    public String makeExitTextDescrytp(String [][] textArray){
        String exit = "";
        boolean sentido = true;
        for (int i=numColum; i>0; i--) {
            if(sentido){
                for (int e=numRows; e>0; e--){
                    exit = exit + (textArray[e - 1][i - 1]);
                }
                sentido = !sentido;
            }else{
                for ( int e=0; e<numRows; e++ ){
                    exit = exit +(textArray[e][i - 1]);
                }
                sentido=!sentido;
            }
        }
        return exit;
    }


    public void fillArray(String [][] arrayString, String plainText){ // primer for recorre la X, el sgundo la Y
        boolean sentido = true; //cambio el sentido del recorrido de las columnas
        String [] arrayPlainText = plainText.split(""); // spliteo la frase para ir cogindo sus letras
        int position=0;
        for (int i=numColum; i>0; i--) { // el primer for recorre las filas
            if(sentido){  // los otros dos for recorren las columnas se le cambia el sentido a cada recorrido
                for (int e=numRows; e>0; e--){
                    arrayString [e-1][i-1]=arrayPlainText[position];
                    position++;
                }
                sentido = !sentido;
            }else{
                for ( int e=0; e<numRows; e++ ){
                    arrayString [e][i-1]=arrayPlainText[position];
                    position++;
                }
                sentido=!sentido;
            }
        }
    }

    public String makeExitTextEncrypt(String [] [] ss){
        String s= new String();
        for (int i =0 ; i<numRows; i++){
            for (int e=0; e<numColum; e++){
                s = s +(ss[i][e]);
            }
        }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChineseEncryption that = (ChineseEncryption) o;
        return numColum == that.numColum && numRows == that.numRows && selection == that.selection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numColum, numRows, selection);
    }

    @Override
    public String toString() {
        return "ChineseEncryption{" +
                "numColum=" + numColum +
                ", numRows=" + numRows +
                ", selection=" + selection +
                '}';
    }

    @Override
   public ChineseEncryption clone(){
        return new ChineseEncryption(this.numColum, this.numRows);
    }

}
