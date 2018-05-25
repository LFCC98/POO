import java.util.Scanner;
import java.util.*;
import java.util.stream.*;
import java.time.*;
import java.io.*;

public class JavaFaturaMenu{
    /** Inteiro que indica a fase do menu atual*/
    private int fase;
    /**
     * Construtor vazio
     */
    public JavaFaturaMenu(){
        fase = 0;
    }
    /**
     * Metodo main
     * 
     * @param path  Caminho para o ficheiro
     */
    public void main(String path){
        Scanner sc = new Scanner(System.in);
        String [] menuInicial = {"Sair", "Login Individuo","Login Empresa", "Registar Individuo", "Registar Empresa", "Administrador"};
        String [] menuIndividuos = {"LogOut", "Imprimir Faturas", "Imprimir Faturas por Validar", "Imprimir Fatura Detalhada",
                "Validar Fatura", "Montante Acumulado", "Montante Acumulado familia"};
        String [] menuEmpresas = {"LogOut", "Adicionar Fatura", "Imprimir fatura detalhada", "Imprimir Faturas", 
                "Imprimir faturas por valor", "Imprimir todas as Faturas por data", "Imprimir Faturas por contribuinte e valor", 
                "Alterar Natureza de Fatura", "Total faturado"};
        String [] menuAdmin = {"Sair", "Adicionar Natureza", "Lista das Empresas", "Lista de Individuos", "Info detalhada de Empresa", 
                "Info detalhada de Individuo", "Top empresas com mais valor", "Top 10 Individuos que mais gastaram", 
                "Imprimir Faturas Individuo", "Imprimir Faturas Empresa"};
        int ultima = -1;
        Sistema s = new Sistema();
        try{
            s.carregaEstado("Estado");
        }
        catch(FileNotFoundException exc){
            System.out.println(exc);
            criaSistema(s);
        }
        catch(IOException exc){
            System.out.println(exc);
            criaSistema(s);
        }
        catch(ClassNotFoundException exc){
            System.out.println(exc);
            criaSistema(s);
        }
        String str;
        Individuos i = null;
        Empresas e = null;
        boolean b;
        while(fase != -1){
            switch(fase){
                case 0: printMenu(menuInicial);
                do{
                    b = true;
                    try{
                        ultima = sc.nextInt();
                        b = false;
                    }
                    catch(Exception exc){
                        System.out.println(exc);
                    }
                }
                while(b);
                switch(ultima){
                    case 0: System.out.println("A sair");
                    fase = -1;
                    break;
                    case 1: i = loginIndividuo(s);
                    break;
                    case 2: e = loginEmpresa(s);
                    break;
                    case 3: i = registaIndividuo(s);
                    fase = 1;
                    break;
                    case 4: e = registaEmpresa(s);
                    fase = 2;
                    break;
                    case 5: logAdmin(s);
                    fase = 3;
                    break;
                }
                break;
                case 1: printMenu(menuIndividuos);
                do{
                    b = true;
                    try{
                        ultima = sc.nextInt();
                        b = false;
                    }
                    catch(Exception exc){
                        System.out.println(exc);
                    }
                }
                while(b);
                switch(ultima){
                    case 0: fase = 0;
                    break;
                    case 1: printFaturas(s, i.getNIF());
                    break;
                    case 2: printFaturasPorValidar(s, i.getNIF());
                    break;
                    case 3: printFaturaDetalhada(s, i.getNIF());
                    break;
                    case 4: validaFatura(s, i);
                    break;
                    case 5: printMontanteAcumulado(s, i);
                    break;
                    case 6: printMontanteFamilia(s, i);
                    break;
                }
                break;
                case 2: printMenu(menuEmpresas);
                do{
                    b = true;
                    try{
                        ultima = sc.nextInt();
                        b = false;
                    }
                    catch(Exception exc){
                        System.out.println(exc);
                    }
                }
                while(b);
                switch(ultima){
                    case 0: fase = 0;
                    break;
                    case 1: adicionaFatura(s, e);
                    break;
                    case 2: imprimeDetalheFatura(s, e);
                    break;
                    case 3: imprimeTodasFaturas(s, e);
                    break;
                    case 4: imprimeFaturasValor(s, e);
                    break;
                    case 5: imprimeFaturasData(s, e);
                    break;
                    case 6: imprimeFaturasContribuinteValor(s, e);
                    break;
                    case 7: alteraNatureza(s, e);
                    break;
                    case 8: printTotalFaturado(s, e);
                    break;
                }
                break;
                case 3: printMenu(menuAdmin);
                do{
                    b = true;
                    try{
                        ultima = sc.nextInt();
                        b = false;
                    }
                    catch(Exception exc){
                        System.out.println(exc);
                    }
                }
                while(b);
                switch(ultima){
                    case 0: fase = 0;
                    break;
                    case 1: adicionaNatureza(s);
                    break;
                    case 2: printEmpresas(s);
                    break;
                    case 3: printIndividuos(s);
                    break;
                    case 4: printDetalheEmpresa(s);
                    break;
                    case 5: printDetalheIndividuo(s);
                    break;
                    case 6: printEmpresasComMaisValor(s);
                    break;
                    case 7: print10ContribuintesMaisGastam(s);
                    break;
                    case 8: printFaturasIndividuo(s);
                    break;
                    case 9: printFaturasEmpresa(s);
                    break;
                }
                break;
            }
        }
        try{
            s.guardaEstado("Estado");
        }
        catch(FileNotFoundException exc){
            System.out.println(exc);
        }
        catch(IOException exc){
            System.out.println(exc);
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
     * 
     * @param nif nif do individuo
     */
    public void printFaturas(Sistema s, int nif){
        Set<Fatura> listaF = s.getSistema().get(nif);
        for(Fatura fatura: listaF){
            System.out.println(fatura.getId());
        }
    }
    /**
     * Metodo que imprime o id de todas as faturas por validar
     * 
     * @param s   Sistema que contem todas as faturas do individuo
     * 
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
     * 
     * @param nif nif do individuo
     */
    public void printFaturaDetalhada(Sistema s, int nif){
        Scanner sc = new Scanner(System.in);
        String id;
        Fatura f;
        try{
            id = sc.nextLine();
            f = s.getFatura(id, nif);
            System.out.println(f);
        }
        catch(NaoExisteFaturaException exc){
            System.out.println(exc);
        }
        catch(Exception exc){
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
            System.out.println(i);
        }
    }
    /**
     * Metodo que imprime o nif de todos os individuos
     * 
     * @param s Sistema que contem a informação de todos os individuos
     */
    public void printIndividuos(Sistema s){
        for(Integer i: s.getSistema().keySet()){
            System.out.println(i);
        }        
    }
    /**
     * Metodo que imprime o montante deduzido pelo individuo no último ano
     * 
     * @param s Sistema que contem a informação de todos os individuos
     * 
     * @param i Individuo que pretende descodrir o montante deduzido
     */
    public void printMontanteAcumulado(Sistema s, Individuos i){
        try{
            LocalDate end = LocalDate.now();
            LocalDate begin = LocalDate.of(end.getYear() - 1, end.getMonth(), end.getDayOfMonth());
            double montante = s.valorTotalDeduzidoTempo(i.getNIF(), begin, end);
            System.out.println(montante);
        }
        catch(NaoExisteIndividuoException exc){
            System.out.println(exc);
        }
        catch(Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * Metodo que imprime o montante deduzido pela familia
     * 
     * @param s Sistema que contem a informação de todos os individuos
     * 
     * @param i Individuo que pretende descobrir o montante deduzido pelo agregado familiar
     */
    public void printMontanteFamilia(Sistema s, Individuos i){
        try{
            LocalDate end = LocalDate.now();
            LocalDate begin = LocalDate.of(end.getYear() - 1, end.getMonth(), end.getDayOfMonth());
            double montante = s.valorTotalDeduzidoFam(i.getNIF(), begin, end);
            System.out.println(montante);
        }
        catch(NaoExisteIndividuoException exc){
            System.out.println(exc);
        }
        catch(Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * Metodo que imprime o total faturado por uma empresa
     * 
     * @param s Sistema que contem todas as faturas da empresa
     * 
     * @param e Empresa que quer descobrir o total faturado
     */
    public void printTotalFaturado(Sistema s, Empresas e){
        try{
            double total = s.valorTotalEmpresa(e.getNIF());
            System.out.println(total);
        }
        catch(NaoExisteEmpresaException exc){
            System.out.println(exc);
        }
        catch(NaoExisteFaturaException exc){
            System.out.println(exc);
        }
        catch(Exception exc){
            System.out.println(exc);
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
            System.out.println("NIF da empresa");
            nif = sc.nextInt();
            if(s.existeEmpresa(nif)){
                System.out.println(s.getInfo().get(nif));
            }
        }
        catch (Exception exc){
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
            System.out.println("NIF do individuo");
            nif = sc.nextInt();
            if(s.existeIndividuo(nif)){
                System.out.println(s.getInfo().get(nif));
            }
        }
        catch (Exception exc){
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
        int n;
        Set<Integer> list;
        try{
            System.out.println("Numero de empresas");
            n = sc.nextInt();
            list = s.topXEmpresas(n);
            for(Integer i: list){
                System.out.println(i);
            }
        }
        catch(NaoExisteNIFException exc){
            System.out.println(exc);
        }
        catch(NaoExisteFaturaException exc){
            System.out.println(exc);
        }
        catch (Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * Metodo que imprime os 10 contribuintes que mais gastaram em todo o sistema
     * 
     * @param s Sistema que contem todas as faturas e informação de todos os individuos
     */
    public void print10ContribuintesMaisGastam(Sistema s){
        try{
            int x = 1;
            Set<Integer> top = s.top10Contribuintes();
            for(Integer i: top){
                System.out.println(x + "-" + i);
                x++;
            }    
        }
        catch(NaoExisteNIFException exc){
            System.out.println(exc);
        }
        catch (Exception exc){
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
        Fatura f;
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Insira o Id da fatura");
            String str = sc.nextLine();
            f = s.getFatura(str, e);
            System.out.println(f.toString());
        }
        catch(NaoExisteFaturaException me){
            System.out.println(me);
        }
        catch (Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * Metodo que imprime todas as Fatura de uma determinada empresa 
     * 
     * @param s Sistema que contem todas as faturas e informação de todas as Empresas 
     * 
     * @param e Empresa que será imprimida todas as faturas
     */
    public void imprimeTodasFaturas(Sistema s, Empresas e){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Todas as faturas da sua empresa:");
            int nif = e.getNIF();
            Set<Fatura> listaF = s.getFaturasEmpresas(nif);
            for(Fatura f: listaF){
                System.out.println(f.getId());
            }
        }
        catch(NaoExisteFaturaException me){
            System.out.println(me);
        }
        catch (Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * Metodo que imprime todas as faturas de uma empresa ordenadas por ordem crescente
     * 
     * @param s Sistema que contem todas as faturas da empresa
     * 
     * @param e Empresa que será imprimido o total faturado
     */
    public void imprimeFaturasValor(Sistema s, Empresas e){
        try{
            Set<Fatura> listaF = s.ordenaValor(e.getNIF());
            for(Fatura f: listaF){
                System.out.println(f.getId());
            }
        }
        catch(NaoExisteEmpresaException exc){
            System.out.println(exc);
        }
        catch(NaoExisteFaturaException exc){
            System.out.println(exc);
        }
        catch (Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * Metodo que imprime as faturas de uma empresa ordenadas por data
     * 
     * @param s Sistema que contem todas as faturas da empresa
     * 
     * @param e Empresa do qual são imprimidas todas as faturas ordenadas por data
     */
    public void imprimeFaturasData(Sistema s, Empresas e){
        try{
            Set<Fatura> listaF = s.ordenaData(e.getNIF());
            for(Fatura f: listaF)
                System.out.println(f.getId());
        }
        catch(NaoExisteNIFException exc){
            System.out.println(exc);
        }
        catch(NaoExisteFaturaException exc){
            System.out.println(exc);
        }
        catch (Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * Metodo que imprime todas as faturas de uma empresa
     * 
     * @param s Sistema que contem todas as faturas da Empresa
     */
    public void printFaturasEmpresa(Sistema s){
        Scanner sc = new Scanner(System.in);
        int n;
        try{
            System.out.println("Qual o NIF da Empresa");
            n = sc.nextInt();
            Set<Fatura> listaF = s.getFaturasEmpresas(n);
            for(Fatura f: listaF){
                System.out.println(f.getId());
            }
        }
        catch(Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * Metodo que imprime todas as faturas de um individuo
     * 
     * @param s Sistema que contem todas as faturas do individuo
     */
    public void printFaturasIndividuo(Sistema s){
        Scanner sc = new Scanner(System.in);
        int n;
        try{
            System.out.println("Qual o NIF do Individuo");
            n = sc.nextInt();
            printFaturas(s, n);
        }
        catch(Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * Metodo que imprime as faturas de uma empresa ordenadas por contribuinte e por valor
     * 
     * @param s Sistema que contem todas as faturas da empresa
     * 
     * @param e Empresa que são imprimdas as faturas
     */
    public void imprimeFaturasContribuinteValor(Sistema s, Empresas e){
        try{
            Set<Fatura> listaF = s.ordenaContribuinteValor(e.getNIF());
            for(Fatura f: listaF)
                System.out.println(f.getId());
        }
        catch(NaoExisteNIFException exc){
            System.out.println(exc);
        }
        catch(NaoExisteFaturaException exc){
            System.out.println(exc);
        }
        catch (Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * Metodo que valida o acesso ao sistema de um individuo
     * 
     * @param s Sistema que contem a informação de todos os individuos
     * 
     * @param i Parametro em que vai ser guardada a informação do individuo
     */
    public Individuos loginIndividuo(Sistema s){
        boolean b;
        int n = -1;
        Individuos i = new Individuos();
        Scanner sc = new Scanner(System.in);
        String pass;
        try{
            System.out.println("Insira NIF individuo");
            n = sc.nextInt();
            System.out.println("Insira palavra passe");
            pass = sc.nextLine();
            pass = sc.nextLine();
            b = s.existeIndividuo(n) && s.validaAcesso(n, pass);
            if(b){
                i = (Individuos) s.getInfo().get(n);
                fase = 1;
                return i;
            }
        }
        catch (NaoExisteNIFException exc){
            System.out.println(exc);
        }
        catch(PasseErradaException exc){
            System.out.println(exc);
        }
        catch(Exception exc){
            System.out.println(exc);
        }
        return i;
    }
    /**
     * Metodo que valida o acesso ao sistema de uma empresa
     * 
     * @param s Sistema que contem toda a informação das empresas
     * 
     * @param e Parametro em que vai ser guardada a informação de um empresa
     */
    public Empresas loginEmpresa(Sistema s){
        int n = -1;
        boolean b;
        Scanner sc = new Scanner(System.in);
        String pass;
        Empresas e = new Empresas();
        try{
            System.out.println("Insira NIF");
            n = sc.nextInt();
            pass = sc.nextLine();
            System.out.println("Insira palavra passe");
            pass = sc.nextLine();
            b = s.existeEmpresa(n) && s.validaAcesso(n, pass);
            if(b){
                e = (Empresas) s.getInfo().get(n);
                fase = 2;
            }
            return e;
        }
        catch (NaoExisteNIFException exc){
            System.out.println(exc);
        }
        catch(PasseErradaException exc){
            System.out.println(exc);
        }
        catch(Exception exc){
            System.out.println(exc);
        }
        return e;
    }
    /**
     * Metodo que regista um individuo num sistema
     * 
     * @param s Sistema no qual o individuo vai ser inserido
     * 
     * @param i Parametro em que vai ser guardada a informação do individuo
     */
    public Individuos registaIndividuo(Sistema s){
        String str;
        Scanner sc = new Scanner(System.in);
        Individuos i = new Individuos();
        boolean b;
        int n = -1;
        int j = 0;
        List<Natureza> listaNat = new ArrayList<>();
        try{
            do{
                System.out.println("Insira o NIF (9 numeros)");
                n = sc.nextInt();
            }
            while(9 != String.valueOf(n).length() && !s.existeNIF(n));
            i.setNIF(n);
            System.out.println("Email");
            str = sc.nextLine();
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
            System.out.println("Insira o numero de dependentes do agregado familiar");
            n = sc.nextInt();
            i.setAgregado(n);
            while(j < i.getAgregado()){
                System.out.println("Insira o NIF do agregado familiar");
                n = sc.nextInt();
                i.insereAgregado(n);
                j++;
            }
            i.setCodigo(s.getNatureza().stream().collect(Collectors.toSet()));
            s.adicionaIndividuo(i);
            //Fazer metodo em individuos que calcula o coeficiente fiscal de um individuo
            System.out.println("Acabou de se registar");
            fase = 1;
            return i;
        }
        catch(ExisteAgregadoException exc){
            System.out.println(exc);
        }
        catch(ExisteNIFSistemaException exc){
            System.out.println(exc);
        }
        catch (Exception exc){
            System.out.println(exc);
        }
        return i;
    }
    /**
     * Metodo que vai inserir uma empresa num sistema
     * 
     * @param s Sistema que vai ser inserida a empresa
     * 
     * @param e Parametro em que vai ser guardada a informação da empresa
     */
    public Empresas registaEmpresa(Sistema s){
        String str;
        boolean b;
        int n;
        Empresas e = new Empresas();
        Scanner sc = new Scanner(System.in);
        int ultima = 0;
        List<Natureza> listaNat = new ArrayList<>();
        try{
            do{
                System.out.println("Insira o NIF (9 numeros)");
                n = sc.nextInt();
            }
            while(9 != String.valueOf(n).length() && !s.existeNIF(n));
            e.setNIF(n);
            System.out.println("Email");
            str = sc.nextLine();
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
            listaNat = s.getNatureza();
            int x = listaNat.size();
            do{
                for(int k = 0; k < x; k++){
                    System.out.println(k + "-" + listaNat.get(k));
                }
                System.out.println(x + "-Sair");
                try{
                    ultima = sc.nextInt();
                    if(ultima < x){
                        Natureza nat = listaNat.get(ultima);
                        e.adicionaAtividade(nat);
                    }
                }
                catch (JaExisteNaturezaException exc){
                    System.out.println(exc);
                }
                catch(Exception exc){
                    System.out.println(exc);
                }
            }
            while(ultima != x);
            s.adicionaEmpresas(e);
            System.out.println("Acabou de se registar");
            fase = 2;
            return e;
        }
        catch(ExisteNIFSistemaException exc){
            System.out.println(exc);
        }
        return e;
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
     * 
     * @param i Individuo que pretende aceder ao sistema para validar as suas faturas
     */
    public void validaFatura(Sistema s, Individuos i){
        String str;
        int n;
        Scanner sc = new Scanner(System.in);
        Fatura f;
        Natureza nat;
        List<Natureza> lista;
        try{
            System.out.println("Insira o id da Fatura que quer mudar de natureza");
            str = sc.nextLine();
            f = s.getFatura(str, i.getNIF());
            lista = f.getNatureza().stream().collect(Collectors.toList());
            for(int j = 0; j < lista.size(); j++){
                System.out.println(j + "-" + lista.get(j));
            }
            n = sc.nextInt();
            sc.nextLine();
            nat = lista.get(n);
            f.escolheNatureza(nat);
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
        catch(Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * Metodo que permite a uma empresa adicionar faturas no sistema
     * 
     * @param s Sistema no qual a empresa pretende inserir a fatura
     * @param e Empresa que pretende inserir uma fatura no sistema
     */
    public void adicionaFatura(Sistema s, Empresas e){
        int n;
        Fatura f = new Fatura();
        String str;
        Scanner sc = new Scanner(System.in);
        List<Natureza> lista = e.getAtividades().stream().collect(Collectors.toList());
        Natureza nat;
        try{
            n = e.getNIF();
            str = Integer.toString(n) + Integer.toString(s.getFaturasEmpresas(n).size());
            f.setId(str);
            System.out.println("Descrição da Fatura");
            str = sc.nextLine();
            f.setDescricao(str);
            f.setDesignacao(e.getNome());
            f.setEmitente(e.getNIF());
            f.setData(LocalDate.now());
            System.out.println("Insira o NIF do cliente");
            n = sc.nextInt();
            str = sc.nextLine();
            f.setCliente(n);
            System.out.println("Insira o valor da fatura");
            n = sc.nextInt();
            str = sc.nextLine();
            f.setValor(n);
            while(n != lista.size() || f.getNatureza().size() == 0){
                for(int i = 0; i < lista.size(); i++){
                    System.out.println(i + "-" + lista.get(i).getTipo());
                }
                if(f.getNatureza().size() != 0){
                    System.out.println(lista.size() + "-Avanca ");
                }
                n = sc.nextInt();
                str = sc.nextLine();
                if(n < lista.size()){
                    nat = lista.get(n);
                    f.adicionaNatureza(nat);
                }
            }
            s.adicionaFatura(f);
        }
        catch(NaturezaInvalidaException exc){
            System.out.println(exc);
        }
        catch(NaoExisteIndividuoException exc){
            System.out.println(exc);
        }
        catch(ExisteFaturaException exc){
            System.out.println(exc);
        }
        catch (Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * 
     * 
     * 
     * 
     * 
     */
    public void alteraNatureza(Sistema s, Empresas e){
        Scanner sc = new Scanner(System.in);
        int n;
        String id;
        Fatura f;
        List<Natureza> listaF, listaE = e.getAtividades().stream().collect(Collectors.toList());
        Natureza nat;
        try{
            System.out.println("Id da fatura");
            id = sc.nextLine();
            f = s.getFatura(id, e);
            System.out.println("Inserir (0) ou remover natureza");
            n = sc.nextInt();
            listaF = f.getNatureza().stream().collect(Collectors.toList());
            if(n == 0){
                for(int i = 0; i < listaE.size(); i++){
                    System.out.println(i + "-" + listaE.get(i));
                }
                n = sc.nextInt();
                nat = listaE.get(n);
                s.alteraNatureza(f.getId(), nat, f.getCliente());
            }
            else{
                for(int i = 0; i < listaF.size(); i++){
                    System.out.println(i + "-" + listaF.get(i));
                }
                n = sc.nextInt();
                nat = listaE.get(n);
                s.removeNaturezaFatura(f.getId(),f.getCliente(), nat);
            }
        }
        catch(Exception exc){
            System.out.println(exc);
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
        catch(JaExisteNaturezaException exc){
            System.out.println(exc);
        }
        catch(Exception exc){
            System.out.println(exc);
        }
    }
    /**
     * Metodo que carrega informação para um sistema
     * 
     * @param path caminho para o ficheiro
     * 
     * @return Sistema que carregou a informação do ficheiro
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
    /**
     * Metodo que cria um sistema
     * 
     * @param s Sistema em que são guardadas as alterações
     */
    public void criaSistema(Sistema s){
        try{
            Natureza n1 = new Natureza("Educaçao", 500, 0.45);
            Natureza n2 = new Natureza("Saude", 5000, 0.25);
            Natureza n3 = new Natureza("Restauraçao", 4400, 0.20);
            Natureza n4 = new Natureza("Outro", 0, 0);
            Natureza n5 = new Natureza("Construção Civil", 25000, 0.05);
            Natureza n6 = new Natureza("Comércio", 250, 0.15);
            Natureza n7 = new Natureza("Reparações", 100, 0.5);
            s.adicionaNatureza(n1);
            s.adicionaNatureza(n2);
            s.adicionaNatureza(n3);
            s.adicionaNatureza(n4);
            s.adicionaNatureza(n5);
            s.adicionaNatureza(n6);
            s.adicionaNatureza(n7);

            Set<Integer> si = new HashSet<>(10);
            Set<Natureza> s1 = new HashSet<>(), s2 = new HashSet<>(), s3 = new HashSet<>(), s4 = new HashSet<>(), s5 = new HashSet<>();
            s1.add(n1); s1.add(n2);
            s2.add(n2); s2.add(n3);
            s3.add(n4);
            s4.add(n3); s4.add(n6); s4.add(n7);
            s5.add(n3); s5.add(n7);

            Empresas e1 = new Empresas(111111111, "empresae1@gmail.com", "e1Company", "e1Place", "e1Pass", s1, 1);
            Empresas e2 = new Empresas(222222222, "empresae2@gmail.com", "e2Company", "e2Place", "e2Pass", s2, 1);
            Empresas e3 = new Empresas(333333333, "empresae3@gmail.com", "e3Company", "e3Place", "e3Pass", s2, 1);
            Empresas e4 = new Empresas(444444444, "empresae4@gmail.com", "e4Company", "e4Place", "e4Pass", s3, 1);
            Empresas e5 = new Empresas(123412341, "empresae5@hotmail.com", "e5Company", "Guarda", "e5Pass", s4, 1);
            s.adicionaEmpresas(e1);
            s.adicionaEmpresas(e2);
            s.adicionaEmpresas(e3);
            s.adicionaEmpresas(e4);
            s.adicionaEmpresas(e5);

            Individuos i1 = new Individuos(555555555, "individuosi1@gmail.com", "Joao Costa Silva", "l1", "i1Pass", 0, si, 1, s1);
            Individuos i2 = new Individuos(666666666, "individuosi2@gmail.com", "Joao  Manuel Pereira Silva", "l2", "i2Pass", 0, si, 1, s1);
            Individuos i3 = new Individuos(777777777, "individuosi3@gmail.com", "Ana Silva", "l3", "i3Pass", 0, si, 1, s1);
            Individuos i4 = new Individuos(888888888, "individuosi4@gmail.com", "Lucas Peixoto Silva", "l4", "i4Pass", 0, si, 1, s1);
            Individuos i5 = new Individuos(855555558, "individuosi5@gmail.com", "Rodrigo Costa", "Vila Verde", "i5Pass", 0, si, 1, s1);
            Individuos i6 = new Individuos(123456789, "abc@gmail.com", "André Peixoto", "Porto", "i6Pass", 0, si, 1, s1);
            Individuos i7 = new Individuos(987654321, "xyz@gmail.com", "Bruno Peixoto", "Porto", "i8Pass", 0, si, 1, s1);
            Individuos i8 = new Individuos(112233445, "myemail@gmail.com", "Marega", "Porto", "i8Pass", 0, si, 1, s1);
            s.adicionaIndividuo(i1);
            s.adicionaIndividuo(i2);
            s.adicionaIndividuo(i3);
            s.adicionaIndividuo(i4);
            s.adicionaIndividuo(i5);
            s.adicionaIndividuo(i6);
            s.adicionaIndividuo(i7);
            s.adicionaIndividuo(i8);
            
            Fatura f1 = new Fatura("4444444440", e4.getNIF(), i4.getNIF(), e4.getNome(),"Doces", LocalDate.of(2012, 12, 12), s3, 10,
                    s3.stream().collect(Collectors.toList()));
            Fatura f2 = new Fatura("2222222220", e2.getNIF(), i3.getNIF(), e2.getNome(),"Comer", LocalDate.of(2013, 12, 12), s2, 150,
                    s2.stream().collect(Collectors.toList()));
            Fatura f3 = new Fatura("4444444441", e4.getNIF(), i1.getNIF(), e4.getNome(),"Doces", LocalDate.of(2014, 12, 12), s3, 1,
                    s3.stream().collect(Collectors.toList()));
            Fatura f4 = new Fatura("3333333330", e3.getNIF(), i1.getNIF(), e3.getNome(),"Gripe", LocalDate.of(2012, 1, 12), s2, 25,
                    s2.stream().collect(Collectors.toList()));
            Fatura f5 = new Fatura("4444444442", e4.getNIF(), i1.getNIF(), e4.getNome(),"Doces", LocalDate.of(2018, 1, 12), s3, 8,
                    s3.stream().collect(Collectors.toList()));
            Fatura f6 = new Fatura("1234123410", e5.getNIF(), i1.getNIF(), e5.getNome(),"Lanche e Recordação", LocalDate.of(2018, 3, 5), 
                    s4, 8, s4.stream().collect(Collectors.toList()));
            Fatura f7 = new Fatura("1234123411", e5.getNIF(), i1.getNIF(), e5.getNome(),"Lanche e Recordação", LocalDate.of(2018, 2, 25), 
                    s5, 8, s5.stream().collect(Collectors.toList()));
            s.adicionaFatura(f1);
            s.adicionaFatura(f2);
            s.adicionaFatura(f3);
            s.adicionaFatura(f4);
            s.adicionaFatura(f5);
            s.adicionaFatura(f6);
            s.adicionaFatura(f7);

            s.addAgregado(i4.getNIF(), i3.getNIF());
            s.addAgregado(i4.getNIF(), i5.getNIF());
            s.addAgregado(i4.getNIF(), i6.getNIF());
            s.addAgregado(i4.getNIF(), i7.getNIF());
            s.addAgregado(i4.getNIF(), i8.getNIF());

            Administrador admin = new Administrador("Admin", "admin");
            s.setAdministrador(admin);
        }
        catch(NaoExisteIndividuoException exc){
            System.out.println(exc);
        }
        catch(ExisteFaturaException exc){
            System.out.println(exc);
        }
        catch(JaExisteNaturezaException exc){
            System.out.println(exc);
        }
        catch(ExisteAgregadoException exc){
            System.out.println(exc);
        }
        catch(ExisteNIFSistemaException exc){
            System.out.println(exc);
        }
    }
}