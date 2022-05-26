package postorden;

public class PostOrden {

    Nodo root;

    public PostOrden() {
        root = null;
    }

    public void insertar(int dato) {
        Nodo nuevo;
        nuevo = new Nodo(dato);
        nuevo.dato = dato;
        nuevo.hijoI = null;
        nuevo.hijoD = null;
        if (root == null) {
            root = nuevo;
        } else {
            Nodo anterior = null, reco;
            reco = root;
            while (reco != null) {
                anterior = reco;
                if (dato < reco.dato) {
                    reco = reco.hijoI;
                } else {
                    reco = reco.hijoD;
                }
            }
            if (dato < anterior.dato) {
                anterior.hijoI = nuevo;
            } else {
                anterior.hijoD = nuevo;
            }
        }
    }

    public Nodo eliminarNodo(Nodo actual, int dato) {
        if (actual == null) {
            System.out.println("No existe el elemento a eliminar");
        } else {
            if (dato > actual.dato) {
                actual.hijoD = eliminarNodo(actual.hijoD, dato);
            } else if (dato < actual.dato) {
                actual.hijoI = eliminarNodo(actual.hijoI, dato);
            } else {
                Nodo aux = actual;
                if (aux.hijoI == null) {
                    return aux.hijoD;
                } else if (aux.hijoD == null) {
                    return aux.hijoI;
                } else {
                    aux.dato = reemplazar(aux);
                }
            }
        }
        return actual;
    }

    public void borrar(int value) {
        root = eliminarNodo(root, value);
    }

    private boolean buscarNodo(Nodo actual, int val) {
        if (actual == null) {
            return false;
        }
        if (val == actual.dato) {
            return true;
        }
        return val < actual.dato
                ? buscarNodo(actual.hijoI, val)
                : buscarNodo(actual.hijoD, val);
    }

    public boolean buscar(int val) {
        return buscarNodo(root, val);
    }

    public int reemplazar(Nodo actual) {
        Nodo p = actual;
        Nodo a = actual.hijoI;
        int valor;
        while (a.hijoD != null) {
            p = a;
            a = a.hijoD;
        }
        valor = a.dato;
        if (p == actual) {
            p.hijoI = a.hijoI;
        } else {
            p.hijoD = a.hijoI;
        }
        return valor;
    }

    private void imprimirPost(Nodo reco) {
        if (reco != null) {
            imprimirPost(reco.hijoI);
            imprimirPost(reco.hijoD);
            System.out.print(reco.dato + " ");
        }
    }

    private void imprimirPre(Nodo reco) {
        if (reco != null) {
            System.out.print(reco.dato + " ");
            imprimirPre(reco.hijoI);
            imprimirPre(reco.hijoD);
        }
    }

    private void imprimirInOrden(Nodo r) {
        if (r != null) {
            imprimirInOrden(r.hijoI);
            System.out.println(r.dato + " ");
            imprimirInOrden(r.hijoD);

        }

    }

    public boolean vacio() {
        return root == null;
    }

    public void imprimirPost() {
        imprimirPost(root);
        System.out.println();
    }

    public void imprimirPre() {
        imprimirPre(root);
        System.out.println();
    }

    public void imprimirInOrden() {
        imprimirInOrden(root);
        System.out.println();
    }
}
