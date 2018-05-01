public class IVA_Faturas extends Natureza{
    
    public IVA_Faturas(){
        super("IVA_Faturas", 0.25, 500);
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