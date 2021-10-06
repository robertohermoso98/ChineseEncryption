public class LengthMatrixException extends Exception{

    public LengthMatrixException(){
     super();
    }

    public String getMessage(){
        return ("El tamaño del el texto a descifrar no coincide con el tamaño de la matriz");
    }

}
