package postorden;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        PostOrden ar = new PostOrden();
        int n = 0;
        int con = 1;
        System.out.println("Ingrese 10 numeros");
        for (int i = 0; i <= 10; i++) {
            System.out.printf("%d. ", con++);
            n = entrada.nextInt();
            if (n % 2 == 0) {
                try {
                    File file = new File("/Users/xavierchavez/Desktop/UTPL/Estructura "
                            + "de Datos/Semana3/EjerciciosPracticoExp/practica1.txt");
                    if (file.createNewFile()) {
                        System.out.println("Su archivo se ha creado con el nombre: "
                                + file.getName());
                    } else {
                        System.out.println("El archivo ya existe");
                    }
                    System.out.println("El archivo se llama: " + file.getName());
                    System.out.printf("La longitud del archivo es: %s bytes\n",
                            file.length());

                } catch (IOException e) {
                    System.out.println("Error al crear el archivo");
                }
            } else {
                ar.insertar(n);
            }
        }
        System.out.println("Elegir presentar numero impares\n"
                + "1. PostOrden\n2. PreOrden\n3. InOrden");
        int x = entrada.nextInt();

        switch (x) {
            case 1:
                if (ar.vacio()) {
                    System.out.println("El arbol esta Vacio");
                } else {
                    ar.imprimirPost();
                }
                break;
            case 2:
                if (ar.vacio()) {
                    System.out.println("El arbol esta Vacio");
                } else {
                    ar.imprimirPre();
                }
                break;
            case 3:
                if (ar.vacio()) {
                    System.out.println("El arbol esta vacio");
                } else {
                    ar.imprimirInOrden();
                }
                break;
        }
    }

}
