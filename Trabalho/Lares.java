public class Lares implements Natureza{
    private String tipo;
    private double limite;
    private double deducao;
    
    public Lares(){
        tipo = "Lares";
        limite = 500;
        deducao = 0.25;   
    }
    
    public Lares(String tipo, double limite, double deducao){
        tipo = tipo;
        limite = limite;
        deducao = deducao;
    }
    
    public Lares(Lares l){
        tipo = l.getTipo();
        limite = l.getLimite();
        deducao = l.getDeducao();
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