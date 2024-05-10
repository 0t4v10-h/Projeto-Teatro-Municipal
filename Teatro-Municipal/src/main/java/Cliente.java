
public class Cliente{
    
    private String nome;
    private String email;
    private String dataNasc;
    
    public Cliente(){}
    public Cliente(String nome, String email, String dataNasc){
    
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
    
    public String getDataNasc(){
        return this.dataNasc;
    }
    public void setDataNasc(String dataNasc){
        this.dataNasc = dataNasc;
    }
}
