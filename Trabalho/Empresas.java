import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.io.*;
public class Empresas extends Entidades implements Serializable{
    /**Set de Naturezas da empresa na qual pode passar faturas dos tipos que dispoe*/
    private Set<Natureza> atividades;
    /**Deducao fiscal da empresa*/
    private double ded_fiscal;
    /**Construtor vazio da Empresas*/
    public Empresas(){
        super();
        atividades = new HashSet<>();
        ded_fiscal = 1;
    }
    /**Construtor com as variaveis da Empresa*/
    public Empresas(int nif, String e, String nome, String morada, String pass, Set<Natureza> nat, double deducao){
        super(nif, e, nome, morada, pass);
        this.ded_fiscal = deducao;
        setAtividades(nat);
    }
    /**Construtor de uma Empresa com uma Empresa*/
    public Empresas(Empresas e){
        super(e);
        atividades = e.getAtividades();
        ded_fiscal = e.getDeducao();
    }
     /**
     * Metodo que retorna a deducao fiscal de uma Empresa
     * 
     * @return Double com a deducao fiscal de uma Empresa
     */
    public double getDeducao(){
        return ded_fiscal;
    }
     /**
     * Metodo que retorna a lista de Naturezas que a Empresa pode passar
     * 
     * @return Set com as Naturezas que a Empresa pode passar
     */
    public Set<Natureza> getAtividades(){
        return atividades.stream().map(nat -> nat.clone()).collect(Collectors.toSet());
    }
     /**
     * Metodo que altera as atividades de uma Empresa
     * 
     * @param nat Novo valor das Atividades da empresa
     */
    public void setAtividades(Set<Natureza> nat){
        atividades = nat.stream().map(n -> n.clone()).collect(Collectors.toSet());
    }
     /**
     * Metodo que altera o valor da deducao fiscal de uma Empresa
     * 
     * @param fiscal Novo valor da deducaoFiscal da empresa
     */
    public void setDeducao(double fiscal){
        ded_fiscal = fiscal;
    }
     /**
     * Metodo que retorna uma copia da Empresa
     * 
     * @return Copia da Empresa
     */
    public Empresas clone(){
        return new Empresas(this);
    }
     /**
     * Metodo que transforma uma Empresa numa String
     * 
     * @return String de uma Empresa
     */
    public String toString (){
        String s = "NIF: " + getNIF() + " Email: " + getEmail() + " Nome: " + getNome() + " Morada: " + getMorada() 
        + " Password: " + getPassword() + " Deducao Fiscal: " + ded_fiscal + " Atividades: ";
        for(Natureza nat: atividades)
            s += nat.toString();
        return s;
    }
     /**
     * Metodo que compara um objeto com uma Empresa
     * 
     * @param o Objeto que ira ser comparado com a empresa
     * 
     * @return true caso sejam guais, false caso contrario
     */
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        Empresas i = (Empresas)o;
        if(super.equals(i) && ded_fiscal == i.getDeducao() && atividades.equals(i))
            return true;
        return false;
    }
     /**
     * Metodo que retorna o hashCode de uma Empresa
     * 
     * @return hashCode de uma Empresa
     */
    public int hashCode(){
        return this.getNIF();
    }
     /**
     * Metodo que adiciona uma atividade Economica de uma Empresa
     * 
     * @param n Natureza que ira ser inserida
     */
    public void adicionaAtividade(Natureza n) throws JaExisteNaturezaException{
        if(atividades.contains(n)){
            throw new JaExisteNaturezaException("A atividade ja existe na empresa");
        }
        atividades.add(n.clone());
    }
}
