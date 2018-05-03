public class Donativos implements Natureza{
    private String tipo;
    private double limite;
    private double deducao;
    
    public Donativos(){
        tipo = "Donativos";
        limite = 0.25;
        deducao = 500;
    }
    
    public Donativos(String tipo, double limite, double deducao){
        tipo = tipo;
        limite = limite;
        deducao = deducao;
    }
    
    public Donativos(Donativos d){
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
}