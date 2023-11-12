/**
 * ArvorePesquisa
 */
public class ArvorePesquisa {
    Node raiz;

    ArvorePesquisa() {
        raiz = null;
    }

    public void InsertFather(int x) {
        if (raiz == null) {
            raiz = new Node(x);
        } else if (x < raiz.valor) {
            InsertFather(x, raiz.esq, raiz);
        } else if (x > raiz.valor) {
            InsertFather(x, raiz.dir, raiz);
        }
    }

    private void InsertFather(int x, Node i, Node father) {
        if (i == null) {
            if (x < father.valor) {
                father.esq = new Node(x);
            } else if (x > father.valor) {
                father.dir = new Node(x);
            }
        } else {
            if (x < i.valor) {
                InsertFather(x, i.esq, i);
            } else if (x > i.valor) {
                InsertFather(x, i.dir, i);
            }
        }
    }

    public void Insert(int x) {
        raiz = Insert(x, raiz);
    }

    private Node Insert(int x, Node i) {
        if (i == null) {
            i = new Node(x);
        } else if (x < i.valor) {
            i.esq = Insert(x, i.esq);
        } else if (x > i.valor) {
            i.dir = Insert(x, i.dir);
        }
        return i;
    }

    public void Remove(int x) {
        raiz = Remove(x, raiz);
    }

    private Node Remove(int x, Node i) {
        if (x < i.valor) {
            i.esq = Remove(x, i.esq);
        } else if (x > i.valor) {
            i.dir = Remove(x, i.dir);
        } else if (i.dir == null) {
            i = i.esq;
        } else if (i.esq == null) {
            i = i.dir;
        } else {
            i.esq = getMaiorEsq(i, i.esq);
        }
        return i;
    }

    private Node getMaiorEsq(Node i, Node j) {
        if (j.dir == null) {
            i.valor = j.valor;
            j = j.esq;
        } else {
            j.dir = getMaiorEsq(i, j.dir);
        }
        return j;
    }

    public void CaminharCentral() {
        CaminharCentral(raiz);
    }

    public void CaminharCentral(Node i) {
        if (i != null) {
            CaminharCentral(i.esq);
            System.out.println(i.valor + " ");
            CaminharCentral(i.dir);
        }
    }

    public void CaminharPos(Node i) {
        if (i != null) {
            CaminharCentral(i.esq);
            CaminharCentral(i.dir);
            System.out.println(i.valor + " ");
        }
    }

    public void CaminharPre(Node i) {
        if (i != null) {
            System.out.println(i.valor + " ");
            CaminharCentral(i.esq);
            CaminharCentral(i.dir);
        }
    }

    public static void main(String[] args) {
        ArvorePesquisa tree = new ArvorePesquisa();
        tree.Insert(10);
        tree.InsertFather(11);
        tree.Remove(10);
        tree.Insert(90);
        tree.CaminharCentral();
    }
}

class Node {
    Node esq, dir;
    int valor;

    Node(int valor) {
        this.valor = valor;
        esq = dir = null;
    }
}