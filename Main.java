import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private final Scanner scanner;

    public Main() {
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = -1;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion (0-6): ");
            System.out.println();
            switch (opcion) {
                case 1:
                    casoPruebaDesordenado();
                    break;
                case 2:
                    casoPruebaOrdenadoSinDuplicados();
                    break;
                case 3:
                    casoPruebaOrdenadoConDuplicados();
                    break;
                case 4:
                    casoPruebaElementoInexistente();
                    break;
                case 5:
                    casoPruebaPersonalizado();
                    break;
                case 6:
                    comparativaExperimentalRendimiento();
                    break;
                case 0:
                    System.out.println("===============================================================");
                    System.out.println(" Gracias por utilizar el Buscador Adaptativo.");
                    System.out.println(" Universidad de Cartagena - Ingenieria de Software.");
                    System.out.println("===============================================================");
                    break;
                default:
                    System.out.println("[Error] Opcion invalida. Ingrese un valor entre 0 y 6.");
            }
            if (opcion != 0) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n===============================================================");
        System.out.println(" SISTEMA DE BUSQUEDA ADAPTATIVA (LINEAL VS BINARIA)");
        System.out.println(" Ejercicio N. 30 | Estudiante: Cristian Torres | U. de Cartagena");
        System.out.println("===============================================================");
        System.out.println(" 1. Probar Arreglo Desordenado (Auto-seleccion: Busqueda Lineal)");
        System.out.println(" 2. Probar Arreglo Ordenado sin duplicados (Auto-seleccion: Binaria)");
        System.out.println(" 3. Probar Arreglo Ordenado con duplicados (Rango lower/upper bound)");
        System.out.println(" 4. Probar Elemento Inexistente (Comprobacion de fallos)");
        System.out.println(" 5. Ingresar Arreglo Personalizado (Prueba libre)");
        System.out.println(" 6. Comparativa Experimental: Lineal O(n) vs Binaria O(log n)");
        System.out.println(" 0. Salir");
        System.out.println("===============================================================");
    }

    private void casoPruebaDesordenado() {
        System.out.println("--- CASO 1: ARREGLO DESORDENADO ---");
        int[] arreglo = {45, 12, 89, 23, 7, 56, 23, 90, 11, 23};
        int objetivo = 23;
        System.out.println("Arreglo analizado: " + Arrays.toString(arreglo));
        System.out.println("Elemento objetivo a buscar: " + objetivo);
        System.out.println();

        ResultadoBusqueda res = BuscadorAdaptativo.buscar(arreglo, objetivo);
        System.out.println(res);
    }

    private void casoPruebaOrdenadoSinDuplicados() {
        System.out.println("--- CASO 2: ARREGLO ORDENADO SIN DUPLICADOS ---");
        int[] arreglo = {5, 12, 19, 28, 34, 45, 61, 73, 88, 92, 105, 120};
        int objetivo = 73;
        System.out.println("Arreglo analizado: " + Arrays.toString(arreglo));
        System.out.println("Elemento objetivo a buscar: " + objetivo);
        System.out.println();

        ResultadoBusqueda res = BuscadorAdaptativo.buscar(arreglo, objetivo);
        System.out.println(res);
    }

    private void casoPruebaOrdenadoConDuplicados() {
        System.out.println("--- CASO 3: ARREGLO ORDENADO CON DUPLICADOS (LOWER / UPPER BOUND) ---");
        int[] arreglo = {10, 20, 30, 30, 30, 30, 30, 45, 60, 75, 90};
        int objetivo = 30;
        System.out.println("Arreglo analizado: " + Arrays.toString(arreglo));
        System.out.println("Elemento objetivo con duplicados: " + objetivo);
        System.out.println();

        ResultadoBusqueda res = BuscadorAdaptativo.buscar(arreglo, objetivo);
        System.out.println(res);
    }

    private void casoPruebaElementoInexistente() {
        System.out.println("--- CASO 4: ELEMENTO INEXISTENTE ---");
        int[] arreglo = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        int objetivo = 15;
        System.out.println("Arreglo analizado: " + Arrays.toString(arreglo));
        System.out.println("Elemento objetivo inexistente: " + objetivo);
        System.out.println();

        ResultadoBusqueda res = BuscadorAdaptativo.buscar(arreglo, objetivo);
        System.out.println(res);
    }

    private void casoPruebaPersonalizado() {
        System.out.println("--- CASO 5: INGRESO DE ARREGLO PERSONALIZADO ---");
        System.out.print("Ingrese los enteros separados por espacios o comas: ");
        String linea = scanner.nextLine().trim();
        String[] partes = linea.split("[,\\s]+");

        int[] arreglo = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            arreglo[i] = Integer.parseInt(partes[i].trim());
        }

        int objetivo = leerEntero("Ingrese el numero objetivo a buscar: ");
        System.out.println("\nAnalizando y procesando...");
        ResultadoBusqueda res = BuscadorAdaptativo.buscar(arreglo, objetivo);
        System.out.println(res);
    }

    private void casoPruebaComparativaExperimentalRendimiento() {
        comparativaExperimentalRendimiento();
    }

    private void comparativaExperimentalRendimiento() {
        System.out.println("--- CASO 6: COMPARATIVA EXPERIMENTAL LINEAL O(n) VS BINARIA O(log n) ---");
        int n = 100_000;
        System.out.println("Generando arreglo ordenado de " + String.format("%,d", n) + " elementos...");
        int[] arreglo = new int[n];
        for (int i = 0; i < n; i++) {
            arreglo[i] = i * 2;
        }

        int objetivo = (n - 1) * 2;
        System.out.println("Buscando el elemento en la ultima posicion: " + objetivo);
        System.out.println();

        long t1 = System.nanoTime();
        ResultadoBusqueda resLineal = BuscadorAdaptativo.buscarLinealConMetricas(arreglo, objetivo, "ORDENADO (Forzado Lineal)", t1);

        long t2 = System.nanoTime();
        ResultadoBusqueda resBinaria = BuscadorAdaptativo.buscarBinariaCrecienteConMetricas(arreglo, objetivo, "ORDENADO (Binaria)", t2);

        System.out.printf("%-25s | %-18s | %-18s%n", "Metrica", "Busqueda Lineal O(n)", "Busqueda Binaria O(log n)");
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-25s | %-18d | %-18d%n", "Comparaciones", resLineal.getComparaciones(), resBinaria.getComparaciones());
        System.out.printf("%-25s | %-18d | %-18d%n", "Tiempo (nanosegundos)", resLineal.getTiempoNano(), resBinaria.getTiempoNano());
        System.out.printf("%-25s | %-18.4f | %-18.4f%n", "Tiempo (milisegundos)", resLineal.getTiempoNano() / 1_000_000.0, resBinaria.getTiempoNano() / 1_000_000.0);
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Conclusion demostrada:");
        System.out.println("Para n = " + n + ", Lineal realizo " + resLineal.getComparaciones() +
                           " comparaciones vs solo " + resBinaria.getComparaciones() + " de Binaria (reduccion masiva log2(n)).");
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                if (!mensaje.isEmpty()) {
                    System.out.print(mensaje);
                }
                String linea = scanner.nextLine().trim();
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("[Error] Entrada invalida. Ingrese un numero entero.");
            }
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.iniciar();
    }
}
