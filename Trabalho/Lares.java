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
    public Lares clone(){
        return new Lares(this);
    }
    
    public String toString(){
        String s = "Tipo: " + tipo + " Limite: " + limite + " Deducao:" + deducao;
        return s;
    }
    
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
            
        Lares d = (Lares) o;
        if(deducao == d.getDeducao() && tipo == d.getTipo() && limite == d.getLimite())
            return true;
        return false;
    }
}