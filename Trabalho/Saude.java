public class Saude implements Natureza
{
    private String tipo;
    private double limite;
    private double deducao;
    
    public Saude(){
        tipo = "";
        limite = 0;
        deducao = 0;
    }
    
    public Saude(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    public Saude(Saude s){
        tipo = s.getTipo();
        limite = s.getLimite();
        deducao = s.getDeducao();
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
       
    public Saude clone(){
        return new Saude(this);
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
    Saude s = (Saude) o;
    
    if(tipo.equals(s.getTipo()) && limite == s.getLimite() && deducao == s.getDeducao())
        return true;
    return false;
    }
}