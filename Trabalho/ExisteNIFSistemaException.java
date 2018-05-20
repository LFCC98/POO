public class ExisteNIFSistemaException extends Exception{
    /** Mensagem no caso de existir um certo NIF*/
    public ExisteNIFSistemaException(String msg){
        super(msg);
    }
    /** Mensagem vazia no caso de existir um certo NIF*/
    public ExisteNIFSistemaException(){
        super();
    }
}