public class NaoExisteFaturaException extends Exception{
    /** Mensagem vazia no caso de nao existir uma fatura*/
    public NaoExisteFaturaException(){
        super();
    }
    /** Mensagem no caso de nao existir uma fatura*/
    public NaoExisteFaturaException(String str){
        super(str);
    }
}