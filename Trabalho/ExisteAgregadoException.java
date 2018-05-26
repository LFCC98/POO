/**
 * Classe que reporta a excecao de nao conseguir adicionar um agregado Familiar 
 *
 */
public class ExisteAgregadoException extends Exception{
    /** Mensagem no caso de nao ser possivel adicionar um agregado familiar*/
    public ExisteAgregadoException(String msg){
        super(msg);
    }
    /** Mensagem vazia no caso de nao ser possivel  adicionar um agregado familiar*/
    public ExisteAgregadoException(){
        super();
    }
}