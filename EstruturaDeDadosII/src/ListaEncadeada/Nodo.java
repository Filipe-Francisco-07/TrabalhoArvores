package ListaEncadeada;

public class Nodo {
	private int dado;
	private Nodo Prox;
	
	public Nodo(int dado) {
		this.dado = dado;
		this.Prox = null;
	}
	
	public int getDado() {
		return dado;
	}
	public void setDado(int dado) {
		this.dado = dado;
	}
	public Nodo getProx() {
		return Prox;
	}
	public void setProx(Nodo prox) {
		Prox = prox;
	}
	
}