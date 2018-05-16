import java.util.Scanner;
import java.util.*;
import java.util.stream.*;
import java.time.*;

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
        String pass = "";
        String str;
        Fatura f;
        Individuos i = null;
        Empresas e = null;
        Set<Fatura> listaF = null;
        while(ultima != 0){
            List<Natureza> listaNat = s.getNatureza();
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
                                b = false;
                                System.out.println("Insira NIF");
                                try{
                                    n = sc.nextInt();
                                    System.out.println("Insira palavra passe");
                                    pass = sc.nextLine();
                                    b = s.validaAcesso(n, pass); 
                                }
                                catch (Exception exc){
                                    System.out.println(exc.getMessage());
                                }
                            }
                            while(!b);
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
                            System.out.println("Escolher naturezas para o qual pode descontar");
                            int tam = s.getNatureza().size();
                            do{
                                for(int k = 1; k < tam; k++){
                                   System.out.println(k + "-" + listaNat.get(k));
                                }
                                System.out.println(tam + "-Sair");
                                try{
                                   ultima = sc.nextInt();
                                   Natureza nat = listaNat.get(ultima);
                                   i.adicionaAtividade(nat);
                                }
                                catch (Exception exc){
                                   System.out.println(exc);
                                }
                            }
                            while(ultima != tam);
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
                                    s.existeNIF(n);
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
                           int x = s.getNatureza().size();
                           do{
                               for(int k = 1; k < x; k++){
                                   System.out.println(k + "-" + listaNat.get(k));
                               }
                               System.out.println(x + "-Sair");
                               try{
                                   ultima = sc.nextInt();
                                   Natureza nat = listaNat.get(ultima);
                                   e.adicionaAtividade(nat);
                               }
                               catch (Exception exc){
                                   System.out.println(exc);
                               }
                           }
                           while(ultima != x);
                           System.out.println("Acabou de se registar");
                           fase = 2;
                           break;
                        case 4:/**Administrador*/
                            break;
                    }
                    break;
                case 1:
                    for(int x = 0; x < menuIndividuos.length; x++){
                        System.out.println(x + "-" + menuIndividuos[x]);
                    }
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
                            Set<String> porValidar = s.faturaPorValidar(i.getNIF());
                            for(String nif: porValidar){
                                System.out.println(nif);
                            }
                            break;
                            /** Alterar natureza da Fatura*/
                        case 3:
                            break;
                        }
                    break;
                case 2:
                    for(int x = 0; x < menuEmpresas.length; x++){
                        System.out.println(x + "-" + menuEmpresas[x]);
                    }
                    /**Fazer try aqui*/
                    ultima = sc.nextInt();
                    switch(ultima){
                        case 0:
                            fase = 0;
                            break;
                        case 1:
                            f = new Fatura();
                            do{
                                b = true;
                                try{
                                    System.out.println("Id da Fatura");
                                    str = sc.nextLine();
                                    if(s.existeFaturaId(str)){
                                        b = false;
                                    }
                                }
                                catch (Exception exc){
                                    System.out.println(exc);
                                }
                           }
                           while(b);
                           System.out.println("Designacao da fatura");
                           str = sc.nextLine();
                           f.setDescricao(str);
                           f.setDesignacao(e.getNome());
                           f.setEmitente(e.getNIF());
                           f.setData(LocalDate.now());
                           do{
                                try{
                                    System.out.println("Insira o NIF do cliente");
                                    n = sc.nextInt();                            
                                    f.setCliente(n);
                                    b = false;
                                }
                                catch (Exception exc){
                                    b = true;
                                    System.out.println(exc);
                                }
                           }
                           while(b);
                           do{
                                try{
                                    System.out.println("Insira o valor da fatura");
                                    n = sc.nextInt();
                                    f.setValor(n);
                                    b = false;
                                }
                                catch (Exception exc){
                                    b = true;
                                    System.out.println(exc);
                                }
                           }
                           while(b);
                           f.setNatureza(e.getAtividades());
                           break;
                        case 2:
                            /**Alterar Natureza da Fatura*/
                            break;
                    }
            }
        }
    }
}