package TrabalhoArvores;

import java.util.Stack;

public class ArvoreB {

  private int T;

  public class Nodo {
    int n;
    int chave[] = new int[2 * T - 1];
    Nodo filhos[] = new Nodo[2 * T];
    boolean folha = true;

    public int Encontrar(int k) {
      for (int i = 0; i < this.n; i++) {
        if (this.chave[i] == k) {
          return i;
        }
      }
      return -1;
    }
  }

  public ArvoreB(int t) {
    T = t;
    raiz = new Nodo();
    raiz.n = 0;
    raiz.folha = true;
  }

  private Nodo raiz;
  
  public boolean buscar(int k) {
	    if (this.Buscar(raiz, k) != null) {
	      return true;
	    } else {
	      return false;
	    }
	  }

  private Nodo Buscar(Nodo x, int chave) {
    int i = 0;
    if (x == null)
      return x;
    for (i = 0; i < x.n; i++) {
      if (chave < x.chave[i]) {
        break;
      }
      if (chave == x.chave[i]) {
        return x;
      }
    }
    if (x.folha) {
      return null;
    } else {
      return Buscar(x.filhos[i], chave);
    }
  }

  private void Dividir(Nodo x, int pos, Nodo y) {
    Nodo z = new Nodo();
    z.folha = y.folha;
    z.n = T - 1;
    for (int j = 0; j < T - 1; j++) {
      z.chave[j] = y.chave[j + T];
    }
    if (!y.folha) {
      for (int j = 0; j < T; j++) {
        z.filhos[j] = y.filhos[j + T];
      }
    }
    y.n = T - 1;
    for (int j = x.n; j >= pos + 1; j--) {
      x.filhos[j + 1] = x.filhos[j];
    }
    x.filhos[pos + 1] = z;

    for (int j = x.n - 1; j >= pos; j--) {
      x.chave[j + 1] = x.chave[j];
    }
    x.chave[pos] = y.chave[T - 1];
    x.n = x.n + 1;
  }

  public void Inserir(final int chave) {
    Nodo r = raiz;
    if (r.n == 2 * T - 1) {
      Nodo s = new Nodo();
      raiz = s;
      s.folha = false;
      s.n = 0;
      s.filhos[0] = r;
      Dividir(s, 0, r);
      InserirValor(s, chave);
    } else {
      InserirValor(r, chave);
    }
    System.out.println("Valor " + chave + " foi inserido.");
  }

  final private void InserirValor(Nodo x, int k) {

    if (x.folha) {
      int i = 0;
      for (i = x.n - 1; i >= 0 && k < x.chave[i]; i--) {
        x.chave[i + 1] = x.chave[i];
      }
      x.chave[i + 1] = k;
      x.n = x.n + 1;
    } else {
      int i = 0;
      for (i = x.n - 1; i >= 0 && k < x.chave[i]; i--) {
      }
      ;
      i++;
      Nodo tmp = x.filhos[i];
      if (tmp.n == 2 * T - 1) {
        Dividir(x, i, tmp);
        if (k > x.chave[i]) {
          i++;
        }
      }
      InserirValor(x.filhos[i], k);
    }

  }

  public void Mostrar() {
    Mostrar(raiz);
  }

  private void Mostrar(Nodo x) {
    assert (x == null);
    for (int i = 0; i < x.n; i++) {
      System.out.print(x.chave[i] + " ");
    }
    if (!x.folha) {
      for (int i = 0; i < x.n + 1; i++) {
        Mostrar(x.filhos[i]);
      }
    }
  }

  public void Remover(int chave) {
    Nodo x = raiz;
    int pos;
    System.out.println("Valor " + chave + " removido.");

    while (x != null) {
      pos = x.Encontrar(chave);

      if (pos != -1) {
        if (x.folha) {
          int i = 0;
          for (i = 0; i < x.n && x.chave[i] != chave; i++) {
          }

          if (i < x.n) {
            for (; i < x.n - 1; i++) {
              x.chave[i] = x.chave[i + 1];
            }
            x.n--;
          }
          break;
        } else {
          x = x.filhos[pos];

        }
      } else {
        pos = 0;

        while (pos < x.n && x.chave[pos] < chave) {
          pos++;
        }

        x = x.filhos[pos];
      }
    }

    if (x != null && x == raiz && x.n == 0) {
      raiz = x.filhos[0];
    }

  }

  public void Tarefa(int a, int b) {
    Stack<Integer> st = new Stack<>();
    EncontrarChaves(a, b, raiz, st);

    while (!st.isEmpty()) {
      int chaveParaRemover = st.pop();
      if (raiz != null) {
        Remover(chaveParaRemover);
      }
    }
  }

  private void EncontrarChaves(int a, int b, Nodo x, Stack<Integer> st) {
    int i = 0;
    for (i = 0; i < x.n && x.chave[i] < b; i++) {
      if (x.chave[i] > a) {
        st.push(x.chave[i]);
      }
    }
    if (!x.folha) {
      for (int j = 0; j < i + 1; j++) {
        EncontrarChaves(a, b, x.filhos[j], st);
      }
    }
  }
}