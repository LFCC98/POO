import java.util.Scanner;

public class JavaFaturaMenu
{
    private String [] opcoes;
    private int ultima;
    private int fase;
    
    public JavaFaturaMenu(String [] opcoes){
        System.arraycopy(opcoes, 0, this.opcoes, 0, 0);
        this.ultima = -1;
        this.fase = 0;
    }
    
    public void menu(Sistema s){
        Scanner sc = new Scanner(System.in);
        while(ultima != 0){
            System.out.println(this.opcoes);
            ultima = sc.nextInt();
            switch(ultima){
                case 0: System.out.println("A sair");
                break;
                case 1: System.out.println("Insira NIF");
                        int NIF = sc.nextInt();
                        if(s.getSistema().keySet().contains(NIF)){
                            fase = 1;
                        }
                break;
                case 2: if(fase != 1){
                        System.out.println("Primeiro introduza o NIF");
                        }
                        System.out.println("Introduza a palavra-passe");
                        String pass = sc.nextLine();
                break;
                case 3: System.out.println("Imprimir todas as faturas");
            }
        }
    }
}