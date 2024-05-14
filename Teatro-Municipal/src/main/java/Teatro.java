import java.util.ArrayList;

public class Teatro
{
    private String nome;
    private ArrayList<Evento> eventos;
    private int totalEventosCadastrados;
    
    public Teatro(){
        eventos = new ArrayList<>();
    }
    public Teatro(String nome, int totalEventosCadastrados){
        this.nome = nome;
        this.totalEventosCadastrados = totalEventosCadastrados;
        eventos = new ArrayList<>();
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

    public boolean comprarIngresso(Evento evento, Cliente cliente, Assento assento){
        return evento.verificaIngressoVendido(cliente, assento);
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