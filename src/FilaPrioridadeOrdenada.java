public class FilaPrioridadeOrdenada {
    public No primeiro;
    public No ultimo;
    public int contador;

    public FilaPrioridadeOrdenada() {
        primeiro = null;
        ultimo = null;
        contador = 0;
    }

    public void enqueue(char letra, int frequencia) {
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

    public void enqueue(No root) {
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

    public No dequeue() {
        if (primeiro != null) {
            No aux = primeiro;
            primeiro = primeiro.proximo;
            contador--;
            return aux;
        }
        return null;
    }

    public No front() {
        if (primeiro != null) {
            return primeiro;
        }
        return null;
    }

    public boolean contains(char letra) {
        No aux = primeiro;

        while (aux != null) {
            if (letra == aux.caracter)
                return true;
            aux = aux.proximo;
        }

        return false;
    }

    public int size() {
        return contador;
    }

    public void list() {
        this.list(primeiro);
        System.out.println();
    }

    private void list(No root) {
        if (root.esquerdo != null)
            this.list(root.esquerdo);

        System.out.print(root.caracter + "" + root.frequencia + " "); // ORDEM

        if (root.direito != null) {
            this.list(root.direito);
        }
    }
}