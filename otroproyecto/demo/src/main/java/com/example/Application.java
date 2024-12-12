package com.example;

import java.util.Scanner;

public class Application {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Ingrese la calificación");

        int calificacion = Integer.valueOf(sc.nextLine());

        System.out.println(obtenerNota(calificacion));

    }

    public static String obtenerNota(int calificacion) {
        if (calificacion >= 90 && calificacion <= 100) {
            return "A";
        } else if (calificacion >= 80 && calificacion <= 89) {
            return "B";
        } else if (calificacion >= 70 && calificacion <= 79) {
            return "C";
        } else if (calificacion >= 60 && calificacion <= 69) {
            return "D";
        } else {
            return "F";
        }
    }
}
