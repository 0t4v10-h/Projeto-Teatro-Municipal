import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner ler = new scanner(System.in);
        Teatro teatro = new Teatro();
        Assento assentoEscolhido = new Assento();
        Ingresso ingresso = new Ingresso();


        System.out.println("Bem-vindo ao Teatro ---!");

        eventosDisponiveis(teatro);

        System.out.println("Agenda de eventos: ");
        System.out.println("-------------------------------------------------");
        for(Evento evento : teatro.getEvento()){
            System.out.println("Evento: " +evento.getNumero());
            System.out.println("Peça: " +evento.getNome());
            System.out.println("Data: " +evento.getData()+ " - Horario: " +evento.getHorario());
            System.out.println("Descrição: " +evento.getDescricao());
            System.out.println("Assentos disponiveis: " +evento.assentosDisponiveis());
            System.out.println();

            if(evento.assentosDisponiveis.size() > 0){
                System.out.println("Comprar ingresso: (S)im / (N)ão");
                String le = ler.nextLine();

                if(le.equalsIgnoreCase("S")){
                    System.out.println("Precisamos que você se cadastre!!!");
                    cadastrarCliente(ler);

                    System.out.println("Escolha o número do assento desejado: ");
                    assentoEscolhido.setAssento(ler.nextInt());

                    if(teatro.comprarIngresso() = false){
                        System.out.println("Desculpe, a poltrona selecionada não está disponível. Escolha outra.");
                        assentoEscolhido.setAssento(ler.nextInt());
                    }

                    if(precoIngresso(evento, cliente) == true){
                        System.out.println("Feliz Aniversario!!! \nFicamos feliz por você ter escolhido comemorar seu aniversario com a gente.");
                        System.out.println("Você ganhou um desconto de 50%.")
                    }
                    
                    teatro.comprarIngresso(evento, cliente, assentoEscolhido);

                    System.out.println("-------------------------------------------------");
                    System.out.println("Ingresso comprado para o evento: " +evento.getNome());
                    System.out.println("Poltrona: " +ingresso.getAssento().getNumero());
                    System.out.println("Data: " +evento.getData()+ " - Horario: " +evento.getHorario());

                    if(precoIngresso(evento, cliente) == true){
                        System.out.println("Preço: " +ingresso.getPreco() * 0.50);
                    } else{
                        System.out.println("Preço: " +ingresso.getPreco());
                    }
                } else{
                    break;
                }
            } else{
                System.out.println("Não há mais ingressos disponiveis para este evento.");
                break;
            }
        }
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
