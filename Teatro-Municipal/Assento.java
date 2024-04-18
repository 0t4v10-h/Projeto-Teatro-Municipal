
public class Assento
{
   //Atributos
   private int numero;
   private boolean assentoDisponivel;
   
   //Contrutor
   public Assento(){
   
   }
   public Assento(int numero, boolean assentoDisponivel){
       this.numero = numero;
       this.assentoDisponivel = assentoDisponivel;
   }
   
   //Metodos
   public int verificaAssento(int numero, boolean assentoOcupado){
       return 0; 
   }
   
   //Get e Set
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
