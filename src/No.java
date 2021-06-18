public class No {
    public No anterior;
    public No proximo;
    public No esquerdo;
    public No direito;
    public char caracter;
    public int frequencia;
    public boolean isLeaf;

    public No(char dado, int frequencia) {
        this.anterior = null;
        this.proximo = null;
        this.esquerdo = null;
        this.direito = null;
        this.caracter = dado;
        this.frequencia = frequencia;
    }

    public No(No direito, No esquerdo) {
        this.esquerdo = esquerdo;
        this.direito = direito;
        this.frequencia = esquerdo.frequencia + direito.frequencia;
    }

    public No() {
        this.anterior = null;
        this.proximo = null;
        this.esquerdo = null;
        this.direito = null;
    }

    public boolean isLeaf() {
        if (direito == null && esquerdo == null)
            return true;
        return false;
    }
}
