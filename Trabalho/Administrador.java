import java.io.*;

public class Administrador implements Serializable
{
    private String nome;
    private String password;
    
    public Administrador(){
        nome = "";
        password = "";
    }
    
    public Administrador(String nome, String pass){
        nome = nome;
        password = pass;
    }
    
    public Administrador(Administrador admin){
        nome = admin.getNome();
        password = admin.getPassword();
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setNome(String name){
        nome = name;
    }
    
    public void setPassword(String pass){
        password = pass;
    }
    
    public Administrador clone(){
        return new Administrador(this);
    }
    
    public String toString(){
        return nome + " " + password;
    }
    
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Administrador admin = (Administrador) o;
        if(nome.equals(admin.getNome()) && password.equals(admin.getPassword())) return true;
        return false;
    }
}