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
}
