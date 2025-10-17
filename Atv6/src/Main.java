import java.util.*;

/**
 * @author Erick
 * @version 1.0
 */

public class Main {
    static final int LIMITE_HISTORIAL = 5;
    static Scanner scanner = new Scanner(System.in);
    static List<String> frasesOriginales = new ArrayList<>();
    static Deque<String> historialInversiones = new ArrayDeque<>();

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Invertir frase");
            System.out.println("2 - Ver frases");
            System.out.println("3 - Deshacer ultima inversion");
            System.out.println("4 - Invertir palabra");
            System.out.println("5 - Salir");
            System.out.print("Opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> invertirFrase();
                case 2 -> mostrarFrases();
                case 3 -> deshacerInversion();
                case 4 -> invertirLetras();
                case 5 -> System.out.println("Programa finalizado.");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 5);
    }

    static void invertirFrase() {
        System.out.print("Ingrese una frase: ");
        String frase = scanner.nextLine();
        frasesOriginales.add(frase);

        Stack<String> pilaPalabras = new Stack<>();
        String[] palabras = frase.split(" ");
        for (String palabra : palabras) {
            pilaPalabras.push(palabra);
        }

        StringBuilder fraseInvertida = new StringBuilder();
        while (!pilaPalabras.isEmpty()) {
            fraseInvertida.append(pilaPalabras.pop()).append(" ");
        }

        String resultado = fraseInvertida.toString().trim();
        agregarAlHistorial(resultado);
        System.out.println("Frase original: " + frase);
        System.out.println("Frase invertida: " + resultado);
    }

    static void mostrarFrases() {
        if (frasesOriginales.isEmpty()) {
            System.out.println("No hay frases.");
            return;
        }

        List<String> ordenadas = new ArrayList<>(frasesOriginales);
        Collections.sort(ordenadas);
        System.out.println("Frases en orden alfabetico:");
        for (String f : ordenadas) {
            System.out.println("- " + f);
        }
    }

    static void deshacerInversion() {
        if (!historialInversiones.isEmpty()) {
            String removida = historialInversiones.pop();
            System.out.println("Ultima inversion deshecha: " + removida);
        } else {
            System.out.println("No hay inversiones para deshacer.");
        }
    }

    static void invertirLetras() {
        System.out.print("Ingrese una frase: ");
        String frase = scanner.nextLine();
        frasesOriginales.add(frase);

        StringBuilder resultado = new StringBuilder();
        for (String palabra : frase.split(" ")) {
            resultado.append(new StringBuilder(palabra).reverse()).append(" ");
        }

        String invertida = resultado.toString().trim();
        agregarAlHistorial(invertida);
        System.out.println("Frase con letras invertidas: " + invertida);
    }

    static void agregarAlHistorial(String fraseInvertida) {
        if (historialInversiones.size() >= LIMITE_HISTORIAL) {
            historialInversiones.removeLast();
        }
        historialInversiones.push(fraseInvertida);
    }
}