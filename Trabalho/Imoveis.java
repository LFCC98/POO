public class Imoveis implements Natureza
{
    private String tipo;
    private double limite;
    private double deducao;
    
    public Imoveis(){
       tipo = "";
       limite = 0;
       deducao = 0;
    }
    
    public Imoveis(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    public Imoveis(Imoveis i){
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
    
    public Imoveis clone(){
        return new Imoveis(this);
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
    Imoveis s = (Imoveis) o;
    
    if(tipo.equals(s.getTipo()) && limite == s.getLimite() && deducao == s.getDeducao())
        return true;
    return false;
    }
}