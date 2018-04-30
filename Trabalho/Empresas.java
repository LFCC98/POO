import java.util.Set;
import java.util.HashSet;
public class Empresas extends Entidades
{
    private Set<String> atividades;
    private double ded_fiscal;
    
    public Empresas(){
        super();
        atividades = new HashSet<>();
        ded_fiscal = 0;
    }
    
    public Empresas(int nif, String e, String nome, String morada, String pass, Set<String> act, double deducao){
        super(nif, e, nome, morada, pass);
        ded_fiscal = deducao;
        Set<String> novo = new HashSet<>();
        for(String s: act){
            novo.add(s);
        }
        atividades = novo;
    }
    
    public Empresas(Empresas e){
        super(e);
        atividades = e.getAtividades();
        ded_fiscal = e.getDeducao();
    }
    
    public double getDeducao(){
        return ded_fiscal;
    }
    
    public Set<String> getAtividades(){
        Set<String> s = new HashSet<>();
        for(String str : atividades)
            s.add(str);
        return s;
    }
    
    public void setDeducao(double fiscal){
        ded_fiscal = fiscal;
    }
    
    public void setAtividades(Set<String> act){
        Set<String> s = new HashSet<>();
        for(String str : act)
            s.add(str);
        atividades = s;
    }
    
    public Empresas clone(){
        return new Empresas(this);
    }

    public String toString (){
        String s = "NIF: " + getNIF() + " Email: " + getEmail() + " Nome: " + getNome() + " Morada: " + getMorada() 
        + " Password: " + getPassword() + " Deducao Fiscal: " + ded_fiscal + " Atividades: ";
        for(String str : atividades)
            s += str + ", ";
        return s;
    }
    
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        Empresas i = (Empresas)o;
        if(getNIF() == i.getNIF() && getEmail() == i.getEmail() && getNome() == i.getNome() && getMorada() == i.getMorada() 
        && getPassword() == i.getPassword() && ded_fiscal == i.getDeducao() && atividades.equals(i))
            return true;
        return false;
    }
}
