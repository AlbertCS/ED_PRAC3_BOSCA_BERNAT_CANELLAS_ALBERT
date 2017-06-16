package TADs.Arbre;

import TADs.TAD;

public class Arbre<K extends Comparable<K>, V> implements TAD<K, V>, Cloneable {
	
	private NodeArbre<K, V> arrel;
	//private int numNodes; el trec, sino cada subarbre te el seu numNodes i no s'actualitza correctament
	// per a tenir el numNodes, els fe i fd, haurien de ser punters a NodeABC...
	
	/**
	 * Constructor de l'arbre sense parametres
	 */
	public Arbre() {
		arrel=null;
	}
	
	/**
	 * Constructor de l'arbre amb parametres
	 * @param k la clau de l'element
	 * @param v valor de l'element
	 */
	public Arbre(K k, V v) {
		arrel=new NodeArbre<K,V>(k,v);
	}
	
	/**
	 * Metode que afegeix un node al arbre
	 */
	@Override
	public void afegir (K k, V v) {
		if (esBuit()) {
			arrel=new NodeArbre<K,V>(k,v);
		} else {
			if (arrel.k.equals(k)) arrel.v=v;
			else if (arrel.k.compareTo(k)>0) {
				if (arrel.fe!=null)
					arrel.fe.afegir(k, v);
				else {
					arrel.fe=new Arbre<K,V>(k,v);
				}
			}
			else if (arrel.k.compareTo(k)<0) {
				if (arrel.fd!=null)
					arrel.fd.afegir(k, v);
				else {
					arrel.fd=new Arbre<K,V>(k,v);
				}
			}
		}
	}

	/**
	 * Metode que retorna la clau de l'arrel
	 * @return la clau de l'arrel
	 */
	public K arrel() {
		if (arrel!=null) return (arrel.k);
		return null;
	}
	
	/**
	 * Retorna el node de mes a la dreta
	 * @return aux node del arbre
	 */
	public NodeArbre<K,V> mesDret () {
		if (arrel==null) return null;
		else if(arrel.fd==null) return arrel;
		else {
			NodeArbre<K,V> aux=arrel.fd.arrel;
			while(aux.fd!=null){
				aux=aux.fd.arrel;
			}
			return aux;
		}
	}
	
	/**
	 * Retorna el node de mes a l'esquerra
	 * @return aux node del arbre
	 */
	public NodeArbre<K,V> mesEsquerre () {
		if (arrel==null) return null;
		else if(arrel.fe==null) return arrel;
		else {
			NodeArbre<K,V> aux=arrel.fe.arrel;
			while(aux.fe!=null){
				aux=aux.fe.arrel;
			}
			return aux;
		}
	}

