import java.util.ArrayList;

public class Teatro
{
    private String nome;
    private ArrayList<Evento> eventos;
    
    public Teatro(){
        eventos = new ArrayList<>();
    }
    public Teatro(String nome){
        this.nome = nome;
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
    }

    public boolean comprarIngresso(Evento evento, Cliente cliente, Assento assento){
        return evento.verificaIngressoVendido(cliente, assento);
    }

    public String relatorio(){
        Evento evento = new Evento();
        String rel;
        rel = "Relatorio de vendas: \n";
        int ingressosVendidos = 0;
        for (Evento event : eventos){
            ingressosVendidos = event.totalAssentosDisponiveis();
            int assentosOcupados = 0;

            for (Assento assento : event.getAssentosDisponiveis()){
                assentosOcupados += event.totalAssentosDisponiveis();
            }
            int aux = ingressosVendidos - event.totalAssentosDisponiveis();
            rel += "\nEvento: " + event.getNome() + "\n";
            rel +="Ingresso Vendidos: " + aux + "\n";
            rel += "Assentos Ocupados: " + aux + "\n";
        }
        rel += "R$ " + evento.getPrecoIngresso() * ingressosVendidos + " arrecadados!";

        return rel;

    }
}
