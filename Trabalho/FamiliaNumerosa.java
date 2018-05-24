import java.util.*;

public class FamiliaNumerosa extends Individuos implements Desconto{
    /**Construtor vazio de uma familia numerosa*/
    public FamiliaNumerosa(){
       super();
    }
    /**Construtor de uma familia numerosa atraves de uma familia numerosa*/
    public FamiliaNumerosa(FamiliaNumerosa f){
        super(f);
    }
    /**Construtor de uma Familia numerosa com todas as variaveis como argumento*/
    public FamiliaNumerosa(int nif, String e, String nome, String morada, String pass, int agregado, Set<Integer> NIF_fam,
    double coef_fiscal, Set<Natureza> codigo){
        super(nif, e, nome, morada, pass, agregado, NIF_fam, coef_fiscal, codigo);
    }
     /** 
      * Metodo que faz copia de uma FamiliaNumerosa com diferentes posiçoes de memoria
     *
     *@return Uma copia de uma FamiliaNumerosa
     */
    public FamiliaNumerosa clone(){
        return new FamiliaNumerosa(this);
    }
    /** 
     * Metodo que transforma uma FamiliaNumerosa numa String
     * 
     * @return uma String de uma FamiliaNumerosa
     */
    public String toString(){
        String s = super.toString();
        return s;
    }
     /** 
      * Metodo que verifica se uma FamiliaNumerosa e igual a um objeto
     * 
     * @return uma String de uma FamiliaNumerosa
     */
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        FamiliaNumerosa fn = (FamiliaNumerosa) o;
        if(super.equals(o))
            return true;
        return false;
    }
    /** 
     * Metodo que calcula a percentagem que ira aumentar pela quantidade de filhos
     * 
     * @return Um double com a percentagem que ira aumentar 
     */
    public double reducaoImposto(){
        double t =1;
        if(eFamNumerosa())
            return 1 + Math.pow(0.05, getAgregado());
        return t;
    }
}