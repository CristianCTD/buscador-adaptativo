# Buscador Adaptativo: Algoritmos de Búsqueda Lineal vs. Binaria

**Asignatura:** Estructuras de Datos  
**Unidad:** Unidad 3 - Taller Práctico: Algoritmos de Búsqueda  
**Programa:** Ingeniería de Software  
**IES:** Universidad de Cartagena (Centro Tutorial Cereté)  
**Tutor:** John Carlos Arrieta Arrieta  
**Estudiante:** Cristian Torres  
**Ejercicio Asignado:** N.º 30 - Buscador Adaptativo

---

## 1. Descripción del Ejercicio
El **Buscador Adaptativo** es un algoritmo inteligente que analiza en tiempo de ejecución las propiedades intrínsecas de un arreglo para seleccionar automáticamente la estrategia de búsqueda más eficiente:

1. **Análisis de precondiciones en $O(n)$:** Evalúa si la estructura de datos se encuentra desordenada, ordenada creciente o decreciente.
2. **Selección automática:**
   - Si los datos están **desordenados**, ejecuta **Búsqueda Lineal** ($O(n)$), dado que no cumple la precondición de la binaria.
   - Si los datos están **ordenados**, ejecuta **Búsqueda Binaria** ($O(\log n)$), aprovechando la técnica de *divide y vencerás*.
3. **Manejo integral de duplicados:** Emplea variantes de *Lower Bound* y *Upper Bound* para localizar el primer índice, el último índice y el rango total de ocurrencias en $O(\log n)$.
4. **Métricas de rendimiento:** Registra comparaciones exactas y tiempos de cómputo en nanosegundos para justificar empíricamente la superioridad de $O(\log n)$ frente a $O(n)$ en grandes volúmenes de datos.

---

## 2. Compilación y Ejecución

### Compilación:
```bash
javac *.java
```

### Ejecución:
```bash
java Main
```
