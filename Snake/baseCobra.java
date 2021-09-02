
import java.awt.event.KeyEvent;

public class baseCobra {

	//=================================================================================================
	public static void desenhaParedes(int n){

		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(-1, n/2-1, 0.40, n/2+2);
		StdDraw.filledRectangle(n, n/2, 0.40, n/2+2);
		StdDraw.filledRectangle(n/2, -1, n/2+1, 0.40);
		StdDraw.filledRectangle(n/2, n, n/2+1, 0.40);
	}
	//=================================================================================================



	//=================================================================================================
	// Produz uma função de onda sonora
	public static double[] tone(double hz, double duration) {
		int N = (int) (44100 * duration);
		double[] a = new double[N+1];
		for (int i = 0; i <= N; i++)
    	    a[i] = Math.sin(2 * Math.PI * i * hz / 44100);
		return a;
    }
    //=================================================================================================



	//=================================================================================================
	// Método que atualiza posição  da fruta
	public static void fruta(int[] x, int[] y, int[] ci, int[] cj, int n){

		boolean in = false; // variável que verifica se a fruta está em cima da cobra

		/* Se a cabeça da cobrinha coincidir com a fruta OU Se a fruta estiver em cima de uma bolinha 
		que forma a cobrinha, então coloque uma fruta em um novo local do grid */
		for (int k = 0; k < 13; k++) {
			if (ci[k] == x[0] && cj[k] == y[0]) {
				in = true;
				break;
			}
		}
		
		if (in == true){
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledSquare(x[0], y[0], .55);
			double[] a = tone(440 * (Math.pow(2,(double)14/12)), 0.05);
	   		StdAudio.play(a); // Reproduz o som
			x[0] = (int)(Math.random()*n);
			y[0] = (int)(Math.random()*n);
			fruta(x, y, ci, cj, n);
		}
		// Se a cobra ainda não comeu a fruta, desenhe a fruta no mesmo local de antes
		else {
			StdDraw.picture(x[0], y[0], "apple.jpg", 1.15, 1.15);
			return;
		}
	}
	//=================================================================================================



	//=================================================================================================
	// Atualiza a posição das bolinhas que formam a cobrinha caso ela ande um passo em qualquer direção
	public static void atualizaPosicao(int[] ci, int[] cj){

		for (int k = 12; k > 0; k--) {
	    	ci[k] = ci[k-1];
	    	cj[k] = cj[k-1];
	    }
	}
	//=================================================================================================



	//=================================================================================================
	public static void desenhaCobrinha(int[] ci, int[] cj){
		
		StdDraw.setPenColor(0, 200, 0);
	    for (int k = 0; k < 13; k++) {
	    	StdDraw.filledCircle(ci[k], cj[k], .48);
	    }

	}
	//=================================================================================================

    

