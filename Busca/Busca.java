public class Busca { 
	
    // Recebe um número de matrícula (args[0]) e um arquivo de texto
    // com números de matrícula e nomes de estudantes
    public static void main(String args[]) {

    	int matricula = Integer.parseInt(args[0]);	// Número de matrícula que se deseja encontrar
    	
    	int length = StdIn.readInt();	// Lê o número de matrículas no arquivo.txt e o guarda na variável "length"
    	int x = StdIn.readInt();	// Lê o número x para o qual listaremos todas as matrículas maiores que x e o guarda na variável "x"

    	// Cria as listas que irão guardar o número de matrícula dos estudantes ("matriculas") e o nome dos estudantes ("nomes")
    	int[] matriculas = new int[length];
    	String[] nomes = new String[length];

    	// Lê os números das matrículas no arquivo.txt e os guarda na lista "matriculas"
    	// Lê os nomes dos estudantes no arquivo.txt e os guarda na lista "nomes"
    	int i = 0;
		while (!StdIn.isEmpty()) {
			matriculas[i] = StdIn.readInt();
			nomes[i] = StdIn.readString();
			i = i + 1;
		}

		// Imprime a saída no formato desejado
		StdOut.println("#########################################################################");
		int index1 = BuscasArray.buscaBinariaPrint(matriculas, 0, 0, matriculas.length-1, matricula);	// Guarda o índice da matrícula "matricula" na lista de matrículas encontrado pela busca binária na variável index1
		int index2 = BuscasArray.buscaSequencialPrint(matriculas, 0, 0, matricula);	// Guarda o índice da matrícula "matricula" na lista de matrículas encontrado pela busca sequencial na variável index2
		StdOut.println("#########################################################################");

		StdOut.println();
		StdOut.println();
		StdOut.println();
		StdOut.println("#########################################################");
		StdOut.printf("Matrícula %d encontrada na posição %d\n", matricula, index1);	// Imprime o índice da matrícula "matricula"
		StdOut.println("Nome: " + nomes[index1]);	// Imprime o nome referente à matrícula "matricula"
		StdOut.println("#########################################################");
		StdOut.println();
		StdOut.println();
		StdOut.println();

		// Imprime as matrículas maiores que x
		PrintArray.listaMatriculas(matriculas, x);
    } 
} 
