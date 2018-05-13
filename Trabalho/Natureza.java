public class Natureza{
    private String tipo;
    private double limite;
    private double ded;
    
    public Natureza(){
        tipo = "";
        limite = 0;
        ded = 0;
    }
    
    public Natureza(String tipo, double limite, double ded){
        tipo = tipo;
        limite = limite;
        ded = ded;
    }
    
    public Natureza(Natureza n){
        tipo = n.getTipo();
        limite = n.getLimite();
        ded  = n.getDed();
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String tipo){
        tipo = tipo;
    }
    
    public double getLimite(){
        return limite;
    }
    
    public void setLimite(double limite){
        limite = limite;
    }
    
    public double getDed(){
        return ded;
    }
    
    public void setDed(double ded){
        ded = ded;
    }
    
    public Natureza clone(){
        return new Natureza(this);
    }
    
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
            
        Natureza n = (Natureza) o;
        if(tipo.equals(n.getTipo()) && limite == n.getLimite() && ded == n.getDed())
            return true;
        return false;
    }
    
    public String toString(){
        String s = "Atividade Economica:" + tipo + " Limite maximo de deducao: " + limite + " Deducao:" + ded;
        return s;
    }
}