package ies.jandula;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class CombinacionCorrecta {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        //Declaración de variables.
        int intentos = 20;
        boolean acertado = false;
        String intentoUsuario = " ";
        int posicionCorrecta = 0;
        String codigoCompa = " ";
        String codigoSecreto = " ";

        // Obtener el código secreto generado por mi compañero.
    
        codigoSecreto = generarCodigoSecretoPorDefecto(codigoCompa);

        // Mostrar mensaje de bienvenida, y una pequeña explicación del juego.
        System.out.println("¡Bienvenido al juego 'La combinación correcta'!");
        System.out.println("Debes adivinar un código de 4 dígitosm, puedes incluir 0-9, * y #.");
        System.out.println("¡Tienes 20 intentos!");


        // Creamos un bucle while para introducir al usuario en el juego mientras tenga intentos.
        //Este bucle será nuestra columna vertebral del ejercicio.
        while (intentos > 0) {
            //Mostramos los intentos y pedimos al usuario por pantalla que introduzca digitos.
            System.out.println("Intentos restantes: " + intentos);
            System.out.print("Introduce tu combinación: ");
            intentoUsuario = teclado.nextLine();

            // Con este if nos aseguramos que el usurario introduce 4 digitos. 
            if (intentoUsuario.length() != 4) {
                System.out.println("Por favor, introduce exactamente 4 caracteres.");
                continue; 
                //Introducimos el continue, para que salte el bucle y vuelva a comenzar desde el principio.
                //con esto evitamos errores y que nos resten oportunidades.
            }

           // Creamos un bucle for para comparar el codigo secreto con el introducido del usuario.
           for (int i = 0; i < 4; i++) {

                //Si la comparación es correcta utilizamos un int posicionCorrecta para contabilizar las psociones correctas.
               if (intentoUsuario.charAt(i) == codigoSecreto.charAt(i)) {
                   posicionCorrecta++; //Contabilzamos la posición correcta para despues mostrar por pantalla cuantos deellos estan en su posición.
               } 
               if (codigoSecreto.contains(String.valueOf(intentoUsuario.charAt(i)))) {
                    //Con este if le decimos al usuario por pantalla que ha introducido un digito correcto.
                    System.out.println("Dígito acertado: " + intentoUsuario.charAt(i));
               }
           }

           //Mostrmos por pantalla cuantos digitos estan en su posición correcta.
           //Nota: con esto solo decimos que alguno de los digitos estan en su posición pero no revelamos cual de ellos es.
           System.out.println("Dígitos en la posición correcta: " + posicionCorrecta);

           // Con este if verificamos si todos los digitos estan en su posición.
           if (posicionCorrecta == 4) {
                //Si estan en su posición entra, cambiamos el boolean acertado a true y utilizamos el break para romper el bucle.
               acertado = true;
               break;
           }

           //Si no aciertan los cuatro digitos, restamos intentos al usuario.
           intentos--;
           //devolvemos a 0 la posión correcta para que no se sumen las veces que se acierte un número.
           posicionCorrecta = 0;
       }

       // Resultado final.
       if (acertado) {
            //Si acierta los 4 digitos, el boolean acertado es true y lo manda dentro del if, mostrando el mensaje y el código secreto.
           System.out.println("¡Felicidades! Has adivinado el código secreto: " + codigoSecreto);
       } else {
            //Pero si el jugador se queda sin intentos, pierde y entra en el else porque el boolean acertado es false.
           System.out.println("¡Lo siento!, te has quedado sin intentos. El código secreto era: " + codigoSecreto);
           //Muestra por pantalla un mensaje y el código secreto.
       }    
   }

   public static String generarCodigoSecretoPorDefecto(String codigoCompa) {


       //La parte de pedir un código aleatorio es parte de mi compañero Alejandro.
       //Realizo una función para yo poder trabajar a partir del número que generará la parte de mi compañero.
       //Aqui pondré un código por defecto como por ejemplo 123# y comenzare a trabajar desde ese código.
       //Nota: mi compañero debe restringir los digitos introducidos, que el usuario solo pueda usar 0 a 9, * y #. 
       //Por eso mi codigo aún no esta acabado, tengo que verificarlo. Aún estoy pensando si hacer un nuevo if o introducirlo con el if que solo deja introudcir 4 dígitos.

       return "123#"; // Código por defecto
   }
}