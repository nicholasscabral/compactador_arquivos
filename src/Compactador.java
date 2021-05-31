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
        String path = "C:/Users/nicho/OneDrive/Documentos/Programacao/projetos/compactador_arquivos/src/dados.txt";
        Scanner file = new Scanner(new FileReader(path)).useDelimiter("\\n");

        FilaPrioridadeOrdenada fila = new FilaPrioridadeOrdenada();

        String line = file.next();
        char[] caracteres = line.toCharArray();

        for (char c : caracteres) {
            int frequencia = count(c, line);

            fila.enqueue(c, frequencia);
        }

        fila.list();
    }
}
