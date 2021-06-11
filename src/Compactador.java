import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Compactador {
    public static void main(String[] args) throws FileNotFoundException {

        String path = "src/dados.txt";
        Scanner file = new Scanner(new FileReader(path)).useDelimiter("\\n");

        Huffman huffman = new Huffman();

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
                huffman.enqueue(c, ascciTable[indiceAscci]);
            }
        }

        while (huffman.primeiro != huffman.ultimo){
            No left = huffman.dequeue();
            No right = huffman.dequeue();

            No root = new No(right, left);
            huffman.enqueue(root);
        }

        for (int i = 0; i < 255; i++) {
            if (ascciTable[i] != 0) {
                char c = (char) i;
                String code = huffman.buildBinaryCode(c, huffman.primeiro);
                codeTable[c] = code;
            }
        }
    }
}
