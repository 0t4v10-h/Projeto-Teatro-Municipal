public class Ingresso
{
    private Evento evento;
    private Assento assento;

    public Ingresso() {}
    public Ingresso(Evento evento, Assento assento)
    {
        this.evento = evento;
        this.assento = assento;
    }

    public Evento getEvento(){
        return evento;
    }
    public void setEvento(Evento evento){
        this.evento = evento;
    }
    public Assento getAssento(){
        return assento;
    }
    public void setAssento(Assento assento){
        this.assento = assento;
    }
}
