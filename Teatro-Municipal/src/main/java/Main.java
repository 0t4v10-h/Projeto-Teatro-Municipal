import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Teatro teatro = new Teatro();

        Assento assentoEscolhido = new Assento();
        Cliente cliente = new Cliente();
        Ingresso ingresso = new Ingresso();
        Usuario usuario = new Usuario();
        eventosDisponiveis(teatro);

        System.out.println("\n--- Bem-vindo ao Teatro Municipal de Juiz de Fora! ---\n");

        while(true){

            System.out.println("## 1) Entrar");
            System.out.println("## 2) Cadastrar");
            System.out.println("## 3) Sair");
            int login = ler.nextInt();
            ler.nextLine();

            if(login == 1){
                System.out.println("### Informe seu nome de usuario: ");
                usuario.setNomeUsuario(ler.nextLine());

                System.out.println("### Informe sua senha: ");
                usuario.setSenha(ler.nextInt());
                ler.nextLine();

                if(usuario.getSenha() >= 1111 && usuario.getSenha() <= 9999){
                    int menu = menuCliente();

                    if(menu == 1) {
                        System.out.println("Que bom ter você de volta! ");

                        if (iteracaoCliente(teatro, ler, cliente)) break;
                    }else if (menu == 0) {
                        System.out.println("Ate Mais!!");
                        break;
                    }

                }else if(usuario.getSenha() == 0000){
                    int menuFunc = menuFuncionario();

                    if(menuFunc == 1){
                        System.out.println("Informações do cliente: ");

                        cadastrarCliente(ler);
                        if (iteracaoFuncionario(teatro, ler)) break;

                    }else if(menuFunc == 2){
                        teatro.cadastrarEvento(ler);
                        System.out.println("Evento cadastrado com sucesso!");

                        agendaEventos(teatro);
                        menuFuncionario();


                    }else if(menuFunc == 3){
                        System.out.println(teatro.relatorio());
                    }
                }else{
                    System.out.println("Senha Invalida!");
                }
            }else if(login == 2){
                System.out.println("\n### Cadastro: ");
                cadastrarCliente(ler);

                int menu = menuCliente();

                if (iteracaoCliente(teatro, ler, cliente)) break;
            }else if(login == 3){
                System.out.println("Obrigado pela visita, volte sempre!!!");
                break;
            }
        }
    }

    public static void eventosDisponiveis(Teatro teatro) {

        Evento e1 = new Evento(1, "Espetaculo Rei Leão", "05/06/2024", "18:00", "O espetáculo musical é narrado pelo sábio Rafiki que conta a história de Simba, o principe dos leões e herdeiro do trono.", 50, 100);
        teatro.addEvento(e1);

        Evento e2 = new Evento(2, "Frozen: O musical", "06/06/2024", "20:00", "Um musical baseado no filme da Disney, Frozen.", 50, 100);
        teatro.addEvento(e2);

        Evento e3 = new Evento(3, "Hermanoteu na terra de Godah", "07/06/2024", "19:00", "A peça conta a historia de Hermanoteu, um urbano típico, obediente e bom pastor do tempo do Antigo Testamento da Bíblia.", 50, 100);
        teatro.addEvento(e3);

        Evento e4 = new Evento(4, "Romeu e Julieta", "08/06/2024", "18:30", "Romeu e Julieta é uma tragédia sobre dois adolescentes cuja morte acaba unindo suas famílias, outrora em pé de guerra.", 50, 100);
        teatro.addEvento(e4);
    }


    private static boolean iteracaoFuncionario(Teatro teatro, Scanner ler) {
        agendaEventos(teatro);

        System.out.println("Informe o número do evento escolhido pelo cliente!");
        int numeroEventoEscolhido = ler.nextInt();
        ler.nextLine();

        Evento evento = escolherEvento(teatro, numeroEventoEscolhido);

        escolherAssento(evento, ler);

        int pagamento = pagarIngresso(evento, ler);

        if(comprarNovoIngresso(ler)){
            return iteracaoFuncionario(teatro, ler);
        }else {
            return false;
        }
    }

    private static boolean iteracaoCliente(Teatro teatro, Scanner ler, Cliente cliente) {
        Assento assentoEscolhido;
        agendaEventos(teatro);

        Evento evento = eventoEscolhido(ler, teatro);

        if (evento.totalAssentosDisponiveis() > 0) {
            System.out.println("Comprar ingresso: (S)im / (N)ão");
            String le = ler.nextLine();

            if(le.equalsIgnoreCase("s")){
                assentoEscolhido = escolherAssento(evento, ler);

                int pagamento = pagarIngresso(evento,ler);

                evento.comprarIngresso(assentoEscolhido, teatro, evento, cliente);

                if(comprarNovoIngresso(ler)){
                    iteracaoCliente(teatro, ler, cliente);
                }else{
                    return false;
                }
            }else{
                System.out.println("### Até mais!!!");
                return true;
            }
        }else{
            System.out.println("Não há mais assentos disponiveis para esse evento!");
            System.out.println("Deseja escolher outro evento: (S)im / (N)ão");
            String le = ler.nextLine();

            if(le.equalsIgnoreCase("s")){
                evento = eventoEscolhido(ler, teatro);
                assentoEscolhido = escolherAssento(evento, ler);
                evento.comprarIngresso(assentoEscolhido, teatro, evento, cliente);
            }
            return true;
        }
        return false;
    }

    //Evento
    private static void agendaEventos(Teatro teatro) {
        System.out.println("Agenda de eventos: ");
        System.out.println("-------------------------------------------------");
        for (Evento evento : teatro.getEvento()) {
            System.out.println("Evento: " + evento.getNumero());
            System.out.println("Peça: " + evento.getNome());
            System.out.println("Data: " + evento.getData() + " - Horario: " + evento.getHorario());
            System.out.println("Descrição: " + evento.getDescricao());
            System.out.println("Assentos disponiveis: " + evento.totalAssentosDisponiveis());
            System.out.println();
        }
    }

    private static Evento escolherEvento(Teatro teatro, int numeroEventoEscolhido) {
        Evento eventoEscolhido = null;
        for (Evento evento : teatro.getEvento()){
            if (evento.getNumero() == numeroEventoEscolhido){
                eventoEscolhido = evento;
                break;
            }
        }
        return eventoEscolhido;
    }

    private static Evento eventoEscolhido(Scanner ler, Teatro teatro) {
        System.out.println("Escolha o número do evento desejado ou digite 0 para sair:");
        int numeroEventoEscolhido = ler.nextInt();
        ler.nextLine();

        Evento evento = escolherEvento(teatro, numeroEventoEscolhido);

        if (evento != null) {
            System.out.println("Você escolheu o evento: " + evento.getNome());
        }
        return evento;
    }



    //Assento
    private static Assento escolherAssento(Evento eventoEscolhido, Scanner ler) {
        Assento assentoEscolhido;
        System.out.println("Assentos disponiveis: ");
        for (Assento assento : eventoEscolhido.getAssentosDisponiveis()) {
            System.out.print(" " + assento.getNumeroAssento());
        }
        System.out.println("\nEscolha o número do assento desejado: ");
        int numeroAssentoEscolhido = ler.nextInt();
        ler.nextLine();

        return Evento.verificaAssentoDisponivel(eventoEscolhido, numeroAssentoEscolhido);
    }


    //Ingresso


    private static boolean comprarNovoIngresso(Scanner ler) {
        System.out.println("### Deseja comprar outro ingresso: (S)im / (N)ão");
        String novoIngresso = ler.nextLine();

        if(novoIngresso.equalsIgnoreCase("s")){
            return true;
        }else{
            return false;
        }
    }

    private static int pagarIngresso(Evento evento,Scanner ler) {
        System.out.println("Forma de Pagamento: ");
        System.out.println("## 1) Dinheiro");
        System.out.println("## 2) Pix");
        System.out.println("## 3) Cartão");
        int pagamento = ler.nextInt();
        ler.nextLine();

        if(pagamento == 1 || pagamento == 2){
            System.out.println("Pagamento realizado!");

        }else if (pagamento == 3){
            System.out.println("Possibilidade de pagamento: até 3x sem juros");
            System.out.println("Deseja parcelar de quantas vezes?");
            int parcelas = ler.nextInt();
            ler.nextLine();
            System.out.println("R$" + evento.getPrecoIngresso() / parcelas + " por mês");
        }
        return pagamento;
    }

    public static boolean precoIngressoComDesconto(Evento evento, Cliente cliente){
        String dataNascimentoCliente = cliente.getDataNasc();
        String dataEvento = evento.getData();

        String[] parteDataNascimento = dataNascimentoCliente.split("/");
        int diaNascimento = Integer.parseInt(parteDataNascimento[0]);
        int mesNascimento = Integer.parseInt(parteDataNascimento[1]);

        String[] parteDataEvento = dataEvento.split("/");
        int diaEvento = Integer.parseInt(parteDataEvento[0]);
        int mesEvento = Integer.parseInt(parteDataEvento[1]);

        return diaNascimento == diaEvento && mesNascimento == mesEvento;


           /* if(dataNascimentoCliente.equals(dataEvento)){

                return true;
            } else{
                return false;
        }*/
    }


    //Cliente
    public static void cadastrarCliente(/*Teatro teatro,*/ Scanner ler){
        Cliente cliente = new Cliente();

        System.out.println("### Nome: ");
        cliente.setNome(ler.nextLine());

        System.out.println("### Email: ");
        cliente.setEmail(ler.nextLine());

        System.out.println("### Data de nascimento: ");
        cliente.setDataNasc(ler.nextLine());

        System.out.println("### Senha (4 números diferentes de 0): ");
        cliente.setSenha(ler.nextInt());
        ler.nextLine();

       // teatro.addCliente(cliente);
    }

    //Menu
    public static int menuCliente() {
        Scanner ler = new Scanner(System.in);

        System.out.println("### MENU PRINCIPAL");
        System.out.println("## 1) Comprar ingresso");
        System.out.println("## 0) Sair");

        return ler.nextInt();
    }

    public static int menuFuncionario() {
        Scanner ler = new Scanner(System.in);

        System.out.println("### MENU PRINCIPAL (FUNCIONARIO)");
        System.out.println("## 1) Atender cliente");
        System.out.println("## 2) Cadastrar evento");
        System.out.println("## 3) Gerar relatorio");
        System.out.println("## 0) Sair");

        return ler.nextInt();
    }
}