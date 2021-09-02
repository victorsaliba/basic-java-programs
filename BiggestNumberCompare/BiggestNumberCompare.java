public class BiggestNumberCompare {
    public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int T = Integer.parseInt(args[2]);

		int bigger;
		int choice;
		int big;
		int card;

		//Guarda o valor da maior carta em "bigger"
		if (a < b) bigger = 1;
		else bigger = 0;

		//Guarda o valor da maior carta em "big"
		if (a < b) big = b;
		else big = a;

		//Estrategia quando Bob nao ve nenhuma carta
		int rightNoChoice = 0;
	
		//Estrategia quando Bob ve uma carta
		int rightWiChoice = 0;

		for (int i = 0; i < T; i++){
		
	    	//Bob escolhe uma das duas cartas uniformemente ao acaso
	    	if (Math.random() < .5) choice = 0;
	    	else choice = 1;

	    	// Escolha correta?
			if (choice == bigger) rightNoChoice++;
		
			//Bob escolhe uma carta e lê o valor da carta
			if (Math.random() < .5) card = a;
			else card = b;
		
			/*Estrategia: Quanto maior o número da carta escolhida,
        	menor é a probabilidade da carta não escolhida ser a maior dentre as duas*/
        	for (double n = 0; n <= card; n++){
            	if ((int)n == card) { //Verifica se o contador n possui o número da carta de Bob
                	if (card == a) {
                    	if (Math.random() > (n/100)) { //de acordo com a probabilidade de a carta não escolhida ser a maior dentre as duas, Bob troca de carta ou não
                        	card = b; //se a carta de Bob for a carta a, então Bob trocará sua carta pela carta b
                        	break;
                 		}
                	}
                	else if (card == b) {
                    	if (Math.random() > (n/100)) { //de acordo com a probabilidade de a carta não escolhida ser a maior dentre as duas, Bob troca de carta ou não
                        	card = a; //se a carta de Bob for a carta b, então Bob trocará sua carta pela carta a
                        	break;
                    	}
                	}
            	}
			}
		
			// Escolha correta?
			if (card == big) rightWiChoice++;
		}

		// Estatisticas
		double frequencyNo = 100*(double)rightNoChoice/T;
		double frequencyWi = 100*(double)rightWiChoice/T;
		System.out.println("No choice: " + rightNoChoice + " - " + frequencyNo + "%");
		System.out.println("With choice: " + rightWiChoice + " - " + frequencyWi + "%");
	}
}
