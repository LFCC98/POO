import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class Sistema
{
    private Map<Integer, Set<Fatura>> sistema;
    
    public Sistema(){
        sistema = new HashMap<>();
    }
    
    public Sistema(Map<Integer, Set<Fatura>> m){
        sistema = new HashMap<>();
        sistema.putAll(m);
    }
    
    public Sistema(Sistema s){
        sistema = s.getSistema();
    }
    
    public Map<Integer, Set<Fatura>> getSistema(){
        Map<Integer, Set<Fatura>> m = new HashMap<>();
        for(Integer i: sistema.keySet()){
            Set<Fatura> s = new HashSet<>();
            for(Fatura f: sistema.get(i)){
                s.add(f.clone());
            }
            m.put(i, s);
        }
        return m;
    }
    
    public void SetSistema(Map<Integer, Set<Fatura>> m){
        sistema = new HashMap<>();
        for(Integer i: m.keySet()){
            Set<Fatura> s = new HashSet<>();
            for(Fatura f: m.get(i)){
                s.add(f.clone());
            }
            sistema.put(i, s);
        }
    }
    
    public Sistema clone(){
        return new Sistema(this);
    }
    
    public String toString(){
        String s = "";
        for(Integer i: sistema.keySet()){
            s+= "NIF: " + sistema.get(i).toString();
        }
        return s;
    }
    
    public boolean equals(Object o){
        return sistema.equals(o);
    }
}
