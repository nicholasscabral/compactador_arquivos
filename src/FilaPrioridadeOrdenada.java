public class FilaPrioridadeOrdenada {
    public No primeiro;
    public No ultimo;
    public int contador;

    public FilaPrioridadeOrdenada() {
        primeiro = null;
        ultimo = null;
        contador = 0;
    }

    public void enqueue(int valor, int frequencia) {
        No novo = new No(valor, frequencia);

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

    public void dequeue() {
        if (primeiro != null) {
            primeiro = primeiro.proximo;
            contador--;
        }
    }

    public No front() {
        if (primeiro != null) {
            return primeiro;
        }
        return null;
    }

    public int size() {
        return contador;
    }

    public void list() {
        No aux = primeiro;

        while (aux != null) {
            System.out.print(aux.caracter + "(" + aux.frequencia + ") ");
            aux = aux.proximo;
        }
        System.out.println();
    }
}