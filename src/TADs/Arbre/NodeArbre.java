package TADs.Arbre;

/**
 * Classe NodeArbre
 * @param <K>
 * @param <V>
 */
public class NodeArbre<K extends Comparable<K>, V> implements Cloneable {
	protected K k;
	protected V v;
	protected Arbre<K, V> fe;
	protected Arbre<K, V> fd;
	
	/**
	 * Constructor del node de l'arbre
	 * @param k la clau
	 * @param v l'informacio del node
	 */
	public NodeArbre(K k, V v) {
		this.k=k;
		this.v=v;
		fe=null;
		fd=null;
	}

	/**
	 * Metode toString d'un node
	 * @return l'informacio del node
	 */
	@Override
	public String toString() {
		return "NodeABC [k=" + k + ", v=" + v + ", fe=" + fe + ", fd=" + fd + "]";
	}

	/**
	 * Metode que crea un clon d'un node de l'arbre
	 * @return obj clon del node
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected NodeArbre<K,V> clone() {
		NodeArbre<K, V> obj=null;
		try{
            obj=(NodeArbre<K, V>)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println("No es pot duplicar");
        }
		return obj;
	}

	/**
	 * Getter de k
	 * @return k retorna la clau
	 */
	public K getK() {
		return k;
	}

	/**
	 * Setter de k
	 * @param k la clau
	 */
	public void setK(K k) {
		this.k = k;
	}

	/**
	 * Getter de v
	 * @return la info del node
	 */
	public V getV() {
		return v;
	}

	/**
	 * Setter de V
	 * @param v la info del node
	 */
	public void setV(V v) {
		this.v = v;
	}

	/**
	 * Getter del fill esquerra 
	 * @return fe el fill esquerra
	 */
	public Arbre<K, V> getFe() {
		return fe;
	}

	/**
	 * Setter del fill esquerra
	 * @param fe fill esquerra
	 */
	public void setFe(Arbre<K, V> fe) {
		this.fe = fe;
	}

	/**
	 * Getter del fill dret
	 * @return fd el fill dret
	 */
	public Arbre<K, V> getFd() {
		return fd;
	}

	/**
	 * Setter del fill dret
	 * @param fd el fill dret
	 */
	public void setFd(Arbre<K, V> fd) {
		this.fd = fd;
	}
	
}
