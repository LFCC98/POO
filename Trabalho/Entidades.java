public abstract class Entidades
{
    
    private int NIF;
    private String email;
    private String nome;
    private String morada;
    private String password;
    
    public Entidades(){
        NIF = 0;
        email = nome = morada = password = "";
    }
    
    public Entidades(int n, String e, String nome, String morada, String pass){
        NIF = n;
        email = e;
        this.nome = nome;
        morada = morada;
        password = pass;
    }
    
    public Entidades(Entidades d){
        NIF = d.getNIF();
        email = d.getEmail();
        nome = d.getNome();
        morada = d.getMorada();
        password = d.getPassword();
    }
    
    public int getNIF(){
        return NIF;
    }

    public String getEmail(){
        return email;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getMorada(){
        return morada;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setNIF(int NIF){
    NIF = NIF;
    }

    public void setEmail(String email){
    email = email;
    }
    
    public void setNome(String nome){
    nome = nome;
    }
    
    public void setMorada(String morada){
    morada = morada;
    }
    
    public void setPassword(String password){
    password = password;
    }

    public String toString(){
        String s = "NIF: " + NIF + " Email: " + email + " Nome: " + nome + " Morada: " + morada + " Password: " + password;
        return s ;
    }
    
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
    
    public boolean login(String password){
        return this.password.equals(password);
    }
}
