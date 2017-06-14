package TADs.TaulaHash;

import TADs.TAD;

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
			while((aux.getSeguent()!=null) && (aux.getKey()!=k)) aux=aux.getSeguent();
			if((aux.getSeguent()==null) && (aux.getKey()!=k)){
				aux.setSeguent(new NodeHash<K, V>(k, v, null));
				this.numElem++;
			}
			else aux.setValue(v);
		}
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
			while((aux.getSeguent()!=null) && (aux.getKey()!=k)) aux=aux.getSeguent();
			if((aux.getSeguent()==null) && (aux.getKey()!=k)){
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
			while((aux.getSeguent()!=null) && (aux.getKey()!=k)) aux=aux.getSeguent();
			if((aux.getSeguent()==null) && (aux.getKey()!=k)) return null;
			else return aux.getValue();
		}
	}
	
	/**
	 * Aquest metode es especific per a Strings en el cas de utlitzar altre tipus de formats cal utilitzar
	 * el metode prederminat del objecte .hasCode que s'ha deixat comentat //. 
	 */
	private int hashCode(K k){
		String aux=(String) k;
		int valorActual=0, code=0;
		float factorCarrega=capacitat/26;
		char a='a';
		
		a=aux.charAt(0);
		valorActual=a-97;			//Obtindrem valor de a-z, es a dir, de 0-25
		code=(int) (Math.round(valorActual*factorCarrega));		//Obtenim en quina fraccio de 1/26 a partir de la primera lletra es troba la posicio de la paraula
		
		for(int i=1;i<aux.length();i++){
			a=aux.charAt(i);
			valorActual=a-97;			//Obtindrem valor de a-z, es a dir, de 0-25
			factorCarrega=factorCarrega/26;		//A dividim la seccio de la taula a la qual pertany la primera lletra de la paraula en un abecedari de nou, i busquem on correspon la segona lletra i aixi succesivament
			valorActual=(int) (Math.round(valorActual*factorCarrega));
			code+=valorActual;
		}
		
		return code;
	}
}
