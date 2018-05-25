import java.io.*;

public abstract class Entidades implements Serializable{
    /**Identificaçao da entidade*/
    private int NIF;
    /**Endereço eletronico da entidade*/
    private String email;
    /**Nome entidade*/
    private String nome;
    /**Local de residencia da entidade*/
    private String morada;
    /**Identificaçao da entidade*/
    private String password;
    
    /**Construtor vazio de uma Entidades*/
    public Entidades(){
        NIF = 0;
        email = nome = morada = password = "";
    }
    /**Construtor com todas as variaveis de instancia de uma Entidades*/
    public Entidades(int n, String e, String nome, String morada, String pass){
        this.NIF = n;
        this.email = e;
        this.nome = nome;
        this.morada = morada;
        this.password = pass;
    }
    /**Construtor de uma Entidades */
    public Entidades(Entidades d){
        NIF = d.getNIF();
        email = d.getEmail();
        nome = d.getNome();
        morada = d.getMorada();
        password = d.getPassword();
    }
     /**
     * Metodo que retorna o NIF de uma Entidade
     * 
     * @return NIF da entidade
     */
    public int getNIF(){
        return NIF;
    }
     /**
     * Metodo que retorna o email de uma Entidade
     * 
     * @return email da entidade
     */
    public String getEmail(){
        return email;
    }
     /**
     * Metodo que retorna o nome de uma Entidade
     * 
     * @return nome da entidade
     */
    public String getNome(){
        return nome;
    }
     /**
     * Metodo que retorna a morada de uma Entidade
     * 
     * @return morada da entidade
     */
    public String getMorada(){
        return morada;
    }
     /**
     * Metodo que retorna a password de uma Entidade
     * 
     * @return Password da entidade
     */
    public String getPassword(){
        return password;
    }
     /**
     * Metodo que altera o NIF de uma Entidade
     * 
     * @param NIF valor no qual o NIF vai ser alterado
     */
    public void setNIF(int NIF){
    this.NIF = NIF;
    }
     /**
     * Metodo que altera o email de uma Entidade
     * 
     * @param email valor no qual o NIF vai ser alterado
     */
    public void setEmail(String email){
    this.email = email;
    }
     /**
     * Metodo que altera o nome de uma Entidade
     * 
     * @param nome valor no qual o NIF vai ser alterado
     */
    public void setNome(String nome){
    this.nome = nome;
    }
     /**
     * Metodo que altera a morada de uma Entidade
     * 
     * @param morada valor no qual o NIF vai ser alterado
     */
    public void setMorada(String morada){
    this.morada = morada;
    }
     /**
     * Metodo que altera a password de uma Entidade
     * 
     * @param password valor no qual o NIF vai ser alterado
     */
    public void setPassword(String password){
    this.password = password;
    }
     /**
     * Metodo que retorna uma String da entidades
     * 
     * @return String da Entidades
     */
    public String toString(){
        String s = "NIF: " + NIF + " Email: " + email + " Nome: " + nome + " Morada: " + morada + " Password: " + password;
        return s ;
    }
    /**
     * Metodo que retorna um boolean da igualdade entre um objeto e uma Entidades
     * 
     * @param o Objeto que ira ser comparado com a entidade
     * 
     * @return a comparaçao entre o objeto e a entidade
     */
    public boolean equals(Object o){
    if(o == this)
        return true;
        
    if(o == null || o.getClass() != this.getClass())
        return false;
    Entidades i = (Entidades) o;
    
    if(NIF == i.getNIF() && email == i.getEmail() && nome == i.getNome() && morada == i.getMorada() && password == i.getPassword())
        return true;
    return false;
    }
    /**
     * Metodo que verifica se uma entidades acertou a password e da lhe acesso
     * 
     * @param password Password que o Individuo introduziu
     * 
     * @return true caso comparaçao acerte a password, false caso contrario
     */
    public boolean login(String password){
        return this.password.equals(password);
    }
}