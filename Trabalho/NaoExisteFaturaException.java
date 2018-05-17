public class NaoExisteFaturaException extends Exception
{
    public NaoExisteFaturaException(){
        super();
    }
    
    public NaoExisteFaturaException(String str){
        super(str);
    }
}