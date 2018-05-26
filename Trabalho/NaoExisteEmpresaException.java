/**
 * Classe que reporta a excecao de nao encontrar no sistema uma Empresa 
 *
 */
public class NaoExisteEmpresaException extends Exception{
    /** Mensagem vazia no caso de nao existir uma certa empresa*/
    public NaoExisteEmpresaException(){
        super();
    }
    /** Mensagem no caso de nao existir uma certa empresa*/
    public NaoExisteEmpresaException(String msg){
        super(msg);
    }
}
