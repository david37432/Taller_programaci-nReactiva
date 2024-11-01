// TemperatureReading.java
package com.reactiva;

// Clase que representa una lectura de temperatura
public class TemperatureReading {
    private final String location; // Ubicación de la lectura
    private final double temperatureCelsius; // Temperatura en grados Celsius

    // Constructor que inicializa la ubicación y la temperatura
    public TemperatureReading(String location, double temperatureCelsius) {
        this.location = location; // Asigna la ubicación
        this.temperatureCelsius = temperatureCelsius; // Asigna la temperatura en Celsius
    }

    // Método para obtener la ubicación de la lectura
    public String getLocation() {
        return location; // Retorna la ubicación
    }

    // Método para obtener la temperatura en Celsius
    public double getTemperatureCelsius() {
        return temperatureCelsius; // Retorna la temperatura en Celsius
    }

    // Método para convertir la temperatura a Fahrenheit
    public double getTemperatureFahrenheit() {
        return (temperatureCelsius * 9 / 5) + 32; // Conversión de Celsius a Fahrenheit
    }
}
