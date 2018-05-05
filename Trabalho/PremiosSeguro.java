public class PremiosSeguro implements Natureza
{
    private String tipo;
    private double limite;
    private double deducao;
    
    public PremiosSeguro(){
       tipo = "";
       limite = 0;
       deducao = 0;
    }
    
    public PremiosSeguro(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    public PremiosSeguro(PremiosSeguro ps){
        tipo = ps.getTipo();
        limite = ps.getLimite();
        deducao = ps.getDeducao();
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
    
    public PremiosSeguro clone(){
        return new PremiosSeguro(this);
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
    PremiosSeguro s = (PremiosSeguro) o;
    
    if(tipo.equals(s.getTipo()) && limite == s.getLimite() && deducao == s.getDeducao())
        return true;
    return false;
    }
}