import java.util.Objects;

public class ChineseEncryption  implements Cloneable {

    private int numColum;
    private int numRows;



    public ChineseEncryption(){
        numColum=3;
    }
    public ChineseEncryption(int colum, int rows){
        numColum=colum;
        numRows=rows;
    }

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

    public String encrytion(String plainText){

        Text text = new Text();
        String [] [] arrayString = new String [numRows][numColum];
        plainText=text.fillPlainText(plainText, numColum*numRows);
        fillArray(arrayString, plainText);
       return makeExitTextEncrypt(arrayString);
    }

   public String description(String plainText){
        String [] text = plainText.split("");
        String [][] textArray= new String[numColum][numRows];
        int cont=0;
        for (int i =0 ; i < numColum ; i++){
            for ( int e =0 ; e < numRows ; e++){
                textArray[i][e]=text[cont];
                cont++;
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
        for (int i =0 ; i<numColum; i++){
            for (int e=0; e<numRows; e++){
                s = s +(ss[i][e]);
            }
        }
        return s;
    }

    @Override
    public String toString() {
        return "ChineseEncryption{" +
                "numColum=" + numColum +
                ", numRows=" + numRows +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChineseEncryption that = (ChineseEncryption) o;
        return numColum == that.numColum && numRows == that.numRows;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numColum, numRows);
    }

    @Override
   public ChineseEncryption clone(){
        return new ChineseEncryption(this.numColum, this.numRows);
    }

}
