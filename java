/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Richardo Rod Loa
 */
public class JavaApplication222 {
    record municipioAguaEscasa(String nombreSector, int totalPersonas, int diasAcceso, int inicioIntervalo, int finIntervalo){
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Crear un array para almacenar los datos de los municipios
        municipioAguaEscasa[] data = new municipioAguaEscasa[10];
        
        try {
            // Abrir el archivo de datos
            File file = new File("C:\\Users\\Richardo Rod Loa\\Documents\\NetBeansProjects\\JavaApplication2\\src\\javaapplication2\\datos.txt");
            Scanner fileScanner = new Scanner(file);
            
            // Leer datos desde el archivo y almacenarlos en el array
            int index = 0;
            while (fileScanner.hasNextLine() && index < data.length) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String nombreSector = parts[0];
                    int totalPersonas = Integer.parseInt(parts[1]);
                    int diasAcceso = Integer.parseInt(parts[2]);
                    int inicioIntervalo = Integer.parseInt(parts[3]);
                    int finIntervalo = Integer.parseInt(parts[4]);
                    data[index] = new municipioAguaEscasa(nombreSector, totalPersonas, diasAcceso, inicioIntervalo, finIntervalo);
                    index++;
                }
            }
            
            // Cerrar el escáner del archivo
            fileScanner.close();
        }
        
        catch (FileNotFoundException e) {
            System.err.println("No se pudo encontrar el archivo de datos. ");
            return;
        }
        
        for (municipioAguaEscasa municipio : data) {
            // Inicializar el contador de personas con acceso a agua limpia
            int personasAcceso = 0;
            
            // Calcular la duración del intervalo en horas y más
            int duracionIntervalo = municipio.finIntervalo - municipio.inicioIntervalo;
            int totalHoras = duracionIntervalo * municipio.diasAcceso;
            
            // Sumar la cantidad de personas con acceso en este intervalo
            for (int i = 1; i <= municipio.diasAcceso; i++) {
                System.out.print("Ingrese la cantidad de personas con acceso en el dia " + i + " en " + municipio.nombreSector + ": ");
                int personasIntervalo = scanner.nextInt();
                personasAcceso += personasIntervalo;
            }
            
            // Calcular el porcentaje de personas con acceso a agua limpia en una semana
            double porcentajeAcceso = (double) personasAcceso / (municipio.totalPersonas) * 100;
            
            // Mostrar el resultado
            System.out.println("El porcentaje de personas con acceso a agua limpia en una semana en " + municipio.nombreSector + " es del " + porcentajeAcceso + "%");
            System.out.println("El total de horas con acceso a agua limpia en una semana en " + municipio.nombreSector + " es de " + totalHoras + "h");
        }
        
        // Cerrar el escáner de la entrada estándar
        scanner.close();
        
        // TODO code application logic here
    }
    
}
