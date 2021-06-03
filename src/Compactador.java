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
    }
}
