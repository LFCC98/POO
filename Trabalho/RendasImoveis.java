public class RendasImoveis implements Natureza
{
    private String tipo;
    private double limite;
    private double deducao;
    
    public RendasImoveis(){
       tipo = "";
       limite = 0;
       deducao = 0;
    }
    
    public RendasImoveis(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    public RendasImoveis(RendasImoveis r){
        tipo = r.getTipo();
        limite = r.getLimite();
        deducao = r.getDeducao();
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
    
    public RendasImoveis clone(){
        return new RendasImoveis(this);
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
    RendasImoveis s = (RendasImoveis) o;
    
    if(tipo.equals(s.getTipo()) && limite == s.getLimite() && deducao == s.getDeducao())
        return true;
    return false;
    }
}