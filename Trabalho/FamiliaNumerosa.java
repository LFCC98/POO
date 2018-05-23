import java.util.*;

public class FamiliaNumerosa extends Individuos implements Desconto{

    public FamiliaNumerosa(){
       super();
    }
                        
    public FamiliaNumerosa(FamiliaNumerosa f){
        super(f);
    }
        
    public FamiliaNumerosa(int nif, String e, String nome, String morada, String pass, int agregado, Set<Integer> NIF_fam,
    double coef_fiscal, Set<Natureza> codigo){
        super(nif, e, nome, morada, pass, agregado, NIF_fam, coef_fiscal, codigo);
    }
    
    public FamiliaNumerosa clone(){
        return new FamiliaNumerosa(this);
    }
    
    public String toString(){
        String s = super.toString();
        return s;
    }
    
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        FamiliaNumerosa fn = (FamiliaNumerosa) o;
        if(super.equals(this))
            return true;
        return false;
    }
    

    
    public double reducaoImposto(){
        double t =1;
        if(eFamNumerosa())
            return 1 + Math.pow(0.05, getAgregado());
        return t;
    }
}