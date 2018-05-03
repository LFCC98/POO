public class Imoveis implements Natureza
{
    private String tipo;
    private double limite;
    private double deducao;
    
    public Imoveis(){
       tipo = "";
       limite = 0;
       deducao = 0;
    }
    
    public Imoveis(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    public Imoveis(Imoveis i){
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