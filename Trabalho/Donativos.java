public class Donativos extends Natureza{

    
    public Donativos(){
        super("Donativos", 0.25, 500);
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