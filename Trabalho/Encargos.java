public class Encargos implements Natureza
{
    private String tipo;
    private double limite;
    private double deducao;
    
    public Encargos(){
        tipo = "";
        limite = 0;
        deducao = 0;
    }
    
    public Encargos(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    public Encargos(Encargos e){
        tipo = e.getTipo();
        limite = e.getLimite();
        deducao = e.getDeducao();
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
    
    public Encargos clone(){
        return new Encargos(this);
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
    Encargos s = (Encargos) o;
    
    if(tipo.equals(s.getTipo()) && limite == s.getLimite() && deducao == s.getDeducao())
        return true;
    return false;
    }
}