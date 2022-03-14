package main.java;

import main.java.utils.ExcepcionDynamicList;

/**
 * <p>
 * Clase que permite crear una lista que almacena nodos de tipo Link y ofrece
 * métodos para realizar operaciones sobre la lista
 * @author FerMendezA
 */
public class LinkList<T> {
    private Link<T> first;

    public LinkList() {
        first = null;
    }

    // Método que devuelva el primer elemento de la lista (solo lo devuelve, no lo
    // elimina de la lista)
    /**
     * <p>
     * Este método regresa el nodo correspondiente a la primera posición de la
     * lista.
     * </p>
     * 
     * @return El primer nodo de la lista si es que no está vacía, null en caso
     *         contrario.
     * 
     */
    public Link<T> first() {
        return first;
    }

    // Método que devuelva el último elemento de la lista (solo lo devuelve, no lo
    // elimina de la lista)

    /**
     * <p>
     * Este método regresa el nodo correspondiente a la última posición de la lista.
     * </p>
     * 
     * @return El último nodo de la lista si es que no está vacía, null en caso
     *         contrario.
     * 
     */
    public Link<T> last() {
        if (isEmpty()) {
            return first;
        } else {
            Link<T> temp = first;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            return temp;
        }
    }

    // Método que devuelva el tamaño de la lista
    /**
     * <p>
     * Este método permite conocer el tamaño de la lista, es decir el número de
     * elementos que contiene.
     * </p>
     * 
     * @return Un entero que representa la cantidad de elementos almacenados en la
     *         lista.
     * 
     */
    public int getSize() {
        int size = 0;
        Link<T> temp = first;
        while (temp != null) {
            temp = temp.getNext();
            size++;
        }
        return size;
    }

    // Método para insertar un elemento antes de uno proporcionado (búsqueda por
    // dato).
    /**
     * <p>
     * Este método permite insertar un nodo nuevo antes de algún nodo existente.
     * </p>
     * 
     * @param objRef El dato existente en alguno de los nodos de la lista, que
     *               servirá de referencia para insertar antes de él.
     * @param objNew El nuevo dato que se asignará a un nuevo nodo que será
     *               insertado antes del nodo de referencia.
     * 
     */
    public void insertBefore(T objRef, T objNew) throws ExcepcionDynamicList {
        Link<T> temp = first;
        Link<T> anterior = null;
        while (temp != null) {
            if (objRef.toString().equals(temp.getDato().toString())) {
                Link<T> l = new Link<T>(objNew);
                l.setNext(temp);

                if (anterior != null) {
                    anterior.setNext(l);
                } else {
                    first = l;
                }
                break;
            }
            anterior = temp;
            temp = temp.getNext();
        }
        if (temp == null) {
            throw new ExcepcionDynamicList("Error : no se encontró el elemento de referencia para la inserción ");
        }

    }

    // Método para insertar un elemento después de uno proporcionado (búsqueda por
    // dato).
    /**
     * <p>
     * Este método permite insertar un nodo nuevo después de algún nodo existente.
     * </p>
     * 
     * @param objRef El dato existente en alguno de los nodos de la lista, que
     *               servirá de referencia para insertar después.
     * @param objNew El nuevo dato que se asignará a un nuevo nodo que será
     *               insertado después del nodo de referencia.
     * 
     */
    public void insertAfter(T objRef, T objNew) throws ExcepcionDynamicList {
        Link<T> temp = first;
        while (temp != null) {
            if (objRef.toString().equals(temp.getDato().toString())) {
                Link<T> l = new Link<T>(objNew);
                l.setNext(temp.getNext());
                temp.setNext(l);
                break;
            }
            temp = temp.getNext();
        }
        if (temp == null) {
            throw new ExcepcionDynamicList("Error : no se encontró el elemento de referencia para la inserción ");
        }
    }

