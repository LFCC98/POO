/**
 * Classe que reporta a excecao de nao se conseguir adicionar uma Natureza 
 *
 */
public class JaExisteNaturezaException extends Exception{
    /** Mensagem no caso de ja existir no sistema uma Natureza*/
    public JaExisteNaturezaException(String msg){
        super(msg);
    }
    /** Mensagem vazia no caso de ja existir no sistema uma Natureza*/
    public JaExisteNaturezaException(){
        super();
    }
}
