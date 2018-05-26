/**
 * Classe que reporta a excecao de ja existir um Distrito 
 *
 */
public class JaExisteDistritoException extends Exception{
    /** Mensagem no caso de ja existir na lista de distritos do interior um distrito*/
    public JaExisteDistritoException(String msg){
        super(msg);
    }
    /** Mensagem vazia no caso de ja existir na lista um distrito*/
    public JaExisteDistritoException(){
        super();
    }
}