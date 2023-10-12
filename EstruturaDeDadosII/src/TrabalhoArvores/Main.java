package TrabalhoArvores;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArvoreBinaria ab = new ArvoreBinaria();
		
		ArrayGenerator a = new ArrayGenerator();
		
		int array [] = a.criarArray(1000000);
		a.preencherOrganizado(array);
		
		for(int element : array) {
			ab.insert(element);
		}
		
		for(int element : array) {
			ab.remove(element);
		}
	}

}
