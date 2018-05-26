/**
 * Classe que reporta a excecao de nao se conseguir adicionar uma Natureza 
 *
 */
public class NaturezaInvalidaException extends Exception{
    /** Mensagem vazia no caso de nao existir uma certa Natureza */
    public NaturezaInvalidaException(){
        super();
    }
    /** Mensagem no caso de nao existir uma certa Natureza*/
    public NaturezaInvalidaException(String s){
        super(s);
    }
}