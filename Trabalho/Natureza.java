import java.io.*;

public class Natureza implements Serializable{
    /** Nome da natureza*/
    private String tipo;
    /** Limite maximo de deduçao das faturas com esta natureza selecionada*/
    private double limite;
    /** % que sera descontada de todas as faturas desta natureza*/
    private double ded;
    /** Constroi uma nova Natureza vazia*/
    public Natureza(){
        tipo = "Outro";
        limite = 0;
        ded = 0;
    }
    /** Constroi uma nova Natureza com as variaveis dos argumentos */
    public Natureza(String tipo, double limite, double ded){
        this.tipo = tipo;
        this.limite = limite;
        this.ded = ded;
    }
    /** Constroi uma nova Natureza com uma Natureza*/
    public Natureza(Natureza n){
        tipo = n.getTipo();
        limite = n.getLimite();
        ded  = n.getDed();
    }
    /**
    * Metodo que retorna o nome, tipo, da natureza
    * 
    * @return o tipo da fatura
    */
    public String getTipo(){
        return tipo;
    }
    /**
    * Metodo que altera o tipo de uma Natureza 
    * 
    * @param tipo Tipo da Natureza vai ser alterada para o tipo
    */    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    /**
    * Metodo que retorna o limite de uma Natureza
    * 
    * @return um double com o limite maximo da natureza
    */
    public double getLimite(){
        return limite;
    }
    /**
    * Metodo que altera o limite de uma Natureza
    * 
    * @param limite Limite da naturez vai ser alterada para este valor
    */    
    public void setLimite(double limite){
        this.limite = limite;
    }
    /**
    * Metodo que retorna a % de deduçao de uma natureza
    * 
    * @return a de deduçao de uma Natureza
    */
    public double getDed(){
        return ded;
    }
    /**
    * Metodo que altera deduçao de uma Natureza
    * 
    * @param ded A deduçao da Natureza vai ser alterada para ded
    */    
    public void setDed(double ded){
        this.ded = ded;
    }
    /**
    * Metodo que faz uma copia de uma Natureza
    * 
    * @return uma copia de uma Natureza
    */
    public Natureza clone(){
        return new Natureza(this);
    }
    /**
    * Metodo que compara um objeto com uma Natureza
    * 
    * @return um boolean que corresponde a igualdade do objeto com uma Natureza
    */    
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
    /**
    * Metodo que passa uma Natureza para uma String
    * 
    * @return uma String da natureza
    */    
    public String toString(){
        String s = " Atividade Economica: " + tipo + " Limite maximo de deducao: " + limite + " Deducao: " + ded;
        return s;
    }
    /**
    * Metodo que retorna o hash code da natureza
    * 
    * @return o hash code de uma Natureza 
    */
    public int hashCode(){
        return this.tipo.hashCode();
    }
}