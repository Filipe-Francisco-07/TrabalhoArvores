package ListaEncadeada;

public class ListaEncadeada {
	private Nodo inicio;
	
	public ListaEncadeada() {
		inicio = null;
	}
	
	public boolean vazia() {
		return inicio == null;
	}
	
	public Nodo getInicio() {
		return inicio;
	}
	
	public void inserirOrdenado(int dado) {
		Nodo novoNodo = new Nodo(dado);
		if(vazia()) {
			inicio = novoNodo;
			return;
		}
		if(dado < inicio.getDado()) {
			novoNodo.setProx(inicio);
			inicio= novoNodo;
			return;
		}
		
		Nodo atual = inicio;
		Nodo anterior = null;
		
		while(atual != null && dado > atual.getDado()) {
			anterior = atual;
			atual= atual.getProx();
		}
		
		novoNodo.setProx(atual);
		anterior.setProx(novoNodo);
	}
	
	public void inserirInicio(int dado) {
		Nodo novoNodo = new Nodo(dado);
		novoNodo.setProx(inicio);
		inicio = novoNodo;
	}
	
	public void mostraLista() {
		if(vazia()) {
			System.out.println("Null list. :(");
			return;
		}
		Nodo aux = inicio;
		while(aux != null) {
			System.out.print(aux.getDado()+" ");
			aux = aux.getProx();
		}
		System.out.println();
		
	}
	
	public void inserirFinal(int valor) {
		if(vazia()) {
			return;
		}
		
		Nodo aux = inicio;
		Nodo aux2 = inicio;
		
		while(aux2.getProx().getDado() != valor) {
			aux2 = aux2.getProx();
		}
		
		while(aux.getProx() != null) {
			aux = aux.getProx();
		}
		
		if(aux.getProx() == null) {
			
		}
		
	}
	
	public void removeLista(int valor) {
		if(vazia()) {
			System.out.println("Null position, can't delete. :(");
			return;
		}
		if(inicio.getDado()== valor) {
			inicio = inicio.getProx();
			return;
		}
		Nodo aux = inicio;
		while(aux != null) {
			if(aux.getProx().getDado()==valor) {
				aux.setProx(aux.getProx().getProx());
				return;
			}
			aux = aux.getProx();
		}		
	}
	
	public void deletarUltimo() {
		if(inicio == null) return;
		
		Nodo aux = inicio;
		Nodo ant = null;
		
		if(inicio.getProx()== null) {
			inicio = null;
			return;
		}
		
		while(inicio.getProx()== null) {
			ant = aux;
			aux = aux.getProx();
		}
		ant.setProx(null);
	}
	
	public int buscarValor(int valor) {
		if(vazia()){
			return -1;
		}
	
		Nodo aux = inicio;
		int posicao = 0;
		while(aux != null) {
			posicao++;
			if(aux.getDado()==valor) {
				return posicao;
			}
			aux = aux.getProx();
		}
		return-1;
	}
	
	
}