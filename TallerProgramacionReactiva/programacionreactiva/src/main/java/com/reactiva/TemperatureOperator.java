// TemperatureOperator.java
package com.reactiva;

import io.reactivex.Observable; // Importación de la clase Observable de RxJava
import java.util.List; // Importación de la interfaz List

public class TemperatureOperator {

    // Método para procesar las lecturas de temperatura
    public Observable<TemperatureReading> processTemperatures(List<TemperatureReading> readings1, List<TemperatureReading> readings2) {
        // Combina las dos listas de lecturas en un solo flujo de datos
        return Observable.merge(
                Observable.fromIterable(readings1), // Convierte readings1 en un Observable
                Observable.fromIterable(readings2)  // Convierte readings2 en un Observable
        )
        // Filtra las lecturas para incluir solo aquellas que están en el rango de 20 a 30 grados Celsius
        .filter(reading -> reading.getTemperatureCelsius() >= 20 && reading.getTemperatureCelsius() <= 30) 
        // Transforma cada lectura a Fahrenheit al crear un nuevo objeto TemperatureReading
        .map(reading -> new TemperatureReading(reading.getLocation(), reading.getTemperatureFahrenheit())) 
        // Transforma cada lectura en dos: una con la temperatura original y otra con la temperatura aumentada en 1 grado Celsius
        .flatMap(reading -> Observable.just(reading, new TemperatureReading(reading.getLocation(), reading.getTemperatureCelsius() + 1))) 
        // Elimina las lecturas duplicadas basadas en la ubicación
        .distinct(reading -> reading.getLocation()); 
    }

    // Método para combinar lecturas con etiquetas
    public Observable<String> combineWithLabels(Observable<TemperatureReading> observable) {
        // Define las etiquetas como un Observable
        Observable<String> labels = Observable.just("Low", "Medium", "High");

        // Usa zip para combinar las lecturas de temperatura con las etiquetas
        return observable.zipWith(labels.repeat(observable.count().blockingGet()), // Repite las etiquetas según el número de lecturas
                (reading, label) -> reading.getLocation() + " is " + label + " with temperature: " + reading.getTemperatureCelsius());
    }
}
