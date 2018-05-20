public class FaturaValidaException extends Exception{
    /** Mensagem vazia no caso de a fatura nao ser valida*/
    public FaturaValidaException(){
        super();
    }
    /** Mensagem no caso de a fatura nao ser valida*/
    public FaturaValidaException(String msg){
        super(msg);
    }
}