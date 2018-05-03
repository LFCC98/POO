public class Saude implements Natureza
{
    private String tipo;
    private double limite;
    private double deducao;
    
    public Saude(){
        tipo = "";
        limite = 0;
        deducao = 0;
    }
    
    public Saude(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    public Saude(Saude s){
        tipo = s.getTipo();
        limite = s.getLimite();
        deducao = s.getDeducao();
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