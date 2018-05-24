import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.*;
import java.io.*;

public class Fatura implements Comparator<Fatura>, Comparable<Fatura>, Serializable
{
    /** Identificação da fatura */
    private String id;
    /** Numero fiscal do emitente*/
    private int emitente;
    /** Designação do emitente*/
    private String designacao;
    /** Data da despesa*/
    private LocalDate data;
    /** Numero fiscal do cliente*/
    private int cliente;
    /** Descrição da despesa*/
    private String descricao;
    /** Natureza da despesa*/
    private Set<Natureza> natureza;
    /** Valor da fatura*/
    private int valor;
    /** Variavel que guarda o historico das atividades economicas*/
    private List<Natureza> historico;
    /** Contrutoi uma fatura vazia*/
    public Fatura(){
        emitente = cliente = 0;
        valor = 1;
        designacao = descricao = id = "";
        data.now();
        natureza = new HashSet<>();
        historico = new ArrayList<>();
    }
    /** Construtoi uma nova fatura*/
    public Fatura(Fatura f){
        id = f.getId();
        emitente = f.getEmitente();
        cliente = f.getCliente();
        designacao = f.getDesignacao();
        descricao = f.getDescricao();
        data = f.getData();
        natureza = f.getNatureza();
        valor = f.getValor();
        historico = f.getHistorico();
    }
    /** Construtor parameterizado*/
    public Fatura(String id, int emit, int cli, String desi, String desc, LocalDate d, Set<Natureza> nat, int valor, List<Natureza> his){
        this.id = id;
        this.emitente = emit;
        this.cliente = cli;
        this.designacao = desi;
        this.descricao = desc;
        this.data = d;
        setNatureza(nat);
        this.valor = valor;
        setHistorico(his);
    }
    /**
     * Metodo que devolve o id da fatura
     * 
     * @return O id da Fatura
     */
    public String getId(){
        return id;
    }
    /**
     * Metodo que altera o id da fatura
     * 
     * @param id Novo valor do id da fatura
     */
    public void setId(String id){
        this.id = id;
    }
    /**
     * Metodo que devolve o nif do emitente
     * 
     * @return O NIF do emitente
     */
    public int getEmitente(){
        return emitente;
    }
    /**
     * Metodo que altera o nif do emitente da fatura
     * 
     * @param x Novo valor do emitente da fatura
     */
    public void setEmitente(int x){
        emitente = x;
    }
    /**
     * Metodo que devolve o nif do cliente
     * 
     * @return O NIF do cliente
     */
    public int getCliente(){
        return cliente;
    }
    /**
     * Metodo que altera o nif do cliente
     * 
     * @param x Novo valor do nif do cliente
     */
    public void setCliente(int x){
        cliente = x;
    }
    /**
     * Metodo que devolve a designação da fatura
     * 
     * @return A desigançao da fatura
     */
    public String getDesignacao(){
        return designacao;
    }
    /**
     * Metodo que altera a designação da fatura
     * 
     * @param s Novo valor da designação da fatura
     */
    public void setDesignacao(String s){
        designacao = s;
    }
    /**
     * Metodo que devolve a descrição de uma fatura
     * 
     * @return A descriçao da fatura
     */
    public String getDescricao(){
        return descricao;
    }
    /**
     * Metodo que altera a descrição de uma fatura
     * 
     * @param s Novo valor da descrição
     */
    public void setDescricao(String s){
        descricao = s;
    }
    /**
     * Metodo que devolve a data da fatura
     * 
     * @return A data de lançamento do sistema
     */
    public LocalDate getData(){
        return data;
    }
    /**
     * Metodo que altera a data de uma fatura
     * 
     * @param d Novo valor da data da fatura
     */
    public void setData(LocalDate d){
        data = d;
    }
    /**
     * Metodo que retorna a natureza da fatura
     * 
     * @return A lista de Naturezas que a fatura pertence
     */
    public Set<Natureza> getNatureza(){
        return natureza.stream().map(nat -> nat.clone()).collect(Collectors.toSet());
    }
    /**
     * Metodo que altera a natureza da fatura
     * 
     * @param nat Novo valor da natureza da fatura
     */
    public void setNatureza(Set<Natureza> nat){
        natureza = nat.stream().map(n -> n.clone()).collect(Collectors.toSet());
    }
    /**
     * Metodo que devolve o valor da fatura
     * 
     * @return O valor da fatura
     */
    public int getValor(){
        return valor;
    }
    /**
     * Metodo que altera o valor de uma fatura
     * 
     * @param valor Novo valor da fatura
     */
    public void setValor(int valor){
        this.valor = valor;
    }
    /**
     * Metodo que retorna o historico da fatura
     * 
     * @return O historico da fatura
     */
    public List<Natureza> getHistorico(){
        return historico.stream().map(his -> his.clone()).collect(Collectors.toList());
    }
    /**
     * Metodo que altera o historico da fatura
     * 
     * @param his Novo valor do historico da fatura
     */
    public void setHistorico(List<Natureza> his){
        historico = his.stream().map(h -> h.clone()).collect(Collectors.toList());
    }
    /**
     * Metodo que retorna uma copia da fatura
     * 
     * @return A copia de uma Fatura
     */
    public Fatura clone(){
        return new Fatura(this);
    }
    /**
     * Metodo que testa se uma fatura é igual a um objeto
     * 
     * @param o Objeto a comparar com a fatura
     * 
     * @return true ou false se um objeto for igual a uma fatura
     */
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        Fatura f = (Fatura)o;
        
