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

        int indiceAscci;
        No left, right, root;
        String line, code;

        for (int i = 0; i <= 255; i++) {
            ascciTable[i] = 0;
        }

        while (file.hasNext()) {
            line = file.next();
            char[] caracteres = line.toCharArray();

            for (char c : caracteres) {
                indiceAscci = c;
                ascciTable[indiceAscci]++;
            }

            for (char c : caracteres) {
                indiceAscci = c;
                huffman.enqueue(c, ascciTable[indiceAscci]);
            }
        }

        while (huffman.primeiro != huffman.ultimo){
            left = huffman.dequeue();
            right = huffman.dequeue();

            root = new No(right, left);
            huffman.enqueue(root);
        }

        for (int i = 0; i < 255; i++) {
            if (ascciTable[i] != 0) {
                char c = (char) i;
                code = huffman.buildBinaryCode(c, huffman.primeiro);
                codeTable[c] = code;
            }
        }
    }
}
