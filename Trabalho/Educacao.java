public class Educacao implements Natureza
{
    private String tipo;
    private double limite;
    private double deducao;
    
    public Educacao(){
       tipo = "";
       limite = 0;
       deducao = 0;
    }
    
    public Educacao(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    private Educacao(Educacao e){
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
    
    public Educacao clone(){
        return new Educacao(this);
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
    Educacao s = (Educacao) o;
    
    if(tipo.equals(s.getTipo()) && limite == s.getLimite() && deducao == s.getDeducao())
        return true;
    return false;
    }
}