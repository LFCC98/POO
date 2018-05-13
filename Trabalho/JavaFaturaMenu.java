import java.util.Scanner;

public class JavaFaturaMenu
{
    private String [] opcoes;
    private int ultima;
    private int fase;
    
    public JavaFaturaMenu(){
        opcoes = new String[5];
        this.ultima = -1;
        this.fase = 0;
    }
    
    public void menu(Sistema s){
        Scanner sc = new Scanner(System.in);
        String [] nova = new String[5];
        nova [0] = "Sair";
        nova [1] = "Login";
        nova [2] = "Registar Individuo";
        nova [3] = "Registar Empresa";
        nova [4] = "Administrador";
        int n = -1;
        boolean b;
        int j;
        String pass = "";
        String str;
        Fatura f;
        Individuos i;
        Empresas e;
        while(ultima != 0){
            for(int x = 0; x < nova.length; x++){
                System.out.println(x + " " + nova[x]);
            }
            ultima = sc.nextInt();
            j = 0;
            switch(ultima){
                case 0: System.out.println("A sair");
                break;
                case 1: do{
                        System.out.println("Insira NIF");
                        try{
                        n = sc.nextInt();
                        System.out.println("Insira palavra passe");
                        pass = sc.nextLine();
                    }
                    catch (Exception exc){
                        System.out.println(exc);
                    }
                    }
                    while(!s.getInfo().keySet().contains(n) && s.getInfo().get(n).getPassword().equals(pass));
                break;
                case 2:
                    i = new Individuos();
                    do{
                        try{
                            System.out.println("Insira o NIF");
                            n = sc.nextInt();
                            i.setNIF(n);
                            b = false;
                        }
                        catch (Exception exc){
                            b = true;
                            System.out.println(exc);
                        }
                    }
                    while(b);
                    System.out.println("Email");
                    str = sc.nextLine();
                    i.setEmail(str);
                    System.out.println("Nome");
                    str = sc.nextLine();
                    i.setNome(str);
                    System.out.println("Morada");
                    str = sc.nextLine();
                    i.setMorada(str);
                    System.out.println("Password");
                    str = sc.nextLine();
                    i.setPassword(str);
                    do{
                    try{
                        System.out.println("Insira o numero de dependentes do agregado familiar");
                        n = sc.nextInt();
                        i.setAgregado(n);
                        b = false;
                        }
                        catch (Exception exc){
                        b = true;
                        System.out.println(exc);
                        }
                    }
                    while(b);
                    while(j < i.getAgregado()){
                        try{
                        System.out.println("Insira o NIF do agregado familiar");
                        n = sc.nextInt();
                        i.insereAgregado(n);
                        j++;
                        }
                        catch (Exception exc){
                        System.out.println(exc);
                        }
                    }
                    System.out.println("Acabou de se registar");
                break;
                case 3:
                    e = new Empresas();
                    do{
                        try{
                            System.out.println("Insira o NIF");
                            n = sc.nextInt();                            
                            e.setNIF(n);
                            b = false;
                        }
                        catch (Exception exc){
                            b = true;
                            System.out.println(exc);
                        }
                    }
                    while(b);
                    System.out.println("Email");
                    str = sc.nextLine();
                    e.setEmail(str);
                    System.out.println("Nome");
                    str = sc.nextLine();
                    e.setNome(str);
                    System.out.println("Morada");
                    str = sc.nextLine();
                    e.setMorada(str);
                    System.out.println("Password");
                    str = sc.nextLine();
                    e.setPassword(str);
                    
                break;
            }
        }
    }
}