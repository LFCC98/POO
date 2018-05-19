import java.util.Scanner;
import java.util.*;
import java.util.stream.*;
import java.time.*;
import java.io.*;

public class JavaFaturaMenu
{
    /**
     * Metodo main
     * 
     * @param path  Caminho para o ficheiro
     */
    public void main(String path){
        Scanner sc = new Scanner(System.in);
        String [] menuInicial = {"Sair", "Login", "Registar Individuo", "Registar Empresa", "Administrador"};
        String [] menuIndividuos = {"LogOut", "Imprimir Faturas", "Imprimir Faturas por Validar", "Imprimir Fatura Detalhada", "Validar Fatura"};
        String [] menuEmpresas = {"LogOut", "Adicionar Fatura", "Imprimir fatura detalhada", "Imprimir todas as Faturas","Alterar Natureza de Fatura"};
        String [] menuAdmin = {"Sair", "Adicionar Natureza", "Lista das Empresas", "Lista de Individuos", "Info detalhada de Empresa", 
                "Info detalhada de Individuo", "Top empresas com mais valor", "Top 10 Individuos que mais gastaram",
                "Alterar Deducao Fiscal de Empresa", "Alterar Deducao Fiscal de Individuo"};
        int fase = 0;
        int ultima = -1;
        Sistema s = carregaSistema(path);
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
                    case 2: imprimeDetalheFatura(s, e);
                    case 3: imprimeDetalheTodasFaturas(s, e);
                    case 4: /**Alterar Natureza da Fatura*/
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

    /**
     * Metodo que imprime um menu no ecra
     * 
     * @param Array com as opções do menu
     */
    public void printMenu(String [] menu){
        for(int i = 0; i < menu.length; i++){
            System.out.println(i + "-" + menu[i]);
        }
    }

    /**
     * Metodo que imprime o id de todas as faturas de um individuo
     * 
     * @param s   Sistema que contem todas as faturas do individuo
     * @param nif nif do individuo
     */
    public void printFaturas(Sistema s, int nif){
        Set<Fatura> listaF = s.getSistema().get(nif);
        for(Fatura fatura: listaF){
            System.out.println(fatura);
        }
    }

    /**
     * Metodo que imprime o id de todas as faturas por validar
     * 
     * @param s   Sistema que contem todas as faturas do individuo
     * @param nif nif do individuo
     */
    public void printFaturasPorValidar(Sistema s, int nif){
        Set<String> listaF = s.faturaPorValidar(nif);
        for(String str: listaF){
            System.out.println(str);
        }
    }

    /**
     * Metodo que imprime toda a informacao relativa a uma fatura
     * 
     * @param s   Sistema que contem todas as faturas
     * @param nif nif do individuo
     */
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

    /**
     * Metodo que imprime o nif de todas as empresas no sistema
     * 
     * @param s Sistema que contem a informação de todas as empresas
     */
    public void printEmpresas(Sistema s){
        for(Integer i: s.getEmpFaturas().keySet()){
            System.out.println();
        }
    }

    /**
     * Metodo que imprime o nif de todos os individuos
     * 
     * @param s Sistema que contem a informação de todos os individuos
     */
    public void printIndividuos(Sistema s){
        for(Integer i: s.getSistema().keySet()){
            System.out.println();
        }        
    }

    /**
     * Metodo que imprime toda a informação sobre uma empresa
     * 
     * @param s Sistema que contem toda a informação das empresas
     */
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

    /**
     * Metodo que imprime toda a informação de um individuo
     * 
     * @param s Sistema que contem a informação de todos os individuos
     */
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

    /**
     * Metodo que imprime o nif das n empresas com mais valor de um sistema
     * 
     * @param s Sistema que contem toda a informação das empresas e faturas
     */
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

    /**
     * Metodo que imprime os 10 contribuintes que mais gastaram em todo o sistema
     * 
     * @param s Sistema que contem todas as faturas e informação de todos os individuos
     */
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
    /**
     * Metodo que imprime uma Fatura de uma determinada empresa 
     * 
     * @param s Sistema que contem todas as faturas e informação de todas as Empresas 
     * 
     * @param e Empresa que será imprimida uma das faturas
     */
    public void imprimeDetalheFatura(Sistema s, Empresas e){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Insira o Id da fatura");
            String str = sc.nextLine();
            for(FaturaEmpresa f : s.getEmpFaturas().get(e.getNIF())){
                if(f.getId().equals(str))
                    System.out.println(s.getFatura(str, f.getNIF()));
            }
        }
        catch(NaoExisteFaturaException me){
            System.out.println(me.getMessage());
        }
    }
    /**
     * Metodo que imprime todas as Fatura de uma determinada empresa 
     * 
     * @param s Sistema que contem todas as faturas e informação de todas as Empresas 
     * 
     * @param e Empresa que será imprimida todas as faturas
     */
    public void imprimeDetalheTodasFaturas(Sistema s, Empresas e){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Todas as faturas da sua empresa");
            for(FaturaEmpresa f : s.getEmpFaturas().get(e.getNIF())){
                System.out.println(s.getFatura(f.getId(), f.getNIF()));
                }
        }
        catch(NaoExisteFaturaException me){
            System.out.println(me.getMessage());
        }
    }
    /**
     * Metodo que valida o acesso ao sistema de um individuo
     * 
     * @param s Sistema que contem a informação de todos os individuos
     * @param i Parametro em que vai ser guardada a informação do individuo
     */
    public void loginIndividuo(Sistema s, Individuos i){
        boolean b;
        int n = -1;
        Scanner sc = new Scanner(System.in);
        String pass;
        do{
            b = false;
            try{
                System.out.println("Insira NIF");
                n = sc.nextInt();
                System.out.println("Insira palavra passe");
                pass = sc.nextLine();
                b = s.existeIndividuo(n) && s.validaAcesso(n, pass);
            }
            catch (Exception exc){
                System.out.println(exc.getMessage());
            }
        }
        while(!b);
        i = (Individuos) s.getInfo().get(n);
    }

    /**
     * Metodo que valida o acesso ao sistema de uma empresa
     * 
     * @param s Sistema que contem toda a informação das empresas
     * @param e Parametro em que vai ser guardada a informação de um empresa
     */
    public void loginEmpresa(Sistema s, Empresas e){
        boolean b;
        int n = -1;
        Scanner sc = new Scanner(System.in);
        String pass;    
        do{
            b = false;
            try{
                System.out.println("Insira NIF");
                n = sc.nextInt();
                System.out.println("Insira palavra passe");
                pass = sc.nextLine();
                b = s.existeEmpresa(n) && s.validaAcesso(n, pass);
            }
            catch (Exception exc){
                System.out.println(exc.getMessage());
            }
        }
        while(!b);
        e = (Empresas) s.getInfo().get(n);       
    }

    /**
     * Metodo que regista um individuo num sistema
     * 
     * @param s Sistema no qual o individuo vai ser inserido
     * @param i Parametro em que vai ser guardada a informação do individuo
     */
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

    /**
     * Metodo que vai inserir uma empresa num sistema
     * 
     * @param s Sistema que vai ser inserida a empresa
     * @param e Parametro em que vai ser guardada a informação da empresa
     */
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

    /**
     * Metodo para o administrador entrar no sistema
     * 
     * @param s Sistema no qual o administrador pretende aceder
     */
    public void logAdmin(Sistema s){
        String str;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Insira a pass admin");
            str = sc.nextLine();
        }
        while(!s.getAdministrador().getPassword().equals(str));
    }

    /**
     * Metodo que permite a um individuo validar uma fatura
     * 
     * @param s Sistema que guarda a informação de todas as faturas do individuo
     * @param i Individuo que pretende aceder ao sistema para validar as suas faturas
     */
    public void validaFatura(Sistema s, Individuos i){
        boolean b;
        String str;
        int n;
        Scanner sc = new Scanner(System.in);
        Fatura f;
        Natureza nat;
        List<Natureza> lista;
        do{
            b = true;
            try{
                System.out.println("Insira o id da Fatura que quer mudar de natureza");
                str = sc.nextLine();
                f = s.getFatura(str, i.getNIF());
                lista = f.getNatureza().stream().collect(Collectors.toList());
                for(int j = 0; j < lista.size(); j++){
                    System.out.println(i + "-" + lista.get(j));
                }
                n = sc.nextInt();
                nat = lista.get(n);
                f.escolheNatureza(nat);
                b = false;
            }
            catch(NaoExisteFaturaException exc){
                System.out.println(exc);
            }
            catch(IndexOutOfBoundsException exc){
                System.out.println(exc);
            }
            catch(NaturezaInvalidaException exc){
                System.out.println(exc);
            }
        }while(b);
    }

    /**
     * Metodo que permite a uma empresa adicionar faturas no sistema
     * 
     * @param s Sistema no qual a empresa pretende inserir a fatura
     * @param e Empresa que pretende inserir uma fatura no sistema
     */
    public void adicionaFatura(Sistema s, Empresas e){
        boolean b;
        int n;
        Fatura f = new Fatura();
        String str;
        Scanner sc = new Scanner(System.in);
        List<Natureza> lista = e.getAtividades().stream().collect(Collectors.toList());
        Natureza nat;
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
            b = true;
            try{
                System.out.println("Insira o NIF do cliente");
                n = sc.nextInt();
                f.setCliente(n);
                System.out.println("Insira o valor da fatura");
                n = sc.nextInt();
                f.setValor(n);
                while(n != lista.size()){
                    for(int i = 0; i < lista.size(); i++){
                        System.out.println(i + "-" + lista.get(i));
                        n = sc.nextInt();
                        nat = lista.get(n);
                        f.adicionaNatureza(nat);
                    }
                }
                s.adicionaFatura(f);
                b = false;
            }
            catch (Exception exc){
                System.out.println(exc);
            }
        }
        while(b);
    }

    public void alteraNatureza(Sistema s, Empresas e){
        Scanner sc = new Scanner(System.in);
        int n;
        int i;
        String id;
        Fatura f;
        List<Natureza> listaF, listaE = e.getAtividades().stream().collect(Collectors.toList());
        Natureza nat;
        try{
            System.out.println("NIF do cliente fatura a alterar");
            n = sc.nextInt();
            System.out.println("Id da fatura");
            id = sc.nextLine();
            f = s.getFatura(id, n);
            System.out.println("Inserir (0) ou remover natureza");
            n = sc.nextInt();
            listaF = f.getNatureza().stream().collect(Collectors.toList());
            if(n == 0){
                for(i = 0; i < listaE.size(); i++){
                    System.out.println(i + "-" + listaE.get(i));
                }
                nat = listaE.get(i);
                f.adicionaNatureza(nat);
            }
            else{
                /** Como fazer para remover uma natureza por causa dos clones */
            }
        }
        catch(Exception exc){
            
        }
    }

    /**
     * Metodo que permite ao administrador adicionar uma nova natureza no sistema
     * 
     * @param s Sistema no qual se pretende adicionar uma natureza
     */
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

    /**
     * Metodo que carrega informação para um sistema
     * 
     * @param path caminho para o ficheiro
     */
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