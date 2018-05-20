import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
public class Empresas extends Entidades
{
    private Set<Natureza> atividades;
    private double ded_fiscal;
    
    public Empresas(){
        super();
        atividades = new HashSet<>();
        ded_fiscal = 0;
    }
    
    public Empresas(int nif, String e, String nome, String morada, String pass, Set<Natureza> nat, double deducao){
        super(nif, e, nome, morada, pass);
        this.ded_fiscal = deducao;
        setAtividades(nat);
    }
    
    public Empresas(Empresas e){
        super(e);
        atividades = e.getAtividades();
        ded_fiscal = e.getDeducao();
    }
    
    public double getDeducao(){
        return ded_fiscal;
    }
    
    public Set<Natureza> getAtividades(){
        return atividades.stream().map(nat -> nat.clone()).collect(Collectors.toSet());
    }
    
    public void setAtividades(Set<Natureza> nat){
        atividades = nat.stream().map(n -> n.clone()).collect(Collectors.toSet());
    }
    
    public void setDeducao(double fiscal){
        ded_fiscal = fiscal;
    }
    
    public Empresas clone(){
        return new Empresas(this);
    }

    public String toString (){
        String s = "NIF: " + getNIF() + " Email: " + getEmail() + " Nome: " + getNome() + " Morada: " + getMorada() 
        + " Password: " + getPassword() + " Deducao Fiscal: " + ded_fiscal + " Atividades: ";
        for(Natureza nat: atividades)
            s += nat.toString();
        return s;
    }
    
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
    
    public int hashCode(){
        return this.getNIF();
    }
    
    public void adicionaAtividade(Natureza n) throws JaExisteNaturezaException{
        if(atividades.contains(n)){
            throw new JaExisteNaturezaException("A atividade ja existe na empresa");
        }
        atividades.add(n.clone());
    }
}
