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
    
    public Fatura(){
        emitente = cliente = 0;
        designacao = descricao = id = "";
        data.now();
        natureza = new HashSet<>();
        historico = new ArrayList<>();
    }
    
    public Fatura(Fatura f){
        id = f.getId();
        emitente = f.getEmitente();
        cliente = f.getCliente();
        designacao = f.getDesignacao();
        descricao = f.getDescricao();
        data = f.getData();
        natureza = f.getNatureza();
        historico = f.getHistorico();
    }
    
    public Fatura(int x, int y, String s, String r, String id, LocalDate d, Set<Natureza> nat, List<Natureza> his){
        id = id;
        emitente = x;
        cliente = y;
        designacao = s;
        descricao = r;
        data = d;
        setNatureza(nat);
        setHistorico(his);
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(int id){
        id = id;
    }
    
    public int getEmitente(){
        return emitente;
    }
    
    public void setEmitente(int x){
        emitente = x;
    }
    
    public int getCliente(){
        return cliente;
    }
    
    public void setCliente(int x){
        cliente = x;
    }
    
    public String getDesignacao(){
        return designacao;
    }
    
    public void setDesignacao(String s){
        designacao = s;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public void setDescricao(String s){
        descricao = s;
    }
    
    public LocalDate getData(){
        return data;
    }
    
    public void setData(LocalDate d){
        data = d;
    }
    
    public Set<Natureza> getNatureza(){
        return natureza.stream().map(nat -> nat.clone()).collect(Collectors.toSet());
    }
    
    public void setNatureza(Set<Natureza> nat){
        natureza = nat.stream().map(n -> n.clone()).collect(Collectors.toSet());
    }
    
    public int getValor(){
        return valor;
    }
    
    public void setValor(int valor){
        valor = valor;
    }
    
    public List<Natureza> getHistorico(){
        return historico.stream().map(his -> his.clone()).collect(Collectors.toList());
    }
    
    public void setHistorico(List<Natureza> his){
        historico = his.stream().map(h -> h.clone()).collect(Collectors.toList());
    }
    
    public Fatura clone(){
        return new Fatura(this);
    }
    
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
    
    public String toString(){
        String s = "Id: " + id + " Emitente: " + emitente + " Designacao: " + designacao + " Data: " + data.toString() + " Cliente: "
        + cliente + " Descricao: " + descricao + " Valor: " + valor;
        for(Natureza n: natureza)
           s += n.toString();
        for(Natureza nat : historico)
            s += nat.toString();
        return s;
    }

    public void escolheNatureza(Natureza n) throws NaturezaInvalidaException{
        if(!natureza.contains(n)){
            throw new NaturezaInvalidaException("A fatura nao tem essa natureza");
        }
        natureza.clear();
        natureza.add(n);
        historico.add(n);
    }
    
    public int compare(Fatura f1, Fatura f2){
        return f1.getValor() - f2.getValor();
    }
    
    public int compareTo(Fatura f){
        return valor - f.getValor();
    }
    
    public int comparaData(Fatura f1, Fatura f2){
        return f1.compareTo(f2);
    }
    
    public int compareToData(Fatura f){
        return data.compareTo(f.getData());
    }
    
    public int compareNIF(Fatura f1, Fatura f2){
        return f1.getValor() - f2.getValor();
    }
    
    public int compareToNIF(Fatura f){
        return valor - f.getValor();
    }
    
    public int compareNIFValor(Fatura f1, Fatura f2){
        if(f1.getCliente() != (f2.getCliente()))
            return f1.getCliente() - f2.getCliente();
        else{
            return f1.getValor() - f2.getValor();
        }
    }
    
    public int compareToNIFValor(Fatura f){
        if(f.getCliente() != cliente)
            return cliente - f.getCliente();
        else{
            return valor - f.getValor();
        }
    }
    
    public double valorDeduzido(Natureza n, Fatura f, double e, double i){
        double val = f.getValor() * dedTotal(e,i,n);
        if(val < n.getLimite())
            return val;
        else return n.getLimite();
    }
    
    public double dedTotal(double e, double i, Natureza n){
        return e * i * n.getDed();
    }
}