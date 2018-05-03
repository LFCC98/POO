public class IVA_Faturas implements Natureza{
    private String tipo;
    private double limite;
    private double deducao;
    
    public IVA_Faturas(){
        tipo = "IVA Faturas";
        limite = 500;
        deducao = 0.25;        
    }
    
    public IVA_Faturas(String tipo, double limite, double deducao){
        tipo = tipo;
        limite = limite;
        deducao = deducao;
    }
    
    public IVA_Faturas(IVA_Faturas i){
        tipo = i.getTipo();
        limite = i.getLimite();
        deducao = i.getDeducao();
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public double getDeducao(){
        return deducao;
    }
    
    public double getLimite(){
        return limite;
    }
    public IVA_Faturas clone(){
        return new IVA_Faturas(this);
    }
    
    public String toString(){
        String s = "Tipo: " + tipo + " Limite: " + limite + " Deducao:" + deducao;
        return s;
    }
    
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
            
        IVA_Faturas d = (IVA_Faturas) o;
        if(deducao == d.getDeducao() && tipo == d.getTipo() && limite == d.getLimite())
            return true;
        return false;
    }
}