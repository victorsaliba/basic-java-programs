
public class HanoiRevisited {

//====================================================================================================================================
    // draw the current state of the Towers of Hanoi game
    public static void draw(int[] pole) {

        int N = pole.length - 1;

        // draw 3 poles
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.004);

        //Desenha no eixo y estando nos pontos 0, 1 e 2
        for (int i = 0; i < 3; i++){
            StdDraw.line(i, -1, i, N);
            StdDraw.line(i - (0.7/2), -0.48, i + (0.7/2), -0.48);
        }

        // draw N discs
        int[] discs = new int[3];   // discs[p] = # discs on pole p
        for (int i = N; i >= 1; i--) {
            StdDraw.setPenColor(0 ,0 , 255*i/N); // atualiza a cor da caneta para um tom de azul distinto do anterior
            StdDraw.setPenRadius(0.035);
            double size = 0.5 * i / N; // Define length of the disc
            int p = pole[i]; // p recebe a torre que contém o disco i
            StdDraw.line(p-size/2, discs[p], p + size/2, discs[p]);
            discs[p]++; // atualiza a qtd de discos na torre p
        }

        StdDraw.show();
        StdDraw.pause(400);
    }
//====================================================================================================================================


//====================================================================================================================================
    public static void hanoi(int N, int[] moves) {

        int[] pole = new int[N+1];  // pole[i] indica em que torre está o disco i
        draw(pole);                 // desenha os discos nas condições iniciais
    
        /* chama o método "hanoi" para resolver o problema onde 
        deve-se transferir os discos da torre 0 para a torre 2 */
        hanoi(N, 0, 1, 2, pole, moves);
    }
//====================================================================================================================================


//====================================================================================================================================
    public static void hanoi(int n, int from, int aux, int to, int[] pole, int[] moves) {

        if (n == 0) return; // caso base: quando não houver discos para mover, faça nada

        hanoi(n-1, from, aux, to, pole, moves); // move os n-1 discos da torre inicial para torre final passando pela torre auxiliar
        StdOut.println("Move disc " + n + " from pole " + from + " to pole " + aux);
        pole[n] = aux; // move o maior disco da torre inicial para a torre auxiliar
        moves[0]++; // a quantidade de movimentos aumenta em 1 unidade
        draw(pole);
        hanoi(n-1, to, aux, from, pole, moves); // move os n-1 discos da torre final para a torre inicial passando pela torre auxiliar
        StdOut.println("Move disc " + n + " from pole " + aux + " to pole " + to);
        pole[n] = to; // move o maior disco da torre auxiliar para a torre final
        moves[0]++; // a quantidade de movimentos aumenta em 1 unidade
        draw(pole);
        hanoi(n-1, from, aux, to, pole, moves); // move os n-1 discos da torre inicial para a torre final passando pela torre auxiliar
    }
//====================================================================================================================================



    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);   // number of discs
        int WIDTH  = 200;                    // width of largest disc
        int HEIGHT = 20;                     // height of each disc

        // set size of window and sale
        StdDraw.setCanvasSize(4*WIDTH, (N+3)*HEIGHT);
        StdDraw.setXscale(-1, 3);
        StdDraw.setYscale(-1, N+3);

        int[] moves = new int[1]; // cria a lista que irá guardar a qtd de movimentos na posição 0

        // solve the Towers of Hanoi with N discs
        hanoi(N, moves);

        // Imprime a saída no formato desejado
        StdOut.println("------------------------------------------");
        StdOut.printf("Qtd discos = %d, total de movimentos = %d\n", N, moves[0]);
    }
}



