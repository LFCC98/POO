public class NaoExisteNIFException extends Exception{
    /**Mensagem no caso de nao existir um certo NIF*/
    public NaoExisteNIFException(String msg){
        super(msg);
    }
    /** Mensagem vazia no caso de nao existir um certo NIF*/
    public NaoExisteNIFException(){
        super();
    }
}