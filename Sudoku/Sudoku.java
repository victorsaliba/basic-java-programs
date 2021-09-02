public class Sudoku {



	static int[] board = new int[81]; // vetor que representa o tabuleiro de sudoku
	static int total = 0; // guarda a quantidade de soluções
	static boolean verbose = false; // determina o modo de execução do programa
	static boolean validInstance = false; // determina se a instância é válida ou não



	/* INICIALIZA O TABULEIRO: guarda as informações iniciais em um
	vetor de strings (temporary_board), traduz essas informações 
	para valores inteiros (no lugar de "." coloca-se 0) e as coloca
	no vetor "board" */
	//=============================================================
	public static void initializeBoard() {

		String[] temporary_board = new String[81];

		while (!StdIn.isEmpty()) {
			for (int i = 0; i < 81; i++) {
				temporary_board[i] = StdIn.readString();
			}
		}

		initializeBoard(temporary_board);
	}

	public static void initializeBoard(String[] temporary_board) {

		for (int i = 0; i < 81; i++) {
			if (temporary_board[i].equals(".")) {
				board[i] = 0;
			}
			else {
				board[i] = Integer.parseInt(temporary_board[i]);
			}
		}
	}
	//=============================================================



	// IMPRIME O TABULEIRO
	//=======================================================
	public static void printBoard() {

		/* laço for que imprime nove "casas" do tabuleiro
		e pula uma linha a fim de montar um tabuleiro 9x9 */
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				StdOut.print(board[9*i+j] + " "); 
			}
			StdOut.println();
		}
		StdOut.println();
    }
    //=======================================================
    


    // MÉTODOS QUE VERIFICAM SE NÃO HÁ CONFLITO NAS LINHAS, COLUNAS E "CAIXAS" 3x3
    //==============================================================================
    public static boolean sameRow(int k) {

    	/* o número "9*q" determina o endereço do primeiro número 
    	da linha do tabuleiro em que está o elemento "board[k]" */
    	int q = k/9;

    	for (int i = 9*q; i < (9*q+9); i++) {
    		if (i != k) {
				if (board[i] == board[k]) return true;
    		}
    	}

    	return false;
    }

    public static boolean sameColumn(int k) {
    	
    	/* o número "q" determina o endereço do primeiro número 
    	da coluna do tabuleiro em que está o elemento "board[k]" */
    	int q = k%9;

    	int i = q;
    	while (i <= (q+72)) {
    		if (i != k) {
    			if (board[i] == board[k]) return true;
    		}
    		i = i + 9; // pula 9 "casas" para analisar o próximo elemento da coluna
    	}

    	return false;
    }
    
    public static boolean sameBox(int k) {
    	
    	/* Neste método, nos orientamos pelos números (int)(k/3) e k%9 para
    	encontrarmos a caixa a qual o número "board[k]" está contido
    	Considere os seguintes endereços (1,2,...,8) das "caixas 3x3" do
    	tabuleiro de sudoku:
			 3			 3			 3
		_____1_____|_____2_____|_____3_____|3
		_____4_____|_____5_____|_____6_____|3
		_____7_____|_____8_____|_____9_____|3

		*/
    	int q = 0;

    	if ((0 <= (k/3)) && ((k/3) <= 8)) {
    		if ((0 <= (k%9)) && ((k%9) <= 2)) {
    			q = 0; // endereço do primeiro número contido na caixa 1
    		}
    		else if ((3 <= (k%9)) && ((k%9) <= 5)) {
    			q = 3; // endereço do primeiro número contido na caixa 2
    		}
    		else if ((6 <= (k%9)) && ((k%9) <= 8)) {
    			q = 6; // endereço do primeiro número contido na caixa 3
    		}
    	}
    	else if ((9 <= (k/3)) && ((k/3) <= 17)) {
    		if ((0 <= (k%9)) && ((k%9) <= 2)) {
    			q = 27; // endereço do primeiro número contido na caixa 4
    		}
    		else if ((3 <= (k%9)) && ((k%9) <= 5)) {
    			q = 30; // endereço do primeiro número contido na caixa 5
    		} 
    		else if ((6 <= (k%9)) && ((k%9) <= 8)) {
    			q = 33; // endereço do primeiro número contido na caixa 6
    		}
    	}
    	else if ((18 <= (k/3)) && ((k/3) <= 26)) {
    		if ((0 <= (k%9)) && ((k%9) <= 2)) {
    			q = 54; // endereço do primeiro número contido na caixa 7
    		}
    		else if ((3 <= (k%9)) && ((k%9) <= 5)) {
    			q = 57; // endereço do primeiro número contido na caixa 8
    		}
    		else if ((6 <= (k%9)) && ((k%9) <= 8)) {
    			q = 60; // endereço do primeiro número contido na caixa 9
    		}
    	}
		
		// laço for que compara os números da "caixa 3x3" com o número board[k]
		for (int i = 0; i < 3; i++) {
			for (int j = q; j < (q+3); j++) {
    			if ((9*i+j) != k) {
    				if (board[9*i+j] == board[k]) return true;
    			}
    		}
    	}

    	return false;
    }
    //==============================================================================



    // VERIFICA A INSTÂNCIA
    //================================================
    public static void verifyInstance(int k) {

    	if (k == 81) {
    		validInstance = true;
    		return;
    	}
    	/* se os números != 0 satisfazem as regras do 
    	sudoku, então a instância é 
    	válida (validInstance = true)*/
		if (board[k] != 0) {
			if (isConsistent(k)) verifyInstance(k+1);
	    	else {
	    		validInstance = false;
	    	}
		}
		else verifyInstance(k+1);
    }
    //================================================


	
	// MÉTODOS QUE RESOLVEM O SUDOKU
	//=============================================================================================
    public static boolean isConsistent(int k) {

    	if (sameRow(k)) return false; // verifica se haverá dois números iguais na mesma linha

    	if (sameColumn(k)) return false; // verifica se haverá dois números iguais na mesma coluna

    	if (sameBox(k)) return false; // verifica se haverá dois números iguais na mesma caixa 3x3

    	return true;
    }

	public static void solve(int k) {
		
		if (k == 81) {
			total++; // adiciona mais uma solução ao total de soluções
			// *Se verbose = true, não imprime o tabuleiro
			if (!verbose) {
				printBoard();
			}
			
	    	return;
		}

		// Se a "casa" já estiver preenchida com um número, analise a próxima casa
		if (board[k] != 0) {
			solve(k+1);
			return;
		}
		
		/* Se colocarmos o número 1 (r={1,2,...,9}) na "casa" k, será válido colocar tal número
		nessa casa? Se não, analise o próximo número da lista de r, Se sim, analise a próxima
		"casa" */
		for (int r = 1; r <= 9; r++) {
	    	board[k] = r;
	    	if (isConsistent(k)) {
	    		solve(k+1);
	    	}
		}

		board[k] = 0; // Desfaz as alterações anteriores para uma próxima análise
    }
    //=============================================================================================



	public static void main(String[] args) {

		verbose = args.length > 0; // Se houver um argumento a mais, não imprima o tabuleiro (*veja método "solve")
		initializeBoard();

		verifyInstance(0);
		if (validInstance) {
			solve(0);
			StdOut.print(total + " solution(s)");
			StdOut.println();
		}
		else {
			StdOut.println("Invalid Instance");
			StdOut.println();
		}
	}
}