import java.util.*;
import java.io.*;
import java.time.*;
import java.util.stream.*;

public class Sistema implements Serializable/**, Comparator<Empresas>, Comparable<>*/
{
    private Map<Integer, Set<Fatura>> sistema;
    private Map<Integer, Set<FaturaEmpresa>> empFaturas;
    private Map<Integer, Entidades> info;
    private List<Natureza> natureza;
    private Administrador admin;
    
    public Sistema(){
        sistema = new HashMap<>();
        empFaturas = new HashMap<>();
        info = new HashMap<>();
        natureza = new ArrayList<>();
        admin = new Administrador();
    }
    
    public Sistema(Map<Integer, Set<Fatura>> m,Map<Integer, Set<FaturaEmpresa>> f,  Map<Integer, Entidades> info, List<Natureza> n, Administrador a){
        sistema = new HashMap<>();
        empFaturas = new HashMap<>();
        info = new HashMap<>();
        natureza = new ArrayList<>();
        sistema.putAll(m);
        empFaturas.putAll(f);
        info.putAll(info);
        natureza.addAll(n);
        admin = new Administrador(a);
    }
    
    public Sistema(Sistema s){
        sistema = s.getSistema();
        empFaturas = s.getEmpFaturas();
        info = s.getInfo();
        natureza = s.getNatureza();
        admin = s.getAdministrador();
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
    
    public Map<Integer, Set<FaturaEmpresa>> getEmpFaturas(){
        Map<Integer, Set<FaturaEmpresa>> m = new HashMap<>();
        for(Integer i: empFaturas.keySet()){
            Set<FaturaEmpresa> s = new HashSet<>();
            for(FaturaEmpresa f: m.get(i)){
                s.add(f);
            }
            m.put(i, s);
        }
        return m;
    }
    
    public void setEmpFaturas(Map<Integer, Set<FaturaEmpresa>> m){
        empFaturas = new HashMap<>();
        for(Integer i: m.keySet()){
            Set<FaturaEmpresa> s = new HashSet<>();
            for(FaturaEmpresa f: m.get(i)){
                s.add(f);
            }
            empFaturas.put(i, s);
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
    
    public List<Natureza> getNatureza(){
        List<Natureza> s = new ArrayList<>();
        for(Natureza n : natureza)
            s.add(n);
        return s;
    }
    
    public void setNatureza(List<Natureza> n){
        natureza = new ArrayList<>();
        for(Natureza x: n)
            natureza.add(x);
    }
    
    public Administrador getAdministrador(){
        return admin.clone();
    }
    
    public void setAdministrador(Administrador a){
        admin = a.clone();
    }
    
    public Sistema clone(){
        return new Sistema(this);
    }
    
    public String toString(){
        String s = "Administrador: " + admin.toString();
        for(Integer i: sistema.keySet()){
            s+= "NIF: " + info.get(i).toString() + " Dados:" + info.get(i).toString() + "  Faturas: ";
            for(Fatura f : sistema.get(i))
                s += " " + f.toString();
        }
        s += " Natureza:";
        for(Natureza n: natureza){
            s += " " + n;
        }
        s += "Fatura de empresas";
        for(Integer i: empFaturas.keySet())
            s += " " + empFaturas.get(i);
        return s;
    }
    
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
    
        Sistema s = (Sistema) o;
        if(s.getSistema().equals(sistema) && s.getInfo().equals(info) && s.getNatureza().equals(natureza) 
        && admin.equals(s.getAdministrador()) && s.getEmpFaturas().equals(empFaturas))
            return true;
        return false;
    }
    
    public void adicionaNatureza(Natureza n) throws JaExisteNaturezaException{
        for(Natureza nat: natureza){
            if(nat.getTipo().equals(n))
                throw new JaExisteNaturezaException("Natureza " + n + " ja existe no sistema");
            natureza.add(n);
        }
    }
    
    public boolean existeNIF(int conta){
        return info.containsKey(conta);
    }
    
    public void adicionaIndividuo(Individuos c) throws ExisteNIFSistemaException{
        if(existeNIF(c.getNIF()))
            throw new ExisteNIFSistemaException("NIF" + c.getNIF() + " e invalido, porque ja existe");
        sistema.put(c.getNIF(), new HashSet<>());
        info.put(c.getNIF(), c.clone());
    }
    
    public void adicionaEmpresas(Empresas c) throws ExisteNIFSistemaException{
        if(existeNIF(c.getNIF()))
            throw new ExisteNIFSistemaException("NIF" + c.getNIF() + " e invalido, porque ja existe");
        empFaturas.put(c.getNIF(), new HashSet<>());
        info.put(c.getNIF(), c.clone());
    }
    
    public boolean validaAcesso(int conta, String passe) throws NaoExisteNIFException, PasseErradaException{
        if(!info.containsKey(conta))
            throw new NaoExisteNIFException("NIF" + conta + "enixestente");
        else{
            if(!info.get(conta).getPassword().equals(passe))
                throw new PasseErradaException("palavra-passe incorreta");
            else return true;
        }
    }
    
    public Set<FaturaEmpresa> listaFaturasEmpresas(int conta)throws NaoExisteEmpresaException{
        if(!empFaturas.containsKey(conta))
            throw new NaoExisteEmpresaException("O " + conta + " nao existe");         
        return empFaturas.get(conta);
    }
    
    public Set<Fatura> listaFaturasContribuintes(int conta)throws NaoExisteIndividuoException{
        if(!sistema.containsKey(conta))
            throw new NaoExisteIndividuoException("O " + conta + " nao existe");         
        return sistema.get(conta);
    }
    
    public boolean existeEmpresa(int conta) throws NaoExisteNIFException{
        if(!info.containsKey(conta))
            throw new NaoExisteNIFException("O " + conta + " nao existe");
        if(info.get(conta) instanceof Empresas)
            return true;
        return false;
    }
    
    public boolean existeIndividuo(int conta) throws NaoExisteNIFException{
        if(!info.containsKey(conta))
            throw new NaoExisteNIFException("O " + conta + " nao existe");
        if(info.get(conta) instanceof Individuos)
            return true;
        return false;
    }
    
    public boolean existeFaturaId(String Id){
        boolean x = false;
        for(Integer i: sistema.keySet())
            for(Fatura s: sistema.get(i))
                if(s.getId().equals(Id))
                    x = true;        
        return x;
    }
    /*
    public boolean existeFatura(Fatura f){
        boolean x = false;
        for(Integer i: sistema.keySet())
            for(Fatura s: sistema.get(i))
                if(s.getId().equals(f.getId()))
                    x = true;
        return x;
    }
    */
    public void adicionaFatura(Fatura f) throws NaoExisteIndividuoException, ExisteFaturaException{
        if(!sistema.containsKey(f.getCliente()))
            throw new NaoExisteIndividuoException("O NIF:" + f.getCliente() + " nao existe");
        else if(existeFaturaId(f.getId())){
            throw new ExisteFaturaException("A fatura com o numero " + f.getId() + " ja se encontra no sistema");
        }            
        else{
            FaturaEmpresa fa = new FaturaEmpresa(f.getId(), f.getCliente());
             empFaturas.get(f.getEmitente()).add(fa.clone());
             sistema.get(f.getCliente()).add(f.clone());
        }
    }
    
    public Fatura getFatura(String id, Integer nif) throws NaoExisteFaturaException{
        Set<Fatura> list = sistema.get(nif);
        for(Fatura f: list){
            if(f.getId().equals(id)) return f;
        }
        throw new NaoExisteFaturaException("Nao existe nenhuma fatura com esse id");
    }
           
    public Natureza getAtividade(String s){
        Natureza n = new Natureza();
        
        for(Natureza nat: natureza)
            if(s.equals(nat.getTipo()))
            n = nat.clone();
        return n;
    }
                    
    public void alteraNatureza(String s, Fatura f) throws NaturezaInvalidaException{
        Natureza n = getAtividade(s), xs = new Natureza();
        if(!f.getHistorico().contains(n))
            throw new NaturezaInvalidaException("Natureza" + s + "invalida");
        else if(f.getNatureza().size() > 1)
            throw new NaturezaInvalidaException("Nao pode alterar porque ainda nao selecionou a atividade economica da fatura");
        else if(f.getNatureza().equals(n))
            throw new NaturezaInvalidaException("Natureza" + s + " ja esta selecionada na fatura" + f);
        for(Natureza nat: f.getNatureza()) xs = nat;
        f.getHistorico().add(xs);
        f.getNatureza().remove(xs);
        f.getNatureza().add(n);
    }
    // Ver se e preciso adicionar agregados familiares
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
    // No caso de garantir que nao ha erro
    public double valorTotalEmpresasTempo(int conta, LocalDate begin, LocalDate end) throws NaoExisteNIFException, NaoExisteFaturaException{
        if(!info.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        Set<FaturaEmpresa> s = empFaturas.get(conta);
        double t = 0;
        for(FaturaEmpresa fa: s){
            try{
            Fatura f = getFatura(fa.getId(), fa.getNIF());
            if(f.getData().isBefore(end) && f.getData().isAfter(begin))
                t += f.getValor();
            }
            catch(NaoExisteFaturaException e){
                throw new NaoExisteFaturaException("As empresas tem faturas com Id nao identificados");
            }
        }        
        return t;
    }    
    
    public double valorTotalTempo(int conta, LocalDate begin, LocalDate end) throws NaoExisteNIFException{
        if(!info.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        Set<Fatura> s = sistema.get(conta);
        double t = 0;
        for(Fatura f: s)
            if(f.getData().isAfter(begin) && f.getData().isBefore(end))
            t += f.getValor();
        return t;
    }
    
    public double valorTotalEmpresa(int conta) throws NaoExisteEmpresaException, NaoExisteFaturaException{
        if(!empFaturas.containsKey(conta))
            throw new NaoExisteEmpresaException("NIF: " + conta + "nao existe");
        Set<FaturaEmpresa> s = empFaturas.get(conta);
        double t = 0;
        for(FaturaEmpresa fa: s){
            try{
            Fatura f = getFatura(fa.getId(), fa.getNIF());
            t += f.getValor();
            }
            catch(NaoExisteFaturaException e){
                throw new NaoExisteFaturaException("As empresas tem faturas com Id nao identificados");
            }
        }        
        return t;
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
        
    public double valorTotalDeduzidoTempo(int conta, LocalDate begin, LocalDate end) throws NaoExisteIndividuoException{
        if(!sistema.containsKey(conta))
            throw new NaoExisteIndividuoException("O individuo " + conta + " não existe");
        Individuos i = (Individuos) info.get(conta);
        double t = 0, p = 0;
        for(Natureza n: natureza){
            if(i.getCodigo().contains(n))
            for(Fatura f: sistema.get(conta))
                if(f.getNatureza().size() == 1  && n.getTipo().equals(f.getNatureza()) && f.getData().isAfter(begin) && 
                f.getData().isBefore(end)){
                    Empresas e = (Empresas) info.get(f.getEmitente());
                    p += f.valorDeduzido(n, f, e.getDeducao(),i.getCoef_fiscal());
                }
            if(p > n.getLimite())
                p = n.getLimite();
            t += p;
            p = 0;
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
    
    public List<Fatura> ordenaValor(int conta) throws NaoExisteEmpresaException, NaoExisteFaturaException{
        if(!info.containsKey(conta))
            throw new NaoExisteEmpresaException("NIF: " + conta + "nao existe");
        Set<FaturaEmpresa> s = empFaturas.get(conta);
        Set<Fatura> se = new HashSet<>();
        for(FaturaEmpresa fa: s){
            try{
            Fatura f = getFatura(fa.getId(), fa.getNIF());
            se.add(f);
            }
            catch(NaoExisteFaturaException e){
                throw new NaoExisteFaturaException("As empresas tem faturas com Id nao identificados");
            }
        }
        List<Fatura> l = SettoList(se);
        Collections.sort(l, Fatura :: compareTo);
        return l;
    }

    public List<Fatura> ordenaData(int conta) throws NaoExisteNIFException, NaoExisteFaturaException{
        if(!sistema.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        Set<FaturaEmpresa> s = empFaturas.get(conta);
        Set<Fatura> se = new HashSet<>();
        for(FaturaEmpresa fa: s){
            try{
            Fatura f = getFatura(fa.getId(), fa.getNIF());
            se.add(f);
            }
            catch(NaoExisteFaturaException e){
                throw new NaoExisteFaturaException("As empresas tem faturas com Id nao identificados");
            }
        }
        List<Fatura> l = SettoList(se);
        l = SettoList(se);
        Collections.sort(l, Fatura :: compareToData);
        return l;
    }
    
    public List<Fatura> ordenaContribuinte(int conta, LocalDate begin, LocalDate end) throws NaoExisteNIFException, NaoExisteFaturaException{
        if(!sistema.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        Set<FaturaEmpresa> s = empFaturas.get(conta);
        Set<Fatura> se = new HashSet<>();
        for(FaturaEmpresa fa: s){
            try{
            Fatura f = getFatura(fa.getId(), fa.getNIF());
            se.add(f);
            }
            catch(NaoExisteFaturaException e){
                throw new NaoExisteFaturaException("As empresas tem faturas com Id nao identificados");
            }
        }
        List<Fatura> l = SettoList(se);
        Collections.sort(l, Fatura :: compareToNIF);
        return l;
    }
    
    public List<Fatura> ordenaContribuinteValor(int conta) throws NaoExisteNIFException, NaoExisteFaturaException{
        if(!sistema.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        Set<FaturaEmpresa> s = empFaturas.get(conta);
        Set<Fatura> se = new HashSet<>();
        for(FaturaEmpresa fa: s){
            try{
            Fatura f = getFatura(fa.getId(), fa.getNIF());
            se.add(f);
            }
            catch(NaoExisteFaturaException e){
                throw new NaoExisteFaturaException("As empresas tem faturas com Id nao identificados");
            }
        }
        List<Fatura> l = SettoList(se);
        Collections.sort(l, Fatura :: compareToNIFValor);
        return l;
    }
    
    public ArrayList<Integer> top10Contribuintes() throws NaoExisteNIFException{
        ArrayList<Integer> id = new ArrayList<>();
        Set<Integer> s = sistema.keySet();
        boolean b;
        for(int x = 0; x < 10; x++){
            id.set(x, 0);
        }
        for(Integer i: s){
            try{
                b = valorTotal(i) > valorTotal(id.get(9));
                if(info.get(i) instanceof Individuos && b){
                    id.set(9, i);
                    Collections.sort(id);
                }
            }
            catch(NaoExisteNIFException exc){
              throw new NaoExisteNIFException(exc.getMessage());
            }
        }       
        return id;
    }
    /**
    public ArrayList<Entidades> topXEmpresas(int x, LocalDate begin, LocalDate end) throws NaoExisteNIFException, NaoExisteFaturaException{
        ArrayList<Entidades> id = new ArrayList<>(x);
        Set<Integer> s = empFaturas.keySet();
        boolean b;
        for(Integer i: s){
            try{
                b = valorTotalEmpresasTempo(i, begin, end) > valorTotalEmpresasTempo(id.get(x - 1).getNIF(), begin, end);
                if(info.get(i) instanceof Empresas && b){
                    id.set(x - 1, info.get(i));
                    id.sort((Entidades e1, Entidades e2) -> valorTotalEmpresa(e1.getNIF()) - valorTotalEmpresa(e2.getNIF()));
                }
            }           
            catch(NaoExisteNIFException e){
                throw new NaoExisteNIFException(e.getMessage()); 
            }
            catch(NaoExisteFaturaException e){
                throw new NaoExisteFaturaException(e.getMessage());
            }
        }
        return id;
    }
    */
    public Set<String> faturaPorValidar(int NIF){
        Set<String> s = new HashSet<>();
        for(Fatura f: sistema.get(NIF)){
            if(f.getNatureza().size() > 1)
                s.add(f.getId());
        }
        return s;
    }
    
    public int compareValorEmpresa(Empresas e1, Empresas e2) throws NaoExisteFaturaException, NaoExisteEmpresaException{
        try{
        double v1 = valorTotalEmpresa(e1.getNIF()), v2 = valorTotalEmpresa(e2.getNIF());
        if(v1 > v2)
            return 1;
        else if(v1 < v2)
            return -1;
        else return 0;
        }
        catch(NaoExisteFaturaException e){
            throw new NaoExisteFaturaException(e.getMessage());
        }
        catch(NaoExisteEmpresaException e){
            throw new  NaoExisteEmpresaException(e.getMessage());
        }

    }
    /**
    public int compare(Empresas e1, Empresas e2){
    
    }
    */
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