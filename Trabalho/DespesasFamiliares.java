public class DespesasFamiliares implements Natureza
{    
    private String tipo;
    private double limite;
    private double deducao;
    
    public DespesasFamiliares(){
        tipo = "";
        limite = 0;
        deducao = 0;
    }
    
    public DespesasFamiliares(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    public DespesasFamiliares(DespesasFamiliares d){
        tipo = d.getTipo();
        limite = d.getLimite();
        deducao = d.getDeducao();
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
    
    public DespesasFamiliares clone(){
        return new DespesasFamiliares(this);
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
    DespesasFamiliares d = (DespesasFamiliares) o;
    
    if(tipo.equals(d.getTipo()) && limite == d.getLimite() && deducao == d.getDeducao())
        return true;
    return false;
    }
}