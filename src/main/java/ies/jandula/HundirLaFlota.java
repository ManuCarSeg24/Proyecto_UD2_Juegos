package ies.jandula;

import java.util.Scanner;

public class HundirLaFlota {
    public static void main(String[] args) {
        
        //NOTA: Todo lo que he realizado han sido pruebas y todas las variables son provisionales
        //Esto que he puesto aqui es una guia de como dividirnos el trabajo
        //Nada de esto es definitivo, puesto va a ir cambiando por si nos hace falta otros metodos
    
        // Crear los tableros de ambos jugadores (3x3)
        Scanner teclado = new Scanner(System.in);

        //Declaracion de variables

        char[][] taleroJugador1 = {{'A', 'A', 'A'}, {'A', 'A', 'A'}, {'A', 'A', 'A'}};
        char[][] taleroJugador2 = {{'A', 'A', 'A'}, {'A', 'A', 'A'}, {'A', 'A', 'A'}};

        System.out.println("¡Bienvenido jugadores al juego de hundir la flota!");
        System.out.println("Vamos a explicar brevemente las reglas del juego.");
        System.out.println("Cada jugador tendrá un tablero de 3x3 y dos barcos que colocará en secreto.");
        System.out.println("Cada barco ocupa dos lugares en el tablero.");
        System.out.println("Para colocarlos, debemos introducir la fila y la columna a la que queremos poner nuestro barco.");
        System.out.println("Las filas y las columnas estan formadas del 1 al 3.");
        System.out.println("Por ejemplo: mi barco esta colocado en la fila 1 columna 1 fila 2 columna 1");
        System.out.println("Una vez colocados, empezaremos a disparar al tablero del otro jugador.");
        System.out.println("Si no tocamos el barco, nos dirá que hemos dado en el Agua.");
        System.out.println("Si tocamos el barco, nos dirá Tocado y el barco estará dañado pero aun sigue participando.");
        System.out.println("Si tocamos por completo el barco, nos dira tocado y hundido, con esto eliminamos el barco.");
        System.out.println("Gana el jugador que hunda todos los barcos del jugador rival.");
        System.out.println("Aclarado todo esto, ¡Empezemos la partida!");

        //esctructura más bien orientativa.
    }
    //Apartir de aqui, trabajaremos con funciones (mientras hablamos si tenemos problemas entre nosotros), cada uno nos repartiremos las funciones.
    //Uno de nosotros (Alumno A) se dedicará a introducir los barcos, validar las posiciones, que puedan estar adyacentes y no se solapen.
    //El Alumno B se dedicara a las funciones de los disparos, de verificar si ha tocado, si ha dado agua o si ha tocado y hundido.
    //Y el Alumno A se dedicará a recoger ese resultado, finalizar el juego y pintar por pantalla las posiciones de los barcos delos jugadores en los tableros.
}
