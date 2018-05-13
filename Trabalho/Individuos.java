import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Individuos extends Entidades
{
    private int agregado;
    private Set<Integer> NIF_fam;
    private double coef_fiscal;
    private Set<Natureza> codigo;
    
    public Individuos(){
    super();
    agregado = 0;
    coef_fiscal = 0;
    NIF_fam = new HashSet<>();
    codigo = new HashSet<>();
    }
    
    public Individuos(int agregado, Set<Integer> NIF_fam, double coef_fiscal, Set<Natureza> codigo, int nif, String e,
    String nome, String morada, String pass){
    super(nif, e, nome, morada, pass);
    agregado = agregado;
    setNIF_fam(NIF_fam);
    coef_fiscal = coef_fiscal;
    setCodigo(codigo);
    }
    
    public Individuos(Individuos umIndividuo){
    super(umIndividuo);
    agregado =umIndividuo.getAgregado();
    NIF_fam = umIndividuo.getNIF_fam();
    coef_fiscal = umIndividuo.getCoef_fiscal();
    codigo = umIndividuo.getCodigo();
    }
        
    public int  getAgregado(){
        return agregado;
    }
    
    public Set<Integer> getNIF_fam(){
        return NIF_fam;
    }
    
    public double getCoef_fiscal(){
        return coef_fiscal;
    }
    
    public Set<Natureza> getCodigo(){
        return codigo.stream().map(nat -> nat.clone()).collect(Collectors.toSet());
    }
    
    public void setAgregado(int agregado){
    agregado = agregado;
    }
    
    public void setNIF_fam(Set<Integer> NIF_fam){
        NIF_fam = new HashSet<>();
        for(Integer i: NIF_fam){
            NIF_fam.add(i);
        }
    }
    
    public void setCoef_fiscal(double coef_fiscal){
    coef_fiscal = coef_fiscal;
    }
    
    public void setCodigo(Set<Natureza> cod){
        codigo = cod.stream().map(n -> n.clone()).collect(Collectors.toSet());
    }
    
    public Individuos clone(){
    return new Individuos(this);
    }
    
    public String toString(){
    String s = "NIF: " + getNIF() + " Email: " + getEmail() + " Nome: " + getNome() + " Morada: " + getMorada() 
    + " Password: " + getPassword() + " Agregado: " + agregado + " Coeficiente fiscal" + coef_fiscal + " Codigo:";
    for(Natureza nat: codigo)
        s += nat.toString();
    s += " NIF familiar:";
    for(Integer i: NIF_fam){
        s+= " " + i;
    }
    return s ;
    }
    
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
    /** Alterar para o caso de s nao ser uma natureza*/
    public void escolheNatureza(Fatura f, String s) throws NaturezaInvalidaException{
        if(!codigo.contains(s)){
            throw new NaturezaInvalidaException("Nao pode escolher a natureza " + s);
        }
        else {
            f.escolheNatureza(s);
        }
    }
    
    public void insereAgregado(int i){
        NIF_fam.add(i);
    }
}