	/**
	 * Metode que elimina un node del arbre a partir de la clau
	 * @return retorna el element eliminat sino retorna null
	 */
	@Override
	public V esborrar(K k) {
		if (arrel!=null) {
			if (arrel.k.compareTo(k)>0) {
				if (arrel.fe!=null)
					arrel.fe=esborrar(k, arrel.fe);
			}
			else if (arrel.k.compareTo(k)<0) {
				if (arrel.fd!=null)
					arrel.fd=esborrar(k, arrel.fd);
			} else {
				// he d'esborrar l'arrel
				// arrel no te fills
				if ((arrel.fd==null) && (arrel.fe==null)) {
					arrel=null;
				}
				else {
					// arrel te un fill o dos
					if ((arrel.fd==null) && (arrel.fe!=null)) {
						//l'arrel te el fe
						arrel=arrel.fe.arrel;
					}
					if ((arrel.fd!=null) && (arrel.fe==null)) {
						//l'arrel te el fd
						arrel=arrel.fd.arrel;
					}
					if ((arrel.fd!=null) && (arrel.fe!=null)) {
						// arrel te dos fills
						K succKlau=arrel.fd.minim();
						V succValor=consultar(succKlau);
						esborrar(succKlau);
						arrel.k=succKlau;
						arrel.v=succValor;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Metode que esborra un node de l'arbre
	 * @param k la clau del node a eliminar
	 * @param arbre arbre amb els nodes
	 * @return retorna el element eliminat sino retorna null
	 */
	private Arbre<K,V> esborrar(K k, Arbre<K,V> arbre) {
		if (arbre!=null) {
			if (arbre.arrel.k.compareTo(k)>0) {
				if (arbre.arrel.fe!=null)
					arbre.arrel.fe=esborrar(k, arbre.arrel.fe);
			}
			if (arbre.arrel.k.compareTo(k)<0) {
				if (arbre.arrel.fd!=null)
					arbre.arrel.fd=esborrar(k, arbre.arrel.fd);
			}
			if (arbre.arrel.k.compareTo(k)==0) {
				if ((arbre.arrel.fe!=null) && (arbre.arrel.fd!=null)) {
					// te dos fills
					K succKlau=arbre.arrel.fd.minim();
					V succValor=consultar(succKlau);
					esborrar(succKlau);
					arbre.arrel.k=succKlau;
					arbre.arrel.v=succValor;
					
				} else {
					// te un o cap fill
					if (arbre.arrel.fe!=null) {
						arbre=(Arbre<K, V>) arbre.fillEsq();
					} else arbre=(Arbre<K, V>) arbre.fillDret();
				}
			}
		}
		return arbre;
	}
	
	/**
	 * Metode que comprova si existeix el node
	 * @param k la clau del node 
	 * @return true si existeix sino false 
	 */
	public boolean existeix(K k) {
		if (arrel==null) return false;
		else if (arrel.k.equals(k)) return true;
		else if (arrel.k.compareTo(k)>0) {
			if (arrel.fe!=null)
				return(arrel.fe.existeix(k));
			else return false;
		}
		else {
			if (arrel.fd!=null)
				return(arrel.fd.existeix(k));
			else return(false);
		}
	}

	/**
	 * Metode que consulta amb una clau
	 * @return retorna la informacio del node
	 */
	@Override
	public V consultar (K k) {
		if (arrel==null) return null;
		else if (arrel.k.equals(k)) return arrel.v;
		else if (arrel.k.compareTo(k)>0) {
			if (arrel.fe!=null)
				return(arrel.fe.consultar(k));
			else return null;
		}
		else {
			if (arrel.fd!=null) 
				return(arrel.fd.consultar(k));
			else return null;
		}
	}

	/**
	 * Retorna el numero d'elements
	 * @return el num d'elements
	 */
	public int numElem() {
		LlistaGenericaNoOrd<K> llista=this.inordre();
		return llista.getNum();
	}

	/**
	 * Retorna el maxim del arbre
	 * @return el node maxim
	 */
	public K maxim() {
		// tenim dos opcions per a calcular el resultat
		// 1. ultim element del recorregut inordre - cost O(n)
		LlistaGenericaNoOrd<K> llista=this.inordre();
		return llista.consultarIessim(llista.getNum()-1);
		// 2. seguiment pels fills drets fins arribar a un que no en te - cost O(alçada arbre)
		/*if (arrel==null) return null;
		else {
			if (arrel.fd!=null) {
				ABCdinamic<K, V> aux=arrel.fd;
				while (aux.fillDret()!=null) {
					aux=(ABCdinamic<K, V>) aux.fillDret();
				}
				return (aux.arrel.k);
			} else return arrel.k;
		}*/
	}

	/**
	 * Retorna el minim del arbre
	 * @return el node minim
	 */
	public K minim() {
		// tenim dos opcions per a calcular el resultat
		// 1. primer element del recorregut inordre - cost O(n)
		/*LlistaGenericaNoOrd<K> llista=this.inordre();
		return llista.consultarIessim(0);*/
		// 2. seguiment pels fills esquerres fins arribar a un que no en te - cost O(alçada arbre)
		if (arrel==null) return null;
		else {
			if (arrel.fe!=null) {
				Arbre<K, V> aux=arrel.fe;
				while (aux.fillEsq()!=null) {
					aux=(Arbre<K, V>) aux.fillEsq();
				}
				return (aux.arrel.k);
			} else return arrel.k;
		}
	}

	/**
	 * Metode que retorna el fill esquerra
	 * @return el fill esquerra
	 */
	public Arbre<K, V> fillEsq() {
		if ((arrel!=null)&&(arrel.fe!=null)) return(arrel.fe.clone());
		return null;
	}

	/**
	 * Metode que retorna el fill dret
	 * @return el fill dret
	 */
	public Arbre<K, V> fillDret() {
		if ((arrel!=null)&&(arrel.fd!=null)) return(arrel.fd.clone());
		return null;
	}

	/**
	 * Metode que retorna si esta buit
	 * @return true si es buit sino false
	 */
	public boolean esBuit() {
		return (arrel==null);
	}

	/**
	 * Llista amb el sentit inordre del arbre
	 * @return llista amb el sentit inordre
	 */
	public LlistaGenericaNoOrd<K> inordre() {
		LlistaGenericaNoOrd<K> llista=new LlistaGenericaNoOrd<K>(10);
		if (arrel!=null) {
			if (arrel.fe!=null) llista.afegirElement(arrel.fe.inordre());
			llista.afegirElement(arrel.k);
			if (arrel.fd!=null) llista.afegirElement(arrel.fd.inordre());
		}
		return llista;
	}

	/**
	 * Metode que clona el arbre
	 * @return obj copia del arbre
	 */
	@SuppressWarnings("unchecked")
	public Arbre<K, V> clone() {
		Arbre<K, V> obj=null;
		try{
            obj=(Arbre<K, V>)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println("No es pot duplicar");
        }
		obj.arrel=obj.arrel.clone();
		if (obj.fillEsq()!=null) obj.arrel.fe = (Arbre<K,V>)obj.fillEsq().clone();
		if (obj.fillDret()!=null) obj.arrel.fd = (Arbre<K,V>)obj.fillDret().clone();
        return obj;
	}

	/**
	 * Metode toString de l'arbre
	 * @return String amb l'arbre
	 */
	public String toString() {
		return "ABCdinamic [arrel=" + arrel + "]";
	}

	
}
