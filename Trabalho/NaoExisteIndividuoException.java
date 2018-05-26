/**
 * Classe que reporta a excecao de nao existir um Individuo 
 *
 */
public class NaoExisteIndividuoException extends Exception{
    /** Mensagem no caso de nao existir um Individuo*/
    public NaoExisteIndividuoException(String msg){
        super(msg);
    }
    /** Mensagem vazia no caso de nao existir um certo individuo*/
    public NaoExisteIndividuoException(){
        super();
    }
}