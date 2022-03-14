package main.java.utils;
/** 
 * <p>
 * Clase que permite crear las excepciones necesarias cuando suceda algún error en las operaciones de LinkList
 */
public class ExcepcionDynamicList extends Exception {
    public ExcepcionDynamicList(String mensaje){
        super(mensaje);
    }
}