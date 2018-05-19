import java.io.*;

public class FaturaEmpresa implements Serializable
{
    private String id;
    private int nif;
    
    public FaturaEmpresa(){
        id = "";
        nif = 0;
    }
    
    public FaturaEmpresa(String id, int nif){
        id = id;
        nif = nif;
    }
    
    public FaturaEmpresa(FaturaEmpresa fe){
        id = fe.getId();
        nif = fe.getNIF();
    }
    
    public String getId(){
        return id;
    }
    
    public int getNIF(){
        return nif;
    }
    
    public void setId(String id){
        id = id;
    }
    
    public void setNIF(int nif){
        nif = nif;
    }
    
    public FaturaEmpresa clone(){
        return new FaturaEmpresa(this);
    }
    
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        FaturaEmpresa fe = (FaturaEmpresa) o;
        if(id.equals(fe.getId()) && nif == fe.getNIF()) return true;
        return false;
    }
    
    public String toString(){
        return ("Id: " + id + "nif: " + nif);
    }
    
    public int hashCode(){
        return (int) id.hashCode() * nif;
    }
}
