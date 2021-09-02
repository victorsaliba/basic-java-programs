public class Threesum {

	//=================================================================
	public static void ordenacao(int[] vetor) {

		int n = vetor.length;

		//laço que percorre o vetor
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				/*se houver um elemento à direita menor que um elemento
				à direita menor que um elemento à esquerda, troque os
				dois elementos de lugar*/
				if (vetor[i] < vetor[j]) {
                    int aux = vetor[i];
                    vetor[i] = vetor[j];
                    vetor[j] = aux;
                }
            }
        }
	}
	//=================================================================



	//==========================================================================
	public static int countTrivial(int[] vetor) {

		int n = vetor.length;
        int count1 = 0;
        /*percorre o vetor 3 vezes a fim de verificar todas as triplas possíveis
        e encontrar aquelas que somam 0*/
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    if (vetor[i] + vetor[j] + vetor[k] == 0) {
                        count1++;
                    }
                }
            }
        }

        return count1;
	}
	//==========================================================================



	//MÉTODO countEficiente:
	/*IDEIA CENTRAL: Para cada elemento vetor[i], verifique as duplas (vetor[left], vetor[right])
	que, somadas, são iguais a -vetor[i], logo, a tripla (vetor[i], vetor[left], vetor[right]) irá
	somar 0.*/
	//============================================================================================
	public static int countEficiente(int[] vetor) {

		int n = vetor.length;
		int count2 = 0;
		/*A fim de tornar o método mais eficiente, ordenamos o vetor que contém os elementos
		em ordem crescente, dessa forma, teremos a seguinte execução...*/
		for (int i = 0; i < n; i++) {
      		int left = i + 1;
      		int right = vetor.length - 1;
      		while (left < right) {

      			/*Caso encontre uma dupla (vetor[left], vetor[right]) tal que
      			(vetor[i] + vetor[left] = -vetor[i]), então adicione 1 ao total
      			de triplas e continue a busca*/
        		if (vetor[i] + vetor[left] + vetor[right] == 0) {
          			count2++;
          			left++;
          			right--;
        		}

        		/*se (vetor[i] + vetor[left] < -vetor[i]), basta somar 1 à
				variável left para que a soma (vetor[i] + vetor[left]) aumente o seu valor*/
        		else if (vetor[i] + vetor[left] + vetor[right] < 0) left++;

        		/*caso (vetor[i] + vetor[left] > -vetor[i]), basta subtrair 1 à
				variável right para que a soma (vetor[i] + vetor[left]) diminua o seu valor*/
        		else right--;
      		}
    	}

    	return count2;
	}
	//============================================================================================



	public static void main(String[] args) {

		// GERA UM VETOR DE INTEIROS QUE NÃO POSSUI ELEMENTOS REPETIDOS
		//=============================================================
		int M = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);
        if (N > 2*M) N = 2*M;
        int[] vetor = new int[2*M];
        for (int i = -M; i < M; i++) {
            vetor[i+M] = i;
        }
        Generator.scramble(vetor);
 		//=============================================================


    	double startTime_1 = System.currentTimeMillis() / 1000.0; //começa a marcar o tempo
    	int count1 = countTrivial(vetor); 
		double stopTime_1 = System.currentTimeMillis() / 1000.0; //termina de marcar o tempo
		double deltaTime_1 = stopTime_1 - startTime_1; // calcula o tempo de execução do método countTrivial
		
		ordenacao(vetor); // ordena o vetor antes de executar o método countEficiente
		
		double startTime_2 = System.currentTimeMillis() / 1000.0; //começa a marcar o tempo
    	int count2 = countEficiente(vetor); // Guarda´a quantidade de triplas que somam 0 na variável count2
		double stopTime_2 = System.currentTimeMillis() / 1000.0; //termina de marcar o tempo
		double deltaTime_2 = stopTime_2 - startTime_2; // calcula o tempo de execução do método countEficiente
		
		double deltaTime = deltaTime_1 - deltaTime_2; // calcula a diferença entre os tempos de execução dos 2 métodos

		//Imprime as informações requeridas
		StdOut.printf("Quantidade de triplas encontradas pelo método countTrivial: %d\n", count1);
		StdOut.printf("Quantidade de triplas encontradas pelo método countEficiente: %d\n", count2);
		StdOut.printf("Diferença de tempo na execução dos dois métodos: %.0f segundos\n", deltaTime);

	}
}