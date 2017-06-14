package TADs.Arbre;

public class NodeArbre<K extends Comparable<K>, V> implements Cloneable {
	protected K k;
	protected V v;
	protected Arbre<K, V> fe;
	protected Arbre<K, V> fd;
	
	public NodeArbre(K k, V v) {
		this.k=k;
		this.v=v;
		fe=null;
		fd=null;
	}

	@Override
	public String toString() {
		return "NodeABC [k=" + k + ", v=" + v + ", fe=" + fe + ", fd=" + fd + "]";
	}

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

	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}

	public Arbre<K, V> getFe() {
		return fe;
	}

	public void setFe(Arbre<K, V> fe) {
		this.fe = fe;
	}

	public Arbre<K, V> getFd() {
		return fd;
	}

	public void setFd(Arbre<K, V> fd) {
		this.fd = fd;
	}
	
}