        if(id == f.getId() && emitente == f.getEmitente() && designacao == f.getDesignacao() && data.equals(f.getData()) 
        && cliente == f.getCliente() && descricao == f.getDescricao() && natureza.equals(f.getNatureza()) && valor == f.getValor() 
        && historico.equals(f.getHistorico()))
            return true;
        return false;
    }
    /**
     * Metodo que transforma uma fatura em uma string
     * 
     * @return Uma string de uma Fatura
     */
    public String toString(){
        String s = "Id: " + id + " Emitente: " + emitente + " Designacao: " + designacao + " Data: " + data.toString() + " Cliente: "
        + cliente + " Descricao: " + descricao + " Valor: " + valor + " Natureza: ";
        for(Natureza n: natureza)
           s += n.getTipo() + " ";
           s+= "Historico";
        for(Natureza nat : historico)
            s += nat.getTipo();
        return s;
    }
    /** 
     * Metodo que da o hashCode de uma Fatura
     * 
     * @return Inteiro com o hashCode de uma Fatura
     */
    public int hashCode(){
        return this.id.hashCode();
    }
    /**
     * Metodo que escolhe a natureza de uma fatura
     * 
     * @param n Natureza da fatura a escolher
     */
    public void escolheNatureza(Natureza n) throws NaturezaInvalidaException{
        if(!natureza.contains(n)){
            throw new NaturezaInvalidaException("A fatura nao tem essa natureza");
        }
        natureza.clear();
        natureza.add(n);
        historico.add(n);
    }
    
    /** Metodo que adiciona uma natureza a uma Fatura
     * 
     * @param n Natureza que vai ser adicionada a Fatura
     */
    public void adicionaNatureza(Natureza n) throws NaturezaInvalidaException{
        if(natureza.contains(n)){
            throw new NaturezaInvalidaException("A fatura já tem essa natureza");
        }
        natureza.add(n);
        historico.add(n);
    }
    /**
     * Metodo para adicionar uma natureza a uma fatura
     * 
     * @param n Natureza a adicionar
     */
    public void alteraNatureza(Natureza n) throws NaturezaInvalidaException, FaturaValidaException{
        if(natureza.contains(n)){
            throw new NaturezaInvalidaException("A fatura já tem essa natureza " + n);
        }
        else if(natureza.size() <= 1){
            throw new FaturaValidaException("A fatura ja esta validada");
        }
        natureza.add(n);
        historico.add(n);
    }
    /**
     * Metodo que remove uma natureza da fatura
     * 
     * @param n Natureza a remover
     */
    public void removeNatureza(Natureza n) throws RemoverNaturezaException{
        if(natureza.size() > 1){
            natureza.remove(n);
        }
        else{
            throw new RemoverNaturezaException("Fatura ja esta validada");
        }
    }
    /**
     * Metodo que compara duas faturas
     * 
     * @param f1 Fatura a comparar
     * 
     * @param f2 Fatura a comparar
     * 
     * @return Um inteiro que indica a diferença de valor entre duas faturas
     */
    public int compare(Fatura f1, Fatura f2){
        return f1.getValor() - f2.getValor();
    }
    /**
     * Metodo que compara duas faturas
     * 
     * @param f Fatura a comparar
     * 
     * @return Um inteiro que indica a diferença de valor entre duas faturas
     */
    public int compareTo(Fatura f){
        return valor - f.getValor();
    }
    /**
     * Comparator de faturas em relação à sua data
     * 
     * @param f1 Fatura 1
     * 
     * @param f2 Fatura 2
     * 
     * @return Um inteiro que indica a diferença da data entre duas faturas
     */
    public int compareData(Fatura f1, Fatura f2){
        return f1.compareTo(f2);
    }
    /**
     * Metodo que compara duas faturas pela data
     * 
     * @param f Fatura a comparar
     * 
     * @return Um inteiro que indica a diferença da data entre duas faturas
     */
    public int compareToData(Fatura f){
        return data.compareTo(f.getData());
    }
    /**
     * Metodo que compara duas faturas tendo como relação o nif do cliente
     * 
     * @param f1 Fatura 1
     * 
     * @param f2 Fatura 2
     * 
     * @return Um inteiro qual o que tem o NIF maior
     */
    public int compareNIF(Fatura f1, Fatura f2){
        return f1.getCliente() - f2.getCliente();
    }
    /**
     * Metodo que compara duas faturas pelo nif cliente
     * 
     * @param f Fatura a comparar
     * 
     * @return Um inteiro que indica qual a faura com NIF maior
     */
    public int compareToNIF(Fatura f){
        return cliente - f.getCliente();
    }
    /**
     * Metodo que compara duas faturas pelo nif do cliente e caso tenham o mesmo nif compara pelo valor
     * 
     * @param f1 Fatura 1
     * 
     * @param f2 Fatura 2
     * 
     * @return Um inteiro que indica qual a faura com NIF maior e o valor
     */
    public int compareNIFValor(Fatura f1, Fatura f2){
        if(f1.getCliente() != (f2.getCliente()))
            return f1.getCliente() - f2.getCliente();
        else{
            return f1.getValor() - f2.getValor();
        }
    }
    /**
     * Metodo que compara duas faturas pelo nif do cliente e caso tenham o mesmo nif compara pelo valor
     * 
     * @param f1 Fatura 1
     * 
     * @param f2 Fatura 2
     * 
     * @return Um inteiro que indica qual a faura com NIF maior e o valor
     */
    public int compareToNIFValor(Fatura f){
        if(f.getCliente() != cliente)
            return cliente - f.getCliente();
        else{
            return valor - f.getValor();
        }
    }
    /**
     * Metodo que deduz o valor a deduzir de uma fatura
     * 
     * @param n Natureza da fatura
     * 
     * @param f Fatura da deduzir
     * 
     * @param e Valor dedutivo do emitente
     * 
     * @param i Valor dedutivo do cliente
     * 
     * @return Um double com o valor deduzido de uma Fatura
     */
    public double valorDeduzido(Natureza n, Fatura f, double e, double i){
        double val = f.getValor() * dedTotal(e,i,n);
        if(val < n.getLimite())
            return val;
        else return n.getLimite();
    }
    /**
     * Metodo que calcula o valor a deduzir de uma fatura
     * 
     * @param e Valor dedutivo do emitente
     * 
     * @param i Valor dedutivo do cliente
     * 
     * @param n Natureza da fatura
     * 
     * @return Um double com o valor total deduzido de uma Fatura
     */
    public double dedTotal(double e, double i, Natureza n){
        return e * i * n.getDed();
    }
}