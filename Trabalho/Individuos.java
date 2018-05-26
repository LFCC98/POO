import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.io.*;

/**
 * Classe que contem toda a informaçao de uma Pessoa do sistema 
 *
 */

public class Individuos extends Entidades implements Serializable{
     /**Tamanho do agregado familiar*/
    private int agregado;
     /**Set com os NIF do agregado familiar*/
    private Set<Integer> NIF_fam;
     /**Coeficiente fiscal do individuo*/
    private double coef_fiscal;
     /**Naturezas que o individuo pode descontar*/
    private Set<Natureza> codigo;
    
     /** Construtor vazio de Individuos*/
    public Individuos(){
    super();
    agregado = 0;
    coef_fiscal = 1;
    NIF_fam = new HashSet<>();
    codigo = new HashSet<>();
    }
     /** Construtor com todas as variaveis de instancia dos Individuos*/
    public Individuos(int nif, String e, String nome, String morada, String pass, int agregado, Set<Integer> NIF_fam, 
    double coef_fiscal, Set<Natureza> codigo){
    super(nif, e, nome, morada, pass);
    this.agregado = agregado;
    this.setNIF_fam(NIF_fam);
    this.coef_fiscal = coef_fiscal;
    setCodigo(codigo);
    }
     /** Construtor com um Individuo*/
    public Individuos(Individuos umIndividuo){
    super(umIndividuo);
    agregado =umIndividuo.getAgregado();
    NIF_fam = umIndividuo.getNIF_fam();
    coef_fiscal = umIndividuo.getCoef_fiscal();
    codigo = umIndividuo.getCodigo();
    }
     /** 
      * Metodo que retorna o tamanho do agregado familiar
     * 
     * @return Tamanho do agregado familiar
     */
    public int getAgregado(){
        return agregado;
    }
     /** 
      * Metodo que retorna o agregado familiar
     * 
     * @return Set do agregado familiar
     */
    public Set<Integer> getNIF_fam(){
        Set<Integer> s = new HashSet<>();
        for(Integer i: NIF_fam)
            s.add(i);
        return s;
    }
     /** 
      * Metodo que retorna o coeficiente fiscal do individuo
     * 
     * @return O coeficiente fiscal do individuo
     */    
    public double getCoef_fiscal(){
        return coef_fiscal;
    }
     /** 
      * Metodo que retorna as Naturezas que os Individuos podem descontar
     * 
     * @return Set de naturezas que o Individuo pode descontar
     */    
    public Set<Natureza> getCodigo(){
        return codigo.stream().map(nat -> nat.clone()).collect(Collectors.toSet());
    }
     /** 
      * Metodo que altera o tamanho do agregado familiar
     * 
     * @param agregado Tamanho do agregado familiar
     */    
    public void setAgregado(int agregado){
    this.agregado = agregado;
    }
     /** 
      * Metodo que altera agregado familiar
     * 
     * @param familia Agregado familiar tera o novo valor
     */ 
    public void setNIF_fam(Set<Integer> familia){
        NIF_fam = new HashSet<>();
        for(Integer i: familia){
            NIF_fam.add(i);
        }
    }
     /** 
      * Metodo que altera o coeficiente fiscal
     * 
     * @param coef_fiscal O coeficiente fiscal tera como novo valor
     */ 
    public void setCoef_fiscal(double coef_fiscal){
    this.coef_fiscal = coef_fiscal;
    }
     /** 
      * Metodo que altera o coeficiente fiscal
     * 
     * @param coef_fiscal O coeficiente fiscal tera como novo valor
     */
    public void setCodigo(Set<Natureza> cod){
        codigo = cod.stream().map(n -> n.clone()).collect(Collectors.toSet());
    }
     /** 
      * Metodo retorna uma copia do Individuo
     * 
     * @return Uma copia do Individuo
     */
    public Individuos clone(){
    return new Individuos(this);
    }
     /** 
      * Metodo que "transforma" um Individuo numa String
     * 
     * @return Uma String do Individuo
     */
    public String toString(){
    String s = "NIF: " + getNIF() + " Email: " + getEmail() + " Nome: " + getNome() + " Morada: " + getMorada() 
    + " Password: " + getPassword() + " Agregado: " + agregado + " Coeficiente fiscal" + coef_fiscal + " Codigo:";
    for(Natureza nat: codigo)
        s += nat.getTipo();
    s += " NIF familiar:";
    for(Integer i: NIF_fam){
        s+= " " + i;
    }
    return s ;
    }
     /** 
     * Metodo que verifica se um Objeto e igual a um Individuo
     * 
     * @param o Objeto que ira ser comparado com o individuo
     * 
     * @return true se forem iguais, false caso contrario
     */
    public boolean equals(Object o){
    if(o == this)
        return true;
        
    if(o == null || o.getClass() != this.getClass())
        return false;
    Individuos i = (Individuos) o;
    
    if(super.equals(i) && agregado == i.getAgregado() && i.getNIF_fam().equals(NIF_fam) && 
    coef_fiscal == i.getCoef_fiscal() && i.getCodigo().equals(codigo))
        return true;
    return false;
    }
     /** 
     * Metodo que retorna o hashCode de um Individuo
     * 
     * @return o HashCode de um Individuo
     */
    public int hashCode(){
        return this.getNIF();
    }
     /** 
     * Metodo que insere um Agregado num Individuo
     * 
     * @param i Inteiro que vai ser adicionado ao agregado familiar do Individuo
     */
    public void insereAgregado(int i) throws ExisteAgregadoException{
        if(NIF_fam.contains(i)){
            throw new ExisteAgregadoException("Individuo já está no agregado");
        }
        NIF_fam.add(i);
        this.agregado++;
    }
     /** 
     * Metodo que retorna um boolean corresponde, se e uma FamiliaNumerosa ou nao
     * 
     * @return boolean se o Individuo pertence a uma FamiliaNumerosa
     */
    public boolean eFamNumerosa(){
        if(this.getAgregado() >= 4)
            return true;
        return false;
    }
     /** 
     * Metodo que adiciona uma Natureza a um Individuo
     * 
     * @param n Natureza que vai ser adicionada
     */
    public void adicionaAtividade(Natureza n) throws JaExisteNaturezaException{
        if(codigo.contains(n)){
            throw new JaExisteNaturezaException("A atividade ja existe na empresa");
        }
        codigo.add(n.clone());
    }
}