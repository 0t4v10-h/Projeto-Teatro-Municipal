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

                if(menu == 1){
                    eventosDisponiveis(teatro);

                    System.out.println("Agenda de eventos: ");
                    System.out.println("-------------------------------------------------");
                    for(Evento evento : teatro.getEvento()) {
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

                    Evento eventoEscolhido = null;
                    for (Evento evento : teatro.getEvento()){
                        if (evento.getNumero() == numeroEventoEscolhido){
                            eventoEscolhido = evento;
                            break;
                        }
                    }
                    if (eventoEscolhido != null){
                        System.out.println("Você escolheu o evento: " + eventoEscolhido.getNome());
                    }
                    if(eventoEscolhido.assentosDisponiveis() > 0){
                        System.out.println("Comprar ingresso: (S)im / (N)ão");
                        String le = ler.nextLine();

                        if (le.equalsIgnoreCase("S")){
                            System.out.println("Precisamos que você se cadastre!!!");
                            cadastrarCliente(ler);
                        }
                    }

                    System.out.println("Assentos disponiveis: ");
                    for(Assento assento : eventoEscolhido.getAssentosDisponiveis()){
                        System.out.print(" " + assento.getNumeroAssento());
                    }
                    System.out.println("\nEscolha o número do assento desejado: ");
                    assentoEscolhido.setNumeroAssento(ler.nextInt());
                    teatro.comprarIngresso(eventoEscolhido,cliente,assentoEscolhido);

                    //Necessario criar uma matriz com o numeros dos assentos disponiveis

                    while (teatro.comprarIngresso(eventoEscolhido,cliente,assentoEscolhido)){
                        System.out.println("Desculpe, a poltrona selecionada não está disponível. Escolha outra.");
                        assentoEscolhido.setNumeroAssento(ler.nextInt());
                    }

                    double preco = ingresso.getPreco();
                    if(precoIngresso(eventoEscolhido, cliente) == true){
                        System.out.println("Feliz Aniversario!!! \nFicamos feliz por você ter escolhido comemorar seu aniversario com a gente.");
                        System.out.println("Você ganhou um desconto de 50%.");
                        preco *= 0.50;
                    }
                    ingresso.setPreco(preco);


                    if (ingresso.getAssento() != null){
                        System.out.println("-------------------------------------------------");
                        System.out.println("Ingresso comprado para o evento: " +eventoEscolhido.getNome());
                        System.out.println("Poltrona: " +ingresso.getAssento().getNumeroAssento());
                        System.out.println("Data: " +eventoEscolhido.getData()+ " - Horario: " +eventoEscolhido.getHorario());

                    /*if(precoIngresso(eventoEscolhido, cliente) == true){
                        System.out.println("Preço: " +ingresso.getPreco() * 0.50);
                    } else{
                        System.out.println("Preço: " +ingresso.getPreco());
                    }
                    }else{
                        System.out.println("Não há mais ingressos disponiveis para este evento.");*/
                            }

                } else if (menu == 0) {
                    System.out.println("Ate Mais!!");
                    break;
                }
            }






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