import java.util.ArrayList;
import java.util.Scanner;

public class Teatro
{
    private String nome;
    private ArrayList<Evento> eventos;
    private ArrayList<Usuario> usuarios;
    private int totalEventosCadastrados;

    public Teatro(){
        eventos = new ArrayList<>();
        usuarios = new ArrayList<>();
    }
    public Teatro(String nome, int totalEventosCadastrados){
        this.nome = nome;
        this.totalEventosCadastrados = totalEventosCadastrados;
        eventos = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public String getNome(){
        return this.nome;
    }

    public ArrayList<Evento> getEvento(){
        return this.eventos;
    }

    public void addEvento(Evento eventos){
        this.eventos.add(eventos);
        setTotalEventosCadastrados(+1);
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void addUsuarios(Usuario usuarios) {
        this.usuarios.add(usuarios);
    }

    public Usuario verificaUsuarios(String nomeUsuario){

        for(Usuario c : usuarios){
            if(c.getNomeUsuario().equals(nomeUsuario)){
                return c;
            }
        }
        return null;
    }

    public void cadastrarEvento(Scanner ler) {
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
        ler.nextLine();

        System.out.println("### Preço do ingresso: ");
        evento.setPrecoIngresso(ler.nextDouble());
        ler.nextLine();

        addEvento(evento);
    }


    public String relatorio(){
        int totalIngressosVendidos = 0;
        int totalAssentosOcupados = 0;
        double totalValorObtido = 0.0;

        String rel = "\nRelatorio de vendas: \n";
        for (int i = 0; i < eventos.size(); i++){
            Evento event = eventos.get(i);

            int ingressosVendidos = event.getCapacidade() - event.totalAssentosDisponiveis();
            totalIngressosVendidos += ingressosVendidos;

            int assentosOcupados = event.getCapacidade() - event.totalAssentosDisponiveis();
            totalAssentosOcupados += assentosOcupados;

            double valorObtido = ingressosVendidos * event.getPrecoIngresso();
            totalValorObtido += valorObtido;

            rel += "\nEvento: " + event.getNome() + "\n";
            rel +="Ingresso Vendidos: " +ingressosVendidos+ "\n";
            rel += "Assentos Ocupados: " +assentosOcupados+ "\n";
            rel += "Valor Obtido: " +valorObtido+ "\n";
        }

        rel += "\nTotal de Eventos: " +getEvento().size();
        rel += "\nTotal de Ingressos Vendidos: " +totalIngressosVendidos;
        rel += "\nTotal de Assentos Ocupados: " +totalAssentosOcupados;
        rel += "\nTotal do Valor Obtido: " +totalValorObtido;

        return rel;
    }

    public int getTotalEventosCadastrados() {
        return totalEventosCadastrados;
    }

    public void setTotalEventosCadastrados(int i) {
        this.totalEventosCadastrados = totalEventosCadastrados;
    }
}
