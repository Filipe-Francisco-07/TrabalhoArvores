package TrabalhoArvores;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArvoreB ab = new ArvoreB(3);
		
		ArrayGenerator ag = new ArrayGenerator();
		
		int array [] = ag.criarArray(1000000);
		ag.preencherOrganizado(array);
		
		
		long time1 = System.currentTimeMillis();
	
		for(int element : array) {
			ab.Inserir(element);
		}
		long time2 = System.currentTimeMillis();
		
		double tempoInsercao = ((time2 - time1));
		time1 = 0;
		time2 = 0;
		
		time1 = System.nanoTime();
		
		if(ab.buscar(1000001)) {
			System.out.println("Valor encontrado");
		}else {
			System.out.println("Valor nao encontrado");
		}
		
		/*for (int element : array) {
			ab.Remover(element);
	    }
		*/
		time2 = System.nanoTime();
		
		double tempoRemocao = ((time2 - time1)*0.000001);
		
		
		System.out.println("Tempo de execução da inserção de "+array.length+" valores: "+tempoInsercao+" ms.");
		
		System.out.println("Tempo de execução da busca de um valor inexistente: "+tempoRemocao+" ms.");
	}

}
