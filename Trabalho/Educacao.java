public class Educacao extends Natureza
{
    public Educacao(){
       super("Educacao", 0.25, 5000);
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