package TrabalhoArvores;

import java.util.Queue;

import ListaEncadeada.ListaEncadeada;

import java.util.LinkedList;

public class ArvoreBinaria {
	private int tamanho_arvore;
	private int altura_arvore;
	private ListaEncadeada lista_encadeada;
    
	private class Nodo{
		private int key;
		private Nodo right,left;
		
		public Nodo(int item) {
			this.key = item;
			right = left = null;
		}
	}
	
	public ArvoreBinaria() {
		lista_encadeada = new ListaEncadeada();
	}

	public int getTamanho_arvore() {
		return tamanho_arvore;
	}

	public void setTamanho_arvore(int tamanho_arvore) {
		this.tamanho_arvore = tamanho_arvore;
	}

	public int getAltura_arvore() {
		return altura_arvore;
	}

	public void setAltura_arvore(int altura_arvore) {
		this.altura_arvore = altura_arvore;
	}

	//a
	public void showBiggestNumber() {
		showBiggest(raiz);
	}
	
	private int findBiggestValue(Nodo raiz) {
	    if (raiz == null) {
	        return  Integer.MIN_VALUE;
	    }
	    
	    int maiorEsquerda = findBiggestValue(raiz.left);
	    int maiorDireita = findBiggestValue(raiz.right);
	    
	    int maior = 0;
	    if(maiorEsquerda > maiorDireita) {
	    	maior = maiorEsquerda;
	    }else if(maiorDireita > maiorEsquerda) {
	    	maior = maiorDireita;
	    }else {
	    	maior = maiorDireita;
	    }
	    
	    return Math.max(raiz.key, maior);
	}

	private void showBiggest(Nodo raiz) {
	    int maiorValor = findBiggestValue(raiz);
	    System.out.println("O maior valor na arvore é: " + maiorValor);
	}
	
	//b
	public void showSmallestNumber() {
		showSmallest(raiz);
	}
	
	private int findSmallestValue(Nodo raiz) {
	    if (raiz == null) {
	        return Integer.MAX_VALUE;
	    }
	    
	    int menorEsquerda = findSmallestValue(raiz.left);
	    int menorDireita = findSmallestValue(raiz.right);
	  
	    int menor;
	    if(menorEsquerda < menorDireita) {
	    	menor = menorEsquerda;
	    }else if(menorDireita < menorEsquerda){
	    	menor = menorDireita;
	    }else {
	    	menor = menorDireita;
	    }
	    
	    return Math.min(menor, raiz.key);
	}

	private void showSmallest(Nodo raiz) {
	    int menorValor = findSmallestValue(raiz);
	    System.out.println("O menor valor na arvore é: " + menorValor);
	}
	
	Nodo raiz = null;
	
	public void insert(int key) {
		raiz = insertData(raiz,key);
	    System.out.println("Valor "+key+" foi inserido.");
	}
	
	private Nodo insertData(Nodo raiz, int key) {
		if(raiz == null) {
			raiz = new Nodo(key);
			tamanho_arvore++;
			return raiz;
		}
		if(key < raiz.key) {
			raiz.left = insertData(raiz.left,key);
		}else if(key > raiz.key) {
			raiz.right = insertData(raiz.right,key);
		}
		
		return raiz;
	}
	
	public boolean buscar(int valor) {
	    return buscarNodo(raiz, valor);
	}

	private boolean buscarNodo(Nodo nodo, int valor) {
	    if (nodo == null) {
	        return false;
	    }

	    if (valor == nodo.key) {
	        return true;
	    }

	    if (valor < nodo.key) {
	        return buscarNodo(nodo.left, valor); 
	    } else {
	        return buscarNodo(nodo.right, valor); 
	    }
	}
	
	public void showJustPairs() {
		showPairs(raiz);
	}
	
	private void showPairs(Nodo raiz) {
		if(raiz != null) {
			showPairs(raiz.left);
		
			if(raiz.key % 2 == 0)System.out.println(raiz.key);
			showPairs(raiz.right);
		}
	}
	
	
	public void showInOrder() {
		showOrderly(raiz);
	}
	
	public void showInUnGrownOrder() {
		showUnGrownOrderly(raiz);
	}
	
