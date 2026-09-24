package excepciones;

public class CupoExcedidoException extends Exception{

    public CupoExcedidoException(String tipoError){
        super(tipoError);
    }

}