    //=================================================================================================
    public static void flow(int n, int[] ci, int[] cj, int[] x, int[] y) {

		// Espera para apertar a próxima tecla
		while (!StdDraw.hasNextKeyTyped());
		StdDraw.nextKeyTyped();
	
		if (StdDraw.isKeyPressed (KeyEvent.VK_W)){
	    	StdOut.println("Move - cima");

	    	// Se a cobrinha andar um passo para cima, ela vai passar por cima dela?
	    	// Se sim, execute o método flow novamente até que o jogador faça um movimento distinto
	    	for (int i = 1; i < 13; i++) {
				if (ci[0] == ci[i] && (cj[0]+1) == cj[i]) {
					flow(n, ci, cj, x, y);
				}
			}

			// Verifica se a cobrinha não vai ultrapassar o limite do grid
	    	if (cj[0] < n-1){

	    		// "Apaga" a última bolinha que forma a cobrinha para dar a impressão de movimento
	    		StdDraw.setPenColor(StdDraw.WHITE);
	    		StdDraw.filledSquare(ci[12], cj[12], .55);

	    		atualizaPosicao(ci, cj);

	    		// Atualiza a posição da cabeça da cobrinha
	    		cj[0] = cj[0] + 1;

	    		fruta(x, y, ci, cj, n);

	    		desenhaCobrinha(ci, cj);
	    	}
		}
		else if (StdDraw.isKeyPressed (KeyEvent.VK_S)){
	    	StdOut.println("Move - baixo");

	    	// Se a cobrinha andar um passo para baixo, ela vai passar por cima dela?
	    	// Se sim, execute o método flow novamente até que o jogador faça um movimento distinto
	    	for (int i = 1; i < 13; i++) {
				if (ci[0] == ci[i] && (cj[0]-1) == cj[i]) {
					flow(n, ci, cj, x, y);
				}
			}

			// Verifica se a cobrinha não vai ultrapassar o limite do grid
	    	if (cj[0] >= 1){

	    		// "Apaga" a última bolinha que forma a cobrinha para dar a impressão de movimento
	    		StdDraw.setPenColor(StdDraw.WHITE);
	    		StdDraw.filledSquare(ci[12], cj[12], .55);

	    		atualizaPosicao(ci, cj);

	    		// Atualiza a posição da cabeça da cobrinha
	    		cj[0] = cj[0] - 1;

	    		fruta(x, y, ci, cj, n);

	    		desenhaCobrinha(ci, cj);
	    	}
		}
		else if (StdDraw.isKeyPressed (KeyEvent.VK_A)){
	    	StdOut.println("Move - esquerda");

	    	// Se a cobrinha andar um passo para esquerda, ela vai passar por cima dela?
	    	// Se sim, execute o método flow novamente até que o jogador faça um movimento distinto
	    	for (int i = 1; i < 13; i++) {
				if ((ci[0]-1) == ci[i] && cj[0] == cj[i]) {
					flow(n, ci, cj, x, y);
				}
			}

			// Verifica se a cobrinha não vai ultrapassar o limite do grid
	    	if (ci[0] >= 1){

	    		// "Apaga" a última bolinha que forma a cobrinha para dar a impressão de movimento
	    		StdDraw.setPenColor(StdDraw.WHITE);
	    		StdDraw.filledSquare(ci[12], cj[12], .55);

	    		atualizaPosicao(ci, cj);

	    		// Atualiza a posição da cabeça da cobrinha
	    		ci[0] = ci[0] - 1;

	    		fruta(x, y, ci, cj, n);

	    		desenhaCobrinha(ci, cj);
	    	}
		}
		else if (StdDraw.isKeyPressed (KeyEvent.VK_D)){
	    	StdOut.println("Move - direita");

	    	// Se a cobrinha andar um passo para direita, ela vai passar por cima dela?
	    	// Se sim, execute o método flow novamente até que o jogador faça um movimento distinto
	    	for (int i = 1; i < 13; i++) {
				if ((ci[0]+1) == ci[i] && cj[0] == cj[i]) {
					flow(n, ci, cj, x, y);
				}
			}

			// Verifica se a cobrinha não vai ultrapassar o limite do grid
	    	if (ci[0] < n-1){

	    		// "Apaga" a última bolinha que forma a cobrinha para dar a impressão de movimento
	    		StdDraw.setPenColor(StdDraw.WHITE);
	    		StdDraw.filledSquare(ci[12], cj[12], .55);

	    		atualizaPosicao(ci, cj);

	    		// Atualiza a posição da cabeça da cobrinha
	    		ci[0] = ci[0] + 1;

	    		fruta(x, y, ci, cj, n);

	    		desenhaCobrinha(ci, cj);
	    	}
		}

		flow(n, ci, cj, x, y);
    }
    //=================================================================================================


    

    // test client
    public static void main(String[] args) {
	
		// n recebe o tamanho do grid
		int n = Integer.parseInt(args[0]);

		StdDraw.setXscale(-1, n);
    	StdDraw.setYscale(-1, n);

    	// Coordenadas (x,y) das 13 bolinhas que formam a cobrinha
    	// *Obs: (ci[0],cj[0]) são as coordenadas da cabeça da cobrinha
    	int[] ci = {n/2, n/2, n/2, n/2, n/2, n/2, n/2, n/2, n/2, n/2, n/2, n/2, n/2}; // Coordenadas y (linha[i])
		int[] cj = {n/2, n/2-1, n/2-2, n/2-3, n/2-4, n/2-5, n/2-6, n/2-7, n/2-8, n/2-9, n/2-10, n/2-11, n/2-12}; // Coordenadas x (coluna[j])

    	desenhaParedes(n);

    	desenhaCobrinha(ci, cj);

    	// Posição inicial da fruta
    	int[] x = {(int)(Math.random()*n)};
    	int[] y = {(int)(Math.random()*n)};

		flow(n, ci, cj, x, y);
    }
}
