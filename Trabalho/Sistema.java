import java.util.*;
import java.io.*;
import java.time.*;
import java.util.stream.*;

public class Sistema implements Serializable
{
    private Map<Integer, Set<Fatura>> sistema;
    private Map<Integer, Entidades> info;
    private Set<Natureza> natureza;
    
    public Sistema(){
        sistema = new HashMap<>();
        info = new HashMap<>();
        natureza = new HashSet<>();
    }
    
    public Sistema(Map<Integer, Set<Fatura>> m, Map<Integer, Entidades> info, Set<Natureza> n){
        sistema = new HashMap<>();
        info = new HashMap<>();
        n = new HashSet<>();
        sistema.putAll(m);
        info.putAll(info);
        natureza.addAll(n);
    }
    
    public Sistema(Sistema s){
        sistema = s.getSistema();
        info = s.getInfo();
        natureza = s.getNatureza();
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
    
    public Set<Natureza> getNatureza(){
        Set<Natureza> s = new HashSet<>();
        for(Natureza n : natureza)
            s.add(n);
        return s;
    }
    
    public void setNatureza(Set<Natureza> n){
        natureza = new HashSet<>();
        for(Natureza x: n)
            natureza.add(x);
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
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
    
        Sistema s = (Sistema) o;
        if(s.getSistema().equals(sistema) && s.getInfo().equals(info) && s.getNatureza().equals(natureza))
            return true;
        return false;
    }
    
    public void adicionaIndividuo(Individuos c) throws ExisteNIFSistemaException{
        if(sistema.containsKey(c.getNIF()))
            throw new ExisteNIFSistemaException("NIF" + c.getNIF() + " e invalido, porque ja existe");
        sistema.put(c.getNIF(), new HashSet<>());
        info.put(c.getNIF(), c.clone());
    }
    
    public void adicionaEmpresas(Empresas c) throws ExisteNIFSistemaException{
        if(sistema.containsKey(c.getNIF()))
            throw new ExisteNIFSistemaException("NIF" + c.getNIF() + " e invalido, porque ja existe");
        sistema.put(c.getNIF(), new HashSet<>());
        info.put(c.getNIF(), c.clone());
    }
    
    public boolean validaAcesso(int conta, int passe) throws NaoExisteNIFException, PasseErradaException{
        if(!info.containsKey(conta))
            throw new NaoExisteNIFException("NIF" + conta + "enixestente");
        else{
            if(!info.get(conta).getPassword().equals(passe))
                throw new PasseErradaException("palavra-passe incorreta");
            else return true;
        }
    }
    
    public boolean existeFaturaId(String Id){
        boolean x = false;
        for(Integer i: sistema.keySet())
            for(Fatura s: sistema.get(i))
                if(s.getId().equals(Id))
                    x = true;        
        return x;
    }
    
    public boolean existeFatura(Fatura f){
        boolean x = false;
        for(Integer i: sistema.keySet())
            for(Fatura s: sistema.get(i))
                if(s.getId().equals(f.getId()))
                    x = true;
        return x;
    }
    
    public void adicionaFatura(Fatura f) throws NaoExisteIndividuoException, ExisteFaturaException{
        if(!sistema.containsKey(f.getCliente()))
            throw new NaoExisteIndividuoException("O NIF:" + f.getCliente() + " nao existe");
        else if(existeFatura(f)){
            throw new ExisteFaturaException("A fatura com o numero " + f.getId() + " ja se encontra no sistema");
        }            
        else{
             sistema.get(f.getEmitente()).add(f.clone());
             sistema.get(f.getCliente()).add(f.clone());
        }
    }
    
    public void addAgregado(int conta, int addN) throws NaoExisteIndividuoException, ExisteAgregadoException{
        if(!sistema.containsKey(conta) || !(info.get(conta) instanceof Individuos))
            throw new NaoExisteIndividuoException("NIF " + conta + " nao existe");
        else if(!sistema.containsKey(addN) || !(info.get(addN) instanceof Individuos))
            throw new NaoExisteIndividuoException("NIF " + addN + " nao existe");
        
        Individuos i = (Individuos) info.get(conta);
        if(i.getNIF_fam().contains(addN))
            throw new ExisteAgregadoException(conta + " " + addN + " ja pertencem ao mesmo agregado familiar");
        i.setAgregado(i.getAgregado() + 1);
        i.getNIF_fam().add(addN);
        i = (Individuos) info.get(addN);
        i.setAgregado(i.getAgregado() + 1);
        i.getNIF_fam().add(conta);
    }
    
    public double valorTotal(int conta) throws NaoExisteNIFException{
        if(!sistema.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        Set<Fatura> s = sistema.get(conta);
        double t = 0;
        for(Fatura f: s)
            t += f.getValor();
        return t;
    }
    
    public double valorTotalFam(int conta) throws NaoExisteIndividuoException,NaoExisteNIFException{
        if(!sistema.containsKey(conta) || !(info.get(conta) instanceof Individuos))
            throw new NaoExisteIndividuoException("NIF " + conta + " nao existe");
        double t = 0;
        Individuos e = (Individuos) info.get(conta);
        for(Integer i: e.getNIF_fam()){
            if(!sistema.containsKey(conta))
                throw new NaoExisteNIFException("NIF " + conta + "nao existe");
            t += valorTotal(i);
        }
        return t;
    }    
    
    public Set<Natureza> setNaturezaFatura(Set<Natureza> s, Natureza x){
        s.clear();
        s.add(x);
        return s;
    }
    
    public List<Fatura> SettoList(Set<Fatura> s){
        List<Fatura> l = new ArrayList<>();
        for(Fatura f : s)
            l.add(f);
        return l;
    }
    
    public List<Fatura> ordenaValor(int conta) throws NaoExisteNIFException{
        if(!sistema.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        List<Fatura> l = new ArrayList<>();
        Set<Fatura> s = sistema.get(conta);
        l = SettoList(s);
        Collections.sort(l, Fatura :: compareTo);
        return l;
    }

    public List<Fatura> ordenaData(int conta) throws NaoExisteNIFException{
        if(!sistema.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        List<Fatura> l = new ArrayList<>();
        Set<Fatura> s = sistema.get(conta);
        l = SettoList(s);
        Collections.sort(l, Fatura :: compareToData);
        return l;
    }
    
    public List<Fatura> ordenaContribuinte(int conta, LocalDate begin, LocalDate end) throws NaoExisteNIFException{
        if(!sistema.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        List<Fatura> l = sistema.get(conta).stream().filter(d -> d.getData().isBefore(begin) && d.getData().isAfter(end))
        .collect(Collectors.toList());
        Collections.sort(l, Fatura :: compareToNIF);
        return l;
    }
    
    public List<Fatura> ordenaContribuinteValor(int conta) throws NaoExisteNIFException{
        if(!sistema.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        List<Fatura> l = new ArrayList<>(); 
        Set<Fatura> s = sistema.get(conta);
        l = SettoList(s);
        Collections.sort(l, Fatura :: compareToNIFValor);
        return l;
    }
    
    public ArrayList<Integer> top10Contibuintes() throws NaoExisteNIFException{
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
    
    public ArrayList<Integer> topXEmpresas(int x) throws NaoExisteNIFException{
        ArrayList<Integer> id = new ArrayList<>(x);
        Set<Integer> s = sistema.keySet();
        for(int h = 0; h < x; x++){
            id.set(h, 0);
        }
        for(Integer i: s){
            if((info.get(i) instanceof Empresas) && valorTotal(i) > id.get(x - 1)){
                id.set(x - 1, i);
                Collections.sort(id);
            }
        }
        return id;
    }
    
    public Set<String> faturaPorValidar(int NIF){
        Set<String> s = new HashSet<>();
        for(Fatura f: sistema.get(NIF)){
            if(f.getNatureza().size() > 1)
                s.add(f.getId());
        }
        return s;
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