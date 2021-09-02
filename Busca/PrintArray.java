public class PrintArray{

    //=====================================================================================================================
    // Retorna o índice do menor elemento de arr que é maior ou igual a x
    public static int maiorProximo (int[] arr, int l, int r, int x) {
        
        // Guarda a média entre os índices "l" e "r" na variável "index"
        int index = (l + r)/2;

        // Verifica se o valor x é menor ou igual ao menor elemento da lista
        if (x <= arr[0]) return 0;

        // Caso o intervalo contenha apenas 1 elemento, este elemento será igual a x e, portanto, x está na lista
        if (arr[index] == x) return index;   // Retorna o índice de x no caso em que x pertence ao intervalo

        // Caso o intervalo contenha dois elementos, x não está na lista e o método irá retornar o índice do maior elemento
        if (l == (r-1)) return r;   // Retorna o índice do menor elemento maior que x (arr[r])

        /*
        ESTRATÉGIA RECURSIVA:
        Reduzir o intervalo de índices "[l,r]" até o intervalo conter apenas 1 elemento (Neste caso: arr[l] = x = arr[r]) 
        ou 2 elementos (Neste caso: arr[l] < x < arr[r]).
        */
        if (arr[index] > x) r = index;

        if (arr[index] < x) l = index;

        return maiorProximo(arr, l, r, x);  // Laço recursivo
    }
    //=====================================================================================================================






    //=====================================================================================================================
    // Lista todas as matrículas maiores que x
    public static void listaMatriculas(int[] arr, int x) {

        // Guarda a quantidade de elementos maiores que x na variável "length"
        int length = 0;
        length = arr.length - maiorProximo(arr, 0, arr.length-1, x);

        // Cria a lista "maiores" que irá conter os elementos maiores que x
        int[] maiores = new int[length];

        // Preenche a lista "maiores" com os elementos maiores que x
        int w = 0;
        for (int i = maiorProximo(arr, 0, arr.length-1, x); i < arr.length; i++) {
            if (arr[i] >= x) {
                maiores[w] = arr[i];
                w = w + 1;
            }
        }

        // Imprime as matrículas maiores que x
        for (int f = 0; f < maiores.length; f++) {
            StdOut.printf("Matrícula %d\n", maiores[f]);
        }
    }
    //=====================================================================================================================






    public static void main(String args[]) {
        int[] arr = {2, 5, 6, 7, 20, 35, 40, 43, 50, 98, 100};

        listaMatriculas(arr, 34);
        listaMatriculas(arr, 35);
    }
}
