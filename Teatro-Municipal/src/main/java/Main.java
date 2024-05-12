import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Teatro teatro = new Teatro();
        Assento assentoEscolhido = new Assento();
        Cliente cliente = new Cliente();
        Ingresso ingresso = new Ingresso();


        System.out.println("--- Bem-vindo ao Teatro! ---");

           while (true){
                int menu = menuCliente();

                if(menu == 1) {
                    eventosDisponiveis(teatro);

                    System.out.println("Agenda de eventos: ");
                    System.out.println("-------------------------------------------------");
                    for (Evento evento : teatro.getEvento()) {
                        System.out.println("Evento: " + evento.getNumero());
                        System.out.println("Peça: " + evento.getNome());
                        System.out.println("Data: " + evento.getData() + " - Horario: " + evento.getHorario());
                        System.out.println("Descrição: " + evento.getDescricao());
                        System.out.println("Assentos disponiveis: " + evento.assentosDisponiveis());
                        System.out.println();
                    }

                    System.out.println("Escolha o número do evento desejado ou digite 0 para sair:");
                    int numeroEventoEscolhido = ler.nextInt();
                    ler.nextLine();

                    Evento eventoEscolhido = escolherEvento(teatro, numeroEventoEscolhido);

                    if (eventoEscolhido != null) {
                        System.out.println("Você escolheu o evento: " + eventoEscolhido.getNome());
                    }
                    if (eventoEscolhido.assentosDisponiveis() > 0) {
                        System.out.println("Comprar ingresso: (S)im / (N)ão");
                        String le = ler.nextLine();

                        if (le.equalsIgnoreCase("S")) {
                            System.out.println("Precisamos que você se cadastre!!!");
                            cadastrarCliente(ler);
                        }
                    }

                    System.out.println("Assentos disponiveis: ");
                    for (Assento assento : eventoEscolhido.getAssentosDisponiveis()) {
                        System.out.print(" " + assento.getNumeroAssento());
                    }
                    System.out.println("\nEscolha o número do assento desejado: ");
                    int numeroAssentoEscolhido = ler.nextInt();
                    ler.nextLine();

                    assentoEscolhido = verificaAssentoDisponivel(eventoEscolhido, numeroAssentoEscolhido);

                    if (assentoEscolhido != null) {

                        if (teatro.comprarIngresso(eventoEscolhido, cliente, assentoEscolhido)) {
                            System.out.println("Ingresso comprado com sucesso!");
                        }
                    } else {
                        System.out.println("Assento inválido. Tente novamente.");
                    }

                    if (precoIngresso(eventoEscolhido, cliente)) {
                        System.out.println("Feliz Aniversario!!! \nFicamos feliz por você ter escolhido comemorar seu aniversario com a gente.");
                        System.out.println("Você ganhou um desconto de 50%.");
                    }

                    if (ingresso.getAssento() != null) {
                        System.out.println("-------------------------------------------------");
                        System.out.println("Ingresso comprado para o evento: " + eventoEscolhido.getNome());
                        System.out.println("Poltrona: " + ingresso.getAssento().getNumeroAssento());
                        System.out.println("Data: " + eventoEscolhido.getData() + " - Horario: " + eventoEscolhido.getHorario());

                        if (precoIngresso(eventoEscolhido, cliente)) {
                            System.out.println("Preço: " + ingresso.getPreco() * 0.50);
                        } else {
                            System.out.println("Preço: " + ingresso.getPreco());
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

        public static int menuVendedor() {
            Scanner ler = new Scanner(System.in);

            System.out.println("### MENU PRINCIPAL");
            System.out.println("## 1) Cadastrar cliente");
            System.out.println("## 2) Cadastrar evento");
            System.out.println("## 3) Gerar Relatorio");
            System.out.println("## 0) Sair");

            return ler.nextInt();
        }

        public static void eventosDisponiveis(Teatro teatro) {

            Evento e1 = new Evento(1, "Espetaculo Rei Leão", "05/06/2024", "18:00", "O espetáculo musical é narrado pelo sábio Rafiki que conta a história de Simba, o principe dos leões e herdeiro do trono.", 50);
            teatro.addEvento(e1);

            Evento e2 = new Evento(2, "Frozen: O musical", "06/06/2024", "20:00", "Um musical baseado no filme da Disney, Frozen.", 50);
            teatro.addEvento(e2);

            Evento e3 = new Evento(3, "Hermanoteu na terra de Godah", "07/06/2024", "19:00", "A peça conta a historia de Hermanoteu, um urbano típico, obediente e bom pastor do tempo do Antigo Testamento da Bíblia.", 50);
            teatro.addEvento(e3);

            Evento e4 = new Evento(4, "Romeu e Julieta", "08/06/2024", "18:30", "Romeu e Julieta é uma tragédia sobre dois adolescentes cuja morte acaba unindo suas famílias, outrora em pé de guerra.", 50);
            teatro.addEvento(e4);
        }

        public static void cadastrarCliente(Scanner ler){
            Cliente cliente = new Cliente();

            System.out.println("Nome: ");
            cliente.setNome(ler.nextLine());

            System.out.println("Email: ");
            cliente.setEmail(ler.nextLine());

            System.out.println("Data de nascimento: ");
            cliente.setDataNasc(ler.nextLine());
        }

        public static boolean precoIngresso(Evento evento, Cliente cliente){
            if(evento.getData().equals(cliente.getDataNasc())){
                return true;
            } else{
                return false;
        }
    }
}