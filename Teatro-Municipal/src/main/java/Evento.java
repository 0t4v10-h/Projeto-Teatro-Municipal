import java.util.ArrayList;

public class Evento
{
    private int numero;
    private String nome;
    private String data;
    private String horario;
    private String descricao;
    private int capacidade;
    private ArrayList<Assento> assentosDisponiveis;
    
    public Evento(int numero, String nome, String data, String horario, String descricao, int capacidade){
        this.numero = numero;
        this.nome = nome;
        this.data = data;  
        this.horario = horario;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.assentosDisponiveis = new ArrayList<>();
        for(int i = 1; i <= capacidade; i++){
            this.assentosDisponiveis.add(new Assento(i));
        }
    }     

    public int getNumero(){
        return this.numero;
    }
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getData(){
        return this.data;
    }
    public void setData(String data){
        this.data = data;
    }
    
    public String getHorario(){
        return this.horario;
    }
    public void setHorario(String horario){
        this.horario = horario;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public int getCapacidade(){
        return this.capacidade;
    }
    public void setCapacidade(int capacidade){
        this.capacidade = capacidade;
    }

    public boolean venderIngresso(Cliente cliente, Assento assento){
        if(assentosDisponiveis.contains(assento)){
            assentosDisponiveis.remove(assento);
            return true;
        } else{
            return false;
        }
    }

    public int assentosDisponiveis(){
        return assentosDisponiveis.size();
    }
}
