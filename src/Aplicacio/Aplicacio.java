package Aplicacio;

import java.io.*;
import java.util.*;

/**
 * Class per testejar el programa
 * @author Albert Cañellas Sole
 * @author Bernat Bosca Candel
 *
 */


public class Aplicacio {
	
	/**
	 * Metode que mostra un menu per indicar el tipus d'implementacio.
	 * @param teclat variable de tipus Scanner
	 * @return	opcio escollida
	 */
	public static int tipusImplementacio(Scanner teclat) {
		boolean opcioOk=true;
		String op;
		int opcio=0;
		
		System.out.println("Indica tipus de cua a utilitzar:\n\t1. Vector\n\t2. Circular\n\t3. Dinàmica\n\t4. JavaCollection");
		op=teclat.nextLine();
		if(Character.isDigit(op.charAt(0))) opcio=Integer.parseInt(op);
		
		while(opcioOk){
			switch(opcio){
				case 1: 
					opcioOk=false; break;
				case 2: 
					opcioOk=false; break;
				case 3: 
					opcioOk=false; break;
				case 4: 
					opcioOk=false; break;
				default:
					System.out.println("Valor incorrecte. Indica tipus de cua a utilitzar:\n\t1. Vector\n\t2. Circular\n\t3. Dinàmica\n\t4. JavaCollection");
					op=teclat.nextLine();
					if(Character.isDigit(op.charAt(0))) opcio=Integer.parseInt(op); break;
			}
		}
		return opcio;
	}
	
	/**
	 * Metode que crea el tipus de cua corresponent
	 * @param opcio implementacio escollida
	 * @param mida dimensio de la cua
	 * @param cua	la cua implementada
	 * @param clau	la clau amb la que tractar el missatge
	 * @return retorna la cua corresponent
	 */
	/*public static TADDades<Integer> implementacio(int opcio, int mida, TADDades<Integer> cua, String clau) {
		switch(opcio){
			case 1:
				cua=new CuaVector<Integer>(mida); break;
			case 2:
				cua=new CuaCircular<Integer>(mida); break;
			case 3:
				cua=new CuaDinamic<Integer>(); break;
			case 4:
				cua=new CuaJava<Integer>(); break;
			default: break;
		}
		try{
			for(int i=0;i<mida;i++)	cua.encuar(new Integer (clau.charAt(i)-48));
		} catch (CuaPlena e){
			System.out.println("Aquesta circunstancia no deuria de passar mai.");
		}
		return cua;
	}*/
	
	/**
	 * Metode que tracta la lletra, sumant o restant el valor de la clau corresponent
	 * @param a lletra a tractar
	 * @param num valor de la clau a aplicar
	 * @param signe indica si s'ha de sumar(xifrar) o restar(desxifrar)
	 * @return la lletra tractada
	 */
	public static char tractarLletra(char a, int num, char signe) {
		char mayus[]={'R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I'};
		char minus[]={'r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i'};
		int i=0;
		
		if(signe=='+') {
			if((int)(a)<82) a=(char) ((int)(a)+num);	//Lletra mayus sense problemes
			else if((int)(a)<91) {	//Lletra mayus amb problemes
				while(a!=mayus[i]) i++;
				a= mayus[i+num];
			} 
			else if (((int)(a)>96)&((int)(a)<114)) a=(char) ((int)(a)+num);	//Lletra minus sense problemes
			else if((int)(a)<123) {	//Lletra minus amb problemes
				while(a!=minus[i]) i++;
				a=minus[i+num];
			}
		}
		else {
			i=9;
			if(((int)(a)>64)&((int)(a)<74)) {	//Lletra mayus amb problemes
				while(a!=mayus[i]) i++;
				a= mayus[i-num];	
			}
			else if((int)(a)<91) a=(char) ((int)(a)-num);	//Lletra mayus sense problemes
			else if (((int)(a)>96)&((int)(a)<106)) {	//Lletra minus amb problemes
				while(a!=minus[i]) i++;
				a=minus[i-num];
			}
			else if((int)(a)<123) a=(char) ((int)(a)-num);	//Lletra minus sense problemes
				
		}
		return a;
	}
	
