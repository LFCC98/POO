import java.util.*;
import java.io.*;

/**
 * Classe que contem a informaçao sobre os distritos que sao do interior do pais
 *
 */

public class DistritosInterior implements Serializable
{
    private List<String> distritos;

    /** Constroi uma lista vazia*/
    public DistritosInterior(){
        List<String> distritos = new ArrayList<>();
    }
    
    /** Construtor parametrizado*/
    public DistritosInterior(List<String> dist){
        this.distritos = dist;
    }
    
    /** Constroi uma nova lista*/
    public DistritosInterior(DistritosInterior di){
        distritos = di.getDist();
    }
    
    /**
     * Metodo que devolve a lista de distritos do interior
     * 
     * @return A lista de distritos
     */
    public List<String> getDist(){
        return distritos;
    }
    
    /**
     * Metodo que altera a lista de distritos do interior
     * 
     * @param empInt A nova lista de distritos
     */
    public void setDist(List<String> distInt){
        distritos = distInt;
    }
    
    /** 
     * Metodo que faz copia de um DistritoInterior com diferentes posiçoes de memoria
     *
     *@return Uma copia de um DistritoInterior
     */
    public DistritosInterior clone(){
        return new DistritosInterior(this);
    }
    
    /** 
     * Metodo que transforma um DistritoInterior numa String
     * 
     * @return uma String de um DistritoInterior
     */
    public String toString (){
        String s = " Distritos do interior: " + getDist();
        return s;
    }
    
    /** 
     * Metodo que verifica se um DistritoInterior e igual a um objeto
     * 
     * @return uma String de um DistritoInterior
     */
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        DistritosInterior i = (DistritosInterior)o;
        if(super.equals(i) && distritos == i.getDist())
            return true;
        return false;
    }
    
    /** Metodo que adiciona um distrito a uma lista de distritos do interior
     * 
     * @param s Distrito a adicionar à lista de distritos do interior
     */
    public void adicionaDistrito(String s) throws JaExisteDistritoException{
        if(distritos.contains(s)){
            throw new JaExisteDistritoException("O distrito já pertence à lista");
        }
        distritos.add(s);
    }
    
    /**
     * Metodo que guarda num ficheiro um Sistema que contem todas as faturas, Contribuintes e Empresas
     * 
     * @param nomeFichiro Ficheio que serao guardados os dados do sistema
     */
    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
    /**
     * Metodo que carrega de um ficheiro um Sistema com todas as faturas, Contribuintes e Empresas 
     * 
     * @param nomeFicheiro Ficheiro em que estao guardadas as informações sobre o sistema
     * 
     * @return um Sistema
     */
    public DistritosInterior carregaEstado(String nomeFicheiro) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);
        DistritosInterior di = (DistritosInterior) ois.readObject();
        ois.close();
        return di;
    }
}
