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
    
    public double valorTotal(int conta){
        Set<Fatura> s = sistema.get(conta);
        double t = 0;
        for(Fatura f: s)
            t += f.getValor();
        return t;
    }
            
    public ArrayList<Integer> top10Contibuintes(){
        ArrayList<Integer> id = new ArrayList<>();
        Set<Integer> s = sistema.keySet();
        for(int x = 0; x < 10; x++){
            id.set(x, 0);
        }
        for(Integer i: s){
            if((info.get(i) instanceof Individuos) && valorTotal(i) > id.get(9)){
                id.set(9, i);
                Collections.sort(id);
            }
        }       
        return id;
    }
    
    public ArrayList<Integer> topXEmpresas(int x){
        ArrayList<Integer> id = new ArrayList<>(x);
        Set<Integer> s = sistema.keySet();
        for(int h = 0; h < x; x++){
            id.set(h, 0);
        }
        for(Integer i: s){
            if((info.get(i) instanceof Individuos) && valorTotal(i) > id.get(x - 1)){
                id.set(x - 1, i);
                Collections.sort(id);
            }
        }
        return id;
    }
    
    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
    
    public Sistema carregaEstado(String nomeFicheiro) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Sistema s = (Sistema) ois.readObject();
        ois.close();
        return s;
    }
}