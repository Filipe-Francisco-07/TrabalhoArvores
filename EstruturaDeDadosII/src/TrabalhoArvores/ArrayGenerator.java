package TrabalhoArvores;
import java.util.Random;

public class ArrayGenerator {

	public int []criarArray(int tam) {
		int array [] = new int[tam];
		return array;
	}
	
	public int []preencherOrganizado(int array[]) {
		for(int i = 0; i < array.length; i++) {
			array[i] = i;
		}	
		
		return array;
	}
	
	public int []preencherInvertido(int array[]) {
		int j = array.length;
		for(int i = 0; i < array.length; i++) {
			array[i] = j;
			j--;
		}	
		
		return array;
	}
	
	public int []preencherAleatorio(int array[]) {
		Random r = new Random();
		
		for(int i = 0; i < array.length; i++) {
			int j = r.nextInt(1,array.length);
			array[i] = j;
		}	
		
		return array;
	}
	
}