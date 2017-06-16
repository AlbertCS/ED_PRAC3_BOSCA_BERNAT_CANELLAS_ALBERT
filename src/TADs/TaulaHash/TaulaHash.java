package TADs.TaulaHash;

import TADs.*;

/**
 * Classe TaulaHash
 * @param <K>
 * @param <V>
 */
public class TaulaHash<K, V> implements TAD<K, V> {

	private NodeHash<K, V>[] taulaLlistes;
	private int numElem, capacitat;
	
	/**
	 * Constructor de la taula de Hash
	 * @param capacitat capacitat de la taula de Hash
	 */
	@SuppressWarnings("unchecked")
	public TaulaHash(int capacitat) {
		taulaLlistes=new NodeHash[capacitat];
		numElem=0;
		this.capacitat=capacitat;
	}
	
	
	/**
	 * Metode que afegeix un element 
	 * @param k la clau
	 * @param v el valor 
	 */
	@Override
	public void afegir(K k, V v) {
		//int clauHash=k.hashCode()%capacitat;
		int clauHash=this.hashCode(k);
		if(taulaLlistes[clauHash]==null) {
			taulaLlistes[clauHash]=new NodeHash<K, V>(k, v, null);
			numElem++;
		}
		else{
			NodeHash<K, V> aux=taulaLlistes[clauHash];
			while((aux.getSeguent()!=null) && (!k.equals(aux.getKey()))) aux=aux.getSeguent();
			if((aux.getSeguent()==null) && (!k.equals(aux.getKey()))){
				aux.setSeguent(new NodeHash<K, V>(k, v, null));
				this.numElem++;
			}
			else aux.setValue(v);
		}
	}
	
	/**
	 * Metode que afegeix directement el node
	 * @param node node a afegir
	 * @param posicio posicio on afegir-lo
	 */
	protected void afegir2(NodeHash<K, V> node, int posicio) {
		taulaLlistes[posicio]=node;
	}

	/**
	 * Metode que esborra un element
	 * @param k la clau del element a esborrar
	 * @return retorna l'element eliminat
	 */
	@Override
	public V esborrar(K k) {
		//int clauHash=k.hashCode()%capacitat;
		int clauHash=this.hashCode(k);
		if(taulaLlistes[clauHash]==null) {
			return null;
		}
		else{
			NodeHash<K, V> aux=taulaLlistes[clauHash];
			while((aux.getSeguent()!=null) && (!k.equals(aux.getKey()))) aux=aux.getSeguent();
			if((aux.getSeguent()==null) && (!k.equals(aux.getKey()))){
				return null;
			}
			else return (aux.getValue());
		}
	}

	/**
	 * Metode que consulta
	 * @param k clau del element a consultar
	 * @return retorna l'element a consultar
	 */
	@Override
	public V consultar(K k) {
		//int clauHash=k.hashCode()%capacitat;
		int clauHash=this.hashCode(k);
		if(taulaLlistes[clauHash]==null) {
			return null;
		}
		else{
			NodeHash<K, V> aux=taulaLlistes[clauHash];
			while((aux.getSeguent()!=null) && (!k.equals(aux.getKey()))) aux=aux.getSeguent();
			if((aux.getSeguent()==null) && (!k.equals(aux.getKey()))) return null;
			else return aux.getValue();
		}
	}
	
	/**
	 * Constructor del Iterator
	 * @return pI el iterator
	 */
	public IteratorHash<K, V> iterator2() {
		IteratorHash<K, V> pI=new IteratorHash<K, V>(this);
		return pI;
	}
	
	
	/**
	 * Metode que calcula el codi hash.
	 * Aquest metode es especific per a Strings en el cas de utlitzar altre tipus de formats cal utilitzar
	 * el metode prederminat del objecte .hasCode que s'ha deixat comentat //. 
	 * @param k la clau 
	 * @return code el codi hash
	 */
	private int hashCode(K k){
		String aux=(String) k;
		float valorActual=0, code=0;
		float factorCarrega=capacitat/26;
		char a='a';
		
		a=aux.charAt(0);
		valorActual=a-97;			//Obtindrem valor de a-z, es a dir, de 0-25
		code=valorActual*factorCarrega;		//Obtenim en quina fraccio de 1/26 a partir de la primera lletra es troba la posicio de la paraula
		
		for(int i=1;i<aux.length();i++){
			a=aux.charAt(i);
			valorActual=a-97;			//Obtindrem valor de a-z, es a dir, de 0-25
			factorCarrega=factorCarrega/26;		//A dividim la seccio de la taula a la qual pertany la primera lletra de la paraula en un abecedari de nou, i busquem on correspon la segona lletra i aixi succesivament
			valorActual=valorActual*factorCarrega;
			code+=valorActual;
		}
		
		return ((int) (Math.round(code)));
	}
	
	/**
	 * Metode que consulta una posicio
	 * @param i posicio a consultar
	 * @return el node de la posicio
	 */
	protected NodeHash<K, V> consultarIessim(int i) {
		if (i<capacitat) return(taulaLlistes[i]);
		else return(null);
	}

	/**
	 * Getter del num elements
	 * @return numElem retorna el num d'elements
	 */
	public int getNumElem() {
		return numElem;
	}

	/**
	 * Getter de la capacitat de la taula Hash
	 * @return capacitat la capacitat de la taula
	 */
	public int getCapacitat() {
		return capacitat;
	}
}
