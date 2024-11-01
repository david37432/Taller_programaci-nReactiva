// Main.java
package com.reactiva;

import io.reactivex.Observable; // Importación de la clase Observable de RxJava
import java.util.Arrays; // Importación de la clase Arrays para facilitar la creación de listas
import java.util.List; // Importación de la interfaz List

public class Main {
    public static void main(String[] args) {
        // Lista de lecturas de temperatura de dos ubicaciones
        List<TemperatureReading> readings1 = Arrays.asList(
                new TemperatureReading("Location A", 22.0), // Lectura de temperatura para "Location A"
                new TemperatureReading("Location A", 25.0),
                new TemperatureReading("Location A", 18.0)
        );

        List<TemperatureReading> readings2 = Arrays.asList(
                new TemperatureReading("Location B", 30.0), // Lectura de temperatura para "Location B"
                new TemperatureReading("Location B", 27.0),
                new TemperatureReading("Location B", 35.0)
        );

        // Crear una instancia de TemperatureOperator que contendrá la lógica de procesamiento
        TemperatureOperator operator = new TemperatureOperator();

        // Procesar las temperaturas de las dos listas utilizando el método processTemperatures
        // Esto utiliza el operador merge (se combinan observables) en el método processTemperatures
        Observable<TemperatureReading> processedTemperatures = operator.processTemperatures(readings1, readings2).share();

        // Imprimir las temperaturas en Fahrenheit
        // Aquí se utiliza el operador map (convertir temperaturas de Celsius a Fahrenheit) en el método getTemperatureFahrenheit()
        processedTemperatures.subscribe(reading -> 
            System.out.println(reading.getLocation() + ": " + reading.getTemperatureFahrenheit() + "°F"));

        // Combinar las lecturas de temperatura procesadas con etiquetas
        // Aquí se utilizan los operadores zip (combinar resultados con etiquetas) en el método combineWithLabels
        operator.combineWithLabels(processedTemperatures)
                .subscribe(result -> System.out.println(result)); // Imprimir resultados combinados
    }
}
