public class Saude extends Natureza
{
    private static String tipo;
    private static double deducao;
    private static double limite;
    
    public Saude(){
        tipo = "Saude";
        deducao = 0.25;
        limite = 500;
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