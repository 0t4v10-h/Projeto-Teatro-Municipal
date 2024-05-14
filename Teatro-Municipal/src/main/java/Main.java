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


        System.out.println("\n--- Bem-vindo ao Teatro! ---\n");

        while (true){
            System.out.println("###  Ja possui cadastro: (S)im / (N)ão");
            String cadastro = ler.nextLine();

            if(cadastro.equalsIgnoreCase("s")){
                System.out.println("### Informe seu nome de usuario: ");
                usuario.setNome(ler.nextLine());

                System.out.println("### Informe sua senha: ");
                usuario.setSenha(ler.nextLine());

                if(usuario.getSenha().equals("0000")){
                    int menuFunc = menuFuncionario();

                    if(menuFunc == 1){
                        System.out.println("Informações do cliente: ");
                        cadastrarCliente(ler);
                        agendaEventos(teatro);

                        System.out.println("Informe o número do evento escolhido pelo cliente!");
                        int numeroEventoEscolhido = ler.nextInt();
                        ler.nextLine();

                        Evento evento = escolherEvento(teatro, numeroEventoEscolhido);

                        escolherAssento(evento,ler);

                        int pagamento = pagarIngresso(evento,ler);



                    }else if(menuFunc == 2){
                        cadastrarEvento(ler);
                    }else if(menuFunc == 3){
                        System.out.println(teatro.relatorio());
                    }else if(menuFunc == 0){
                        break;
                    }


                } else if(usuario.getSenha() != "0000"){
                    int menu = menuCliente();

                    if(menu == 1) {
                        System.out.println("Bem-vindo de volta! ");

                        agendaEventos(teatro);

                        Evento evento = eventoEscolhido(ler, teatro);

                        if (evento.totalAssentosDisponiveis() > 0) {
                            System.out.println("Comprar ingresso: (S)im / (N)ão");
                            String le = ler.nextLine();
                        }

                        assentoEscolhido = escolherAssento(evento, ler);

                        int pagamento = pagarIngresso(evento,ler);

                        ingressoComprado(assentoEscolhido, teatro, evento, cliente);
                    }
                }
            } else{
                System.out.println("Precisamos que você se cadastre!!!");
                cadastrarCliente(ler);
            }



            int menu = menuCliente();

            if(menu == 1) {


                agendaEventos(teatro);

                Evento evento = eventoEscolhido(ler, teatro);

                if (evento.totalAssentosDisponiveis() > 0) {
                    System.out.println("Comprar ingresso: (S)im / (N)ão");
                    String le = ler.nextLine();
                }

                assentoEscolhido = escolherAssento(evento, ler);

                ingressoComprado(assentoEscolhido, teatro, evento, cliente);

                if (precoIngresso(evento, cliente)) {
                    System.out.println("Feliz Aniversario!!! \nFicamos feliz por você ter escolhido comemorar seu aniversario com a gente.");
                    System.out.println("Você ganhou um desconto de 50%.");
                }

                if (ingresso.getAssento() != null) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Ingresso comprado para o evento: " + evento.getNome());
                    System.out.println("Poltrona: " + ingresso.getAssento().getNumeroAssento());
                    System.out.println("Data: " + evento.getData() + " - Horario: " + evento.getHorario());

                    if (precoIngresso(evento, cliente)) {
                        System.out.println("Preço: " + evento.getPrecoIngresso() * 0.50);
                    } else {
                        System.out.println("Preço: " + evento.getPrecoIngresso());
                    }
                    } /*else {
                       System.out.println("Não há mais ingressos disponiveis para este evento.");
                    }*/


            } else if (menu == 0) {
                System.out.println("Ate Mais!!");
                break;
            }
        }
    }

    private static void cadastrarEvento(Scanner ler) {
        Evento evento = new Evento();

        System.out.println("### Cadastrar novo evento!\n");
        System.out.println("### Número: ");
        evento.setNumero(ler.nextInt());
        ler.nextLine();

        System.out.println("### Nome: ");
        evento.setNome(ler.nextLine());

        System.out.println("### Data: ");
        evento.setData(ler.nextLine());

        System.out.println("### Horario: ");
        evento.setHorario(ler.nextLine());

        System.out.println("### Descrição: ");
        evento.setDescricao(ler.nextLine());

        System.out.println("### Capacidade: ");
        evento.setCapacidade(ler.nextInt());
        ler.nextInt();

        System.out.println("### Preço do ingresso: ");
        evento.setPrecoIngresso(ler.nextDouble());
        ler.nextDouble();
    }

    private static int pagarIngresso(Evento evento, Scanner ler) {
        System.out.println("Forma de Pagamento: ");
        System.out.println("## 1) Dinheiro");
        System.out.println("## 2) Pix");
        System.out.println("## 3) Cartão");
        int pagamento = ler.nextInt();
        ler.nextLine();
        if(pagamento == 1 || pagamento == 2){
            System.out.println("Pagamento realizado!");
            System.out.println("Ingresso vendido!");
        }
        if (pagamento == 3){
            System.out.println("Opção de parcela: Em até 3x sem juros");
            System.out.println("Em quantas vezes deseja parcelar?");
            int parcelas = ler.nextInt();
            ler.nextLine();
            System.out.println(evento.getPrecoIngresso() / parcelas + "R$ por mês");
            System.out.println("Ingresso Vendido");
        }
        return pagamento;
    }

    private static void ingressoComprado(Assento assentoEscolhido, Teatro teatro, Evento evento, Cliente cliente) {
        if (assentoEscolhido != null) {

            if (teatro.comprarIngresso(evento, cliente, assentoEscolhido)) {
                System.out.println("Ingresso comprado com sucesso!");
            }
        } else {
            System.out.println("Assento inválido. Tente novamente.");
        }
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

    private static Assento escolherAssento(Evento eventoEscolhido, Scanner ler) {
        Assento assentoEscolhido;
        System.out.println("Assentos disponiveis: ");
        for (Assento assento : eventoEscolhido.getAssentosDisponiveis()) {
            System.out.print(" " + assento.getNumeroAssento());
        }
        System.out.println("\nEscolha o número do assento desejado: ");
        int numeroAssentoEscolhido = ler.nextInt();
        ler.nextLine();

        return verificaAssentoDisponivel(eventoEscolhido, numeroAssentoEscolhido);
    }

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

    private static Assento verificaAssentoDisponivel(Evento eventoEscolhido, int numeroAssentoEscolhido) {
        Assento assentoEscolhido;
        assentoEscolhido = null;
        for (Assento assento : eventoEscolhido.getAssentosDisponiveis()) {
            if (assento.getNumeroAssento() == numeroAssentoEscolhido) {
                assentoEscolhido = assento;
                break;
            }
        }
        return assentoEscolhido;
    }

    public static int menuCliente() {
            Scanner ler = new Scanner(System.in);

                System.out.println("### MENU PRINCIPAL");
                System.out.println("## 1) Comprar ingresso");
                System.out.println("## 0) Sair");

                return ler.nextInt();
        }

        public static int menuFuncionario() {
            Scanner ler = new Scanner(System.in);

            System.out.println("### MENU PRINCIPAL");
            System.out.println("## 1) Atender cliente");
            System.out.println("## 2) Cadastrar evento");
            System.out.println("## 3) Gerar relatorio");
            System.out.println("## 0) Sair");

            return ler.nextInt();
        }

        public static void eventosDisponiveis(Teatro teatro) {

            Evento e1 = new Evento(1, "Espetaculo Rei Leão", "05/06/2024", "18:00", "O espetáculo musical é narrado pelo sábio Rafiki que conta a história de Simba, o principe dos leões e herdeiro do trono.", 50, 100.0);
            teatro.addEvento(e1);

            Evento e2 = new Evento(2, "Frozen: O musical", "06/06/2024", "20:00", "Um musical baseado no filme da Disney, Frozen.", 50, 100);
            teatro.addEvento(e2);

            Evento e3 = new Evento(3, "Hermanoteu na terra de Godah", "07/06/2024", "19:00", "A peça conta a historia de Hermanoteu, um urbano típico, obediente e bom pastor do tempo do Antigo Testamento da Bíblia.", 50, 100);
            teatro.addEvento(e3);

            Evento e4 = new Evento(4, "Romeu e Julieta", "08/06/2024", "18:30", "Romeu e Julieta é uma tragédia sobre dois adolescentes cuja morte acaba unindo suas famílias, outrora em pé de guerra.", 50, 100);
            teatro.addEvento(e4);
        }

        public static void cadastrarCliente(Scanner ler){
            Cliente cliente = new Cliente();

            System.out.println("### Nome: ");
            cliente.setNome(ler.nextLine());

            System.out.println("### Email: ");
            cliente.setEmail(ler.nextLine());

            System.out.println("### Data de nascimento: ");
            cliente.setDataNasc(ler.nextLine());

            System.out.println("### Senha: ");
            cliente.setSenha(ler.nextLine());
        }

        public static boolean precoIngresso(Evento evento, Cliente cliente){
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
    }
