import java.io.*;

/**
 * Classe que associa uma despesa a uma Empresa
 */
public class FaturaEmpresa implements Serializable{
    /**Identificaçao da Fatura*/
    private String id;
    /**NIF do individuo que a empresa prestou o serviço*/
    private int nif;
    
    /**Construtor vazio de uma FaturaEmpresa*/
    public FaturaEmpresa(){
        id = "";
        nif = 0;
    }
    /**Construtor com as variaveis de uma FaturaEmpresa*/
    public FaturaEmpresa(String id, int nif){
        this.id = id;
        this.nif = nif;
    }
    /**Construtor de uma FaturaEmpresa com uma FuturaEmpresa*/
    public FaturaEmpresa(FaturaEmpresa fe){
        id = fe.getId();
        nif = fe.getNIF();
    }
     /**
     * Metodo que retorna a Identificaçao de uma Fatura
     * 
     * @return Id da fatura
     */
    public String getId(){
        return id;
    }
     /**
     * Metodo que retorna a Identificaçao do Individuo em que a fatura foi passada
     * 
     * @return NIF do Individuo
     */
    public int getNIF(){
        return nif;
    }
     /**
     * Metodo que altera o Id de uma Fatura
     * 
     * @param id Novo valor da Identificaçao da Fatura
     */
    public void setId(String id){
        this.id = id;
    }
     /**
     * Metodo que altera o NIF de uma FaturaEmpresa
     * 
     * @param nif Novo valor do NIF do Individuo que a empresa passou a fatura
     */
    public void setNIF(int nif){
        this.nif = nif;
    }
     /**
     * Metodo que copia uma FaturaEmpresa
     * 
     * @return uma Copia da FaturaEmpresa
     */
    public FaturaEmpresa clone(){
        return new FaturaEmpresa(this);
    }
     /**
     * Metodo que compara um Objeto com uma FaturaEmpresa
     * 
     * @param o Objeto que ira ser comparado com a FaturaEmpresa
     * 
     * @return true caso sejam iguais, false caso contrario
     */
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        FaturaEmpresa fe = (FaturaEmpresa) o;
        if(id.equals(fe.getId()) && nif == fe.getNIF()) return true;
        return false;
    }
     /**
     * Metodo que transforma uma FaturaEmpresa numa String
     * 
     * @return uma String de uma FaturaEmpresa
     */
    public String toString(){
        return ("Id: " + id + "nif: " + nif);
    }
     /**
     * Metodo que retorna o hashCode de uma FaturaEmpresa
     * 
     * @return O hashCode de uma FaturaEmpresa
     */
    public int hashCode(){
        return (int) id.hashCode() * nif;
    }
}
