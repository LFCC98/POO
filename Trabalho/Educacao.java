public class Educacao implements Natureza
{
    private String tipo;
    private double limite;
    private double deducao;
    
    public Educacao(){
       tipo = "";
       limite = 0;
       deducao = 0;
    }
    
    public Educacao(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    private Educacao(Educacao e){
        tipo = e.getTipo();
        limite = e.getLimite();
        deducao = e.getDeducao();
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