import java.util.Scanner;
import java.util.*;
import java.util.stream.*;
import java.time.*;
import java.io.*;

public class JavaFaturaMenu
{
    public void main(String [] argv){
        Scanner sc = new Scanner(System.in);
        String [] menuInicial = {"Sair", "Login", "Registar Individuo", "Registar Empresa", "Administrador"};
        String [] menuIndividuos = {"LogOut", "Imprimir Faturas", "Imprimir Faturas por Validar", "Imprimir Fatura Detalhada", "Validar Fatura"};
        String [] menuEmpresas = {"LogOut", "Adicionar Fatura", "Alterar Natureza de Fatura"};
        String [] menuAdmin = {"Sair", "Adicionar Natureza", "Lista das Empresas", "Lista de Individuos", "Info detalhada de Empresa", 
            "Info detalhada de Individuo", "Top empresas com mais valor", "Top 10 Individuos que mais gastaram",
            "Alterar Deducao Fiscal de Empresa", "Alterar Deducao Fiscal de Individuo"};
        int fase = 0;
        int ultima = -1;
        Sistema s = carregaSistema(argv[0]);
        String str;
        Individuos i = null;
        Empresas e = null;
        while(ultima != 0){
            switch(fase){
                case 0: printMenu(menuInicial);
                    /**Fazer o try aqui*/
                    ultima = sc.nextInt();
                    switch(ultima){
                        case 0: System.out.println("A sair");
                        case 1: loginIndividuo(s, i);
                                fase = 1;
                        case 2: loginEmpresa(s, e);
                        case 3: registaIndividuo(s, i);
                                fase = 1;
                        case 4: registaEmpresa(s, e);
                                fase = 2;
                        case 5: logAdmin(s);
                                fase = 3;
                    }
                case 1: printMenu(menuIndividuos);
                    /**Fazer o try aqui*/
                    ultima = sc.nextInt();
                    switch(ultima){
                        case 0: fase = 0;
                        case 1: printFaturas(s, i.getNIF());
                        case 2: printFaturasPorValidar(s, i.getNIF());
                        case 3: printFaturaDetalhada(s, i.getNIF());
                        case 4: validaFatura(s, i);
                    }
                case 2: printMenu(menuEmpresas);
                    /**Fazer try aqui*/
                    ultima = sc.nextInt();
                    switch(ultima){
                        case 0: fase = 0;
                        case 1: adicionaFatura(s, e);
                        case 2: /**Alterar Natureza da Fatura*/
                    }
                case 3: printMenu(menuAdmin);
                    /**Fazer o try aqui*/
                    ultima = sc.nextInt();
                    switch(ultima){
                        case 0: fase = 0;
                        case 1: adicionaNatureza(s);
                        case 2: printEmpresas(s);
                        case 3: printIndividuos(s);
                        case 4: printDetalheEmpresa(s);
                        case 5: printDetalheIndividuo(s);
                        case 6: printEmpresasComMaisValor(s);
                        case 7: print10ContribuintesMaisGastam(s);
                        case 8: /** Alterar deducao de empresa */
                        case 9: /** Alterar deducao de individuo */
                    }
            }
        }
    }
    
    public void printMenu(String [] menu){
        for(int i = 0; i < menu.length; i++){
            System.out.println(i + "-" + menu[i]);
        }
    }
    
    public void printFaturas(Sistema s, int nif){
        Set<Fatura> listaF = s.getSistema().get(nif);
        for(Fatura fatura: listaF){
            System.out.println(fatura);
        }
    }
    
    public void printFaturasPorValidar(Sistema s, int nif){
        Set<String> listaF = s.faturaPorValidar(nif);
        for(String str: listaF){
            System.out.println(str);
        }
    }
    
    public void printFaturaDetalhada(Sistema s, int nif){
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        Fatura f;
        try{
            f = s.getFatura(id, nif);
            System.out.println(f);
        }
        catch(NaoExisteFaturaException exc){
            System.out.println(exc);
        }
    }
    
    public void printEmpresas(Sistema s){
        for(Integer i: s.getEmpFaturas().keySet()){
            System.out.println();
        }
    }
    
    public void printIndividuos(Sistema s){
        for(Integer i: s.getSistema().keySet()){
            System.out.println();
        }        
    }
    
    public void printDetalheEmpresa(Sistema s){
        Scanner sc = new Scanner(System.in);
        int nif;
        try{
            nif = sc.nextInt();
            s.existeEmpresa(nif);
            System.out.println(s.getInfo().get(nif));
        }
        catch(Exception exc){
            System.out.println(exc);
        }
    }
    
    public void printDetalheIndividuo(Sistema s){
        Scanner sc = new Scanner(System.in);
        int nif;
        try{
            nif = sc.nextInt();
            s.existeIndividuo(nif);
            System.out.println(s.getInfo().get(nif));
        }
        catch(Exception exc){
            System.out.println(exc);        
        }
    }

