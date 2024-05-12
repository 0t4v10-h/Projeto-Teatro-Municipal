public class Ingresso
{
    private Evento evento;
    private double preco;
    private Assento assento;

    public Ingresso()
    {
        
    }
    public Ingresso(Evento evento, double preco, Assento assento)
    {
        this.evento = evento;
        this.preco = preco;
        this.assento = assento;
    }

    public Evento getEvento(){
        return evento;
    }
    public void setEvento(Evento evento){
        this.evento = evento;
    }
    public double getPreco(){
        return preco;
    }
    public void setPreco(double preco){
        this.preco = preco;
    }
    public Assento getAssento(){
        return assento;
    }
    public void setAssento(Assento assento){
        this.assento = assento;
    }
    
}
