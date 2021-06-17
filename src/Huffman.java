import java.io.*;

public class Huffman {
    private No primeiro, ultimo, left, right, root;
    private int contador;
    private int[] asciiTable;
    private String[] codeTable;
    private String code, line, fileOutPath = "src/saida.txt";
    private BufferedReader fileIn;
    private BufferedWriter fileOut = new BufferedWriter(new FileWriter(fileOutPath));
    private StringBuilder encodedBinaryChain = new StringBuilder();


    public Huffman() throws IOException {
        primeiro = null;
        ultimo = null;
        contador = 0;

        asciiTable = new int[256];
        codeTable = new String[256];

        for (int i = 0; i <= 255; i++) {
            asciiTable[i] = 0;
        }
    }

    private void enqueue(char letra, int frequencia) {
        if (!this.contains(letra)) {
            No novo = new No(letra, frequencia);

            if (primeiro == null) {
                primeiro = novo;
                ultimo = novo;
            }
            else if (novo.frequencia < primeiro.frequencia) {
                novo.proximo = primeiro;
                primeiro.anterior = novo;
                primeiro = novo;
            }
            else if (novo.frequencia >= ultimo.frequencia) {
                ultimo.proximo = novo;
                novo.anterior = ultimo;
                ultimo = novo;
            }
            else {
                No aux = primeiro;

                while (aux != null && novo.frequencia >= aux.frequencia) {
                    aux = aux.proximo;
                }

                novo.proximo = aux;
                novo.anterior = aux.anterior;
                aux.anterior = novo;
                novo.anterior.proximo = novo;

            }
            contador++;
        }
    }

    private void enqueue(No root) {
        if (primeiro == null) {
            primeiro = root;
            ultimo = root;
        }
        else if (root.frequencia < primeiro.frequencia) {
            root.proximo = primeiro;
            primeiro.anterior = root;
            primeiro = root;
        }
        else if (root.frequencia >= ultimo.frequencia) {
            ultimo.proximo = root;
            root.anterior = ultimo;
            ultimo = root;
        }
        else {
            No aux = primeiro;

            while (aux != null && root.frequencia >= aux.frequencia) {
                aux = aux.proximo;
            }

            root.proximo = aux;
            root.anterior = aux.anterior;
            aux.anterior = root;
            root.anterior.proximo = root;

        }
        contador++;
    }

    private No dequeue() {
        if (primeiro != null) {
            No aux = primeiro;
            primeiro = primeiro.proximo;
            contador--;
            return aux;
        }
        return null;
    }

    private boolean contains(char letra) {
        No aux = primeiro;

        while (aux != null) {
            if (letra == aux.caracter)
                return true;
            aux = aux.proximo;
        }

        return false;
    }

    private String encode(String text) {
        StringBuilder out = new StringBuilder();
        for (char c : text.toCharArray()) {
            out.append(codeTable[c]);
        }

        return out.toString();
    }

    // preenche a tabela de frequencias
    private void countFrequency(char[] caracteres) {
        for (char c : caracteres)
            asciiTable[c]++;

        for (char c : caracteres)
            this.enqueue(c, asciiTable[c]);
    }

    private void buildEncodedBinaryChain(No root) {
        if (root.isLeaf()) {
            encodedBinaryChain
                    .append("1")
                    .append(String.format("%8s", Integer.toBinaryString(root.caracter)).replace(' ', '0'));
        }

        encodedBinaryChain.append("0");
        if (root.esquerdo != null)
            buildEncodedBinaryChain(root.esquerdo);

        if (root.direito != null)
            buildEncodedBinaryChain(root.direito);
    }

    public void compress(String fileInPath) throws IOException {
        fileIn = new BufferedReader(new FileReader(fileInPath));

        StringBuilder lines = new StringBuilder();
        // lendo cada linha do arquvio e contando frequencia dos caracteres
        while (fileIn.ready()) {
            line = fileIn.readLine();
            lines.append(line);
            char[] caracteres = line.toCharArray();
            this.countFrequency(caracteres);
        }

        // construindo a arvore binaria baseada na frequencia
        this.buildTree();

        // construindo codigo binario de cada caracter e add na tabela de codigos
        for (int i = 0; i < 255; i++) {
            if (asciiTable[i] != 0) {
                char c = (char) i;
                code = this.buildBinaryCode(c, this.primeiro);
                codeTable[c] = code;
            }
        }

        String out = this.encode(lines.toString());
        fileOut.write(encodedBinaryChain.toString() + "\n");
        fileOut.write(out);
        fileOut.close();
    }

    public void expand(String fileInPath) throws FileNotFoundException {
        fileIn = new BufferedReader(new FileReader(fileInPath));
    }

    private void buildTree() {
        while (primeiro != ultimo){
            left = dequeue();
            right = dequeue();

            root = new No(right, left);
            enqueue(root);
        }
        this.buildEncodedBinaryChain(primeiro);
    }

    private String buildBinaryCode(char target, No root) {
        if(root.isLeaf()) {
            if(root.caracter == target)
                return "";
            else
                return null;
        }

        String aux;
        if((aux = buildBinaryCode(target, root.esquerdo)) != null)
            return '0' + aux;

        if((aux = buildBinaryCode(target, root.direito)) != null)
            return '1' + aux;

        return null;
    }

    public int size() {
        return contador;
    }

    private void listQueue() {
        No aux = primeiro;

        while (aux != null) {
            System.out.print(aux.caracter + "(" + aux.frequencia + ") ");
            aux = aux.proximo;
        }
        System.out.println();
    }

    public void listTree() {
        this.listTree(primeiro);
        System.out.println();
    }

    private void listTree(No root) {
        if (root.esquerdo != null)
            this.listTree(root.esquerdo);

        System.out.print(root.caracter + "" + root.frequencia + " "); // ORDEM

        if (root.direito != null) {
            this.listTree(root.direito);
        }
    }
}