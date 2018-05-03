public interface Natureza
{
    public String getTipo();
    public double getDeducao();
    public double getLimite();
    
    public Natureza clone();
    public String toString();
    public boolean equals(Object o);
}