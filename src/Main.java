import java.awt.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static int MovimientosRestantes = 100;
    public static Pila torre1 = new Pila(8);
    public static Pila torre2 = new Pila(8);
    public static Pila torre3 = new Pila(8);

    public static void main(String[] args) {
        cargarTorres();
        ValidarMovimientos();
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Movimientos restantes: " + MovimientosRestantes);
        System.out.println("======================");
        System.out.println("1. Mover color entre torres");
        System.out.println("0. Salir");
        System.out.println("======================");
        System.out.print("Seleccione una opción: ");
        choice = scanner.nextInt();

        int from;
        int to;
        switch (choice) {
            case 1:
                System.out.print("Seleccione la torre de origen: (1, 2, 3): ");
                from = scanner.nextInt();

                System.out.print("Seleccione la torre de destino: (1, 2, 3): ");
                to = scanner.nextInt();
                moverColor(from, to);
                MovimientosRestantes--;
                break;
            case 0:
                MovimientosRestantes = -1;
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }

    }


    private static void mostrarTorres() {

        System.out.println("======================");
        System.out.print("Torre 1: ");
        torre1.mostrarContenido();
        System.out.print("Torre 2: ");
        torre2.mostrarContenido();
        System.out.print("Torre 3: ");
        torre3.mostrarContenido();
        System.out.println("======================");

    }

    private static void ValidarMovimientos() {
        if (coloresAgrupados()) {
            mostrarTorres();
            System.out.println("¡Colores agrupados correctamente! Ganaste.");
            MovimientosRestantes = 0;
            return;
        }

        if (MovimientosRestantes < 0) {
            System.out.println("Game over");
            System.out.println("Ha salido del juego");
        }
        if (MovimientosRestantes == 0) {
            System.out.println("Game over");
            System.out.println("Se le han acabo los movimientos");
        }

        if (MovimientosRestantes > 0) {
            if(MovimientosRestantes < 100) LimpiarPantalla();
            mostrarTorres();
            menu();
            ValidarMovimientos();
        }
    }

    private static void cargarTorres() {
        String[] colores = {"Rojo", "Verde", "Amarillo"};
        Random random = new Random();

        Map<String, Integer> contadorColores = new HashMap<>();
        for (String color : colores) {
            contadorColores.put(color, 0);
        }

        int[] cantidades = {5, 3, 1};
        Pila[] torres = {torre1, torre2, torre3};

        for (int i = 0; i < torres.length; i++) {
            for (int j = 0; j < cantidades[i]; j++) {
                String colorSeleccionado;
                do {
                    colorSeleccionado = colores[random.nextInt(colores.length)];
                } while (contadorColores.get(colorSeleccionado) >= 3);
                contadorColores.put(colorSeleccionado, contadorColores.get(colorSeleccionado) + 1);
                torres[i].apilar(colorSeleccionado);
            }
        }
    }


    private static void moverColor(int From, int To) {
        String color = null;
        boolean disponible;
        switch (To) {
            case 1:
                disponible = torre1.sePuedeApilar();
                break;
            case 2:
                disponible = torre2.sePuedeApilar();
                break;
            case 3:
                disponible = torre3.sePuedeApilar();
                break;
            default:
                disponible = false;
        }


        if (disponible) {
            switch (From) {
                case 1:
                    color = torre1.desapilar();
                    break;
                case 2:
                    color = torre2.desapilar();
                    break;
                case 3:
                    color = torre3.desapilar();
                    break;
            }

            switch (To) {
                case 1:
                    torre1.apilar(color);
                    break;
                case 2:
                    torre2.apilar(color);
                    break;
                case 3:
                    torre3.apilar(color);
                    break;
            }
        }else{
            System.out.println("La torre está llena");
        }
    }

    private static boolean coloresAgrupados() {
        return torreEsHomogenea(torre1) &&
                torreEsHomogenea(torre2) &&
                torreEsHomogenea(torre3);
    }

    private static boolean torreEsHomogenea(Pila torre) {
        if (torre.estaVacia()) return true;

        Nodo actual = torre.verCima();
        String colorBase = actual.getColor();

        while (actual != null) {
            if (!actual.getColor().equals(colorBase)) {
                return false;
            }
            actual = actual.getSiguiente();
        }
        return true;
    }

    private static void LimpiarPantalla(){
        for(int i = 0; i < 100; i++){
            System.out.println();
        }
    }
}