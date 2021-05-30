public class No {
    public No anterior;
    public No proximo;
    public No esquerdo;
    public No direito;
    public int caracter;
    public int frequencia;

    public No(int dado, int frequencia) {
        this.anterior = null;
        this.proximo = null;
        this.esquerdo = null;
        this.direito = null;
        this.caracter = dado;
        this.frequencia = frequencia;
    }
}
