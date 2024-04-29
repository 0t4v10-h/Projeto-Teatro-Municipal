import java.util.ArrayList;

public class Teatro
{
    private String nome;
    private int capacidade;
    private ArrayList<Evento> eventos;
    
    public Teatro(){
        eventos = new ArrayList<>();
    }
    public Teatro(String nome, int capacidade){
        this.nome = nome;
        this.capacidade = capacidade;
        eventos = new ArrayList<>();
    }
    
    public String getNome(){
        return this.nome = nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public int getCapacidade(){
        return this.capacidade = capacidade;
    }
    public void setCapacidade(int capacidade){
        this.capacidade = capacidade;
    }
    
    public ArrayList<Evento> getEvento(){
        return this.eventos;
    }
    public void addEvento(Evento eventos){
        this.eventos.add(eventos);
    }
}
