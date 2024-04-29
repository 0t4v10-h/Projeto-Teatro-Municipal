
public class Assento
{
   private int numero;
   private boolean assentoDisponivel;


   public Assento(){
   
   }
   public Assento(int numero, boolean assentoDisponivel){
       this.numero = numero;
       this.assentoDisponivel = assentoDisponivel;
   }
   

   public int verificaAssento(int numero, boolean assentoOcupado){

       return 0;
   }
   

   public int getNumero(){
       return numero;
   }
   public void setNumero(int numero){
       this.numero = numero; 
   }
   public boolean getAssentoDisponivel(){
       return assentoDisponivel;
   }
   public void setAssentoDisponivel(boolean assentoDisponivel){
       this.assentoDisponivel = assentoDisponivel;          
   }

}
