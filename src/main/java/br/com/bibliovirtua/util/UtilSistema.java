package br.com.bibliovirtua.util;

import java.util.Arrays;
import java.util.Random;

/** Classe responsavel por implementar metodos de procura em Coleções.
 * 
 * @author Samantha Silva
 *
 */
public class UtilSistema {

	public static Long gerarNumero(int qntDigitos){
		StringBuilder codigo = new StringBuilder();
        Random random = new Random();

        for(int contador = 0; contador < qntDigitos; contador++){
            codigo.append(random.nextInt(9));
        }
        
        return Long.parseLong(codigo.toString());
	}

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String capitalize(String s) {
        return String.valueOf(s.charAt(0)).toUpperCase().concat(s.substring(1));
    }

    public static String gerarToken() {
        int numCaracteres = 8;

        StringBuilder senha = new StringBuilder(numCaracteres);

        for (int i = 0; i < numCaracteres; i++) {

		
            int escolha = (int) (Math.random() * 10);

            int codeCarac = 0;

            if (escolha < 3) {
				 
                codeCarac = 48 + (int) (Math.random() * 10);

            } else if (escolha < 6) {
			
                codeCarac = 65 + (int) (Math.random() * 25);

            } else {
			
                codeCarac = 97 + (int) (Math.random() * 25);
            }

            if (Arrays.asList(new Integer[]{48, 49, 73, 76, 79, 105, 108, 111}).contains(codeCarac)) {
                codeCarac += 2;
            }
            StringBuilder append = senha.append(String.valueOf((char) codeCarac));
        }

        return senha.toString();
    }
	
}
