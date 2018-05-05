public class PPR implements Natureza{
    
    private String tipo;
    private double limite;
    private double deducao;
    
    public PPR(){
       tipo = "";
       limite = 0;
       deducao = 0;
    }
    
    public PPR(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    public PPR(PPR ppr){
        tipo = ppr.getTipo();
        limite = ppr.getLimite();
        deducao = ppr.getDeducao();
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
    
    public PPR clone(){
        return new PPR(this);
    }
    
    public String toString(){
        String s = "Tipo: " + tipo + "\nLimite: " + limite + "\nDeducao: " + deducao;
        return s;
    }
    
    public boolean equals(Object o){
    if(o == this)
        return true;
        
    if(o == null || o.getClass() != this.getClass())
        return false;
    PPR s = (PPR) o;
    
    if(tipo.equals(s.getTipo()) && limite == s.getLimite() && deducao == s.getDeducao())
        return true;
    return false;
    }
}