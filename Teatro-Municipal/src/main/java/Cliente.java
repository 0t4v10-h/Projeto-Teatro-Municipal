
public class Cliente extends Usuario{
    
    private String email;
    private String dataNasc;

    public Cliente(){
        super("","");
    }
    public Cliente(String email, String dataNasc){
        super("", "");
        this.email = email;
        this.dataNasc = dataNasc;
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
