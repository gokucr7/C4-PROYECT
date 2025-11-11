package Clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public final class AlumnoEstadisticas {

    private AlumnoEstadisticas() {
    }

    public static double calcularMedia(List<Double> datos) {
        validarDatos(datos);
        return datos.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public static double calcularModa(List<Double> datos) {
        validarDatos(datos);
        Map<Double, Long> frecuencias = datos.stream()
                .collect(Collectors.groupingBy(Double::doubleValue, Collectors.counting()));
        long maximaFrecuencia = Collections.max(frecuencias.values());
        return frecuencias.entrySet().stream()
                .filter(e -> e.getValue() == maximaFrecuencia)
                .map(Map.Entry::getKey)
                .sorted(Comparator.naturalOrder())
                .findFirst()
                .orElse(0.0);
    }

    public static double calcularMediana(List<Double> datos) {
        validarDatos(datos);
        List<Double> copia = new ArrayList<>(datos);
        Collections.sort(copia);
        int n = copia.size();
        if (n % 2 == 0) {
            return (copia.get(n / 2 - 1) + copia.get(n / 2)) / 2.0;
        }
        return copia.get(n / 2);
    }

    public static double calcularCuartil1(List<Double> datos) {
        return calcularCuartil(datos, 0.25);
    }

    public static double calcularCuartil3(List<Double> datos) {
        return calcularCuartil(datos, 0.75);
    }

    public static double calcularDesviacionMedia(List<Double> datos) {
        validarDatos(datos);
        double media = calcularMedia(datos);
        double suma = datos.stream().mapToDouble(d -> Math.abs(d - media)).sum();
        return suma / datos.size();
    }

    public static double calcularVarianza(List<Double> datos) {
        validarDatos(datos);
        DescriptiveStatistics stats = new DescriptiveStatistics();
        datos.forEach(stats::addValue);
        return stats.getVariance();
    }

    public static double calcularDesviacionTipica(List<Double> datos) {
        return Math.sqrt(calcularVarianza(datos));
    }

    public static double calcularCoeficienteVariacion(List<Double> datos) {
        double media = calcularMedia(datos);
        if (media == 0.0) {
            return 0.0;
        }
        double desviacionTipica = calcularDesviacionTipica(datos);
        return (desviacionTipica / media) * 100.0;
    }

    public static double calcularAsimetria(List<Double> datos) {
        validarDatos(datos);
        DescriptiveStatistics stats = new DescriptiveStatistics();
        datos.forEach(stats::addValue);
        return stats.getSkewness();
    }

    public static double calcularCurtosis(List<Double> datos) {
        validarDatos(datos);
        DescriptiveStatistics stats = new DescriptiveStatistics();
        datos.forEach(stats::addValue);
        return stats.getKurtosis();
    }

    private static double calcularCuartil(List<Double> datos, double porcentaje) {
        validarDatos(datos);
        List<Double> copia = new ArrayList<>(datos);
        Collections.sort(copia);
        double posicion = porcentaje * (copia.size() - 1);
        int inferior = (int) Math.floor(posicion);
        int superior = (int) Math.ceil(posicion);
        if (inferior == superior) {
            return copia.get(inferior);
        }
        double fraccion = posicion - inferior;
        return copia.get(inferior) + (copia.get(superior) - copia.get(inferior)) * fraccion;
    }

    private static void validarDatos(List<Double> datos) {
        Objects.requireNonNull(datos, "datos");
        if (datos.isEmpty()) {
            throw new IllegalArgumentException("La lista de datos no puede estar vacía");
        }
    }
}
