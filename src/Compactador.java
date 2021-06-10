import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Compactador {
    public static int count(char letra, String texto) {
        int contador = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (letra == texto.charAt(i))
                contador++;
        }
        return contador;
    }

    public static void main(String[] args) throws FileNotFoundException {

        String path = "src/dados.txt";
        Scanner file = new Scanner(new FileReader(path)).useDelimiter("\\n");

        FilaPrioridadeOrdenada fila = new FilaPrioridadeOrdenada();

        int[] ascciTable = new int[256];
        String[] codeTable = new String[256];

        for (int i = 0; i <= 255; i++) {
            ascciTable[i] = 0;
        }

        while (file.hasNext()) {
            String line = file.next();
            char[] caracteres = line.toCharArray();

            for (char c : caracteres) {
                int indiceAscci = c;
                ascciTable[indiceAscci] += 1;
            }

            for (char c : caracteres) {
                int indiceAscci = c;
                fila.enqueue(c, ascciTable[indiceAscci]);
            }
        }

        System.out.println(ascciTable[97]); // a
        fila.list();

        while (fila.primeiro != fila.ultimo){
            No left = fila.dequeue();
            No right = fila.dequeue();

            No root = new No(right, left);
            fila.enqueue(root);
        }

        fila.list();
        System.out.println(fila.primeiro.frequencia + " essa eh a raiz pai");
        System.out.println(fila.primeiro.direito.frequencia + "" + fila.primeiro.direito.caracter + " filho direito");
        System.out.println(fila.primeiro.esquerdo.frequencia + "" + fila.primeiro.esquerdo.caracter + " filho esquerdo");
        System.out.println(fila.primeiro.direito.direito.frequencia + "" + fila.primeiro.direito.direito.caracter + " neto direito do " + fila.primeiro.frequencia);
        System.out.println(fila.primeiro.direito.esquerdo.frequencia + "" + fila.primeiro.direito.esquerdo.caracter + " neto esquerdo do " + fila.primeiro.frequencia);
        System.out.println(fila.primeiro.esquerdo.direito.frequencia + "" + fila.primeiro.esquerdo.direito.caracter + " neto esquerdo direito");

    }
}
