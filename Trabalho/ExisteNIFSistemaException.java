/**
 * Classe que reporta a excecao de existir um NIF no sistema aquele que se pretende inserir 
 *
 */
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