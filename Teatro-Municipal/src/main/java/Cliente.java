public class Cliente extends Usuario{

    private String email;

    public Cliente(){
        super("","",0, "");
    }
    public Cliente(String nome, String nomeUsuario, int senha,String email, String dataNasc){
        super("", "", 0, "");
        this.email = email;

    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
}
