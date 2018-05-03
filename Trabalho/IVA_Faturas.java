public class IVA_Faturas implements Natureza{
    private String tipo;
    private double limite;
    private double deducao;
    
    public IVA_Faturas(){
        tipo = "IVA Faturas";
        limite = 500;
        deducao = 0.25;        
    }
    
    public IVA_Faturas(String tipo, double limite, double deducao){
        tipo = tipo;
        limite = limite;
        deducao = deducao;
    }
    
    public IVA_Faturas(IVA_Faturas i){
        tipo = i.getTipo();
        limite = i.getLimite();
        deducao = i.getDeducao();
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