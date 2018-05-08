import java.util.*;
import java.io.*;
import java.time.*;
import java.util.stream.Collectors;

public class Sistema implements Serializable
{
    private Map<Integer, Set<Fatura>> sistema;
    private Map<Integer, Entidades> info;
    
    public Sistema(){
        sistema = new HashMap<>();
        info = new HashMap<>();
    }
    
    public Sistema(Map<Integer, Set<Fatura>> m, Map<Integer, Entidades> info){
        sistema = new HashMap<>();
        info = new HashMap<>();
        sistema.putAll(m);
        info.putAll(info);
    }
    
    public Sistema(Sistema s){
        sistema = s.getSistema();
        info = s.getInfo();
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
    
    public Map<Integer, Entidades> getInfo(){
        Map<Integer, Entidades> m = new HashMap<>();
        for(Integer i: info.keySet()){
            Entidades e = info.get(i);
            m.put(i, e);
        }
        return m;
    }
    
    public void setInfo(Map<Integer, Entidades> e){
        info = new HashMap<>();
        for(Integer i: e.keySet())
            info.put(i,e.get(i));
    }
    
    public Sistema clone(){
        return new Sistema(this);
    }
    
    public String toString(){
        String s = "";
        for(Integer i: sistema.keySet()){
            s+= "NIF: " + sistema.get(i).toString() + " Dados:" + info.get(i).toString();
        }
        return s;
    }
    
    public boolean equals(Object o){
        return sistema.equals(o);
    }
    
    
    
    public void adicionaIndividuo(Individuos c) throws ExisteNIFSistema{
        if(sistema.containsKey(c.getNIF()))
            throw new ExisteNIFSistema("NIF" + c.getNIF() + " e invalido, porque ja existe");
        sistema.put(c.getNIF(), new HashSet<>());
        info.put(c.getNIF(), c.clone());
    }
    
    public void adicionaEmpresas(Empresas c) throws ExisteNIFSistema{
        if(sistema.containsKey(c.getNIF()))
            throw new ExisteNIFSistema("NIF" + c.getNIF() + " e invalido, porque ja existe");
        sistema.put(c.getNIF(), new HashSet<>());
        info.put(c.getNIF(), c.clone());
    }
    
    public boolean validaAcesso(int conta, int passe) throws NaoExisteNIF, PasseErrada{
        if(!info.containsKey(conta))
            throw new NaoExisteNIF("NIF" + conta + "enixestente");
        else{
            if(!info.get(conta).getPassword().equals(passe))
                throw new PasseErrada("palavra-passe incorreta");
            else return true;
        }
    }
    
    public void adicionaFatura(Fatura f) throws NaoExisteIndividuo{
        if(!sistema.containsKey(f.getCliente()))
            throw new NaoExisteIndividuo("O NIF:" + f.getCliente() + " nao existe");
        else{
             sistema.get(f.getEmitente()).add(f);
             sistema.get(f.getCliente()).add(f);
        }
    }
    
    public List<Fatura> SettoList(Set<Fatura> s){
        List<Fatura> l = new ArrayList<>();
        for(Fatura f : s)
            l.add(f);
        return l;
    }
    
    public List<Fatura> ordenaValor(int conta){
        List<Fatura> l = new ArrayList<>();
        Set<Fatura> s = sistema.get(conta);
        l = SettoList(s);
        Collections.sort(l, Fatura :: compareTo);
        return l;
    }

    public List<Fatura> ordenaData(int conta){
        List<Fatura> l = new ArrayList<>();
        Set<Fatura> s = sistema.get(conta);
        l = SettoList(s);
        Collections.sort(l, Fatura :: compareToData);
        return l;
    }
    
    public List<Fatura> ordenaContribuinte(int conta, LocalDate begin, LocalDate end){
        List<Fatura> l = sistema.get(conta).stream().filter(d -> d.getData().isBefore(begin) && d.getData().isAfter(end))
        .collect(Collectors.toList());
        Collections.sort(l, Fatura :: compareToNIF);
        return l;
    }
    
    public List<Fatura> ordenaContribuinteValor(int conta){
        List<Fatura> l = new ArrayList<>(); 
        Set<Fatura> s = sistema.get(conta);
        l = SettoList(s);
        Collections.sort(l, Fatura :: compareToNIFValor);
        return l;
    }
}