	public void showInLevelingOrder() {
		if(raiz == null) {
			System.out.println("Empty tree.");
			return;
		}
		Queue<Nodo> fila = new LinkedList<>();
		fila.add(raiz);
		
		while(!fila.isEmpty()) {
			int LevelSize = fila.size();
			for(int i=0;i<LevelSize;i++) {
				Nodo actualNodo = fila.poll();
				if(actualNodo != null) {
					System.out.print(actualNodo.key+" ");
					fila.add(actualNodo.left);
					fila.add(actualNodo.right);
				}else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}
	
	private void showUnGrownOrderly(Nodo raiz) {
		if(raiz != null) {
			showOrderly(raiz.right);
		
			System.out.println(raiz.key);
			showOrderly(raiz.left);
		}
	}
	
	private void showOrderly(Nodo raiz) {
		if(raiz != null) {
			showOrderly(raiz.left);
		
			System.out.println(raiz.key);
			showOrderly(raiz.right);
		}
	}
	
	public void remove(int chave) {
		raiz = removeItem(raiz,chave);
	    System.out.println("Valor "+chave+" foi removido.");
		
	}
	
	  
	
	private Nodo removeItem(Nodo raiz, int chave) {
		if(raiz == null) {
			return null;
		}
		if(chave < raiz.key) {
			raiz.left = removeItem(raiz.left,chave);
		}else if(chave > raiz.key) {
			raiz.right = removeItem(raiz.right, chave);
		}else {
			if(raiz.left == null) {
				tamanho_arvore--;
				return raiz.right;
			}else if(raiz.right == null) {
				tamanho_arvore--;
				return raiz.left;
			}else {
				Nodo next = findNext(raiz.right);
				raiz.key = next.key;
				raiz.right = removeItem(raiz.right,next.key);
			}
		}
	return raiz;
	}
	
	private Nodo findNext(Nodo nodo) {
		while(nodo.left != null) {
			nodo = nodo.left;
		}
		return nodo;
	}
	
	public int calcularAlturaArvore() {
	    return calcularAltura(raiz);
	}

	private int calcularAltura(Nodo raiz) {
	    if (raiz == null) {
	        return 0; 
	    }

	    int alturaEsquerda = calcularAltura(raiz.left);
	    int alturaDireita = calcularAltura(raiz.right);

	    if(alturaEsquerda > alturaDireita) {
	    	return(alturaEsquerda+1);
	    }else {
	    	return(alturaDireita+1);
	    }
	    
	}

	public ListaEncadeada converterParaListaEncadeada() {
      	  converterLista(raiz);
      	  return lista_encadeada;
	 }

	private void converterLista(Nodo raiz) {
		if (raiz == null) {
			return;
		}
	    converterLista(raiz.left);
	    lista_encadeada.inserirOrdenado(raiz.key);       

	    converterLista(raiz.right);
	}
	
	 public int verNivelDoNodo(int numero) {
	        return verNivel(raiz, numero, 0);
	 }

	 private int verNivel(Nodo nodo, int numero, int nivel) {
	        if (nodo == null) {
	            return -1;
	        }
	        if (nodo.key == numero) {
	            return nivel; 
	        }

	        int nivelEsquerda = verNivel(nodo.left, numero, nivel + 1);
	        if (nivelEsquerda != -1) {
	            return nivelEsquerda;
	        }
	        return verNivel(nodo.right, numero, nivel + 1);		       
	 
	 }
	 
	 public void mostrarFolha() {
		 mostrarFolhas(raiz);
	 }
	 
	 private void mostrarFolhas(Nodo raiz) {
			if(raiz != null) {
				
				if(raiz.left == null  && raiz.right == null) {
					System.out.println(raiz.key);
				}
				
				mostrarFolhas(raiz.right);			
				mostrarFolhas(raiz.left);
			}
	}
	 
	public void verSubDireita(int valor) {
		
		verSubDir(raiz, valor,false);
	}
	
	private void verSubDir(Nodo raiz, int valor, boolean a) {
		if(raiz != null) {
			if(raiz.key == valor){
				a = true;
			}
			if(a) {
				System.out.println(raiz.key);
			}
			
			verSubDir(raiz.right,valor,a);
		}			
		
	}
	
	public void verSubEsquerda(int valor) {
		
		verSubEsq(raiz, valor,false);
	}
	
	private void verSubEsq(Nodo raiz, int valor, boolean a) {
		if(raiz != null) {
			if(raiz.key == valor){
				a = true;
			}
			if(a) {
				System.out.println(raiz.key);
			}
			
			verSubEsq(raiz.left,valor,a);
		}			
		
	}
	
	public void verAncestral(int valorAlvo) {
        verAncestralNo(raiz, valorAlvo);
    }

    private boolean verAncestralNo(Nodo nodo, int valorAlvo) {
        if (nodo == null) {
            return false;
        }

        if (nodo.key == valorAlvo) {
            return true; 
        }

        if (verAncestralNo(nodo.left, valorAlvo) || verAncestralNo(nodo.right, valorAlvo)) {
            System.out.print(nodo.key + " ");
            return true;
        }

        return false;
    }
    
    public void verDescendentes(int valor) {
        verDescendentesNo(raiz, valor);
    }

    private boolean verDescendentesNo(Nodo nodo, int valor) {
        if (nodo == null) {
            return false;
        }

        if (nodo.key == valor) {
            verDescendentes(nodo.left);
            verDescendentes(nodo.right);
            return true; 
        }

        if (verDescendentesNo(nodo.left, valor) || verDescendentesNo(nodo.right, valor)) {
            return true;
        }

        return false;
    }

    private void verDescendentes(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        
        System.out.print(nodo.key + " ");
        verDescendentes(nodo.left);
        verDescendentes(nodo.right);
    }
    
    public void inserirSemRecursividade(int valor) {
    	
        Nodo novoNodo = new Nodo(valor);

        if (raiz == null) {
            raiz = novoNodo;
            tamanho_arvore++;
            return;
        }

        Nodo nodo = raiz;
        Nodo aux;
        
        System.out.println("Valor "+valor+" foi inserido.");
        
        while (true) {
        	aux = nodo;
            if (valor < nodo.key) {
            	nodo = nodo.left;
                if (nodo == null) {
                	aux.left = novoNodo;
                    tamanho_arvore++;
                    return;
                }
            } else {
            	nodo = nodo.right;
                if (nodo == null) {
                	aux.right = novoNodo;
                    tamanho_arvore++;
                    return;
                }
            }
        }
	   
    }
	 
	 
}