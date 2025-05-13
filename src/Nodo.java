public class Nodo {
    private String color;
    private Nodo siguiente;

    public Nodo(String color) {
        this.color = color;
        this.siguiente = null;
    }

    public String getColor() {
        return color;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }


}