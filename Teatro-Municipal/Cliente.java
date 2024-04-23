import java.time.LocalDate;

public class Cliente{
    
    private String nome;
    private String email;
    private LocalDate dataNasc;
    
    public Cliente(){}
    public Cliente(String nome, String email, LocalDate dataNasc){
    
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
    }
    
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    
    public LocalDate getDataNasc(){
        return this.dataNasc;
    }
    public void setDataNasc(LocalDate dataNasc){
        this.dataNasc = dataNasc;
    }
}
