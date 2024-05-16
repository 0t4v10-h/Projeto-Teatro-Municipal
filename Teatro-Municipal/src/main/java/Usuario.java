public class Usuario {
    private String nome;
    private String nomeUsuario;
    private int senha;

    public Usuario(){}
    public Usuario(String nome, String nomeUsuario, int senha) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSenha() {

        return senha;
    }
    public void setSenha(int senha) {
        this.senha = senha;
    }

    public String getNomeUsuario() {

        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {

        this.nomeUsuario = nomeUsuario;
    }
}