    public void printEmpresasComMaisValor(Sistema s){
        Scanner sc = new Scanner(System.in);
        boolean b;
        int n, dia, mes, ano;
        LocalDate begin = null, end = null;
        ArrayList<Integer> list;
        do{
            b = true;
            try{
                System.out.println("Numero de empresas");
                n = sc.nextInt();
                System.out.println("Ano");
                ano = sc.nextInt();
                System.out.println("Mes");
                mes = sc.nextInt();
                System.out.println("Dia");
                dia = sc.nextInt();
                begin.of(ano, mes, dia);
                System.out.println("Ano");
                ano = sc.nextInt();
                System.out.println("Mes");
                mes = sc.nextInt();
                System.out.println("Dia");
                dia = sc.nextInt();
                begin.of(ano, mes, dia);/**
                list = s.topXEmpresas(n, begin, end);
                for(int i = 0; i < n; i++){
                    System.out.println(i + "-" + list.get(i));
                }*/
            }
            catch (Exception exc){
                System.out.println(exc.getMessage());
            }
        }
        while(!b);
    }
    
    public void print10ContribuintesMaisGastam(Sistema s){
        try{
            ArrayList<Integer> list = s.top10Contribuintes();
            for(int i = 0; i < 10; i++){
                System.out.println(i + "-" + list.get(i));
            }    
        }
        catch(NaoExisteNIFException exc){
            System.out.println(exc);
        }
    }
    /**Alterar a cond do while para ver se e um individuo*/
    public void loginIndividuo(Sistema s, Individuos i){
        boolean b;
        int n = -1;
        Scanner sc = new Scanner(System.in);
        String pass;
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
        i = (Individuos) s.getInfo().get(n);
    }
    /**Alterar a cond do while para verificar se e uma empresa*/
    public void loginEmpresa(Sistema s, Empresas e){
        boolean b;
        int n = -1;
        Scanner sc = new Scanner(System.in);
        String pass;    
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
        e = (Empresas) s.getInfo().get(n);       
    }
    
    public void registaIndividuo(Sistema s, Individuos i){
        String str;
        Scanner sc = new Scanner(System.in);
        boolean b;
        int n = -1;
        int j = 0;
        int fase;
        int ultima = -1;
        List<Natureza> listaNat = new ArrayList<>();
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
    }
    
    public void registaEmpresa(Sistema s, Empresas e){
        String str;
        boolean b;
        int n;
        Scanner sc = new Scanner(System.in);
        int ultima = 0;
        int fase;
        List<Natureza> listaNat = new ArrayList<>();
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
    }
    
    public void logAdmin(Sistema s){
        String str;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Insira a pass admin");
            str = sc.nextLine();
        }
        while(!s.getAdministrador().getPassword().equals(str));
    }
    
    public void validaFatura(Sistema s, Individuos i){
        boolean b;
        String str;
        Scanner sc = new Scanner(System.in);
        Fatura f = null;
        do{
            b = true;
            System.out.println("Insira o id da Fatura que quer mudar de natureza");
            str = sc.nextLine();
            try{
                f = s.getFatura(str, i.getNIF());
                b = false;
            }
            catch(NaoExisteFaturaException exc){
                System.out.println(exc);
            }
        }while(b);
        /**
            * Metodo que verifica se uma String e uma natureza de uma fatura
        */
        do{
            b = true;
            try{
                str = sc.nextLine();
                for(Natureza nat: f.getNatureza()){
                    System.out.println(nat);
                }
            }
            catch (Exception exc){
                System.out.println(exc);
            }
        }while(b);
    }
    
    public void adicionaFatura(Sistema s, Empresas e){
        boolean b;
        int n;
        Fatura f = new Fatura();
        String str;
        Scanner sc = new Scanner(System.in);
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
    }
    
    public void adicionaNatureza(Sistema s){
        String str;
        Scanner sc = new Scanner(System.in);
        int ded, lim;
        Natureza nat;
        System.out.println("Nova Natureza");
        System.out.println("Nome da Natureza");
        str = sc.nextLine();
        try{
            System.out.println("Deducao da Natureza");
            ded = sc.nextInt();
            System.out.println("Limite da Natureza");
            lim = sc.nextInt();
            nat = new Natureza(str, lim, ded);
            s.adicionaNatureza(nat);
        }
        catch(Exception exc){
            System.out.println(exc);
        }
    }
    
    public Sistema carregaSistema(String path){
        Sistema s = new Sistema();
        try{
            s.carregaEstado(path);
        }
        catch(FileNotFoundException exc){
            System.out.println(exc);
        }
        catch(IOException exc){
            System.out.println(exc);
        }
        catch(ClassNotFoundException exc){
            System.out.println(exc);
        }
        return s;
    }
}