	/**
	 * Metode que tractara les dades amb el metode triat
	 * @param nomFitxer	nom del fitxer d'on es llegirant les dades, amb aquest nom es creara el fitxer on guardar les dades
	 * @param cuaClau cua que conte la clau
	 * @param signe indica si s'ha de sumar(xifrar) o restar(desxifrar)
	 */
	/*public static void tractarDades(String nomFitxer, TADDades<Integer> cuaClau, char signe) {
		
		try {
			//Variables
			BufferedReader f=new BufferedReader(new FileReader(nomFitxer+".txt"));
			String frase, nomGuardar;
			Character a;
			Integer num;

			//Xifrar
			if (signe=='+') nomGuardar=(nomFitxer+"_vX.txt");
			//Desxifrar
			else nomGuardar=(nomFitxer+"_vD.txt");
			BufferedWriter g=new BufferedWriter(new FileWriter(nomGuardar));
			
			frase=f.readLine();
			while(frase!=null){ 
				for(int i=0;i<frase.length();i++){
					a=frase.charAt(i);
					if(Character.isLetter(a)){
						try{
							num=cuaClau.desencuar();
							a = tractarLletra(a.charValue(), num.intValue(), signe);
							g.write(String.valueOf(a));
							cuaClau.encuar(num);
						} catch(CuaBuida|CuaPlena e){
							System.out.println("Aquesta circunstancia no deuria de passar mai.");
						}
					}
					else g.write(String.valueOf(a));
				}
			frase = f.readLine();
			if(frase!=null) g.newLine();
			}
			g.close();
			f.close();
		}catch (IOException e) {
			System.err.println("Error de tipus IOException.");
		}
	}*/
	
	/**
	 * Metode que pregunta si es vol xifrar(+) o desxifrar(-) i guarda el signe dependent de l'opcio triada
	 * @param teclat variable de tipus Scanner
	 * @return retorna el signe depenent de l'opcio triada
	 */
	public static char xifrarDesxifrar(Scanner teclat) {
		boolean opcioOk=false;
		int opcio=0;
		char signe='0';
		String op;
		
		System.out.println("Indica si vols xifrar o desxifrar:\n\t1. Xifrar\n\t2. Desxifrar");
		op=teclat.nextLine();
		if(Character.isDigit(op.charAt(0))) opcio=Integer.parseInt(op);
		
		while(!opcioOk){
			switch(opcio){
				//Xifrar
				case 1: signe='+'; opcioOk=true; break;
				//Desxifrar
				case 2: signe='-'; opcioOk=true; break;
				default:
					System.out.println("Valor incorrecte. Indica si vols xifrar o desxifrar:\n\t1. Xifrar\n\t2. Desxifrar");
					op=teclat.nextLine();
					if(Character.isDigit(op.charAt(0))) opcio=Integer.parseInt(op); break;
			}
		}
		return signe;
	}
	
	/**
	 * Metode que comprova que el nom del fitxer sigui correcte
	 * @param teclat variable de tipus Scanner
	 * @return nomFitxer retorna el nom del teclat
	 */
	public static String nomCorrecte(Scanner teclat) {
		String nomFitxer;
		boolean isOk=true;
		
		System.out.println("Indica el nom del fitxer. Si no has creat cap, el nom que has de ficar és 'text'.");
		nomFitxer=teclat.nextLine();
		File nameFile = new File(nomFitxer+".txt");
		while(isOk){
			if(nameFile.isFile()) isOk=false;
			else {
				System.out.println("El fitxer amb el nom "+nomFitxer+" NO existeix. Indica un altre nom: ");
				nomFitxer=teclat.nextLine();
				nameFile = new File(nomFitxer+".txt");
			}
		}
		return nomFitxer;
	}
	
	/**
	 * Metode que comprova que la clau sigui correcta
	 * @param teclat variable de tipus Scanner
	 * @return clau retorna la clau
	 */
	public static String clauCorrecta(Scanner teclat) {
		int i=0;
		String clau;
		boolean isOk=true, nonum=true;
		
		System.out.println("Indica la clau:");
		clau=teclat.nextLine();
		while(isOk){
			while((nonum) && (i<clau.length())){
				if(Character.isDigit(clau.charAt(i))) i++;
				else nonum=false;				
			}
			if((nonum==false) && (i>0)){
				System.out.println("La clau que has introduit te algun caràcter incorrecte. Indica la clau:");
				clau=teclat.nextLine();
				isOk=true;
			}
			else isOk=false;
		}
		return clau;
	}
	/**
	 * 	/-Main-/
	 * @param args args
	 */
	public static void main(String[] args) {
		Scanner teclat=new Scanner(System.in);
		//TADDades<Integer> cuaClau=null;
		String nomFitxer, clau;
		int opcio;
		char signe;
		long tempsi, tempsf;
		
		//Tipus de implementació
		opcio=tipusImplementacio(teclat);
		
		//Nom fitxer
		nomFitxer=nomCorrecte(teclat);

		//Clau
		clau=clauCorrecta(teclat);
		
		//Xifrar/Desxifrar
		signe=xifrarDesxifrar(teclat);
		
		//Operacions
		tempsi=System.nanoTime();
		//cuaClau=implementacio(opcio, clau.length(), cuaClau, clau);
		//tractarDades(nomFitxer, cuaClau, signe);
		tempsf=System.nanoTime();
		
		System.out.println("Les Dades s'han tractat correctament.\n");
		System.out.println("El programa ha tardat: "+(tempsf-tempsi)+"ns.");
		
		teclat.close();
	}
}
