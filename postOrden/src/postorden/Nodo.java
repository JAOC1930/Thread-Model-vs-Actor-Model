package postorden;

public class Nodo {
    int dato;
    Nodo hijoI;
    Nodo hijoD;
    
    public Nodo(int x){
        this.dato = x;
        this.hijoD = null;
        this.hijoI = null;        
    }
    
    @Override
    public String toString(){
        return "El dato es: " + dato;                 
    }    
}