public class RemoverNaturezaException extends Exception{
    /** Mensagem vazia no caso de nao existir um certo NIF*/
    public RemoverNaturezaException(){
        super();
    }
    /** Mensagem de erro no caso de nao ser possivel remover uma natureza*/
    public RemoverNaturezaException(String msg){
        super(msg);
    }
}
