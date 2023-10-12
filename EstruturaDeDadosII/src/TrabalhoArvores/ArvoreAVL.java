package TrabalhoArvores;

public class ArvoreAVL {
	private class Nodo{
	private int dado, altd, alte, copy;
	private Nodo dir,esq;
	
	
	public Nodo(int dado) {
		this.dado = dado;
		dir = esq = null;
		altd = alte = 0;
		copy = 1;

	}
 }
	
	Nodo raiz;
	int primos = 0;
	public ArvoreAVL() {
		raiz = null;
	}

	public void inserir(int dado) {
		raiz = inserirDado(raiz,dado);
	}
	
	public void verNosPrimos() {
		verPrimos(raiz);
		System.out.println("Temos "+primos+ " primos na arvore.");
	}
	
	private void verPrimos(Nodo raiz) {

			if (raiz != null) {
				verPrimos(raiz.esq);
				if(raiz.dado == 2 || raiz.dado == 3) {
					System.out.println(raiz.dado+" é primo");		
					primos++;
				}else if(!(raiz.dado % 2 ==0) && !(raiz.dado % 3 == 0) ) {
					System.out.println(raiz.dado+" é primo");
					primos++;

				}
				verPrimos(raiz.dir);
			}
			return;
		
	}

	private Nodo inserirDado(Nodo raiz, int dado) {
		if (raiz == null) {
			raiz = new Nodo(dado);
			return raiz;

		}
		if (dado < raiz.dado) {
			raiz.esq = inserirDado(raiz.esq,dado);
			if (raiz.esq.altd > raiz.esq.alte) {
				raiz.alte = raiz.esq.altd + 1;
			} else {
				raiz.alte = raiz.esq.alte + 1;
			}
			raiz = balanceamento(raiz);
		} else if (dado > raiz.dado) {
			raiz.dir = inserirDado(raiz.dir,dado);
			if (raiz.dir.altd > raiz.dir.alte) {
				raiz.altd = raiz.dir.altd + 1;
			} else {
				raiz.altd = raiz.dir.alte + 1;
			}
			raiz = balanceamento(raiz);

		}else {
			raiz.copy++;
		}
		return raiz;
	}
	
	
	public void remove(int chave) {
		raiz = removeItem(raiz,chave);
		
	}
	
	private Nodo removeItem(Nodo raiz, int chave) {
		if(raiz == null) {
			return null;
		}
		if(chave < raiz.dado) {
			raiz.esq = removeItem(raiz.esq,chave);
		}else if(chave > raiz.dado) {
			raiz.dir = removeItem(raiz.dir, chave);
		}else {
			if(raiz.esq == null) {
				return raiz.dir;
			}else if(raiz.dir == null) {
				return raiz.esq;
			}else {
				Nodo next = findNext(raiz.dir);
				raiz.dado = next.dado;
				raiz.dir = removeItem(raiz.dir,next.dado);
			}
		}
	return raiz;
	}
	
	private Nodo findNext(Nodo nodo) {
		while(nodo.esq != null) {
			nodo = nodo.esq;
		}
		return nodo;
	}

	private Nodo balanceamento (Nodo raiz) {
		int ĩ = raiz.altd - raiz.alte;
		int ĩSubArv;
		if (ĩ == 2) {
			ĩSubArv = raiz.dir.altd - raiz.dir.alte;
			if (ĩSubArv >=0) {
				raiz = rotacao_esquerda(raiz);
			} else {
				raiz.dir = rotacao_direita(raiz.dir);
		raiz = rotacao_esquerda(raiz);
			}
		} else if (ĩ == -2) {
			ĩSubArv = raiz.esq.altd - raiz.esq.alte;
			if (ĩSubArv <= 0) {
				raiz = rotacao_direita(raiz);
			} else {
				raiz.esq = rotacao_esquerda(raiz.esq);
				raiz = rotacao_direita(raiz);
			}
		}
		return raiz;
	}

	public boolean eAvl(ArvoreAVL arvore) {
		if(verAvl(arvore.raiz))
		return true;
		else {
			return false;
		}
		
	}
	
	public  boolean verAvl(Nodo raiz) {
	if (raiz != null) {
		verAvl(raiz.esq);
		if(raiz.altd - raiz.alte >= 2 || raiz.altd - raiz.alte <= -2){
			return false;
		}
		verAvl(raiz.dir);
	}
	return true;
}

	private Nodo rotacao_esquerda(Nodo raiz) {
		Nodo aux1, aux2;
		aux1 = raiz.dir;
		aux2 = raiz.esq;
		raiz.dir = aux2;
		aux1.esq = raiz;
		if (raiz.dir == null) {
			raiz.altd = 0;
		} else if (raiz.dir.alte > raiz.dir.altd) {
			raiz.altd = raiz.dir.alte + 1;
		} else {
			raiz.altd = raiz.dir.altd + 1;
		}
		if (aux1.esq.alte > aux1.esq.altd) {
			aux1.alte = aux1.esq.alte + 1;
		} else {
			aux1.alte = aux1.esq.altd + 1;
		}
		return aux1;
	}

	private Nodo rotacao_direita(Nodo raiz) {
		Nodo aux1, aux2;
		aux1 = raiz.esq;
		aux2 = aux1.dir;
		raiz.esq = aux2;
		aux1.dir = raiz;
		if (raiz.esq == null) {
			raiz.alte = 0;
		} else if (raiz.esq.alte > raiz.esq.altd) {
			raiz.alte = raiz.esq.alte + 1;
		} else {
			raiz.alte = raiz.esq.altd + 1;
		}
		if (aux1.dir.alte > aux1.dir.altd) {
			aux1.altd = aux1.dir.alte + 1;
		} else {
			aux1.altd = aux1.dir.altd + 1;
		} 
		return aux1;
	}

	public void mostrarEmOrdem() {
		mostrandoOrdenado(raiz);
	}
	private void mostrandoOrdenado(Nodo raiz) {
		if (raiz != null) {
			mostrandoOrdenado(raiz.esq);
			System.out.println(raiz.dado+" vezes adicionada: "+raiz.copy);
			mostrandoOrdenado(raiz.dir);
		}
	}
	
	public void mostrarNosNoNivel(int nivelDesejado) {
	    mostrarNosNoNivelRecursivo(raiz, nivelDesejado, 0);
	}

	private void mostrarNosNoNivelRecursivo(Nodo raiz, int nivelAlvo, int nivelAtual) {
	    if (raiz == null) {
	        return;
	    }
	    
	    if (nivelAtual == nivelAlvo) {
	        System.out.println(raiz.dado);
	    }
	    
	    mostrarNosNoNivelRecursivo(raiz.esq, nivelAlvo, nivelAtual + 1);
	    mostrarNosNoNivelRecursivo(raiz.dir, nivelAlvo, nivelAtual + 1);
	}
	
	public int somaNivelImpar() {
	    return somarNosImpares(raiz, 0);
	}

	private int somarNosImpares(Nodo raiz, int nivelAtual) {
	    if (raiz == null) {
	        return 0;
	    }

	    int soma = 0;
	    if (nivelAtual % 2 != 0) {
	        soma += raiz.dado;
	    }

	    soma += somarNosImpares(raiz.esq, nivelAtual + 1);
	    soma += somarNosImpares(raiz.dir, nivelAtual + 1);
	    return soma;
	}
}