package main.java;
/** 
 * <p>
 * Clase que permite crear un nodo y proporciona los métodos necesarios para gestionarlo
 */
public class Link<T> {
    private T dData;
    private Link<T> next; 


    /**
     * <p>
     * Este método permite crear un nuevo nodo. 
     * </p>
     * 
     * @param dd El objeto que será asignado al campo de dato del nodo.
     * 
     */
    public Link(T dd) {
        dData = dd;
        //poner el siguiente enlace a null porque todavía no se conoce el siguiente nodo para enlazarlo
        next = null;
    }

    /**
     * <p>
     * Este método permite desplegar en pantalla los nodos que contiene la lista.
     * </p> 
     */
    public void displayLink() {
        System.out.print("{" + dData.toString() + "} "  );
    }

    
    /**
     * <p>
     * Este método permite acceder al dato de un determinado nodo.
     * </p>
     * 
     * @return El dato que almacena un determinado nodo.
     * 
     */
    public T getDato(){
        return dData;
        //NUEVO
    }

    
    /**
     * <p>
     * Este método permite acceder al nodo consecutivo de un determinado nodo.
     * </p>
     * 
     * @return El nodo que se encuentra en el campo de enlace del nodo que llama al método.
     * 
     */
    public Link<T> getNext(){
        return next;
    }

    
    /**
     * <p>
     * Este método permite establecer un enlace entre el nodo que llama el método y el nodo que se desea sea su siguiente.
     * </p>
     * 
     * @param n El nodo que será enlazado como nodo siguiente del nodo que llama al método.
     * 
     * 
     */
    public void setNext(Link<T> n){
        this.next = n;
    }
}
    