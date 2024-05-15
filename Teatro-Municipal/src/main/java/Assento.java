
public class Assento
{
   private int numeroAssento;
   private boolean ocupado;

   public Assento(){}
   public Assento(int numeroAssento){
       this.numeroAssento = numeroAssento;
       this.ocupado = false;
   }

    public int getNumeroAssento(){

       return numeroAssento;
   }
   public void setNumeroAssento(int numeroAssento){
       this.numeroAssento = numeroAssento;
   }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}
