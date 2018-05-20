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