public class RendasImoveis implements Natureza
{
    private String tipo;
    private double limite;
    private double deducao;
    
    public RendasImoveis(){
       tipo = "";
       limite = 0;
       deducao = 0;
    }
    
    public RendasImoveis(String type, double lim, double deduz){
        tipo = type;
        limite = lim;
        deducao = deduz;
    }
    
    public RendasImoveis(RendasImoveis r){
        tipo = r.getTipo();
        limite = r.getLimite();
        deducao = r.getDeducao();
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