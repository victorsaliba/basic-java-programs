public class BuscasArray {

    //=====================================================================================================================
    //Busca binária recursiva em arr
    //Chamada inicial: buscaBinaria(arr, 0, arr.length, x)
    public static int buscaBinaria (int[] arr, int l, int r, int x) {
	   
       int index = PrintArray.maiorProximo(arr, l, r, x);  // Guarda o índice de x na variável "index"
       return index;
    }
    //=====================================================================================================================






    //=====================================================================================================================
    //Busca binária recursiva em arr
    //Chamada inicial: buscaBinariaPrint(arr, 0, 0, arr.length, x)
    //Imprime a quantidade de comparações realizadas pela busca
    public static int buscaBinariaPrint (int[] arr, int chamadas, int l, int r, int x) {

        chamadas++;

        // Guarda a média entre os índices "l" e "r" na variável "index"
        int index = (l + r)/2;

        // Verifica se o valor x é menor ou igual ao menor elemento da lista
        if (x <= arr[0]) {
            StdOut.printf("Busca binária realizou um total de %d chamadas recursivas\n", chamadas);
            return 0;
        }

        // Caso o intervalo contenha apenas 1 elemento, este elemento será igual a x e, portanto, x está na lista
        if (arr[index] == x) {
            StdOut.printf("Busca binária realizou um total de %d chamadas recursivas\n", chamadas);
            return index;   // Retorna o índice de x no caso em que x pertence ao intervalo
        }

        // Caso o intervalo contenha dois elementos, x não está na lista e o método irá retornar o índice do maior elemento
        if (l == (r-1)) {
            StdOut.printf("Busca binária realizou um total de %d chamadas recursivas\n", chamadas);
            return r;   // Retorna o índice do menor elemento maior que x (arr[r])
        }

        /*
        ESTRATÉGIA RECURSIVA:
        Reduzir o intervalo de índices "[l,r]" até o intervalo conter apenas 1 elemento (Neste caso: arr[l] = x = arr[r]) 
        ou 2 elementos (Neste caso: arr[l] < x < arr[r]).
        */
        if (arr[index] > x) r = index;

        if (arr[index] < x) l = index;

        return buscaBinariaPrint(arr, chamadas, l, r, x);  // Laço recursivo
    }
    //=====================================================================================================================






    //=====================================================================================================================
    //Busca sequencial recursiva em arr
    //Chamada inicial: buscaSequencial(arr, 0, x)
    public static int buscaSequencial (int[] arr, int n, int x) {

        // ESTRATÉGIA RECURSIVA: percorrer a lista elemento por elemento até encontrar o elemento x na lista
        if (arr[n] == x) return n;

        return buscaSequencial(arr, n+1, x);    // Laço recursivo
    }
    //=====================================================================================================================






    //=====================================================================================================================
    //Busca sequencial recursiva em arr
    //Chamada inicial: buscaSequencialPrint(arr, 0, 0, x)
    //Imprime a quantidade de comparações realizadas pela busca
    public static int buscaSequencialPrint (int[] arr, int chamadas, int n, int x) {

        chamadas++;

        // ESTRATÉGIA RECURSIVA: percorrer a lista elemento por elemento até encontrar o elemento x na lista
        if (arr[n] == x) {
            StdOut.printf("Busca sequencial realizou um total de %d chamadas recursivas\n", chamadas);
            return n;            
        }

        return buscaSequencialPrint(arr, chamadas, n+1, x); // Laço recursivo
    }
    //=====================================================================================================================


    



    public static void main(String args[]) {
        int[] arr = {2, 5, 6, 7, 20, 35, 40, 43, 50, 98, 100};
        int x = 50;
        int n = arr.length;

        int result;

        result = buscaBinaria(arr, 0, arr.length, x);  
        StdOut.println("BB: Encontrado no índice " + result);
        StdOut.println();
	
        result = buscaBinariaPrint(arr, 0, 0, arr.length, x);
        StdOut.println("BBP: Encontrado no índice " + result);
        StdOut.println();
        
        result = buscaSequencial(arr, 0, x);
        StdOut.println("BS: Encontrado no índice " + result);
        StdOut.println();
        
        result = buscaSequencialPrint(arr, 0, 0, x);
        StdOut.println("BSP: Encontrado no índice " + result);
        StdOut.println();
    }
}
