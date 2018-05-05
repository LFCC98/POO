public class Pensoes_Alimentos implements Natureza{

    private String tipo;
    private double limite;
    private double deducao;
    
    public Pensoes_Alimentos(){
       tipo = "";
       limite = 0;
       deducao = 0;
    }
    
    public Pensoes_Alimentos(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    public Pensoes_Alimentos(Pensoes_Alimentos pa){
        tipo = pa.getTipo();
        limite = pa.getLimite();
        deducao = pa.getDeducao();
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
    
    public Pensoes_Alimentos clone(){
        return new Pensoes_Alimentos(this);
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
    Pensoes_Alimentos s = (Pensoes_Alimentos) o;
    
    if(tipo.equals(s.getTipo()) && limite == s.getLimite() && deducao == s.getDeducao())
        return true;
    return false;
    }
}