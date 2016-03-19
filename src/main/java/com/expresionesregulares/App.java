package com.expresionesregulares;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) {
        String input = "www.pepë@mail.com";

        // comprueba que no empieze por punto o @

        /*
         * El caracter '\' sirve preceder a expresiones con valores de escape, así \\ equivale a \ o \{ equivale a {
         * El caracter '^' indica comienzo de una línea
         * El caracter '|' representa un O lógico
         */
        Pattern p = Pattern.compile("^\\.|^\\@");
        Matcher m = p.matcher(input);
        if (m.find()) {
            System.err.println("Las direcciones email no empiezan por punto o @");
        }

        //comprueba que no empieze por www.
        p = Pattern.compile("^www\\.");
        m = p.matcher(input);
        if (m.find()) {
            System.out.println("Los emails no empiezan por www");
        }

        // comprueba que contenga @
        p = Pattern.compile("\\@");
        m = p.matcher(input);
        if (!m.find()) {
            System.out.println("La cadena no tiene arroba");
        }

        //Comprueba que no contenga caracteres prohibidos   
        /*
         * El caracter '+' representa una o más veces
         * El caracter '^' dentro de los corchetes es un NOT, permite encontrar cualquier carácter que NO se encuentre dentro del grupo indicado
         */
        p = Pattern.compile("[^A-Za-z0-9\\.\\@_\\-~#]+");
        m = p.matcher(input);
        StringBuffer sb = new StringBuffer();
        boolean resultado = m.find();
        boolean caracteresIlegales = false;
        while (resultado) {
            caracteresIlegales = true;
            m.appendReplacement(sb, "");
            resultado = m.find();
        }

        // Añade el ultimo segmento de la entrada a la cadena
        m.appendTail(sb);

        input = sb.toString();
        if (caracteresIlegales) {
            System.out.println("La cadena contenía caracteres ilegales que han sido suprimidos");
        }
        System.out.println("Email: " + input);
    }
}
/*
 Lógicos:

 x|y: x o y.
 xy: x seguido de y
 Intervalos de caracteres:

 [abc]: Cualquiera de los caracteres entre corchetes. Pueden especificarse rangos, por ejemplo, [a-d] que equivale a [abcd]).
 [^abc]: Cualquier carácter que no esté los que están corchetes.
 Intervalos de caracteres predefinidos:

 .: Cualquier carácter individual, salvo el de salto de línea.
 \d: Cualquier carácter de dígito, equivalente a [0-9].
 \D: Cualquier carácter que no sea de dígito, equivale a [^0-9].
 \s: Cualquier carácter individual de espacio en blanco (espacios, tabulaciones, saltos de página o saltos de línea).
 \S: Cualquier carácter individual que no sea un espacio en blanco.
 \w: Cualquier carácter alfanumérico, incluido el de subrayado, equivalente a [A-Za-z0-9_].
 \W: Cualquier carácter que no sea alfanumérico, equivalente a [^A-Za-z0-9_].
 Caracteres:

 \f: Salto de página.
 \n: Salto de línea.
 \r: Retorno de carro.
 \t: Tabulación.
 Limites:

 ^: Principio de entrada o línea.
 $: Fin de entrada o línea.
 \b: Límite de palabra (como un espacio o un retorno de carro)
 \B: Fin de palabra.
 Cuantificadores:

 {n}: Exactamente n apariciones del carácter anterior.
 {n,m}: Como mínimo n y como máximo m apariciones del carácter anterior.
 *: El carácter anterior 0 o más veces.
 +: El carácter anterior 1 o más veces.
 ?: El carácter anterior una vez como máximo (es decir, indica que el carácter anterior es opcional). */
