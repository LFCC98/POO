import java.util.*;
import java.io.*;
import java.time.*;
import java.util.stream.*;

public class Sistema implements Serializable/**, Comparator<Empresas>, Comparable<>*/
{   /** Map de todas as faturas dos Individuos*/
    private Map<Integer, Set<Fatura>> sistema;
    /** Map de todas as faturas das empresas*/
    private Map<Integer, Set<FaturaEmpresa>> empFaturas;
    /** Map de todas as informações das entidades*/
    private Map<Integer, Entidades> info;
    /** Lista de Natureza no qual se pode descontar*/
    private List<Natureza> natureza;
    /** Administrador do sistema*/
    private Administrador admin;

    /** Constroi um novo sistema "vazio"*/
    public Sistema(){
        sistema = new HashMap<>();
        empFaturas = new HashMap<>();
        info = new HashMap<>();
        natureza = new ArrayList<>();
        admin = new Administrador();
    }
    /** Constroi um novo sistema com as variaveis dos argumentos */
    public Sistema(Map<Integer, Set<Fatura>> m,Map<Integer, Set<FaturaEmpresa>> f,  Map<Integer, Entidades> info, List<Natureza> n, 
    Administrador a){
        this.sistema = new HashMap<>();
        this.empFaturas = new HashMap<>();
        this.info = new HashMap<>();
        this.natureza = new ArrayList<>();
        this.sistema.putAll(m);
        this.empFaturas.putAll(f);
        this.info.putAll(info);
        this.natureza.addAll(n);
        this.admin = new Administrador(a);
    }
    /** Constroi um novo sistema */
    public Sistema(Sistema s){
        sistema = s.getSistema();
        empFaturas = s.getEmpFaturas();
        info = s.getInfo();
        natureza = s.getNatureza();
        admin = s.getAdministrador();
    }
    /**
     * Metodo que retorna o sistema das faturas dos individuos
     * 
     * @return as Faturas dos Individuos do sistema
     */
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
    /**
     * Metodo que altera o Set de faturas dos individuos
     * 
     * @param m O set das faturas dos individuos serao alterados para estes valores 
     */
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
    /**
     * Metodo que retorna o Set de faturas das empresas
     * 
     * @return o Set de faturas das Empresas 
     */
    public Map<Integer, Set<FaturaEmpresa>> getEmpFaturas(){
        Map<Integer, Set<FaturaEmpresa>> m = new HashMap<>();
        for(Integer i: empFaturas.keySet()){
            Set<FaturaEmpresa> s = new HashSet<>();
            for(FaturaEmpresa f: empFaturas.get(i)){
                s.add(f);
            }
            m.put(i, s);
        }
        return m;
    }
    /**
     * Metodo que altera os Set das faturas das empresas
     * 
     * @param m o Set das faturas das empresas serao alteradas para estes valores
     */
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
    /**
     * Metodo que retorna um Map sobre a informação das entidades do sistema
     * 
     * @return as informações sobre as Entidades
     */
    public Map<Integer, Entidades> getInfo(){
        Map<Integer, Entidades> m = new HashMap<>();
        for(Integer i: info.keySet()){
            Entidades e = info.get(i);
            m.put(i, e);
        }
        return m;
    }
    /**
     * Metodo que altera as entidades do sistema
     * 
     * @param e Novos entidades do sistema
     */
    public void setInfo(Map<Integer, Entidades> e){
        info = new HashMap<>();
        for(Integer i: e.keySet())
            info.put(i,e.get(i));
    }
    /**
     * Metodo que retorna a lista de naturezas do Sistema
     * 
     * @return as Naturezas atuais do sistema
     */
    public List<Natureza> getNatureza(){
        List<Natureza> s = new ArrayList<>();
        for(Natureza n : natureza)
            s.add(n);
        return s;
    }
    /**
     * Metodo que altera o Set de naturezas
     * 
     * @param n novos valores das naturezas
     */    
    public void setNatureza(List<Natureza> n){
        natureza = new ArrayList<>();
        for(Natureza x: n)
            natureza.add(x);
    }
    /**
     * Metodo que retorna o administrador atual do sistema
     * 
     * @return o administrador atual do sistema
     */
    public Administrador getAdministrador(){
        return admin.clone();
    }
    /**
     * Metodo que altera o administrador
     * 
     * @param a Novos valores do administrador
     */
    public void setAdministrador(Administrador a){
        admin = a.clone();
    }
    /**
     * Metodo que faz uma copia do sitema
     * 
     * @return uma copia do Sistema
     */
    public Sistema clone(){
        return new Sistema(this);
    }
    /**
     * Metodo que retorna todo o sistema numa String
     * 
     *  @return String do sistema
     */
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
    /**
     * Metodo que compara se dois sistemas sao iguais
     * 
     * @param o Object que ira ser comparado com o sistema
     * 
     * @return um boolean que corresponde a igualdade entre os dois sistemas
     */
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
    /**
     * Metodo que adiciona uma Natureza ao Sistema
     * 
     * @param n Natureza que ira ser adicionada ao sistema
     */
    public void adicionaNatureza(Natureza n) throws JaExisteNaturezaException{
        for(Natureza nat: natureza){
            if(nat.getTipo().equals(n))
                throw new JaExisteNaturezaException("Natureza " + n + " ja existe no sistema");
            natureza.add(n);
        }
    }
    /**
     * Metodo que verifica se existe alguma entidade com um certo NIF
     * 
     * @param conta Identificador da entidade que irá ser procurada
     * 
     * @return um boolean que corresponde a existencia de um NIF no sistema
     */
    public boolean existeNIF(int conta){
        return info.containsKey(conta);
    }
    /**
     * Metodo que adiciona um Individuo ao Sistema
     * 
     * @param c Individuo que ira ser adicionada ao sistema
     */
    public void adicionaIndividuo(Individuos c) throws ExisteNIFSistemaException{
        if(existeNIF(c.getNIF()))
            throw new ExisteNIFSistemaException("NIF" + c.getNIF() + " e invalido, porque ja existe");
        sistema.put(c.getNIF(), new HashSet<>());
        info.put(c.getNIF(), c.clone());
    }
    /**
     * Metodo que adiciona uma Empresa ao Sistema
     * 
     * @param c Empresa que ira ser adicionada ao sistema
     */
    public void adicionaEmpresas(Empresas c) throws ExisteNIFSistemaException{
        if(existeNIF(c.getNIF()))
            throw new ExisteNIFSistemaException("NIF" + c.getNIF() + " e invalido, porque ja existe");
        empFaturas.put(c.getNIF(), new HashSet<>());
        info.put(c.getNIF(), c.clone());
    }    
    /**
    * Metodo que adiciona uma Fatura ao Sistema
    * 
    * @param f Fatura que sera adicionada ao sistema
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
    /**
    * Metodo que adiciona um agregado familiar
    * 
    * @param conta NIF que se pretende adicionar um agregado familiar
    * 
    * @param addN NIF que se pretende adicionar um agregado familiar
    */    
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
    /**
     * Metodo que verifica se um utilizador consegue ter acesso aos dados
     * 
     * @param Id Identificador da entidade que ira tentar ter acesso aos dados
     * 
     * @param passe Palavra chave do utilizador com NIF Id
     * 
     * @return um boolean que corresponde se a entidade conseguiu entrar no sistema
     */
    public boolean validaAcesso(int conta, String passe) throws NaoExisteNIFException, PasseErradaException{
        if(!info.containsKey(conta))
            throw new NaoExisteNIFException("NIF" + conta + "enixestente");
        else{
            if(!info.get(conta).getPassword().equals(passe))
                throw new PasseErradaException("palavra-passe incorreta");
            else return true;
        }
    }
    /**
     * Metodo que retorna a lista de faturas de uma empresa
     * 
     * @param Id Identificador da empresa
     * 
     * @return um Set de faturas de uma determinada Empresa
     */
    public Set<FaturaEmpresa> listaFaturasEmpresas(int conta)throws NaoExisteEmpresaException{
        if(!empFaturas.containsKey(conta))
            throw new NaoExisteEmpresaException("O " + conta + " nao existe");         
        return empFaturas.get(conta);
    }
    /**
     * Metodo que retorna a lista de faturas de um contribuinte 
     * 
     * @param Id Identificador do contribuinte
     * 
     * @return um Set de faturas de um determinado Individuo
     */
    public Set<Fatura> listaFaturasContribuintes(int conta)throws NaoExisteIndividuoException{
        if(!sistema.containsKey(conta))
            throw new NaoExisteIndividuoException("O " + conta + " nao existe");         
        return sistema.get(conta);
    }
    /**
     * Metodo que verifica se existe alguma Empresa no sistema
     * 
     * @param Id Identificador da empresa que ira ser procurada no sitema
     * 
     * @return um boolean que corresponde a existencia da empresa no sistema
     */
    public boolean existeEmpresa(int conta){
        if(info.containsKey(conta) && info.get(conta) instanceof Empresas)
            return true;
        return false;
    }
    /**
     * Metodo que verifica se existe algum individuo no sistema com um determinado identificador
     * 
     * @param conta Identificador do individuo que ira ser procurada no sitema
     * 
     * @return um boolean que corresponde a existencia do individuo no sistema
     */
    public boolean existeIndividuo(int conta){
        if(info.containsKey(conta) && info.get(conta) instanceof Individuos)
            return true;
        return false;
    }
    /**
     * Metodo que verifica se existe alguma Fatura no sistema com um determinado identificador
     * 
     * @param Id Identificador da fatura que ira ser procurada no sitema
     * 
     * @return um boolean que corresponde a existencia da fatura no sistema
     */
    public boolean existeFaturaId(String Id){
        boolean x = false;
        for(Integer i: sistema.keySet())
            for(Fatura s: sistema.get(i))
                if(s.getId() != null && s.getId().equals(Id))
                    x = true;        
        return x;
    }
    /**
     * Metodo que retorna uma Fatura 
     * 
     * @param id Identificador da fatura que se pretende retornar
     * 
     * @param nif Fatura esta associadda a este nif
     * 
     * @return uma Fatura
     */    
    public Fatura getFatura(String id, Integer nif) throws NaoExisteFaturaException, IndexOutOfBoundsException{
        try{
            Set<Fatura> list = sistema.get(nif);
            if(list != null){
                for(Fatura f: list){
                    if(f.getId().equals(id)) return f;
                }
            }
        }
        catch(IndexOutOfBoundsException exc){
            throw new IndexOutOfBoundsException(exc.getMessage());
        }
        throw new NaoExisteFaturaException("Nao existe nenhuma fatura com esse id");
    }    
     /**
     * Metodo que retorna uma Fatura 
     * 
     * @param id Identificador da fatura que se pretende retornar
     * 
     * @param e Empresa que contem a fatura que se pretende retornar 
     * 
     * @return uma Fatura
     */   
    public Fatura getFatura(String id, Empresas e) throws NaoExisteFaturaException{
        try{
            Fatura f = new Fatura();
            for(FaturaEmpresa fe: empFaturas.get(e.getNIF())){
                if(fe.getId().equals(id))
                    f = getFatura(fe.getId(), fe.getNIF());
            }
            if(f.getId() != id)
                throw new NaoExisteFaturaException(id);
            return f;
        }
        catch(NaoExisteFaturaException me){
            throw new NaoExisteFaturaException(me.getMessage());
        }
    }
     /**
     * Metodo que retorna um Set de Faturas de uma empresa 
     * 
     * @param nif empresa que se pretende retornar o Set de faturas
     * 
     * @return Set de Faturas
     */   
    public Set<Fatura> getFaturasEmpresas(int nif) throws NaoExisteFaturaException{
        try{
            Set<Fatura> s = new HashSet<>();
            for(FaturaEmpresa fe: empFaturas.get(nif)){
                s.add(this.getFatura(fe.getId(), fe.getNIF()));
            }
            return s;
        }
        catch(NaoExisteFaturaException e){
            throw new NaoExisteFaturaException(e.getMessage());
        }
    }
    /**
     * Metodo que retorna uma natureza dado uma string
     * 
     * @param s Tipo da natureza que se pretende retornar
     * 
     * @return uma Natureza
     */           
    public Natureza getAtividade(String s){
        Natureza n = new Natureza();

        for(Natureza nat: natureza)
            if(s.equals(nat.getTipo()))
                n = nat.clone();
        return n;
    }
    /**
    * Metodo que um dado contribuinte escolhe qual a natureza da fatura
    * 
    * @param s Lista das Naturezas em que se pretende alterar para apenas uma
    * 
    * @param x Natureza que sera a escolhida pelo contribuinte 
    */    
    public void validaFatura(String s, Natureza n, int nif) throws NaturezaInvalidaException{
        try{
            Set<Fatura> fa = sistema.get(nif);
            for(Fatura f: fa)
                if(f.getId().equals(s))
                    f.escolheNatureza(n);
        }
        catch(NaturezaInvalidaException e){
            throw new NaturezaInvalidaException("Natureza "  + n +" invalida");
        }
    }
    /**
     * Metodo que altera uma natureza de uma fatura que ja foi escolhida qual a sua natureza
     * 
     * @param s Natureza que vai ser alterada
     * 
     * @param Fatura que vai ser alterada
     */                    
    public void alteraNatureza(String s, Natureza n, int nif) throws NaturezaInvalidaException, FaturaValidaException{
        try{   
           Set<Fatura> fa = sistema.get(nif);
            for(Fatura f: fa)
                if(f.getId().equals(s))
                    f.alteraNatureza(n);
        }
        catch(NaturezaInvalidaException e){
            throw new NaturezaInvalidaException(e.getMessage());
        }
        catch(FaturaValidaException e){
            throw new FaturaValidaException(e.getMessage());
        
        }
    }
    // No caso de garantir que nao ha erro
    /**
     * Metodo que calcula o valor total de uma empresa num determinado intervalo de tempo
     * 
     * @param conta Identificador da empresa que se pretende calcular o valor total
     * 
     * @param begin Todas as faturas tem que ser posteriores a esta data
     * 
     * @param end Todas as faturas tem que ser anteriores a esta data
     * 
     * @return um double com o valor total de uma empresa num determinado intervalo de tempo
     */
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
     /**
     * Metodo que calcula o valor total de uma empresa num determinado intervalo de tempo
     * 
     * @param conta Identificador da empresa que se pretende calcular o valor total
     * 
     * @param begin Todas as faturas tem que ser posteriores a esta data
     * 
     * @param end Todas as faturas tem que ser anteriores a esta data
     * 
     * @return um double com o valor total de uma empresa num determinado intervalo de tempo
     */
    public double valorTotalDeduzidoEmpresasTempo(int conta, LocalDate begin, LocalDate end) throws NaoExisteEmpresaException, 
    NaoExisteFaturaException{
        if(!info.containsKey(conta) && !(info.get(conta) instanceof Empresas))
            throw new NaoExisteEmpresaException("NIF: " + conta + "nao existe");
        Set<FaturaEmpresa> s = empFaturas.get(conta);
        Empresas e = (Empresas)info.get(conta);
        double t = 0;
        for(FaturaEmpresa fa: s){
            try{
                Fatura f = getFatura(fa.getId(), fa.getNIF());
                if(f.getData().isBefore(end) && f.getData().isAfter(begin))
                    t += f.getValor() * e.getDeducao();
            }
            catch(NaoExisteFaturaException me){
                throw new NaoExisteFaturaException("As empresas tem faturas com Id nao identificados" + me);
            }
        }        
        return t;
    }
     /**
     * Metodo que calcula o valor total de uma empresa de sempre
     * 
     * @param conta Identificador da empresa que se pretende calcular o valor total das faturas que passou de sempre
     * 
     * @return um double com o valor total da empresa
     */
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
    /**
     * Metodo que calcula o valor total de um contribuinte num determinado espaço de tempo 
     * 
     * @param conta Identificador da um contribuinte que se pretende ordenar as faturas
     * 
     * @param begin Todas as faturas tem que ser posteriores a esta data
     * 
     * @param end Todas as faturas tem que ser anteriores a esta data
     * 
     * @return um double com valor total num determinado espaço de tempo
     */        
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
    /**
     * Metodo que calcula o valor total de todas as faturas de uma identidade
     * 
     * @param conta Identificador que se pretende calcular o valor total
     * 
     * @return double com o valor total de uma entidade  
     */    
    public double valorTotal(int conta) throws NaoExisteNIFException{
        if(!sistema.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        Set<Fatura> s = sistema.get(conta);
        double t = 0;
        for(Fatura f: s)
            t += f.getValor();
        return t;
    }
    /**
     * Metodo que calcula o valor total deduzido num certo intervalo de tempo
     * 
     * @param conta Identificador de uma entidade que se pretende deduzir
     * 
     * @param begin Todas as faturas tem que ser posteriores a esta data
     * 
     * @param end Todas as faturas tem que ser anteriores a esta data
     * 
     * @return um double com o valor que vai ser deduzido num intervalo de tempo
     */
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
     /**
     * Metodo que calcula o valor total da familia 
     * 
     * @param conta Identificador do contribuinte que se pretende calcular o valor total do agregado familiar 
     * 
     * @return um double com o valor total do agregado familiar
     */
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
    /**
     * Metodo que o custo total de um individuo
     * 
     * @param conta NIF do individuo que se pretende calcular quanto gastou
     * 
     * @return um double com o valor total
     */   
    public double custoTotalIndividuo(int conta){
        double t = 0;
        for(Fatura f : sistema.get(conta))
            t += f.getValor();
        return t;
    }
    /**
     * Metodo que o custo total de uma empresa
     * 
     * @param conta NIF da empresa que se pretende calcular quanto gastou
     * 
     * @return um double com o valor total
     */         
    public double custoTotalEmpresa(int conta){
        double t = 0;
        Set<Fatura> sf;
        for(FaturaEmpresa fa: empFaturas.get(conta)){
            sf = sistema.get(fa.getId());
            for(Fatura f: sf)
                if(f.getEmitente() == conta)
                    t += f.getValor();
        }
        return t;
    }    
    /**
     * Metodo que ordena por valor as faturas de uma empresa
     * 
     * @param conta Identificador da empresa que se pretende ordenar as faturas
     * 
     * @return uma Lista de faturas ordenadas por valor
     */
    public Set<Fatura> ordenaValor(int conta) throws NaoExisteEmpresaException, NaoExisteFaturaException{
        if(!empFaturas.containsKey(conta))
            throw new NaoExisteEmpresaException("NIF: " + conta + "nao existe");
        Set<FaturaEmpresa> s = empFaturas.get(conta);
        Set<Fatura> se = new TreeSet<>(Fatura :: compareTo);
        for(FaturaEmpresa fa: s){
            try{
                Fatura f = getFatura(fa.getId(), fa.getNIF());
                se.add(f);
            }
            catch(NaoExisteFaturaException e){
                throw new NaoExisteFaturaException("As empresas tem faturas com Id nao identificados");
            }
        }
        return se;
    }
    /**
     * Metodo que ordena por data de emissao as fauras de uma empresas
     * 
     * @param conta Identificador da empresa que se pretende ordenar as faturas
     * 
     * @return uma Lista de faturas ordenadas por data
     */
    public Set<Fatura> ordenaData(int conta) throws NaoExisteNIFException, NaoExisteFaturaException{
        if(!empFaturas.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        Set<FaturaEmpresa> s = empFaturas.get(conta);
        Set<Fatura> se = new TreeSet<>(Fatura :: compareToData);
        for(FaturaEmpresa fa: s){
            try{
                Fatura f = getFatura(fa.getId(), fa.getNIF());
                se.add(f);
            }
            catch(NaoExisteFaturaException e){
                throw new NaoExisteFaturaException("As empresas tem faturas com Id nao identificados");
            }
        }
        return se;
    }
    /**
     * Metodo que ordena por contribuinte as faturas de uma empresa num determinado intervalo de tempo
     * 
     * @param conta Identificador da empresa que se pretende ordenar as faturas
     * 
     * @param begin Todas as faturas tem que ser posteriores a esta data
     * 
     * @param end Todas as faturas tem que ser anteriores a esta data
     * 
     * @return uma Lista de faturas ordenadas por contribuinte
     */
    public Set<Fatura> ordenaContribuinte(int conta, LocalDate begin, LocalDate end) throws NaoExisteNIFException, NaoExisteFaturaException{
        if(!empFaturas.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        Set<FaturaEmpresa> s = empFaturas.get(conta);
        Set<Fatura> se = new TreeSet<>(Fatura :: compareToNIF);
        for(FaturaEmpresa fa: s){
            try{
                Fatura f = getFatura(fa.getId(), fa.getNIF());
                if(f.getData().isAfter(begin) && f.getData().isBefore(end))
                    se.add(f);
            }
            catch(NaoExisteFaturaException e){
                throw new NaoExisteFaturaException("As empresas tem faturas com Id nao identificados");
            }
        }
        return se;
    }
    /**
     * Metodo que dada um NIF de uma empresa ordena as faturas por contribuinte e no caso de serem do mesmo contribuinte ordena por valor 
     * 
     * @param conta Identificador da empresa que se pretende ordenar as faturas
     * 
     * @return um Sistema
     */
    public Set<Fatura> ordenaContribuinteValor(int conta) throws NaoExisteNIFException, NaoExisteFaturaException{
        if(!empFaturas.containsKey(conta))
            throw new NaoExisteNIFException("NIF: " + conta + "nao existe");
        Set<FaturaEmpresa> s = empFaturas.get(conta);
        Set<Fatura> se = new TreeSet<>(Fatura :: compareToNIFValor);
        for(FaturaEmpresa fa: s){
            try{
                Fatura f = getFatura(fa.getId(), fa.getNIF());
                se.add(f);
            }
            catch(NaoExisteFaturaException e){
                throw new NaoExisteFaturaException("As empresas tem faturas com Id nao identificados");
            }
        }
        return se;
    }
    /**
     * Metodo que calcula os 10 contribuintes que masi gastaram em todo o sistema
     * 
     * @return um ArrayList com a identificacao dos 10 contribuintes que mais gastaram
     */
    public Set<Integer> top10Contribuintes() throws NaoExisteNIFException{
        Set<Integer> s = sistema.keySet();
        Set<Integer> tree = new TreeSet<Integer>((i1, i2) -> Integer.compare((int)custoTotalIndividuo(i1), (int)custoTotalIndividuo(i2)));
        for(Integer i: s)
            tree.add(i);
        tree.stream().limit(10);
        return tree;
    }
     /**
     * Metodo que calcula o top X das Empresas com mais valor
     * 
     * @param x Quantas empresas vao estar no top
     * 
     * @return Set com o top X empresas
     */   
    public Set<Integer> topXEmpresas(int x) throws NaoExisteNIFException, NaoExisteFaturaException{
        Set <Integer> s = empFaturas.keySet();
        Set <Integer> tree = new TreeSet<>((i1, i2) -> Integer.compare((int)custoTotalEmpresa(i1), (int)custoTotalEmpresa(i2)));
        for(Integer i: s)
            tree.add(i);
        tree.stream().limit(x);
    return tree;
    }
     /**
     * Metodo que calcula o top X das Empresas que mais deduziram
     * 
     * @param s Lista das empresas que estao no top
     * 
     * @return double com o total deduzido
     */      
    public double topXEmpresasDeducao(Set<Integer> s){
        double t = 0;
        Set<FaturaEmpresa> fa;
        Set<Fatura> fs;
        for(Integer i: s){
            t += custoTotalEmpresa(i);
        }
        return t;
    }
     /**
     * Metodo que retorna o Id das faturas por validar de um individuo
     * 
     * @param NIF nif do individuo que se pretende conhecer as faturas por validas
     * 
     * @return Set com as faturas por validar
     */
    public Set<String> faturaPorValidar(int NIF){
        Set<String> s = new HashSet<>();
        for(Fatura f: sistema.get(NIF)){
            if(f.getNatureza().size() > 1)
                s.add(f.getId());
        }
        return s;
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
    public Sistema carregaEstado(String nomeFicheiro) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Sistema s = (Sistema) ois.readObject();
        ois.close();
        return s;
    }
}