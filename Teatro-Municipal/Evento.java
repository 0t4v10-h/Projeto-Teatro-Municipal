import java.time.LocalDate;
import java.time.LocalTime;

public class Evento
{
    private String nome;
    private LocalDate data;
    private LocalTime horario;
    private String descricao;     
    
    public Evento(){}   
    public Evento(String nome, LocalDate data, LocalTime horario, String descricao){
        this.nome = nome;
        this.data = data;  
        this.horario = horario;
        this.descricao = descricao;
    }     
    
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public LocalDate getData(){
        return this.data;
    }
    public void setData(LocalDate data){
        this.data = data;
    }
    
    public LocalTime getHorario(){
        return this.horario;
    }
    public void setHorario(LocalTime horario){
        this.horario = horario;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
