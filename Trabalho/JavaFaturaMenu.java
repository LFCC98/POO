import java.util.Scanner;
import java.util.*;
import java.util.stream.*;
import java.time.*;
import java.io.*;

public class JavaFaturaMenu{
    private int fase;

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
        String [] menuEmpresas = {"LogOut", "Adicionar Fatura", "Imprimir fatura detalhada", "Imprimir faturas por valor"
            , "Imprimir todas as Faturas por data", "Imprimir faturas entre datas", "Imprimir Faturas por contribuinte e valor",
                "Alterar Natureza de Fatura", "Total faturado"};
        String [] menuAdmin = {"Sair", "Adicionar Natureza", "Lista das Empresas", "Lista de Individuos", "Info detalhada de Empresa", 
                "Info detalhada de Individuo", "Top empresas com mais valor", "Top 10 Individuos que mais gastaram",
                "Alterar Deducao Fiscal de Empresa", "Alterar Deducao Fiscal de Individuo"};
        int ultima = -1;
        Sistema s = criaSistema();
        String str;
        Individuos i = null;
        Empresas e = null;
        while(fase != -1){
            switch(fase){
                case 0: printMenu(menuInicial);
                /**Fazer o try aqui*/
                ultima = sc.nextInt();
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
                /**Fazer o try aqui*/
                ultima = sc.nextInt();
                switch(ultima){
                    case 0: fase = 0;
                    break;
                    case 1: 
                    printFaturas(s, i.getNIF());
                    break;
                    case 2: printFaturasPorValidar(s, i.getNIF());
                    break;
                    case 3: printFaturaDetalhada(s, i.getNIF());
                    break;
                    case 4: //ver melhor
                    validaFatura(s, i);
                    break;
                    case 5: /*printMontanteAcumulado(s, i); */
                    break;
                    case 6: /*printMontanteFamilia(s, i); */
                    break;
                }
                break;
                case 2: printMenu(menuEmpresas);
                /**Fazer try aqui*/
                ultima = sc.nextInt();
                switch(ultima){
                    case 0: fase = 0;
                    break;
                    case 1: adicionaFatura(s, e);
                    break;
                    case 2: imprimeDetalheFatura(s, e);
                    break;
                    case 3: imprimeDetalheTodasFaturas(s, e);
                    break;
                    case 4: /*imprimeFaturasPorValor()*/
                    break;
                    case 5: /*imprimeFaturasPorData()*/
                    break;
                    case 6: /*imprimeFaturasEntreDatas()*/
                    break;
                    case 7: /*imprimePorContribuinteValor()*/
                    break;
                    case 8: alteraNatureza(s, e);
                    break;
                    case 9: printTotalFaturado(s, e);
                }
                break;
                case 3: printMenu(menuAdmin);
                /**Fazer o try aqui*/
                ultima = sc.nextInt();
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
                }
                break;
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
            System.out.println(fatura.getId());
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

    public void printMontanteAcumulado(Sistema s, Individuos i){
        try{
            
        }
        catch(Exception excccccccccccccccc){
            
        }
    }
    
    public void printMontanteFamilia(Sistema s, Individuos i){
        try{
            
        }
        catch(Exception excccccccccc){
            
        }
    }

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
            System.out.println(exc.getMessage());
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
            System.out.println(exc.getMessage());
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
                begin.of(ano, mes, dia);/*
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
            Fatura f = s.getFatura(str, e);
            System.out.println(f);
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
            System.out.println("Todas as faturas da sua empresa:");
            int nif = e.getNIF();
            Set<Fatura> listaF = s.getFaturasEmpresas(nif);
            for(Fatura f: listaF){
                System.out.println(f.getId());
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
        catch (Exception exc){
            System.out.println(exc.getMessage());
        }
        return i;
    }

    /**
     * Metodo que valida o acesso ao sistema de uma empresa
     * 
     * @param s Sistema que contem toda a informação das empresas
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
        catch (Exception exc){
            System.out.println(exc.getMessage());
        }
        return e;
    }      

    /**
     * Metodo que regista um individuo num sistema
     * 
     * @param s Sistema no qual o individuo vai ser inserido
     * @param i Parametro em que vai ser guardada a informação do individuo
     */
    public Individuos registaIndividuo(Sistema s){
        String str;
        Scanner sc = new Scanner(System.in);
        Individuos i = new Individuos();
        boolean b;
        int n = -1;
        int j = 0;
        int fase;
        int ultima = -1;
        List<Natureza> listaNat = new ArrayList<>();
        try{
            System.out.println("Insira o NIF");
            n = sc.nextInt();
            i.setNIF(n);
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
            System.out.println("Insira o numero de dependentes do agregado familiar");
            n = sc.nextInt();
            i.setAgregado(n);
            while(j < i.getAgregado()){
                System.out.println("Insira o NIF do agregado familiar");
                n = sc.nextInt();
                i.insereAgregado(n);
                j++;
            }
            //Fazer metodo em individuos que calcula o coeficiente fiscal de um individuo
            //i.setCodigo(s.getNatureza().stream().collect(Collectors.toList()));
            System.out.println("Acabou de se registar");
            fase = 1;
            return i;
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
            System.out.println("Insira o NIF");
            n = sc.nextInt();
            e.setNIF(n);
            s.existeNIF(n);
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
            return e;
        }
        catch (Exception exc){
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
                System.out.println(i + "-" + lista.get(j));
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
            System.out.println("Id da Fatura");
            str = sc.nextLine();
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
        catch (Exception exc){
            System.out.println(exc);
        }
    }

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
                f.alteraNatureza(nat);
            }
            else{
                //Fazer metodo que altera natureza de fatura na classe sistema e metodo que altera natureza na classe fatura
                /** Como fazer para remover uma natureza por causa dos clones */
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

    public Sistema criaSistema(){
        Sistema s = new Sistema();
        try{
            Natureza n1 = new Natureza("Educaçao", 500, 0.45);
            Natureza n2 = new Natureza("Saude", 5000, 0.25);
            Natureza n3 = new Natureza("Restauraçao", 4400, 0.20);
            Natureza n4 = new Natureza("Outro", 0, 0);
            s.adicionaNatureza(n1);
            s.adicionaNatureza(n2);
            s.adicionaNatureza(n3);
            s.adicionaNatureza(n4);

            Set<Integer> si = new HashSet<>(10);
            Set<Natureza> s1 = new HashSet<>(), s2 = new HashSet<>(), s3 = new HashSet<>();
            s1.add(n1); s1.add(n2);
            s2.add(n2); s2.add(n3);
            s3.add(n4);

            Empresas e1 = new Empresas(1, "empresae1@gmail.com", "e1Company", "e1Place", "e1Pass", s1, 0.25);
            Empresas e2 = new Empresas(2, "empresae2@gmail.com", "e2Company", "e2Place", "e2Pass", s2, 0.25);
            Empresas e3 = new Empresas(3, "empresae3@gmail.com", "e3Company", "e3Place", "e3Pass", s2, 0.25);
            Empresas e4 = new Empresas(4, "empresae4@gmail.com", "e4Company", "e4Place", "e4Pass", s3, 0.25);
            s.adicionaEmpresas(e1);
            s.adicionaEmpresas(e2);
            s.adicionaEmpresas(e3);
            s.adicionaEmpresas(e4);

            Individuos i1 = new Individuos(5, "individuosi1@gmail.com", "Joao Costa Silva", "l1", "i1Pass", 0, si, 0.25, s1);
            Individuos i2 = new Individuos(6, "individuosi2@gmail.com", "Joao  Manuel Pereira Silva", "l2", "i2Pass", 0, si, 0.35, s1);
            Individuos i3 = new Individuos(7, "individuosi3@gmail.com", "Ana Silva", "l3", "i3Pass", 0, si, 0.45, s1);
            Individuos i4 = new Individuos(8, "individuosi4@gmail.com", "Lucas Peixoto Silva", "l4", "i4Pass", 0, si, 0.95, s1);
            s.adicionaIndividuo(i1);
            s.adicionaIndividuo(i2);
            s.adicionaIndividuo(i3);
            s.adicionaIndividuo(i4);

            Fatura f1 = new Fatura("xyz", e4.getNIF(), i4.getNIF(), e4.getNome(),"Doces", LocalDate.of(2012, 12, 12), s3, 10,
                    s3.stream().collect(Collectors.toList()));
            Fatura f2 = new Fatura("abc", e2.getNIF(), i3.getNIF(), e2.getNome(),"Comer", LocalDate.of(2013, 12, 12), s2, 150,
                    s2.stream().collect(Collectors.toList()));
            Fatura f3 = new Fatura("asdha", e4.getNIF(), i1.getNIF(), e4.getNome(),"Doces", LocalDate.of(2014, 12, 12), s3, 1,
                    s3.stream().collect(Collectors.toList()));
            Fatura f4 = new Fatura("hah", e3.getNIF(), i1.getNIF(), e3.getNome(),"Gripe", LocalDate.of(2012, 1, 12), s2, 25,
                    s2.stream().collect(Collectors.toList()));
            Fatura f5 = new Fatura("jsjsh", e4.getNIF(), i1.getNIF(), e4.getNome(),"Doces", LocalDate.of(2018, 1, 12), s3, 8,
                    s3.stream().collect(Collectors.toList()));
            s.adicionaFatura(f1);
            s.adicionaFatura(f2);
            s.adicionaFatura(f3);
            s.adicionaFatura(f4);
            s.adicionaFatura(f5);

            s.addAgregado(i4.getNIF(), i3.getNIF());
            
            
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
        return s;
    }
}