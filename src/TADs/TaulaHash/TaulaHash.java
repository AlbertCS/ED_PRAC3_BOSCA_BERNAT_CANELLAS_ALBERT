package TADs.TaulaHash;

import TADs.*;

public class TaulaHash<K, V> implements TAD<K, V> {

	private NodeHash<K, V>[] taulaLlistes;
	private int numElem, capacitat;
	
	@SuppressWarnings("unchecked")
	public TaulaHash(int capacitat) {
		taulaLlistes=new NodeHash[capacitat];
		numElem=0;
		this.capacitat=capacitat;
	}
	
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
	
	protected void afegir2(NodeHash<K, V> node, int posicio) {
		taulaLlistes[posicio]=node;
	}

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
	
	public IteratorHash<K, V> iterator2() {
		IteratorHash<K, V> pI=new IteratorHash<K, V>(this);
		return pI;
	}
	
	/**
	 * Aquest metode es especific per a Strings en el cas de utlitzar altre tipus de formats cal utilitzar
	 * el metode prederminat del objecte .hasCode que s'ha deixat comentat //. 
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
	
	public NodeHash<K, V> consultarIessim(int i) {
		if (i<capacitat) return(taulaLlistes[i]);
		else return(null);
	}

	public int getNumElem() {
		return numElem;
	}

	public int getCapacitat() {
		return capacitat;
	}
}
