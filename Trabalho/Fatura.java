import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Fatura
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

    public Fatura(){
        emitente = cliente = 0;
        designacao = descricao = id = "";
        data.now();
        natureza = new HashSet<>();
    }
    
    public Fatura(Fatura f){
        id = f.getId();
        emitente = f.getEmitente();
        cliente = f.getCliente();
        designacao = f.getDesignacao();
        descricao = f.getDescricao();
        data = f.getData();
        natureza = f.getNatureza();
    }
    
    public Fatura(int x, int y, String s, String r, String id, LocalDate d, Set<Natureza> nat){
        id = id;
        emitente = x;
        cliente = y;
        designacao = s;
        descricao = r;
        data = d;
        setNatureza(nat);
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
        && cliente == f.getCliente() && descricao == f.getDescricao() && natureza.equals(f.getNatureza()) && valor == f.getValor())
            return true;
        return false;
    }
    
    public String toString(){
        String s = "Id: " + id + " Emitente: " + emitente + " Designacao: " + designacao + " Data: " + data.toString() + " Cliente: " + cliente 
        + " Descricao: " + descricao + " Valor: " + valor;
        for(Natureza n: natureza)
           s += n.toString();
        return s;
    }
}
