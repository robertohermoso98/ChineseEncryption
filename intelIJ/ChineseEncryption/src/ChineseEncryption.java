
public class ChineseEncryption {

    public ChineseEncryption(){

    }
    public String encrytion(String plainText, int x, int y){
        Text text = new Text();
        String [] [] arrayString = new String [y][x];
        plainText=text.fillPlainText(plainText, x*y);
        fillArray(arrayString, plainText, x, y);
       return makeExitTextEncrypt(arrayString,x,y);
    }

   public String description(String plainText, int x, int y){
        String [] text = plainText.split("");
        String [][] textArray= new String[x][y];
        int cont=0;
        for (int i =0 ; i < x ; i++){
            for ( int e =0 ; e < y ; e++){
                textArray[i][e]=text[cont];
                cont++;
            }
        }
        return makeExitTextDescrytp(textArray,x,y);
    }

    public String makeExitTextDescrytp(String [][] textArray, int x, int y){
        String exit = "";
        boolean sentido = true;
        for (int i=x; i>0; i--) {
            if(sentido){
                for (int e=y; e>0; e--){
                    exit = exit + (textArray[e - 1][i - 1]);
                }
                sentido = !sentido;
            }else{
                for ( int e=0; e<y; e++ ){
                    exit = exit +(textArray[e][i - 1]);
                }
                sentido=!sentido;
            }
        }
        return exit;
    }


    public void fillArray(String [][] arrayString, String plainText, int x, int y){ // primer for recorre la X, el sgundo la Y
        boolean sentido = true; //cambio el sentido del recorrido de las columnas
        String [] arrayPlainText = plainText.split(""); // spliteo la frase para ir cogindo sus letras
        int position=0;
        for (int i=x; i>0; i--) { // el primer for recorre las filas
            if(sentido){  // los otros dos for recorren las columnas se le cambia el sentido a cada recorrido
                for (int e=y; e>0; e--){
                    arrayString [e-1][i-1]=arrayPlainText[position];
                    position++;
                }
                sentido = !sentido;
            }else{
                for ( int e=0; e<y; e++ ){
                    arrayString [e][i-1]=arrayPlainText[position];
                    position++;
                }
                sentido=!sentido;
            }
        }
    }

    public String makeExitTextEncrypt(String [] [] ss, int x , int y){
        String s= new String();
        for (int i =0 ; i<x; i++){
            for (int e=0; e<y; e++){
                s = s +(ss[i][e]);
            }
        }
        return s;
    }





}
