
public class SelfAvoidingWalkerGraphic {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);      // lattice size
        int T = Integer.parseInt(args[1]);      // number of trials
        int deadEnds = 0;                       // trials resulting in a dead end
        int passos;                             //número de passos dados
        int simulation = 0;                     //número da simulação
        String formats = "%6d     --- %12d\n";  //formato de preenchimento da tabela

        StdDraw.setCanvasSize(15 * N, 15 * N);

        //define o tamanho do grid
        StdDraw.setXscale(0, N - 1);
        StdDraw.setYscale(0, N - 1);

        StdOut.println("------------------------------------");
        StdOut.println("Simulação  ---  Quantidade de passos");
        StdOut.println("------------------------------------");

        // simulate T self-avoiding walks
        for (int t = 0; t < T; t++) {
            boolean[][] a = new boolean[N][N];   // intersections visited 
            int x = N/2, y = N/2;                // current position

            passos = 0;

            StdDraw.clear(); //limpa o grid
            StdDraw.enableDoubleBuffering();
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            //faz um novo grid
            for (int i = 0; i < N; i++) {
                StdDraw.line(0, i, N, i);
                StdDraw.line(i, 0, i, N);
            }
            StdDraw.show(); //mostra o grid
            StdDraw.setPenColor(StdDraw.BLUE);

            // repeatedly take a random step, unless you've already escaped
            while (x > 0 && x < N-1 && y > 0 && y < N-1)  {

                // dead-end, so break out of loop
                if (a[x-1][y] && a[x+1][y] && a[x][y-1] && a[x][y+1]) {
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.text(N/2, N/2, "SEM SAÍDA"); //informa que o gato ficou sem saída
                    StdDraw.show();
                    StdDraw.pause(500); //aguarda 100ms
                    StdDraw.setPenColor(StdDraw.BLUE);
                    deadEnds++;
                    break;
                }

                // mark (x, y) as visited
                a[x][y] = true; 

                // take a random step to unvisited neighbor
                double r = Math.random(); 
                if (r < 0.25) {
                    if (!a[x+1][y]){
                        StdDraw.line(x, y, x+1, y); //desenha o caminho do gato
                        StdDraw.show();             //mostra o caminho percorrido pelo gato
                        StdDraw.pause(20);
                        x++;
                        passos++;
                    } 
                }
                else if (r < 0.50) {
                    if (!a[x-1][y]){
                        StdDraw.line(x, y, x-1, y); //desenha o caminho do gato
                        StdDraw.show();             //mostra o caminho percorrido pelo gato
                        StdDraw.pause(20);
                        x--;
                        passos++;
                    }
                }
                else if (r < 0.75) {
                    if (!a[x][y+1]){
                        StdDraw.line(x, y, x, y+1); //desenha o caminho do gato
                        StdDraw.show();             //mostra o caminho percorrido pelo gato
                        StdDraw.pause(20);
                        y++;
                        passos++;
                    }  
                }
                else if (r < 1.00) {
                    if (!a[x][y-1]){
                        StdDraw.line(x, y, x, y-1); //desenha o caminho do gato
                        StdDraw.show();             //mostra o caminho percorrido pelo gato
                        StdDraw.pause(20);
                        y--;
                        passos++;
                    } 
                }
            }
            StdOut.printf(formats, simulation, passos); //adiciona à tabela as informações sobre a simulação
            simulation++;
        }
        StdOut.println("------------------------------------");
        StdOut.printf("O gato ficou sem saída %2d%% das vezes\n", 100*deadEnds/T);
        StdOut.println("------------------------------------");
    } 
}