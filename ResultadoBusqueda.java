import java.util.ArrayList;
import java.util.List;

public class ResultadoBusqueda {
    private final boolean encontrado;
    private final int primerIndice;
    private final int ultimoIndice;
    private final List<Integer> todasLasPosiciones;
    private final int totalOcurrencias;
    private final String algoritmoSeleccionado;
    private final String estadoArreglo;
    private final int comparaciones;
    private final long tiempoNano;

    public ResultadoBusqueda(boolean encontrado, int primerIndice, int ultimoIndice,
                             List<Integer> todasLasPosiciones, int totalOcurrencias,
                             String algoritmoSeleccionado, String estadoArreglo,
                             int comparaciones, long tiempoNano) {
        this.encontrado = encontrado;
        this.primerIndice = primerIndice;
        this.ultimoIndice = ultimoIndice;
        this.todasLasPosiciones = (todasLasPosiciones != null) ? todasLasPosiciones : new ArrayList<>();
        this.totalOcurrencias = totalOcurrencias;
        this.algoritmoSeleccionado = algoritmoSeleccionado;
        this.estadoArreglo = estadoArreglo;
        this.comparaciones = comparaciones;
        this.tiempoNano = tiempoNano;
    }

    public boolean isEncontrado() {
        return encontrado;
    }

    public int getPrimerIndice() {
        return primerIndice;
    }

    public int getUltimoIndice() {
        return ultimoIndice;
    }

    public List<Integer> getTodasLasPosiciones() {
        return todasLasPosiciones;
    }

    public int getTotalOcurrencias() {
        return totalOcurrencias;
    }

    public String getAlgoritmoSeleccionado() {
        return algoritmoSeleccionado;
    }

    public String getEstadoArreglo() {
        return estadoArreglo;
    }

    public int getComparaciones() {
        return comparaciones;
    }

    public long getTiempoNano() {
        return tiempoNano;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===============================================================\n");
        sb.append(" REPORTE DE BUSQUEDA ADAPTATIVA\n");
        sb.append("===============================================================\n");
        sb.append(" Estado del arreglo analizado : ").append(estadoArreglo).append("\n");
        sb.append(" Algoritmo auto-seleccionado  : ").append(algoritmoSeleccionado).append("\n");
        sb.append(" Elemento encontrado          : ").append(encontrado ? "SI" : "NO").append("\n");
        if (encontrado) {
            sb.append(" Primer indice encontrado     : ").append(primerIndice).append("\n");
            sb.append(" Ultimo indice encontrado     : ").append(ultimoIndice).append("\n");
            sb.append(" Total de ocurrencias         : ").append(totalOcurrencias).append("\n");
            sb.append(" Todas las posiciones (indices): ").append(todasLasPosiciones).append("\n");
        } else {
            sb.append(" Mensaje                      : El elemento objetivo no existe en el arreglo.\n");
        }
        sb.append(" Comparaciones realizadas     : ").append(comparaciones).append("\n");
        sb.append(" Tiempo de ejecucion          : ").append(tiempoNano).append(" ns (")
          .append(String.format("%.4f", tiempoNano / 1_000_000.0)).append(" ms)\n");
        sb.append("===============================================================");
        return sb.toString();
    }
}
