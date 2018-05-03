public class Regime_Capitalizacao implements Natureza{
    private String tipo;
    private double limite;
    private double deducao;
    
    public Regime_Capitalizacao(){
        tipo = "Regime Publico de Capitalizacao"; 
        deducao = 0.25;
        limite = 500;
    }
    
    public Regime_Capitalizacao(String tipo, double limite, double deducao){
        tipo = tipo;
        limite = limite;
        deducao = deducao;
    }
    
    public Regime_Capitalizacao(Regime_Capitalizacao r){
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
    
    public Regime_Capitalizacao clone(){
        return new Regime_Capitalizacao(this);
    }
    
    public String toString(){
        String s = "Tipo: " + tipo + " Limite: " + limite + " Deducao:" + deducao;
        return s;
    }
    
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
            
        Regime_Capitalizacao d = (Regime_Capitalizacao) o;
        if(deducao == d.getDeducao() && tipo == d.getTipo() && limite == d.getLimite())
            return true;
        return false;
    }
}