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