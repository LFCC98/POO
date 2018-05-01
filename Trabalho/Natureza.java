public abstract class Natureza{
    
    private String tipo;
    private double deducao;
    private double limite;
    
    public Natureza(){
        tipo = "";
        deducao = 0;
        limite = 0;
    }
    
    public Natureza(String tipo, double ded, double lim){
        tipo = tipo;
        deducao = ded;
        limite = lim;
    }
    
    public Natureza(Natureza n){
        tipo = n.getTipo();
        deducao = n.getDeducao();
        limite = n.getLimite();
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