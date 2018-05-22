import java.util.*;


public class FamiliaNumerosa extends Individuos{
    private boolean famNum;
        
    public FamiliaNumerosa(){
       super();
        famNum = false;
    }
                        
    public FamiliaNumerosa(FamiliaNumerosa f){
        super(f);
        famNum = f.getFamNum();
    }
        
    public FamiliaNumerosa(int nif, String e, String nome, String morada, String pass, int agregado, Set<Integer> NIF_fam, 
    double coef_fiscal, Set<Natureza> codigo, boolean famNum){
        super(nif, e, nome, morada, pass, agregado, NIF_fam, coef_fiscal, codigo);
        this.famNum = famNum;
    }
        
    public boolean getFamNum(){
        return famNum;
    }
    
    public void setFamNum(boolean f){
        this.famNum = f;
    }
    
    public FamiliaNumerosa clone(){
        return new FamiliaNumerosa(this);
    }
    
    public String toString(){
        String s = super.toString() + " E uma familia numerosa: " + famNum;
        return s;
    }
    
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        FamiliaNumerosa fn = (FamiliaNumerosa) o;
        if(super.equals(this) && famNum == fn.getFamNum())
            return true;
        return false;
    }
}