    // Método para insertar elemento en forma ordenada a. Creciente SOLO CONSIDERAR
    // DATOS NUMÉRICOS
    /**
     * <p>
     * Este método permite insertar un nodo de manera ordenada y creciente, es decir
     * agrega un nuevo nodo antes de algún nodo que resulte ser mayor que el primero
     * en una comparación de Strings.
     * </p>
     * 
     * @param dato El dato que se asignará al nuevo nodo y será colocado en una
     *             posición ordenada en la lista.
     * 
     */
    public void insertCrescent(T dato) {
        Link<T> newLink = new Link<T>(dato);
        try {
            if (first == null || 0 <= (first.getDato().toString().compareTo(newLink.getDato().toString()))) {
                newLink.setNext(first);
                first = newLink;
            } else {
                Link<T> temp = first;
                while (temp.getNext() != null
                        && 0 < (newLink.getDato().toString().compareTo(temp.getNext().getDato().toString()))) {
                    // temp2 = temp;
                    temp = temp.getNext();
                }
                newLink.setNext(temp.getNext());
                temp.setNext(newLink);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para insertar elemento en forma ordenada b. Decreciente SOLO
    // CONSIDERAR DATOS NUMÉRICOS SUPONER QUE LA LISTA ESTÁ ORDENADA
    /**
     * <p>
     * Este método permite insertar un nodo de manera ordenada y decreciente, es
     * decir agrega un nuevo nodo antes de algún nodo que resulte ser menor que el
     * primero en una comparación de Strings.
     * </p>
     * 
     * @param dato El dato que se asignará al nuevo nodo y será colocado en una
     *             posición ordenada en la lista.
     * 
     */
    public void insertDecrescent(T dato) {
        Link<T> newLink = new Link<T>(dato);
        try {
            // Cuando la lista esté vacía o el elemento por agregar sea menor al de first
            if (first == null || 0 >= (first.getDato().toString().compareTo(newLink.getDato().toString()))) {
                newLink.setNext(first);
                first = newLink;
            } else {
                Link<T> temp = first;
                while (temp.getNext() != null
                        && 0 > (newLink.getDato().toString().compareTo(temp.getNext().getDato().toString()))) {
                    temp = temp.getNext();
                }
                newLink.setNext(temp.getNext());
                temp.setNext(newLink);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un elemento proporcionado (mediante su dato)
    /**
     * <p>
     * Este método permite eliminar el nodo de la lista que contenga un dato
     * específico.
     * </p>
     * 
     * @param data El dato existente en alguno de los nodos de la lista, que servirá
     *             para buscar el nodo a eliminar.
     * 
     */
    public void deleteWhere(Link<T> data) throws ExcepcionDynamicList {
        boolean eliminado = false;
        if (first.getDato().toString().equals(data.getDato().toString())) {
            try {
                deleteFirst();
                eliminado = true;
            } catch (ExcepcionDynamicList e) {
                e.printStackTrace();
            }
        } else {
            Link<T> temp = first;
            Link<T> anterior = null;
            while (temp.getNext() != null) {
                anterior = temp;
                temp = temp.getNext();
                if (temp.getDato().toString().equals(data.toString())) {
                    anterior.setNext(temp.getNext());
                    temp.setNext(null);
                    eliminado = true;
                }
            }
        }
        if (!eliminado) {
            throw new ExcepcionDynamicList("Error : no se encontró el elemento para la eliminación ");
        }
    }

    // Método para eliminar un nodo de una posición proporcionada mediante su indice
    /**
     * <p>
     * Este método permite eliminar el nodo de la lista que corresponda a una
     * posición determinada
     * </p>
     * 
     * @param posDel El entero que representa la posición del nodo que se desea
     *               eliminar.
     * 
     */
    public void deleteAt(int posDel) throws ExcepcionDynamicList {
        if (posDel > 0) {
            boolean eliminado = false;
            if (posDel == 1) {
                try {
                    deleteFirst();
                    eliminado = true;
                } catch (ExcepcionDynamicList e) {
                    e.printStackTrace();
                }
            } else {
                Link<T> temp = first;
                int i = posDel - 1;
                while (temp != null && i > 1) { // llega antes del nodo por eliminar
                    temp = temp.getNext();
                    i--;
                }
                if (i == 1 && temp.getNext() != null) {
                    Link<T> delete = temp.getNext();
                    temp.setNext(delete.getNext());
                    delete = null;
                    eliminado = true;
                }
            }
            if (!eliminado) {
                throw new ExcepcionDynamicList("No se pudo eliminar en dicha posición");
            }
        } else {
            throw new ExcepcionDynamicList("No se pudo eliminar en dicha posición");
        }
    }

    // Método para eliminar todos los elementos de la lista (limpiar la lista).
    /**
     * <p>
     * Este método eliminar todos los elementos de la lista para dejarla vacía.
     * </p>
     */
    public void clearList() {
        // Elimina los nodos desde el primero hasta el último
        Link<T> temp = null;
        while (first != null) {
            temp = first.getNext();
            first = null;
            first = temp;
        }
    }

    // Método para hacer una búsqueda de un elemento y devolver -1 si no lo encontró
    // y la posición del dato en la lista en caso de que se haya encontrado
    /**
     * <p>
     * Este método permite buscar un dato para saber si se encuentra en alguno de
     * los nodos de la lista, también permite conocer la posición del dato si se
     * encontró.
     * </p>
     * 
     * @param data El dato que servirá de referencia para buscar si en la lista se
     *             encuentra algún nodo que contenga dicho dato.
     * @return Un entero que representa la posición del nodo donde se encontró el
     *         dato en la lista. Si regresa -1 indica que no se encontró un nodo con
     *         dicho dato
     */
    public int buscar(String data) {
        Link<T> temp = first;
        int i = 1; // registra la posición del elemento por buscar
        if (isEmpty()) {
            return -1;
        } else {
            while (temp != null) {
                if (temp.getDato().toString().equals(data)) {
                    return i;
                }
                temp = temp.getNext();
                i++;
            }
            return -1;
        }
    }

    // Método para reemplazar un nodo de una posición proporcionada con otro nodo.
    /**
     * <p>
     * Este método permite reemplazar el nodo de una posición determinada en la
     * lista con un nuevo nodo.
     * </p>
     * 
     * @param pos El entero que representa la posición del nodo que se desea
     *            sustituir.
     * @param l   El nodo que se desea que sustituya al nodo de la posición enviada.
     * 
     */
    public void replaceAtWith(int pos, Link<T> l) throws ExcepcionDynamicList {
        if (pos > 0) {
            Link<T> temp = first;
            Link<T> tempAnterior = null;
            int i = pos;
            boolean reemplazo = false;
            while (temp != null && i > 1) { // llega antes del nodo por eliminar
                tempAnterior = temp;
                temp = temp.getNext();
                i--;
            }
            if (temp != null) {
                l.setNext(temp.getNext());
                if (tempAnterior != null) {
                    tempAnterior.setNext(l);

                }
                reemplazo = true;
            }
            if (!reemplazo) {
                throw new ExcepcionDynamicList("No se pudo reemplazar en dicha posición");
            }
        } else {
            throw new ExcepcionDynamicList("No se pudo reemplazar en dicha posición");
        }
    }

    /**
     * <p>
     * Este método permite verificar si la lista está vacía.
     * </p>
     * 
     * @return True en caso de que la lista esté vacía, false en caso contrario.
     * 
     */
    public boolean isEmpty() {
        return (first == null);
    }

    /**
     * <p>
     * Este método permite insertar un nodo nuevo en la primera posición de la
     * lista.
     * </p>
     * 
     * @param dd El dato que se requiere asignar al nuevo nodo de la lista.
     * 
     */
    public void insertFirst(T dd) {
        Link<T> newLink = new Link<T>(dd);
        newLink.setNext(first);
        first = newLink;
    }

    /**
     * <p>
     * Este método permite insertar un nodo nuevo para que ocupe la última posición
     * de la lista.
     * </p>
     * 
     * @param dd El dato que se requiere asignar al nuevo nodo de la lista.
     * 
     */
    public void insertLast(T dd) {
        Link<T> newLink = new Link<T>(dd);
        if (isEmpty()) {
            first = newLink;
        } else {
            Link<T> temp = first;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newLink);
        }
    }

    /**
     * <p>
     * Este método permite eliminar un nodo desde la última posición de la lista.
     * </p>
     * 
     * @return El nodo que anteriormente a la eliminación ocupaba la última posición
     *         de la lista.
     * 
     */
    public Link<T> deleteLast() throws ExcepcionDynamicList {
        if (isEmpty()) {
            throw new ExcepcionDynamicList("Error : la lista está vacía");
        } else {
            Link<T> temp = first;
            // Caso donde el nodo por eliminar es el único
            if (temp.getNext() == null) {
                first = null;
                return temp;
            } else {
                // Caso donde hay dos o más elementos
                Link<T> temp2 = temp;
                while (temp.getNext() != null) {
                    temp2 = temp;
                    temp = temp.getNext();
                }
                temp2.setNext(null);
                return temp;
            }

        }
    }

    /**
     * <p>
     * Este método permite eliminar un nodo desde la primera posición de la lista.
     * </p>
     * 
     * @return El nodo que anteriormente a la eliminación ocupaba la primera
     *         posición de la lista.
     * 
     */
    public Link<T> deleteFirst() throws ExcepcionDynamicList {
        Link<T> temp = null;
        if (isEmpty()) {
            throw new ExcepcionDynamicList("Error : la lista está vacia");
        } else {
            temp = first;
            first = first.getNext();
        }
        return temp;
    }

    /**
     * <p>
     * Este método permite imprimir todos los elementos que tiene almacenada la
     * lista.
     * </p>
     */
    public void displayList() throws ExcepcionDynamicList {
        if (isEmpty()) {
            throw new ExcepcionDynamicList("Error : la lista está vacia");
        }
        System.out.print("List (first--> ");
        Link<T> current = first;
        while (current != null) {
            current.displayLink();
            current = current.getNext();
        }
        System.out.println("<--last)");
    }

}