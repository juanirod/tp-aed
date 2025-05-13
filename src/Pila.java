public class Pila {
    private final int maxSize;
    private int size = 0;
    private Nodo cima;

    public Pila(int maxSize) {
        this.maxSize = maxSize;
        this.cima = null;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public void apilar(String color) {

        Nodo nuevoNodo = new Nodo(color);
        nuevoNodo.setSiguiente(cima);
        cima = nuevoNodo;
        size++;
    }
    public boolean sePuedeApilar(){
        if (size >= maxSize) {
           return false;
        }

        return true;
    }

    public String desapilar() {
        if (estaVacia()) {
            return null;
        }
        String color = cima.getColor();
        cima = cima.getSiguiente();
        size--;
        return color;
    }

    public Nodo verCima() {
        return estaVacia() ? null : cima;
    }

    public void mostrarContenido() {
        Nodo actual = cima;
        String[] elementos = new String[size];
        int index = size - 1;

        while (actual != null) {
            elementos[index--] = actual.getColor();
            actual = actual.getSiguiente();
        }

        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(elementos[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }

        for (int i = size; i < maxSize; i++) {
            if (i == 0 && size == 0) {
                System.out.print("-");
            } else {
                System.out.print(", -");
            }
        }

        System.out.println("]");
    }
}
