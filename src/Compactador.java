import java.io.*;

public class Compactador {
    public static void main(String[] args) throws IOException {

        String pathIn = "src/entrada.txt";
        String pathOut = "src/saida.txt";
        BufferedReader fileIn = new BufferedReader(new FileReader(pathIn));
        //Scanner fileIn = new Scanner(new FileReader(pathIn)).useDelimiter("\\n");
        BufferedWriter fileOut = new BufferedWriter(new FileWriter(pathOut));
        Huffman huffman = new Huffman();

        int[] ascciTable = new int[256];
        String[] codeTable = new String[256];

        int indiceAscci;
        String line, code, text = "";

        for (int i = 0; i <= 255; i++) {
            ascciTable[i] = 0;
        }

        while (fileIn.ready()) {
            line = fileIn.readLine();
            text += line;
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

        huffman.listQueue();
        huffman.buildTree();

        for (int i = 0; i < 255; i++) {
            if (ascciTable[i] != 0) {
                char c = (char) i;
                code = huffman.buildBinaryCode(c, huffman.primeiro);
                codeTable[c] = code;
            }
        }

        String out = "";
        System.out.println(text);
        char[] texto = text.toCharArray();
        for (char c : texto) {
            out += codeTable[c];
        }
        fileOut.write(out);
        fileOut.close();
    }
}
// escrever \n ou writeLine