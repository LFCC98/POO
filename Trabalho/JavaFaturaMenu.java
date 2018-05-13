import java.util.Scanner;
import java.util.*;
import java.util.stream.*;

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
        String [] menuInicial = {"Sair", "Login", "Registar Individuo", "Registar Empresa", "Administrador"};
        String [] menuIndividuos = {"Imprimir Faturas", "Imprimir Faturas por Validar", "Validar Fatura", "LogOut"};
        String [] menuEmpresas = {"Adicionar Fatura", "Alterar Natureza de Fatura", "LogOut"};
        String [] menuAdmin = {"Adicionar Natureza", "Alterar Deducao Fiscal de Empresa", "Alterar Deducao Fiscal de Individuo", "Sair"};
        int n = -1;
        boolean b;
        int j;
        int fase = 0;
        String pass = "";
        String str;
        Fatura f;
        Individuos i;
        Empresas e;
        Set<Fatura> listaF = null;
        while(ultima != 0){
            switch(fase){
                case 0:
                    for(int x = 0; x < menuInicial.length; x++){
                        System.out.println(x + "-" + menuInicial[x]);
                    }
                    /**Fazer o try aqui*/
                    ultima = sc.nextInt();
                    j = 0;
                    switch(ultima){
                        case 0: System.out.println("A sair");
                            break;
                        case 1: 
                            do{
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
                            if(s.getInfo().get(n) instanceof Individuos){
                                i = (Individuos) s.getInfo().get(n);
                                listaF = s.getSistema().get(n);
                                fase = 1;
                            }
                            if(s.getInfo().get(n) instanceof Empresas){
                                e = (Empresas) s.getInfo().get(n);
                                listaF = s.getSistema().get(n);
                                fase = 2;
                            }
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
                            fase = 1;
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
                           fase = 2;
                        break;
                    }
                    break;
                case 1:
                    /**Fazer o try aqui*/
                    ultima = sc.nextInt();
                    switch(ultima){
                        case 0:
                            fase = 0;
                            break;
                        case 1:
                            for(Fatura fatura: listaF){
                                System.out.println(fatura);
                            }
                            break;
                        case 2:
                            Set<Fatura> porValidar = listaF.stream().filter(fatura -> fatura.getNatureza().size() == 1).collect(Collectors
                            .toSet());
                            for(Fatura fatura: porValidar){
                                System.out.println(fatura);
                            }
                        }
                        break;
                case 2:
                    /**Fazer try aqui*/
                    ultima = sc.nextInt();
                    switch(ultima){
                        case 0:
                            fase = 0;
                            break;
                        case 1:
                            /**Adiciona fatura*/
                            break;
                        case 2:
                            /**Alterar Natureza da Fatura*/
                            break;
                    }
            }
        }
    }
}