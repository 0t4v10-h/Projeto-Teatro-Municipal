import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Teatro teatro = new Teatro();

        eventosDisponiveis(teatro);
        clientesCadastrados(teatro);

        System.out.println("\n--- Bem-vindo ao Teatro Municipal de Juiz de Fora! ---\n");

        while(true){

            System.out.println("## 1) Entrar");
            System.out.println("## 2) Cadastrar");
            System.out.println("## 3) Sair");
            int login = ler.nextInt();
            ler.nextLine();

            if(login == 1){
                Usuario usuario = loginUsuario(ler, teatro);

                if(usuario.getSenha() >= 1111 && usuario.getSenha() <= 9999){
                    int menu = menuCliente();

                    if(menu == 1) {
                        System.out.println("\nQue bom ter você de volta! ");

                        if (iteracaoCliente(teatro,usuario, ler)) break;
                    }else if (menu == 0) {
                        System.out.println("Ate Mais!!");
                        break;
                    }

                }else if(usuario.getSenha() == 0000){
                    int menuFunc = menuFuncionario();

                    if(menuFunc == 1){
                        System.out.println("Informações do cliente: ");

                        cadastrarCliente(teatro, ler);
                        if (iteracaoFuncionario(teatro,usuario, ler)) break;

                    }else if(menuFunc == 2){
                        teatro.cadastrarEvento(ler);
                        System.out.println("Evento cadastrado com sucesso!");

                        agendaEventos(teatro);
                        menuFuncionario();


                    }else if(menuFunc == 3){
                        System.out.println(teatro.relatorio());
                        break;
                    }
                }else{
                    System.out.println("Senha Invalida!");
                }
            }else if(login == 2){
                cadastrarCliente(teatro, ler);

                menuCliente();
                Usuario usuario = loginUsuario(ler, teatro);

                if (iteracaoCliente(teatro,usuario, ler)) break;
            }else if(login == 3){
                System.out.println("Obrigado pela visita, volte sempre!!!");
                break;
            }
        }
    }

    private static Usuario loginUsuario(Scanner ler, Teatro teatro) {
        Usuario usuario = null;
        boolean loginCorreto = false;

        while(!loginCorreto) {
            System.out.println("### Informe seu nome de usuario: ");
            String nomeUsuario = ler.nextLine();
            usuario = teatro.verificaUsuarios(nomeUsuario);

            if(usuario != null){
                System.out.println("### Informe sua senha: ");
                int senha = ler.nextInt();
                ler.nextLine();

                if(usuario.getSenha() == senha){
                    System.out.println("### Login realizado com sucesso!\n");
                    loginCorreto = true;
                }else{
                    System.out.println("### Senha incorreta! Tente novamente.");
                }
            }else{
                System.out.println("### Nome de usuario não encontrado!");
            }
        }
        return usuario;
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


    private static boolean iteracaoFuncionario(Teatro teatro,Usuario usuario, Scanner ler) {
        agendaEventos(teatro);

        System.out.println("Informe o número do evento escolhido pelo cliente!");
        int numeroEventoEscolhido = ler.nextInt();
        ler.nextLine();

        Evento evento = escolherEvento(teatro, numeroEventoEscolhido);

        escolherAssento(evento, ler);

        pagarIngresso(evento, usuario, ler);

        if(comprarNovoIngresso(ler)){
            return iteracaoFuncionario(teatro,usuario, ler);
        }else {
            return false;
        }
    }

    private static boolean iteracaoCliente(Teatro teatro,Usuario usuario, Scanner ler) {
        Assento assentoEscolhido;
        agendaEventos(teatro);

        Evento evento = eventoEscolhido(ler, teatro);

        if (evento.totalAssentosDisponiveis() > 0) {
            System.out.println("Comprar ingresso: (S)im / (N)ão");
            String le = ler.nextLine();

            if(le.equalsIgnoreCase("s")){
                assentoEscolhido = escolherAssento(evento, ler);

                pagarIngresso(evento, usuario, ler);

                if(comprarNovoIngresso(ler)){
                    iteracaoCliente(teatro,usuario, ler);
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
            }
            return true;
        }
        return false;
    }

    //Evento
    private static void agendaEventos(Teatro teatro) {
        System.out.println("\nAgenda de eventos: ");
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
        System.out.println("Escolha o número do evento desejado ou digite 0 para voltar:");
        int numeroEventoEscolhido = ler.nextInt();
        ler.nextLine();

        if (numeroEventoEscolhido == 0){
            menuCliente();
        }

        Evento evento = escolherEvento(teatro, numeroEventoEscolhido);

        if (evento != null) {
            System.out.println("Você escolheu o evento: " + evento.getNome());
        }
        return evento;
    }

    //Assento

    private static void imprimirAssentos(Evento eventoEscolhido){
        int colunas = 10;
        ArrayList<Assento> assentoDisponivel = eventoEscolhido.getAssentosDisponiveis();

        System.out.println("Assentos disponiveis: ");
        for (int i = 0; i < assentoDisponivel.size(); i++) {
            Assento assento = assentoDisponivel.get(i);
            if (assento.isOcupado()){
                System.out.print("x\t");
            }else{
                System.out.print(assento.getNumeroAssento()+ "\t");
            }

            if((i+1) % colunas == 0){
                System.out.println();
            }
        }

        if(assentoDisponivel.size() % colunas != 0){
            System.out.println();
        }
    }

    private static Assento escolherAssento(Evento eventoEscolhido, Scanner ler) {
        imprimirAssentos(eventoEscolhido);

        while(true) {
            System.out.println("\nEscolha o número do assento desejado: ");
            String numeroAssentoEscolhido = ler.nextLine();

            try {
                System.out.println("Número de assento escolhido: " + numeroAssentoEscolhido);

                int numeroAssento = Integer.parseInt(numeroAssentoEscolhido);
                System.out.println("Número de assento escolhido: " + numeroAssento);
                System.out.println("Número de assentos disponíveis: " + eventoEscolhido.getAssentosDisponiveis().size());


                if (numeroAssento >= 1 && numeroAssento <= eventoEscolhido.getAssentosDisponiveis().size()) {
                    Assento assentoEscolhido = eventoEscolhido.verificaIngressoVendido(numeroAssento);

                    if (assentoEscolhido != null && !assentoEscolhido.isOcupado()) {

                        for (Assento assento : eventoEscolhido.getAssentosDisponiveis()) {
                            if (assento.getNumeroAssento() == numeroAssento) {
                                assento.setOcupado(true);
                                break;
                            }
                        }
                        return assentoEscolhido;
                    }else {
                        System.out.println("Assento ja ocupado. Escolha outro.");
                        imprimirAssentos(eventoEscolhido);
                    }
                }
            }catch (NumberFormatException e) {
                System.out.println("Número de assento inválido. Por favor, insira um número válido.");
            }
        }
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

    private static int pagarIngresso(Evento evento, Usuario usuario,Scanner ler) {
        System.out.println("\nForma de Pagamento: ");
        System.out.println("## 1) Dinheiro");
        System.out.println("## 2) Pix");
        System.out.println("## 3) Cartão");
        int pagamento = ler.nextInt();
        ler.nextLine();

        double precoComDesconto = 0.0;

        if(verificaDataEventoEAniversario(evento, usuario)){
            evento.setPrecoIngresso(evento.getPrecoIngresso() * 0.50);
            System.out.println("\nVerificamos que no dia do evento é seu aniversário!!! \nFicamos feliz por você ter escolhido comemorar seu aniversario com a gente.");
            System.out.println("Você ganhou um desconto de 50%.");

            precoComDesconto = evento.getPrecoIngresso() * 0.50;
            System.out.println(String.format("Preço do ingresso com desconto: R$ %.2f", evento.getPrecoIngresso() - precoComDesconto));
        }

        if(pagamento == 1 || pagamento == 2){
            System.out.println("\nPagamento realizado!");

        }else if (pagamento == 3){
            System.out.println("\nPossibilidade de pagamento: até 3x sem juros");
            int parcelas = 0;
            while(parcelas < 1 || parcelas > 3){
                System.out.println("Deseja parcelar de quantas vezes?");
                parcelas = ler.nextInt();
                ler.nextLine();
                if(parcelas < 1 || parcelas > 3){
                    System.out.println("Número de parcelas invalido. Escolha entre 1 e 3!");
                }
            }

            System.out.println(String.format("R$ %.2f por mês.", (evento.getPrecoIngresso() - precoComDesconto) / parcelas));
        }
        return pagamento;
    }

    public static boolean verificaDataEventoEAniversario(Evento evento, Usuario usuario){
        String dataNascimentoCliente = usuario.getDataNasc();
        String dataEvento = evento.getData();

        String[] parteDataNascimento = dataNascimentoCliente.split("/");
        int diaNascimento = Integer.parseInt(parteDataNascimento[0]);
        int mesNascimento = Integer.parseInt(parteDataNascimento[1]);

        String[] parteDataEvento = dataEvento.split("/");
        int diaEvento = Integer.parseInt(parteDataEvento[0]);
        int mesEvento = Integer.parseInt(parteDataEvento[1]);

        return diaNascimento == diaEvento && mesNascimento == mesEvento;
    }


    //Cliente
    public static void cadastrarCliente(Teatro teatro, Scanner ler){
        System.out.println("### Precisamos de algumas informações:\n");

        System.out.println("### Nome: ");
        String nome = ler.nextLine();

        System.out.println("### Email: ");
        String email = ler.nextLine();

        System.out.println("### Data de nascimento: ");
        String dataNasc = ler.nextLine();

        String nomeUsuario;
        boolean nomeUsuarioDisponivel = false;
        do {
            System.out.println("### Nome de usuario:");
            nomeUsuario = ler.nextLine();

            if (teatro.verificaUsuarios(nomeUsuario) != null) {
                System.out.println("Nome de usuario ja está em uso. Escolha outro!");
            }else{
                nomeUsuarioDisponivel = true;
            }
        }while (!nomeUsuarioDisponivel);

        System.out.println("### Senha (4 números diferentes de 0):");
        int senha = ler.nextInt();
        ler.nextLine();

        Usuario cliente = new Usuario(nome, nomeUsuario, senha, dataNasc);
        teatro.addUsuarios(cliente);
        System.out.println("## Cliente cadastrado com sucesso!");

    }

    public static void clientesCadastrados(Teatro teatro){
        Usuario c1 = new Usuario("Otavio", "otavio", 1111, "26/10/1999");
        teatro.addUsuarios(c1);

        Usuario c2 = new Usuario("Samuel", "samuel", 2222, "05/06/2000");
        teatro.addUsuarios(c2);

        Usuario c3 = new Usuario("Daves", "daves", 0000, "01/01/1985");
        teatro.addUsuarios(c3);
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
