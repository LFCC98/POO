public class Regime_Capitalizacao extends Natureza{
    
    public Regime_Capitalizacao(){
        super("Regime Publico de Capitalizacao", 0.25, 500);
    }
    
    public String getTipo(){
        return super.getTipo();
    }
    
    public double getDeducao(){
        return super.getDeducao();
    }
    
    public double getLimite(){
        return super.getLimite();
    }

}