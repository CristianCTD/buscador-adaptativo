import java.util.ArrayList;
import java.util.List;

public class BuscadorAdaptativo {

    public enum EstadoOrden {
        ORDENADO_CRECIENTE,
        ORDENADO_DECRECIENTE,
        DESORDENADO,
        VACIO_O_UNITARIO
    }

    public static EstadoOrden determinarEstadoOrden(int[] a) {
        if (a == null || a.length <= 1) {
            return EstadoOrden.VACIO_O_UNITARIO;
        }

        boolean creciente = true;
        boolean decreciente = true;

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                creciente = false;
            }
            if (a[i] < a[i + 1]) {
                decreciente = false;
            }
        }

        if (creciente) return EstadoOrden.ORDENADO_CRECIENTE;
        if (decreciente) return EstadoOrden.ORDENADO_DECRECIENTE;
        return EstadoOrden.DESORDENADO;
    }

    public static ResultadoBusqueda buscar(int[] a, int target) {
        if (a == null || a.length == 0) {
            return new ResultadoBusqueda(false, -1, -1, new ArrayList<>(), 0,
                    "Ninguno (Arreglo vacio)", "Vacio", 0, 0);
        }

        long inicioTiempo = System.nanoTime();
        EstadoOrden estado = determinarEstadoOrden(a);

        ResultadoBusqueda resultado;
        switch (estado) {
            case ORDENADO_CRECIENTE:
            case VACIO_O_UNITARIO:
                resultado = buscarBinariaCrecienteConMetricas(a, target, estado.toString(), inicioTiempo);
                break;
            case ORDENADO_DECRECIENTE:
                resultado = buscarBinariaDecrecienteConMetricas(a, target, estado.toString(), inicioTiempo);
                break;
            case DESORDENADO:
            default:
                resultado = buscarLinealConMetricas(a, target, estado.toString(), inicioTiempo);
                break;
        }

        return resultado;
    }

    public static ResultadoBusqueda buscarLinealConMetricas(int[] a, int target, String estadoTexto, long inicioTiempo) {
        int comparaciones = 0;
        List<Integer> posiciones = new ArrayList<>();
        int primerIndice = -1;
        int ultimoIndice = -1;

        for (int i = 0; i < a.length; i++) {
            comparaciones++;
            if (a[i] == target) {
                if (primerIndice == -1) {
                    primerIndice = i;
                }
                ultimoIndice = i;
                posiciones.add(i);
            }
        }

        long finTiempo = System.nanoTime();
        boolean encontrado = !posiciones.isEmpty();
        return new ResultadoBusqueda(
            encontrado, primerIndice, ultimoIndice, posiciones, posiciones.size(),
            "Busqueda Lineal O(n) [Seleccion automatica por datos desordenados]",
            estadoTexto, comparaciones, (finTiempo - inicioTiempo)
        );
    }

    public static ResultadoBusqueda buscarBinariaCrecienteConMetricas(int[] a, int target, String estadoTexto, long inicioTiempo) {
        int[] contadorComp = new int[1];

        int first = lowerBound(a, target, contadorComp);

        boolean existe = (first < a.length && a[first] == target);

        List<Integer> posiciones = new ArrayList<>();
        int last = -1;

        if (existe) {

            last = lastIndexOfSorted(a, target, contadorComp);
            for (int i = first; i <= last; i++) {
                posiciones.add(i);
            }
        }

        long finTiempo = System.nanoTime();
        return new ResultadoBusqueda(
            existe, existe ? first : -1, existe ? last : -1, posiciones, posiciones.size(),
            "Busqueda Binaria O(log n) [Seleccion automatica por datos ordenados]",
            estadoTexto, contadorComp[0], (finTiempo - inicioTiempo)
        );
    }

    private static int lowerBound(int[] a, int target, int[] comp) {
        int lo = 0;
        int hi = a.length;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >>> 1);
            comp[0]++;
            if (a[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private static int lastIndexOfSorted(int[] a, int target, int[] comp) {
        int lo = 0;
        int hi = a.length;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >>> 1);
            comp[0]++;
            if (a[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        int idx = lo - 1;
        return (idx >= 0 && a[idx] == target) ? idx : -1;
    }

    private static ResultadoBusqueda buscarBinariaDecrecienteConMetricas(int[] a, int target, String estadoTexto, long inicioTiempo) {
        int comparaciones = 0;
        int lo = 0;
        int hi = a.length - 1;
        int posEncontrada = -1;

        while (lo <= hi) {
            comparaciones++;
            int mid = lo + ((hi - lo) >>> 1);
            if (a[mid] == target) {
                posEncontrada = mid;
                break;
            } else if (a[mid] < target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        List<Integer> posiciones = new ArrayList<>();
        int first = -1;
        int last = -1;

        if (posEncontrada != -1) {

            first = posEncontrada;
            while (first > 0 && a[first - 1] == target) {
                first--;
                comparaciones++;
            }
            last = posEncontrada;
            while (last < a.length - 1 && a[last + 1] == target) {
                last++;
                comparaciones++;
            }
            for (int i = first; i <= last; i++) {
                posiciones.add(i);
            }
        }

        long finTiempo = System.nanoTime();
        return new ResultadoBusqueda(
            posEncontrada != -1, first, last, posiciones, posiciones.size(),
            "Busqueda Binaria Decreciente O(log n) [Seleccion automatica]",
            estadoTexto, comparaciones, (finTiempo - inicioTiempo)
        );
    }
}
