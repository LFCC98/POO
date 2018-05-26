import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.*;
import java.io.*;

/** Classe que contêm informações e dados relevantes acerca das empresas com deduções nos impostos por serem do interior do país */

public class EmpresasInterior extends Empresas implements Desconto, Serializable
{
    /** Constroi uma EmpresaInterior vazia*/
    public EmpresasInterior(){
        super();
    }
    
    /** Construtor parametrizado*/
    public EmpresasInterior(int nif, String e, String nome, String morada, String pass, Set<Natureza> nat, double deducao){
        super(nif, e, nome, morada, pass, nat, deducao);
    }
    
    /** Constroi uma nova EmpresaInterior*/
    public EmpresasInterior(EmpresasInterior ei){
        super(ei);
    }
    
    /** 
     * Metodo que faz copia de uma EmpresaInterior com diferentes posiçoes de memoria
     *
     *@return Uma copia de uma EmpresaInterior
     */
    public EmpresasInterior clone(){
        return new EmpresasInterior(this);
    }
    
    /** 
     * Metodo que transforma uma EmpresaInterior numa String
     * 
     * @return uma String de uma EmpresaInterior
     */
    public String toString(){
        String s = super.toString();
        return s;
    }
    
    /** 
     * Metodo que verifica se um EmpresaInterior e igual a um objeto
     * 
     * @return uma String de um EmpresaInterior
     */
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        EmpresasInterior i = (EmpresasInterior)o;
        if(super.equals(i))
            return true;
        return false;
    }
    
    /** 
     * Metodo que calcula a percentagem que ira aumentar pela quantidade de filhos
     * 
     * @return Um double com a percentagem que ira aumentar 
     */
    public double reducaoImposto(){
            return 1.1;
    }
}
