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

}