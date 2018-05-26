/**
 * Classe que reporta a excecao de ja existir uma Fatura com um certo Id no sistema 
 *
 */
public class ExisteFaturaException extends Exception{
    /** Mensagem no caso de existir um certo Fatura*/
    public ExisteFaturaException(String msg){
        super(msg);
    }
    /** Mensagem vazia no caso de existir um certo Fatura*/
    public ExisteFaturaException(){
        super();
    }
}