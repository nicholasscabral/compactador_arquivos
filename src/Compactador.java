import java.io.*;
import java.util.Scanner;

public class Compactador {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Huffman huffman = new Huffman();
        String pathIn;

        System.out.println("Qual operação deseja realizar? \n" +
                "1 - Compactar arquivo \n" +
                "2 - Descompactar arquivo \n" +
                "3 - Sair");

        int operation = input.nextInt();
        switch (operation) {
            case 1: {
                System.out.println("insira o caminho do arquivo a ser compactado");
                pathIn = input.next();
                huffman.compress(pathIn);
                break;
            }
            case 2: {
                System.out.println("insira o caminho do arquivo a ser descompactado");
                pathIn = input.next();
                huffman.expand(pathIn);
                break;
            }
            case 3: break;
        }
    }
}
// escrever \n ou writeLine