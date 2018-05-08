public class Outro implements Natureza
{
private String tipo;
    private double limite;
    private double deducao;
    
    public Outro(){
        tipo = "";
        limite = 0;
        deducao = 0;
    }
    
    public Outro(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    public Outro(Outro d){
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
    
    public Outro clone(){
        return new Outro(this);
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
    Outro d = (Outro) o;
    
    if(tipo.equals(d.getTipo()) && limite == d.getLimite() && deducao == d.getDeducao())
        return true;
    return false;
    }
}
