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
    
    public Individuos(int nif, String e, String nome, String morada, String pass, int agregado, Set<Integer> NIF_fam, 
    double coef_fiscal, Set<Natureza> codigo){
    super(nif, e, nome, morada, pass);
    this.agregado = agregado;
    this.setNIF_fam(NIF_fam);
    this.coef_fiscal = coef_fiscal;
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
    
    public void setNIF_fam(Set<Integer> familia){
        NIF_fam = new HashSet<>();
        for(Integer i: familia){
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
    
    public int hashCode(){
        return this.getNIF();
    }
    
    public void escolheNatureza(Fatura f, Natureza n) throws NaturezaInvalidaException{
        if(!codigo.contains(n)){
            throw new NaturezaInvalidaException("Nao pode escolher a natureza " + n.getTipo());
        }
        try{
            f.escolheNatureza(n);
        }
        catch (NaturezaInvalidaException exc){
            throw new NaturezaInvalidaException(exc.getMessage());
        }
    }
    
    public void insereAgregado(int i) throws ExisteAgregadoException{
        if(NIF_fam.contains(i)){
            throw new ExisteAgregadoException("Individuo já está no agregado");
        }
        NIF_fam.add(i);
    }
    
    public void adicionaAtividade(Natureza n) throws JaExisteNaturezaException{
        if(codigo.contains(n)){
            throw new JaExisteNaturezaException("A atividade ja existe na empresa");
        }
        codigo.add(n.clone());
    }    
        
    public boolean eFamNumerosa(Individuos i){
        if(i.getNIF_fam().size() >= 1)
            return true;        
        return false;
    }
    
    public void mudaClass(Individuos i){
        if(eFamNumerosa(i)){
            Individuos f = new FamiliaNumerosa(i.getNIF(), i.getNome(), i.getNome(), i.getMorada(), i.getPassword(), i.getAgregado(),
            i.getNIF_fam(), i.getCoef_fiscal(), i.getCodigo(), true);
        }            
    }
}
