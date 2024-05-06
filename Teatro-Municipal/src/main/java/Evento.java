import java.time.LocalDate;
import java.time.LocalTime;

public class Evento
{
    private int numero;
    private String nome;
    private String data;
    private String horario;
    private String descricao;     
    
    public Evento(){}   
    public Evento(int numero, String nome, String data, String horario, String descricao){
        this.numero = numero;
        this.nome = nome;
        this.data = data;  
        this.horario = horario;
        this.descricao = descricao;
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
}
