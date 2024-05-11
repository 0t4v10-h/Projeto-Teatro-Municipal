package vianna.trabalho;

import vianna.trabalho.teatro.Assento;
import vianna.trabalho.teatro.Teatro;
import vianna.trabalho.teatro.Ingresso;
import vianna.trabalho.teatro.Cliente;
import vianna.trabalho.teatro.Evento;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
            Scanner ler = new Scanner(System.in);
            Teatro teatro = new Teatro();
            Assento assentoEscolhido = new Assento();
            Ingresso ingresso = new Ingresso();
            Cliente cliente = new Cliente();


            System.out.println("--- Bem-vindo ao Teatro !---");

            while (true){
                int menu = menuCliente();

                if(menu == 1){
                Evento eventoEscolhido = comprarIngresso(teatro);
                break;
                } else if (menu == 2) {
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

        public static Evento comprarIngresso(Teatro teatro){
            Scanner ler = new Scanner(System.in);
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
            return eventoEscolhido;
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